package edu.campus.numerique.module.java.license.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class License {
	
	public License(String licenseId, Boolean valid) {
		super();
		this.licenseId = licenseId;
		this.isValid=valid;
	}
	
	public License() {
	}

	@Id
	private String licenseId;
	
	@Column
	private Boolean isValid;

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	
	
}
