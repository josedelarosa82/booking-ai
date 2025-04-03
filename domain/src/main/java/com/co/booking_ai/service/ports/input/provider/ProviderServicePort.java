package com.co.booking_ai.service.ports.input.provider;

import com.co.booking_ai.service.models.dto.request.ProviderRequest;
import com.co.booking_ai.service.models.provider.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderServicePort {


    Mono<Provider> findById(String id);

    Mono<Provider> create(Provider provider);

    Mono<Provider> update(ProviderRequest provider, String id);

    Flux<Provider> findAll();
}
