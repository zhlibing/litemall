package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallTypeObj;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 鱼塘服务
 */
@RestController
@RequestMapping("/wx/typeobj")
@Validated
public class WxTypeObjController {
    private final Log logger = LogFactory.getLog(WxTypeObjController.class);

    @Autowired
    private LitemallTypeObjService TypeObjService;

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
        LitemallTypeObj info = TypeObjService.findById(id);
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("info", info);
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
        List<LitemallTypeObj> TypeObj = TypeObjService.queryTypeObj(0, 25);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", TypeObj);
        return ResponseUtil.ok(entity);
    }

    /**
     * @return 在售的鱼塘总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = TypeObjService.queryOnActive();
        return ResponseUtil.ok(goodsCount);
    }
}