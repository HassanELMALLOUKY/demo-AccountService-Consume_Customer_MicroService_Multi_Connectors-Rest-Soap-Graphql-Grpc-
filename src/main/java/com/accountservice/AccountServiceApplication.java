package com.accountservice;

import com.customerservice.web.soap.CustomerSoapWebService;
import com.customerservice.web.soap.CustomerSoapWebServiceService;
import net.devh.boot.grpc.client.inject.GrpcClientBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    HttpGraphQlClient graphQlClient(){
        return HttpGraphQlClient.builder()
                .url("http://localhost:8081/graphql")
                .build();
    }

    @Bean
    CustomerSoapWebService soapWebService(){
        return new CustomerSoapWebServiceService().getCustomerSoapWebServicePort();
    }
}
