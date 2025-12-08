package com.example.gestiontareas.model;

import java.time.LocalDateTime;

public class HistorialAccion {

    private String accion;     // Crear, Editar, Completar, Eliminar, etc.
    private Tarea tarea;       // La tarea afectada
    private LocalDateTime fecha; // Cuándo ocurrió

    public HistorialAccion(String accion, Tarea tarea) {
        this.accion = accion;
        this.tarea = tarea;
        this.fecha = LocalDateTime.now();
    }

    public String getAccion() {
        return accion;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
