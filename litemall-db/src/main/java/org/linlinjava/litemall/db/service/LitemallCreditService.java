package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCreditMapper;
import org.linlinjava.litemall.db.domain.LitemallCredit;
import org.linlinjava.litemall.db.domain.LitemallCreditExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallCreditService {
    @Resource
    private LitemallCreditMapper CreditMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallCredit> queryCredit(int offset, int limit) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return CreditMapper.selectByExampleSelective(example);
    }

    public List<LitemallCredit> queryCreditByUser(Integer id, int offset, int limit) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andDeletedEqualTo(false).andValueIdEqualTo(id).andTypeEqualTo((byte) 10);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return CreditMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallCredit findById(Integer id) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return CreditMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallCredit findByIdVO(Integer id) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return CreditMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andDeletedEqualTo(false);
        return (int) CreditMapper.countByExample(example);
    }

    public int updateById(LitemallCredit Credit) {
        Credit.setUpdateTime(LocalDateTime.now());
        return CreditMapper.updateByPrimaryKeySelective(Credit);
    }

    public void deleteById(Integer id) {
        CreditMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallCredit Credit) {
        Credit.setAddTime(LocalDateTime.now());
        Credit.setUpdateTime(LocalDateTime.now());
        CreditMapper.insertSelective(Credit);
    }

    /**
     * @return
     */
    public int count() {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andDeletedEqualTo(false);
        return (int) CreditMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andValueIdEqualTo(userId).andTypeEqualTo((byte) 10).andDeletedEqualTo(false);
        return (int) CreditMapper.countByExample(example);
    }

    public List<LitemallCredit> queryByIds(Integer[] ids) {
        LitemallCreditExample example = new LitemallCreditExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return CreditMapper.selectByExampleSelective(example);
    }
}
