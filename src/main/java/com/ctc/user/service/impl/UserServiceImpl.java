package com.ctc.user.service.impl;

import com.ctc.user.mapper.UserMapper;
import com.ctc.user.pojo.User;
import com.ctc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:48
 * @version:1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser (User user) {
        return userMapper.getUser ( user );
    }

    @Override
    public User getUserById (int id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public int updateUserPassword (String new_password,int id) {
        int i = userMapper.updateUserPassword ( new_password,id );
        return i;
    }

    @Override
    public int addUser (String username ,String password ,String chName) {
        int i = userMapper.addUser ( username,password,chName );
        return i;
    }


    @Override
    public User getUserByUsername (String username) {
        User userByUsername = userMapper.getUserByUsername ( username );
        return userByUsername;
    }

    @Override
    public int deleteUserAndAccount (int userId) {
        int i = userMapper.deleteUserAndAccount(userId);
        return i;
    }
}
