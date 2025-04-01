package com.co.booking_ai.service.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum StatusEnum {
    ACTIVE("active"),
    INACTIVE("inactive"),
    BOOKED("booked"),
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected"),
    BLOCKED("blocked"),
    DISABLED("disabled");

    private final String id;

    private StatusEnum(String id) {
        this.id = id;
    }

    public static String nameFromId(String id) {
        return (String) Arrays.stream(values()).filter((dse) -> {
            return dse.getId().contentEquals(id);
        }).map(Enum::name).findFirst().orElseThrow(() -> {
            return new RuntimeException("OBJECT_STATUS_NOT_VALID");
        });
    }


    public static String idFromName(String name) {
        return (String) Arrays.stream(values()).filter((dse) -> {
            return dse.name().contentEquals(name);
        }).map(value -> value.getId()).findFirst().orElseThrow(() -> {
            return new RuntimeException("OBJECT_STATUS_NOT_VALID");
        });
    }

    public String getId() {
        return this.id;
    }
}
