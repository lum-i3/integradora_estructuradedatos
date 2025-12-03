package com.example.gestiontareas.estructuras;

import com.example.gestiontareas.model.Tarea;

public class NodoArbol {
    public Tarea tarea;
    public NodoArbol izq;
    public NodoArbol der;

    public NodoArbol(Tarea tarea) {
        this.tarea = tarea;
        this.izq = null;
        this.der = null;
    }
}