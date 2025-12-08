package com.example.gestiontareas.estructuras;

public class Pila <T>{
    private Nodo<T> cima; //Ultimo elemento agregado
    private int size; //Numero de elementos

    public Pila() {
        cima = null;
        size = 0;
    }

    //Inserta elemento en la cima
    public void Insertar(T valor) {
        Nodo <T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = cima;
        cima = nuevo;
        size++;
    }

    //Indica si la pila esta vacia
    public boolean PilaVacia() {
        return cima == null;
    }

    //Elimina y devuelve el elemento superior
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

    //Devuelve el dato en la cima sin retirarlo
    public T CimaPila(){
        if(PilaVacia()){
            System.out.println("Pila Vacia");
            return null;
        }
        else{
            return (T) cima.data;
        }
    }

    //Vacia la pila
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

    //Muestra los elementos desde la cima hasta el fondo
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

    //Elimina un elemento específico de la pila
    public boolean eliminar(T elemento) {
        if (PilaVacia()) return false;

        Pila<T> auxiliar = new Pila<>();

        boolean encontrado = false;
        //Mover elementos a pila auxiliar
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

    //Mostrar historial
    public Object[] obtenerArreglo() {
        if (PilaVacia()) return new Object[0];
        Object[] temp = new Object[size];
        Nodo<T> aux = cima;
        int i = 0;
        while (aux != null) {
            temp[i++] = aux.data;
            aux = aux.siguiente;
        }
        return temp;
    }

}
