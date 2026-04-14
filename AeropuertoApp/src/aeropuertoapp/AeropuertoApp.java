/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuertoapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AeropuertoApp extends JFrame {

    private Avion avion = null;
    private Vuelo[] vuelos = new Vuelo[5];
    private String[][] horarios = new String[5][3];
    private int contadorVuelos = 0;
    private Pasajero pasajero = null;

    // Colores del tema
    private static final Color COLOR_FONDO       = new Color(15, 23, 42);
    private static final Color COLOR_PANEL        = new Color(30, 41, 59);
    private static final Color COLOR_PANEL_CLARO  = new Color(51, 65, 85);
    private static final Color COLOR_ACENTO       = new Color(56, 189, 248);
    private static final Color COLOR_ACENTO2      = new Color(99, 102, 241);
    private static final Color COLOR_TEXTO        = new Color(248, 250, 252);
    private static final Color COLOR_TEXTO_MUTED  = new Color(148, 163, 184);
    private static final Color COLOR_EXITO        = new Color(52, 211, 153);
    private static final Color COLOR_ADVERTENCIA  = new Color(251, 191, 36);
    private static final Color COLOR_PELIGRO      = new Color(248, 113, 113);

    private JPanel panelContenido;
    private JLabel labelEstado;

    public AeropuertoApp() {
        setTitle("✈  Sistema de Aeropuerto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setMinimumSize(new Dimension(800, 550));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(COLOR_FONDO);

        add(crearHeader(), BorderLayout.NORTH);
        add(crearPanelIzquierdo(), BorderLayout.WEST);
        add(crearPanelDerecho(), BorderLayout.CENTER);
        add(crearFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    // ─── HEADER ───────────────────────────────────────────────
    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PANEL);
        header.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, COLOR_PANEL_CLARO),
            new EmptyBorder(16, 24, 16, 24)
        ));

        JLabel titulo = new JLabel("✈  AEROPUERTO SISTEMA");
        titulo.setForeground(COLOR_ACENTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel subtitulo = new JLabel("Panel de Control");
        subtitulo.setForeground(COLOR_TEXTO_MUTED);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);

        labelEstado = new JLabel("Sistema listo");
        labelEstado.setForeground(COLOR_EXITO);
        labelEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelEstado.setBorder(new CompoundBorder(
            new LineBorder(COLOR_EXITO, 1, true),
            new EmptyBorder(4, 10, 4, 10)
        ));

        header.add(textos, BorderLayout.WEST);
        header.add(labelEstado, BorderLayout.EAST);
        return header;
    }

    // ─── PANEL IZQUIERDO (menú de botones) ────────────────────
    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_PANEL);
        panel.setPreferredSize(new Dimension(220, 0));
        panel.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 0, 1, COLOR_PANEL_CLARO),
            new EmptyBorder(16, 12, 16, 12)
        ));

        agregarSeccion(panel, "AVIÓN");
        agregarBoton(panel, "1  Registrar Avión", "avion", e -> registrarAvion());

        agregarSeccion(panel, "VUELOS");
        agregarBoton(panel, "2  Registrar Vuelo",       "vuelo",    e -> registrarVuelo());
        agregarBoton(panel, "3  Mostrar Vuelos",         "lista",    e -> mostrarVuelos());
        agregarBoton(panel, "4  Elegir Asiento",         "asiento",  e -> elegirAsiento());
        agregarBoton(panel, "5  Ver Horarios",           "horario",  e -> mostrarHorarios());
        agregarBoton(panel, "6  Actualizar Estado",      "estado",   e -> actualizarEstado());

        agregarSeccion(panel, "PASAJEROS");
        agregarBoton(panel, "7  Registrar Pasajero",    "pasajero", e -> registrarPasajero());
        agregarBoton(panel, "8  Comprar Tiquete",        "tiquete",  e -> comprarTiquete());

        agregarSeccion(panel, "REPORTES");
        agregarBoton(panel, "9  Reporte Ocupación",     "reporte",  e -> reporteOcupacion());
        agregarBoton(panel, "10 Comidas Especiales",    "comidas",  e -> manifestoComidas());

        panel.add(Box.createVerticalGlue());

        JButton btnSalir = crearBotonSalir();
        btnSalir.addActionListener(e -> confirmarSalida());
        panel.add(btnSalir);

        return panel;
    }

    private void agregarSeccion(JPanel panel, String titulo) {
        panel.add(Box.createVerticalStrut(12));
        JLabel lbl = new JLabel(titulo);
        lbl.setForeground(COLOR_TEXTO_MUTED);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setBorder(new EmptyBorder(0, 4, 4, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);
    }

    private void agregarBoton(JPanel panel, String texto, String tag, ActionListener al) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(new Color(0, 0, 0, 0));
        btn.setForeground(COLOR_TEXTO);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBorder(new CompoundBorder(
            new LineBorder(new Color(0,0,0,0), 1, true),
            new EmptyBorder(6, 8, 6, 8)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(COLOR_PANEL_CLARO);
                btn.setForeground(COLOR_ACENTO);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0, 0, 0, 0));
                btn.setForeground(COLOR_TEXTO);
            }
        });

        btn.addActionListener(al);
        panel.add(btn);
        panel.add(Box.createVerticalStrut(2));
    }

    private JButton crearBotonSalir() {
        JButton btn = new JButton("⏻  Salir del Sistema");
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(new Color(127, 29, 29));
        btn.setForeground(COLOR_TEXTO);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(new EmptyBorder(8, 8, 8, 8));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }

    // ─── PANEL DERECHO (área de contenido dinámico) ───────────
    private JScrollPane crearPanelDerecho() {
        panelContenido = new JPanel();
        panelContenido.setBackground(COLOR_FONDO);
        panelContenido.setBorder(new EmptyBorder(24, 24, 24, 24));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        mostrarBienvenida();

        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(COLOR_FONDO);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private void mostrarBienvenida() {
        panelContenido.removeAll();

        JLabel ico = new JLabel("✈", SwingConstants.CENTER);
        ico.setForeground(COLOR_ACENTO);
        ico.setFont(new Font("Segoe UI", Font.PLAIN, 64));
        ico.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Bienvenido al Sistema de Aeropuerto", SwingConstants.CENTER);
        titulo.setForeground(COLOR_TEXTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Seleccioná una opción del menú para comenzar", SwingConstants.CENTER);
        sub.setForeground(COLOR_TEXTO_MUTED);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelContenido.add(Box.createVerticalGlue());
        panelContenido.add(ico);
        panelContenido.add(Box.createVerticalStrut(16));
        panelContenido.add(titulo);
        panelContenido.add(Box.createVerticalStrut(8));
        panelContenido.add(sub);
        panelContenido.add(Box.createVerticalGlue());

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // ─── FOOTER ───────────────────────────────────────────────
    private JPanel crearFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setBackground(COLOR_PANEL);
        footer.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, COLOR_PANEL_CLARO),
            new EmptyBorder(6, 16, 6, 16)
        ));
        JLabel lbl = new JLabel("Sistema de Gestión Aeroportuaria  •  v1.0");
        lbl.setForeground(COLOR_TEXTO_MUTED);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footer.add(lbl);
        return footer;
    }

    // ─── HELPERS DE UI ────────────────────────────────────────
    private void mostrarTarjeta(String titulo, String[][] campos, Color colorTitulo) {
        panelContenido.removeAll();

        JLabel lbl = new JLabel(titulo);
        lbl.setForeground(colorTitulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(lbl);
        panelContenido.add(Box.createVerticalStrut(16));

        for (String[] fila : campos) {
            JPanel fila_ = new JPanel(new BorderLayout(12, 0));
            fila_.setBackground(COLOR_PANEL);
            fila_.setBorder(new CompoundBorder(
                new LineBorder(COLOR_PANEL_CLARO, 1, true),
                new EmptyBorder(10, 14, 10, 14)
            ));
            fila_.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            fila_.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel clave = new JLabel(fila[0]);
            clave.setForeground(COLOR_TEXTO_MUTED);
            clave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            clave.setPreferredSize(new Dimension(160, 20));

            JLabel valor = new JLabel(fila[1]);
            valor.setForeground(COLOR_TEXTO);
            valor.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            fila_.add(clave, BorderLayout.WEST);
            fila_.add(valor, BorderLayout.CENTER);

            panelContenido.add(fila_);
            panelContenido.add(Box.createVerticalStrut(4));
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarMensaje(String titulo, String mensaje, Color color) {
        labelEstado.setText(mensaje.length() > 40 ? titulo : mensaje);
        labelEstado.setForeground(color);
        labelEstado.setBorder(new CompoundBorder(
            new LineBorder(color, 1, true),
            new EmptyBorder(4, 10, 4, 10)
        ));

        JOptionPane pane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(this, titulo);
        dialog.setVisible(true);
    }

    private String pedir(String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje);
    }

    private String[] pedirMultiple(String titulo, String... preguntas) {
        JPanel panel = new JPanel(new GridLayout(preguntas.length, 2, 8, 8));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));

        JTextField[] campos = new JTextField[preguntas.length];
        for (int i = 0; i < preguntas.length; i++) {
            JLabel lbl = new JLabel(preguntas[i] + ":");
            lbl.setForeground(COLOR_TEXTO);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            campos[i] = new JTextField(18);
            panel.add(lbl);
            panel.add(campos[i]);
        }

        int res = JOptionPane.showConfirmDialog(this, panel, titulo,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (res == JOptionPane.OK_OPTION) {
            String[] vals = new String[preguntas.length];
            for (int i = 0; i < preguntas.length; i++) vals[i] = campos[i].getText().trim();
            return vals;
        }
        return null;
    }

    // ─── LÓGICA DE CADA OPCIÓN ────────────────────────────────

    private void registrarAvion() {
        String[] datos = pedirMultiple("Registrar Avión",
            "Matrícula", "Modelo", "Capacidad");
        if (datos == null) return;

        try {
            int cap = Integer.parseInt(datos[2]);
            avion = new Avion(datos[0], datos[1], cap);
            mostrarTarjeta("✅  Avión Registrado", new String[][]{
                {"Matrícula",  datos[0]},
                {"Modelo",     datos[1]},
                {"Capacidad",  cap + " pasajeros"}
            }, COLOR_EXITO);
            labelEstado.setText("Avión registrado");
            labelEstado.setForeground(COLOR_EXITO);
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error", "La capacidad debe ser un número entero.", COLOR_PELIGRO);
        }
    }

    private void registrarVuelo() {
        if (avion == null) {
            mostrarMensaje("Sin avión", "Primero debe registrar un avión.", COLOR_ADVERTENCIA);
            return;
        }
        if (contadorVuelos >= vuelos.length) {
            mostrarMensaje("Límite alcanzado", "No se pueden registrar más de " + vuelos.length + " vuelos.", COLOR_PELIGRO);
            return;
        }

        String[] datos = pedirMultiple("Registrar Vuelo",
            "Código del vuelo", "Destino", "Hora de salida", "Hora de llegada",
            "Estado (En espera / En vuelo / Aterrizado)");
        if (datos == null) return;

        vuelos[contadorVuelos] = new Vuelo(datos[0], datos[1], avion);
        horarios[contadorVuelos][0] = datos[2];
        horarios[contadorVuelos][1] = datos[3];
        horarios[contadorVuelos][2] = datos[4];
        contadorVuelos++;

        mostrarTarjeta("✅  Vuelo Registrado", new String[][]{
            {"Código",       datos[0]},
            {"Destino",      datos[1]},
            {"Hora salida",  datos[2]},
            {"Hora llegada", datos[3]},
            {"Estado",       datos[4]},
            {"Avión",        avion.getMatricula() + " / " + avion.getModelo()}
        }, COLOR_EXITO);
    }

    private void mostrarVuelos() {
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin vuelos", "No hay vuelos registrados.", COLOR_ADVERTENCIA);
            return;
        }

        panelContenido.removeAll();

        JLabel titulo_ = new JLabel("Lista de Vuelos  (" + contadorVuelos + ")");
        titulo_.setForeground(COLOR_ACENTO);
        titulo_.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo_.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(titulo_);
        panelContenido.add(Box.createVerticalStrut(16));

        for (int i = 0; i < contadorVuelos; i++) {
            JPanel card = crearTarjetaVuelo(i);
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelContenido.add(card);
            panelContenido.add(Box.createVerticalStrut(8));
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private JPanel crearTarjetaVuelo(int i) {
        JPanel card = new JPanel(new BorderLayout(12, 0));
        card.setBackground(COLOR_PANEL);
        card.setBorder(new CompoundBorder(
            new LineBorder(COLOR_PANEL_CLARO, 1, true),
            new EmptyBorder(12, 14, 12, 14)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel num = new JLabel("#" + (i + 1));
        num.setForeground(COLOR_ACENTO);
        num.setFont(new Font("Segoe UI", Font.BOLD, 16));
        num.setPreferredSize(new Dimension(32, 20));

        String cod = vuelos[i].getCodigo();
        String dest = vuelos[i].getDestino();
        JLabel info = new JLabel(cod + "  →  " + dest);
        info.setForeground(COLOR_TEXTO);
        info.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel hora = new JLabel(horarios[i][0] + " – " + horarios[i][1]);
        hora.setForeground(COLOR_TEXTO_MUTED);
        hora.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);
        centro.add(info);
        centro.add(hora);

        Color badgeColor = getBadgeColor(horarios[i][2]);
        JLabel estado_ = new JLabel(horarios[i][2]);
        estado_.setForeground(badgeColor);
        estado_.setFont(new Font("Segoe UI", Font.BOLD, 11));
        estado_.setBorder(new CompoundBorder(
            new LineBorder(badgeColor, 1, true),
            new EmptyBorder(3, 8, 3, 8)
        ));

        card.add(num, BorderLayout.WEST);
        card.add(centro, BorderLayout.CENTER);
        card.add(estado_, BorderLayout.EAST);

        return card;
    }

    private Color getBadgeColor(String estado) {
        if (estado == null) return COLOR_TEXTO_MUTED;
        if (estado.equalsIgnoreCase("En vuelo"))      return COLOR_ACENTO;
        if (estado.equalsIgnoreCase("Aterrizado"))    return COLOR_EXITO;
        if (estado.equalsIgnoreCase("En espera"))     return COLOR_ADVERTENCIA;
        return COLOR_TEXTO_MUTED;
    }

    private void elegirAsiento() {
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin vuelos", "No hay vuelos registrados.", COLOR_ADVERTENCIA);
            return;
        }
        int indice = pedirIndiceVuelo();
        if (indice < 0) return;
        vuelos[indice].elegirAsientoVuelo();
    }

    private void mostrarHorarios() {
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin horarios", "No hay horarios registrados.", COLOR_ADVERTENCIA);
            return;
        }

        panelContenido.removeAll();

        JLabel tit = new JLabel("Horarios de Vuelos");
        tit.setForeground(COLOR_ACENTO);
        tit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tit.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(tit);
        panelContenido.add(Box.createVerticalStrut(16));

        String[] cols = {"#", "Código", "Salida", "Llegada", "Estado"};
        String[][] filas = new String[contadorVuelos][5];
        for (int i = 0; i < contadorVuelos; i++) {
            filas[i][0] = String.valueOf(i + 1);
            filas[i][1] = vuelos[i].getCodigo();
            filas[i][2] = horarios[i][0];
            filas[i][3] = horarios[i][1];
            filas[i][4] = horarios[i][2];
        }

        JTable tabla = new JTable(filas, cols);
        tabla.setBackground(COLOR_PANEL);
        tabla.setForeground(COLOR_TEXTO);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setBackground(COLOR_PANEL_CLARO);
        tabla.getTableHeader().setForeground(COLOR_TEXTO_MUTED);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setRowHeight(32);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setEnabled(false);
        tabla.setSelectionBackground(COLOR_PANEL_CLARO);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(new LineBorder(COLOR_PANEL_CLARO, 1));
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        panelContenido.add(scroll);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void actualizarEstado() {
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin vuelos", "No hay vuelos registrados.", COLOR_ADVERTENCIA);
            return;
        }
        int indice = pedirIndiceVuelo();
        if (indice < 0) return;

        String[] opciones = {"En espera", "En vuelo", "Aterrizado"};
        String nuevo = (String) JOptionPane.showInputDialog(
            this, "Seleccione nuevo estado:", "Actualizar Estado",
            JOptionPane.PLAIN_MESSAGE, null, opciones, horarios[indice][2]);

        if (nuevo != null) {
            horarios[indice][2] = nuevo;
            mostrarTarjeta("✅  Estado Actualizado", new String[][]{
                {"Vuelo",         "#" + (indice + 1) + " – " + vuelos[indice].getCodigo()},
                {"Destino",       vuelos[indice].getDestino()},
                {"Nuevo estado",  nuevo}
            }, COLOR_EXITO);
        }
    }

    private void registrarPasajero() {
        String[] datos = pedirMultiple("Registrar Pasajero",
            "Nombre", "Identificación");
        if (datos == null) return;

        String[] niveles = {"Platino", "Oro", "Regular"};
        String nivel = (String) JOptionPane.showInputDialog(
            this, "Nivel de socio:", "Registrar Pasajero",
            JOptionPane.PLAIN_MESSAGE, null, niveles, "Regular");
        if (nivel == null) return;

        String[] comidas = {"Estándar", "Vegetariano", "Kosher", "Sin Gluten"};
        String comida = (String) JOptionPane.showInputDialog(
            this, "Comida especial:", "Registrar Pasajero",
            JOptionPane.PLAIN_MESSAGE, null, comidas, "Estándar");
        if (comida == null) return;

        pasajero = new Pasajero(datos[0], datos[1], nivel, comida);

        mostrarTarjeta("✅  Pasajero Registrado", new String[][]{
            {"Nombre",         datos[0]},
            {"Identificación", datos[1]},
            {"Nivel socio",    nivel},
            {"Comida",         comida}
        }, COLOR_EXITO);
    }

    private void comprarTiquete() {
        if (pasajero == null) {
            mostrarMensaje("Sin pasajero", "Primero debe registrar un pasajero.", COLOR_ADVERTENCIA);
            return;
        }
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin vuelos", "No hay vuelos disponibles.", COLOR_ADVERTENCIA);
            return;
        }

        int indice = pedirIndiceVuelo();
        if (indice < 0) return;

        String precioStr = pedir("Ingrese precio base del tiquete:");
        if (precioStr == null) return;

        try {
            double precioBase = Double.parseDouble(precioStr);
            double ocupacion = vuelos[indice].getAvion().calcularOcupacion();
            boolean recargo = ocupacion > 80.0;
            if (recargo) precioBase *= 1.20;

            double precioFinal = pasajero.aplicarDescuento(precioBase);
            String descuento = pasajero.getNivelSocio().equalsIgnoreCase("Platino") ? "10%"
                             : pasajero.getNivelSocio().equalsIgnoreCase("Oro")     ? "5%"
                             : "0%";

            mostrarTarjeta("🎫  Tiquete Comprado", new String[][]{
                {"Pasajero",        pasajero.getNombre()},
                {"Vuelo",           "#" + (indice + 1) + " – " + vuelos[indice].getCodigo()},
                {"Destino",         vuelos[indice].getDestino()},
                {"Ocupación actual",String.format("%.1f%%", ocupacion) + (recargo ? " ⚠ recargo 20%" : "")},
                {"Precio base",     String.format("$%.2f", precioBase)},
                {"Descuento socio", descuento + " (" + pasajero.getNivelSocio() + ")"},
                {"Precio final",    String.format("$%.2f", precioFinal)}
            }, COLOR_ACENTO2);

        } catch (NumberFormatException ex) {
            mostrarMensaje("Error", "El precio debe ser un número válido.", COLOR_PELIGRO);
        }
    }

    private void reporteOcupacion() {
        if (contadorVuelos == 0) {
            mostrarMensaje("Sin vuelos", "No hay vuelos registrados.", COLOR_ADVERTENCIA);
            return;
        }
        int indice = pedirIndiceVuelo();
        if (indice < 0) return;

        Avion av = vuelos[indice].getAvion();
        double pct = av.calcularOcupacion();
        int ocupados = (int) Math.round(pct * 3 / 100);
        int libres = 3 - ocupados;

        mostrarTarjeta("📊  Reporte de Ocupación", new String[][]{
            {"Vuelo",               "#" + (indice + 1) + " – " + vuelos[indice].getCodigo()},
            {"Asientos ocupados",   String.valueOf(ocupados)},
            {"Asientos libres",     String.valueOf(libres)},
            {"Porcentaje ocupación",String.format("%.1f%%", pct)}
        }, COLOR_ACENTO);

        // Barra de progreso visual
        panelContenido.add(Box.createVerticalStrut(16));
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue((int) pct);
        barra.setStringPainted(true);
        barra.setString(String.format("%.1f%% ocupado", pct));
        barra.setForeground(pct > 80 ? COLOR_PELIGRO : pct > 50 ? COLOR_ADVERTENCIA : COLOR_EXITO);
        barra.setBackground(COLOR_PANEL_CLARO);
        barra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        barra.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(barra);

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void manifestoComidas() {
        if (pasajero == null) {
            mostrarMensaje("Sin pasajeros", "No hay pasajeros registrados.", COLOR_ADVERTENCIA);
            return;
        }

        mostrarTarjeta("🍽  Manifiesto de Comidas Especiales", new String[][]{
            {"Pasajero",       pasajero.getNombre()},
            {"Identificación", pasajero.getIdentificacion()},
            {"Comida especial",pasajero.getComidaEspecial()},
            {"Nivel socio",    pasajero.getNivelSocio()}
        }, COLOR_ADVERTENCIA);
    }

    private int pedirIndiceVuelo() {
        String[] opciones = new String[contadorVuelos];
        for (int i = 0; i < contadorVuelos; i++) {
            opciones[i] = "#" + (i + 1) + " – " + vuelos[i].getCodigo()
                        + " → " + vuelos[i].getDestino();
        }
        String sel = (String) JOptionPane.showInputDialog(
            this, "Seleccione un vuelo:", "Seleccionar Vuelo",
            JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (sel == null) return -1;
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(sel)) return i;
        }
        return -1;
    }

    private void confirmarSalida() {
        int res = JOptionPane.showConfirmDialog(this,
            "¿Desea salir del sistema?", "Confirmar salida",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.YES_OPTION) System.exit(0);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(AeropuertoApp::new);
    }
}