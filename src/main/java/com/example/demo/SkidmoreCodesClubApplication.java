package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkidmoreCodesClubApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SkidmoreCodesClubApplication.class, args);

		String url = "";
		String username = "";
		String password = "";
		DatabaseController db = new DatabaseController(url, username, password);
		boolean emailExists = db.checkIfEmailExists("ankit@scc.com");
		System.out.println(emailExists);

		Integer emailID = db.getEmailID("ankit@scc.com");
		System.out.println(emailID);

		db.addSuggestion("ankit@scc.com", "Hello, World!");
	}

}
