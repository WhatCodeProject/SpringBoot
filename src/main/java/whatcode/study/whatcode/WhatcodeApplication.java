package whatcode.study.whatcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WhatcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatcodeApplication.class, args);
    }

}
