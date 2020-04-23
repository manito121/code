package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/","index.html"})//可以配置多个映射路径
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){

        boolean isV = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if(!isV){
            return new ModelAndView("login","error","用户名或密码错误！");
        }else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastVisit(new Date());
            user.setLastIp(request.getLocalAddr());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }

    }
}
