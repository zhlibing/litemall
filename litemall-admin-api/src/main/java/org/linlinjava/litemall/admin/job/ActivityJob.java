package org.linlinjava.litemall.admin.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.service.LitemallActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检测比赛活动过期情况
 */
@Component
public class ActivityJob {
    private final Log logger = LogFactory.getLog(ActivityJob.class);

    @Autowired
    private LitemallActivityService litemallActivityService;

    /**
     * 每隔一个小时检查
     * TODO
     * 注意，因为是相隔一个小时检查，因此导致优惠券真正超时时间可能比设定时间延迟1个小时
     */
    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void checkActivityNoStart() {
        logger.info("系统开启任务比赛活动是否未开始");

        List<LitemallActivity> litemallActivities = litemallActivityService.queryNoStart();
        for (LitemallActivity litemallActivity : litemallActivities) {
            litemallActivity.setStatus(0);
            litemallActivityService.updateById(litemallActivity);
        }
    }

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void checkActivityOngoing() {
        logger.info("系统开启任务比赛活动是否正在进行");

        List<LitemallActivity> litemallActivities = litemallActivityService.queryOngoing();
        for (LitemallActivity litemallActivity : litemallActivities) {
            litemallActivity.setStatus(1);
            litemallActivityService.updateById(litemallActivity);
        }
    }

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void checkActivityJustice() {
        logger.info("系统开启任务比赛活动是否正在公正");

        List<LitemallActivity> litemallActivities = litemallActivityService.queryJustice();
        for (LitemallActivity litemallActivity : litemallActivities) {
            litemallActivity.setStatus(2);
            litemallActivityService.updateById(litemallActivity);
        }
    }

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void checkActivityExpired() {
        logger.info("系统开启任务比赛活动是否已经过期");

        List<LitemallActivity> litemallActivities = litemallActivityService.queryExpired();
        for (LitemallActivity litemallActivity : litemallActivities) {
            litemallActivity.setStatus(3);
            litemallActivityService.updateById(litemallActivity);
        }
    }
}
