package com.co.booking_ai.service.enums.error;

public enum ErrorUserEnum {
    OBJECT_STATUS_NOT_VALID,
    IDENTITY_ID_ALREADY_EXISTS,
    EMAIL_ALREADY_EXISTS,
    PHONE_BLOCK,
    ERROR_AL_ENVIAR_LA_TAREA,
    JSON_NO_CUMPLE_CON_LAS_VALIDACIONES_MINIMAS,
    CITY_NOT_FOUND,
    ABANDONED_SERVICE,
    USER_NOT_FOUND;

    public String getMessage() {
        return this.name();
    }
}
