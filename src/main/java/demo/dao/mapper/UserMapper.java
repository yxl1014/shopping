package demo.dao.mapper;

import demo.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Insert(value = "insert into userr (uname,uuser,password) values(${user.name},${user.username},${user.password})")
    /*@Results(value = {
            @Result(column = "uname", property = "name"),
            @Result(column = "uuser", property = "username"),
            @Result(column = "password", property = "password"),
    })*/
    int insertUser(@Param(value = "user") User user);

    @Select(value = "select uid,uname,uuser from userr where uname=${name}")
    @Results(value = {
            @Result(column = "uname", property = "name"),
            @Result(column = "uuser", property = "username"),
            @Result(column = "uid", property = "id"),
    })
    User finduserbyname(@Param("name") String name);

    @Select(value = "select uid,uname,uuser,password from userr where uuser=${username} and password=${password}")
    @Results(value = {
            @Result(column = "uid", property = "id"),
            @Result(column = "uname", property = "name"),
            @Result(column = "uuser", property = "username"),
            @Result(column = "password", property = "password"),
    })
    User finduserbyup(@Param(value = "username") String username,@Param(value = "password") String password);

    @Select(value = "select uid,uname,uuser,password from userr where uid=${id}")
    @Results(value = {
            @Result(column = "uid", property = "id"),
            @Result(column = "uname", property = "name"),
            @Result(column = "uuser", property = "username"),
            @Result(column = "password", property = "password"),
    })
    User finduserbyid(@Param("id") int id);

    @Update(value = "UPDATE userr SET uname=#{user.name} WHERE uid=#{user.id}")
    int updateUser(@Param(value = "user") User user);

}
