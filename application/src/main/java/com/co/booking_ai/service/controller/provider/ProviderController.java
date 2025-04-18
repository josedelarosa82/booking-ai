package com.co.booking_ai.service.controller.provider;

import com.co.booking_ai.service.enums.error.ErrorProviderEnum;
import com.co.booking_ai.service.exception.ProviderException;
import com.co.booking_ai.service.models.dto.request.ProviderReq;
import com.co.booking_ai.service.models.dto.response.ProviderScheduleRes;
import com.co.booking_ai.service.models.provider.Provider;
import com.co.booking_ai.service.ports.input.provider.ProviderServicePort;
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
@RequestMapping("${application.booking_ai.api.path}/providers")
@Api(tags = "provider", description = "provider services API")
@Slf4j
public class ProviderController {

    @Autowired
    private ProviderServicePort providerServicePort;

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create new provider", response = Provider.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Creation was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<Provider> createProvider(@RequestBody Provider provider) {
        return providerServicePort.createProvider(provider)
                .doOnNext(value -> log.debug("Provider was created -> {} ", provider))
                .doOnError(error -> log.error("Error creating provider:{} - {}", provider, error.getMessage()));
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update provider by id", response = Provider.class, httpMethod = "PUT")
    @ApiResponses({@ApiResponse(code = 201, message = "Update was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<Provider> updateProvider(@RequestBody ProviderReq provider, @PathVariable String id) {
        return Mono.just(provider)
                .flatMap(value -> {
                    if (value.getSchedule().getDayOfWeeks() == null ||
                            value.getSchedule().getDayOfWeeks().isEmpty()) {
                        return Mono.error(new ProviderException(ErrorProviderEnum.FROM_DATE_NOT_VALID));
                    }
                    return providerServicePort.updateProvider(value, id);
                })
                .doOnNext(value -> log.debug("Provider was updated -> {} ", provider))
                .doOnError(error -> log.error("Error updating provider:{} - {}", provider, error.getMessage()));
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get provider by id", response = Provider.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<Provider> findById(@PathVariable String id) {
        return providerServicePort.findById(id)
                .doOnNext(value -> log.debug("Provider was consulted -> {} ", id))
                .doOnError(error -> log.error("Error querying provider by id:{} - {}", id, error.getMessage()));
    }

    @GetMapping(path = "/{id}/date/{date}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get schedule by id and date", response = ProviderScheduleRes.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<ProviderScheduleRes> findScheduleByIdAndDate(@PathVariable("id") String id,
                                                             @PathVariable("date") long date) {
        return providerServicePort.findScheduleByIdAndDate(id, date)
                .doOnNext(value -> log.debug("Schedule provider was consulted -> {} ", id))
                .doOnError(error -> log.error("Error querying schedule provider by id:{} - {}", id, error.getMessage()));
    }

    @GetMapping(path = "/phone/{phone}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get provider by phone", response = Provider.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Mono<Provider> findByPhone(@PathVariable String phone) {
        return providerServicePort.findByPhone(phone)
                .doOnNext(value -> log.debug("Provider was consulted -> {} ", phone))
                .doOnError(error -> log.error("Error querying provider by phone:{} - {}", phone, error.getMessage()));
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE}, consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get all providers", response = Provider.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Result was success."),
            @ApiResponse(code = 400, message = "Incorrect input data."),
            @ApiResponse(code = 500, message = "Unexpected error.")})
    public Flux<Provider> findAll() {
        return providerServicePort.findAll()
                .doOnNext(value -> log.debug("Provider was consulted"))
                .doOnError(error -> log.error("Error querying all providers - {}", error.getMessage()));
    }

}
