package com.co.booking_ai.service.nosql.repository.customer;

import com.co.booking_ai.service.nosql.document.customer.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDocument, String> {

    Mono<UserDocument> findByPhone(String phone);
}
