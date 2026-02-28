package com.mctbl.ledger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LedgerApplicationTests {

	@Test
	void contextLoads() {
		PasswordEncoder a = new BCryptPasswordEncoder();
		System.out.println(a.encode("1"));
	}

}
