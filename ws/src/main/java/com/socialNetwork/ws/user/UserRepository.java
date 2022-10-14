package com.socialNetwork.ws.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUserName(String userName);
}
