package com.testbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.testbusiness.exceptions.NotFoundException;

@SpringBootApplication
public class TestbusinessApiApplication {

    @Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(TestbusinessApiApplication.class, args);
	}

}
