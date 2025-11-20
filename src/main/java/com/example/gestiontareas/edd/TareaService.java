package com.example.gestiontareas.edd;

import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private Lista<Tarea> listaTareas = new Lista<>();
    private Cola<Tarea> colaPrioridad = new Cola<>();
    private Pila<Tarea> pilaHistorial = new Pila<>();

    public void agregarTarea(Tarea t) {
        listaTareas.agregarFinal(t);
        colaPrioridad.agregarElementos(t);
    }

    public Tarea[] obtenerTareas() {
        Object[] arr = listaTareas.obtenerArreglo();
        Tarea[] tareas = new Tarea[arr.length];

        for (int i = 0; i < arr.length; i++) {
            tareas[i] = (Tarea) arr[i];
        }
        return tareas;
    }

    public void eliminarTarea(int index) {
        Tarea t = listaTareas.obtener(index);
        pilaHistorial.Insertar(t);
        listaTareas.eliminarTarea(index);
    }

    public Tarea[] ordenarPorPrioridad(boolean asc) {
        Tarea[] tareas = obtenerTareas();

        // Ordenamiento burbuja manual
        for (int i = 0; i < tareas.length - 1; i++) {
            for (int j = 0; j < tareas.length - i - 1; j++) {
                int p1 = prioridadValor(tareas[j].getPrioridad());
                int p2 = prioridadValor(tareas[j + 1].getPrioridad());

                boolean condicion = asc ? p1 > p2 : p1 < p2;

                if (condicion) {
                    Tarea temp = tareas[j];
                    tareas[j] = tareas[j + 1];
                    tareas[j + 1] = temp;
                }
            }
        }

        return tareas;
    }

    private int prioridadValor(String p) {
        if (p.equalsIgnoreCase("Alta")) return 1;
        if (p.equalsIgnoreCase("Media")) return 2;
        return 3;
    }

    public Tarea obtenerPorIndex(int index) {
        return listaTareas.obtener(index);
    }

    public void actualizarTarea(int index, Tarea nueva) {
        Tarea actual = listaTareas.obtener(index);

        actual.setTitulo(nueva.getTitulo());
        actual.setDescripcion(nueva.getDescripcion());
        actual.setPrioridad(nueva.getPrioridad());
    }

    public Tarea[] buscar(String texto) {
        Object[] arr = listaTareas.obtenerArreglo();
        Lista<Tarea> resultados = new Lista<>();

        for (Object o : arr) {
            if (o == null) continue;

            Tarea t = (Tarea) o;

            if ((t.getTitulo() != null && t.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                    || (t.getDescripcion() != null && t.getDescripcion().toLowerCase().contains(texto.toLowerCase()))) {

                resultados.agregarFinal(t);
            }
        }

        // convertir a Tarea[]
        Object[] encontrados = resultados.obtenerArregloTareas();
        Tarea[] tareas = new Tarea[encontrados.length];

        for (int i = 0; i < encontrados.length; i++) {
            tareas[i] = (Tarea) encontrados[i];
        }

        return tareas;
    }
}
