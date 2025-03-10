package com.mx.ApiRestFullE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestFullE.entity.EmployeeWorkedHours;
import com.mx.ApiRestFullE.service.EmployeeWorkedHoursServImp;

@RestController
@RequestMapping(path = "/api/EmployeeWorkedHours")
@CrossOrigin
public class EmployeeWorkedHoursWs {
	
	@Autowired
	EmployeeWorkedHoursServImp employeeWorkedHoursServImp;
	
	 // 1) Listar
	// GET -> http://localhost:9000/api/EmployeeWorkedHours/listar-hours
    @GetMapping(path = "listar-hours")
    public List<EmployeeWorkedHours> listar() {
        return employeeWorkedHoursServImp.listar();
    }
    
    // 2) Buscar por ID
    // POST -> http://localhost:9000/api/EmployeeWorkedHours/buscar-hours
    @PostMapping(path = "buscar-hours")
    public EmployeeWorkedHours buscar(@RequestBody EmployeeWorkedHours ewh) {
        return employeeWorkedHoursServImp.buscarPorId(ewh.getIdWorked());
    }
    
    // 3) Guardar
    // POST -> http://localhost:9000/api/EmployeeWorkedHours/guardar-hours
    @PostMapping(path = "guardar-hours")
    public ResponseEntity<?> guardar(@RequestBody EmployeeWorkedHours ewh) {
        String respuesta = employeeWorkedHoursServImp.guardar(ewh);
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
    // 4) Editar
    // POST -> http://localhost:9000/api/EmployeeWorkedHours/editar-hours
    @PostMapping(path = "editar-hours")
    public ResponseEntity<?> editar(@RequestBody EmployeeWorkedHours ewh) {
        String respuesta = employeeWorkedHoursServImp.editar(ewh);
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    // 5) Eliminar
    // POST -> http://localhost:9000/api/EmployeeWorkedHours/eliminar-hours
    @PostMapping(path = "eliminar-hours")
    public ResponseEntity<?> eliminar(@RequestBody EmployeeWorkedHours ewh) {
        String respuesta = employeeWorkedHoursServImp.eliminar(ewh.getIdWorked());
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
