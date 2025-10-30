/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Objetos;

/**
 *
 * @author juanp
 */
public class Libro {
    
    //Posible modificacio para los enums
    private String titulo, ISBN, genero, fecha, autor, estado, idOrigen, idDestino, prioridad;

    public Libro(String titulo, String ISBN, String genero, String fecha, String autor, String estado, String idOrigen, String idDestino, String prioridad) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.genero = genero;
        this.fecha = fecha;
        this.autor = autor;
        this.estado = estado;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.prioridad = prioridad;
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

    public String getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(String idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "|| " + titulo + " || " + ISBN + " || " + genero + " || " + fecha + " || " + autor + " || " + estado + " || " + idOrigen + " || " + idDestino + " || " + prioridad + " ||";
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
}
