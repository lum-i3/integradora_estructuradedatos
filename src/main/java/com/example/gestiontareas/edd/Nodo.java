package com.example.gestiontareas.edd;

public class Nodo<T>{
    public T data;
    public Nodo<T> siguiente;

    public Nodo(T data) {
        this.data = data;
        this.siguiente = null;
    }
}
