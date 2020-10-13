package demo.controller;

import demo.config.annotation.LogRecord;
import demo.entity.User;
import demo.service.UserserviceImpl;
import demo.support.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserserviceImpl userservice;

    @RequestMapping(value = "add_user", method = RequestMethod.POST)
    @LogRecord(operation = "用户操作",type = "注册用户")
    @ResponseBody
    public String insertUser(@RequestBody User user) {
        if (userservice.insertUser(user)) {
            return "sucess insert" + user.getName()+" token:"+JWTUtil.sign(user,60L * 1000L * 30L);
        }
        return "添加用户失败";
    }

    @RequestMapping(value = "update_user", method = RequestMethod.POST)
    @LogRecord(operation = "用户操作",type = "更改用户")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        if (userservice.updateUser(user) != 0) {
            return "sucess insert" + user.getName();
        }
        return "修改用户失败";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @LogRecord(operation = "用户操作",type = "登录")
    @ResponseBody
    public String login(String username,String password){
        User user = userservice.finduserbyup(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user == null || user.getName() == null) {
            return "用户不存在或用户名、密码错误";
        }
        String sign = JWTUtil.sign(user, 60L * 1000L * 30L);
        return "hello" + user.getName() + "token:" + sign;
    }
}
