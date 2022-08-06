package com.cy.store.controller;

import com.cy.store.constant.MessageConstant;
import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.entity.Result;
import com.cy.store.pojo.CheckItem;
import com.cy.store.service.CheckItemService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    /**
     * 新增检查项
     * @param checkItem
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    /**
     * 分页查询检查项
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.findPage(queryPageBean);
    }

    /**
     * 根据id删除检查项
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    /**
     * 查询所有检查项
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckItem> checkItemList = checkItemService.findAll();
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS, checkItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }
}
