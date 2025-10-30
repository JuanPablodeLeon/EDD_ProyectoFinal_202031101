/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Nodos;

import Backend.Objetos.Libro;

/**
 *
 * @author juanp
 */
public class AVLNodo {
    
    public Libro libro;
    public AVLNodo izquierda;
    public AVLNodo derecha;
    public int altura;

    public AVLNodo(Libro libro) {
        this.libro = libro;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1;
    }
    
    
    
}
