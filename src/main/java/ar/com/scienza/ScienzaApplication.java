package ar.com.scienza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EntityScan(basePackages = {"ar.com.scienza"})
@ComponentScan(basePackages = {"ar.com.scienza"})
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
public class ScienzaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ScienzaApplication.class, args);
    }
}
