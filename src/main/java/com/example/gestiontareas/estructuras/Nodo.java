package com.example.gestiontareas.estructuras;

public class Nodo<T>{
    public T data; //Dato almacenado
    public Nodo<T> siguiente; //Enlace al siguiente nodo

    public Nodo(T data) {
        this.data = data;
        this.siguiente = null;
    }
}
