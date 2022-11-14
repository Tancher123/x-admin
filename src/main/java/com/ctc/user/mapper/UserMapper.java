package com.ctc.user.mapper;

import com.ctc.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:42
 * @version:1.0
 **/
@Repository
public interface UserMapper {
    /**
     * @param user:
     * @return User
     * @author 陈天赐
     * @description 登录
     * @date 2022/11/8 9:43
     */
    public User getUser (User user);


    /**
     * @param id:
     * @return User
     * @author 陈天赐
     * @description 通过id查询信息
     * @date 2022/11/10 14:43
     */
    @Select("select * from user where id =#{id}")
    User getUserById (int id);

    /**
     * @param new_password:
     * @param id:
     * @return int
     * @author 陈天赐
     * @description 通过id修改密码
     * @date 2022/11/10 14:44
     */
    int updateUserPassword (@Param("new_password") String new_password , @Param("id") int id);

    /**
     * @param :
     * @return int
     * @author 陈天赐
     * @description 注册
     * @date 2022/11/10 14:45
     */
    int addUser (@Param("username") String username , @Param("password") String password , @Param("chName") String chName);

    /**
     * @param username:
      * @return User
     * @author 陈天赐
     * @description 通过用户名查询信息，判断用户名是否存在
     * @date 2022/11/10 14:57
     */
    @Select("select * from user where username =#{username}")
    User getUserByUsername (String username);

    /**
     * @param userId:
      * @return int
     * @author 陈天赐
     * @description 注销
     * @date 2022/11/12 11:04
     */
    @Delete ( "delete from user where id = #{userId}" )
    int deleteUserAndAccount (int userId);
}
