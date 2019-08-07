package org.linlinjava.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.system.SystemConfig;
import org.linlinjava.litemall.core.util.ResponseUtil;
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
import java.util.concurrent.*;

/**
 * 鱼塘服务
 */
@RestController
@RequestMapping("/wx/circle")
@Validated
public class WxCircleController {
    private final Log logger = LogFactory.getLog(WxCircleController.class);

    @Autowired
    private LitemallCircleService CircleService;

    @Autowired
    private LitemallCommentService commentService;

    @Autowired
    private LitemallUserService userService;

    @Autowired
    private LitemallCollectService collectService;

    @Autowired
    private LitemallFootprintService footprintService;

    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);

    private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

    /**
     * 鱼塘详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param userId 用户ID
     * @param id     商品ID
     * @return 商品详情
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        // 商品信息
        LitemallCircle info = CircleService.findById(id);

        // 评论
        Callable<Map> commentsCallable = () -> {
            List<LitemallComment> comments = commentService.queryGoodsByGid(id, 0, 20);
            List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
            long commentCount = PageInfo.of(comments).getTotal();
            for (LitemallComment comment : comments) {
                Map<String, Object> c = new HashMap<>();
                c.put("id", comment.getId());
                c.put("addTime", comment.getAddTime());
                c.put("content", comment.getContent());
                LitemallUser user = userService.findById(comment.getUserId());
                c.put("nickname", user == null ? "" : user.getNickname());
                c.put("avatar", user == null ? "" : user.getAvatar());
                c.put("picList", comment.getPicUrls());
                commentsVo.add(c);
            }
            Map<String, Object> commentList = new HashMap<>();
            commentList.put("count", commentCount);
            commentList.put("data", commentsVo);
            return commentList;
        };

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, id);
        }

        // 记录用户的足迹 异步处理
        if (userId != null) {
            executorService.execute(() -> {
                LitemallFootprint footprint = new LitemallFootprint();
                footprint.setUserId(userId);
                footprint.setGoodsId(id);
                footprintService.add(footprint);
            });
        }

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("share", SystemConfig.isAutoCreateShareImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.ok(data);
    }

    /**
     * @return 鱼塘列表
     */
    @GetMapping("list")
    public Object list() {
        //查询列表数据
        List<LitemallCircle> Circle = CircleService.queryCircle(0, 5);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", Circle);
        return ResponseUtil.ok(entity);
    }

    /**
     * @return 在售的鱼塘总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = CircleService.queryOnActive();
        return ResponseUtil.ok(goodsCount);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallCircle circle) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (circle.getId() == null || circle.getId().equals(0)) {
            circle.setId(null);
            circle.setUserId(userId);
            CircleService.add(circle);
        } else {
            circle.setUserId(userId);
            if (CircleService.updateById(circle) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(circle.getId());
    }

    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallCircle circle) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = circle.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        CircleService.deleteById(id);
        return ResponseUtil.ok();
    }
}