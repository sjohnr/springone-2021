package com.example.api;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
public class FlightController {
	private final FlightRepository flightRepository;

	public FlightController(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@GetMapping("/all")
	public Flux<Flight> getAllFlights() {
		return this.flightRepository.findAll();
	}

	@GetMapping
	public Flux<Flight> getFlights(Authentication authentication) {
		return this.flightRepository.findByPilotId(authentication.getName());
	}

	@PutMapping("/{flightNumber}/taxi")
	@PostAuthorize("returnObject.pilotId == authentication.name")
	public Mono<Flight> taxi(@PathVariable String flightNumber) {
		return this.flightRepository.findByFlightNumber(flightNumber)
			.map(flight -> {
				flight.setStatus(Flight.Status.TAXI);
				return flight;
			});
	}

	@PutMapping("/{flightNumber}/take-off")
	public Mono<Flight> takeOff(@PathVariable String flightNumber) {
		return this.flightRepository.findByFlightNumber(flightNumber)
			.map(flight -> {
				flight.setStatus(Flight.Status.TAKE_OFF);
				return flight;
			});
	}
}
