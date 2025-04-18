package com.co.booking_ai.service.usecase.provider;


import com.co.booking_ai.service.enums.BookingStatusEnum;
import com.co.booking_ai.service.enums.error.ErrorBookingEnum;
import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.enums.error.ErrorServiceEnum;
import com.co.booking_ai.service.exception.BookingException;
import com.co.booking_ai.service.exception.ProviderException;
import com.co.booking_ai.service.exception.ServiceException;
import com.co.booking_ai.service.models.provider.Booking;
import com.co.booking_ai.service.ports.input.provider.BookingServicePort;
import com.co.booking_ai.service.ports.output.provider.BookingImpPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class BookingService implements BookingServicePort {

    @Autowired
    BookingImpPort bookingImpPort;

    @Autowired
    ProviderService providerService;

    /**
     * Create a new booking for a provider.
     *
     * @param booking The appointment details to create.
     * @return A Mono containing the created Booking object.
     */
    public Mono<Booking> createBookingProvider(Booking booking) {
        return Mono.just(booking)
                .filter(provider -> isValidBooking(booking).block())
                .switchIfEmpty(Mono.error(new BookingException(ErrorBookingEnum.BOOKING_ALREADY_EXISTS)))
                .flatMap(value -> providerService.findById(booking.getProviderId()))
                .switchIfEmpty(Mono.error(new ProviderException(ErrorProviderEnum.PROVIDER_NOT_FOUND)))
                .flatMap(value -> {
                    if (value.getServices() == null || value.getServices().isEmpty()) {
                        return Mono.error(new ProviderException(ErrorProviderEnum.PROVIDER_NOT_FOUND));
                    }
                    if (booking.getServiceId() == null || providerService.getServiceById(value, booking.getServiceId()) == null) {
                        return Mono.error(new ServiceException(ErrorServiceEnum.SERVICE_NOT_FOUND));
                    }
                    if (booking.getDate().toString() == null || booking.getDate().toString().equals("")) {
                        return Mono.error(new ServiceException(ErrorServiceEnum.INVALID_DATE));
                    }
                    return Mono.just(Booking.builder()
                            .providerId(booking.getProviderId())
                            .serviceId(booking.getServiceId())
                            .date(booking.getDate())
                            .status(booking.getStatus() != null || !booking.getStatus().equals("") ? booking.getStatus() : BookingStatusEnum.pending)
                            .build());
                })
                .flatMap(bookingImpPort::save)
                .doOnError(error -> {
                    log.error("Error ProviderService.createBookingProvider({}) -> {}", booking, error.getMessage());
                });
    }

    private Mono<Boolean> isValidBooking(Booking booking) {
        return bookingImpPort.findByProviderIdAndDate(booking.getProviderId(), booking.getDate())
                .flatMap(existingBooking -> {
                    if (existingBooking != null)
                        return Mono.just(false);
                    return Mono.just(true);
                });
    }


    /**
     * Update an existing booking for a provider.
     *
     * @param booking The updated appointment details.
     * @param id      The ID of the booking to update.
     * @return A Mono containing the updated Booking object.
     */
    public Mono<Booking> updateBookingProvider(Booking booking, String id) {
        return null;
    }
}
