package com.example.api;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {
	@Id
	private String flightNumber;
	private String pilotId;
	private Status status = Status.BOARD;

	public Flight() {
	}

	public Flight(String flightNumber, String pilotId, Status status) {
		this.flightNumber = flightNumber;
		this.pilotId = pilotId;
		this.status = status;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getPilotId() {
		return pilotId;
	}

	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		BOARD, TAXI, TAKE_OFF
	}
}
