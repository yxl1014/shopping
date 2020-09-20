package demo.dao;

import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class Userdao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertUser(User user) {
        String sql = "insert into userr (uname,uuser,password) values(?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
    }

    public int updateUser(User user) {
        String sql = "delete from userr where username=? and password=?";
        if (jdbcTemplate.update(sql, user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).replace('+','@')) == 0)
            return 0;
        else {
            sql = "insert into userr (uname,uuser,password) values(?,?,?)";
            return jdbcTemplate.update(sql, user.getName(), user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).replace('+','@'));
        }
    }

    public User finduserbyname(String name) {
        User user = new User();
        String sql = "select uid,uname,uuser from userr where uname=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                if(resultSet.next()){
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setUsername(resultSet.getString(3));
                }
            }
        });
        return user;
    }
}
