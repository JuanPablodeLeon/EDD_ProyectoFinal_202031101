/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Objetos;

/**
 *
 * @author juanp
 */
public class Bibliotecas {
    
    private String id, nombre, ubicacion;
    private int tiempoIngreso, tiempoTraspaso, dispachInterval;

    public Bibliotecas(String id, String nombre, String ubicacion, int tiempoIngreso, int tiempoTraspaso, int dispachInterval) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tiempoIngreso = tiempoIngreso;
        this.tiempoTraspaso = tiempoTraspaso;
        this.dispachInterval = dispachInterval;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTiempoIngreso() {
        return tiempoIngreso;
    }

    public void setTiempoIngreso(int tiempoIngreso) {
        this.tiempoIngreso = tiempoIngreso;
    }

    public int getTiempoTraspaso() {
        return tiempoTraspaso;
    }

    public void setTiempoTraspaso(int tiempoTraspaso) {
        this.tiempoTraspaso = tiempoTraspaso;
    }

    public int getDispachInterval() {
        return dispachInterval;
    }

    public void setDispachInterval(int dispachInterval) {
        this.dispachInterval = dispachInterval;
    }

    @Override
    public String toString() {
        return "Bibliotecas{" + "id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", tiempoIngreso=" + tiempoIngreso + ", tiempoTraspaso=" + tiempoTraspaso + ", dispachInterval=" + dispachInterval + '}';
    }
    
    
}
