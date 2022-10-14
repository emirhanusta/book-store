package com.socialNetwork.ws.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Users {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Size(min=4,max=255)
	//@UniqueUserName
	private String userName;
	@NotNull
	@Size(min=4,max=255)
	private String displayName;
	@NotNull
	//@Size(min=8,max=16)
	//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$")
	private String password;
}
