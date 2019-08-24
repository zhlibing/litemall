package org.linlinjava.litemall.wx.web;

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
 * 鱼塘服务
 */
@RestController
@RequestMapping("/wx/credit")
@Validated
public class WxCreditController {
    private final Log logger = LogFactory.getLog(WxCreditController.class);

    @Autowired
    private LitemallCreditService CreditService;

    @GetMapping("listByUser")
    public Object listByUser(@NotNull Integer userId, @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallCredit> Credits = CreditService.queryCreditByUser(userId, page, limit);
        List<Map<String, Object>> CreditVoList = new ArrayList<>(Credits.size());
        for (LitemallCredit litemallCredit : Credits) {
            Map<String, Object> CreditVo = new HashMap<>();
            CreditVo.put("info", litemallCredit);
            CreditVoList.add(CreditVo);
        }
        return ResponseUtil.okList(CreditVoList, Credits);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallCredit Credit) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (Credit.getId() == null || Credit.getId().equals(0)) {
            Credit.setId(null);
            Credit.setValueId(userId);
            Credit.setType(Credit.getType());
            CreditService.add(Credit);
        } else {
            Credit.setValueId(userId);
            if (CreditService.updateById(Credit) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(Credit.getId());
    }
}