package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.ProviderStatusEnum;
import com.co.booking_ai.service.models.provider.Service;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "providers")
@CompoundIndexes({
        @CompoundIndex(name = "phone_email_idx", def = "{'phone' : 1, 'email': 1}")
})
public class ProviderDocument implements Serializable {
    @HashIndexed
    @Id
    private String id;
    private String name;
    @Indexed
    private String phone;
    @Indexed
    private String email;
    private ProviderStatusEnum status;
    private ScheduleDocument schedule;
    private List<Service> services;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
