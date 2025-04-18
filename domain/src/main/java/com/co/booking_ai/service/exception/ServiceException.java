package com.co.booking_ai.service.exception;

import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.enums.error.ErrorServiceEnum;

public class ServiceException extends RuntimeException{
    public ServiceException(ErrorServiceEnum errorServiceEnum) {
        super(errorServiceEnum.name());
    }
}
