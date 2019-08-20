package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallGroupMapper;
import org.linlinjava.litemall.db.domain.LitemallGroup;
import org.linlinjava.litemall.db.domain.LitemallGroup.Column;
import org.linlinjava.litemall.db.domain.LitemallGroupExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallGroupService {
    Column[] columns = new Column[]{Column.id, Column.age, Column.description
            , Column.picUrls, Column.status, Column.currentPeople, Column.type, Column.userId, Column.latitude, Column.longitude};
    @Resource
    private LitemallGroupMapper groupMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGroup> queryGroup(int offset, int limit) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return groupMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallGroup> queryGroupByUser(Integer id, int offset, int limit) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(id);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return groupMapper.selectByExampleSelective(example, columns);
    }

    /**
     * @param id
     * @return
     */
    public LitemallGroup findById(Integer id) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return groupMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallGroup findByIdVO(Integer id) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return groupMapper.selectOneByExampleSelective(example, columns);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andDeletedEqualTo(false);
        return (int) groupMapper.countByExample(example);
    }

    public int updateById(LitemallGroup Group) {
        Group.setUpdateTime(LocalDateTime.now());
        return groupMapper.updateByPrimaryKeySelective(Group);
    }

    public void deleteById(Integer id) {
        groupMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallGroup Group) {
        Group.setAddTime(LocalDateTime.now());
        Group.setUpdateTime(LocalDateTime.now());
        groupMapper.insertSelective(Group);
    }

    /**
     * @return
     */
    public int count() {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andDeletedEqualTo(false);
        return (int) groupMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) groupMapper.countByExample(example);
    }

    public List<LitemallGroup> queryByIds(Integer[] ids) {
        LitemallGroupExample example = new LitemallGroupExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return groupMapper.selectByExampleSelective(example, columns);
    }
}
