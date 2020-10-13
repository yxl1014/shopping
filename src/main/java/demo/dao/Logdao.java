package demo.dao;

import demo.config.info.JdbcInfo;
import demo.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Logdao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert_log(Log log){
        String sql="insert into loginfo (log_op,log_type,uid,ltime,url) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{log.getLog_op(),log.getLog_type(),log.getUid(),log.getLtime(),log.getUrl()});
    }
}
