package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallActivityFishpondsMapper;
import org.linlinjava.litemall.db.domain.LitemallActivityFishponds;
import org.linlinjava.litemall.db.domain.LitemallActivityFishpondsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallActivityFishpondsService {
    @Resource
    private LitemallActivityFishpondsMapper ActivityFishpondsMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallActivityFishponds> queryActivityFishponds(int offset, int limit) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ActivityFishpondsMapper.selectByExampleSelective(example);
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallActivityFishponds> queryActivityFishponds(int activityId, int offset, int limit) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andDeletedEqualTo(false).andActivityIdEqualTo(activityId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ActivityFishpondsMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallActivityFishponds findById(Integer id) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return ActivityFishpondsMapper.selectOneByExample(example);
    }

    /**
     * @param activityId
     * @return
     */
    public LitemallActivityFishponds findByIdVO(Integer fishpondsId, Integer activityId) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andFishpondsIdEqualTo(fishpondsId).andActivityIdEqualTo(activityId).andDeletedEqualTo(false);
        return ActivityFishpondsMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andDeletedEqualTo(false);
        return (int) ActivityFishpondsMapper.countByExample(example);
    }

    public int updateById(LitemallActivityFishponds ActivityFishponds) {
        ActivityFishponds.setUpdateTime(LocalDateTime.now());
        return ActivityFishpondsMapper.updateByPrimaryKeySelective(ActivityFishponds);
    }

    public void deleteById(Integer id) {
        ActivityFishpondsMapper.deleteByPrimaryKey(id);
    }

    public void deleteByFishpondsId(Integer fishpondsId) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andFishpondsIdEqualTo(fishpondsId);
        ActivityFishpondsMapper.logicalDeleteByExample(example);
    }

    public void deleteByActivityId(Integer activityId) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andActivityIdEqualTo(activityId);
        ActivityFishpondsMapper.logicalDeleteByExample(example);
    }

    public void quiteById(Integer fishpondsId, Integer id) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andActivityIdEqualTo(id).andFishpondsIdEqualTo(fishpondsId);
        ActivityFishpondsMapper.deleteByExample(example);
    }

    public void add(LitemallActivityFishponds ActivityFishponds) {
        ActivityFishponds.setAddTime(LocalDateTime.now());
        ActivityFishponds.setUpdateTime(LocalDateTime.now());
        ActivityFishpondsMapper.insertSelective(ActivityFishponds);
    }

    /**
     * @return
     */
    public int count(Integer fishpondsId, Integer activityId) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andDeletedEqualTo(false).andFishpondsIdEqualTo(fishpondsId).andActivityIdEqualTo(activityId);
        return (int) ActivityFishpondsMapper.countByExample(example);
    }

    public List<LitemallActivityFishponds> queryByIds(Integer[] ids) {
        LitemallActivityFishpondsExample example = new LitemallActivityFishpondsExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return ActivityFishpondsMapper.selectByExampleSelective(example);
    }
}
