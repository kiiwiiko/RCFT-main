package com.example.application.services;

import com.example.application.Models.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona, String> {
    public Persona findByCedula(String cedula);
    public  Persona findByNombre(String nombre);
    public Persona findByCedulaAndNombre(String cedula, String nombre);

}
