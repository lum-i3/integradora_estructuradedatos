package com.example.gestiontareas.edd;

public class Main {
    public static void main(String[] args) {
        Pila<Tarea> historial = new Pila<>();

        Tarea t1 = new Tarea(
                "Mapa mental",
                "Realizar un mapa mental",
                "Media"
        );
        Tarea t2 = new Tarea(
                "Estudiar",
                "Estudiar para el examen de matematicas",
                "Alta"
        );
        Tarea t3 = new Tarea(
                "Ensayo",
                "Escribir ensayo sobre el renacimiento",
                "Baja"
        );
        Tarea t4 = new Tarea(
                "Cartel",
                "Imprimir cartel de igualdad de genero",
                "Media"
        );

        Lista<Tarea> lista = new Lista<>();


        lista.agregarInicio(t1);
        historial.Insertar(t1);

        lista.agregarFinal(t2);
        historial.Insertar(t2);

        lista.agregarFinal(t3);
        historial.Insertar(t3);

        lista.agregarFinal(t4);
        historial.Insertar(t4);

        System.out.println("Lista de Tareas Inicial");
        lista.mostrar();
        System.out.println("\nHistorial de Tareas Agregadas (Pila)");
        System.out.println("Elementos en la Pila: ");
        historial.mostrarPila();


        System.out.println("\nDeshacer");

        if (!historial.PilaVacia()) {
            Tarea tareaDeshecha = historial.Quitar();
            int tamano = lista.tamanioLista();
            lista.eliminar(tamano-1);
            System.out.println("Acción Deshecha: Se registró la adición de '" + tareaDeshecha.getTitulo() + "'.");
        }

        if (!historial.PilaVacia()) {
            Tarea tareaDeshecha = historial.Quitar();
            int tamano = lista.tamanioLista();
            lista.eliminar(tamano-1);
            System.out.println("Acción Deshecha: Se registró la adición de '" + tareaDeshecha.getTitulo() + "'.");
        }

        System.out.println("\nEstado de la Pila después de 2 Deshacer");
        System.out.println("Elementos restantes en la Pila: ");
        historial.mostrarPila();
        System.out.println("Elementos restantes en la Lista: ");
        lista.mostrar();

    }
}
