package demo.dao;

import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class Userdao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertUser(User user) {
        String sql = "update insert userr (name,user,password) values(?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
    }

    public int updateUser(User user) {
        String sql = "delete from userr where username=? and password=?";
        if (jdbcTemplate.update(sql, user.getUsername(), user.getPassword()) == 0)
            return 0;
        else {
            sql = "update insert userr (name,user,password) values(?,?,?)";
            return jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
        }
    }

    public User finduserbyuid(int uid) {
        User user = new User();
        String sql = "select * from userr where uid=?";
        jdbcTemplate.query(sql, new Object[]{uid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
        });
        return user;
    }
}
