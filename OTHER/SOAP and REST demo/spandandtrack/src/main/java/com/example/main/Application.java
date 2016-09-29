package com.example.main;

import com.example.config.RestConfig;
import com.example.config.SoapConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author yuriy.dizhak
 */
@SpringBootApplication
@Import(value = {SoapConfig.class, RestConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
