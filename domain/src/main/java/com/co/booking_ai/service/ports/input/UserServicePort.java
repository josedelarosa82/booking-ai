package com.co.booking_ai.service.ports.input;

import com.co.booking_ai.service.models.customer.User;
import reactor.core.publisher.Mono;

public interface UserServicePort {

    //Mono<Servicio> crearServicioUsuario(ServicioUsuarioDTO servicioUsuarioDTOReq);

    Mono<User> findUserById(String id);

    Mono<User> save(User user);
}
