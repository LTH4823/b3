package org.zerock.b3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class B3Application {

    public static void main(String[] args) {
        SpringApplication.run(B3Application.class, args);
    }

}
