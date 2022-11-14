package com.ctc.common.controller;


import com.ctc.common.vo.Result;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:15
 * @version:1.0
 **/
@Controller
public class CommonController {
    //跳转到登录页面
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
    //登录跳转到index页面
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    //跳转到注册页面
    @GetMapping("/to/register")
    public String toRegister(){
        return "register";
    }

    //跳转到找回密码页面
    @GetMapping("/findPwd")
    public String toFindPwd(){
        return "findPwd";
    }


    //注销
    @GetMapping("/logout")
    @ResponseBody
    public Result<Object> logout(HttpSession session){
        session.removeAttribute ("loginUsername");
        return Result.success ( "您已成功退出登录" );
    }
    //验证码
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
