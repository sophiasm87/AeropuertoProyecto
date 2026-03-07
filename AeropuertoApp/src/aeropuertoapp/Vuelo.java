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


public class Vuelo {

    private String codigo;
    private String destino;
    private Avion avion;

    public Vuelo(String codigo, String destino, Avion avion) {
        this.codigo = codigo;
        this.destino = destino;
        this.avion = avion;
    }

    public void mostrarInfo(String horaSalida, String horaLlegada, String estado) {
        JOptionPane.showMessageDialog(null,
                "Vuelo:\nCódigo: " + codigo
                + "\nDestino: " + destino
                + "\nHora salida: " + horaSalida
                + "\nHora llegada: " + horaLlegada
                + "\nEstado: " + estado);
        avion.mostrarInfo();
    }

    public void elegirAsientoVuelo() {
        avion.elegirAsiento();
    }

    public Avion getAvion() {
        return avion;
    }
}
