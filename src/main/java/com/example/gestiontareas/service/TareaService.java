package com.example.gestiontareas.service;

import com.example.gestiontareas.model.Tarea;
import com.example.gestiontareas.estructuras.Lista;
import com.example.gestiontareas.estructuras.Pila;
import com.example.gestiontareas.estructuras.Cola;
import com.example.gestiontareas.estructuras.ArbolBinario;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private Lista<Tarea> listaTareas = new Lista<>();
    private Pila<Tarea> historialPila = new Pila<>();
    private Cola<Tarea> colaPendientes = new Cola<>();
    private ArbolBinario arbolTareas = new ArbolBinario();

    public void agregarTarea(Tarea tarea) {
        listaTareas.agregarFinal(tarea);
        historialPila.Insertar(tarea);
        colaPendientes.agregarElementos(tarea);
        arbolTareas.insertar(tarea);
    }

    public boolean eliminarTarea(int id) {

        for (int i = 0; i < listaTareas.tamanioLista(); i++) {
            Tarea t = listaTareas.obtener(i);

            if (t.getId() == id) {

                listaTareas.eliminarTarea(i);
                arbolTareas.eliminar(t.getTitulo());
                colaPendientes.eliminarElemento(t); // debes implementar eliminarElemento en tu cola

                historialPila.Insertar(t);

                return true;
            }
        }
        return false;
    }

    public boolean editarTarea(Tarea editada) {

        Tarea original = buscarPorId(editada.getId());
        if (original == null) return false;

        // quitar del árbol antes de modificar
        arbolTareas.eliminar(original.getTitulo());

        original.setTitulo(editada.getTitulo());
        original.setDescripcion(editada.getDescripcion());
        original.setPrioridad(editada.getPrioridad());

        // reinserción en árbol
        arbolTareas.insertar(original);

        historialPila.Insertar(original);
        return true;
    }

    public Tarea buscarPorId(int id) {
        for (int i = 0; i < listaTareas.tamanioLista(); i++) {
            Tarea t = listaTareas.obtener(i);
            if (t.getId() == id) return t;
        }
        return null;
    }

    public Tarea buscarPorTitulo(String titulo) {
        return arbolTareas.buscar(titulo);
    }

    public Object[] obtenerTodas() {
        return listaTareas.obtenerArreglo();
    }

    public Tarea[] ordenarAsc() {
        return arbolTareas.inordenLista();
    }

    public Tarea[] ordenarDesc() {
        return arbolTareas.inordenListaInvertida();
    }

    public Tarea atenderSiguiente() {
        return colaPendientes.quitarElemento();
    }

    public int pendientesEnCola() {
        return colaPendientes.TamanoCola();
    }

    public Tarea ultimaAccion() {
        return historialPila.CimaPila();
    }

    public Tarea deshacerAccion() {
        return historialPila.Quitar();
    }
}
