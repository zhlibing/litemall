package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallActivityMapper;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivity.Column;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallActivityService {
    Column[] columns = new Column[]{
            Column.id, Column.title, Column.description, Column.picUrls, Column.status,
            Column.reword, Column.type, Column.userId, Column.addTime, Column.activityTime, Column.reword, Column.currentPeople,
            Column.collectioncount, Column.fee};
    @Resource
    private LitemallActivityMapper activityMapper;

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

    /**
     * @param id
     * @return
     */
    public LitemallActivity findById(Integer id) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
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
}
