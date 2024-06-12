package br.com.usystem.stockroll.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoGenerica {

    @Bean
    public Connection connection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

    // @Bean
    // public ServletRegistrationBean imageServlet() {
    //     ServletRegistrationBean servlet = new ServletRegistrationBean(new ImageServlet(), "/image/servlet/*");
    //     servlet.setLoadOnStartup(1);
    //     return servlet;
    // }


  
}
