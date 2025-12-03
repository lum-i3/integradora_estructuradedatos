package com.example.gestiontareas.estructuras;

public class Nodo<T>{
    public T data;
    public Nodo<T> siguiente;

    public Nodo(T data) {
        this.data = data;
        this.siguiente = null;
    }
}
