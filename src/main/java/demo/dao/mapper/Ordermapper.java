package demo.dao.mapper;

import demo.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Ordermapper {

    @Insert(value = "insert into orderr (uid,cid,otime) values(${order.uid},${order.cid},${order.time}))")
    int insertOrder(@Param(value = "order") Order order);

    @Delete(value = "delete from orderr where uid=${uid} and cid=${cid}")
    int deleteorder(@Param(value = "uid") int uid,@Param(value = "cid") int cid);
}
