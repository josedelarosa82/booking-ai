package com.co.booking_ai.service.models.provider;

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
public class Provider implements Serializable {

    private static final long serialVersionUID = 4148470894805432878L;

    @ApiParam(value = "Provider Id")
    private String id;
    @ApiParam(value = "Provider name")
    private String name;
    @ApiParam(value = "Provider email")
    private String email;
    @ApiParam(value = "Provider phone")
    private String phone;
    @ApiParam(value = "Provider status")
    private StatusEnum status;
    @ApiParam(value = "Provider schedule")
    private Schedule schedule;
    @ApiParam(value = "Provider service")
    private List<Service> services;
    @ApiParam(value = "Create by")
    private String createBy;
    @ApiParam(value = "Update by")
    private String updateBy;

}
