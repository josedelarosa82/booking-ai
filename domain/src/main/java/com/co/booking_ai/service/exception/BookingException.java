package com.co.booking_ai.service.exception;

import com.co.booking_ai.service.enums.error.ErrorBookingEnum;

public class BookingException extends RuntimeException {
    public BookingException(ErrorBookingEnum errorBookingEnum) {
        super(errorBookingEnum.name());
    }
}
