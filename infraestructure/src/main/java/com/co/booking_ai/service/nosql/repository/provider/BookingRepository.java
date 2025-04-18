package com.co.booking_ai.service.nosql.repository.provider;

import com.co.booking_ai.service.nosql.document.provider.BookingDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import java.util.Date;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<BookingDocument, String> {

    Mono<BookingDocument> findByProviderIdAndDate(String providerId, Date date);

    Mono<BookingDocument> findByProviderIdAndServiceIdAndDate(String providerId, String serviceId, Date date);
}
