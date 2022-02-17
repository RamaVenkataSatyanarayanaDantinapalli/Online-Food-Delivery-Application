package com.cg.fds.dto;

import javax.validation.constraints.NotNull;

public class LoginSavePostDTO {
	
	@NotNull(message = "username is empty")
	private String username;
	@NotNull(message = "password is empty")
	private String password;
	@NotNull(message = "userrole is empty")
	private boolean userrole;
	
	public LoginSavePostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginSavePostDTO(@NotNull(message = "username is empty") String username,
			@NotNull(message = "password is empty") String password,
			@NotNull(message = "userrole is empty") boolean userrole) {
		super();
		this.username = username;
		this.password = password;
		this.userrole = userrole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getUserrole() {
		return userrole;
	}

	public void setUserrole(boolean userrole) {
		this.userrole = userrole;
	}
	
	
}
