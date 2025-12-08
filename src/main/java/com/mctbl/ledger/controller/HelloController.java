package com.mctbl.ledger.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mctbl.ledger.config.JWTConfig;
import com.mctbl.ledger.restservice.Greeting;
import io.jsonwebtoken.*;

@RestController
@RequestMapping("/api")
public class HelloController {

	@Autowired
	public JWTConfig jwt;

	@GetMapping("/hello")
	public String getHello() {
		return "<h1>Hello World!</h1>";
	}

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(name="name", defaultValue = "world") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/secret")
	public String secret() {
		return String.format("<h1>%s</h1>", jwt.secret);
	}

}
