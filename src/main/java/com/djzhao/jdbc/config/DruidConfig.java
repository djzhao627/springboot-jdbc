package com.djzhao.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置
 *
 * @author djzhao
 * @date 20/03/19 11:34
 * @email djzhao627@gmail.com
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    // druid后台监控
    // ServletRegistrationBean：相当于web.xml
    @Bean
    public ServletRegistrationBean servlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 配置参数
        Map<String, String> params = new HashMap<>();
        // 登录用户名和密码
        params.put("loginUsername", "druid");
        params.put("loginPassword", "druid");

        // 允许谁可以访问
        params.put("allow", "localhost");

        // 禁止谁访问
        params.put("djzhao", "192.168.31.123");


        // 设置初始化参数
        registrationBean.setInitParameters(params);
        return registrationBean;
    }

}
