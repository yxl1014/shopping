package demo.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 8980715928561207698L;

    /*订单oid，（key）
    用户id：uid
    商品id，cid
    订单时间，time*/
    private int oid;
    private int uid;
    private int cid;
    private String time;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
