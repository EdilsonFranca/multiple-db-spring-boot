package com.javatodev.api;

import com.javatodev.api.model.db1.UserEntity1;
import com.javatodev.api.repository.db1.User1Repository;
import com.javatodev.api.repository.db2.User2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessResourceFailureException;

@SpringBootApplication
public class ApiApplication {
	private final User1Repository userRepository1;

	private final User2Repository userRepository2;

	public ApiApplication(User1Repository userRepository1, User2Repository userRepository2) {
		this.userRepository1 = userRepository1;
		this.userRepository2 = userRepository2;

		UserEntity1 user1 = UserEntity1.builder().username("Edilson França").build();

		try {
			this.userRepository1.save(user1);
		} catch (DataAccessResourceFailureException ex) {
			System.out.println("Falha ao salvar no primeiro banco: " + ex.getMessage());
		}

		try {
			this.userRepository2.save(user1);
		} catch (DataAccessResourceFailureException ex) {
			System.out.println("Falha ao salvar no segundo banco: " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
