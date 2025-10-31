/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Estructuras;

import Backend.Nodos.Nodo;
import Backend.Nodos.NodoHash;

/**
 *
 * @author juanp
 */
public class HashTable<K, V>{
    
    private Lista<NodoHash<K,V>>[] tabla;
    private int size;
    
    public HashTable(int size){
        this.size = size;
        
        tabla = new Lista[size];
        for (int i = 0; i < size; i++) {
            tabla[i] = new Lista<>();
        }
    }
    
    private int funcionHash(K clave){
        int sumaAscii = 0;
        String claveString = clave.toString();
        for (char c : claveString.toCharArray()) {
            sumaAscii += c;
        }
        return sumaAscii % size;
    }
    
    public boolean insertar(K clave, V valor) {
        if (claveDuplicada(clave)) {
            System.out.println("\nError, el elemento con la clave: " + clave + ", ya existe.");
            return false;
        }
        // obteniendo el indice del elemento
        int indice = funcionHash(clave);
        // agregando al elemento a la tabla con el indice que devolvio la funcion hash
        tabla[indice].insertar(new NodoHash<>(clave, valor));
        return true;
    }

    private boolean claveDuplicada(K clave) {
        int indice = funcionHash(clave);
        return buscarEnLista(tabla[indice], clave) != null;
    }

    public V buscar(K clave) {
        // obteniendo el indice del elemento a buscar
        int indice = funcionHash(clave);
        // buscando y retornando el elemento por la clave
        NodoHash<K, V> resultado = buscarEnLista(tabla[indice], clave);
        return resultado != null ? resultado.getValor() : null;
    }

    private NodoHash<K, V> buscarEnLista(Lista<NodoHash<K, V>> lista, K clave) {
        return lista.buscar(nodo -> nodo.getClave().equals(clave));
    }

    public boolean eliminar(K clave) {
        int indice = funcionHash(clave);
        Lista<NodoHash<K, V>> lista = tabla[indice];
        return lista.eliminar(nodo -> nodo.getClave().equals(clave));
    }

    public void mostrarElementos() {
        int numero = 0;
        System.out.println("\n=== MOSTRANDO TODOS LOS ELEMENTOS ===");
        for (int i = 0; i < size; i++) {
            Lista<NodoHash<K, V>> bucketActual = tabla[i];
            
            if (!bucketActual.estaVacio()) {
                System.out.println("\nBucket " + i + " (" + bucketActual.getSize() + " elementos):");
                
                Nodo<NodoHash<K, V>> actual = bucketActual.getPrimero();
                while (actual != null) {
                    numero++;
                    NodoHash<K, V> nodoHash = actual.getValor();
                    System.out.println(numero + ". Clave: " + nodoHash.getClave() + 
                                     " -> Valor: " + nodoHash.getValor());
                    actual = actual.getSiguiente();
                }
            }
        }
        
        if (numero == 0) {
            System.out.println("La tabla hash está vacía.");
        } else {
            System.out.println("\nTotal de elementos: " + numero);
        }
    }

    // Método para obtener todos los elementos como una lista
    public Lista<V> obtenerTodosLosElementos() {
        Lista<V> todos = new Lista<>();
        
        for (int i = 0; i < size; i++) {
            Lista<NodoHash<K, V>> bucketActual = tabla[i];
            
            if (!bucketActual.estaVacio()) {
                Nodo<NodoHash<K, V>> actual = bucketActual.getPrimero();
                while (actual != null) {
                    todos.insertar(actual.getValor().getValor());
                    actual = actual.getSiguiente();
                }
            }
        }
        return todos;
    }

    // Método para obtener todos los pares clave-valor
    public Lista<NodoHash<K, V>> obtenerTodosLosNodos() {
        Lista<NodoHash<K, V>> todos = new Lista<>();
        
        for (int i = 0; i < size; i++) {
            Lista<NodoHash<K, V>> bucketActual = tabla[i];
            
            if (!bucketActual.estaVacio()) {
                Nodo<NodoHash<K, V>> actual = bucketActual.getPrimero();
                while (actual != null) {
                    todos.insertar(actual.getValor());
                    actual = actual.getSiguiente();
                }
            }
        }
        return todos;
    }

    public int getSize() {
        return size;
    }
    
    public int getCantidadElementos(){
        
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += tabla[i].getSize();
        }
        return count;
    }
    
    public double getFactorCarga(){
        return (double) getCantidadElementos() / size;
    }
    
    public boolean estaVacia(){
        return getCantidadElementos() == 0;
    }
}
