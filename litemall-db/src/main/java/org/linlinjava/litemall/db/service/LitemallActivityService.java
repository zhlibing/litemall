package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.ActivityLimiteMapper;
import org.linlinjava.litemall.db.dao.LitemallActivityMapper;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivity.Column;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallActivityService {
    Column[] columns = new Column[]{
            Column.id, Column.title, Column.description, Column.picUrls, Column.status,
            Column.reword, Column.type, Column.userId, Column.addTime, Column.startTime, Column.endTime, Column.reword, Column.currentPeople,
            Column.collectioncount, Column.fee, Column.rule, Column.latitude, Column.longitude};
    @Resource
    private LitemallActivityMapper activityMapper;
    @Resource
    private ActivityLimiteMapper activityLimiteMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallActivity> queryActivity(int offset, int limit) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return activityMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallActivity> queryActivityByUser(Integer id, int offset, int limit) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(id);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return activityMapper.selectByExampleSelective(example, columns);
    }

    /**
     * @param id
     * @return
     */
    public LitemallActivity findById(Integer id) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return activityMapper.selectOneByExample(example);
    }

    public LitemallActivity findByIdAndStatus(Integer id, Integer status) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andStatusEqualTo(status).andDeletedEqualTo(false);
        return activityMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallActivity findByIdVO(Integer id) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return activityMapper.selectOneByExampleSelective(example, columns);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false);
        return (int) activityMapper.countByExample(example);
    }

    public int updateById(LitemallActivity Activity) {
        Activity.setUpdateTime(LocalDateTime.now());
        return activityMapper.updateByPrimaryKeySelective(Activity);
    }

    public void deleteById(Integer id) {
        activityMapper.logicalDeleteByPrimaryKey(id);
    }

    public void deleteByFishpondsId(Integer fishpondsId) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andFishpondsIdEqualTo(fishpondsId);
        activityMapper.logicalDeleteByExample(example);
    }

    public void add(LitemallActivity Activity) {
        Activity.setAddTime(LocalDateTime.now());
        Activity.setUpdateTime(LocalDateTime.now());
        activityMapper.insertSelective(Activity);
    }

    /**
     * @return
     */
    public int count() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false);
        return (int) activityMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) activityMapper.countByExample(example);
    }

    public boolean checkExistByName(String name) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andTitleEqualTo(name).andDeletedEqualTo(false);
        return activityMapper.countByExample(example) != 0;
    }

    public List<LitemallActivity> queryByIds(Integer[] ids) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return activityMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallActivity> queryNoStart() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or()
                .andStartTimeGreaterThan(addTmp(LocalDateTime.now(), 2 * 60 * 60 * 1000))
                .andDeletedEqualTo(false);
        return activityMapper.selectByExample(example);
    }

    public List<LitemallActivity> queryPrepare() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or()
                .andStartTimeLessThan(addTmp(LocalDateTime.now(), 2 * 60 * 60 * 1000))
                .andStartTimeGreaterThan(LocalDateTime.now())
                .andDeletedEqualTo(false);
        return activityMapper.selectByExample(example);
    }

    public List<LitemallActivity> queryOngoing() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or()
                .andStartTimeLessThan(LocalDateTime.now())
                .andEndTimeGreaterThan(LocalDateTime.now())
                .andDeletedEqualTo(false);
        return activityMapper.selectByExample(example);
    }

    public List<LitemallActivity> queryJustice() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or()
                .andEndTimeGreaterThan(addTmp(LocalDateTime.now(), -2 * 60 * 60 * 1000))
                .andEndTimeLessThan(LocalDateTime.now())
                .andDeletedEqualTo(false);
        return activityMapper.selectByExample(example);
    }

    public List<LitemallActivity> queryExpired() {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or()
                .andEndTimeLessThan(addTmp(LocalDateTime.now(), -2 * 60 * 60 * 1000))
                .andDeletedEqualTo(false);
        return activityMapper.selectByExample(example);
    }

    public int reduceStock(Integer id, Short num){
        return activityLimiteMapper.reduceStock(id, num);
    }

    public int addStock(Integer id, Short num){
        return activityLimiteMapper.addStock(id, num);
    }

    public LocalDateTime timestamToDatetime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public long datatimeToTimestamp(LocalDateTime ldt) {
        long timestamp = ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }

    public LocalDateTime addTmp(LocalDateTime localDateTime, long tmp) {
        long start = datatimeToTimestamp(localDateTime);
        long end = start + tmp;
        return timestamToDatetime(end);
    }
}
