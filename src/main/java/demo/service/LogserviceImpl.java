package demo.service;

import demo.dao.Logdao;
import demo.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogserviceImpl {

    @Autowired
    private Logdao logdao;

    public int log_insert(Log log){
        return logdao.insert_log(log);
    }
}
