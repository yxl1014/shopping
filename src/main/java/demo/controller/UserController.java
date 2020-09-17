package demo.controller;

import demo.entity.User;
import demo.service.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserserviceImpl userservice;

    @RequestMapping(value = "add_user", method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(@RequestBody User user) {
        if (userservice.insertUser(user)) {
            return "sucess insert" + user.getName();
        }
        return "添加用户失败";
    }

    @RequestMapping(value = "update_user", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        if (userservice.updateUser(user) != 0) {
            return "sucess insert" + user.getName();
        }
        return "修改用户失败";
    }
}
