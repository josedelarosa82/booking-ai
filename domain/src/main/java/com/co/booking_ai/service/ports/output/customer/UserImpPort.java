package com.co.booking_ai.service.ports.output.customer;

import com.co.booking_ai.service.models.customer.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserImpPort {

    Mono<User> findById(String id);

    Mono<User> findByPhone(String phone);

    Mono<User> save(User user);

    Flux<User> findAll();

}
