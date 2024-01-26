package com.example.application.views.agregarciudadano;

import com.example.application.services.PersonaServices;
import com.example.application.views.MainLayout;
import com.example.application.Models.Persona;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@PageTitle("Agregar Ciudadano")
@Route(value = "Agregar-Ciudadano", layout = MainLayout.class)
public class AgregarCiudadanoView extends Composite<VerticalLayout> {

    private TextField textFieldNombres;
    private TextField textFieldId;
    private DatePicker datePickerFechaNacimiento;
    private TextField textFieldLugarNacimiento;
    private TextField textFieldEstadoCivil;
    private TextField textFieldProfesion;
    private Button buttonPrimary;
    private Button buttonSecondary;

    private PersonaServices personaServices;

    public AgregarCiudadanoView(PersonaServices personaServices) {
        this.personaServices = personaServices;

        H2 h2 = new H2("Agregar a un ciudadano");
        H4 h4 = new H4("Por favor rellenar todos los espacios solicitados:");
        FormLayout formLayout2Col = new FormLayout();

        textFieldNombres = new TextField("Nombres Completos:");
        textFieldId = new TextField("Cedula");
        datePickerFechaNacimiento = new DatePicker("Fecha de Nacimiento");
        textFieldLugarNacimiento = new TextField("Lugar de Nacimiento:");
        textFieldEstadoCivil = new TextField("Estado Civil:");
        textFieldProfesion = new TextField("Profesion:");
        buttonPrimary = new Button("Continuar", event -> agregarCiudadano());
        buttonSecondary = new Button("Cancelar", event -> personaServices.limpiarFormulario(this));

        formLayout2Col.add(
                textFieldNombres, textFieldId, datePickerFechaNacimiento,
                textFieldLugarNacimiento, textFieldEstadoCivil, textFieldProfesion
        );

        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");
        layout.add(h2, h4, formLayout2Col, buttonPrimary, buttonSecondary);
    }

    private void agregarCiudadano() {
        try {
            Persona nuevaPersona = new Persona();
            Binder<Persona> binder = new Binder<>(Persona.class);

            // Vincula automáticamente los campos de la vista a los campos de la entidad Persona
            binder.bindInstanceFields(this);

            if (binder.writeBeanIfValid(nuevaPersona)) {
                personaServices.agregarPersona(nuevaPersona);
                personaServices.refrescarPersonasRegistradas();
                personaServices.limpiarFormulario(this);
            } else {
                Notification.show("ERROR: Por favor, ingrese datos válidos en todos los campos.");
            }
        } catch (Exception e) {
            // Imprime la pila de excepciones para identificar la causa del error
            e.printStackTrace();
            Notification.show("ERROR: No se pudo agregar la persona. Consulta la consola para más detalles.");
        }
    }


    public TextField getTextFieldNombres() {
        return textFieldNombres;
    }

    public void setTextFieldNombres(TextField textFieldNombres) {
        this.textFieldNombres = textFieldNombres;
    }

    public TextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(TextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public DatePicker getDatePickerFechaNacimiento() {
        return datePickerFechaNacimiento;
    }

    public void setDatePickerFechaNacimiento(DatePicker datePickerFechaNacimiento) {
        this.datePickerFechaNacimiento = datePickerFechaNacimiento;
    }

    public TextField getTextFieldLugarNacimiento() {
        return textFieldLugarNacimiento;
    }

    public void setTextFieldLugarNacimiento(TextField textFieldLugarNacimiento) {
        this.textFieldLugarNacimiento = textFieldLugarNacimiento;
    }

    public TextField getTextFieldEstadoCivil() {
        return textFieldEstadoCivil;
    }

    public void setTextFieldEstadoCivil(TextField textFieldEstadoCivil) {
        this.textFieldEstadoCivil = textFieldEstadoCivil;
    }

    public TextField getTextFieldProfesion() {
        return textFieldProfesion;
    }

    public void setTextFieldProfesion(TextField textFieldProfesion) {
        this.textFieldProfesion = textFieldProfesion;
    }

    public Button getButtonPrimary() {
        return buttonPrimary;
    }

    public void setButtonPrimary(Button buttonPrimary) {
        this.buttonPrimary = buttonPrimary;
    }

    public Button getButtonSecondary() {
        return buttonSecondary;
    }

    public void setButtonSecondary(Button buttonSecondary) {
        this.buttonSecondary = buttonSecondary;
    }

    public PersonaServices getPersonaServices() {
        return personaServices;
    }

    public void setPersonaServices(PersonaServices personaServices) {
        this.personaServices = personaServices;
    }
}