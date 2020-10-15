package demo.dao.mapper;

import demo.entity.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Commoditymapper {

    @Insert(value = "insert into commodity (cname,category,price) values (${commodity.cname},${commodity.category},${commodity.price})")
    int insertcommodity(@Param(value = "commidty") Commodity commodity);
}
