package com.example.api;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FlightRepository extends ReactiveCrudRepository<Flight, String> {
	Mono<Flight> findByFlightNumber(String flightNumber);
	Flux<Flight> findByPilotId(String pilotId);
}
