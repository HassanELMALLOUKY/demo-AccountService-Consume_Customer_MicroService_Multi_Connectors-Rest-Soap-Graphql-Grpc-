package com.accountservice.service.RestClient;

import com.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8081",value = "open-feign-customers")
@Transactional
public interface CustomerOpenFeignRestClient {
    @GetMapping("/customers")
    List<Customer> getCustomers();

    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);

    @PostMapping("/customers")
    Customer saveCustomer(@RequestBody Customer customer);


    @DeleteMapping("/customers/{id}")
    void deleteCustomerById(@PathVariable Long id);
}
