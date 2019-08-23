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
@RequestMapping("/wx/question")
@Validated
public class WxQuestionController {
    private final Log logger = LogFactory.getLog(WxQuestionController.class);

    @Autowired
    private LitemallQuestionService QuestionService;

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

    private int type = 6;

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
        LitemallQuestion info = QuestionService.findById(id);

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

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, id, type);
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

        executorService.submit(commentsCallableTsk);
        executorService.submit(collectListCallableTsk);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("collectCount", collectService.countCollectMe(id, this.type));
            data.put("share", SystemConfig.isAutoCreateShareImage());
            data.put("publishUser", userVo);
            data.put("comment", commentsCallableTsk.get());
            data.put("collect", collectListCallableTsk.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.ok(data);
    }

    /**
     * @return 鱼塘列表
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId) {
        //查询列表数据
        List<LitemallQuestion> QuestionList = QuestionService.queryQuestion(0, 5);
        List<Map<String, Object>> QuestionVoList = new ArrayList<>(QuestionList.size());
        for (LitemallQuestion litemallQuestion : QuestionList) {
            Map<String, Object> QuestionVo = new HashMap<>();
            QuestionVo.put("info", litemallQuestion);
            if (userId != null) {
                QuestionVo.put("userHasCollect", collectService.count(userId, litemallQuestion.getId(), this.type));
            }
            QuestionVo.put("collectCount", collectService.countCollectMe(litemallQuestion.getId(), this.type));

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser user = userService.findDetailById(litemallQuestion.getUserId());
            userVo.put("user", user);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollectMe(user.getId(), 10));
            QuestionVo.put("publishUser", userVo);

            QuestionVoList.add(QuestionVo);
        }
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", QuestionVoList);
        return ResponseUtil.ok(entity);
    }

    @GetMapping("listall")
    public Object listall(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallQuestion> QuestionList = QuestionService.queryQuestion(page, limit);

        List<Map<String, Object>> QuestionVoList = new ArrayList<>(QuestionList.size());
        for (LitemallQuestion Question : QuestionList) {
            Map<String, Object> QuestionVo = new HashMap<>();
            QuestionVo.put("info", Question);
            if (userId != null) {
                QuestionVo.put("userHasCollect", collectService.count(userId, Question.getId(), this.type));
            }
            QuestionVo.put("collectCount", collectService.countCollectMe(Question.getId(), this.type));

            Map<String, Object> userVo = new HashMap<>();
            LitemallUser user = userService.findDetailById(Question.getUserId());
            userVo.put("user", user);
            if (userId != null) {
                userVo.put("userHasCollect", collectService.count(userId, user.getId(), 10));
            }
            userVo.put("collectCount", collectService.countCollectMe(user.getId(), 10));
            QuestionVo.put("publishUser", userVo);

            QuestionVoList.add(QuestionVo);
        }
        return ResponseUtil.okList(QuestionVoList, QuestionList);
    }

    @GetMapping("listByUser")
    public Object listByUser(@NotNull Integer userId, @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallQuestion> questions = QuestionService.queryQuestionByUser(userId, page, limit);
        List<Map<String, Object>> questionVoList = new ArrayList<>(questions.size());
        for (LitemallQuestion litemallQuestion : questions) {
            Map<String, Object> questionVo = new HashMap<>();
            questionVo.put("info", litemallQuestion);
            questionVoList.add(questionVo);
        }
        return ResponseUtil.okList(questionVoList, questions);
    }

    /**
     * @return 在售的鱼塘总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = QuestionService.queryOnActive();
        return ResponseUtil.ok(goodsCount);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallQuestion litemallQuestion) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (litemallQuestion.getId() == null || litemallQuestion.getId().equals(0)) {
            litemallQuestion.setId(null);
            litemallQuestion.setUserId(userId);
            QuestionService.add(litemallQuestion);
        } else {
            litemallQuestion.setUserId(userId);
            if (QuestionService.updateById(litemallQuestion) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(litemallQuestion.getId());
    }

    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallQuestion litemallQuestion) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = litemallQuestion.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        QuestionService.deleteById(id);
        return ResponseUtil.ok();
    }

    @PostMapping("refresh")
    public Object refresh(@LoginUser Integer userId, @RequestBody LitemallQuestion question) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Integer id = question.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            if (QuestionService.refreshUpdateTime(question) == 0) {
                return ResponseUtil.updatedDataFailed();
            } else {
                return ResponseUtil.ok();
            }
        }
    }
}