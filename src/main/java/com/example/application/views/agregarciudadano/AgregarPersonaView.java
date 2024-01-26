package com.example.application.views.agregarciudadano;

import com.example.application.services.PersonaServices;
import com.example.application.Models.Persona;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@PageTitle("Agregar Persona")
@Route(value = "agregar-persona", layout = MainLayout.class)
@Uses(Icon.class)
public class AgregarPersonaView extends VerticalLayout implements HasUrlParameter<String> {

    private final PersonaServices personaServices;
    private final TextField cedulaField = new TextField("Cédula");
    private final TextField nombreField = new TextField("Nombre");
    private final TextField direccionField = new TextField("Fecha Nacimiento");
    private final TextField telefonoField = new TextField("Teléfono");
    private final Button guardarButton = new Button("Guardar");
    private String cedula;

    public AgregarPersonaView(PersonaServices personaServices) {
        this.personaServices = personaServices;

        guardarButton.addClickListener(e -> guardarPersona());

        add(cedulaField, nombreField, direccionField, telefonoField, guardarButton);
    }

    private void guardarPersona() {
        String cedula = cedulaField.getValue();
        String nombre = nombreField.getValue();
        String direccion = direccionField.getValue();
        String telefono = telefonoField.getValue();

        if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            Notification.show("Completa todos los campos");
            return;
        }

        Persona persona = new Persona(cedula, nombre, null, null, direccion, telefono);

        if (this.cedula != null) {
            personaServices.editarPersona(this.cedula, persona);
        } else {
            personaServices.agregarPersona(persona);
        }

        limpiarCampos();
        Notification.show("Persona guardada correctamente");
    }

    private void limpiarCampos() {
        cedulaField.clear();
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String cedula) {
        this.cedula = cedula;
        if (cedula != null) {
            cedulaField.setValue(cedula);
            // Cargar otros valores de la persona según la cédula y establecerlos en los campos correspondientes
        }
    }
}