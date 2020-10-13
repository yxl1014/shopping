package demo.controller;

import demo.config.annotation.LogRecord;
import demo.entity.Order;
import demo.service.OrderserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class OrderController {
    @Autowired
    private OrderserviceImpl orderservice;

    @RequestMapping(value = "add_order", method = RequestMethod.POST)
    @LogRecord(operation = "订单操作",type = "添加订单")
    @ResponseBody
    public String addCommodity(@RequestBody Order order) {
        if (orderservice.insertOrder(order))
            return "sucess insert" + order.getTime();
        return "添加订单失败";
    }

    @RequestMapping(value = "delete_order", method = RequestMethod.GET)
    @LogRecord(operation = "订单操作",type = "删除订单")
    @ResponseBody
    public String deletobyuoid(@RequestParam(name = "uid") int uid, @RequestParam(name = "cid") int cid) {
        if (orderservice.delete_Order_by_cid_from_uid(uid, cid) != 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }

    @RequestMapping(value = "findbyuid_order", method = RequestMethod.GET)
    @LogRecord(operation = "订单操作",type = "uid查询订单")
    @ResponseBody
    public String findbyuid(@RequestParam(name = "uid") int uid) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("用户id:" + uid + "\n");
        ArrayList<Integer> oid = orderservice.findByUserId(uid);
        if (oid.size() != 0) {
            for (Integer i : oid) {
                stringBuffer.append("\t订单id:" + i + ";\n");
            }
            stringBuffer.append("---------------end----------------");
            return stringBuffer.toString();
        }
        return "无该用户订单信息！";
    }


    @RequestMapping(value = "findallbyuid_order", method = RequestMethod.GET)
    @LogRecord(operation = "订单操作",type = "uid详细查询订单")
    @ResponseBody
    public String find_all(@RequestParam(name = "uid") int uid) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("用户id:" + uid + "\n");
        ArrayList<Order> orders = orderservice.findallbyuid(uid);
        if (orders.size() != 0) {
            for (Order order: orders) {
                stringBuffer.append("----------------------------------");
                stringBuffer.append("\t订单id:" + order.getOid() + ";\n");
                stringBuffer.append("\t商品id:" + order.getCid() + ";\n");
                stringBuffer.append("\t订单时间id:" + order.getTime() + ";\n");
            }
            stringBuffer.append("---------------end----------------");
            return stringBuffer.toString();
        }
        return "无该用户订单信息！";
    }
}
