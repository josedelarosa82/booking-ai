package com.co.booking_ai.service.ports.input.provider;

import com.co.booking_ai.service.models.provider.Booking;
import reactor.core.publisher.Mono;

public interface BookingServicePort {

    /**
     * Book an appointment with a provider.
     *
     * @param booking The appointment details to book.
     * @return A Mono containing the Booking object with booking information.
     */
    Mono<Booking> createBookingProvider(Booking booking);

    /**
     * Update an existing booking for a provider.
     *
     * @param booking The updated appointment details.
     * @param id      The ID of the booking to update.
     * @return A Mono containing the updated Booking object.
     */
    Mono<Booking> updateBookingProvider(Booking booking, String id);


}
