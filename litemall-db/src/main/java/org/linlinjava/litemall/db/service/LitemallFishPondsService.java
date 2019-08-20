package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallFishPondsMapper;
import org.linlinjava.litemall.db.domain.LitemallFishPonds;
import org.linlinjava.litemall.db.domain.LitemallFishPondsExample;
import org.linlinjava.litemall.db.domain.LitemallFishPonds.Column;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallFishPondsService {
    Column[] columns = new Column[]{Column.id, Column.title, Column.description
            , Column.picUrls, Column.level, Column.currentPeople, Column.fee, Column.reword, Column.type, Column.userId, Column.latitude, Column.longitude};
    @Resource
    private LitemallFishPondsMapper fishPondsMapper;

    /**
     * 获取5个鱼塘
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallFishPonds> queryFishPonds(int offset, int limit) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return fishPondsMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallFishPonds> queryFishpondsByUser(Integer id, int offset, int limit) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(id);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return fishPondsMapper.selectByExampleSelective(example, columns);
    }

    /**
     * 获取某个鱼塘信息,包含完整信息
     *
     * @param id
     * @return
     */
    public LitemallFishPonds findById(Integer id) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return fishPondsMapper.selectOneByExample(example);
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public LitemallFishPonds findByIdVO(Integer id) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return fishPondsMapper.selectOneByExampleSelective(example, columns);
    }

    /**
     * 获取所有在售物品总数
     *
     * @return
     */
    public Integer queryOnActive() {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andDeletedEqualTo(false);
        return (int) fishPondsMapper.countByExample(example);
    }

    public int updateById(LitemallFishPonds fishPonds) {
        fishPonds.setUpdateTime(LocalDateTime.now());
        return fishPondsMapper.updateByPrimaryKeySelective(fishPonds);
    }

    public void deleteById(Integer id) {
        fishPondsMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallFishPonds fishPonds) {
        fishPonds.setAddTime(LocalDateTime.now());
        fishPonds.setUpdateTime(LocalDateTime.now());
        fishPondsMapper.insertSelective(fishPonds);
    }

    /**
     * 获取所有物品总数，包括在售的和下架的，但是不包括已删除的商品
     *
     * @return
     */
    public int count() {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andDeletedEqualTo(false);
        return (int) fishPondsMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) fishPondsMapper.countByExample(example);
    }

    public boolean checkExistByName(String name) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andTitleEqualTo(name).andDeletedEqualTo(false);
        return fishPondsMapper.countByExample(example) != 0;
    }

    public List<LitemallFishPonds> queryByIds(Integer[] ids) {
        LitemallFishPondsExample example = new LitemallFishPondsExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return fishPondsMapper.selectByExampleSelective(example, columns);
    }
}
