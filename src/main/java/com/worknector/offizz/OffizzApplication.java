package com.worknector.offizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OffizzApplication {

    public static void main(String[] args) {
        SpringApplication.run(OffizzApplication.class, args);
    }

}
