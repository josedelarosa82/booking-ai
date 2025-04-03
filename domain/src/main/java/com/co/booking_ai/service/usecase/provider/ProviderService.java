package com.co.booking_ai.service.usecase.provider;


import com.co.booking_ai.service.constants.Constants;
import com.co.booking_ai.service.enums.ProviderStatusEnum;
import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.exception.ProviderException;
import com.co.booking_ai.service.models.dto.request.ProviderRequest;
import com.co.booking_ai.service.models.provider.Provider;
import com.co.booking_ai.service.ports.input.provider.ProviderServicePort;
import com.co.booking_ai.service.ports.output.provider.ProviderImpPort;
import com.co.booking_ai.service.utils.Dates;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class ProviderService implements ProviderServicePort {

    @Autowired
    ProviderImpPort providerImpPort;

    public Mono<Provider> findById(String id) {
        return providerImpPort.findById(id)
                .doOnError(error -> {
                    log.error("Error ProviderService.findById({}) -> {}", id, error.getMessage());

                });
    }

    public Flux<Provider> findAll() {
        return providerImpPort.findAll()
                .doOnError(error -> {
                    log.error("Error ProviderService.findAll({}) -> {}", error.getMessage());

                });
    }

    public Mono<Provider> create(Provider provider) {
        return Mono.just(provider)
                .switchIfEmpty(Mono.error(new ProviderException(ErrorProviderEnum.PROVIDER_NOT_FOUND)))
                .map(value -> {
                    value.setCreateDate(Dates.getCurrentDate());
                   value.setServices(value.getServices().stream().map(
                       service -> {
                           service.setId(UUID.randomUUID().toString());
                           service.setCreateDate(Dates.getCurrentDate());
                           service.setCreateBy(value != null && value.getCreateBy() != null && !value.getCreateBy().equals("") ? value.getCreateBy() : Constants.DEFAULT_USER);
                           return service;
                       }).collect(Collectors.toList()));
                    value.setStatus(value != null && value.getStatus()!=null && !value.getStatus().equals("") ? value.getStatus() : ProviderStatusEnum.active);
                    value.setCreateBy(value != null && value.getCreateBy()!=null && !value.getCreateBy().equals("") ? value.getCreateBy() : Constants.DEFAULT_USER);
                    return value;
                })
                .flatMap(providerImpPort::save)
                .doOnError(error -> {
                    log.error("Error ProviderService.create({}) -> {}", provider, error.getMessage());

                });
    }

    public Mono<Provider> update(ProviderRequest provider, String id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new ProviderException(ErrorProviderEnum.PROVIDER_NOT_FOUND)))
                .map(value -> {
                    value.setId(id);
                    value.setName(provider != null && provider.getName()!=null && !provider.getName().equals("") ? provider.getName() : value.getName());
                    value.setEmail(provider != null && provider.getEmail()!=null && !provider.getEmail().equals("") ? provider.getEmail() : value.getEmail());
                    value.setPhone(provider != null && provider.getPhone()!=null && !provider.getPhone().equals("") ? provider.getPhone() : value.getPhone());
                    value.setStatus(provider != null && provider.getStatus()!=null && !provider.getStatus().equals("") ? provider.getStatus() : value.getStatus());
                    value.setSchedule(provider != null && provider.getSchedule()!=null ? provider.getSchedule() : value.getSchedule());
                    value.setServices(provider != null && provider.getServices()!=null ? provider.getServices() : value.getServices());
                    value.setUpdateBy(provider != null && provider.getUpdateBy()!=null && !provider.getUpdateBy().equals("") ? provider.getUpdateBy() : (value.getUpdateBy() !=null && !value.getUpdateBy().equals("")) ? value.getUpdateBy() : Constants.DEFAULT_USER);
                    value.setUpdateDate(Dates.getCurrentDate());
                    return value;
                })
                .flatMap(providerImpPort::save)
                .doOnError(error -> {
                    log.error("Error ProviderService.update({}) -> {}", provider, error.getMessage());

                });
    }

}
