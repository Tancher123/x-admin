package com.ctc.user.service;

import com.ctc.user.pojo.User;

public interface UserService {
        /**
     * @param user:
     * @return User
     * @author 陈天赐
     * @description 登录
     * @date 2022/11/8 10:23
     */

      public User getUser(User user);


    User getUserById (int id);

    int updateUserPassword(String new_password,int id);

    int addUser(String username ,String password ,String chName);

    User getUserByUsername(String username);

    int deleteUserAndAccount (int userId);
}
