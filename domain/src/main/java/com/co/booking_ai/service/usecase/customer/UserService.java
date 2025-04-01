package com.co.booking_ai.service.usecase.customer;


import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.ports.input.UserServicePort;
import com.co.booking_ai.service.ports.output.UserImpPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class UserService implements UserServicePort {

    @Autowired
    UserImpPort userImpPort;

//    public Mono<Servicio> crearServicioUsuario(ServicioUsuarioDTO servicioUsuarioDTOReq) {
//        return Mono.just(Servicio.builder().build());
//        /*return ValidarDatosServicioUsuario.validarDatos(servicioUsuarioReq)
//                .doOnNext(valor -> log.info("===== datos de entrada principal {}",valor))
//                .flatMap(createUser -> Mono.just(createUser)
//                        .doOnNext(valor -> log.info("===== datos de entrada {}",valor))
//                        .filter(validateServiceOrigen -> validateServiceOrigen.getOrigen().equals(Constants.SERVICIO_ORIGEN_CHATBOT))
//                        .flatMap(dataUser -> ObjectsFactoryCiudad.obtenerCiudadDentroCobertura(serviciosCiudadServicePort.findAll(),servicioUsuarioReq.getLatitud(),servicioUsuarioReq.getLongitud()))
//                        .doOnNext(valor -> log.info("==== obtener distancia ciudad  {}",valor))
//                        .map(valorCiudad -> ServicioUsuario.builder()
//                                .latitud(createUser.getLatitud())
//                                .longitud(createUser.getLongitud())
//                                .idCiudad(createUser.getIdCiudad())
//                                .medioPago(createUser.getMedioPago())
//                                .tipoServicio(createUser.getTipoServicio())
//                                .build()))
//                .doOnNext(valor -> log.info("==== ciudad obtenida {}",valor))
//                .flatMap(user -> serviciosGeoService.completarDireccionPrincipal(user.getLatitud(),user.getLongitud(), user.getIdCiudad())
//                        .doOnNext(valor -> log.info("==== adapter de geo {}",valor))
//                        .map(dataGeo -> user.toBuilder()
//                                .direccionPrincipal(dataGeo.getDireccion().replaceAll("\\(.*?\\) ?", ""))
//                                .barrioOrigen(dataGeo.getBarrio())
//                                .build())
//                        // linea 2889 dell proyecto centrales, logica de validaciones en la clase ValidarDatosServicioUsuario
//                        .map(filterPaymentMethod -> Objects.nonNull(filterPaymentMethod.getMedioPago())
//                                &&  filterPaymentMethod.getMedioPago().equals(Constants.MEDIO_PAGO_TARJETA) ? user : user
//                        )
//
//                )
//                .switchIfEmpty(Mono.defer(() -> Mono.just(servicioUsuarioReq)
//                        .map(valorServicio -> ServicioUsuario.builder()
//                                .latitud(valorServicio.getLatitud())
//                                .longitud(valorServicio.getLongitud())
//                                .idCiudad(valorServicio.getIdCiudad())
//                                .medioPago(valorServicio.getMedioPago())
//                                .tipoServicio(valorServicio.getTipoServicio())
//                                .build())
//                ))
//                .doOnError(error -> log.error(error.getMessage()));*/
//    }

    public Mono<User> findUserById(String id) {
        return userImpPort.findById(id)
                .doOnError(error -> {
                    log.error("Error UserService.findServicesById({}) -> {}", id, error.getMessage());

                });
    }

    public Mono<User> save(User user) {
        return userImpPort.save(user)
                .doOnError(error -> {
                    log.error("Error UserService.save({}) -> {}", user, error.getMessage());

                });
    }

}
