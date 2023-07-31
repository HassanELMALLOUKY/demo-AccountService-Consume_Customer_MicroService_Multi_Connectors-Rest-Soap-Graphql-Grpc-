package com.accountservice.service.RestClient;

import com.accountservice.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RestTemplateService {
    private RestTemplate restTemplate;

    public List<Customer> restTemplateCustomerList(){
        Customer[] customers = restTemplate.getForObject("http://localhost:8081/customers", Customer[].class);
        assert customers != null;
        return List.of(customers);
    }

    public Customer restTemplateCustomerById(@PathVariable Long id){

        return restTemplate.getForObject("http://localhost:8081/customers/"+id, Customer.class);
    }

    public Customer restTemplateSaveCustomer(Customer customer){
        return restTemplate.postForObject("http://localhost:8081/customers",customer,Customer.class);
    }

    public Customer restTemplateUpdateCustomer(Long id, Customer customer){
        Customer c = restTemplateCustomerById(id);
        assert c != null;
        c.setName(customer.getName());
        c.setEmail(customer.getEmail());

        return restTemplateSaveCustomer(c);

    }


    public void restTemplateDeleteCustomerById(Long id){
        restTemplate.exchange(
                "http://localhost:8081/customers/"+id,
                HttpMethod.DELETE,
                null,
                Void.class
        );;
    }
}
