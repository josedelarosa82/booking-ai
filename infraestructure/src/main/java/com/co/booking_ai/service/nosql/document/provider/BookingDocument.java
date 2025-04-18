package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.BookingStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
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
@Document(collection = "booking_providers")
@CompoundIndexes({
        @CompoundIndex(name = "provider_service_idx", def = "{'providerId' : 1, 'serviceId': 1}")
})
public class BookingDocument implements Serializable {
    @HashIndexed
    @Id
    private String id;
    @Indexed
    private String providerId;
    @Indexed
    private String serviceId;
    private BookingStatusEnum status;
    private Date date;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
