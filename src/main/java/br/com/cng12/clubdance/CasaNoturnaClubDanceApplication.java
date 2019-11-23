package br.com.cng12.clubdance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CasaNoturnaClubDanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaNoturnaClubDanceApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
