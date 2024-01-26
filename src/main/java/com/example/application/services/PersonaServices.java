package com.example.application.services;

import com.example.application.Models.Persona;
import com.example.application.views.agregarciudadano.AgregarCiudadanoView;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServices {

    PersonaRepository personaRepository;

    public PersonaServices(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        try {
            personas = personaRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERROR: No se a encontrado una base de datos.");
        }
        return personas;
    }

    public void agregarPersona(Persona persona) {
        try {
            // Verificar si la información necesaria está presente
            if (esInformacionValida(persona)) {
                // Guardar la nueva persona sin verificar duplicados
                personaRepository.save(persona);
                Notification.show("Ciudadano agregado con éxito.");
                System.out.println("Nuevo Ciudadano agregado correctamente.");
            } else {
                System.out.println("ERROR: Información incompleta. Por favor, ingrese todos los campos obligatorios.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo guardar la persona.");
        }
    }

    private boolean esInformacionValida(Persona persona) {
        // Verificar que la información necesaria esté presente
        return persona != null
                && persona.getNombre() != null
                && !persona.getNombre().isEmpty()
                && persona.getCedula() != null
                && !persona.getCedula().isEmpty();
    }

    public List<Persona> obtenerPersonasRegistradas() {
        return new ArrayList<>(getAllPersonas());
    }
    public List<Persona> refrescarPersonasRegistradas() {
        return obtenerPersonasRegistradas();
    }

    // Añade este método para limpiar el formulario
    public void limpiarFormulario(AgregarCiudadanoView agregarCiudadanoView) {
        // Puedes reiniciar los campos del formulario o tomar otras acciones según tu lógica
        agregarCiudadanoView.getTextFieldNombres().clear();
        agregarCiudadanoView.getTextFieldId().clear();
        agregarCiudadanoView.getDatePickerFechaNacimiento().clear();
        agregarCiudadanoView.getTextFieldLugarNacimiento().clear();
        agregarCiudadanoView.getTextFieldEstadoCivil().clear();
        agregarCiudadanoView.getTextFieldProfesion().clear();
    }
    }

