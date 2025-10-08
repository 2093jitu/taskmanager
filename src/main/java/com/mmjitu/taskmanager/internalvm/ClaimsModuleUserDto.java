package com.mmjitu.taskmanager.internalvm;

public class ClaimsModuleUserDto {

	private Long id;

	private String fullname;

	private String pfNo;

	private String email;

	private String designation;

	private String mobile;

	private String gender;

	private Boolean isActive = true;

	// ===============================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	// ===================================================
	public ClaimsModuleUserDto(Long id, String fullname, String pfNo, String email, String designation, String mobile,
			String gender) {

		this.id = id;
		this.fullname = fullname;
		this.pfNo = pfNo;
		this.email = email;
		this.designation = designation;
		this.mobile = mobile;
		this.gender = gender;
	}

	public ClaimsModuleUserDto() {
	}

}