package demo.dao;

import demo.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Commoditydao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertCommodity(Commodity commodity) {
        String sql = "update insert commodity (name,category,price) values (?,?,?)";
        return jdbcTemplate.update(sql, commodity.getCname(), commodity.getCategory(), commodity.getPrice());
    }
}
