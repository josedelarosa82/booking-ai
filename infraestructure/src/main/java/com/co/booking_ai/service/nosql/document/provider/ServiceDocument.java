package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.ServiceStatusEnum;
import com.co.booking_ai.service.enums.ServiceTypeEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDocument implements Serializable {

    private String id;
    private String name;
    private String description;
    private int amount;
    private BigDecimal price;
    private ServiceTypeEnum type;
    private ServiceStatusEnum status;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
