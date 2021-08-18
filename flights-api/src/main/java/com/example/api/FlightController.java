package com.example.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
	private final FlightRepository flightRepository;

	public FlightController(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@GetMapping("/all")
	public List<Flight> getAllFlights() {
		return this.flightRepository.findAll();
	}

	@GetMapping
	public List<Flight> getFlights(Authentication authentication) {
		return this.flightRepository.findByPilotId(authentication.getName());
	}

	@PutMapping("/{flightNumber}/taxi")
	@PostAuthorize("returnObject.pilotId == authentication.name")
	@Transactional
	public Flight taxi(@PathVariable String flightNumber) {
		Flight flight = this.flightRepository.findByFlightNumber(flightNumber);
		flight.setStatus(Flight.Status.TAXI);
		return flight;
	}

	@PutMapping("/{flightNumber}/take-off")
	@Transactional
	public Flight takeOff(@PathVariable String flightNumber) {
		Flight flight = this.flightRepository.findByFlightNumber(flightNumber);
		flight.setStatus(Flight.Status.TAKE_OFF);
		return flight;
	}
}
