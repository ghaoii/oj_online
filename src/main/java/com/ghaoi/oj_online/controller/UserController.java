package com.ghaoi.oj_online.controller;

import com.ghaoi.oj_online.mapper.UserMapper;
import com.ghaoi.oj_online.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    // 登录
    @RequestMapping("/login")
    public Object login(String username, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int state = -1;
        String msg = "";
        if(username == null || "".equals(username)) {
            msg = "未输入用户名";
        }else if(password == null || "".equals(password)){
            msg = "未输入密码";
        }else {
            User user = userMapper.search(username);
            if(user == null) {
                // 没有找到用户
                msg = "用户名不存在!";
            }else {
                // 根据用户名找到了用户
                if(!password.equals(user.getPassword())) {
                    msg = "密码错误!";
                }else {
                    state = 1;
                    // 设置session属性
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                }
            }
        }
        map.put("state", state);
        map.put("msg", msg);
        return map;
    }

    // 注册
    @RequestMapping("/register")
    public Object register(String username, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int state = -1;
        String msg = "";
        if(username == null || username.equals("")) {
            msg = "未输入用户名";
        }else if(password == null || "".equals(password)){
            msg = "未输入密码";
        }else {
            // 检查数据库中是否已经存在该用户名
            if(userMapper.search(username) != null) {
                // 找到该用户
                msg = "用户名已存在!";
            }else {
                // 没有找到该用户
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                int i = userMapper.addUser(user);
                if(i == 1) {
                    state = 1;
                    log.info("用户添加成功: " + username);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                }else {
                    msg = "添加失败";
                    log.info("用户添加失败");
                }
            }
        }
        map.put("state", state);
        map.put("msg", msg);
        return map;
    }


    // 注销
    @RequestMapping("/logout")
    public Object logout(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int state = -1;
        String msg = "";
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            // 说明当前没有登录
            msg = "您还没有登录!";
            log.info("当前用户还没有登录");
        }else {
            state = 1;
            session.removeAttribute("user");
            log.info("用户已注销");
        }
        map.put("state", state);
        map.put("msg", msg);
        return map;
    }
}
