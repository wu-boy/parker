package com.wu.parker.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wu.parker.common.web.BaseResult;
import com.wu.parker.easypoi.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/12/15
 */
@Api(description = "用户服务")
@RestController
@RequestMapping("/users/")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("导入excel")
    @PostMapping("import-excel")
    public BaseResult importExcel(@RequestParam("file") MultipartFile file){
        BaseResult result = new BaseResult();

        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<User> userList = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
            userList.forEach(o -> System.out.println(o));
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("导入excel{}", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}
