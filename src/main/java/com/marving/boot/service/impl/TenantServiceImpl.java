package com.marving.boot.service.impl;

import com.marving.boot.dao.saas.TenantDao;
import com.marving.boot.domain.Tenant;
import com.marving.boot.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mercop on 2017/7/27.
 */
@Service("TenantService")
public class TenantServiceImpl implements TenantService{

    @Autowired
    private TenantDao tenantDao;

    @Override
    public List<Tenant> getAllTenantOfThisDb() {
        return tenantDao.getAllTenantOfThisDb();
    }
}
