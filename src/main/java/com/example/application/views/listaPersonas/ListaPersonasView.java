package com.example.application.views.listaPersonas;

import com.example.application.Models.Persona;
import com.example.application.services.PersonaServices;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.List;

@PageTitle("Lista Personas")
@Route(value = "lista-personas", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaPersonasView extends Composite<VerticalLayout> {

    private final PersonaServices personaServices;

    public ListaPersonasView(PersonaServices personaServices) {
        this.personaServices = personaServices;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<Persona> grid = new Grid<>(Persona.class, false);
        grid.addColumn(Persona::getCedula).setHeader("Cédula").setAutoWidth(true);
        grid.addColumn(Persona::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Persona::getDireccion).setHeader("Fecha").setAutoWidth(true);
        grid.addColumn(Persona::getTelefono).setHeader("Teléfono").setAutoWidth(true);

        grid.addComponentColumn(persona -> {
            Button editarButton = new Button(new Icon(VaadinIcon.EDIT));
            editarButton.addClickListener(e -> navigateToEditarPersona(persona.getCedula()));
            return editarButton;
        }).setHeader("Editar").setAutoWidth(true);

        grid.addComponentColumn(persona -> {
            Button eliminarButton = new Button(new Icon(VaadinIcon.TRASH));
            eliminarButton.addClickListener(e -> eliminarPersona(persona));
            return eliminarButton;
        }).setHeader("Eliminar").setAutoWidth(true);

        // Obtener la lista de personas y establecer en la tabla
        grid.setItems(personaServices.getAllPersonas());

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        getContent().add(grid);
    }

    private void navigateToEditarPersona(String cedula) {
        UI.getCurrent().navigate("agregar-persona/" + cedula);
    }

    private void eliminarPersona(Persona persona) {
        personaServices.eliminarPersona(persona.getCedula());
        updateList();
        Notification.show("Persona eliminada correctamente");
    }

    private void updateList() {
        Grid<Persona> grid = getContent().getChildren()
                .filter(component -> component instanceof Grid)
                .map(component -> (Grid<Persona>) component)
                .findFirst().orElse(null);

        if (grid != null) {
            grid.setItems(personaServices.getAllPersonas());
        }
    }
}