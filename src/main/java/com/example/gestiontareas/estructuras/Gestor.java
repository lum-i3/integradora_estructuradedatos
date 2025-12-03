package com.example.gestiontareas.estructuras;

import com.example.gestiontareas.model.Tarea;

public class Gestor {
    private Cola<Tarea> colaAlta = new Cola<>();
    private Cola<Tarea> colaMedia = new Cola<>();
    private Cola<Tarea> colaBaja = new Cola<>();

    public void agregarTarea(Tarea tarea, Lista lista) {
        lista.agregarFinal(tarea);
        switch (tarea.getPrioridad().toLowerCase()) {
            case "alta":
                colaAlta.agregarElementos(tarea);
                break;
            case "media":
                colaMedia.agregarElementos(tarea);
                break;
            case "baja":
                colaBaja.agregarElementos(tarea);
                break;
        }
    }

    public Tarea completarSiguienteTarea() {
        Tarea tarea = null;

        if (!colaAlta.estaVacia()) {
            tarea = colaAlta.quitarElemento();
        } else if (!colaMedia.estaVacia()) {
            tarea = colaMedia.quitarElemento();
        } else if (!colaBaja.estaVacia()) {
            tarea = colaBaja.quitarElemento();
        }

        if (tarea != null) {
            tarea.setCompletada(true);
            System.out.println("Tarea completada: " + tarea.getTitulo());
        } else {
            System.out.println("No hay tareas pendientes");
        }
        return tarea;
    }

    public void mostrarColas() {
        System.out.println("\nTareas por prioridad");
        System.out.print("Alta: ");
        colaAlta.mostrarElementos();
        System.out.print("\nMedia: ");
        colaMedia.mostrarElementos();
        System.out.print("\nBaja: ");
        colaBaja.mostrarElementos();
        System.out.println();
    }
}

