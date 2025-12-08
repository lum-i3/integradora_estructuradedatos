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

    //Estructuras de datos usadas en memoria
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
        //Buscar tarea en lista
        for (int i = 0; i < listaTareas.tamanioLista(); i++) {
            Tarea t = listaTareas.obtener(i);
            if (t.getId() == id) {
                //Eliminar de BD
                tareaRepository.deleteById(id);
                //Eliminar de estructuras
                listaTareas.eliminarTarea(i);
                arbolTareas.eliminar(t.getTitulo());
                colaPendientes.eliminarElemento(t);
                historialPila.Insertar(t); //Registrar la accion en el historial

                return true;
            }
        }
        return false;
    }

    public boolean editarTarea(Tarea editada) {
        //Buscar tarea original
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
        //Guardar en el historial
        historialPila.Insertar(original);

        return true;
    }

    public boolean completarTarea(int id) {
        //Buscar tarea
        Tarea tarea = buscarPorId(id);
        if (tarea == null) return false;
        //Marcar completada y actualizar BD
        tarea.setCompletada(true);
        tareaRepository.save(tarea);

        // Eliminar de cola de pendientes
        colaPendientes.eliminarElemento(tarea);
        // Registrar acción en historial
        historialPila.Insertar(tarea);

        return true;
    }

    //Buscar tarea recorriendo la lista enlazada
    public Tarea buscarPorId(int id) {
        for (int i = 0; i < listaTareas.tamanioLista(); i++) {
            Tarea t = listaTareas.obtener(i);
            if (t.getId() == id) return t;
        }
        return null;
    }

    //Buscar por coincidencia parcial en título usando el arbol
    // devuelve Tarea[] para el controller
    public Tarea[] buscarPorTitulo(String texto) {
        Lista<Tarea> resultadosLista = arbolTareas.buscarPorFraccion(texto);
        return resultadosLista.obtenerArregloTareas();
    }

    //Convertir lista enlazada a arreglo para devolverse al controller
    public Object[] obtenerTodas() {
        return listaTareas.obtenerArreglo();
    }

    //Orden ascendente por prioridad
    public Tarea[] ordenarAsc() {
        Tarea[] tareas = arbolTareas.inordenLista(); // NO importa el orden base
        ordenarPorPrioridadAsc(tareas);
        return tareas;
    }

    //Orden descendente por prioridad
    public Tarea[] ordenarDesc() {
        Tarea[] tareas = arbolTareas.inordenLista();
        ordenarPorPrioridadDesc(tareas);
        return tareas;
    }

    //Asignar valor numerico a la prioridad
    private int prioridadValor(String prioridad) {
        if (prioridad == null) return 0;

        switch (prioridad.toLowerCase()) {
            case "alta": return 3;
            case "media": return 2;
            case "baja": return 1;
            default: return 0;
        }
    }

    //Bubble sort ascendente
    private void ordenarPorPrioridadAsc(Tarea[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                int p1 = prioridadValor(arr[j].getPrioridad());
                int p2 = prioridadValor(arr[j + 1].getPrioridad());

                if (p1 > p2) {
                    Tarea temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //Bubble sort descendente
    private void ordenarPorPrioridadDesc(Tarea[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                int p1 = prioridadValor(arr[j].getPrioridad());
                int p2 = prioridadValor(arr[j + 1].getPrioridad());

                if (p1 < p2) {
                    Tarea temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public boolean descompletarTarea(int id) {
        //Buscar tarea
        Tarea tarea = buscarPorId(id);
        if (tarea == null) return false;

        tarea.setCompletada(false);
        tareaRepository.save(tarea);
        //Regresar a la cola de pendientes
        colaPendientes.agregarElementos(tarea);
        //Quitar del historial
        historialPila.eliminar(tarea);

        return true;
    }

    //Atender siguiente tarea en orden FIFO
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

    //Recargar estructuras desde la BD
    public void sincronizarConBD() {
        listaTareas = new Lista<>();
        arbolTareas = new ArbolBinario();
        colaPendientes = new Cola<>();
        cargarTareasDesdeDB();
    }
}