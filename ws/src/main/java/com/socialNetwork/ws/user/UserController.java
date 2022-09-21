package com.socialNetwork.ws.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialNetwork.ws.shared.GenericResponse;


@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/1.0/users")
	public GenericResponse creatUser(@RequestBody Users user) {
		userService.save(user);
		return new GenericResponse("user created");
	}
	
}
