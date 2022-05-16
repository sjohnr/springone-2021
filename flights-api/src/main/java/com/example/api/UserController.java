package com.example.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/info")
	public Mono<Principal> getInfo(Principal principal) {
		return Mono.justOrEmpty(principal);
	}

}
