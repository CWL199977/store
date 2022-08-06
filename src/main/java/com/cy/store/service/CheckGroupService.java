package com.cy.store.service;


import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(Integer[] checkitemIds, CheckGroup checkGroup);

    List<CheckGroup> findAll();
}
