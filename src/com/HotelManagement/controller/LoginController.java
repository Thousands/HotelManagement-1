package com.HotelManagement.controller;

import com.HotelManagement.pojo.User;
import com.HotelManagement.service.user.UserService;
import com.HotelManagement.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dologin.html")
    public String doLogin(@RequestParam(value = "username") String userCode,
                          @RequestParam(value = "password") String password,
                          HttpSession session, HttpServletRequest request){

        User loginUser = this.userService.login(userCode, password);

        if(loginUser!=null){
            session.setAttribute(Constants.USER_SESSION, loginUser);
            return "index";
        }else{
            request.setAttribute("error", "用户名或者密码错误");
            return "login";
        }
    }

    @RequestMapping(value = {"/sys/logout.html","/sys/user/logout.html"})
    public String logout(HttpSession session){

        session.invalidate();
        return "login";
    }
}
