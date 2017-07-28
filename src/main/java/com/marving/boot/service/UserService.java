package com.marving.boot.service;


import com.marving.boot.domain.cluster.Admin;
import com.marving.boot.domain.master.UserInfo;

import java.util.List;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
public interface UserService {
    List<UserInfo> getAllUser();

    List<Admin> getAllAdmin();
}
