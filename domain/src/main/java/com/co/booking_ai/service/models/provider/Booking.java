package com.co.booking_ai.service.models.provider;

import com.co.booking_ai.service.enums.BookingStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Booking implements Serializable {

    private static final long serialVersionUID = 4048470894805432878L;

    @ApiParam(value = "Booking id")
    private String id;
    @ApiParam(value = "Provider Id")
    private String providerId;
    @ApiParam(value = "Service Id")
    private String serviceId;
    @ApiParam(value = "Booking status")
    private BookingStatusEnum status;
    @ApiParam(value = "Booking date")
    private Date date;
    @ApiParam(value = "Create date")
    private Date createDate;
    @ApiParam(value = "Create by")
    private String createBy;
    @ApiParam(value = "Update date")
    private Date updateDate;
    @ApiParam(value = "Update by")
    private String updateBy;
}
