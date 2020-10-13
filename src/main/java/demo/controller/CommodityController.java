package demo.controller;

import demo.config.annotation.LogRecord;
import demo.entity.Commodity;
import demo.service.CommodityserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommodityController {

    @Autowired
    private CommodityserviceImpl commodityservice;

    @RequestMapping(value = "add_commodity", method = RequestMethod.POST)
    @LogRecord(operation = "商品操作",type = "增加商品")
    @ResponseBody
    public String addCommodity(@RequestBody Commodity commodity) {
        if (commodityservice.insertCommodity(commodity)) {
            return "sucess insert" + commodity.getCname();
        }
        return "添加商品失败";
    }
}
