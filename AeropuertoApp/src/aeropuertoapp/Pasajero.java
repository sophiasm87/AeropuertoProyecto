/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuertoapp;
import javax.swing.JOptionPane;

/**
 *
 * @author sophi
 */


public class Pasajero {

    private String nombre;
    private String identificacion;
    private String nivelSocio; // Platino, Oro, Regular
    private String comidaEspecial; // Vegetariano, Kosher, Sin Gluten, Estándar

    public Pasajero(String nombre, String identificacion, String nivelSocio, String comidaEspecial) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.nivelSocio = nivelSocio;
        this.comidaEspecial = comidaEspecial;
    }

    public void mostrarInfo() {
        JOptionPane.showMessageDialog(null,
                "Pasajero:\nNombre: " + nombre
                + "\nIdentificación: " + identificacion
                + "\nNivel de socio: " + nivelSocio
                + "\nComida especial: " + comidaEspecial);
    }

    public double aplicarDescuento(double precioBase) {
        double precioFinal = precioBase;

        if (nivelSocio.equalsIgnoreCase("Platino")) {
            precioFinal = precioBase * 0.90; // 10% descuento
        } else if (nivelSocio.equalsIgnoreCase("Oro")) {
            precioFinal = precioBase * 0.95; // 5% descuento
        } else {
            precioFinal = precioBase; // Regular, sin descuento
        }

        return precioFinal;
    }

    public String getComidaEspecial() {
        return comidaEspecial;
    }

    public String getNombre() {
        return nombre;
    }
}
