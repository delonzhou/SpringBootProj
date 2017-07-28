package com.marving.boot.controller;

import com.marving.boot.common.ThreadHolder;
import com.marving.boot.domain.Tenant;
import com.marving.boot.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mercop on 2017/7/27.
 */

@RestController
@RequestMapping("/tenants")
public class TenantController {



    @Autowired
    private TenantService tenantService;

    @RequestMapping(value = "/login/{id}",method = RequestMethod.GET)
    public List<Tenant> login(@PathVariable String id){
        //模拟登陆 - 动态选择所在数据库
        ThreadHolder.putTenant(id);
        return tenantService.getAllTenantOfThisDb();
    }
}
