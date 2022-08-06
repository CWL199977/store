package com.cy.store.mapper;


import com.cy.store.entity.QueryPageBean;
import com.cy.store.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {
    void add(CheckItem checkItem);

    List<CheckItem> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    List<CheckItem> findAll();
}
