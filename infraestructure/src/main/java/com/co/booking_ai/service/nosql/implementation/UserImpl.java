package com.co.booking_ai.service.nosql.implementation;

import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.nosql.document.customer.UserDocument;
import com.co.booking_ai.service.nosql.repository.UserRepository;
import com.co.booking_ai.service.ports.output.UserImpPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Slf4j
public class UserImpl implements UserImpPort {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Mono<User> findById(String id) {
        return userRepository.findById(id)
                .map(value -> modelMapper.map(value, User.class))
                .doOnNext(value -> log.debug("Find user by id: {}", value));
    }

    public Mono<User> save(User user) {
        return Mono.just(user)
                .map(value -> {
                    return User.builder()
                            .email(value.getEmail())
                            .name(value.getName())
                            .status(value.getStatus())
                            .createBy(value.getCreateBy())
                            .createDate(new Date())
                            .build();
                })
                .map(value -> modelMapper.map(value, UserDocument.class))
                .flatMap(userRepository::save)
                .map(value -> modelMapper.map(value, User.class))
                .doOnNext(value -> log.debug("Saving user: {}", value));
    }
}
