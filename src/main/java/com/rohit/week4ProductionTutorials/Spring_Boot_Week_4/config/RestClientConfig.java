package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

//    @Value("${employee.service.base.url}")
//    private String BASE_URL;

    @Bean
    @Qualifier("employeeClient")
    RestClient getEmployeeServiceRestClient(@Value("${employeeService.base.url}") String BASE_URL) {
        System.out.println("Base URL from properties = " + BASE_URL); // debug
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError, (req, res) -> {
                            throw new RuntimeException("Server error occurred");
                        }
                )
                .build();
    }
}
