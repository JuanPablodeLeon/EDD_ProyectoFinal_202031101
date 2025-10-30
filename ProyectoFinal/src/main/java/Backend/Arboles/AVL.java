/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Arboles;

import Backend.Nodos.AVLNodo;
import Backend.Objetos.Libro;

/**
 *
 * @author juanp
 */
public class AVL {
    
    private AVLNodo raiz;
    
    public AVL(){
        raiz = null;
    }
    /*----- Metodos Publicos ------*/
    
    public void insertar(Libro libro){
        raiz = insertar(raiz, libro);
    }
    
    public void eliminar(String titulo){
        raiz = eliminar(raiz, titulo);
    }
    
    public boolean buscar(String titulo){
        return buscar(raiz, titulo);
    }
    
    public Libro buscarLibro(String titulo){
        return busquedaLibro(raiz, titulo);
    }
    
    public void imprimir(){
        imprimirPriv(raiz);
        System.out.println();
    }
    
    public boolean estaVacio(){
        return raiz == null;
    }
    
    /*----- Metodos Recursivos -----*/
    
    private AVLNodo rotacionDerecha(AVLNodo dato){
        
        AVLNodo nodo = dato.izquierda;
        AVLNodo nodo1 = dato.derecha;
        
        nodo.derecha = dato;
        dato.izquierda = nodo1;
        
        dato.altura = calculoAltura(altura(dato.izquierda), altura(dato.derecha));
        nodo.altura = calculoAltura(altura(nodo.izquierda), altura(nodo.derecha));

        return nodo;
    }
    
    private AVLNodo rotacionIzquierda(AVLNodo dato){
        
        AVLNodo nodo = dato.derecha;
        AVLNodo nodo1 = nodo.izquierda;
        
        nodo.izquierda = dato;
        dato.derecha = nodo1;
        
        dato.altura = calculoAltura(altura(dato.izquierda), altura(dato.derecha));
        nodo.altura = calculoAltura(altura(nodo.izquierda), altura(nodo.derecha));
        
        return nodo;
    }
    
    private AVLNodo insertar(AVLNodo nodo, Libro libro){
        
        if (nodo == null) return new AVLNodo(libro);
        
        String libroLower = conversionLower(libro.getTitulo());
        String nodoLower = conversionLower(nodo.libro.getTitulo());
        
        if (libroLower.compareTo(nodoLower) < 0) {
            nodo.izquierda = insertar(nodo.izquierda, libro);
        }else if(libroLower.compareTo(nodoLower) > 0){
            nodo.derecha = insertar(nodo.derecha, libro);
        }else{
            nodo.izquierda = insertar(nodo.izquierda, libro);
        }
        
        nodo.altura = calculoAltura(altura(nodo.izquierda), altura(nodo.derecha));
        
        int balance = factorEquilibrio(nodo);
        
        //Izquierda Izquierda
        if (balance > 1 && libroLower.compareTo(conversionLower(nodo.izquierda.libro.getTitulo())) < 0) {
            return rotacionDerecha(nodo);
        }
        
        //Derecha derecha
        if (balance < -1 && libroLower.compareTo(conversionLower(nodo.derecha.libro.getTitulo())) > 0) {
            return rotacionIzquierda(nodo);
        }
        
        //Izquierda derecha
        if (balance > 1 && libroLower.compareTo(conversionLower(nodo.izquierda.libro.getTitulo())) > 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        
        //Derecha izquierda
        if (balance < -1 && libroLower.compareTo(conversionLower(nodo.derecha.libro.getTitulo())) < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }
        
        return nodo;
    }
    
    private AVLNodo eliminar(AVLNodo raiz, String titulo){
        
        if(raiz == null) return raiz;
        
        String tituloLower = conversionLower(titulo);
        String nodoLower = conversionLower(raiz.libro.getTitulo());
        
        if (tituloLower.compareTo(nodoLower) < 0) {
            raiz.izquierda = eliminar(raiz.izquierda, titulo);
        }else if(tituloLower.compareTo(nodoLower) > 0){
            raiz.derecha = eliminar(raiz.derecha, titulo);
        }else{
            if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if(raiz.derecha == null){
                return raiz.izquierda;
            } else{
                AVLNodo temp = valorMinimo(raiz.derecha);
                raiz.libro = temp.libro;
                raiz.derecha = eliminar(raiz.derecha, temp.libro.getTitulo());
            }
        }
        
        if (raiz == null) return raiz;
        
        raiz.altura = calculoAltura(altura(raiz.izquierda), altura(raiz.derecha));
        
        int balance = factorEquilibrio(raiz);
        
        //Izquierda izquierda
        if (balance > 1 && factorEquilibrio(raiz.izquierda) >= 0) {
            return rotacionDerecha(raiz);
        }
        
        //Izquierda derecha
        if (balance > 1 && factorEquilibrio(raiz.izquierda) < 0) {
            raiz.izquierda = rotacionIzquierda(raiz.izquierda);
            return rotacionDerecha(raiz);
        }
        
        //Derecha derecha
        if (balance < -1 && factorEquilibrio(raiz.derecha) <= 0) {
            return rotacionIzquierda(raiz);
        }
        
        //Derecha izquierda
        if (balance < -1 && factorEquilibrio(raiz.derecha) > 0) {
            raiz.derecha = rotacionDerecha(raiz.derecha);
            return rotacionIzquierda(raiz);
        }
        
        return raiz;
    }
    
    private void imprimirPriv(AVLNodo raiz){
        if (raiz != null) {
            imprimirPriv(raiz.izquierda);
            raiz.libro.toString();
            imprimirPriv(raiz.derecha);
        }
    }

    private boolean buscar(AVLNodo raiz, String titulo){
        if( raiz == null) return false;
        
        String raizLower = conversionLower(raiz.libro.getTitulo());
        String tituloLower = conversionLower(titulo);
        
        if (raizLower.equals(tituloLower)) {
            return true;
        }
        
        if(tituloLower.compareTo(raizLower) < 0){
            return buscar(raiz.izquierda, titulo);
        }
        
        return buscar(raiz.derecha, titulo);
    }
    
    private Libro busquedaLibro(AVLNodo raiz, String titulo){
        if (raiz == null) return null;
        
        String raizLower = conversionLower(raiz.libro.getTitulo());
        String tituloLower = conversionLower(titulo);
        
        if (raizLower.equals(tituloLower)) {
            return raiz.libro;
        }
        
        if (tituloLower.compareTo(raizLower) < 0) {
            return busquedaLibro(raiz.izquierda, titulo);
        }
        return busquedaLibro(raiz.derecha, titulo);
    }
    
    private AVLNodo valorMinimo(AVLNodo nodo){
        
        AVLNodo temp = nodo;
        while (temp.izquierda != null) {
            temp = temp.izquierda;
        }
        return temp;
    }
    
    private String conversionLower(String titulo) {
        return titulo.toLowerCase();
    }
    
    private int altura(AVLNodo nodo){
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }
    
    private int factorEquilibrio(AVLNodo nodo){
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }
    
    //En caso de no funcionar quitarlo
    private int calculoAltura(int izquierda, int derecha){
        return Math.max(izquierda, derecha) +1;
    }
    
}
