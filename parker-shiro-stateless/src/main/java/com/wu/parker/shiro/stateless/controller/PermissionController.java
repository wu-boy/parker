package com.wu.parker.shiro.stateless.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
@Api(description = "资源服务")
@RestController
@RequestMapping("/security/permissions/")
public class PermissionController {

    @ApiOperation("查询资源")
    @GetMapping()
    @RequiresPermissions("permission:retrieve")
    public String get(@RequestHeader("token") String token){
        return "有permission:retrieve这个权限的用户才能访问，不然访问不了";
    }
}
