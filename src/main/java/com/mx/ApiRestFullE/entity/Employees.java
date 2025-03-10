package com.mx.ApiRestFullE.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMPLOYEES")
@Data
public class Employees {
	
	@Id
	@Column(name = "ID")
	private Integer idEmployees;
	
	@Column(name = "NAME")
	private String nombreEmployees;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "BIRTHDATE")
	private Date birthdate;
	
	
	//Cardinalidad -- Muchos modelos pertecen a una marca
	//FetchType.EAGER --- Indicamos que la relacion debe ser cargada 
	//al monento -- procesos hilos
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GENDER_ID")
	Genders gender; //Este si va en el encapsulamiento
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_ID")
	Jobs job; //Este si va en el encapsulamiento
	
	@OneToMany(mappedBy = "employees", cascade = CascadeType.ALL)
	@JsonIgnore //para ignorar una propiedad o lista de propiedades
	private List<EmployeeWorkedHours> lista = new ArrayList<>();

}
