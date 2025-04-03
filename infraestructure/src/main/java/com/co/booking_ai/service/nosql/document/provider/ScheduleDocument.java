package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.models.provider.DayOfWeek;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDocument implements Serializable {

    private List<DayOfWeek> dayOfWeeks;
    private boolean holidayExcluded;
    private List<Date> dayExcluded;

}
