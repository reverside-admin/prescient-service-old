package za.co.prescient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;


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
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
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
