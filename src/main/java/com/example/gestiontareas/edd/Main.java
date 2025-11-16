package com.example.gestiontareas.edd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gestor gestor = new Gestor();
        Lista<Tarea> lista = new Lista<>();
        Pila<Tarea> historial = new Pila<>();

        boolean continuar = true;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Ver Lista");
            System.out.println("3. Ver Prioridades (Colas)");
            System.out.println("4. Ver Historial (Pila)");
            System.out.println("5. Completar Tarea");
            System.out.println("6. Deshacer");
            System.out.println("7. Buscar por Indice");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descripcion: ");
                    String desc = sc.nextLine();
                    System.out.print("Prioridad (Alta/Media/Baja): ");
                    String prio = sc.nextLine();

                    Tarea t = new Tarea(titulo, desc, prio);
                    gestor.agregarTarea(t, lista);
                    historial.Insertar(t);
                    break;

                case 2:
                    lista.mostrarTarea();
                    break;

                case 3:
                    gestor.mostrarColas();
                    break;

                case 4:
                    historial.mostrarPila();
                    break;

                case 5:
                    gestor.completarSiguienteTarea();
                    break;

                case 6:
                    if (!historial.PilaVacia()) {
                        Tarea eliminada = historial.Quitar();
                        int tamano = lista.tamanioLista();
                        lista.eliminarTarea(tamano - 1);
                        System.out.println("Se deshizo: " + eliminada.getTitulo());
                    } else {
                        System.out.println("Nada que deshacer.");
                    }
                    break;

                case 7:
                    System.out.print("Indice (1, 2...): ");
                    int idx = sc.nextInt();
                    // Restamos 1 porque la lista empieza en 0
                    try {
                        Tarea encontrada = lista.obtener(idx - 1);
                        System.out.println("Encontrada: " + encontrada.getTitulo());
                    } catch (Exception e) {
                        System.out.println("Indice no valido.");
                    }
                    break;

                case 0:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcion incorrecta");
            }

        } while (continuar);
    }
}