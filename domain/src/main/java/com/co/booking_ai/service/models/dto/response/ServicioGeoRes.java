package com.co.booking_ai.service.models.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ServicioGeoRes implements Serializable {

    private static final long serialVersionUID = 4048470894805432878L;

    private String direccion;
    private String direccionSinAcentos;
    private String direccionrecortada;
    private String barrio;
    private Boolean processed;

}