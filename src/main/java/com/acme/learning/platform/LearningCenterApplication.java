package com.acme.learning.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //activa la superclass declarada en shared->domain->model->auditModel
@SpringBootApplication
public class LearningCenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(LearningCenterApplication.class, args);
    }

}
