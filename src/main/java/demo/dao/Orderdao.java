package demo.dao;

import demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class Orderdao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Integer> findByUserId(int uid) {
        ArrayList<Integer> oids = new ArrayList<>();
        String sql = "SELECT oid FROM orderr WHERE uid=?";
        jdbcTemplate.query(sql, new Object[]{uid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    oids.add(resultSet.getInt(1));
                }
            }
        });
        return oids;
    }

    public int delete_Order_by_cid_from_uid(int uid, int cid) {
        String sql = "delete from orderr where uid=? and cid=?";
        return jdbcTemplate.update(sql, uid, cid);
    }

    public ArrayList<Order> findallbyuid(int uid) {
        ArrayList<Order> orders = new ArrayList<>();
        Order o = new Order();
        String sql = "select * from orderr where uid=?";
        jdbcTemplate.query(sql, new Object[]{uid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    o.setOid(resultSet.getInt(1));
                    o.setUid(resultSet.getInt(2));
                    o.setCid(resultSet.getInt(3));
                    o.setTime(resultSet.getString(4));
                    orders.add(o);
                }
            }
        });
        return orders;
    }

    public int insertOrder(Order order) {
        String sql = "update insert orderr (uid,cid,otime) values(?,?,?)";
        return jdbcTemplate.update(sql, order.getUid(), order.getCid(), order.getTime());
    }
}
