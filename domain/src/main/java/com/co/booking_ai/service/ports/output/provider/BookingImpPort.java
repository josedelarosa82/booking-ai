package com.co.booking_ai.service.ports.output.provider;

import com.co.booking_ai.service.models.provider.Booking;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface BookingImpPort {

    Mono<Booking> findByProviderIdAndDate(String providerId, Date date);

    Mono<Booking> save(Booking booking);

}
