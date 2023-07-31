package com.accountservice.service.GraphqlClient;

import com.accountservice.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class GraphqlClientService {
    private HttpGraphQlClient httpGraphQlClient;

    public List<Customer> getAllCustomers(){
        var queryDoc= """
                query{
                  allCustomers{
                    id,name,email
                  }
                }
                """;
        return httpGraphQlClient.document(queryDoc)
                .retrieve("allCustomers")
                .toEntityList(Customer.class).block();
    }

    public Mono<Customer> getCustomerById(Long id){
        var queryDoc= """
                query($id:Int){
                  getCustomerById(id:$id){
                    name,email
                  }
                }
                """;
        return httpGraphQlClient.document(queryDoc).variable("id",id)
                .retrieve("getCustomerById")
                .toEntity(Customer.class);
    }

    public Customer saveCustomer(Customer customer){
        var queryDoc= """
                mutation($name:String,$email:String){
                  saveCustomer(customer:{
                    name:$name,
                    email:$email
                  }){
                    id,name, email
                  }
                }
                """;
        return httpGraphQlClient.document(queryDoc)
                .variable("name",customer.getName())
                .variable("email",customer.getEmail())
                .retrieve("saveCustomer")
                .toEntity(Customer.class).block();
    }

    public Customer updateCustomer(Long id,Customer customer){
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("id",id);
        vars.put("name",customer.getName());
        vars.put("email",customer.getEmail());
        var queryDoc= """
                mutation($id:Int,$name:String,$email:String){
                  updateCustomer(id:$id,customer:
                  {
                     name:$name,
                    email:$email
                  }
                  ){
                    id,name,email
                  }
                }
                """;
        return httpGraphQlClient.document(queryDoc).variables(vars)
                .retrieve("updateCustomer")
                .toEntity(Customer.class).block();
    }

    public Boolean deleteCustomerById(Long id){
        var queryDoc= """
                mutation($id:Int){
                 deleteCustomerById(id:$id)
                }
                """;
        return httpGraphQlClient.document(queryDoc).variable("id",id)
                .retrieve("deleteCustomerById")
                .toEntity(Boolean.class).block();
    }
}
