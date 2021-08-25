package com.example.api;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FlightControllerTests {
	private static final String FLIGHT_102 = "102";
	private static final String FLIGHT_103 = "103";

	@Autowired
	MockMvc mockMvc;

	@MockBean
	FlightRepository flightRepository;

	@Test
	@WithMockUser(username = "marcus", authorities = "flights:write")
	void taxiWhenPilotIdEqualToAuthenticationNameThenOk() throws Exception {
		when(this.flightRepository.findByFlightNumber(anyString()))
				.thenReturn(new Flight(FLIGHT_102, "marcus", Flight.Status.BOARD));

		this.mockMvc.perform(put("/flights/{flightNumber}/taxi", FLIGHT_102)
				.with(csrf().asHeader()))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "marcus", authorities = "flights:write")
	void taxiWhenPilotIdNotEqualToAuthenticationNameThenForbidden() throws Exception {
		when(this.flightRepository.findByFlightNumber(anyString()))
				.thenReturn(new Flight(FLIGHT_103, "steve", Flight.Status.BOARD));

		this.mockMvc.perform(put("/flights/{flightNumber}/taxi", FLIGHT_103)
				.with(csrf().asHeader()))
				.andExpect(status().isForbidden());
	}
}
