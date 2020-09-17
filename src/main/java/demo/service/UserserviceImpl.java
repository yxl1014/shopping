package demo.service;

import demo.dao.Userdao;
import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserserviceImpl {

    @Autowired
    private Userdao userdao;

    public boolean insertUser(User user) {
        //用户重名校验
        User userById = userdao.finduserbyuid(user.getId());
        if (userById != null && userById.getName() != null && userById.getName().equals(user.getName())) {
            return false;
        }
        //对用户密码进行MD5,目的是，数据库中的敏感数据，不要存储明文。
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userdao.insertUser(user) != 0;
    }

    @Cacheable(cacheNames = "user", key = "#user.id")
    public int updateUser(User user) {
        return userdao.updateUser(user);
    }
}
