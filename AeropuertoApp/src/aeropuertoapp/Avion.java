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




public class Avion {

    private String matricula;
    private String modelo;
    private int capacidad;
    private boolean asiento1;
    private boolean asiento2;
    private boolean asiento3;

    public Avion(String matricula, String modelo, int capacidad) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.asiento1 = false;
        this.asiento2 = false;
        this.asiento3 = false;
    }

    public void mostrarInfo() {
        String estadoAsiento1 = asiento1 ? "Ocupado" : "Libre";
        String estadoAsiento2 = asiento2 ? "Ocupado" : "Libre";
        String estadoAsiento3 = asiento3 ? "Ocupado" : "Libre";

        JOptionPane.showMessageDialog(null,
                "Avión:\nMatrícula: " + matricula
                + "\nModelo: " + modelo
                + "\nCapacidad: " + capacidad + " pasajeros"
                + "\nEstado de asientos:\n1: " + estadoAsiento1
                + "\n2: " + estadoAsiento2
                + "\n3: " + estadoAsiento3);
    }

    public void elegirAsiento() {
        String estadoAsiento1 = asiento1 ? "Ocupado" : "Libre";
        String estadoAsiento2 = asiento2 ? "Ocupado" : "Libre";
        String estadoAsiento3 = asiento3 ? "Ocupado" : "Libre";

        String opcion = JOptionPane.showInputDialog(
                "Elige número de asiento (1, 2, 3):\n"
                + "1: " + estadoAsiento1 + "\n"
                + "2: " + estadoAsiento2 + "\n"
                + "3: " + estadoAsiento3);

        if (opcion.equals("1")) {
            if (!asiento1) {
                asiento1 = true;
                JOptionPane.showMessageDialog(null, "Asiento 1 reservado.");
            } else {
                JOptionPane.showMessageDialog(null, "El asiento 1 ya está ocupado.");
            }
        } else if (opcion.equals("2")) {
            if (!asiento2) {
                asiento2 = true;
                JOptionPane.showMessageDialog(null, "Asiento 2 reservado.");
            } else {
                JOptionPane.showMessageDialog(null, "El asiento 2 ya está ocupado.");
            }
        } else if (opcion.equals("3")) {
            if (!asiento3) {
                asiento3 = true;
                JOptionPane.showMessageDialog(null, "Asiento 3 reservado.");
            } else {
                JOptionPane.showMessageDialog(null, "El asiento 3 ya está ocupado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número inválido.");
        }
    }

    public double calcularOcupacion() {
        int ocupados = 0;
        if (asiento1) {
            ocupados++;
        }
        if (asiento2) {
            ocupados++;
        }
        if (asiento3) {
            ocupados++;
        }
        return (ocupados * 100.0) / 3.0;
    }

    public String reporteOcupacion() {
        int ocupados = 0;
        int libres = 0;

        if (asiento1) {
            ocupados++;
        } else {
            libres++;
        }
        if (asiento2) {
            ocupados++;
        } else {
            libres++;
        }
        if (asiento3) {
            ocupados++;
        } else {
            libres++;
        }

        double porcentaje = (ocupados * 100.0) / 3.0;

        return "Ocupación del avión:\n"
                + "Asientos ocupados: " + ocupados + "\n"
                + "Asientos libres: " + libres + "\n"
                + "Porcentaje total: " + porcentaje + "%";
    }
}
