package com.co.booking_ai.service.controller.customer;

import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.ports.input.UserServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RefreshScope
@RestController
@RequestMapping("${application.booking_ai.api.path}/users")
@Api(tags = "user", description = "user services API")
@Slf4j
public class UserController {

    @Autowired
    private UserServicePort userServicePort;

    @PostMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create user", response = User.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Creation was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> createUser(@RequestBody User user) {
        return userServicePort.save(user)
                .doOnNext(value -> log.debug("Se creó el usuario -> {} ", user))
                .doOnError(error -> log.error("Error creando el usuario {}", error.getMessage()));
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get user information", response = User.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> findUserById(@PathVariable String id) {
        return userServicePort.findUserById(id)
                .doOnNext(value -> log.debug("Se consultó el servicio del usuario -> {} ", id))
                .doOnError(error -> log.error("Error consultando un servicio del usuario {}", error.getMessage()));
    }

}
