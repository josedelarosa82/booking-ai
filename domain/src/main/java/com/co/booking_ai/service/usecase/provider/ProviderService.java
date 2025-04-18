package com.co.booking_ai.service.usecase.provider;


import com.co.booking_ai.service.constants.Constants;
import com.co.booking_ai.service.enums.ProviderStatusEnum;
import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.exception.ProviderException;
import com.co.booking_ai.service.models.dto.request.ProviderReq;
import com.co.booking_ai.service.models.dto.response.ProviderScheduleRes;
import com.co.booking_ai.service.models.provider.*;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class ProviderService implements ProviderServicePort {

    @Autowired
    ProviderImpPort providerImpPort;

    /**
     * Find a provider by ID.
     *
     * @param id The ID of the provider to find.
     * @return A Mono containing the found Provider object, or an error if not found.
     */
    public Mono<Provider> findById(String id) {
        return providerImpPort.findById(id)
                .doOnError(error -> {
                    log.error("Error ProviderService.findById({}) -> {}", id, error.getMessage());

                });
    }

    /**
     * Find a provider's schedule by ID and date.
     *
     * @param id   The ID of the provider.
     * @param date The date for which to find the schedule.
     * @return A Mono containing the ProviderScheduleRes object with the schedule information.
     */
    public Mono<ProviderScheduleRes> findScheduleByIdAndDate(String id, long date) {
        return providerImpPort.findById(id)
                .map(provider -> ProviderScheduleRes.builder()
                        .id(provider.getId())
                        .name(provider.getName())
                        .phone(provider.getPhone())
                        .times(convertScheduleToTimes(provider.getSchedule(), date))
                        .build())
                .doOnError(error -> {
                    log.error("Error ProviderService.findScheduleByIdAndDate({},{}) -> {}", id, date, error.getMessage());

                });
    }

    /**
     * Find a provider by phone number.
     *
     * @param phone The phone number of the provider to find.
     * @return A Mono containing the found Provider object, or an error if not found.
     */
    public Mono<Provider> findByPhone(String phone) {
        return providerImpPort.findByPhone(phone)
                .doOnError(error -> {
                    log.error("Error ProviderService.findByPhone({}) -> {}", phone, error.getMessage());

                });
    }

    /**
     * Find all providers.
     *
     * @return A Flux containing all Provider objects.
     */
    public Flux<Provider> findAll() {
        return providerImpPort.findAll()
                .doOnError(error -> {
                    log.error("Error ProviderService.findAll({}) -> {}", error.getMessage());

                });
    }

    /**
     * Create a new provider with the given data.
     *
     * @param provider The provider data to create.
     * @return A Mono containing the created Provider object.
     */
    public Mono<Provider> createProvider(Provider provider) {
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
                    value.setStatus(value != null && value.getStatus() != null && !value.getStatus().equals("") ? value.getStatus() : ProviderStatusEnum.active);
                    value.setCreateBy(value != null && value.getCreateBy() != null && !value.getCreateBy().equals("") ? value.getCreateBy() : Constants.DEFAULT_USER);
                    return value;
                })
                .flatMap(providerImpPort::save)
                .doOnError(error -> {
                    log.error("Error ProviderService.create({}) -> {}", provider, error.getMessage());

                });
    }

    /**
     * Update a provider with the given ID using the provided data.
     *
     * @param provider The updated provider data.
     * @param id       The ID of the provider to update.
     * @return A Mono containing the updated Provider object.
     */
    public Mono<Provider> updateProvider(ProviderReq provider, String id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new ProviderException(ErrorProviderEnum.PROVIDER_NOT_FOUND)))
                .map(value -> {
                    value.setId(id);
                    value.setName(provider != null && provider.getName() != null && !provider.getName().equals("") ? provider.getName() : value.getName());
                    value.setEmail(provider != null && provider.getEmail() != null && !provider.getEmail().equals("") ? provider.getEmail() : value.getEmail());
                    value.setPhone(provider != null && provider.getPhone() != null && !provider.getPhone().equals("") ? provider.getPhone() : value.getPhone());
                    value.setStatus(provider != null && provider.getStatus() != null && !provider.getStatus().equals("") ? provider.getStatus() : value.getStatus());
                    value.setSchedule(provider != null && provider.getSchedule() != null ? provider.getSchedule() : value.getSchedule());
                    value.setServices(provider != null && provider.getServices() != null ? provider.getServices() : value.getServices());
                    value.setUpdateBy(provider != null && provider.getUpdateBy() != null && !provider.getUpdateBy().equals("") ? provider.getUpdateBy() : (value.getUpdateBy() != null && !value.getUpdateBy().equals("")) ? value.getUpdateBy() : Constants.DEFAULT_USER);
                    value.setUpdateDate(Dates.getCurrentDate());
                    return value;
                })
                .flatMap(providerImpPort::save)
                .doOnError(error -> {
                    log.error("Error ProviderService.update({}) -> {}", provider, error.getMessage());

                });
    }

    /**
     * Find a service by ID within a provider.
     *
     * @param provider  The provider to search within.
     * @param serviceId The ID of the service to find.
     * @return The found Service object, or null if not found.
     */
    public Service getServiceById(Provider provider, String serviceId) {
        return provider.getServices().stream()
                .filter(service -> service.getId().equals(serviceId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Convert the schedule to a list of Time objects based on the given date.
     *
     * @param schedule The Schedule object containing the schedule information.
     * @param date     The date for which to convert the schedule.
     * @return A list of Time objects representing the schedule for the given date.
     */
    private List<Time> convertScheduleToTimes(Schedule schedule, long date) {
        return schedule.getDayOfWeeks().stream()
                .filter(dayOfWeek -> Dates.getDayNameOfWeek(date).equals(dayOfWeek.getDay().name()))
                .flatMap(dayOfWeek -> convertScheduleToDays(dayOfWeek).stream())
                .collect(Collectors.toList());
    }

    /**
     * Convert the schedule to a list of Time objects.
     *
     * @param dayOfWeek The DayOfWeek object containing the schedule information.
     * @return A list of Time objects representing the schedule.
     */
    private List<Time> convertScheduleToDays(DayOfWeek dayOfWeek) {
        return Stream.iterate(dayOfWeek.getFromHour(), time -> time.plusMinutes(dayOfWeek.getTimeSlot()))
                .takeWhile(time -> time.isBefore(dayOfWeek.getToHour()))
                .map(from -> Time.builder()
                        .from(from)
                        .to(from.plusMinutes(dayOfWeek.getTimeSlot()).isAfter(dayOfWeek.getToHour()) ?
                                dayOfWeek.getToHour() : from.plusMinutes(dayOfWeek.getTimeSlot()))
                        .build())
                .collect(Collectors.toList());
    }

}
