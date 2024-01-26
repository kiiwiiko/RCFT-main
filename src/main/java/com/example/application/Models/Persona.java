package com.example.application.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
    public class Persona {

        @Id
        private String id;
        @Indexed(name = "cedula", unique = true)
        private String cedula;
        private String nombre;
        private LocalDate fechaNacimiento;
        private String lugarNacimiento;
        private String estadoCivil;
        private String profesion;

        // Constructor
        public Persona(String cedula, String nombre, LocalDate fechaNacimiento,
                       String lugarNacimiento, String estadoCivil, String profesion) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.lugarNacimiento = lugarNacimiento;
            this.estadoCivil = estadoCivil;
            this.profesion = profesion;
        }

        public Persona() {}

        // Getters
        public String getCedula() {
            return cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }

        public String getLugarNacimiento() {
            return lugarNacimiento;
        }

        public String getEstadoCivil() {
            return estadoCivil;
        }

        public String getProfesion() {
            return profesion;
        }

        // Setters
        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public void setLugarNacimiento(String lugarNacimiento) {
            this.lugarNacimiento = lugarNacimiento;
        }

        public void setEstadoCivil(String estadoCivil) {
            this.estadoCivil = estadoCivil;
        }

        public void setProfesion(String profesion) {
            this.profesion = profesion;
        }
        @Override
        public String toString() {
            return "Persona " +
                    "Cédula=" + cedula  +
                    ", Nombre=" + nombre  +
                    ", Fecha de Nacimiento=" + (fechaNacimiento != null ? fechaNacimiento.toString() : "N/A") +
                    ", Lugar de Nacimiento=" + lugarNacimiento  +
                    ", Estado Civil=" + estadoCivil  +
                    ", Profesión=" + profesion
                    ;
    }
    }
