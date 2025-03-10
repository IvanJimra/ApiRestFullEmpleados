package com.mx.ApiRestFullE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestFullE.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Integer>{
	
	boolean existsByNombreEmployees(String nombreEmployees);
	
	boolean existsByLastName(String lastName);
	
}
