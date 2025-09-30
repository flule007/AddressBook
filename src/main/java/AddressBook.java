/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author flule
 */
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class AddressBook {
    private Map<String, String> contactos; // Teléfono : Nombre
    private String archivo;

    public AddressBook(String archivo) {
        this.contactos = new HashMap<>();
        this.archivo = archivo;
    }

    // Cargar contactos desde archivo CSV
    public void load() {
        contactos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    contactos.put(datos[0], datos[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo, se creará uno nuevo al guardar.");
        }
    }

    // Guardar contactos en archivo CSV
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Contactos guardados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los contactos: " + e.getMessage());
        }
    }

    // Listar contactos
    public void list() {
        StringBuilder sb = new StringBuilder("Contactos:\n");
        for (Map.Entry<String, String> entry : contactos.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Crear nuevo contacto
    public void create() {
        String numero = JOptionPane.showInputDialog("Ingresa número:");
        if (numero == null || numero.trim().isEmpty()) return;

        if (contactos.containsKey(numero)) {
            JOptionPane.showMessageDialog(null, "Ese número ya está registrado.");
        } else {
            String nombre = JOptionPane.showInputDialog("Ingresa nombre:");
            if (nombre == null || nombre.trim().isEmpty()) return;

            contactos.put(numero, nombre);
            JOptionPane.showMessageDialog(null, "Contacto agregado exitosamente.");
        }
    }

    // Eliminar contacto
    public void delete() {
        String numero = JOptionPane.showInputDialog("Ingresa el número a eliminar:");
        if (numero == null || numero.trim().isEmpty()) return;

        if (contactos.remove(numero) != null) {
            JOptionPane.showMessageDialog(null, "Contacto eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "El número no existe en la agenda.");
        }
    }
}
