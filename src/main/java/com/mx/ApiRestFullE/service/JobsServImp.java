package com.mx.ApiRestFullE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestFullE.entity.Jobs;
import com.mx.ApiRestFullE.repository.JobsRepository;

@Service
public class JobsServImp {
	
	@Autowired
	JobsRepository jobsRepository;
	
	 // 1) Listar
    @Transactional(readOnly = true)
    public List<Jobs> listar() {
        return jobsRepository.findAll();
    }
    
    // 2) Buscar por ID
    @Transactional(readOnly = true)
    public Jobs buscarPorId(Integer id) {
        return jobsRepository.findById(id).orElse(null);
    }
    
    // 3) Guardar
    public String guardar(Jobs job) {
    	
        if (jobsRepository.existsById(job.getIdJobs())) 
            return "Error: El ID " + job.getIdJobs() + " ya existe en la tabla JOBS.";
        
        
        jobsRepository.save(job);
        return "Puesto guardado correctamente.";
    }
    
    // 4) Editar
    public String editar(Jobs job) {
    	
        if (!jobsRepository.existsById(job.getIdJobs())) 
            return "Error: El puesto con ID " + job.getIdJobs() + " no existe, no se puede editar.";
        
        jobsRepository.save(job);
        return "Puesto con ID " + job.getIdJobs() + " editado correctamente.";
    }
    
    // 5) Eliminar
    public String eliminar(Integer id) {
    	
        if (!jobsRepository.existsById(id)) 
            return "Error: El puesto con ID " + id + " no existe, no se puede eliminar.";
        
        jobsRepository.deleteById(id);
        return "Puesto con ID " + id + " eliminado correctamente.";
    }

}
