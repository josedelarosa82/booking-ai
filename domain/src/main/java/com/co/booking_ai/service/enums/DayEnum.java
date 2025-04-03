package com.co.booking_ai.service.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum DayEnum {
    monday(1),
    tuesday(2),
    wednesday(3),
    thursday(4),
    friday(5),
    saturday(6),
    sunday(7);

    private final int id;

    private DayEnum(int id) {
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
