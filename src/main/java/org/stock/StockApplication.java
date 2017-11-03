package org.stock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StockApplication {
    
    public static void main(String[] args) {
//        SpringApplication.run(EmpServerApplication.class, args);
        new SpringApplicationBuilder(StockApplication.class)
                .properties("spring.config.name=application,swagger,version")
                .run(args);
    }
}
