package com.example.gestiontareas.service;

import com.example.gestiontareas.model.Tarea;
import com.example.gestiontareas.repository.TareaRepository;
import com.example.gestiontareas.estructuras.Lista;
import com.example.gestiontareas.estructuras.Pila;
import com.example.gestiontareas.estructuras.Cola;
import com.example.gestiontareas.estructuras.ArbolBinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    private Lista<Tarea> listaTareas = new Lista<>();
    private Pila<Tarea> historialPila = new Pila<>();
    private Cola<Tarea> colaPendientes = new Cola<>();
    private ArbolBinario arbolTareas = new ArbolBinario();

    // Cargar tareas de la BD al iniciar
    @PostConstruct
    public void cargarTareasDesdeDB() {
        List<Tarea> tareasBD = tareaRepository.findAll();
        for (Tarea tarea : tareasBD) {
            listaTareas.agregarFinal(tarea);
            arbolTareas.insertar(tarea);
            if (!tarea.isCompletada()) {
                colaPendientes.agregarElementos(tarea);
            }
        }
    }

    public void agregarTarea(Tarea tarea) {
        // Guardar en BD primero
        Tarea tareaGuardada = tareaRepository.save(tarea);

        // Agregar a estructuras de datos
        listaTareas.agregarFinal(tareaGuardada);
        historialPila.Insertar(tareaGuardada);
        colaPendientes.agregarElementos(tareaGuardada);
        arbolTareas.insertar(tareaGuardada);
    }

    public boolean eliminarTarea(int id) {
        for (int i = 0; i < listaTareas.tamanioLista(); i++) {
            Tarea t = listaTareas.obtener(i);

            if (t.getId() == id) {
                // Eliminar de BD
                tareaRepository.deleteById(id);

                // Eliminar de estructuras
                listaTareas.eliminarTarea(i);
                arbolTareas.eliminar(t.getTitulo());
                colaPendientes.eliminarElemento(t);
                historialPila.Insertar(t);

                return true;
            }
        }
        return false;
    }

    public boolean editarTarea(Tarea editada) {
        Tarea original = buscarPorId(editada.getId());
        if (original == null) return false;

        // Quitar del árbol antes de modificar
        arbolTareas.eliminar(original.getTitulo());

        // Actualizar valores
        original.setTitulo(editada.getTitulo());
        original.setDescripcion(editada.getDescripcion());
        original.setPrioridad(editada.getPrioridad());
        original.setCompletada(editada.isCompletada());

        // Guardar en BD
        tareaRepository.save(original);

        // Reinsertar en árbol
        arbolTareas.insertar(original);
        historialPila.Insertar(original);

        return true;
    }

    public boolean completarTarea(int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) return false;

        tarea.setCompletada(true);
        tareaRepository.save(tarea);

        // Eliminar de cola de pendientes
        colaPendientes.eliminarElemento(tarea);
        historialPila.Insertar(tarea);

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
        Tarea tarea = colaPendientes.quitarElemento();
        if (tarea != null) {
            tarea.setCompletada(true);
            tareaRepository.save(tarea);
        }
        return tarea;
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

    public void sincronizarConBD() {
        listaTareas = new Lista<>();
        arbolTareas = new ArbolBinario();
        colaPendientes = new Cola<>();
        cargarTareasDesdeDB();
    }
}