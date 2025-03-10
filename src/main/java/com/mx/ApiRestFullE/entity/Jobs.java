package com.mx.ApiRestFullE.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "JOBS")
@Data
public class Jobs {
	
	@Id
	@Column(name = "ID")
	private Integer idJobs;
	
	@Column(name = "NAME")
	private String nombreJobs;
	
	@Column(name = "SALARY")
	private Float salary;
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	@JsonIgnore //para ignorar una propiedad o lista de propiedades
	private List<Employees> lista = new ArrayList<>();
	

}
