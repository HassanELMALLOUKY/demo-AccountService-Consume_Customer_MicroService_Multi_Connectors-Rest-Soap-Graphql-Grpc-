package com.accountservice.web.rest;

import com.accountservice.service.RestClient.CustomerOpenFeignRestClient;
import com.accountservice.model.Customer;
import com.accountservice.service.RestClient.RestTemplateService;
import com.accountservice.service.RestClient.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/account-service")
@AllArgsConstructor
public class AccountRestController {
    private RestTemplateService restTemplateService;
    private WebClientService webClientService;
    private RestTemplate restTemplate;
    private CustomerOpenFeignRestClient customerRestClient;

    //=====================Première Solution: Rest Template =================================
    //Client Bloquant n'est pas réactif
    @GetMapping("/rest_template/customers")
    public List<Customer> restTemplateCustomerList(){
        return restTemplateService.restTemplateCustomerList();
    }
    @GetMapping("/rest_template/customers/{id}")
    public Customer restTemplateCustomerById(@PathVariable Long id){

        return restTemplateService.restTemplateCustomerById(id);
    }

    @PostMapping("/rest_template/customers")
    public Customer restTemplateSaveCustomer(@RequestBody Customer customer){
        return restTemplateService.restTemplateSaveCustomer(customer);
    }

    @PutMapping("/rest_template/customers/{id}")
    public Customer restTemplateUpdateCustomer(@PathVariable Long id,@RequestBody Customer customer){
        return restTemplateService.restTemplateUpdateCustomer(id,customer);
    }
    // reste à fixer une erreur
    @DeleteMapping("/rest_template/customers/{id}")
    public void restTemplateDeleteCustomerById(@PathVariable Long id){
        restTemplateService.restTemplateDeleteCustomerById(id);
    }
//=====================Deuxième Solution: Web Client =================================
    //Client Non Bloquant c'est à dire réactif
    @GetMapping("/web_client/customers")
    public Flux<Customer> webClientcustomerList(){
        return webClientService.webClientcustomerList();
    }


    @GetMapping("/web_client/customers/{id}")
    public Mono<Customer> webClientCustomerById(@PathVariable Long id){
        return webClientService.webClientCustomerById(id);

    }
    // still need some
    @PostMapping("/web_client/customers")
    public Mono<Customer> webClientSaveCustomer(@RequestBody Customer customer){
       return webClientService.webClientSaveCustomer(customer);
    }

    @PutMapping("/web_client/customers/{id}")
    public Mono<Customer> webClientSaveCustomer(@PathVariable Long id,@RequestBody Customer customer){
        return webClientService.webClientUpdateCustomer(id,customer);
    }

    @DeleteMapping("/web_client/customers/{id}")
    public Mono<Customer> webClientDeleteCustomerById(@PathVariable Long id){
        return webClientService.webClientDeleteCustomerById(id);

    }

//===================== Troisième Solution: Open Feign  =================================

    //Client Non Bloquant c'est à dire réactif
    @GetMapping("/open_feign/customers")
    public List<Customer> openFeignCustomerList(){
        return customerRestClient.getCustomers();
    }

    @GetMapping("/open_feign/customers/{id}")
    public Customer openFeignCustomerById(@PathVariable Long id){
        return customerRestClient.getCustomerById(id);
    }

    @PostMapping("/open_feign/customers")
    public Customer openFeignSaveCustomer(@RequestBody Customer customer){
        return customerRestClient.saveCustomer(customer);
    }

    @PutMapping("/open_feign/customers/{id}")
    public Customer openFeignSaveCustomer(@PathVariable Long id,@RequestBody Customer customer){
        Customer customerById = customerRestClient.getCustomerById(id);
        customerById.setName(customer.getName());
        customerById.setEmail(customer.getEmail());
        return customerRestClient.saveCustomer(customerById);
    }

    @DeleteMapping("/open_feign/customers/{id}")
    public void openFeignDeleteCustomerById(@PathVariable Long id){
        customerRestClient.deleteCustomerById(id);
    }
}
