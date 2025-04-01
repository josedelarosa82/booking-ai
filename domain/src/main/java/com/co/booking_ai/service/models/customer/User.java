package com.co.booking_ai.service.models.customer;

import com.co.booking_ai.service.enums.StatusEnum;
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
public class User implements Serializable {

    @ApiParam(value = "User Id")
    private String id;
    @ApiParam(value = "User name")
    private String name;
    @ApiParam(value = "User email")
    private String email;
    @ApiParam(value = "User phone")
    private String phone;
    @ApiParam(value = "User status")
    private StatusEnum status;
    @ApiParam(value = "Create by")
    private String createBy;
    @ApiParam(value = "Update by")
    private String updateBy;

}
