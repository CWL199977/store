package com.cy.store.service.impl;


import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.mapper.CheckItemMapper;
import com.cy.store.pojo.CheckItem;
import com.cy.store.service.CheckItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页条件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        List<CheckItem> checkItemList = checkItemMapper.findPage(queryPageBean);

        //获取分页信息
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItemList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void delete(Integer id) {
        checkItemMapper.delete(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.findAll();
    }
}
