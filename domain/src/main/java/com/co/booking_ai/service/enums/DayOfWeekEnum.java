package com.co.booking_ai.service.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum DayOfWeekEnum {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int id;

    private DayOfWeekEnum(int id) {
        this.id = id;
    }

    public static String nameFromId(int id) {
        return (String) Arrays.stream(values()).filter((dse) -> {
            return dse.getId()==id;
        }).map(Enum::name).findFirst().orElseThrow(() -> {
            return new RuntimeException("OBJECT_SERVICE_TYPE_NOT_VALID");
        });
    }


    public static int idFromName(String name) {
        return (int) Arrays.stream(values()).filter((dse) -> {
            return dse.name().contentEquals(name);
        }).map(value -> value.getId()).findFirst().orElseThrow(() -> {
            return new RuntimeException("OBJECT_SERVICE_TYPE_NOT_VALID");
        });
    }

    public int getId() {
        return this.id;
    }
}
