/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Objetos;

import Backend.Estructuras.Lista;
import java.util.Objects;

/**
 *
 * @author juanp
 */
public class Libro {
    
    //Posible modificacio para los enums
    private String titulo, ISBN, genero, fecha, autor, estado, prioridad;
    private Lista<Libro> listaRepetidos;

    public Libro(String titulo, String ISBN, String genero, String fecha, String autor, String estado, String prioridad) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.genero = genero;
        this.fecha = fecha;
        this.autor = autor;
        this.estado = estado;
        this.prioridad = prioridad;
        this.listaRepetidos = new Lista<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Lista<Libro> getListaRepetidos() {
        return listaRepetidos;
    }

    @Override
    public String toString() {
        return "|| " + titulo + " || " + ISBN + " || " + genero + " || " + fecha + " || " + autor + " || " + estado + " || " + prioridad + " ||";
    }
    
    public int conversionInt(){
        return Integer.parseInt(fecha);
    }

    public boolean estadoDiponible(){
        return "disponible".equalsIgnoreCase(estado);
    }
    
    public boolean estadoAgotado(){
        return "agotado".equalsIgnoreCase(estado);
    }
    
    public boolean estadoTransito(){
        return "transito".equalsIgnoreCase(estado);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return Objects.equals(titulo, libro.titulo) &&
               Objects.equals(ISBN, libro.ISBN) &&
               Objects.equals(genero, libro.genero) &&
               Objects.equals(fecha, libro.fecha) &&
               Objects.equals(autor, libro.autor) &&
               Objects.equals(estado, libro.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, ISBN, genero, fecha, autor, estado);
    }
}
