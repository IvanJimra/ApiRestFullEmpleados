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

import com.mx.ApiRestFullE.entity.Jobs;
import com.mx.ApiRestFullE.service.JobsServImp;

@RestController
@RequestMapping(path = "api/JobsWs")
@CrossOrigin
public class JobsWs {
	
	@Autowired
	JobsServImp jobsServImp;
	
	 // 1) Listar
	// GET -> http://localhost:9000/api/JobsWs/listar-jobs
    @GetMapping("listar-jobs")
    public List<Jobs> listar() {
        return jobsServImp.listar();
    }
    
    // 2) Buscar
    // POST -> http://localhost:9000/api/JobsWs/buscar-jobs
    @PostMapping("buscar-jobs")
    public Jobs buscar(@RequestBody Jobs j) {
        return jobsServImp.buscarPorId(j.getIdJobs());
    }
    
    // 3) Guardar
    // POST -> http://localhost:9000/api/JobsWs/guardar-jobs
    @PostMapping("guardar-jobs")
    public ResponseEntity<?> guardar(@RequestBody Jobs j) {
        String respuesta = jobsServImp.guardar(j);
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
    // 4) Editar
 // POST -> http://localhost:9000/api/JobsWs/editar-jobs
    @PostMapping("editar-jobs")
    public ResponseEntity<?> editar(@RequestBody Jobs j) {
        String respuesta = jobsServImp.editar(j);
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    // 5) Eliminar
 // POST -> http://localhost:9000/api/JobsWs/eliminar-jobs
    @PostMapping("eliminar-jobs")
    public ResponseEntity<?> eliminar(@RequestBody Jobs j) {
        String respuesta = jobsServImp.eliminar(j.getIdJobs());
        if (respuesta.startsWith("Error")) {
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
