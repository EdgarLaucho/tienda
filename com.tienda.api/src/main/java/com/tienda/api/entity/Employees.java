package com.tienda.api.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de los empleados
 */
@Entity
@Table(name = "employees")
public class Employees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "employee_name",length  = 40)
	private String employeeName;
	
	@Column(name = "employee_last_name", length = 45)
	private String employeeLastName;
	
	@Column(name = "employee_start_time")
	private LocalTime employeeStartTime;
	
	@Column(name= "employee_end_time")
	private LocalTime employeeEndTime;
	
	@Column(name = "employee_position")
	private String employeePosition;
	
	@Column(name = "employee_status")
	private String employeeStatus;
	
	@Column(name = "employee_type")
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

	public Employees(String employeeName, String employeeLastName, LocalTime employeeStartTime,
			LocalTime employeeEndTime, String employeePosition,
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

	public Employees() {
		super();
	
	}
	
	

}
