package com.example.gestiontareas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Identificador único autoincremental

    @Column(nullable = false)
    private String titulo; //Título de la tarea

    @Column(length = 500)
    private String descripcion; //Descripción de tarea

    @Column(nullable = false)
    private boolean completada; //Boolean para completar

    @Column(nullable = false)
    private String prioridad; //Nivel de prioridad

    //Constructor de nuevas tareas
    public Tarea(String titulo, String descripcion, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = false;
        this.prioridad = prioridad;
    }

    //Constructor vacío obligatorio para JPA
    public Tarea() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    //Representación en texto para depuración
    @Override
    public String toString() {
        return "Tarea " + id + "\n" +
                "Titulo: " + titulo + "\n" +
                "Descripcion: " + descripcion + "\n" +
                "Completada: " + completada + "\n" +
                "Prioridad: " + prioridad + "\n";
    }
}