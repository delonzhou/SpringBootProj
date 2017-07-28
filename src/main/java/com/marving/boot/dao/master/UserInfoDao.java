package com.marving.boot.dao.master;


import com.marving.boot.domain.master.UserInfo;

import java.util.List;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
public interface UserInfoDao {

    List<UserInfo> getAllUser();

    UserInfo findById(String id);

    void update(UserInfo userInfo);

}
