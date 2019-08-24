package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallScore;
import org.linlinjava.litemall.db.service.LitemallScoreService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鱼塘服务
 */
@RestController
@RequestMapping("/wx/score")
@Validated
public class WxScoreController {
    private final Log logger = LogFactory.getLog(WxScoreController.class);

    @Autowired
    private LitemallScoreService ScoreService;

    @GetMapping("listByUser")
    public Object listByUser(@LoginUser Integer userId, @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallScore> Scores = ScoreService.queryScoreByUser(userId, page, limit);
        List<Map<String, Object>> ScoreVoList = new ArrayList<>(Scores.size());
        for (LitemallScore litemallScore : Scores) {
            Map<String, Object> ScoreVo = new HashMap<>();
            ScoreVo.put("info", litemallScore);
            ScoreVoList.add(ScoreVo);
        }
        return ResponseUtil.okList(ScoreVoList, Scores);
    }

    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallScore Score) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (Score.getId() == null || Score.getId().equals(0)) {
            Score.setId(null);
            Score.setValueId(userId);
            Score.setType(Score.getType());
            ScoreService.add(Score);
        } else {
            Score.setValueId(userId);
            if (ScoreService.updateById(Score) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.ok(Score.getId());
    }
}