package com.accountservice.service.RestClient;

import com.accountservice.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class WebClientService {
    public Flux<Customer> webClientcustomerList(){
        WebClient webClient= WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        return webClient.get().uri("/customers").retrieve().bodyToFlux(Customer.class);
    }

    public Mono<Customer> webClientCustomerById(Long id){
        WebClient webClient= WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        return webClient.get().uri("/customers/" + id).retrieve().bodyToMono(Customer.class);

    }

    public Mono<Customer> webClientSaveCustomer(Customer customer){
        WebClient webClient= WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        return webClient.post().uri("/customers").body(BodyInserters.fromValue(customer)).retrieve().bodyToMono(Customer.class);
    }

    public Mono<Customer> webClientUpdateCustomer(Long id,Customer customer){
        WebClient webClient= WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        return webClient.put().uri("/customers/"+id).body(BodyInserters.fromValue(customer)).retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<Customer> webClientDeleteCustomerById(Long id){
        WebClient webClient= WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        return webClient.delete().uri("/customers/" + id).retrieve().bodyToMono(Customer.class);

    }
}
