package com.example.gestiontareas.estructuras;
import com.example.gestiontareas.model.Tarea;

public class ArbolBinario {

    private NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // Insertar por título
    public void insertar(Tarea tarea) {
        raiz = insertarRec(raiz, tarea);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Tarea tarea) {
        if (nodo == null) {
            return new NodoArbol(tarea);
        }

        if (tarea.getTitulo().compareToIgnoreCase(nodo.tarea.getTitulo()) < 0) {
            nodo.izq = insertarRec(nodo.izq, tarea);
        } else {
            nodo.der = insertarRec(nodo.der, tarea);
        }

        return nodo;
    }

    // devuelve una Lista<Tarea> con las coincidencias parciales (case-insensitive)
    public Lista<Tarea> buscarPorFraccion(String texto) {
        Lista<Tarea> resultados = new Lista<>();
        if (texto == null || texto.isEmpty()) return resultados;
        buscarPorFraccionRec(raiz, texto.toLowerCase(), resultados);
        return resultados;
    }

    private void buscarPorFraccionRec(NodoArbol nodo, String texto, Lista<Tarea> resultados) {
        if (nodo == null) return;

        buscarPorFraccionRec(nodo.izq, texto, resultados);

        if (nodo.tarea != null && nodo.tarea.getTitulo() != null &&
                nodo.tarea.getTitulo().toLowerCase().contains(texto)) {
            resultados.agregarFinal(nodo.tarea);
        }

        buscarPorFraccionRec(nodo.der, texto, resultados);
    }

    public void eliminar(String titulo) {
        raiz = eliminarRec(raiz, titulo);
    }

    private NodoArbol eliminarRec(NodoArbol nodo, String titulo) {
        if (nodo == null) return null;

        int cmp = titulo.compareToIgnoreCase(nodo.tarea.getTitulo());

        if (cmp < 0) {
            nodo.izq = eliminarRec(nodo.izq, titulo);
        } else if (cmp > 0) {
            nodo.der = eliminarRec(nodo.der, titulo);
        } else {
            // caso 1: sin hijos
            if (nodo.izq == null && nodo.der == null)
                return null;

            // caso 2: un hijo
            if (nodo.izq == null) return nodo.der;
            if (nodo.der == null) return nodo.izq;

            // caso 3: dos hijos → obtener sucesor
            NodoArbol sucesor = obtenerMinimo(nodo.der);
            nodo.tarea = sucesor.tarea;
            nodo.der = eliminarRec(nodo.der, sucesor.tarea.getTitulo());
        }

        return nodo;
    }

    private NodoArbol obtenerMinimo(NodoArbol nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
        return nodo;
    }

    public Tarea[] inordenLista() {
        int total = contarNodos(raiz);
        Tarea[] arreglo = new Tarea[total];
        llenarInorden(raiz, arreglo, new int[]{0});
        return arreglo;
    }

    private int contarNodos(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izq) + contarNodos(nodo.der);
    }

    private void llenarInorden(NodoArbol nodo, Tarea[] arr, int[] i) {
        if (nodo != null) {
            llenarInorden(nodo.izq, arr, i);
            arr[i[0]++] = nodo.tarea;
            llenarInorden(nodo.der, arr, i);
        }
    }

    public Tarea[] inordenListaInvertida() {
        int total = contarNodos(raiz);
        Tarea[] arreglo = new Tarea[total];
        llenarInordenDesc(raiz, arreglo, new int[]{0});
        return arreglo;
    }

    private void llenarInordenDesc(NodoArbol nodo, Tarea[] arr, int[] i) {
        if (nodo != null) {
            llenarInordenDesc(nodo.der, arr, i);
            arr[i[0]++] = nodo.tarea;
            llenarInordenDesc(nodo.izq, arr, i);
        }
    }

    public void inorden() {
        inordenRec(raiz);
    }

    private void inordenRec(NodoArbol nodo) {
        if (nodo != null) {
            inordenRec(nodo.izq);
            System.out.println(nodo.tarea);
            inordenRec(nodo.der);
        }
    }
}
