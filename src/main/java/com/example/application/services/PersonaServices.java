package com.example.application.services;

import com.example.application.Models.Persona;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServices {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServices(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaByCedula(String cedula) {
        return Optional.ofNullable(personaRepository.findByCedula(cedula));
    }

    public void agregarPersona(Persona persona) {
        personaRepository.save(persona);
        Notification.show("Persona agregada correctamente");
    }

    public void editarPersona(String cedula, Persona personaActualizada) {
        Persona personaExistente = personaRepository.findByCedula(cedula);
        if (personaExistente != null) {
            personaExistente.setNombre(personaActualizada.getNombre());
            // Actualiza otros campos según sea necesario
            personaRepository.save(personaExistente);
            Notification.show("Persona editada correctamente");
        } else {
            Notification.show("No se encontró la persona con la cédula proporcionada");
        }
    }

    public void eliminarPersona(String cedula) {
        Persona personaExistente = personaRepository.findByCedula(cedula);
        if (personaExistente != null) {
            personaRepository.delete(personaExistente);
            Notification.show("Persona eliminada correctamente");
        } else {
            Notification.show("No se encontró la persona con la cédula proporcionada");
        }
    }


    // Puedes agregar más métodos según tus necesidades
}
