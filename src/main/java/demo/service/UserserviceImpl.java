package demo.service;

import demo.dao.Userdao;
import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserserviceImpl {

    @Autowired
    private Userdao userdao;

    public boolean insertUser(User user) {
        //用户重名校验
        User userByname = userdao.finduserbyname(user.getName());
        if (userByname != null && userByname.getName() != null && userByname.getName().equals(user.getName())) {
            return false;
        }
        //对用户密码进行MD5,目的是，数据库中的敏感数据，不要存储明文。
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userdao.insertUser(user) != 0;
    }

    public int updateUser(User user) {
        return userdao.updateUser(user);
    }

    public User finduserbyup(String usrname,String password){
        return userdao.finduserbyup(usrname,password);
    }
    public User findbyid(int id){
        return userdao.findbyid(id);
    }
}
