package com.co.booking_ai.service.nosql.repository;

import com.co.booking_ai.service.nosql.document.customer.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDocument, String> {
    Mono<UserDocument> findById(String id);

    Mono<UserDocument> save(UserDocument userDocument);
}
