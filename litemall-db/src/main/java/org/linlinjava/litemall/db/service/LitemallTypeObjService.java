package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallTypeObjMapper;
import org.linlinjava.litemall.db.domain.LitemallTypeObj;
import org.linlinjava.litemall.db.domain.LitemallTypeObjExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallTypeObjService {
    @Resource
    private LitemallTypeObjMapper typeObjMapper;

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallTypeObj> queryTypeObj(int offset, int limit) {
        LitemallTypeObjExample example = new LitemallTypeObjExample();
        example.or().andIsShowClientEqualTo(true);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return typeObjMapper.selectByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallTypeObj findById(Integer id) {
        LitemallTypeObjExample example = new LitemallTypeObjExample();
        example.or().andIdEqualTo(id);
        return typeObjMapper.selectOneByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public LitemallTypeObj findByIdVO(Integer id) {
        LitemallTypeObjExample example = new LitemallTypeObjExample();
        example.or().andIdEqualTo(id);
        return typeObjMapper.selectOneByExampleSelective(example);
    }

    /**
     * @return
     */
    public Integer queryOnActive() {
        LitemallTypeObjExample example = new LitemallTypeObjExample();
        example.or().andIsShowClientEqualTo(true);
        return (int) typeObjMapper.countByExample(example);
    }

    public int updateById(LitemallTypeObj TypeObj) {
        TypeObj.setUpdateTime(LocalDateTime.now());
        return typeObjMapper.updateByPrimaryKeySelective(TypeObj);
    }

    public void deleteById(Integer id) {
        typeObjMapper.deleteByPrimaryKey(id);
    }

    public void add(LitemallTypeObj TypeObj) {
        TypeObj.setAddTime(LocalDateTime.now());
        TypeObj.setUpdateTime(LocalDateTime.now());
        typeObjMapper.insertSelective(TypeObj);
    }
}
