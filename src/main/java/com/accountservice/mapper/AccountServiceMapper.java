package com.accountservice.mapper;

import com.accountservice.model.Customer;
import com.customerservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceMapper {
    private final ModelMapper mapper=new ModelMapper();

   public Customer fromGrpcCustomer(CustomerServiceOuterClass.Customer customerGrpc){
       return mapper.map(customerGrpc, Customer.class);
   }

    public CustomerServiceOuterClass.CustomerRequest fromCustomerToGrpc(Customer customer){
        return mapper.map(customer, CustomerServiceOuterClass.CustomerRequest.class);
    }
}
