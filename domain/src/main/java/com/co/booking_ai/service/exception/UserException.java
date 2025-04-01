package com.co.booking_ai.service.exception;

import com.co.booking_ai.service.enums.error.ErrorUserEnum;

public class UserException extends RuntimeException{
    public UserException(ErrorUserEnum errorUserEnum) {
        super(errorUserEnum.name());
    }
}
