package com.mx.ApiRestFullE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestFullE.entity.Employees;
import com.mx.ApiRestFullE.service.EmployeesServImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "api/EmployeesWs")
public class EmployeesWs {

	@Autowired
	EmployeesServImp employeesServImp;
	
	//Listar
	// GET -> http://localhost:9000/api/EmployeesWs/listar-employees
	@GetMapping(path = "listar-employees")
	public List<Employees> listar() {
		return employeesServImp.listar();
	}
	
	
	//Buscar por Id
	//POST -> http://localhost:9000/api/EmployeesWs/buscar-employees
	@PostMapping(path = "buscar-employees")
	public Employees buscarPorId(@RequestBody Employees emp) {
		
		return employeesServImp.buscarPorId(emp.getIdEmployees());
	}
	
	// Guardar
    // POST -> http://localhost:8080/api/EmployeesWs/guardar-employees
    @PostMapping(path = "guardar-employees")
    public ResponseEntity<?> guardar(@RequestBody Employees emp) {
        String respuesta = employeesServImp.guardar(emp);
        
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
    // Editar
    // POST -> http://localhost:8080/api/EmployeesWs/editar-employees
    @PostMapping(path = "editar-employees")
    public ResponseEntity<?> editar(@RequestBody Employees emp) {
        String respuesta = employeesServImp.editar(emp);
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    // Eliminar
    // POST -> http://localhost:8080/api/EmployeesWs/eliminar-employees
    @PostMapping(path = "eliminar-employees")
    public ResponseEntity<?> eliminar(@RequestBody Employees emp) {
        String respuesta = employeesServImp.eliminar(emp.getIdEmployees());
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
	
	
}
