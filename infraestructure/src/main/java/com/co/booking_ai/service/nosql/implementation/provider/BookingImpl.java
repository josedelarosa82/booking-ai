package com.co.booking_ai.service.nosql.implementation.provider;

import com.co.booking_ai.service.models.provider.Booking;
import com.co.booking_ai.service.nosql.document.provider.BookingDocument;
import com.co.booking_ai.service.nosql.repository.provider.BookingRepository;
import com.co.booking_ai.service.ports.output.provider.BookingImpPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Slf4j
public class BookingImpl implements BookingImpPort {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Mono<Booking> findById(String id) {
        return bookingRepository.findById(id)
                .map(value -> modelMapper.map(value, Booking.class))
                .doOnNext(value -> log.debug("Find booking provider by id: {}", value));
    }

    public Mono<Booking> findByProviderIdAndDate(String providerId, Date date) {
        return bookingRepository.findByProviderIdAndDate(providerId, date)
                .map(value -> modelMapper.map(value, Booking.class))
                .doOnNext(value -> log.debug("Find booking provider by providerId: {} and date: {}", providerId, date, value));
    }

    public Flux<Booking> findAll() {
        return bookingRepository.findAll()
                .map(value -> modelMapper.map(value, Booking.class))
                .doOnNext(value -> log.debug("Find all booking providers: {}", value));
    }

    public Mono<Booking> save(Booking booking) {
        return Mono.just(booking)
                .map(value -> modelMapper.map(value, BookingDocument.class))
                .flatMap(bookingRepository::save)
                .map(value -> modelMapper.map(value, Booking.class))
                .doOnNext(value -> log.debug("Creating booking provider: {}", value));
    }

}
