package com.company.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@EnableJpaRepositories(basePackages = {"com.company.app"})
//@EntityScan(basePackages = {"com.company.app"})
//@ComponentScan(basePackages = {"com.company.app"})
//@Configuration
//@ConfigurationPropertiesScan
@Slf4j
@SpringBootApplication
public class StarterConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(StarterConfiguration.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> log.debug("*******************  The app has been started.  *******************");
    }

}
