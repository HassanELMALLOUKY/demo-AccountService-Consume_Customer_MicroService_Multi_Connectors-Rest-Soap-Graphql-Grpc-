package com.accountservice.web.graphql;

import com.accountservice.model.Customer;
import com.accountservice.service.GraphqlClient.GraphqlClientService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account-service/graphql")
public class AccountGraphqlController {
    private GraphqlClientService graphqlClientService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return graphqlClientService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Mono<Customer> getCustomerById(@PathVariable Long id) {
        return graphqlClientService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return graphqlClientService.saveCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
        return graphqlClientService.updateCustomer(id,customer);
    }

    @DeleteMapping("/customers/{id}")
    public boolean deleteCustomerById(@PathVariable Long id) {
        return graphqlClientService.deleteCustomerById(id);
    }
}
