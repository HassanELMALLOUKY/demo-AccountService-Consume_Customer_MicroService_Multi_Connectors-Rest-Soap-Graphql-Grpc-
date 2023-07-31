package com.accountservice.service.GrpcClient;

import com.accountservice.mapper.AccountServiceMapper;
import com.accountservice.model.Customer;
import com.customerservice.stub.CustomerServiceGrpc;
import com.customerservice.stub.CustomerServiceOuterClass;
import com.customerservice.web.soap.GetCustomerById;
import com.customerservice.web.soap.SaveCustomer;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountGrpcService {
    @Autowired
    private AccountServiceMapper accountMapper;
    @GrpcClient(value = "CustomerGrpcService")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;


    public List<Customer> getAllCustomers(){
        CustomerServiceOuterClass.GetAllCustomersRequest request= CustomerServiceOuterClass.GetAllCustomersRequest
                .newBuilder().build();
        List<CustomerServiceOuterClass.Customer> customers = customerServiceBlockingStub.getAllCustomers(request)
                .getCustomersList();
        return customers.stream().map(accountMapper::fromGrpcCustomer).collect(Collectors.toList());
    }

    public Customer getCustomerById(Long id){
        CustomerServiceOuterClass.GetCustomerByIdRequest idRequest= CustomerServiceOuterClass.GetCustomerByIdRequest
                .newBuilder()
                .setCustomerId(id)
                .build();
        CustomerServiceOuterClass.Customer customer = customerServiceBlockingStub.getCustomerById(idRequest).getCustomer();
        return accountMapper.fromGrpcCustomer(customer);
    }
    //still working here
    public Customer saveCustomer(Customer customer){
        CustomerServiceOuterClass.saveCustomerRequest customerRequest= CustomerServiceOuterClass.saveCustomerRequest
                .newBuilder()
                .setCustomerRequest(accountMapper.fromCustomerToGrpc(customer))
                .build();
        CustomerServiceOuterClass.Customer customer1 = customerServiceBlockingStub.saveCustomer(customerRequest).getCustomer();
        return accountMapper.fromGrpcCustomer(customer1);

    }
    public void deleteCustomerById(Long id){
        CustomerServiceOuterClass.deleteCustomerRequest deleteCustomerRequest= CustomerServiceOuterClass.deleteCustomerRequest
                .newBuilder()
                .setId(id)
                .build();
        customerServiceBlockingStub.deleteCustomer(deleteCustomerRequest);
    }
}
