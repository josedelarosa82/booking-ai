package com.co.booking_ai.service.ports.input.provider;

import com.co.booking_ai.service.models.dto.request.ProviderReq;
import com.co.booking_ai.service.models.dto.response.ProviderScheduleRes;
import com.co.booking_ai.service.models.provider.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderServicePort {

    /**
     * Find a provider by ID.
     *
     * @param id The ID of the provider to find.
     * @return A Mono containing the found Provider object, or an error if not found.
     */
    Mono<Provider> findById(String id);

    /**
     * Find a provider's schedule by ID and date.
     *
     * @param id   The ID of the provider.
     * @param date The date for which to find the schedule.
     * @return A Mono containing the ProviderScheduleRes object with the schedule information.
     */
    Mono<ProviderScheduleRes> findScheduleByIdAndDate(String id, long date);

    /**
     * Find a provider by phone number.
     *
     * @param phone The phone number of the provider to find.
     * @return A Mono containing the found Provider object, or an error if not found.
     */
    Mono<Provider> findByPhone(String phone);

    /**
     * Create a new provider.
     *
     * @param provider The Provider object to create.
     * @return A Mono containing the created Provider object.
     */
    Mono<Provider> createProvider(Provider provider);

    /**
     * Update an existing provider.
     *
     * @param provider The Provider object with updated data.
     * @param id       The ID of the provider to update.
     * @return A Mono containing the updated Provider object.
     */
    Mono<Provider> updateProvider(ProviderReq provider, String id);

    /**
     * Find all providers.
     *
     * @return A Flux containing all Provider objects.
     */
    Flux<Provider> findAll();
}
