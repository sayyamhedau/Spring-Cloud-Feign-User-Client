package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.User;

import lombok.NonNull;

@FeignClient(name = "USER-CLIENT", url = "https://jsonplaceholder.typicode.com")
public interface UserClientService {
	@GetMapping(value = "/users")
	List<User> getUsers();

	@GetMapping(value = "/users/{id}")
	Optional<User> getUser(@NonNull @PathVariable Integer id);

	@PostMapping(value = "/users")
	User createNewUser(@RequestBody User user);

	@DeleteMapping(value = "/users/{id}")
	void deleteUser(@NonNull @PathVariable Integer id);

	@PutMapping(value = "/users/{id}")
	void updateUser(@RequestBody User user, @NonNull @PathVariable Integer id);
}
