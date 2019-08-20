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
@RequestMapping("/wx/fishponds")
@Validated
public class WxFishPondsController {
    private final Log logger = LogFactory.getLog(WxFishPondsController.class);

    @Autowired
    private LitemallFishPondsService fishPondsService;

    @Autowired
    private LitemallFishPondsUserService FishPondsUserService;

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

    private int type = 5;

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
        LitemallFishPonds info = fishPondsService.findById(id);

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
                userVo.put("collectCount", collectService.countCollectMe(user.getId(), 10));
                c.put("publishUser", userVo);
                // 用户收藏
                Random rand = new Random();
                int random = rand.nextInt(9999) + 9999;
                int userHasCollect = 0;
                int collectCount = 0;
                if (comment.getId() != null & userId != null) {
                    userHasCollect = collectService.count(userId, comment.getId(), 9);
                }
                collectCount = collectService.countCollectMe(comment.getId(), 9);
                c.put("userHasCollect", userHasCollect);
                c.put("collectCount", collectCount + 0);
                commentsVo.add(c);
            }
            Map<String, Object> commentList = new HashMap<>();
            commentList.put("count", commentCount);
            commentList.put("data", commentsVo);
            return commentList;
        };

        // 收藏列表
        Callable<Map> collectListCallable = () -> {
            List<LitemallCollect> collectList = collectService.queryListByType(id, (byte) type, 1, 20, "add_time", "desc");
            List<Map<String, Object>> collectVo = new ArrayList<>(collectList.size());
            long commentCount = PageInfo.of(collectList).getTotal();
            for (LitemallCollect litemallCollect : collectList) {
                Map<String, Object> c = new HashMap<>();
                LitemallUser user = userService.findDetailById(litemallCollect.getUserId());
                c.put("user", user);
                collectVo.add(c);
            }
            Map<String, Object> collectListVo = new HashMap<>();
            collectListVo.put("count", commentCount);
            collectListVo.put("data", collectVo);
            return collectListVo;
        };
        // 加入列表
        Callable<List<Map<String, Object>>> joinListCallable = () -> {
            List<LitemallFishPondsUser> litemallFishPondsUsers = FishPondsUserService.queryFishPondsUser(id, 0, 100);
            List<Map<String, Object>> usersVo = new ArrayList<>(litemallFishPondsUsers.size());
            for (LitemallFishPondsUser litemallFishPondsUser : litemallFishPondsUsers) {
                LitemallUser FishPondsUser = userService.findDetailById(litemallFishPondsUser.getUserId());
                Map<String, Object> c = new HashMap<>();
                c.put("user", FishPondsUser);
                // 用户收藏
                Random rand = new Random();
                int random = rand.nextInt(9999) + 9999;
                int userHasCollect = 0;
                int collectCount = 0;
                if (FishPondsUser.getId() != null & userId != null) {
                    userHasCollect = collectService.count(userId, FishPondsUser.getId(), 10);
                }
                collectCount = collectService.countCollectMe(FishPondsUser.getId(), 10);
                c.put("userHasCollect", userHasCollect);
                c.put("collectCount", collectCount + 0);
                usersVo.add(c);
            }
            return usersVo;
        };
        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, id, type);
        }

        // 用户加入
        int userHasJoin = 0;
        if (userId != null) {
            userHasJoin = FishPondsUserService.count(userId, id);
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

        Map<String, Object> userVo = new HashMap<>();
        LitemallUser user = null;
        if (info != null) {
            user = userService.findDetailById(info.getUserId());
        }
        userVo.put("user", user);
        if (userId != null) {
            userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
        }
        userVo.put("collectCount", collectService.countCollectMe(user.getId(), 10));

        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);
        FutureTask<Map> collectListCallableTsk = new FutureTask<>(collectListCallable);
        FutureTask<List<Map<String, Object>>> joinListCallableTsk = new FutureTask<>(joinListCallable);

        executorService.submit(commentsCallableTsk);
        executorService.submit(collectListCallableTsk);
        executorService.submit(joinListCallableTsk);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("collectCount", collectService.countCollectMe(id, this.type));
            data.put("userHasJoin", userHasJoin);
            data.put("share", SystemConfig.isAutoCreateShareImage());
            data.put("publishUser", userVo);
            data.put("comment", commentsCallableTsk.get());
            data.put("collect", collectListCallableTsk.get());
            data.put("joinUsers", joinListCallableTsk.get());
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
        List<LitemallFishPonds> fishPonds = fishPondsService.queryFishPonds(0, 5);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", fishPonds);
        return ResponseUtil.ok(entity);
    }

    @GetMapping("listall")
    public Object listall(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallFishPonds> FishPondsList = fishPondsService.queryFishPonds(page, limit);

        List<Map<String, Object>> FishPondsVoList = new ArrayList<>(FishPondsList.size());
        for (LitemallFishPonds FishPonds : FishPondsList) {
            Map<String, Object> FishPondsVo = new HashMap<>();
            FishPondsVo.put("FishPondsInfo", FishPonds);
            if (userId != null) {
                FishPondsVo.put("userHasCollect", collectService.count(userId, FishPonds.getId(), this.type));
            }
            FishPondsVo.put("collectCount", collectService.countCollectMe(FishPonds.getId(), this.type));

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser user = userService.findDetailById(FishPonds.getUserId());
            userVo.put("user", user);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollectMe(user.getId(), 10));
            FishPondsVo.put("publishUser", userVo);

            List<LitemallFishPondsUser> litemallFishPondsUsers = FishPondsUserService.queryFishPondsUser(FishPonds.getId(), 0, 100);
            List<Map<String, Object>> usersVo = new ArrayList<>(litemallFishPondsUsers.size());
            for (LitemallFishPondsUser litemallFishPondsUser : litemallFishPondsUsers) {
                LitemallUser FishPondsUser = userService.findDetailById(litemallFishPondsUser.getUserId());
                Map<String, Object> c = new HashMap<>();
                c.put("user", FishPondsUser);
                // 用户收藏
                Random rand = new Random();
                int random = rand.nextInt(9999) + 9999;
                int userHasCollect = 0;
                int collectCount = 0;
                if (FishPondsUser.getId() != null & userId != null) {
                    userHasCollect = collectService.count(userId, FishPondsUser.getId(), 10);
                }
                collectCount = collectService.countCollectMe(FishPondsUser.getId(), 10);
                c.put("userHasCollect", userHasCollect);
                c.put("collectCount", collectCount + 0);
                usersVo.add(c);
            }
            FishPondsVo.put("joinUsers", usersVo);

            FishPondsVoList.add(FishPondsVo);
        }
        return ResponseUtil.okList(FishPondsVoList, FishPondsList);
    }

    @PostMapping("join")
    public Object join(@LoginUser Integer userId, @RequestBody LitemallFishPonds litemallFishPonds) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallFishPonds.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        LitemallFishPondsUser litemallFishPondsUser = FishPondsUserService.findByIdVO(userId, id);
        if (litemallFishPondsUser == null) {
            litemallFishPondsUser = new LitemallFishPondsUser();
            litemallFishPondsUser.setUserId(userId);
            litemallFishPondsUser.setFishPondsId(id);
            FishPondsUserService.add(litemallFishPondsUser);
            return ResponseUtil.ok(litemallFishPondsUser.getId());
        } else {
            return ResponseUtil.fail(1001, "不能重复加入");
        }
    }

    @PostMapping("quit")
    public Object quit(@LoginUser Integer userId, @RequestBody LitemallFishPonds litemallFishPonds) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallFishPonds.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        LitemallFishPondsUser litemallFishPondsUser = FishPondsUserService.findByIdVO(userId, id);
        if (litemallFishPondsUser == null) {
            return ResponseUtil.fail(1002, "不在该组织内");
        } else {
            FishPondsUserService.deleteById(litemallFishPondsUser.getId());
            return ResponseUtil.ok();
        }
    }

    /**
     * @return 在售的鱼塘总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = fishPondsService.queryOnActive();
        return ResponseUtil.ok(goodsCount);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallFishPonds fishPonds) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (fishPonds.getId() == null || fishPonds.getId().equals(0)) {
            fishPonds.setId(null);
            fishPonds.setUserId(userId);
            fishPondsService.add(fishPonds);
        } else {
            fishPonds.setUserId(userId);
            if (fishPondsService.updateById(fishPonds) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(fishPonds.getId());
    }

    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallFishPonds fishPonds) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = fishPonds.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        fishPondsService.deleteById(id);
        return ResponseUtil.ok();
    }

}