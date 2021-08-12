package com.example.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
	Flight findByFlightNumber(String flightNumber);
	List<Flight> findByPilotId(String pilotId);
}
