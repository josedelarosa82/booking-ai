package com.co.booking_ai.service.exception;

import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.enums.error.ErrorServiceEnum;

public class ProviderException extends RuntimeException{
    public ProviderException(ErrorProviderEnum errorProviderEnum) {
        super(errorProviderEnum.name());
    }
}
