package com.example.demo;

import com.example.demo.entities.User;
import com.example.demo.entities.UserGroup;
import com.example.demo.repository.UserGroupRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGroupRepository  userGroupRepository;

	@Test
	@Rollback(value = false)
	void contextLoads() {
		assertNotNull(userRepository);
		User user = new User();
		user.setSurname("Smith");
		user.setFirstName("John");
		user.setPassportNumber("123456");

		userRepository.save(user);
	}

	@Test
	@Rollback(value = false)
	void adduserGroup() {
		UserGroup userGroup = new UserGroup();
		userGroup.setName("students");

		userGroupRepository.save(userGroup);

		{
			User user = new User();
			user.setSurname("Smith");
			user.setFirstName("John");
			user.setPassportNumber("123456");
			userRepository.save(user);
			userGroup.getUsers().add(user);
		}

		{
			User user = new User();
			user.setSurname("Conor");
			user.setFirstName("John");
			user.setPassportNumber("1236");
			userRepository.save(user);
			userGroup.getUsers().add(user);
		}

		userGroupRepository.save(userGroup);
	}

	@Test
	@Rollback(value = false)
	void adduserGroupInOneSetting() {
		UserGroup userGroup = new UserGroup();
		userGroup.setName("students");

		{
			User user = new User();
			user.setSurname("Smith");
			user.setFirstName("John");
			user.setPassportNumber("123456");
			userGroup.getUsers().add(user);
		}

		{
			User user = new User();
			user.setSurname("Conor");
			user.setFirstName("John");
			user.setPassportNumber("1236");
			userGroup.getUsers().add(user);
		}

		userGroupRepository.save(userGroup);
	}

}
