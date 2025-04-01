package com.co.booking_ai.service.exception;

import com.co.booking_ai.service.enums.error.ErrorUserEnum;

public class ProviderException extends RuntimeException{
    public ProviderException(ErrorUserEnum errorUserEnum) {
        super(errorUserEnum.name());
    }
}
