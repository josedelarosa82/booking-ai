package com.co.booking_ai.service.ports.input.customer;

import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.models.dto.request.UserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServicePort {

    //Mono<Servicio> crearServicioUsuario(ServicioUsuarioDTO servicioUsuarioDTOReq);

    Mono<User> findById(String id);

    Mono<User> create(User user);

    Mono<User> update(UserRequest user, String id);

    Flux<User> findAll();
}
