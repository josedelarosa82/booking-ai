package com.co.booking_ai.service.enums.error;

public enum ErrorCategoryEnum {
    // From Request

    INVALID_REQUEST("invalid-request", 400), // 400: Bad Request
    ARGUMENT_MISMATCH("argument-mismatch", 400), // 400: Bad Request
    UNAUTHORIZED("unauthorized", 401), // 401: Unauthorized
    FORBIDDEN("forbidden", 403), // 403: Forbidden
    RESOURCE_NOT_FOUND("resource-not-found", 404),  // 404: Not found
    CONFLICT("conflict", 409), // 409: Conflict
    PRECONDITION_FAILED("precondition-failed", 412), // 412: Precondition failed

    // From Server

    EXTERNAL_ERROR("external-error", 500), // 500: Internal server error
    HOST_NOT_FOUND("host-not-found", 500), // 500: Internal server error
    UNEXPECTED("unexpected", 500), // 500: Internal server error
    NOT_IMPLEMENTED("not-implemented", 501), // 501: Not Implemented
    SERVICE_UNAVAILABLE("service-unavailable", 503), // 503: Service unavailable (Circuit Breaker)
    EXTERNAL_TIMEOUT("external-timeout", 503); // 503: Service unavailable

    private static final String PROPERTY_PREFIX = "application.salers.error-code.";

    private String property;
    private int httpStatus;

    ErrorCategoryEnum(String property, int httpStatus) {
        this.property = PROPERTY_PREFIX.concat(property);
        this.httpStatus = httpStatus;
    }

    private String codeProperty() {
        return new StringBuilder(this.property).append(".code").toString();
    }

    private String descriptionProperty() {
        return new StringBuilder(this.property).append(".description").toString();
    }

    private String errorTypeProperty() {
        return new StringBuilder(this.property).append(".error-type").toString();
    }

    private String defaultCode() {
        return "TL9999";
    }

    private String defaultDescription() {
        return "Sin descripcion configurada.";
    }


}
