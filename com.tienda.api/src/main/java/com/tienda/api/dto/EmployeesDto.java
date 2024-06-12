package com.tienda.api.dto;

import java.time.LocalTime;

public class EmployeesDto {

private Long employeeId;
	
	private String employeeName;
	
	private String employeeLastName;

	private LocalTime employeeStartTime;
	
	private LocalTime employeeEndTime;
	
	private String employeePosition;
	
	private String employeeStatus;
	
	private String employeeType;

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employeeLastName
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	/**
	 * @param employeeLastName the employeeLastName to set
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}




	/**
	 * @return the employeeStartTime
	 */
	public LocalTime getEmployeeStartTime() {
		return employeeStartTime;
	}

	/**
	 * @param employeeStartTime the employeeStartTime to set
	 */
	public void setEmployeeStartTime(LocalTime employeeStartTime) {
		this.employeeStartTime = employeeStartTime;
	}

	/**
	 * @return the employeeEndTime
	 */
	public LocalTime getEmployeeEndTime() {
		return employeeEndTime;
	}

	/**
	 * @param employeeEndTime the employeeEndTime to set
	 */
	public void setEmployeeEndTime(LocalTime employeeEndTime) {
		this.employeeEndTime = employeeEndTime;
	}

	/**
	 * @return the employeePosition
	 */
	public String getEmployeePosition() {
		return employeePosition;
	}

	/**
	 * @param employeePosition the employeePosition to set
	 */
	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}

	/**
	 * @return the employeeStatus
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * @param employeeStatus the employeeStatus to set
	 */
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**
	 * @return the employeeType
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeesDto(String employeeName, String employeeLastName,
			LocalTime employeeStartTime, LocalTime employeeEndTime, String employeePosition,
			String employeeStatus, String employeeType) {
		super();
		this.employeeName = employeeName;
		this.employeeLastName = employeeLastName;
		this.employeeStartTime = employeeStartTime;
		this.employeeEndTime = employeeEndTime;
		this.employeePosition = employeePosition;
		this.employeeStatus = employeeStatus;
		this.employeeType = employeeType;
	}

	public EmployeesDto() {
		super();
	
	}
}
