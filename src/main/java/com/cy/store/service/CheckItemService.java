package com.cy.store.service;


import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    List<CheckItem> findAll();
}
