package com.co.booking_ai.service.ports.output;

import com.co.booking_ai.service.models.customer.User;
import reactor.core.publisher.Mono;

public interface UserImpPort {

    Mono<User> findById(String id);

    Mono<User> save(User user);

}
