/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuertoapp;
 import javax.swing.JOptionPane;
/**
 *
 * @author sophi
 */
public class AeropuertoApp {

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
        Avion avion = null;
        Vuelo[] vuelos = new Vuelo[5];
        String[][] horarios = new String[5][3];
        int contadorVuelos = 0;

        Pasajero pasajero = null;

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Sistema de Aeropuerto\n"
                    + "1. Registrar Avión\n"
                    + "2. Registrar Vuelo\n"
                    + "3. Mostrar Vuelos\n"
                    + "4. Elegir Asiento en un Vuelo\n"
                    + "5. Mostrar Horarios\n"
                    + "6. Actualizar Estado de Vuelo\n"
                    + "7. Registrar Pasajero\n"
                    + "8. Comprar Tiquete\n"
                    + "9. Reporte de Ocupación\n"
                    + "10. Manifiesto de Comidas Especiales\n"
                    + "11. Salir\n"
                    + "Elige una opción:"));

            switch (opcion) {
                case 1:
                    String matricula = JOptionPane.showInputDialog("Ingrese matrícula del avión:");
                    String modelo = JOptionPane.showInputDialog("Ingrese modelo del avión:");
                    int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese capacidad del avión:"));
                    avion = new Avion(matricula, modelo, capacidad);
                    JOptionPane.showMessageDialog(null, "Avión registrado correctamente.");
                    break;

                case 2:
                    if (avion == null) {
                        JOptionPane.showMessageDialog(null, "Primero debe registrar un avión.");
                    } else if (contadorVuelos < vuelos.length) {
                        String codigo = JOptionPane.showInputDialog("Ingrese código del vuelo:");
                        String destino = JOptionPane.showInputDialog("Ingrese destino del vuelo:");
                        String horaSalida = JOptionPane.showInputDialog("Ingrese hora de salida:");
                        String horaLlegada = JOptionPane.showInputDialog("Ingrese hora de llegada:");
                        String estado = JOptionPane.showInputDialog("Ingrese estado del vuelo (En espera, En vuelo, Aterrizado):");
                        vuelos[contadorVuelos] = new Vuelo(codigo, destino, avion);
                        horarios[contadorVuelos][0] = horaSalida;
                        horarios[contadorVuelos][1] = horaLlegada;
                        horarios[contadorVuelos][2] = estado;
                        contadorVuelos++;
                        JOptionPane.showMessageDialog(null, "Vuelo registrado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden registrar más vuelos.");
                    }
                    break;

                case 3:
                    if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos registrados.");
                    } else {
                        for (int i = 0; i < contadorVuelos; i++) {
                            vuelos[i].mostrarInfo(horarios[i][0], horarios[i][1], horarios[i][2]);
                        }
                    }
                    break;

                case 4:
                    if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos registrados.");
                    } else {
                        String numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo (1-" + contadorVuelos + "):");
                        int indice = Integer.parseInt(numVuelo) - 1;
                        if (indice >= 0 && indice < contadorVuelos) {
                            vuelos[indice].elegirAsientoVuelo();
                        } else {
                            JOptionPane.showMessageDialog(null, "Número de vuelo inválido.");
                        }
                    }
                    break;

                case 5:
                    if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay horarios registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("Horarios de vuelos:\n");
                        for (int i = 0; i < contadorVuelos; i++) {
                            sb.append("Vuelo ").append(i + 1)
                                    .append(": Salida ").append(horarios[i][0])
                                    .append(" - Llegada ").append(horarios[i][1])
                                    .append(" - Estado ").append(horarios[i][2])
                                    .append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;

                case 6:
                    if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos registrados.");
                    } else {
                        String numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo (1-" + contadorVuelos + "):");
                        int indice = Integer.parseInt(numVuelo) - 1;
                        if (indice >= 0 && indice < contadorVuelos) {
                            String nuevoEstado = JOptionPane.showInputDialog("Ingrese nuevo estado del vuelo:");
                            horarios[indice][2] = nuevoEstado;
                            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Número de vuelo inválido.");
                        }
                    }
                    break;

                case 7:
                    String nombre = JOptionPane.showInputDialog("Ingrese nombre del pasajero:");
                    String identificacion = JOptionPane.showInputDialog("Ingrese identificación:");
                    String nivelSocio = JOptionPane.showInputDialog("Ingrese nivel de socio (Platino, Oro, Regular):");
                    String comidaEspecial = JOptionPane.showInputDialog("Ingrese comida especial (Vegetariano, Kosher, Sin Gluten, Estándar):");
                    pasajero = new Pasajero(nombre, identificacion, nivelSocio, comidaEspecial);
                    JOptionPane.showMessageDialog(null, "Pasajero registrado correctamente.");
                    break;

                case 8:
                    if (pasajero == null) {
                        JOptionPane.showMessageDialog(null, "Primero debe registrar un pasajero.");
                    } else if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos disponibles.");
                    } else {
                        String numVuelo = JOptionPane.showInputDialog("Seleccione número de vuelo (1-" + contadorVuelos + "):");
                        int indice = Integer.parseInt(numVuelo) - 1;
                        if (indice >= 0 && indice < contadorVuelos) {
                            double precioBase = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio base del tiquete:"));

                            double ocupacion = vuelos[indice].getAvion().calcularOcupacion();
                            if (ocupacion > 80.0) {
                                precioBase = precioBase * 1.20;
                                JOptionPane.showMessageDialog(null, "Ocupación mayor al 80%. Precio base aumentado en 20%.");
                            }

                            double precioFinal = pasajero.aplicarDescuento(precioBase);

                            JOptionPane.showMessageDialog(null,
                                    "Compra realizada:\nVuelo " + (indice + 1)
                                    + "\nPrecio base ajustado: " + precioBase
                                    + "\nPrecio final con descuento: " + precioFinal);
                        } else {
                            JOptionPane.showMessageDialog(null, "Número de vuelo inválido.");
                        }
                    }
                    break;

                case 9:
                    if (contadorVuelos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos registrados.");
                    } else {
                        String numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo (1-" + contadorVuelos + "):");
                        int indice = Integer.parseInt(numVuelo) - 1;
                        if (indice >= 0 && indice < contadorVuelos) {
                            String reporte = vuelos[indice].getAvion().reporteOcupacion();
                            JOptionPane.showMessageDialog(null, reporte);
                        } else {
                            JOptionPane.showMessageDialog(null, "Número de vuelo inválido.");
                        }
                    }
                    break;

                case 10:
                    if (pasajero == null) {
                        JOptionPane.showMessageDialog(null, "No hay pasajeros registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("Manifiesto de Comidas Especiales:\n");
                        sb.append("Pasajero: ").append(pasajero.getNombre())
                                .append(" - Comida: ").append(pasajero.getComidaEspecial())
                                .append("\n");
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;

                case 11:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 11);
    }
}
