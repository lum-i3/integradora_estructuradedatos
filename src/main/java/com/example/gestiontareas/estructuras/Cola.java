package com.example.gestiontareas.estructuras;

public class Cola<T> {
    private Nodo<T> inicio; //Primer elemento de la cola
    private Nodo<T> fin; //Ultimo elemento de la cola
    private int size; //Numero de elementos

    public Cola(){
        inicio = null;
        fin = null;
        size = 0;
    }

    //Verifica si la cola esta vacia
    public boolean estaVacia(){
        return inicio == null;
    }

    //Inserta un elemento al final
    public void agregarElementos(T elemento){
        Nodo<T> nuevo = new Nodo<>(elemento);
        if(estaVacia()){
            inicio = nuevo;
        }
        else{
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        size++;
    }

    //Elimina y devuelve el primer elemento
    public T quitarElemento(){
        if(estaVacia()){
            System.out.println("No encontrado");
            return null;
        }
        T aux = inicio.data;
        inicio = inicio.siguiente;
        size--;
        if (inicio == null) {
            fin = null;
        }
        return aux;
    }

    //Devuelve el primer elemento sin eliminarlo
    public T verInicio(){
        if(estaVacia()){
            System.out.println("No encontrado");
            return null;
        }
        return inicio.data;
    }

    //Muestra todos los elementos de la cola
    public void mostrarElementos(){
        if(estaVacia()){
            System.out.println("No encontrado");
            return;
        }
        System.out.print("Elemento: ");
        Nodo<T> actual = inicio;
        while(actual != null){
            System.out.print(actual.data + " ");
            actual = actual.siguiente;
        }
        System.out.print("|");
    }

    //Vacía la cola completamente
    public void borrarElementos(){
        inicio = null;
        fin = null;
        size = 0;
    }

    //Elimina un elemento específico dentro de la cola
    public boolean eliminarElemento(T elemento) {
        if (estaVacia()) return false;

        // Caso especial: el primer nodo es el elemento
        if (inicio.data.equals(elemento)) {
            quitarElemento();
            return true;
        }

        Nodo<T> actual = inicio;
        Nodo<T> anterior = null;

        while (actual != null) {
            if (actual.data.equals(elemento)) {
                anterior.siguiente = actual.siguiente;

                // Si eliminamos el último, actualizar fin
                if (actual == fin) {
                    fin = anterior;
                }

                size--;
                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false; // no encontrado
    }


    public int TamanoCola(){
        return size;
    }
}
