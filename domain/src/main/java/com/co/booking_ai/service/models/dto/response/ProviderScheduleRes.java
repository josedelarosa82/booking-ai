package com.co.booking_ai.service.models.dto.response;

import com.co.booking_ai.service.models.provider.Time;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ProviderScheduleRes implements Serializable {
    private static final long serialVersionUID = 4948470894805432878L;

    private String id;
    private List<Time> times;
}
