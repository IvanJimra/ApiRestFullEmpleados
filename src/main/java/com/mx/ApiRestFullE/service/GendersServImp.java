package com.mx.ApiRestFullE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestFullE.entity.Genders;
import com.mx.ApiRestFullE.repository.GendersRepository;

@Service
public class GendersServImp {
	
	@Autowired
	GendersRepository gendersRepository;
	
	//// 1) Listar
    @Transactional(readOnly = true)
    public List<Genders> listar() {
        return gendersRepository.findAll();
    }
    
    // 2) Buscar por ID
    @Transactional(readOnly = true)
    public Genders buscarPorId(Integer id) {
        return gendersRepository.findById(id).orElse(null);
    }
    
    // 3) Guardar
    public String guardar(Genders gender) {
        if (gendersRepository.existsById(gender.getIdGenders())) 
            return "Error: El ID " + gender.getIdGenders() + " ya existe en la tabla de Genders.";
        
        
        gendersRepository.save(gender);
        return "Género guardado correctamente.";
    }
    
    // 4) Editar
    public String editar(Genders gender) {
    	
        if (!gendersRepository.existsById(gender.getIdGenders())) 
            return "Error: El género con ID " + gender.getIdGenders() + " no existe, no se puede editar.";
        
        gendersRepository.save(gender);
        return "Género con ID " + gender.getIdGenders() + " editado correctamente.";
    }
    
    // 5) Eliminar
    public String eliminar(Integer id) {
    	
        if (!gendersRepository.existsById(id)) 
            return "Error: El género con ID " + id + " no existe, no se puede eliminar.";
        
        gendersRepository.deleteById(id);
        return "Género con ID " + id + " eliminado correctamente.";
    }

}
