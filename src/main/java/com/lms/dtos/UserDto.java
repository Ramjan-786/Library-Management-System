package com.lms.dtos;

import javax.persistence.Column;

public class UserDto {
	
	private int id;
	
	private String first_name;
	
	private String last_name;
	
	private String address;
	
	private String contact_no;
	
	private String branch;
	
	private String email;
	
	private String password;
	
	private String role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDto() {
		super();
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", address=" + address
				+ ", contact_no=" + contact_no + ", branch=" + branch + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}

	public UserDto(int id, String first_name, String last_name, String address, String contact_no, String branch,
			String email, String password, String role) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.contact_no = contact_no;
		this.branch = branch;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	

}
