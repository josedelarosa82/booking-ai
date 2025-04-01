package com.co.booking_ai.service.models.provider;

import com.co.booking_ai.service.enums.DayOfWeekEnum;
import com.co.booking_ai.service.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Schedule implements Serializable {

    private static final long serialVersionUID = 0x3992544c9df2d4aeL;

    @ApiParam(value = "Shedule Id")
    private String id;
    @ApiParam(value = "From hour")
    private String fromHour;
    @ApiParam(value = "To hour")
    private String toHour;
    @ApiParam(value = "Time slot")
    private String timeSlot;
    @ApiParam(value = "Day of week")
    private List<DayOfWeekEnum> dayOfWeek;
    @ApiParam(value = "Hour excluded")
    private List<String> hourExcluded;
    @ApiParam(value = "Holiday excluded")
    private boolean holidayExcluded;
    @ApiParam(value = "Shedule status")
    private StatusEnum status;
    @ApiParam(value = "Create by")
    private String createBy;
    @ApiParam(value = "Update by")
    private String updateBy;

}
