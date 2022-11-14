package com.ctc;

import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.impl.EmpServiceImpl;
import com.ctc.user.mapper.AccountMapper;
import com.ctc.user.mapper.UserMapper;
import com.ctc.user.pojo.Account;
import com.ctc.user.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@SpringBootTest
class XAdminApplicationTests {
//    @Autowired
//    private EmpServiceImpl empService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void contextLoads2 ( ) {
//        User all = userMapper.getAllById (1 );
//        System.out.println (all.getAccount ());
//        System.out.println (all );
        Account accountClass = accountMapper.getAccountAndAccountClassAndUser ( 1 );
        System.out.println (accountClass.getAccountClass ().getClassName () );
        System.out.println (accountClass.getUser ().getUsername () );
    }

    @Test
    void contextLoads ( ) {
//        Emp emp = new Emp ( );
//        emp.setName ( "测试" );
//        emp.setBirthday ( new Date (  ) );
//        int i = empService.addEmp (emp );
//
//        if ( i>0 ){
//            System.out.println ("成功" );
//        }else {
//            System.out.println ("失败" );
//        }
        //md5 spring 提供的加密方法，要自己加盐
//        String s1 = DigestUtils.md5DigestAsHex ( "123456".getBytes ( ) );
//        String s2 = DigestUtils.md5DigestAsHex ( "123456".getBytes ( ) );
//
//        System.out.println (s1 );//e10adc3949ba59abbe56e057f20f883e
//        System.out.println (s2 );//e10adc3949ba59abbe56e057f20f883e




        //spring security 安全框架提供的加密方法，可以自动加盐，无需自己保存盐值
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
        String encode1 = passwordEncoder.encode ( "123456" );
        String encode2 = passwordEncoder.encode ( "123456" );
        System.out.println ( encode1 );//$2a$10$WSneMceTF0didcIF1MfIt.xmtn0VTf8EHuHy.ljUTpunmrNhl3eC2
        System.out.println ( encode2 );//$2a$10$DFyiehSYX7qnu4zl7Z2Jqe63mE6cVq/dstWpbKgPqNfnJnrWX8AkG
        //验证
        boolean matches1 = passwordEncoder.matches ( "123456" , "$2a$10$DFyiehSYX7qnu4zl7Z2Jqe63mE6cVq/dstWpbKgPqNfnJnrWX8AkG" );
        boolean matches2 = passwordEncoder.matches ( "123456" , "$2a$10$WSneMceTF0didcIF1MfIt.xmtn0VTf8EHuHy.ljUTpunmrNhl3eC2" );
        System.out.println (matches1 );//true
        System.out.println (matches2 );//true
    }

}
