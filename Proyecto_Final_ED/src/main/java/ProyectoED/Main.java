/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoED;

import javax.swing.JOptionPane;

/**
 *
 * @author José Manuel
 */


public class Main {
public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        Cine cine = new Cine();
        int option;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog(null, 
                      "1.- Comprar boleto\n" +
                      "2.- Ver reporte general\n" +
                      "3.- Ver reporte por función\n" +
                      "4.- Salir\n" +
                      "Ingrese una opción:"));

            switch (option) {
                case 1:
                    cine.comprarBoleto();
                    break;
                case 2:
                    cine.verReporteGeneral();
                    break;
                case 3:
                    cine.verReportePorFuncion();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (option != 4);
    }
}

