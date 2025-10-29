/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Objetos;

/**
 *
 * @author juanp
 */
public class Conexiones {
    
    private String idOrigen, idDestino;
    private int tiempo;
    private double costo;

    public Conexiones(String idOrigen, String idDestino, int tiempo, double costo) {
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.tiempo = tiempo;
        this.costo = costo;
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

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Conexiones{" + "idOrigen=" + idOrigen + ", idDestino=" + idDestino + ", tiempo=" + tiempo + ", costo=" + costo + '}';
    }
    
}
