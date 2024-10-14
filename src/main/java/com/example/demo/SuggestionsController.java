package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionsController {
	private DatabaseBean db;

	public SuggestionsController() {
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		db = new DatabaseBean(url, username, password);
	}

	@GetMapping("/subscribe")
	public String subscribeUser(@RequestParam String email) {
		boolean response = db.addEmail(email);

		if (response) {
			return "Success!";
		} else {
			return "Failure, likely duplicate email";
		}
	}
}
