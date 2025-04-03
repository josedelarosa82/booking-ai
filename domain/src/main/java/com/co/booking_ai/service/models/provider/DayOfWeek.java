package com.co.booking_ai.service.models.provider;

import com.co.booking_ai.service.enums.DayEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class DayOfWeek implements Serializable {
    private static final long serialVersionUID = 0x3992544c9af2d4aeL;

    @ApiParam(value = "Day of week")
    private DayEnum day;
    @ApiParam(value = "From hour")
    private int fromHour;
    @ApiParam(value = "To hour")
    private int toHour;
    @ApiParam(value = "Time slot")
    private int timeSlot;

}
