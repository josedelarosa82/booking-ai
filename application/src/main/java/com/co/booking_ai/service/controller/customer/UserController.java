package com.co.booking_ai.service.controller.customer;

import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.models.dto.request.UserRequest;
import com.co.booking_ai.service.ports.input.customer.UserServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    @ApiOperation(value = "Create new user", response = User.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Creation was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> createUser(@RequestBody User user) {
        return userServicePort.create(user)
                .doOnNext(value -> log.debug("User was created -> {} ", user))
                .doOnError(error -> log.error("Error creating user:{} - {}", user, error.getMessage()));
    }

    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update user by id", response = User.class, httpMethod = "PUT")
    @ApiResponses({@ApiResponse(code = 201, message = "Update was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> updateUser(@RequestBody UserRequest user, @PathVariable String id) {
        return userServicePort.update(user, id)
                .doOnNext(value -> log.debug("User was updated -> {} ", user))
                .doOnError(error -> log.error("Error updating user:{} - {}", user, error.getMessage()));
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get user by id", response = User.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> findById(@PathVariable String id) {
        return userServicePort.findById(id)
                .doOnNext(value -> log.debug("User was consulted -> {} ", id))
                .doOnError(error -> log.error("Error querying user by id:{} - {}", id, error.getMessage()));
    }

    @GetMapping(path = "/phone/{phone}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get user by phone", response = User.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<User> findByPhone(@PathVariable String phone) {
        return userServicePort.findByPhone(phone)
                .doOnNext(value -> log.debug("User was consulted -> {} ", phone))
                .doOnError(error -> log.error("Error querying user by phone:{} - {}", phone, error.getMessage()));
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get all users", response = User.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Flux<User> findAll() {
        return userServicePort.findAll()
                .doOnNext(value -> log.debug("User was consulted"))
                .doOnError(error -> log.error("Error querying all users - {}", error.getMessage()));
    }

}
