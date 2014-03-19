package za.co.prescient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class)
                .properties("security.basic.enabled=true")
                .run(args);
    }


    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic().and()
                    .authorizeRequests()
                    .antMatchers("/service/**","/views/**")
                    .fullyAuthenticated();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withDefaultSchema()
//                    .usersByUsernameQuery("select user_name, password, TRUE from user_detail where user_name = ?")
                    .usersByUsernameQuery("select ud.user_name, ud.password, TRUE from user_detail ud, user_status us where ud.user_name = ? and ud.user_status_id = us.id and us.id = 2")
                    .authoritiesByUsernameQuery("select ud.user_name, ut.type from user_detail ud, user_type ut where ud.user_type_id = ut.id and ud.user_name = ?");
        }


    }
}
