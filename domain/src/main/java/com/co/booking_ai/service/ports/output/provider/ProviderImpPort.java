package com.co.booking_ai.service.ports.output.provider;

import com.co.booking_ai.service.models.provider.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderImpPort {

    Mono<Provider> findById(String id);

    Mono<Provider> findByPhone(String phone);

    Mono<Provider> save(Provider provider);

    Flux<Provider> findAll();

}
