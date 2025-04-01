package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.ServiceTypeEnum;
import com.co.booking_ai.service.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "services")
public class ServiceDocument implements Serializable {
    @HashIndexed
    @Id
    private String id;
    private String name;
    private String description;
    private int amount;
    private String price;
    private ServiceTypeEnum type;
    @Indexed()
    private String providerId;
    private StatusEnum status;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
