package demo.config.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {
    @Value("${URL}")
    private String url;

    @Value("${DRIVER}")
    private String driver;

    @Value("${USERNAME}")
    private String username;

    @Value("${PASSWORD}")
    private String password;

    @Value("${FILTERS}")
    private String filters;

    @Value("${CONNECTION_PROPERTIES}")
    private String connectionProperties;

    public String getFilters() {
        return filters;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
