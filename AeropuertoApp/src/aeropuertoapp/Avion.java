/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuertoapp;
import javax.swing.JOptionPane;

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

    // ── Getters nuevos (necesarios para la GUI) ──────────────
    public String getMatricula() { return matricula; }
    public String getModelo()    { return modelo; }
    public int    getCapacidad() { return capacidad; }

    public void mostrarInfo() {
        String e1 = asiento1 ? "Ocupado" : "Libre";
        String e2 = asiento2 ? "Ocupado" : "Libre";
        String e3 = asiento3 ? "Ocupado" : "Libre";

        JOptionPane.showMessageDialog(null,
                "Avión:\nMatrícula: " + matricula
                + "\nModelo: " + modelo
                + "\nCapacidad: " + capacidad + " pasajeros"
                + "\nEstado de asientos:\n1: " + e1
                + "\n2: " + e2
                + "\n3: " + e3);
    }

    public void elegirAsiento() {
        String e1 = asiento1 ? "Ocupado" : "Libre";
        String e2 = asiento2 ? "Ocupado" : "Libre";
        String e3 = asiento3 ? "Ocupado" : "Libre";

        String opcion = JOptionPane.showInputDialog(
                "Elige número de asiento (1, 2, 3):\n"
                + "1: " + e1 + "\n"
                + "2: " + e2 + "\n"
                + "3: " + e3);
        if (opcion == null) return;

        switch (opcion.trim()) {
            case "1":
                if (!asiento1) { asiento1 = true; JOptionPane.showMessageDialog(null, "Asiento 1 reservado."); }
                else JOptionPane.showMessageDialog(null, "El asiento 1 ya está ocupado.");
                break;
            case "2":
                if (!asiento2) { asiento2 = true; JOptionPane.showMessageDialog(null, "Asiento 2 reservado."); }
                else JOptionPane.showMessageDialog(null, "El asiento 2 ya está ocupado.");
                break;
            case "3":
                if (!asiento3) { asiento3 = true; JOptionPane.showMessageDialog(null, "Asiento 3 reservado."); }
                else JOptionPane.showMessageDialog(null, "El asiento 3 ya está ocupado.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Número inválido.");
        }
    }

    public double calcularOcupacion() {
        int ocupados = 0;
        if (asiento1) ocupados++;
        if (asiento2) ocupados++;
        if (asiento3) ocupados++;
        return (ocupados * 100.0) / 3.0;
    }

    public String reporteOcupacion() {
        int ocupados = 0, libres = 0;
        if (asiento1) ocupados++; else libres++;
        if (asiento2) ocupados++; else libres++;
        if (asiento3) ocupados++; else libres++;
        double pct = (ocupados * 100.0) / 3.0;
        return "Ocupación del avión:\n"
                + "Asientos ocupados: " + ocupados + "\n"
                + "Asientos libres: "   + libres   + "\n"
                + "Porcentaje total: "  + pct      + "%";
    }
}