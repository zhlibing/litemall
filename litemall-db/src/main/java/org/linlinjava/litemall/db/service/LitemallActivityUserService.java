package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallActivityUserMapper;
import org.linlinjava.litemall.db.domain.LitemallActivityUser;
import org.linlinjava.litemall.db.domain.LitemallActivityUserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallActivityUserService {
    @Resource
    private LitemallActivityUserMapper ActivityUserMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallActivityUser> queryActivityUser(int offset, int limit) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ActivityUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallActivityUser> queryActivityUser(int activityId, int offset, int limit) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andDeletedEqualTo(false).andActivityIdEqualTo(activityId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ActivityUserMapper.selectByExampleSelective(example);
    }

    public List<LitemallActivityUser> queryUserActivity(int userId, int offset, int limit) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ActivityUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallActivityUser findById(Integer id) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return ActivityUserMapper.selectOneByExample(example);
    }

    /**
     * @param activityId
     * @return
     */
    public LitemallActivityUser findByIdVO(Integer userId, Integer activityId) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andUserIdEqualTo(userId).andActivityIdEqualTo(activityId).andDeletedEqualTo(false);
        return ActivityUserMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andDeletedEqualTo(false);
        return (int) ActivityUserMapper.countByExample(example);
    }

    public int updateById(LitemallActivityUser ActivityUser) {
        ActivityUser.setUpdateTime(LocalDateTime.now());
        return ActivityUserMapper.updateByPrimaryKeySelective(ActivityUser);
    }

    public void deleteById(Integer id) {
        ActivityUserMapper.deleteByPrimaryKey(id);
    }

    public void quiteById(Integer userId, Integer id) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andActivityIdEqualTo(id).andUserIdEqualTo(userId);
        ActivityUserMapper.deleteByExample(example);
    }

    public void add(LitemallActivityUser ActivityUser) {
        ActivityUser.setAddTime(LocalDateTime.now());
        ActivityUser.setUpdateTime(LocalDateTime.now());
        ActivityUserMapper.insertSelective(ActivityUser);
    }

    /**
     * @return
     */
    public int count(Integer userId, Integer activityId) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId).andActivityIdEqualTo(activityId);
        return (int) ActivityUserMapper.countByExample(example);
    }

    public List<LitemallActivityUser> queryByIds(Integer[] ids) {
        LitemallActivityUserExample example = new LitemallActivityUserExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return ActivityUserMapper.selectByExampleSelective(example);
    }
}
