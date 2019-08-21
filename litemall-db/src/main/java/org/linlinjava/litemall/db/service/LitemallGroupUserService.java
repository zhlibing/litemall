package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallGroupUserMapper;
import org.linlinjava.litemall.db.domain.LitemallGroupUser;
import org.linlinjava.litemall.db.domain.LitemallGroupUserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallGroupUserService {
    @Resource
    private LitemallGroupUserMapper GroupUserMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGroupUser> queryGroupUser(int offset, int limit) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return GroupUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGroupUser> queryGroupUser(int GroupId, int offset, int limit) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andDeletedEqualTo(false).andGroupIdEqualTo(GroupId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return GroupUserMapper.selectByExampleSelective(example);
    }

    public List<LitemallGroupUser> queryUserGroup(int userId, int offset, int limit) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return GroupUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallGroupUser findById(Integer id) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return GroupUserMapper.selectOneByExample(example);
    }

    /**
     * @param GroupId
     * @return
     */
    public LitemallGroupUser findByIdVO(Integer userId, Integer GroupId) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andUserIdEqualTo(userId).andGroupIdEqualTo(GroupId).andDeletedEqualTo(false);
        return GroupUserMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andDeletedEqualTo(false);
        return (int) GroupUserMapper.countByExample(example);
    }

    public int updateById(LitemallGroupUser GroupUser) {
        GroupUser.setUpdateTime(LocalDateTime.now());
        return GroupUserMapper.updateByPrimaryKeySelective(GroupUser);
    }

    public void deleteById(Integer id) {
        GroupUserMapper.deleteByPrimaryKey(id);
    }

    public void deleteByValueId(Integer id) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andGroupIdEqualTo(id);
        GroupUserMapper.logicalDeleteByExample(example);
    }

    public void quiteById(Integer userId, Integer id) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andGroupIdEqualTo(id).andUserIdEqualTo(userId);
        GroupUserMapper.deleteByExample(example);
    }

    public void add(LitemallGroupUser GroupUser) {
        GroupUser.setAddTime(LocalDateTime.now());
        GroupUser.setUpdateTime(LocalDateTime.now());
        GroupUserMapper.insertSelective(GroupUser);
    }

    /**
     * @return
     */
    public int count(Integer userId, Integer GroupId) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId).andGroupIdEqualTo(GroupId);
        return (int) GroupUserMapper.countByExample(example);
    }

    public List<LitemallGroupUser> queryByIds(Integer[] ids) {
        LitemallGroupUserExample example = new LitemallGroupUserExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return GroupUserMapper.selectByExampleSelective(example);
    }
}
