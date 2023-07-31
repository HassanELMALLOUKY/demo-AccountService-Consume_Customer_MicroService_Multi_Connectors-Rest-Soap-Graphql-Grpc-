package com.accountservice.service.SoapClient;

import com.customerservice.web.soap.Customer;
import com.customerservice.web.soap.CustomerRequest;
import com.customerservice.web.soap.CustomerSoapWebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountSoapService {
    private CustomerSoapWebService customerSoapWebService;


    public List<Customer> getAllCustomers(){
        return customerSoapWebService.customerList();
    }

    public Customer getCustomerById(Long id){
        return customerSoapWebService.getCustomerById(id);
    }

    public Customer saveCustomer(CustomerRequest customer){
        return customerSoapWebService.saveCustomer(customer);
    }

    public void deleteCustomerById(Long id){
         customerSoapWebService.deleteCustomerById(id);
    }



}
