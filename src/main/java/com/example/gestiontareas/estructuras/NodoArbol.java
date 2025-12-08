package com.example.gestiontareas.estructuras;

import com.example.gestiontareas.model.Tarea;

public class NodoArbol {
    public Tarea tarea; //Contenido del nodo
    public NodoArbol izq; //Hijo izquierdo
    public NodoArbol der; //Hijo derecho

    public NodoArbol(Tarea tarea) {
        this.tarea = tarea;
        this.izq = null;
        this.der = null;
    }
}