package com.marving.boot.service.impl;


import com.marving.boot.cache.RedisCache;
import com.marving.boot.dao.cluster.AdminDao;
import com.marving.boot.dao.master.UserInfoDao;
import com.marving.boot.domain.cluster.Admin;
import com.marving.boot.domain.master.UserInfo;
import com.marving.boot.service.UserRedisService;
import com.marving.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
@Service("userRedisService")
public class UserRedisServiceImpl implements UserRedisService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<UserInfo> getAllUser() {
        return userInfoDao.getAllUser();
    }

    @Override
    public UserInfo findById(String id) {
        String username;
        if((username = redisCache.getInfo(id))==null){
            UserInfo userInfo = userInfoDao.findById(id);
            System.out.println("read db :" + userInfo.getName());
            //update cache;
            redisCache.setInfo(userInfo.getId(),userInfo.getName());
        } else{
            System.out.println("hit the redis cache: " + username);
        }

        return null;
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
        System.out.println("更新数据库成功");
        String name = redisCache.getInfo(userInfo.getId());
        if(name == null){
            redisCache.setInfo(userInfo.getId(),userInfo.getName());
        } else{
            redisCache.setInfo(userInfo.getId(),userInfo.getName());
        }
        System.out.println("写入redis成功");
    }
}
