package com.mx.ApiRestFullE.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestFullE.entity.Employees;
import com.mx.ApiRestFullE.repository.EmployeesRepository;
import com.mx.ApiRestFullE.repository.GendersRepository;
import com.mx.ApiRestFullE.repository.JobsRepository;

@Service
public class EmployeesServImp {
	
	@Autowired
	EmployeesRepository employeesRepository;
	
	@Autowired 
	GendersRepository gendersRepository;
	
	@Autowired
	JobsRepository jobsRepository;
	
	// 1) listar
	@Transactional(readOnly = true)
	public List<Employees> listar(){
		return employeesRepository.findAll();
		
	}
	
	
	// 2) Buscar por Id
	@Transactional(readOnly = true)
	public Employees buscarPorId(Integer id) {
		return employeesRepository.findById(id).orElse(null);
		
	}
	
	
	
	//3) Guardar 
	//Validaciones 1: nombre y apellido del empleado no existan en base de datos
	//Validaciones 2. ser mayor de edad
	//Validaciones 3: el sexo asignado debe existir en la tabla de genders
	//Validaciones 4: el piesto asignado debe existir en la tabla jobs
	
	@Transactional
	public String guardar(Employees employees) {
		
		if (employeesRepository.existsByNombreEmployees(employees.getNombreEmployees())) 
			return "Error: El nombre [" + employees.getNombreEmployees() + "] ya está registrado.";	
		
		if(employeesRepository.existsByLastName(employees.getLastName()))
			return "Error: El apellido [" + employees.getLastName() + "] ya existe.";
		
		//Validar mayor de edad
		if(!esMayorDeEdad(employees.getBirthdate())) 
			return "Error: El empleado debe ser mayor de edad.";
		
		//Validar que el gender exista
		if(employees.getGender() == null || employees.getGender().getIdGenders() == null)
			return "Error: El Gender con id " + employees.getGender().getIdGenders() + " no existe";
		
		if(!gendersRepository.existsById(employees.getGender().getIdGenders()))
			return "Error: El GENDER con ID " + employees.getGender().getIdGenders() + " no existe.";
		
		// Validar que el job exista
		if(employees.getJob() == null || employees.getJob().getIdJobs() == null)
			return "Error: No se especificó un JOB válido.";
		
		if(!jobsRepository.existsById(employees.getJob().getIdJobs()))
			 return "Error: El JOB con ID " + employees.getJob().getIdJobs() + " no existe.";
		
		
		
		employeesRepository.save(employees);
		return "Empleado guardado correctamente.";
	}
	
	//4) Editar
	public String editar(Employees employees) {
		//Verificar que exista el ID
		if(!employeesRepository.existsById(employees.getIdEmployees())) 
			 return "Error: El empleado con ID " + employees.getIdEmployees() + " no existe, no se puede editar.";
		
		employeesRepository.save(employees);
        return "Empleado con ID " + employees.getIdEmployees() + " editado correctamente.";
	}
	
	// 5) Eliminar
    public String eliminar(Integer id) {
        if (!employeesRepository.existsById(id)) {
            return "Error: El empleado con ID " + id + " no existe, no se puede eliminar.";
        }
        employeesRepository.deleteById(id);
        return "Empleado con ID " + id + " eliminado correctamente.";
    }
	
	
	
	/////////////////////////////////////////////////////////////
	// Método auxiliar para validar mayor de edad
    private boolean esMayorDeEdad(Date birthDate) {
        LocalDate birthLocalDate = birthDate.toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthLocalDate, now);
        return period.getYears() >= 18;
    }
    
    
	

}
