package com.ctc.user.controller;

import com.ctc.common.vo.Result;
import com.ctc.user.pojo.Account;
import com.ctc.user.pojo.User;
import com.ctc.user.service.impl.AccountServiceImpl;
import com.ctc.user.service.impl.UserServiceImpl;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:52
 * @version:1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private AccountServiceImpl accountService;

    //跳转到密码修改页面
    @GetMapping("")
    public String toUpdatePassword ( ) {
        return "user/user-password";
    }

    //修改密码
    @PutMapping("")
    @ResponseBody
    public Result<Object> updatePassword (@RequestParam("id") int id ,
                                          @RequestParam("old_password") String old_password ,
                                          @RequestParam("new_password") String new_password) {
        User userById = userService.getUserById ( id );
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
        //参数一是请求密码，参数二是数据库中的值
        boolean matches = passwordEncoder.matches ( old_password , userById.getPassword ( ) );
        if ( !matches ) {
            return Result.fail ( "旧密码错误" );
        }
        boolean newAndOld = passwordEncoder.matches ( new_password , userById.getPassword ( ) );
        if ( newAndOld ) {
            return Result.fail ( "新密码不能为近期用过的密码" );
        }

        String encode = passwordEncoder.encode ( new_password );
        int i = userService.updateUserPassword ( encode , id );
        if ( i > 0 ) {
            return Result.success ( "密码修改成功重新登录" );
        }
        return Result.fail ( "密码修改失败" );
    }

    //找回密码前的信息验证
    @PostMapping("/findPwd")
    @ResponseBody
    public Result<Object> findPwd (@RequestParam("username") String username ,
                                   @RequestParam("aName") String aName ,
                                   @RequestParam("aCid") String aCid) {
        User user = userService.getUserByUsername ( username );
        if ( user == null ) {
            return Result.fail ( "账号不存在" );
        }
        Integer id = user.getId ( );
        Account account = accountService.getAccountByUserId ( id );
//        Account account = accountService.getAccountByANameAndACid(aName,aCid);
        if ( account == null ) {
            return Result.fail ( "您的账号未绑定身份证，找回失败，请联系管理员" );
        }
        if ( !account.getAName ( ).equals ( aName ) ) {
            return Result.fail ( "姓名匹配错误，验证失败！" );
        }
        if ( !account.getACid ( ).equals ( aCid ) ) {
            return Result.fail ( "身份证匹配错误，验证失败！" );
        }
        if ( user != null && account.getACid ( ).equals ( aCid ) && account.getAName ( ).equals ( aName ) ) {
            return Result.success ( "验证成功" , id );
        } else
            return Result.fail ( "未知明错误，验证失败！" );
    }

    //密码找回
    @PutMapping("updatePwd/{id}")
    @ResponseBody
    public Result<Object> updateUserPwd (@PathVariable("id") Integer id , @RequestParam("password") String password) {
        //将密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
        String updatePwd = passwordEncoder.encode ( password );
        int i = userService.updateUserPassword ( updatePwd , id );
        if ( i > 0 ) {
            return Result.success ( );
        }
        return Result.fail ( "密码找回失败，请联系管理员！" );
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login (User user ,
                                 @RequestParam("captcha") String captcha ,
                                 HttpServletRequest request ,
                                 HttpServletResponse response,
                                 HttpSession session,
                                 @RequestParam("rememberMe")Boolean rememberMe) {
        //验证码判断
        if ( !CaptchaUtil.ver ( captcha , request ) ) {
            CaptchaUtil.clear ( request );  // 清除session中的验证码
            return Result.fail ( "验证码错误" );
        }
        User login = userService.getUser ( user );
        if ( login != null ) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
            //参数一是请求密码，参数二是数据库中的值
            boolean matches = passwordEncoder.matches ( user.getPassword ( ) , login.getPassword ( ) );
            if ( matches ) {
                //登录成功
                //记住账号
                Cookie c_password = new Cookie ("password",user.getPassword () );
                Cookie c_username = new Cookie ("username",user.getUsername () );
                session.setAttribute ( "username",user.getUsername () );
                c_username.setMaxAge ( 60*60*24*30 );
                response.addCookie ( c_username );
                //判断是否选择记住密码
                if (rememberMe == true ){
                    //勾选记住密码了
                    //设置存活时间
                    session.setAttribute ( "password",user.getPassword () );
                    session.setAttribute ( "rememberMe",true );
                    c_password.setMaxAge ( 60*60*24*30 );
                    //发送
                    response.addCookie ( c_password );
                }else {
                    c_password.setMaxAge ( 0 );
                }
                session.setAttribute ( "loginUsername" , login.getUsername ( ) );
                session.setAttribute ( "loginChName" , login.getChName ( ) );
                session.setAttribute ( "userId" , login.getId ( ) );
                return Result.success ( "登录成功" , login.getUsername ( ) );
            }
        }
        //登录失败
        return Result.fail ( "用户名或密码错误" );
    }

    @PostMapping("/register")
    @ResponseBody
    public Result<Object> register (@RequestParam("username") String username ,
                                    @RequestParam("password") String password ,
                                    @RequestParam("captcha") String captcha ,
                                    HttpServletRequest request) {
        //验证码判断
        if ( !CaptchaUtil.ver ( captcha , request ) ) {
            CaptchaUtil.clear ( request );  // 清除session中的验证码
            return Result.fail ( "验证码错误" );
        }
        if ( username != null && password != null ) {
            //将密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
            String addPwd = passwordEncoder.encode ( password );
            String chName = "普通用户";
            User userByUsername = userService.getUserByUsername ( username );
            if ( userByUsername != null ) {
                //用户名已经存在
                return Result.fail ( "用户名已存在" );
            }
            int i = userService.addUser ( username , addPwd , chName );
            if ( i > 0 ) {
                //注册成功
                return Result.success ( "注册成功" );
            }
        }
        //注册失败
        return Result.fail ( "注册失败" );
    }

    //查询基本信息
    @GetMapping("/account/{id}")
    public String getAll (@PathVariable("id") int id , Model model) {
        Account accountClassAndUser = accountService.getAccountAndAccountClassAndUser ( id );
        System.out.println ( "======>" + accountClassAndUser );
        if ( accountClassAndUser != null ) {
            model.addAttribute ( "accountList" , accountClassAndUser );
            model.addAttribute ( "accountClassList" , accountService.findAllAccountClass ( ) );
            return "user/user-setting";
        }
        model.addAttribute ( "accountClassList" , accountService.findAllAccountClass ( ) );
        model.addAttribute ( "msg" , "您的信息尚未完善，为了避免忘记密码找回失败，请完善个人信息，绑定自己的身份证" );
        return "user/user-setting-new";
    }

    //新增基本信息
    @PostMapping("/account")
    @ResponseBody
    public Result<Object> addAccount (Account account , Model model) {
        int i = accountService.addAccount ( account );
        if ( i > 0 ) {
            model.addAttribute ( "userId" , account.getUserId ( ) );
            return Result.success ( "您的基本信息保存成功" );
        }
        return Result.fail ( "您的基本信息保存失败" );
    }

    //修改基本信息
    @PutMapping("/account")
    @ResponseBody
    public Result<Object> updateAccount (Account account , Model model) {
        int i = accountService.updateAccount ( account );
        if ( i > 0 ) {
            model.addAttribute ( "userId" , account.getUserId ( ) );
            return Result.success ( "您的基本信息修改成功" );
        }
        return Result.fail ( "您的基本信息修改失败" );
    }


    //注销(删除用户账户名密码和对应的基本信息)
    @DeleteMapping("/{userId}")
    @ResponseBody
    public Result<Object> deleteUserAndAccount (@PathVariable("userId") int userId) {
        int i = accountService.deleteUserAndAccount ( userId );
        int deleteUserAndAccount = userService.deleteUserAndAccount ( userId );
        if ( i > 0 && deleteUserAndAccount > 0 ) {
            //注销成功
            return Result.success ( "注销成功!" );
        }
        return Result.fail ( "注销失败请联系管理员" );
    }

}
