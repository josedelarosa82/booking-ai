package com.co.booking_ai.service.nosql.implementation.provider;

import com.co.booking_ai.service.models.provider.Provider;
import com.co.booking_ai.service.nosql.document.provider.ProviderDocument;
import com.co.booking_ai.service.nosql.repository.provider.ProviderRepository;
import com.co.booking_ai.service.ports.output.provider.ProviderImpPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProviderImpl implements ProviderImpPort {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Mono<Provider> findById(String id) {
        return providerRepository.findById(id)
                .map(value -> modelMapper.map(value, Provider.class))
                .doOnNext(value -> log.debug("Find provider by id: {}", value));
    }

    public Flux<Provider> findAll() {
        return providerRepository.findAll()
                .map(value -> modelMapper.map(value, Provider.class))
                .doOnNext(value -> log.debug("Find all providers: {}", value));
    }

    public Mono<Provider> save(Provider provider) {
        return Mono.just(provider)
                .map(value -> modelMapper.map(value, ProviderDocument.class))
                .flatMap(providerRepository::save)
                .map(value -> modelMapper.map(value, Provider.class))
                .doOnNext(value -> log.debug("Creating provider: {}", value));
    }
}
