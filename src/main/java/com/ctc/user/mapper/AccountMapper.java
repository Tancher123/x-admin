package com.ctc.user.mapper;

import com.ctc.user.pojo.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    /**
     * @param id:
      * @return Account
     * @author 陈天赐
     * @description 查询用户的基本信息
     * @date 2022/11/8 13:13
     */
    Account getAccountAndAccountClassAndUser(int id);

    /**
     * @param account:
      * @return int
     * @author 陈天赐
     * @description 保存用户基本信息
     * @date 2022/11/9 20:08
     */
    int addAccount (Account account);

    /**
     * @param account:
      * @return int
     * @author 陈天赐
     * @description 修改员工信息
     * @date 2022/11/10 9:56
     */
    int updateAccount (Account account);

    /**
     * @param userId:
      * @return int
     * @author 陈天赐
     * @description 注销
     * @date 2022/11/12 11:00
     */
    @Delete ( "delete from account where user_id = #{userId}" )
    int deleteUserAndAccount (int userId);

    /**
     * @param aName:
    	 * @param aCid:
      * @return Account
     * @author 陈天赐
     * @description 找回密码前的信息验证
     * @date 2022/11/12 18:56
     */
    Account getAccountByANameAndACid (String aName , String aCid);

    @Select ( "select * from account where user_id = #{id}" )
    Account getAccountByUserId (Integer id);
}
