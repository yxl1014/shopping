package demo.entity;

import java.io.Serializable;

public class Commodity implements Serializable {
    private static final long serialVersionUID = -5580480076523893877L;

    /*
     *商品id
     *商品名
     *商品类
     *商品价格
     * */
    private int cid;
    private String cname;
    private String category;
    private String price;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
