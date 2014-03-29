package za.co.prescient;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:security.xml")
@EnableJpaRepositories
public class Application extends WebMvcConfigurerAdapter {


    @Value("${spring.datasource.driverClassName}")
    private String databaseDriver;
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.userName}")
    private String databaseUserName;
    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        DataSource driverManagerDataSource = new DataSource();
        driverManagerDataSource.setDriverClassName(databaseDriver);
        driverManagerDataSource.setUrl(databaseUrl);
        driverManagerDataSource.setUsername(databaseUserName);
        driverManagerDataSource.setPassword(databasePassword);
        return  driverManagerDataSource;
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("security.basic.enabled=true")
                .run(args);
    }
}
