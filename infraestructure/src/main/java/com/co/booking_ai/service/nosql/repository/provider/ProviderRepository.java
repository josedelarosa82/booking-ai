package com.co.booking_ai.service.nosql.repository.provider;

import com.co.booking_ai.service.nosql.document.provider.ProviderDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends ReactiveMongoRepository<ProviderDocument, String> {

}
