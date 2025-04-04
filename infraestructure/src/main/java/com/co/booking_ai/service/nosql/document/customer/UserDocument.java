package com.co.booking_ai.service.nosql.document.customer;

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
@Document(collection = "users")
public class UserDocument implements Serializable {
    @HashIndexed
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String phone;
    @Indexed(unique = true)
    private String email;
    private String status;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
