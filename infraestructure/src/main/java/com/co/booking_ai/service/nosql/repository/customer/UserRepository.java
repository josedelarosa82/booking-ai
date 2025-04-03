package com.co.booking_ai.service.nosql.repository.customer;

import com.co.booking_ai.service.nosql.document.customer.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDocument, String> {


}
