/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuertoapp;
import javax.swing.JOptionPane;

public class Pasajero {

    private String nombre;
    private String identificacion;
    private String nivelSocio;    // Platino, Oro, Regular
    private String comidaEspecial; // Vegetariano, Kosher, Sin Gluten, Estándar

    public Pasajero(String nombre, String identificacion, String nivelSocio, String comidaEspecial) {
        this.nombre        = nombre;
        this.identificacion = identificacion;
        this.nivelSocio    = nivelSocio;
        this.comidaEspecial = comidaEspecial;
    }

    // ── Getters (algunos existentes, otros nuevos para la GUI) 
    public String getNombre()         { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getNivelSocio()     { return nivelSocio; }
    public String getComidaEspecial() { return comidaEspecial; }

    public void mostrarInfo() {
        JOptionPane.showMessageDialog(null,
                "Pasajero:\nNombre: "           + nombre
                + "\nIdentificación: "           + identificacion
                + "\nNivel de socio: "           + nivelSocio
                + "\nComida especial: "          + comidaEspecial);
    }

    public double aplicarDescuento(double precioBase) {
        if (nivelSocio.equalsIgnoreCase("Platino")) return precioBase * 0.90;
        if (nivelSocio.equalsIgnoreCase("Oro"))     return precioBase * 0.95;
        return precioBase;
    }
}