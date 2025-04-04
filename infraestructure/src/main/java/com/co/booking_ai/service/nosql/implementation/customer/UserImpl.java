package com.co.booking_ai.service.nosql.implementation.customer;

import com.co.booking_ai.service.models.customer.User;
import com.co.booking_ai.service.nosql.document.customer.UserDocument;
import com.co.booking_ai.service.nosql.repository.customer.UserRepository;
import com.co.booking_ai.service.ports.output.customer.UserImpPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(value -> modelMapper.map(value, User.class))
                .doOnNext(value -> log.debug("Find user by phone: {}", value));
    }

    public Flux<User> findAll() {
        return userRepository.findAll()
                .map(value -> modelMapper.map(value, User.class))
                .doOnNext(value -> log.debug("Find all users: {}", value));
    }

    public Mono<User> save(User user) {
        return Mono.just(user)
                .map(value -> modelMapper.map(value, UserDocument.class))
                .flatMap(userRepository::save)
                .map(value -> modelMapper.map(value, User.class))
                .doOnNext(value -> log.debug("Creating user: {}", value));
    }


}
