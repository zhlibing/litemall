package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallFishPondsUserMapper;
import org.linlinjava.litemall.db.domain.LitemallFishPondsUser;
import org.linlinjava.litemall.db.domain.LitemallFishPondsUserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallFishPondsUserService {
    @Resource
    private LitemallFishPondsUserMapper FishPondsUserMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallFishPondsUser> queryFishPondsUser(int offset, int limit) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return FishPondsUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallFishPondsUser> queryFishPondsUser(int FishPondsId, int offset, int limit) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andDeletedEqualTo(false).andFishPondsIdEqualTo(FishPondsId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return FishPondsUserMapper.selectByExampleSelective(example);
    }

    public List<LitemallFishPondsUser> queryUserFishponds(int userId, int offset, int limit) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return FishPondsUserMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallFishPondsUser findById(Integer id) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return FishPondsUserMapper.selectOneByExample(example);
    }

    /**
     * @param FishPondsId
     * @return
     */
    public LitemallFishPondsUser findByIdVO(Integer userId, Integer FishPondsId) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andUserIdEqualTo(userId).andFishPondsIdEqualTo(FishPondsId).andDeletedEqualTo(false);
        return FishPondsUserMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andDeletedEqualTo(false);
        return (int) FishPondsUserMapper.countByExample(example);
    }

    public int updateById(LitemallFishPondsUser FishPondsUser) {
        FishPondsUser.setUpdateTime(LocalDateTime.now());
        return FishPondsUserMapper.updateByPrimaryKeySelective(FishPondsUser);
    }

    public void deleteById(Integer id) {
        FishPondsUserMapper.deleteByPrimaryKey(id);
    }

    public void deleteByValueId(Integer id) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andFishPondsIdEqualTo(id);
        FishPondsUserMapper.logicalDeleteByExample(example);
    }

    public void quiteById(Integer userId, Integer id) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andFishPondsIdEqualTo(id).andUserIdEqualTo(userId);
        FishPondsUserMapper.deleteByExample(example);
    }

    public void add(LitemallFishPondsUser FishPondsUser) {
        FishPondsUser.setAddTime(LocalDateTime.now());
        FishPondsUser.setUpdateTime(LocalDateTime.now());
        FishPondsUserMapper.insertSelective(FishPondsUser);
    }

    /**
     * @return
     */
    public int count(Integer userId, Integer FishPondsId) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId).andFishPondsIdEqualTo(FishPondsId);
        return (int) FishPondsUserMapper.countByExample(example);
    }

    public List<LitemallFishPondsUser> queryByIds(Integer[] ids) {
        LitemallFishPondsUserExample example = new LitemallFishPondsUserExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return FishPondsUserMapper.selectByExampleSelective(example);
    }
}
