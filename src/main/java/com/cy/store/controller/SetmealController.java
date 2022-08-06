package com.cy.store.controller;


import com.cy.store.util.TencentUtil;
import com.cy.store.constant.MessageConstant;
import com.cy.store.entity.PageResult;
import com.cy.store.entity.QueryPageBean;
import com.cy.store.entity.Result;
import com.cy.store.pojo.Setmeal;
import com.cy.store.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增检查项
     *
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkgroupIds, @RequestBody Setmeal setmeal) {
        try {
            setmealService.add(checkgroupIds, setmeal);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    /**
     * 分页查询套餐
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.findPage(queryPageBean);
    }

    /**
     * 根据id删除检查项
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.delete(id);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }


    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile mulipartFilet) throws IOException {
        String fileName = mulipartFilet.getOriginalFilename();
        //判断有无后缀
        if (fileName.lastIndexOf(".")<0) {
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        if(!prefix.equalsIgnoreCase(".jpg")&&!prefix.equalsIgnoreCase(".jpeg")
                && !prefix.equalsIgnoreCase(".svg")){
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        mulipartFilet.transferTo(excelFile);

        //调用工具上传文件
        String imageName = TencentUtil.uploadfile(excelFile, "avatar");

        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);

    }

}
