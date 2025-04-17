package com.co.booking_ai.service.models.provider;

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
public class Time implements Serializable {

    private static final long serialVersionUID = 4048470894805432878L;

    @ApiParam(value = "Hour from")
    private String from;
    @ApiParam(value = "Hour to")
    private String to;
}
