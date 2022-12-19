package com.neo.model;


public class User{

	private String id;
	private String password;
	private String name;
	private Boolean sex;
	private Integer userType;
	private String departmentName;
	private String telephone;
	private String email;
	private String address;
	private Integer lateNumber;
	private Integer bunkNumber;
	private Integer offNumber;
	private Integer overNumber;
	private Integer evecNumber;
	private Integer baseSalary;

	public User(String id, String password, String name, Boolean sex, Integer userType, String departmentName, String telephone, String email, String address, Integer lateNumber, Integer bunkNumber, Integer offNumber, Integer overNumber, Integer evecNumber, Integer baseSalary) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.userType = userType;
		this.departmentName = departmentName;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.lateNumber = 0;
		this.bunkNumber = 0;
		this.offNumber = 0;
		this.overNumber = 0;
		this.evecNumber = 0;
		this.baseSalary = baseSalary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLateNumber() {
		return lateNumber;
	}

	public void setLateNumber(Integer lateNumber) {
		this.lateNumber = lateNumber;
	}

	public Integer getBunkNumber() {
		return bunkNumber;
	}

	public void setBunkNumber(Integer bunkNumber) {
		this.bunkNumber = bunkNumber;
	}

	public Integer getOffNumber() {
		return offNumber;
	}

	public void setOffNumber(Integer offNumber) {
		this.offNumber = offNumber;
	}

	public Integer getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(Integer overNumber) {
		this.overNumber = overNumber;
	}

	public Integer getEvecNumber() {
		return evecNumber;
	}

	public void setEvecNumber(Integer evecNumber) {
		this.evecNumber = evecNumber;
	}

	public Integer getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Integer baseSalary) {
		this.baseSalary = baseSalary;
	}
}