package com.example.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeHints;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(order = -1)
@TypeHints({
		@TypeHint(typeNames = "org.springframework.security.access.expression.method.MethodSecurityExpressionRoot", access = AccessBits.ALL),
		@TypeHint(typeNames = "org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken", access = AccessBits.ALL)
})
public class FlightsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsApiApplication.class, args);
	}

	@Component
	static class FlightDataInitializer implements CommandLineRunner {

		private final FlightRepository flightRepository;
		private final AccessRuleRepository rules;

		FlightDataInitializer(FlightRepository flightRepository, AccessRuleRepository rules) {
			this.flightRepository = flightRepository;
			this.rules = rules;
		}

		@Override
		public void run(String... args) {
			this.flightRepository.save(new Flight("101", "josh", Flight.Status.TAXI)).block();
			this.flightRepository.save(new Flight("102", "marcus", Flight.Status.BOARD)).block();
			this.flightRepository.save(new Flight("103", "steve", Flight.Status.BOARD)).block();
			this.rules.save(new AccessRule("/flights/all", "flights:all")).block();
			this.rules.save(new AccessRule("/flights/*/take-off", "flights:approve")).block();
			this.rules.save(new AccessRule("/flights", "flights:read")).block();
			this.rules.save(new AccessRule("/**", "flights:write")).block();
		}

	}
}
