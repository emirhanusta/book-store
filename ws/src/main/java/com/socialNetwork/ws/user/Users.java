package com.socialNetwork.ws.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Users {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String userName;
	
	private String displayName;
	
	private String password;
}
