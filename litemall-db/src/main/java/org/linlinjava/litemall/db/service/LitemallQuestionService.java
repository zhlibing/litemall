package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallQuestionMapper;
import org.linlinjava.litemall.db.domain.LitemallCircle;
import org.linlinjava.litemall.db.domain.LitemallCircleExample;
import org.linlinjava.litemall.db.domain.LitemallQuestion;
import org.linlinjava.litemall.db.domain.LitemallQuestion.Column;
import org.linlinjava.litemall.db.domain.LitemallQuestionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallQuestionService {
    Column[] columns = new Column[]{Column.id, Column.userId, Column.title, Column.description, Column.picUrls, Column.status, Column.reword, Column.type};
    @Resource
    private LitemallQuestionMapper questionMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallQuestion> queryQuestion(int offset, int limit) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(offset, limit);
        return questionMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallQuestion> queryQuestionByUser(Integer id, int offset, int limit) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(id);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return questionMapper.selectByExampleSelective(example, columns);
    }

    /**
     * @param id
     * @return
     */
    public LitemallQuestion findById(Integer id) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return questionMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallQuestion findByIdVO(Integer id) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return questionMapper.selectOneByExampleSelective(example, columns);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andDeletedEqualTo(false);
        return (int) questionMapper.countByExample(example);
    }

    public int updateById(LitemallQuestion Question) {
        Question.setUpdateTime(LocalDateTime.now());
        return questionMapper.updateByPrimaryKeySelective(Question);
    }

    public int refreshUpdateTime(LitemallQuestion question) {
        question.setUpdateTime(LocalDateTime.now());
        LitemallQuestionExample litemallQuestionExample = new LitemallQuestionExample();
        litemallQuestionExample.or().andIdEqualTo(question.getId());
        return questionMapper.updateByExampleSelective(question, litemallQuestionExample);
    }

    public void deleteById(Integer id) {
        questionMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallQuestion Question) {
        Question.setAddTime(LocalDateTime.now());
        Question.setUpdateTime(LocalDateTime.now());
        questionMapper.insertSelective(Question);
    }

    /**
     * @return
     */
    public int count() {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andDeletedEqualTo(false);
        return (int) questionMapper.countByExample(example);
    }

    public int countByUser(Integer userId) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) questionMapper.countByExample(example);
    }

    public boolean checkExistByName(String name) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andTitleEqualTo(name).andDeletedEqualTo(false);
        return questionMapper.countByExample(example) != 0;
    }

    public List<LitemallQuestion> queryByIds(Integer[] ids) {
        LitemallQuestionExample example = new LitemallQuestionExample();
        example.or().andIdIn(Arrays.asList(ids)).andDeletedEqualTo(false);
        return questionMapper.selectByExampleSelective(example, columns);
    }
}
