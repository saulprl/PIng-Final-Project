package finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class Usuarios {
    private static Scanner scn = new Scanner(System.in);

    public static void toAdd(int ID, File Users) throws IOException { // Agrega usuarios. Los parámetros ID y Users
                                                                      // se obtienen de ToGet.ID() y PathStorage.getUsersFile().
        try {
            System.out.println("Ingrese su nombre: ");
            String name = scn.nextLine();
            System.out.println("Ingrese '1' para 'Femenino' o '2' para 'Masculino': ");
            int def = scn.nextInt();
            String gender = def == 1 ? "Femenino" : "Masculino";
            System.out.println("Ingrese su edad: ");
            int age = scn.nextInt();
            System.out.println("¿Cuántos viajes ha hecho? ");
            int tripNum = scn.nextInt();
            TheCreator.users(name, gender, age, tripNum, ID, Users); // Recibe todos los parámetros pedidos al usuario y
            // los envía a TheCreator.users().
            System.out.println("Su ID de usuario es: " + ID + ".");  // Se imprime el ID de usuario.
            System.out.println("El usuario ha sido creado. Presione 'Enter' para continuar.");
            System.in.read();
        } catch (InputMismatchException e) {
            System.out.println("Ha ingresado uno o varios parámetros inválidos. Presione 'Enter' para conitnuar.");
            System.in.read();
        }
    }

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

    public static void toModify(ArrayList<String> readFile, File Users) throws IOException { // Modificar archivos de usuario.

        readAndPrint(readFile);

        System.out.println("Ingresa el ID del usuario que deseas modificar: "); // Pide al usuario su ID.
        int toModif;
        try { // En caso de que el usuario ingrese un valor no entero, se usa un try-catch.
            toModif = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Parámetro inválido: solo se admiten números enteros. Inténtelo de nuevo.");
            toModif = scn.nextInt();
        }
        int IDopt;
        for (int i = 0; i < readFile.size(); i++) {
            if (toModif == Integer.parseInt(readFile.get(i))) { // Evalúa el ID ingresado con los IDs registrados.
                do {
                    System.out.println("¿Qué parámetro deseas modificar? "); // Entra en un ciclo, por si se desean modificar
                                                                             // distintos parámetros.
                    System.out.println("1. Nombre.\n2. Género.\n3. Edad.\n4. Viajes realizados.\n0. Salir.");
                    IDopt = scn.nextInt();
                    switch (IDopt) {
                        case 1: // Modificar nombre.
                            System.out.println("Escribe el nombre modificado: ");
                            scn.nextLine();
                            readFile.set(i + 1, scn.nextLine()); // Intercambia el nombre anterior con el nuevo.
                            break;
                        case 2:
                            System.out.println("Ingresa '1' para 'Femenino' o '2' para 'Masculino': ");
                            int newGender;
                            try { // En caso de que el usuario ingrese un valor no entero, se usa un try-catch.
                                newGender = scn.nextInt();
                                if (newGender == 1) {
                                    readFile.set(i + 2, "Femenino"); // Intercambia el género.
                                } else {
                                    readFile.set(i + 2, "Masculino"); // Intercambia el género.
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ingresó un valor inválido: solo se admiten números enteros." +
                                        " Presione 'Enter' para continuar.");
                                System.in.read();
                            }
                            break;
                        case 3:
                            System.out.println("Escribe tu edad modificada: ");
                            int newAge;
                            try { // En caso de que el usuario ingrese un valor no entero, se usa un try-catch.
                                newAge = scn.nextInt();
                                readFile.set(i + 3, newAge + ""); // Intercambia la edad.
                            } catch (InputMismatchException e) {
                                System.out.println("Ingresó un valor inválido: solo se admiten números enteros. " +
                                        "Presione 'Enter' para continuar.");
                                System.in.read();
                            }
                            break;
                        case 4:
                            System.out.println("Escribe el número de viajes realizados: ");
                            int newTripNum;
                            try { // En caso de que el usuario ingrese un valor no entero, se usa un try-catch.
                                newTripNum = scn.nextInt();
                                readFile.set(i + 4, newTripNum + ""); // Intercambia el número de viajes realizados.
                            } catch (InputMismatchException e) {
                                System.out.println("Ingresó un valor inválido: solo se admiten números enteros. " +
                                        "Presione 'Enter' para continuar.");
                                System.in.read();
                            }
                            break;
                        case 0: break;
                        default: // Si no coincide con ningún caso, espera un 'Enter' y vuelve a iterar.
                            System.out.println("Ingresó una opción inválida. Vuelva a intentarlo. Presione " +
                                    "'Enter' para continuar.");
                            System.in.read();
                            break;
                    }
                } while (IDopt != 0);
                break;
            } else {
                i += 5; // Aumenta en 5 para evaluar la siguiente ID.
            }
        }
        Modifier.toModify(readFile, Users); // Se usa toModify con los datos en el ArrayList modificados.
        System.out.println("El usuario ha sido modificado. Presione 'Enter' para continuar.");
        System.in.read();
    }

    private static void readAndPrint(ArrayList<String> readFile) {
        for (int i = 0; i < readFile.size(); i++) { // Lee el archivo recibido por el método. Se guarda en un ArrayList<String>.
            System.out.println("ID: " + readFile.get(i));
            System.out.println("Nombre: " + readFile.get(i + 1));
            System.out.println("---------------");
            i += 5;
        }
    }

    public static void toConsult(ArrayList<String> readFile) throws IOException { // Se imprime la información de un usuario.
        readAndPrint(readFile);
        System.out.println("Ingresa el ID del usuario cuya información deseas consultar: ");
        int toConsult;
        try { // En caso de que el usuario ingrese un valor no entero, se usa un try-catch.
            toConsult = scn.nextInt();
            for (int i = 0; i < readFile.size(); i++) {
                if (toConsult == Integer.parseInt(readFile.get(i))) {
                    System.out.println("ID: " + readFile.get(i));
                    System.out.println("Nombre: " + readFile.get(i + 1));
                    System.out.println("Género: " + readFile.get(i + 2));
                    System.out.println("Edad: " + readFile.get(i + 3));
                    System.out.println("Viajes realizados: " + readFile.get(i + 4));
                    System.out.println("Presione 'Enter' para continuar.");
                    System.in.read();
                    break;
                } else {
                    i += 5;
                    if (i > readFile.size()) { // Si no encuentra el ID, regresa al menú anterior.
                        System.out.println("No se ha encontrado el usuario con ese ID. Presione 'Enter' para continuar.");
                    }
                }
            }
        } catch (InputMismatchException e) { // Regresará al menú anterior.
            System.out.println("Ha ingresado una ID inválida. Presione 'Enter' para continuar. ");
            System.in.read();
        }
    }

    public static void toDelete(ArrayList<String> readFile, File Users) throws IOException { // Borra un usuario.
        readAndPrint(readFile);
        System.out.println("Ingresa el ID del usuario que deseas eliminar: ");
        int toDelete;
        try {
            toDelete = scn.nextInt();
            for (int i = 0; i < readFile.size(); i++) { // Busca la ID.
                if (toDelete == Integer.parseInt(readFile.get(i))) { // Evalúa la ID ingresada con cada ID.
                    for (int j = 0; j < 6; j++) { // Al encontrarla, elimina la misma línea 6 veces, que es un usuario.
                        readFile.remove(i);
                    }
                    System.out.println("El usuario ha sido eliminado. Presiona 'Enter' para continuar.");
                    System.in.read();
                    break;
                } else {
                    i += 5; // Incrementa para evaluar la siguiente ID.
                    if (i > readFile.size()) { // Vuelve al menú anterior.
                        System.out.println("No se ha encontrado un usuario con ese ID. Presione 'Enter' para continuar.");
                        System.in.read();
                    }
                }
            }
        } catch (InputMismatchException e) { // Vuelve al menú anterior.
            System.out.println("Ha ingresado un valor inválido: solo se admiten números enteros. Presione " +
                    "'Enter' para continuar.");
            System.in.read();
        }
        Modifier.toModify(readFile, Users); // Se envía el ArrayList "nuevo" a toModify().
    }
}