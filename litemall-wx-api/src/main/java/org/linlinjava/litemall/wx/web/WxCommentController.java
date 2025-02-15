package org.linlinjava.litemall.wx.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 用户评论服务
 */
@RestController
@RequestMapping("/wx/comment")
@Validated
public class WxCommentController {
    private final Log logger = LogFactory.getLog(WxCommentController.class);

    @Autowired
    private LitemallCommentService commentService;
    @Autowired
    private LitemallUserService litemallUserService;
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private LitemallCircleService circleService;
    @Autowired
    private LitemallActivityService activityService;
    @Autowired
    private LitemallQuestionService questionService;
    @Autowired
    private LitemallGroupService groupService;
    @Autowired
    private LitemallFishPondsService fishPondsService;
    @Autowired
    private LitemallTopicService topicService;
    @Autowired
    private LitemallCollectService collectService;

    private static int type = 9;

    private Object validate(LitemallComment comment) {
        String content = comment.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }

        Short star = comment.getStar();
        if (star == null) {
            return ResponseUtil.badArgument();
        }
        if (star < 0 || star > 5) {
            return ResponseUtil.badArgumentValue();
        }

        Byte type = comment.getType();
        Integer valueId = comment.getValueId();
        if (type == null || valueId == null) {
            return ResponseUtil.badArgument();
        }
        if (type == 0) {
            if (goodsService.findById(valueId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        } else if (type == 1) {
            if (topicService.findById(valueId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        } else {
            return ResponseUtil.badArgumentValue();
        }
        Boolean hasPicture = comment.getHasPicture();
        if (hasPicture == null || !hasPicture) {
            comment.setPicUrls(new String[0]);
        }
        return null;
    }

    /**
     * 发表评论
     *
     * @param userId  用户ID
     * @param comment 评论内容
     * @return 发表评论操作结果
     */
    @PostMapping("save")
    public Object post(@LoginUser Integer userId, @RequestBody LitemallComment comment) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
//        Object error = validate(comment);
//        if (error != null) {
//            return error;
//        }

        comment.setUserId(userId);
        commentService.save(comment);
        return ResponseUtil.ok(comment);
    }

    /**
     * 评论数量
     *
     * @param type    类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @return 评论数量
     */
    @GetMapping("count")
    public Object count(@NotNull Byte type, @NotNull Integer valueId) {
        int allCount = commentService.count(type, valueId, 0);
        int hasPicCount = commentService.count(type, valueId, 1);
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("allCount", allCount);
        entity.put("hasPicCount", hasPicCount);
        return ResponseUtil.ok(entity);
    }

    /**
     * 评论列表
     *
     * @param type     类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId  商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param page     分页页数
     * @param limit    分页大小
     * @return 评论列表
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId, @NotNull Byte type,
                       @NotNull Integer valueId,
                       @NotNull Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallComment> commentList = commentService.query(type, valueId, showType, page, limit);

        List<Map<String, Object>> commentVoList = new ArrayList<>(commentList.size());
        for (LitemallComment comment : commentList) {
            Map<String, Object> commentVo = new HashMap<>();
            commentVo.put("addTime", comment.getAddTime());
            commentVo.put("content", comment.getContent());
            commentVo.put("picList", comment.getPicUrls());
            commentVo.put("id", comment.getId());

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser litemallUser = litemallUserService.findDetailById(comment.getUserId());
            userVo.put("user", litemallUser);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, litemallUser.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollectMe(litemallUser.getId(), 10));
            commentVo.put("publishUser", userVo);

            String reply = commentService.queryReply(comment.getId());
            commentVo.put("reply", reply);

            // 用户收藏
            Random rand = new Random();
            int random = rand.nextInt(9999) + 9999;
            int userHasCollect = 0;
            int collectCount = 0;
            if (comment.getId() != null & userId != null) {
                userHasCollect = collectService.count(userId, comment.getId(), this.type);
            }
            collectCount = collectService.countCollectMe(comment.getId(), this.type);
            commentVo.put("userHasCollect", userHasCollect);
            commentVo.put("collectCount", collectCount + 0);

            commentVoList.add(commentVo);
        }
        return ResponseUtil.okList(commentVoList, commentList);
    }

    @GetMapping("listall")
    public Object listall(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallComment> commentList = commentService.queryAll(page, limit);

        List<Map<String, Object>> commentVoList = new ArrayList<>(commentList.size());
        for (LitemallComment comment : commentList) {
            Map<String, Object> commentVo = new HashMap<>();
            commentVo.put("addTime", comment.getAddTime());
            commentVo.put("content", comment.getContent());
            commentVo.put("picList", comment.getPicUrls());
            commentVo.put("id", comment.getId());

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser litemallUser = litemallUserService.findDetailById(comment.getUserId());
            userVo.put("user", litemallUser);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, litemallUser.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollectMe(litemallUser.getId(), 10));
            commentVo.put("publishUser", userVo);

            String reply = commentService.queryReply(comment.getId());
            commentVo.put("reply", reply);

            // 用户收藏
            Random rand = new Random();
            int random = rand.nextInt(9999) + 9999;
            int userHasCollect = 0;
            int collectCount = 0;
            if (comment.getId() != null & userId != null) {
                userHasCollect = collectService.count(userId, comment.getId(), this.type);
            }
            collectCount = collectService.countCollectMe(comment.getId(), this.type);
            commentVo.put("userHasCollect", userHasCollect);
            commentVo.put("collectCount", collectCount + 0);

            switch (comment.getType()) {
                case 0:
                    LitemallGoods litemallGoods = goodsService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallGoods);
                    break;
                case 4:
                    LitemallCircle litemallCircle = circleService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallCircle);
                    break;
                case 5:
                    LitemallFishPonds litemallFishPonds = fishPondsService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallFishPonds);
                    break;
                case 6:
                    LitemallQuestion litemallQuestion = questionService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallQuestion);
                    break;
                case 7:
                    LitemallGroup litemallGroup = groupService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallGroup);
                    break;
                case 8:
                    LitemallActivity litemallActivity = activityService.findByIdVO(comment.getValueId());
                    commentVo.put("info", litemallActivity);
                    break;
            }

            commentVoList.add(commentVo);
        }
        return ResponseUtil.okList(commentVoList, commentList);
    }

    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallComment litemallComment) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallComment.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        commentService.deleteById(id);
        return ResponseUtil.ok();
    }
}