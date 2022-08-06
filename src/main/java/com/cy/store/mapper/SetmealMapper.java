package com.cy.store.mapper;


import com.cy.store.entity.QueryPageBean;
import com.cy.store.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealMapper {
    void addSetmeal(Setmeal setmeal);

    void addSetmealCheckGroup(@Param("checkgroupId") Integer checkgroupId, @Param("setmealId") Integer setmealId);

    List<Setmeal> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);
}
