package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.DayEnum;
import lombok.*;

import java.io.Serializable;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeekDocument implements Serializable {

    private DayEnum dayOfWeek;
    private int fromHour;
    private int toHour;
    private int timeSlot;

}
