package com.cg.fds.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Entity
public class Login {
	@Id
	@NotNull(message = "username Name is empty")
	private String userName;
	@NotNull(message = "password Name is empty")
	private String password;
	//String role		Admin or user
	@NotNull(message = "userrole is empty")
	private Boolean userRole;	//true  =  admin, false = user
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getUserRole() {
		return userRole;
	}
	public void setUserRole(Boolean userRole) {
		userRole = userRole;
	}
	public Login(String userName, String password,boolean userRole) {
		super();
	
		this.userName = userName;
		this.password = password;
		this.userRole=userRole;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(userRole, password, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(userRole, other.userRole) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + ", UserRole=" +userRole + "]";
	}
	
	
	
}
