package com.co.booking_ai.service.models.dto.request;

import com.co.booking_ai.service.enums.UserStatusEnum;
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
public class UserRequest implements Serializable {

    @ApiParam(value = "User name")
    private String name;
    @ApiParam(value = "User email")
    private String email;
    @ApiParam(value = "User phone")
    private String phone;
    @ApiParam(value = "User status")
    private UserStatusEnum status;
    @ApiParam(value = "Update by")
    private String updateBy;

}
