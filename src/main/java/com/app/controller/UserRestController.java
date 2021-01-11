package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.User;
import com.app.exception.UserNotFoundException;
import com.app.service.UserClientService;
import com.app.util.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.NonNull;

@RestController
@RequestMapping(value = "/v1/api")
@Validated
public class UserRestController {

	@Autowired
	private UserClientService userClientService;
	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getWelcomeMessage() {
		return ResponseEntity.ok(userClientService.getUsers());
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<User> getUser(@NonNull @PathVariable Integer id) {
		User user = null;
		Optional<User> optionalUser = userClientService.getUser(id);
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		} else {
			throw new UserNotFoundException("User Not Found With Id - " + id);
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "/users")
	public ResponseEntity<User> createNewUser(@RequestBody User user) throws JsonProcessingException {
		log.info("UserRestController createNewUser Request :: {}",Converter.convertObjctToJsonFormat(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(userClientService.createNewUser(user));
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<String> deleteUser(@NonNull @PathVariable Integer id) {
		userClientService.deleteUser(id);
		log.info("User Deleted With Id :: {}", id);
		return ResponseEntity.ok("User Deleted Successfully With Id - " + id);
	}

	@PutMapping(value = "/users/{id}")
	public ResponseEntity<String> updateUser(@RequestBody User user, @NonNull @PathVariable Integer id) {
		user.setId(id);
		userClientService.updateUser(user, id);
		log.info("User Record Updated Successfully With Id - {}", id);
		return ResponseEntity.ok("User Record Updated Successfully With Id - " + id);
	}

}
