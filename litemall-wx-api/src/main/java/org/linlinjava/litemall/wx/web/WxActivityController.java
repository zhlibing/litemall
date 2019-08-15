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
import java.util.*;
import java.util.concurrent.*;

/**
 * 鱼塘服务
 */
@RestController
@RequestMapping("/wx/activity")
@Validated
public class WxActivityController {
    private final Log logger = LogFactory.getLog(WxActivityController.class);

    @Autowired
    private LitemallActivityService ActivityService;

    @Autowired
    private LitemallActivityUserService activityUserService;

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

    private int type = 8;

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
        LitemallActivity info = ActivityService.findById(id);

        // 评论
        Callable<Map> commentsCallable = () -> {
            List<LitemallComment> comments = commentService.queryGoodsByGid(id, type, 0, 20);
            List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
            long commentCount = PageInfo.of(comments).getTotal();
            for (LitemallComment comment : comments) {
                Map<String, Object> c = new HashMap<>();
                c.put("id", comment.getId());
                c.put("userId", comment.getUserId());
                c.put("addTime", comment.getAddTime());
                c.put("content", comment.getContent());
                c.put("picList", comment.getPicUrls());
                Map<String, Object> userVo = new HashMap<>();
                LitemallUser user = userService.findDetailById(comment.getUserId());
                userVo.put("user", user);
                if (userId != null) {
                    userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
                }
                userVo.put("collectCount", collectService.countCollect(user.getId(), 10));
                c.put("publishUser", userVo);
                // 用户收藏
                Random rand = new Random();
                int random = rand.nextInt(9999) + 9999;
                int userHasCollect = 0;
                int collectCount = 0;
                if (comment.getId() != null & userId != null) {
                    userHasCollect = collectService.count(userId, comment.getId(), 9);
                }
                collectCount = collectService.countCollect(comment.getId(), 9);
                c.put("userHasCollect", userHasCollect);
                c.put("collectCount", collectCount + 0);
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
            userHasCollect = collectService.count(userId, id, type);
        }

        // 用户加入
        int userHasJoin = 0;
        if (userId != null) {
            userHasJoin = activityUserService.count(userId, id);
        }

        // 记录用户的足迹 异步处理
        if (userId != null) {
            executorService.execute(() -> {
                LitemallFootprint footprint = new LitemallFootprint();
                footprint.setUserId(userId);
                footprint.setGoodsId(id);
                footprint.setType((byte) type);
                footprintService.add(footprint);
            });
        }

        LitemallUser user = null;
        if (info != null) {
            user = userService.findDetailById(info.getUserId());
        }

        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);

        executorService.submit(commentsCallableTsk);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("collectCount", collectService.countCollect(id, this.type));
            data.put("userHasJoin", userHasJoin);
            data.put("share", SystemConfig.isAutoCreateShareImage());
            data.put("user", user);
            data.put("comment", commentsCallableTsk.get());
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
        List<LitemallActivity> Activity = ActivityService.queryActivity(0, 5);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", Activity);
        return ResponseUtil.ok(entity);
    }

    @GetMapping("listall")
    public Object listall(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallActivity> activityList = ActivityService.queryActivity(page, limit);

        List<Map<String, Object>> activityVoList = new ArrayList<>(activityList.size());
        for (LitemallActivity activity : activityList) {
            Map<String, Object> activityVo = new HashMap<>();
            activityVo.put("activityInfo", activity);
            if (userId != null) {
                activityVo.put("userHasCollect", collectService.count(userId, activity.getId(), this.type));
            }
            activityVo.put("collectCount", collectService.countCollect(activity.getId(), this.type));

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser user = userService.findDetailById(activity.getUserId());
            userVo.put("user", user);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollect(user.getId(), 10));
            activityVo.put("publishUser", userVo);

            List<LitemallActivityUser> litemallActivityUsers = activityUserService.queryActivityUser(activity.getId(), 0, 100);
            List<Map<String, Object>> usersVo = new ArrayList<>(litemallActivityUsers.size());
            for (LitemallActivityUser litemallActivityUser : litemallActivityUsers) {
                LitemallUser ActivityUser = userService.findDetailById(litemallActivityUser.getUserId());
                Map<String, Object> c = new HashMap<>();
                c.put("user", ActivityUser);
                // 用户收藏
                Random rand = new Random();
                int random = rand.nextInt(9999) + 9999;
                int userHasCollect = 0;
                int collectCount = 0;
                if (ActivityUser.getId() != null & userId != null) {
                    userHasCollect = collectService.count(userId, ActivityUser.getId(), 10);
                }
                collectCount = collectService.countCollect(ActivityUser.getId(), 10);
                c.put("userHasCollect", userHasCollect);
                c.put("collectCount", collectCount + 0);
                usersVo.add(c);
            }
            activityVo.put("joinUsers", usersVo);

            activityVoList.add(activityVo);
        }
        return ResponseUtil.okList(activityVoList, activityList);
    }

    @PostMapping("join")
    public Object join(@LoginUser Integer userId, @RequestBody LitemallActivity litemallActivity) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallActivity.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        LitemallActivityUser litemallActivityUser = activityUserService.findByIdVO(userId, id);
        if (litemallActivityUser == null) {
            litemallActivityUser = new LitemallActivityUser();
            litemallActivityUser.setUserId(userId);
            litemallActivityUser.setActivityId(id);
            activityUserService.add(litemallActivityUser);
            return ResponseUtil.ok(litemallActivityUser.getId());
        } else {
            return ResponseUtil.fail(1001, "不能重复加入");
        }
    }

    @PostMapping("quit")
    public Object quit(@LoginUser Integer userId, @RequestBody LitemallActivity litemallActivity) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallActivity.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        LitemallActivityUser litemallActivityUser = activityUserService.findByIdVO(userId, id);
        if (litemallActivityUser == null) {
            return ResponseUtil.fail(1002, "不在该组织内");
        } else {
            activityUserService.deleteById(litemallActivityUser.getId());
            return ResponseUtil.ok();
        }
    }

    /**
     * @return 在售的鱼塘总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = ActivityService.queryOnActive();
        return ResponseUtil.ok(goodsCount);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallActivity litemallActivity) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (litemallActivity.getId() == null || litemallActivity.getId().equals(0)) {
            litemallActivity.setId(null);
            litemallActivity.setUserId(userId);
            ActivityService.add(litemallActivity);
        } else {
            litemallActivity.setUserId(userId);
            if (ActivityService.updateById(litemallActivity) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(litemallActivity.getId());
    }

    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallActivity litemallActivity) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallActivity.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        ActivityService.deleteById(id);
        return ResponseUtil.ok();
    }
}