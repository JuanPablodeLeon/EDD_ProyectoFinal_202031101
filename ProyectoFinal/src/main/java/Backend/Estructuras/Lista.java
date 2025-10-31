package Backend.Estructuras;

import Backend.Nodos.Nodo;

public class Lista<T>{

    private Nodo<T> primero;
    private int size;
    
    public Lista(){
        primero = null;
        size = 0;
    }
    
    public void insertar(T valor){
        Nodo<T> nuevo = new Nodo<>(valor);
        
        if (primero == null) {
            primero = nuevo;
        }else{
            Nodo<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        size++;
    }
    
    //Modificar para poder mostrar en front
    public void imprimir(){
        if (primero == null) {
            System.out.println("Lista Vacia");
            return;
        }
        
        Nodo<T> actual = primero;
        for (int i = 0; i < size; i++) {
            System.out.println(actual.getValor());
            System.out.println("----------");
            actual = actual.getSiguiente();
            
        }
    }
    
    public T buscar(java.util.function.Predicate<T> criterio){
        if (primero == null) return null;
        
        Nodo<T> actual = primero;
        while (actual != null) {
            if (criterio.test(actual.getValor())) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public boolean eliminar(java.util.function.Predicate<T> criterio){
        
        if(primero == null) return false;
        
        if (criterio.test(primero.getValor())) {
            Nodo<T> temp = primero;
            primero = primero.getSiguiente();
            temp = null;
            size--;
            return true;
        }
        
        Nodo<T> actual = primero;
        while (actual.getSiguiente() != null) {
            if (criterio.test(actual.getSiguiente().getValor())) {
                Nodo<T> temp = actual.getSiguiente();
                actual.setSiguiente(temp.getSiguiente());
                temp = null;
                size--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean estaVacio(){
        return primero == null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public int getSize() {
        return size;
    }
    
    
}