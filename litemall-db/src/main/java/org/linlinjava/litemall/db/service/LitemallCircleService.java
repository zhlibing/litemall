package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCircleMapper;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.linlinjava.litemall.db.domain.LitemallCircle;
import org.linlinjava.litemall.db.domain.LitemallCircle.Column;
import org.linlinjava.litemall.db.domain.LitemallCircleExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallCircleService {
    Column[] columns = new Column[]{Column.id, Column.content, Column.picUrls, Column.status, Column.addTime, Column.type};
    @Resource
    private LitemallCircleMapper circleMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallCircle> queryCircle(int offset, int limit) {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(offset, limit);
        return circleMapper.selectByExampleSelective(example, columns);
    }
    public List<LitemallCircle> queryCircleByUser(Integer id, int offset, int limit) {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(id);
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(offset, limit);
        return circleMapper.selectByExampleSelective(example, columns);
    }


    /**
     * @param id
     * @return
     */
    public LitemallCircle findById(Integer id) {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return circleMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallCircle findByIdVO(Integer id) {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return circleMapper.selectOneByExampleSelective(example, columns);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andDeletedEqualTo(false);
        return (int) circleMapper.countByExample(example);
    }

    public int updateById(LitemallCircle Circle) {
        Circle.setUpdateTime(LocalDateTime.now());
        return circleMapper.updateByPrimaryKeySelective(Circle);
    }

    public int refreshUpdateTime(LitemallCircle circle) {
        circle.setUpdateTime(LocalDateTime.now());
        LitemallCircleExample litemallCircleExample = new LitemallCircleExample();
        litemallCircleExample.or().andIdEqualTo(circle.getId());
        return circleMapper.updateByExampleSelective(circle, litemallCircleExample);
    }

    public void deleteById(Integer id) {
        circleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallCircle Circle) {
        Circle.setAddTime(LocalDateTime.now());
        Circle.setUpdateTime(LocalDateTime.now());
        circleMapper.insertSelective(Circle);
    }

    /**
     * @return
     */
    public int count() {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andDeletedEqualTo(false);
        return (int) circleMapper.countByExample(example);
    }

    public List<LitemallCircle> queryByIds(Integer[] ids) {
        LitemallCircleExample example = new LitemallCircleExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return circleMapper.selectByExampleSelective(example, columns);
    }
}
