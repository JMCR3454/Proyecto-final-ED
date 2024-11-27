/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoED;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author José Manuel
 */
public class Cine {
    //Valores
    private final int FILAS = 4;
    private final int COLUMNAS = 4;
    private final int PRECIO_BOLETO = 150;
    private final String[] funciones = {
        "Venom el último baile - 2:00 PM",
        "Sonríe 2 - 5:00 PM",
        "Gladiador (ReEstreno) - 8:00 PM"
    };

    private String[][][] asientos; // posicion [función][fila][columna]
    private ArrayList<Boleto> boletosVendidos;

    public Cine() {
        asientos = new String[funciones.length][FILAS][COLUMNAS];
        boletosVendidos = new ArrayList<>();
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        for (int f = 0; f < funciones.length; f++) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    asientos[f][i][j] = "|_|"; // Asiento disponible
                }
            }
        }
    }

    public void comprarBoleto() {
        // Mostrar el menú de funciones
        StringBuilder menuFunciones = new StringBuilder("Seleccione una función:\n");
        for (int i = 0; i < funciones.length; i++) {
            menuFunciones.append(i + 1).append(". ").append(funciones[i]).append("\n");
        }

        int funcionIndex = Integer.parseInt(JOptionPane.showInputDialog(menuFunciones.toString())) - 1;
        if (funcionIndex < 0 || funcionIndex >= funciones.length) {
            JOptionPane.showMessageDialog(null, "Función no válida.");
            return;
        }

        // Mostrar asientos
        mostrarAsientos(funcionIndex);

        // Mostrar lista de asientos disponibles
        ArrayList<String> asientosDisponibles = new ArrayList<>();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (asientos[funcionIndex][i][j].equals("|_|")) {
                    asientosDisponibles.add("Fila " + (i + 1) + ", Columna " + (j + 1));
                }
            }
        }

        if (asientosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asientos disponibles para esta función.");
            return;
        }

        // Seleccionar un asiento
        String[] opciones = asientosDisponibles.toArray(new String[0]);
        String asientoSeleccionado = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione un asiento:",
            "Seleccionar Asiento",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (asientoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún asiento.");
            return;
        }

        // Confirmar la compra
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "El precio del boleto es $" + PRECIO_BOLETO + ". ¿Desea continuar?",
            "Confirmar Compra",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Compra cancelada.");
            return;
        }

        // Obtener fila y columna del asiento seleccionado
        String[] partes = asientoSeleccionado.split("[, ]+");
        int fila = Integer.parseInt(partes[1]) - 1;
        int columna = Integer.parseInt(partes[3]) - 1;

        // Pedir el nombre del cliente
        String cliente = JOptionPane.showInputDialog("Ingrese su nombre:");
        
        // Marcar asiento como ocupado
        asientos[funcionIndex][fila][columna] = "|X|";
        boletosVendidos.add(new Boleto(cliente, funciones[funcionIndex], fila + 1, columna + 1, PRECIO_BOLETO));
        JOptionPane.showMessageDialog(null, "Boleto comprado exitosamente.");
    }

    private void mostrarAsientos(int funcionIndex) {
        StringBuilder asientosVisual = new StringBuilder("Distribución de Asientos:\n");
        asientosVisual.append(" |_| = Disponible  |X| = Ocupado, \n\n");

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                asientosVisual.append(asientos[funcionIndex][i][j]).append(" ");
            }
            asientosVisual.append("\n");
        }

        JOptionPane.showMessageDialog(null, asientosVisual.toString(), "Asientos Disponibles", JOptionPane.INFORMATION_MESSAGE);
    }

    public void verReporteGeneral() {
        if (boletosVendidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han vendido boletos.");
            return;
        }

        StringBuilder reporte = new StringBuilder("Reporte General de Ventas:\n");
        int totalGanancias = 0;

        for (Boleto boleto : boletosVendidos) {
            reporte.append(boleto).append("\n");
            totalGanancias += boleto.getPrecio();
        }

        reporte.append("\nTotal boletos vendidos: ").append(boletosVendidos.size());
        reporte.append("\nTotal ganancias: $").append(totalGanancias);

        JOptionPane.showMessageDialog(null, reporte.toString());
    }

    public void verReportePorFuncion() {
        StringBuilder reporte = new StringBuilder("Reporte por Función:\n");

        for (int i = 0; i < funciones.length; i++) {
            int boletosFuncion = 0;
            int gananciasFuncion = 0;

            for (Boleto boleto : boletosVendidos) {
                if (boleto.getFuncion().equals(funciones[i])) {
                    boletosFuncion++;
                    gananciasFuncion += boleto.getPrecio();
                }
            }

            reporte.append(funciones[i]).append("\n")
                   .append("Boletos vendidos: ").append(boletosFuncion).append("\n")
                   .append("Ganancias: $").append(gananciasFuncion).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, reporte.toString());
    }
}