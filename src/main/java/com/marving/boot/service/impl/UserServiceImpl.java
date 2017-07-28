package com.marving.boot.service.impl;


import com.marving.boot.dao.cluster.AdminDao;
import com.marving.boot.dao.master.UserInfoDao;
import com.marving.boot.domain.cluster.Admin;
import com.marving.boot.domain.master.UserInfo;
import com.marving.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<UserInfo> getAllUser() {
        return userInfoDao.getAllUser();
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }
}
