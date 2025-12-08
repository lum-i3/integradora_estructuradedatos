package com.example.gestiontareas.estructuras;

public class Pila <T>{
    private Nodo<T> cima;
    private int size;

    public Pila() {
        cima = null;
        size = 0;
    }

    public void Insertar(T valor) {
        Nodo <T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = cima;
        cima = nuevo;
        size++;
    }

    public boolean PilaVacia() {
        return cima == null;
    }

    public T Quitar(){
        if (PilaVacia()) {
            System.out.println("Pila Vacia");
            return null;
        }
        T aux = cima.data;
        cima = cima.siguiente;
        size--;
        return aux;
    }

    public T CimaPila(){
        if(PilaVacia()){
            System.out.println("Pila Vacia");
            return null;
        }
        else{
            return (T) cima.data;
        }
    }

    public void LimpiarPila(){
        System.out.println("La Pila esta Vacia");
        cima = null;
        size = 0;
    }

    public int tamanioPila(){
        if(PilaVacia()){
            System.out.println("Pila Vacia");
            return 0;
        }
        return size;
    }

    public void mostrarPila(){
        if(PilaVacia()){
            System.out.println("Pila Vacia");
        }
        else {
            Nodo <T> aux = cima;
            System.out.println("Pila: ");
            while(aux != null){
                System.out.println(aux.data + " ");
                aux = aux.siguiente;
            }
            System.out.println();
        }
    }

    public boolean eliminar(T elemento) {
        if (PilaVacia()) return false;

        Pila<T> auxiliar = new Pila<>();

        boolean encontrado = false;

        while (!PilaVacia()) {
            T temp = Quitar();

            if (temp.equals(elemento) && !encontrado) {
                encontrado = true; // no lo regresamos
            } else {
                auxiliar.Insertar(temp);
            }
        }

        // Regresar los elementos a la pila original
        while (!auxiliar.PilaVacia()) {
            Insertar(auxiliar.Quitar());
        }

        return encontrado;
    }

}
