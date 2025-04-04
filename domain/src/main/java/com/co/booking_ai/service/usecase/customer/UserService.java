package com.co.booking_ai.service.usecase.customer;


import com.co.booking_ai.service.constants.Constants;
import com.co.booking_ai.service.enums.UserStatusEnum;
import com.co.booking_ai.service.enums.error.ErrorUserEnum;
import com.co.booking_ai.service.exception.UserException;
import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.models.dto.request.UserRequest;
import com.co.booking_ai.service.ports.input.customer.UserServicePort;
import com.co.booking_ai.service.ports.output.customer.UserImpPort;
import com.co.booking_ai.service.utils.Dates;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
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

    public Mono<User> findById(String id) {
        return userImpPort.findById(id)
                .doOnError(error -> {
                    log.error("Error UserService.findById({}) -> {}", id, error.getMessage());

                });
    }

    public Mono<User> findByPhone(String phone) {
        return userImpPort.findByPhone(phone)
                .doOnError(error -> {
                    log.error("Error UserService.findByPhone({}) -> {}", phone, error.getMessage());

                });
    }

    public Flux<User> findAll() {
        return userImpPort.findAll()
                .doOnError(error -> {
                    log.error("Error UserService.findAll({}) -> {}", error.getMessage());

                });
    }

    public Mono<User> create(User user) {
        return Mono.just(user)
                .switchIfEmpty(Mono.error(new UserException(ErrorUserEnum.USER_NOT_FOUND)))
                .map(value -> {
                    value.setCreateDate(Dates.getCurrentDate());
                    value.setStatus(value != null && value.getStatus()!=null && !value.getStatus().equals("") ? value.getStatus() : UserStatusEnum.active);
                    value.setCreateBy(value != null && value.getCreateBy()!=null && !value.getCreateBy().equals("") ? value.getCreateBy() : Constants.DEFAULT_USER);
                    return value;
                })
                .flatMap(userImpPort::save)
                .doOnError(error -> {
                    log.error("Error UserService.create({}) -> {}", user, error.getMessage());

                });
    }

    public Mono<User> update(UserRequest user, String id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new UserException(ErrorUserEnum.USER_NOT_FOUND)))
                .map(value -> {
                    value.setId(id);
                    value.setName(user != null && user.getName()!=null && !user.getName().equals("") ? user.getName() : value.getName());
                    value.setEmail(user != null && user.getEmail()!=null && !user.getEmail().equals("") ? user.getEmail() : value.getEmail());
                    value.setPhone(user != null && user.getPhone()!=null && !user.getPhone().equals("") ? user.getPhone() : value.getPhone());
                    value.setStatus(user != null && user.getStatus()!=null && !user.getStatus().equals("") ? user.getStatus() : value.getStatus());
                    value.setUpdateBy(user != null && user.getUpdateBy()!=null && !user.getUpdateBy().equals("") ? user.getUpdateBy() : (value.getUpdateBy() !=null && !value.getUpdateBy().equals("")) ? value.getUpdateBy() : Constants.DEFAULT_USER);
                    value.setUpdateDate(Dates.getCurrentDate());
                    return value;
                })
                .flatMap(userImpPort::save)
                .doOnError(error -> {
                    log.error("Error UserService.update({}) -> {}", user, error.getMessage());

                });
    }

}
