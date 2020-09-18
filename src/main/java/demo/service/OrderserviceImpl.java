package demo.service;

import demo.dao.Orderdao;
import demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderserviceImpl {

    @Autowired
    private Orderdao orderdao;

    public boolean insertOrder(Order order) {
        return orderdao.insertOrder(order) != 0;
    }

    @Cacheable(cacheNames = "order", key = "#uid")
    public ArrayList<Integer> findByUserId(int uid) {
        return orderdao.findByUserId(uid);
    }

    @Cacheable(cacheNames = "order", key = "#uid")
    public int delete_Order_by_cid_from_uid(int uid, int cid) {
        return orderdao.delete_Order_by_cid_from_uid(uid, cid);
    }

    @Cacheable(cacheNames = "order", key = "#uid")
    public ArrayList<Order> findallbyuid(int uid) {
        return orderdao.findallbyuid(uid);
    }
}
