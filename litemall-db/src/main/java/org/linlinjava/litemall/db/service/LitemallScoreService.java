package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallScoreMapper;
import org.linlinjava.litemall.db.domain.LitemallScore;
import org.linlinjava.litemall.db.domain.LitemallScoreExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallScoreService {
    @Resource
    private LitemallScoreMapper ScoreMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallScore> queryScore(int offset, int limit) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ScoreMapper.selectByExampleSelective(example);
    }

    public List<LitemallScore> queryScoreByUser(Integer id, int offset, int limit) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andDeletedEqualTo(false).andValueIdEqualTo(id).andTypeEqualTo((byte) 10);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return ScoreMapper.selectByExampleSelective(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallScore findById(Integer id) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return ScoreMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallScore findByIdVO(Integer id) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return ScoreMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andDeletedEqualTo(false);
        return (int) ScoreMapper.countByExample(example);
    }

    public int updateById(LitemallScore Score) {
        Score.setUpdateTime(LocalDateTime.now());
        return ScoreMapper.updateByPrimaryKeySelective(Score);
    }

    public void deleteById(Integer id) {
        ScoreMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallScore Score) {
        Score.setAddTime(LocalDateTime.now());
        Score.setUpdateTime(LocalDateTime.now());
        ScoreMapper.insertSelective(Score);
    }

    /**
     * @return
     */
    public int count() {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andDeletedEqualTo(false);
        return (int) ScoreMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andValueIdEqualTo(userId).andTypeEqualTo((byte) 10).andDeletedEqualTo(false);
        return (int) ScoreMapper.countByExample(example);
    }

    public List<LitemallScore> queryByIds(Integer[] ids) {
        LitemallScoreExample example = new LitemallScoreExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return ScoreMapper.selectByExampleSelective(example);
    }
}
