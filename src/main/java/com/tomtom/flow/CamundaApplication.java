package com.tomtom.flow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableProcessApplication
@EnableFeignClients
public class CamundaApplication {
    public static void main(final String... args) {
        SpringApplication.run(CamundaApplication.class, args);
    }
/*
    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    private void processPostDeploy(final PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("Demo");
    }*/
}
