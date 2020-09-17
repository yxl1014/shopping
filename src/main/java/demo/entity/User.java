package demo.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -522493560428408386L;

    /*用户id，（key）
    用户姓名：name，
    用户账号：user，
    用户密码：password；*/
    private int id;
    private String name;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
