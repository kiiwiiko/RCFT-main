package com.example.application.views.listaPersonas;

import com.example.application.Models.Persona;
import com.example.application.services.PersonaServices;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.List;


@PageTitle("Lista de Personas Registradas")
@Route(value = "Lista-Personas", layout = MainLayout.class)
public class ListaPersonasView extends Composite<VerticalLayout> {

    private PersonaServices personaServices;
    private Grid<Persona> gridPersonasRegistradas;

    public ListaPersonasView(PersonaServices personaServices) {
        this.personaServices = personaServices;

        H2 h2 = new H2("Lista de Personas Registradas");
        H4 h4 = new H4("Personas registradas hasta ahora:");

        gridPersonasRegistradas = new Grid<>(Persona.class);
        gridPersonasRegistradas.setColumns("cedula", "nombre", "fechaNacimiento", "lugarNacimiento", "estadoCivil", "profesion");

        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");
        layout.add(h2, h4, gridPersonasRegistradas);

        refrescarPersonasRegistradas();
    }

    private void refrescarPersonasRegistradas() {
        List<Persona> personasRegistradas = personaServices.obtenerPersonasRegistradas();
        gridPersonasRegistradas.setItems(personasRegistradas);
    }
}