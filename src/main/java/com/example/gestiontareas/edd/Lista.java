package com.example.gestiontareas.edd;

public class Lista<T> {
    private Nodo<T> head;
    private int size;

    public Lista() {
        this.head = null;
        size = 0;
    }

    //Agrega un nuevo elemento al inicio de la lista

    public void agregarInicio(T data) {
        Nodo<T> nuevo = new Nodo<>(data);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    //Agrega un nuevo elemento al final de la lista

    public void agregarFinal(T data) {
        Nodo<T> nuevo = new Nodo<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo<T> aux = head;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        size++;
    }

    //Elimina un elemento por posición

    public void eliminarTarea(int index) {
        if (head == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        if (index == 0) {
            head = head.siguiente;
            return;
        }

        Nodo<T> aux = head;
        for (int i = 0; aux != null && i < index - 1; i++) {
            aux = aux.siguiente;
        }

        if (aux == null || aux.siguiente == null) {
            System.out.println("Indice fuera del rango");
            return;
        }
        aux.siguiente = aux.siguiente.siguiente;
        size--;
    }

    public int tamanioLista(){
        if(estaVacia()){
            System.out.println("Pila Vacia");
            return 0;
        }
        return size;
    }

    //Muestra todos los elementos de la lista

    public void mostrarTarea() {
        Nodo<T> aux = head;
        System.out.println("Contenido de la lista:");
        while (aux != null) {
            System.out.println(aux.data);
            aux = aux.siguiente;
        }
        System.out.println();
    }

    //Devuelve el elemento en una posición específica.

    public T obtener(int index) {
        Nodo<T> aux = head;
        int contador = 0;
        while (aux != null) {
            if (contador == index) {
                return aux.data;
            }
            contador++;
            aux = aux.siguiente;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }

    //Verifica si la lista está vacía.

    public boolean estaVacia() {
        return head == null;
    }
}
