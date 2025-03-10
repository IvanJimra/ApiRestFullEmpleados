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
@Table(name = "GENDERS")
@Data
public class Genders {
	
	@Id
	@Column(name = "ID")
	private Integer idGenders;
	
	@Column(name = "NAME")
	private String nombreGenders;
	
	//CARDINALIDAD -- UNA MARCA TIENE MUCHOS MODELOS 
	@OneToMany(mappedBy = "gender", cascade = CascadeType.ALL)
	@JsonIgnore //para ignorar una propiedad o lista de propiedades
	private List<Employees> lista = new ArrayList<>();

}
