package com.co.booking_ai.service.nosql.document.provider;

import com.co.booking_ai.service.enums.DayOfWeekEnum;
import com.co.booking_ai.service.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schedules")
public class ScheduleDocument implements Serializable {
    @HashIndexed
    @Id
    private String id;
    private String fromHour;
    private String toHour;
    private String timeSlot;
    private List<DayOfWeekEnum> dayOfWeek;
    private List<String> hourExcluded;
    private boolean holidayExcluded;
    private StatusEnum status;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
