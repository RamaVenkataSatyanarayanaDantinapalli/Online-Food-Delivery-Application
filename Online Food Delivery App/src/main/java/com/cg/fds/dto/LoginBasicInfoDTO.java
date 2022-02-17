package com.cg.fds.dto;

public class LoginBasicInfoDTO {
	
	private String username;
	private String password;
	private boolean userrole;
	
	
	public LoginBasicInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
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
