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

import com.mx.ApiRestFullE.entity.Genders;
import com.mx.ApiRestFullE.service.GendersServImp;

@RestController
@RequestMapping(path = "api/GendersWs")
@CrossOrigin
public class GendersWs {

	@Autowired
	GendersServImp gendersServImp;
	
	
	 // 1) Listar
	// GET -> http://localhost:9000/api/GendersWs/listar-genders
    @GetMapping(path = "listar-genders")
    public List<Genders> listar() {
        return gendersServImp.listar();
    }
    
    // 2) Busca
    // POST -> http://localhost:9000/api/GendersWs/buscar-genders
    @PostMapping(path = "buscar-genders")
    public Genders buscar(@RequestBody Genders g) {
        return gendersServImp.buscarPorId(g.getIdGenders());
    }
    
    // 3) Guardar
    // POST -> http://localhost:9000/api/GendersWs/guardar-genders
    @PostMapping(path = "guardar-genders")
    public ResponseEntity<?> guardar(@RequestBody Genders g) {
        String respuesta = gendersServImp.guardar(g);
        
        if (respuesta.startsWith("Error")) 
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
    // 4) Editar
    // POST -> http://localhost:9000/api/GendersWs/editar-genders
    @PostMapping(path = "editar-genders")
    public ResponseEntity<?> editar(@RequestBody Genders g) {
        String respuesta = gendersServImp.editar(g);
        
        if (respuesta.startsWith("Error")) 
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    // 5) Eliminar
    // POST -> http://localhost:9000/api/GendersWs/eliminar-genders
    @PostMapping(path = "eliminar-genders")
    public ResponseEntity<?> eliminar(@RequestBody Genders g) {
        String respuesta = gendersServImp.eliminar(g.getIdGenders());
        
        if (respuesta.startsWith("Error")) 
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
	
}
