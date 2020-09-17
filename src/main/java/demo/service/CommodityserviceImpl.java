package demo.service;

import demo.dao.Commoditydao;
import demo.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityserviceImpl {
    @Autowired
    private Commoditydao commoditydao;

    public boolean insertCommodity(Commodity commodity) {
        return commoditydao.insertCommodity(commodity) != 0;
    }
}
