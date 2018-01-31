package com.boleiot.controller;

import com.boleiot.model.user.User;
import com.boleiot.service.UserService;
import com.boleiot.utils.HttpResult;
import com.boleiot.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public HttpResult userLogin(@RequestBody Map<String, Object> paramMap, HttpSession session) {
        String username = paramMap.get("username").toString();
        String password = paramMap.get("password").toString();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        User user = new User();
        user.setName("zhouxiaojun");
        user.setPassword("123456");
        new PasswordHelper().encryptPassword(user);
        log.info("login password = " + user.getPassword() + " salt=" + user.getSalt());
        try {
            subject.login(token);
            return HttpResult.ok();
        } catch (IncorrectCredentialsException ice) {
            return new HttpResult(201, "密码错误!", "");
        } catch (UnknownAccountException uae) {
            return new HttpResult(202, "账号不存在!", "");
        } catch (ExcessiveAttemptsException eae) {
            return new HttpResult(203, "登录次数过多!", "");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public HttpResult modifyPassword(@RequestBody Map<String, Object> paramMap) {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        int row = userService.changePassword(userName, paramMap.get("password").toString());
        if (row > 0) {
            return HttpResult.ok();
        }
        return new HttpResult(203, "修改密码失败!", "");
    }
}
