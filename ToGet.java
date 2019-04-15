package finalProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ToGet {
    private static Scanner scn = new Scanner(System.in);

    public static String date() { // Obtiene la fecha actual y le da un formato específico.
        Calendar Now = Calendar.getInstance();
        String date = Now.get(Calendar.YEAR) + " - " + (Now.get(Calendar.MONTH) + 1) + " - " + Now.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public static String individualDate() { // Pide una fecha ingresada por el usuario y la convierte en una String
                                            // de formato específico.
        Calendar Now = Calendar.getInstance();
        System.out.println("Ingrese el día con el siguiente formato: YYYY, MM, DD (presione 'Enter' después de cada dato).");
        Now.set(Calendar.YEAR, scn.nextInt()); // Se almacena el año.
        Now.set(Calendar.MONTH, scn.nextInt() - 1); // Se almacena el mes, restándole 1.
        Now.set(Calendar.DAY_OF_MONTH, scn.nextInt()); // Se almacena el día.
        String date = Now.get(Calendar.YEAR) + " - " + (Now.get(Calendar.MONTH) + 1) + " - " + Now.get(Calendar.DAY_OF_MONTH);
        // Se le da formato y se almacena en date, para luego ser regresada.
        return date;
    }

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

    public static int ID(String usersPath, String usersFile, int readID, int ID, File Usuarios) throws IOException {
        // Obtiene el siguiente ID disponible.
        if (Usuarios.exists() && !Usuarios.isDirectory()) { // Si el archivo existe y no es directorio:
            ArrayList<String> forID = Reader.reading(usersPath, usersFile); // Se lee el archivo de usuarios.
            for (int i = 0; i < forID.size(); i++) {
                readID = Integer.parseInt(forID.get(i)); // Recupera cada ID.
                i += 5;
            } // Cuando recupera la última:
            ID = readID + 1; // Se le asigna su valor + 1 a ID.
        }
        return ID; // Se regresa el valor de ID.
    }

    public static int unit(String busPath, String busFile, int readUnit, int unit, File Autobuses) throws IOException {
        // Obtiene la siguiente unidad disponible.
        if (Autobuses.exists() && !(Autobuses.isDirectory())) { // Si el archivo existe y no es directorio:
            ArrayList<String> forUnit = Reader.reading(busPath, busFile); // Se lee el archivo de autobuses.
            for (int i = 0; i < forUnit.size(); i++) {
                readUnit = Integer.parseInt(forUnit.get(i)); // Recupera cada unidad.
                i += 5;
            } // Al recuperar la útlima:
            unit = readUnit + 1; // Se asigna su valor + 1 a unit.
        }
        return unit; // Se regresa el valor de unit.
    }

    public static int tripID(String tripsPath, String tripsFile, int readTripID, int tripID, File Viajes) throws IOException {
        // Obtiene la siguiente ID de viaje.
        if (Viajes.exists() && !(Viajes.isDirectory())) { // Si el archivo existe y no es directorio:
            ArrayList<String> forTripID = Reader.reading(tripsPath, tripsFile); // Se lee el archivo de viajes.
            for (int i = 0; i < forTripID.size(); i++) {
                readTripID = Integer.parseInt(forTripID.get(i)); // Recupera cada ID de viaje.
                i += 5;
            } // Al llegar al último ID de viaje:
            tripID = readTripID + 1; // Se asigna su valor + 1 a tripID.
        }
        return tripID; // Se regresa el valor de tripID.
    }

    public static int ticketNumber(String salesPath, String salesFile, File Ventas) throws IOException {
        // Obtiene el siguiente número de ticket.
        int ticket = 1;
        int readTicket = 0;
        if (Ventas.exists() && !(Ventas.isDirectory())) { // Si el archivo existe y no es directorio:
            ArrayList<String> forTicketNumber = Reader.reading(salesPath, salesFile); // Lee el archivo de ventas.
            for (int i = 0; i < forTicketNumber.size(); i++) {
                readTicket = Integer.parseInt(forTicketNumber.get(i)); // Recupera cada número de ticket.
                i += 3;
            } // Al recuperar el último:
            ticket = readTicket + 1; // Se asigna su valor + 1 a ticket.
        }
        return ticket; // Se regresa el valor de ticket.
    }
}