package org.linlinjava.litemall.wx.web;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户收藏服务
 */
@RestController
@RequestMapping("/wx/collect")
@Validated
public class WxCollectController {
    private final Log logger = LogFactory.getLog(WxCollectController.class);

    @Autowired
    private LitemallCollectService collectService;
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private LitemallUserService litemallUserService;
    @Autowired
    private LitemallActivityService litemallActivityService;
    @Autowired
    private LitemallCircleService litemallCircleService;
    @Autowired
    private LitemallFishPondsService litemallFishPondsService;
    @Autowired
    private LitemallQuestionService litemallQuestionService;
    @Autowired
    private LitemallGroupService litemallGroupService;
    @Autowired
    private LitemallCommentService litemallCommentService;

    /**
     * 用户收藏列表
     *
     * @param userId 用户ID
     * @param type   类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param page   分页页数
     * @param limit  分页大小
     * @return 用户收藏列表
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId,
                       @NotNull Byte type,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCollect> collectList = collectService.queryByType(userId, type, page, limit, sort, order);

        List<Object> collects = new ArrayList<>(collectList.size());
        for (LitemallCollect collect : collectList) {
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("id", collect.getId());
            c.put("type", collect.getType());
            c.put("valueId", collect.getValueId());

            if (type == 0) {
                LitemallGoods goods = goodsService.findById(collect.getValueId());
                c.put("goods", goods);
                c.put("collectCount", collectService.countCollectMe(goods.getId(), type));
            }
            if (type == 10) {
                LitemallUser user = litemallUserService.findDetailById(collect.getValueId());
                c.put("user", user);
                c.put("collectCount", collectService.countCollectMe(user.getId(), type));
            }
            if (type == 4) {
                LitemallCircle circle = litemallCircleService.findById(collect.getValueId());
                c.put("circle", circle);
                c.put("collectCount", collectService.countCollectMe(circle.getId(), type));
            }
            if (type == 5) {
                LitemallFishPonds fishPonds = litemallFishPondsService.findById(collect.getValueId());
                c.put("FishPondsInfo", fishPonds);
                c.put("collectCount", collectService.countCollectMe(fishPonds.getId(), type));
            }
            if (type == 6) {
                LitemallQuestion question = litemallQuestionService.findById(collect.getValueId());
                c.put("question", question);
                c.put("collectCount", collectService.countCollectMe(question.getId(), type));
            }
            if (type == 7) {
                LitemallGroup group = litemallGroupService.findById(collect.getValueId());
                c.put("GroupInfo", group);
                c.put("collectCount", collectService.countCollectMe(group.getId(), type));
            }
            if (type == 8) {
                LitemallActivity activity = litemallActivityService.findById(collect.getValueId());
                c.put("activityInfo", activity);
                c.put("collectCount", collectService.countCollectMe(activity.getId(), type));
            }
            if (type == 9) {
                LitemallComment comment = litemallCommentService.findById(collect.getValueId());
                c.put("comment", comment);
                c.put("collectCount", collectService.countCollectMe(comment.getId(), type));

                Map<String, Object> userVo = new HashMap<>();
                LitemallUser litemallUser = litemallUserService.findDetailById(comment.getUserId());
                userVo.put("user", litemallUser);
                if (userId != null) {
                    userVo.put("userHasCollect", collectService.count(userId, litemallUser.getId(), 10));
                }
                userVo.put("collectCount", collectService.countCollectMe(litemallUser.getId(), 10));
                c.put("publishUser", userVo);
            }

            collects.add(c);
        }

        return ResponseUtil.okList(collects, collectList);
    }

    @GetMapping("listme")
    public Object listme(@LoginUser Integer userId,
                         @NotNull Byte type,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit,
                         @Sort @RequestParam(defaultValue = "add_time") String sort,
                         @Order @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCollect> collectList = collectService.querymeByType(userId, type, page, limit, sort, order);

        List<Object> collects = new ArrayList<>(collectList.size());
        for (LitemallCollect collect : collectList) {
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("id", collect.getId());
            c.put("type", collect.getType());
            c.put("userId", collect.getUserId());

            if (type == 10) {
                LitemallUser user = litemallUserService.findDetailById(collect.getUserId());
                c.put("user", user);
                c.put("collectCount", collectService.countCollectMe(user.getId(), type));
            }

            collects.add(c);
        }

        return ResponseUtil.okList(collects, collectList);
    }

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     * @param userId 用户ID
     * @param body   请求内容，{ type: xxx, valueId: xxx }
     * @return 操作结果
     */
    @PostMapping("addordelete")
    public Object addordelete(@LoginUser Integer userId, @RequestBody String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Byte type = JacksonUtil.parseByte(body, "type");
        Integer valueId = JacksonUtil.parseInteger(body, "valueId");
        if (!ObjectUtils.allNotNull(type, valueId)) {
            return ResponseUtil.badArgument();
        }

        LitemallCollect collect = collectService.queryByTypeAndValue(userId, type, valueId);

        if (collect != null) {
            collectService.deleteById(collect.getId());
        } else {
            collect = new LitemallCollect();
            collect.setUserId(userId);
            collect.setValueId(valueId);
            collect.setType(type);
            collectService.add(collect);
        }

        return ResponseUtil.ok();
    }
}