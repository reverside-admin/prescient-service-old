package za.co.prescient;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource(value = {"classpath:persistence-context.xml", "classpath:security-context.xml"})
@ComponentScan(basePackages = {"za.co.prescient.api"})
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class )
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

}
