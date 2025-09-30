/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author flule
 */
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        AddressBook agenda = new AddressBook("contacts.txt");
        agenda.load(); // cargar contactos al inicio

        String[] opciones = {"Listar", "Crear", "Eliminar", "Guardar y salir"};
        boolean salir = false;

        while (!salir) {
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Selecciona una opción:",
                    "AGENDA TELEFÓNICA",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0: // Listar
                    agenda.list();
                    break;
                case 1: // Crear
                    agenda.create();
                    break;
                case 2: // Eliminar
                    agenda.delete();
                    break;
                case 3: // Guardar y salir
                    agenda.save();
                    salir = true;
                    break;
                default:
                    salir = true;
                    break;
            }
        }
    }
}
