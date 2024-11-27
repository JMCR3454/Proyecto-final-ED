/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoED;

/**
 *
 * @author José Manuel
 */
public class Boleto {
    private String cliente;
    private String funcion;
    private int fila;
    private int columna;
    private int precio;

    public Boleto(String cliente, String funcion, int fila, int columna, int precio) {
        this.cliente = (cliente == null || cliente.isEmpty()) ? "Anónimo" : cliente;
        this.funcion = funcion;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente + ", Función: " + funcion +
               ", Asiento: Fila " + fila + ", Columna " + columna +
               ", Precio: $" + precio;
    }

    public String getFuncion() {
        return funcion;
    }

    public int getPrecio() {
        return precio;
    }
}