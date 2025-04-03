package com.co.booking_ai.service.models.provider;

import com.co.booking_ai.service.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
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

    @ApiParam(value = "Day of week")
    private List<DayOfWeek> dayOfWeeks;
    @ApiParam(value = "Holiday excluded")
    private boolean holidayExcluded;
    @ApiParam(value = "Day excluded")
    private List<Date> dayExcluded;

}
