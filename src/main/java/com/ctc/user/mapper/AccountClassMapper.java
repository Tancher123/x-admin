package com.ctc.user.mapper;

import com.ctc.user.pojo.AccountClass;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-08 17:06
 * @version:1.0
 **/
@Repository
public interface AccountClassMapper {

    @Select ( "select * from accountclass" )
    List<AccountClass> findAllAccountClass();
}
