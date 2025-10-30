/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Arboles;

import Backend.Nodos.NodoB;
import Backend.Objetos.Libro;

/**
 *
 * @author juanp
 */
public class B {
    
    private NodoB raiz;
    private int t;
    
    public B(int _t){
        raiz = null;
        t = _t;
    }
    
    public void insertar(Libro libro) {
        int fechaInt = libro.conversionInt();
        
        if (raiz == null) {
            raiz = new NodoB(t, true);
            raiz.getLibros()[0] = libro;
            raiz.getFechas()[0] = fechaInt;
        } else {
            if (raiz.getN() == 2 * t - 1) {
                NodoB s = new NodoB(t, false);
                s.getC()[0] = raiz;
                s.splitChild(0, raiz);
                
                int i = 0;
                if (s.getFechas()[0] < fechaInt)
                    i++;
                s.getC()[i].insertNonFull(libro);
                raiz = s;
            } else {
                raiz.insertNonFull(libro);
            }
        }
    }

    public void eliminarLibro(int año, String ISBN, String estado) {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        boolean eliminado = raiz.eliminarLibro(año, ISBN, estado);
        
        if (eliminado) {
            System.out.println("Libro eliminado exitosamente");
            if (raiz.getN() == 0) {
                if (raiz.isHoja()) {
                    raiz = null;
                } else {
                    raiz = raiz.getC()[0];
                }
            }
        } else {
            System.out.println("No se encontró el libro con los criterios especificados");
        }
    }

    public void eliminar(int fecha) {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        raiz.remove(fecha);
        
        if (raiz.getN() == 0) {
            if (raiz.isHoja()) {
                raiz = null;
            } else {
                raiz = raiz.getC()[0];
            }
        }
    }

    public void imprimir() {
        if (raiz != null) {
            System.out.println("=== LISTA COMPLETA DE LIBROS ===");
            raiz.imprimir();
        } else {
            System.out.println("El árbol está vacío");
        }
    }

    public void showRange(int low, int high) {
        if (raiz != null) {
            System.out.print("Libros en el rango de fechas [" + low + ", " + high + "]: ");
            raiz.showRange(low, high);
            System.out.println();
        } else {
            System.out.println("El árbol está vacío");
        }
    }

    public void showRangeDetailed(int low, int high) {
        if (raiz != null) {
            System.out.println("=== LIBROS EN EL RANGO [" + low + " - " + high + "] ===");
            raiz.showRangeDetailed(low, high);
        } else {
            System.out.println("El árbol está vacío");
        }
    }
}
