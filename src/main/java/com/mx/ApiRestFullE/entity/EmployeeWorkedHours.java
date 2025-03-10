package com.mx.ApiRestFullE.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMPLOYEE_WORKED_HOURS")
@Data
public class EmployeeWorkedHours {
	
	@Id
	@Column(name = "ID")
	private Integer idWorked;
	
	@Column(name = "WORKED_HOURS")
	private Integer workedHours;
	
	@Column(name = "WORKED_DATE")
	private Date workedDate;
	
	//MUCHOS EMPLOYEE_WORKED_HOURS TIENEN UN EMPLOYEE
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_ID")
	Employees employees; //Este si va en el encapsulamiento
	

}
