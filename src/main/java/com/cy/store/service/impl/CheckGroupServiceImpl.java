package com.cy.store.service.impl;

import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.mapper.CheckGroupMapper;
import com.cy.store.pojo.CheckGroup;
import com.cy.store.service.CheckGroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        //1、保存检查组信息--->返回自增id
        checkGroupMapper.addCheckGroup(checkGroup);
        //2、保存关联表信息
        Integer checkGroupId = checkGroup.getId();
        //shift+alt+m
        addCheckGroupCheckItem(checkitemIds, checkGroupId);
    }

    private void addCheckGroupCheckItem(Integer[] checkitemIds, Integer checkGroupId) {
        for (Integer checkitemId : checkitemIds) {
            checkGroupMapper.addCheckGroupCheckItem(checkGroupId, checkitemId);
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页条件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        List<CheckGroup> checkGroupList = checkGroupMapper.findPage(queryPageBean);

        //获取分页信息
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(checkGroupList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        //1、修改检查组信息
        checkGroupMapper.edit(checkGroup);
        //2、删除关联表的信息
        Integer checkGroupId = checkGroup.getId();
        checkGroupMapper.deleteCheckGroupCheckItemByCheckGroupId(checkGroupId);
        //3、保存关联表信息
        addCheckGroupCheckItem(checkitemIds, checkGroupId);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }
}
