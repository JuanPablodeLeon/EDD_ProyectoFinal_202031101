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
public class Cola<T> {
    
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int size;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public void insertar(T dato){
        
        Nodo<T> nuevo = new Nodo<>(dato);
        
        if (ultimo == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        size++;
    }
    
    public T extraer(){
        
        if (primero == null)  return null;
        
        Nodo<T> temp = primero;
        T valor_extraido = temp.getValor();
        primero = primero.getSiguiente();
        
        if (primero == null) ultimo = null;
        
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
    
    public T verPrimero(){
        if(primero == null) return null;
        
        return primero.getValor();
    }
    
    public boolean estaVacia(){
        return primero == null;
    }

    public int getSize() {
        return size;
    }
    
   
}
