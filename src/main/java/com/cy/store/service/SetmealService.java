package com.cy.store.service;


import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.pojo.Setmeal;

public interface SetmealService {
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);
}
