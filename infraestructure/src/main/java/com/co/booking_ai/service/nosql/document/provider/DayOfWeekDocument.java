package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.DayEnum;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeekDocument implements Serializable {

    private DayEnum dayOfWeek;
    private Time fromHour;
    private Time toHour;
    private long timeSlot;

}
