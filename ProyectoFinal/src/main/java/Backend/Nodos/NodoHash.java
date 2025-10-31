/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Nodos;

/**
 *
 * @author juanp
 */
public class NodoHash<K,V> {
    
    private K clave;
    private V valor;

    public NodoHash(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "NodoHash{" + "clave=" + clave + ", valor=" + valor + '}';
    }
    
    
}
