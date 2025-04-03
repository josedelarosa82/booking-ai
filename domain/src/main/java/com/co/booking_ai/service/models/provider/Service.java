package com.co.booking_ai.service.models.provider;

import com.co.booking_ai.service.enums.ServiceStatusEnum;
import com.co.booking_ai.service.enums.ServiceTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Service implements Serializable {

    private static final long serialVersionUID = 4048470894805432878L;

    @ApiParam(value = "Service id")
    private String id;
    @ApiParam(value = "Service name")
    private String name;
    @ApiParam(value = "Service description")
    private String description;
    @ApiParam(value = "Service amount")
    private int amount;
    @ApiParam(value = "Service price")
    private BigDecimal price;
    @ApiParam(value = "Service type")
    private ServiceTypeEnum type;
    @ApiParam(value = "Service status")
    private ServiceStatusEnum status;
    @ApiParam(value = "Create date")
    private Date createDate;
    @ApiParam(value = "Create by")
    private String createBy;
    @ApiParam(value = "Update date")
    private Date updateDate;
    @ApiParam(value = "Update by")
    private String updateBy;
}
