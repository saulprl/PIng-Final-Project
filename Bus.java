package finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class Bus {
    private static Scanner scn = new Scanner(System.in);

    public static void toAdd(int unit, File Autobuses) throws IOException { // Agrega un autobús.
        boolean hasTV = false, hasWiFi = false, hasAC = false;
        try {
            System.out.println("Escriba el destino del autobús: ");
            String dest = scn.nextLine(); // Guarda el destino.
            System.out.println("Escriba el modelo del autobús: ");
            String model = scn.nextLine(); // Guarda el modelo.
            System.out.println("Escriba el número de asientos: ");
            int seats = scn.nextInt(); // Guarda el número de asientos.
            System.out.println("Escriba 1 para 'sí' y 2 para 'no': \n¿Tiene TV?");
            if (scn.nextInt() == 1) // ¿Tiene TV?
                hasTV = true;
            System.out.println("¿Tiene WiFi?");
            if (scn.nextInt() == 1) // ¿Tiene WiFi?
                hasWiFi = true;
            System.out.println("¿Tiene aire acondicionado?");
            if (scn.nextInt() == 1) // ¿Tiene AC?
                hasAC = true;
            TheCreator.buses(dest, model, unit, seats, hasTV, hasWiFi, hasAC, Autobuses); // Crea o modifica el archivo de autobuses.
            System.out.println("El autobús ha sido agregado.\nPresione 'Enter' para continuar.");
            System.in.read();
        } catch (InputMismatchException e) {
            System.out.println("Ha ingresado uno o varios parámetros incorrectos. Presione 'Enter' para continuar.");
            System.in.read();
        }
    }

    public static void toModify(ArrayList<String> readFile, File Autobuses) throws IOException { // Modifica un autobús.
        for (int i = 0; i < readFile.size(); i++) { // Imprime las unidades.
            System.out.println("Unidad: " + readFile.get(i));
            System.out.println("---------------");
            i += 5;
        }
        System.out.println("Ingresa la unidad del autobús que deseas modificar: ");
        try {
            int toModify = scn.nextInt();
            int unitOpt;
            for (int i = 0; i < readFile.size(); i++) {
                if (toModify == Integer.parseInt(readFile.get(i))) { // Si coinciden las unidades:
                    do {
                        System.out.println("Elige el parámetro que deseas modificar:\n1. Unidad.\n" +
                                "2. Destino.\n3. Clase.\n4. Número de asientos.\n5. Modelo.\n0. Regresar.");
                        unitOpt = scn.nextInt();
                        boolean hasTV = false, hasWiFi = false, hasAC = false;
                        switch (unitOpt) {
                            case 1: // Unidad.
                                System.out.println("Ingresa la unidad: ");
                                readFile.set(i, scn.nextInt() + "");
                                break;
                            case 2: // Destino.
                                scn.nextLine();
                                System.out.println("Escribe el nuevo destino: ");
                                readFile.set(i + 1, scn.nextLine());
                                break;
                            case 3: // Clase.
                                System.out.println("Ingresa 1 para 'Sí' o 2 para 'No': \n¿Tiene TV?");
                                if (scn.nextInt() == 1)
                                    hasTV = true;
                                System.out.println("¿Tiene WiFi?");
                                if (scn.nextInt() == 1)
                                    hasWiFi = true;
                                System.out.println("¿Tiene aire acondicionado?");
                                if (scn.nextInt() == 1)
                                    hasAC = true;
                                if (hasTV && hasWiFi && hasAC)
                                    readFile.set(i + 2, "VIP");
                                else
                                    readFile.set(i + 2, "Estándar");
                                break;
                            case 4: // Asientos.
                                System.out.println("Ingresa el número de asientos: ");
                                readFile.set(i + 3, scn.nextInt() + "");
                                break;
                            case 5: // Modelo.
                                scn.nextLine();
                                System.out.println("Ingresa el modelo: ");
                                readFile.set(i + 4, scn.nextLine());
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Ingresó una opción inválida. Presione 'Enter' para continuar.");
                                System.in.read();
                                break;
                        }
                    } while (unitOpt != 0);
                    break; // Rompe el for.
                } else {
                    i += 5; // Aumenta en 5 para seguir comparando.
                }
            }
            Modifier.toModify(readFile, Autobuses); // Modifica el archivo de autobuses.
        } catch (InputMismatchException e) {
            System.out.println("Ingresó uno o varios parámetros inválidos. Presione 'Enter' para continuar.");
            System.in.read();
        }
    }

    public static void toDelete(ArrayList<String> readFile, File Autobuses) throws IOException {
        // Elimina un autobús.
        for (int i = 0; i < readFile.size(); i++) { // Imprime cada unidad.
            System.out.println("Unidad: " + readFile.get(i));
            System.out.println("---------------");
            i += 5;
        }
        System.out.println("Ingresa la unidad del autobús que deseas eliminar: ");
        int toDelete;
        try {
            toDelete = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Parámetro inválido: solo se admiten números enteros. Inténtelo de nuevo: ");
            toDelete = scn.nextInt();
        }
        for (int i = 0; i < readFile.size(); i++) {
            if (toDelete == Integer.parseInt(readFile.get(i))) { // Si coincide la unidad:
                for (int j = 0; j < 6; j++) {
                    readFile.remove(i); // Elimina la misma línea seis veces.
                }
                System.out.println("El autobús ha sido eliminado. Presione 'Enter' para continuar: ");
                System.in.read();
                break;
            } else {
                i += 5;
            }
        }

        // Autor: Saúl Alberto Ramos Laborín.
        // Expediente: 217200160.
        // Fecha: 17-05-2018.

        Modifier.toModify(readFile, Autobuses); // Modifica el archivo de autobuses.
    }
}