package com.co.booking_ai.service.models.dto.request;

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
public class ServicioGeoReq implements Serializable {

    private static final long serialVersionUID = -7190588795535117960L;

    private String direccion;
    private String direccionSinAcentos;
    private String direccionrecortada;
    private String barrio;
    private Boolean processed;

}