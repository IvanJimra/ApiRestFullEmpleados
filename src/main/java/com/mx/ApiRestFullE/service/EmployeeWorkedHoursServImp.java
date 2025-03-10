package com.mx.ApiRestFullE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestFullE.entity.EmployeeWorkedHours;
import com.mx.ApiRestFullE.repository.EmployeeWorkedHoursRepository;
import com.mx.ApiRestFullE.repository.EmployeesRepository;

@Service

public class EmployeeWorkedHoursServImp {
	
	@Autowired
	EmployeeWorkedHoursRepository employeeWorkedRepository;
	
	@Autowired
	EmployeesRepository employeesRepository;
	
	//1) lISTAR
	@Transactional(readOnly = true)
	public List<EmployeeWorkedHours> listar(){
		return employeeWorkedRepository.findAll();
	}
	
	//2) Buscar por ID
	@Transactional(readOnly = true)
	public EmployeeWorkedHours buscarPorId(Integer id) {
		return 	employeeWorkedRepository.findById(id).orElse(null);
	}
	
	//3) Guardar
	//Para que se puedan registrar las horas trabajadas debe cumplir lo siguiente:
	//Validaciones 1: el empleado debe estar registrado en la tabla employees
	
	public String guardar(EmployeeWorkedHours ewh) {
		if(ewh.getEmployees() == null || ewh.getEmployees().getIdEmployees() == null)
			 return "Error: El EMPLOYEE con ID " + ewh.getEmployees().getIdEmployees() + " no existe.";
		
		if(!employeesRepository.existsById(ewh.getEmployees().getIdEmployees()))
			 return "Error: El EMPLOYEE con ID " + ewh.getEmployees().getIdEmployees() + " no existe.";
		
		employeeWorkedRepository.save(ewh);
		return "Horas trabajadas guardadas correctamente.";
	}
	
	//4) Editar 
	public String editar(EmployeeWorkedHours ewh) {
		if(!employeeWorkedRepository.existsById(ewh.getIdWorked()))
			 return "Error: El registro con ID " + ewh.getIdWorked() + " no existe, no se puede editar.";
		
		//Validar que empleado exista
		if(ewh.getEmployees() == null || ewh.getEmployees().getIdEmployees() == null 
				|| !employeesRepository.existsById(ewh.getEmployees().getIdEmployees()))
			 return "Error: No se puede editar, el EMPLOYEE asignado no existe.";
		
        employeeWorkedRepository.save(ewh);
        return "Registro de horas editado correctamente.";
    }
	
	//5) Eliminar
	 public String eliminar(Integer id) {
	        if (!employeeWorkedRepository.existsById(id)) 
	            return "Error: El registro con ID " + id + " no existe, no se puede eliminar.";
	        
	        employeeWorkedRepository.deleteById(id);
	        return "Registro con ID " + id + " eliminado correctamente.";
	    }
	

}
