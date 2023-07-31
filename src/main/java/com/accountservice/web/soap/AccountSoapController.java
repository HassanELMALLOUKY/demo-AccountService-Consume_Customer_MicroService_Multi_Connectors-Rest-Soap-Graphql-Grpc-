package com.accountservice.web.soap;

import com.accountservice.service.SoapClient.AccountSoapService;
import com.customerservice.web.soap.Customer;
import com.customerservice.web.soap.CustomerRequest;
import com.customerservice.web.soap.CustomerSoapWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account-service/soap")
public class AccountSoapController {
    private AccountSoapService accountSoapService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return accountSoapService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return accountSoapService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer getCustomerById(@RequestBody CustomerRequest customerRequest){
        return accountSoapService.saveCustomer(customerRequest);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(@PathVariable Long id){
         accountSoapService.deleteCustomerById(id);
    }

}
