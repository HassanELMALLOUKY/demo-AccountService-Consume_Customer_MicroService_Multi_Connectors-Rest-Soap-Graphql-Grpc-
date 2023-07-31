package com.accountservice.web.grpc;

import com.accountservice.model.Customer;
import com.accountservice.service.GrpcClient.AccountGrpcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account-service/grpc")
public class AccountGrpcController {
    private AccountGrpcService accountGrpcService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return accountGrpcService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return accountGrpcService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return accountGrpcService.saveCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        accountGrpcService.deleteCustomerById(id);
    }
}
