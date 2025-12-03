package com.example.gestiontareas.model;

public class Tarea {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String descripcion;
    private boolean completada;
    private String prioridad;

    public Tarea(String titulo, String descripcion,  String prioridad) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = false;
        this.prioridad = prioridad;
    }

    public Tarea() {
    }

    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    //toString

    @Override
    public String toString() {
        return "Tarea " + id + "\n" +
                "Titulo: " + titulo + "\n" +
                "Descripcion: " + descripcion + "\n" +
                "Completada: " + completada + "\n" +
                "Prioridad: " + prioridad + "\n";
    }
}
