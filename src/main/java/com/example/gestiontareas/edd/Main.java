package com.example.gestiontareas.edd;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Lista<Tarea> lista = new Lista<>();
        Pila<Tarea> historial = new Pila<>();

        Tarea t1 = new Tarea("Mapa mental", "Realizar un mapa mental", "Media");
        Tarea t2 = new Tarea("Estudiar", "Estudiar para el examen de matemáticas", "Alta");
        Tarea t3 = new Tarea("Ensayo", "Escribir ensayo sobre el renacimiento", "Baja");
        Tarea t4 = new Tarea("Cartel", "Imprimir cartel de igualdad de género", "Media");

        gestor.agregarTarea(t1, lista);
        historial.Insertar(t1);

        gestor.agregarTarea(t2, lista);
        historial.Insertar(t2);

        gestor.agregarTarea(t3, lista);
        historial.Insertar(t3);

        gestor.agregarTarea(t4, lista);
        historial.Insertar(t4);

        System.out.println("===== LISTA DE TAREAS INICIAL =====");
        lista.mostrarTarea();

        System.out.println("\n===== HISTORIAL DE TAREAS AGREGADAS (PILA) =====");
        historial.mostrarPila();

        System.out.println("\n===== COLAS POR PRIORIDAD =====");
        gestor.mostrarColas();

        System.out.println("\n===== DESHACER (2 ACCIONES) =====");
        for (int i = 0; i < 2; i++) {
            if (!historial.PilaVacia()) {
                Tarea tareaDeshecha = historial.Quitar();
                int tamano = lista.tamanioLista();
                lista.eliminarTarea(tamano - 1);
                System.out.println("Acción deshecha: se eliminó '" + tareaDeshecha.getTitulo() + "'");
            }
        }

        System.out.println("\n===== ESTADO DESPUÉS DE DESHACER =====");
        System.out.println("Pila:");
        historial.mostrarPila();
        System.out.println("Lista:");
        lista.mostrarTarea();

        System.out.println("\n===== COMPLETANDO TAREAS =====");
        gestor.completarSiguienteTarea(); // debería completar una de prioridad alta
        gestor.completarSiguienteTarea(); // siguiente según prioridad

        System.out.println("\n===== ESTADO FINAL =====");
        gestor.mostrarColas();
        System.out.println("\nLista final:");
        lista.mostrarTarea();
    }
}
