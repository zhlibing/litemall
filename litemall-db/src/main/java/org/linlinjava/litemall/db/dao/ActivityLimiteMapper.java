package org.linlinjava.litemall.db.dao;

import org.apache.ibatis.annotations.Param;

public interface ActivityLimiteMapper {
    int addStock(@Param("id") Integer id, @Param("num") Short num);
    int reduceStock(@Param("id") Integer id, @Param("num") Short num);
}