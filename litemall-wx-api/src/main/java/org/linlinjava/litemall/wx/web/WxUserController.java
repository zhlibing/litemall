package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallFootprint;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/wx/user")
@Validated
public class WxUserController {
    private final Log logger = LogFactory.getLog(WxUserController.class);

    @Autowired
    private LitemallOrderService orderService;

    @Autowired
    private LitemallUserService litemallUserService;

    @Autowired
    private LitemallActivityService litemallActivityService;

    @Autowired
    private LitemallFishPondsService litemallFishPondsService;

    @Autowired
    private LitemallQuestionService litemallQuestionService;

    @Autowired
    private LitemallCircleService litemallCircleService;

    @Autowired
    private LitemallGroupService litemallGroupService;

    @Autowired
    private LitemallCollectService litemallCollectService;

    @Autowired
    private LitemallFootprintService footprintService;

    @Autowired
    private LitemallCommentService litemallCommentService;

    @Autowired
    private LitemallActivityUserService litemallActivityUserService;

    @Autowired
    private LitemallFishPondsUserService litemallFishPondsUserService;

    @Autowired
    private LitemallGroupUserService litemallGroupUserService;

    @Autowired
    private LitemallActivityFishpondsService litemallActivityFishpondsService;

    private int type = 10;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("order", orderService.orderInfo(userId));
        return ResponseUtil.ok(data);
    }

    @GetMapping("details")
    public Object details(@LoginUser Integer userId, @NotNull Integer id) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("user", litemallUserService.findDetailById(id));
        data.put("activitys", litemallActivityService.queryActivityByUser(id, 0, 10));
        data.put("circles", litemallCircleService.queryCircleByUser(id, 0, 10));
        data.put("collectCount", litemallCollectService.countCollectMe(id, 10));
        if (userId != null) {
            data.put("userHasCollect", litemallCollectService.count(userId, id, 10));
        }
        if (userId != null) {
            LitemallFootprint footprint = new LitemallFootprint();
            footprint.setUserId(userId);
            footprint.setGoodsId(id);
            footprint.setType((byte) type);
            footprintService.add(footprint);
        }
        return ResponseUtil.ok(data);
    }

    @GetMapping("refresh")
    public Object refresh(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("collectMeCount", litemallCollectService.countCollectMe(userId, 10));
        data.put("meCollectCount", litemallCollectService.countMeCollect(userId, 10));
        data.put("viewMeCount", footprintService.countViewMe(userId, 10));
        data.put("meViewCount", footprintService.countMeView(userId, 10));
        data.put("publishActivityCount", litemallActivityService.countByUser(userId));
        data.put("publishQuestionCount", litemallQuestionService.countByUser(userId));
        data.put("publishFishpondsCount", litemallFishPondsService.countByUser(userId));
        data.put("publishCircleCount", litemallCircleService.countByUser(userId));
        data.put("publishGroupCount", litemallGroupService.countByUser(userId));
        return ResponseUtil.ok(data);
    }

    @GetMapping("deleteItem")
    public Object deleteItem(@LoginUser Integer userId, @NotNull Byte type,
                             @NotNull Integer valueId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (type == 4) {
            litemallCircleService.deleteById(valueId);
            litemallCommentService.deleteByTypeAndValueId(type, valueId);
            litemallCollectService.deleteByTypeAndValueId(type, valueId);
        }
        if (type == 5) {
            litemallFishPondsService.deleteById(valueId);
            litemallCommentService.deleteByTypeAndValueId(type, valueId);
            litemallActivityFishpondsService.deleteByFishpondsId(valueId);
            litemallFishPondsUserService.deleteByValueId(valueId);
            litemallActivityService.deleteByFishpondsId(valueId);
            litemallCollectService.deleteByTypeAndValueId(type, valueId);
        }
        if (type == 6) {
            litemallQuestionService.deleteById(valueId);
            litemallCommentService.deleteByTypeAndValueId(type, valueId);
            litemallCollectService.deleteByTypeAndValueId(type, valueId);
        }
        if (type == 7) {
            litemallGroupService.deleteById(valueId);
            litemallCommentService.deleteByTypeAndValueId(type, valueId);
            litemallGroupUserService.deleteByValueId(valueId);
            litemallCollectService.deleteByTypeAndValueId(type, valueId);
        }
        if (type == 8) {
            litemallActivityService.deleteById(valueId);
            litemallCommentService.deleteByTypeAndValueId(type, valueId);
            litemallActivityFishpondsService.deleteByActivityId(valueId);
            litemallActivityUserService.deleteByActivityId(valueId);
            litemallCollectService.deleteByTypeAndValueId(type, valueId);
        }
        return ResponseUtil.ok();
    }
}