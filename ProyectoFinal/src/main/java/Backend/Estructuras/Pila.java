/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Estructuras;

import Backend.Nodos.Nodo;

/**
 *
 * @author juanp
 */
public class Pila<T> {
    
    private Nodo<T> primero;
    private int size;
    
    public Pila(){
        this.primero = null;
        this.size = 0;
    }
    
    public void insertar(T dato){
        
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        size++;
    }
    
    public T extraer(){
        
        if (primero == null) return null;
        
        Nodo<T> temp = primero;
        T valor_extraido = temp.getValor();
        primero = primero.getSiguiente();
        size--;
        return valor_extraido;
    }
    
    public void imprimir(){
        
        Nodo<T> temp = primero;
        while (temp != null) {
            System.out.println(temp.getValor());
            temp = temp.getSiguiente();
        }
    }
    
    public T verCima(){
        
        if (primero == null) return null;
        
        return primero.getValor();
    }
    
    public boolean estaVacia(){
        return primero == null;
    }

    public int getSize() {
        return size;
    }
    
    
}
