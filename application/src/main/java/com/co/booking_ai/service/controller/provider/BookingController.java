package com.co.booking_ai.service.controller.provider;

import com.co.booking_ai.service.models.provider.Booking;
import com.co.booking_ai.service.models.provider.Provider;
import com.co.booking_ai.service.ports.input.provider.BookingServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RefreshScope
@RestController
@RequestMapping("${application.booking_ai.api.path}/providers/booking")
@Api(tags = "provider", description = "provider services API")
@Slf4j
public class BookingController {


    @Autowired
    private BookingServicePort bookingServicePort;


    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Booking new provider appointment", response = Provider.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Creation was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<Booking> createBookingProvider(@RequestBody Booking booking) {
        return bookingServicePort.createBookingProvider(booking)
                .doOnNext(value -> log.debug("Provider was created -> {} ", booking))
                .doOnError(error -> log.error("Error booking appointment provider:{} - {}", booking, error.getMessage()));
    }

}
