package com.educacion.inedcuchilla.DTO;

public class AlumnoDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String genero;
    private String nombreGrado;
    private String especialidad;
    private char secicon;

    public AlumnoDTO(String nombre, String apellido, String email, String telefono, String genero, String nombreGrado, String especialidad, char secicon) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.genero = genero;
        this.nombreGrado = nombreGrado;
        this.especialidad = especialidad;
        this.secicon = secicon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public char getSecicon() {
        return secicon;
    }

    public void setSecicon(char secicon) {
        this.secicon = secicon;
    }
}
