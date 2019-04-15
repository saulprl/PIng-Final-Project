package finalProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Viajes {

    private static Scanner scn = new Scanner(System.in);

    public static void toAdd(String usersPath, String usersFile, String destPath, String destFile, String busPath, String busFile, int tripID, int ticket, File Sales, File Users, File Trips, File Destinations, File Autobuses) throws IOException {
        // Se agrega un registro de viaje.
        System.out.println("Escriba su ID de usuario: ");
        int forTripID;
        try {
            forTripID = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ha ingresado un parámetro inválido: solo se permiten números enteros. Inténtelo " +
                    "de nuevo:");
            forTripID = scn.nextInt();
        }
        scn.nextLine();
        boolean userExists = false;
        if (Users.exists()) { // Si el archivo de usuarios existe:
            ArrayList<String> readFile = Reader.reading(usersPath, usersFile); // Lee el archivo de usuarios.
            for (int i = 0; i < readFile.size(); i++) {
                if (forTripID == (Integer.parseInt(readFile.get(i)))) { // Compara la ID ingresada con las existentes.
                    userExists = true; // Si se encuentra, el usuario existe.
                    break;
                } else { // Si no, aumenta en cinco hasta encontrarla. De no ser así, el usuario no existe.
                    i += 5;
                }
            }
            if (!userExists) { // Si el usuario no existe:
                System.out.println("El usuario no existe.");
                System.out.println("Presione 'Enter' para regresar al menú de viajes.");
                System.in.read();
            } else { // Si el usuario existe:
                if (Destinations.exists()) { // Si el archivo de destinos existe:
                    readFile = Reader.reading(destPath, destFile); // Lee el archivo de destinos.
                    System.out.print("Destinos disponibles: ");
                    for (int i = 0; i < readFile.size(); i++) { // Se imprimen los destinos disponibles.
                        if (i == (readFile.size() - 1))
                            System.out.println(readFile.get(i) + ".");
                        else
                            System.out.print(readFile.get(i) + ", ");
                    }
                    System.out.println("Ingrese su destino: "); // Recibe el destino del usuario.
                    String tripDestination = scn.nextLine();
                    scn.nextLine();
                    for (int i = 0; i < readFile.size(); i++) {
                        if (tripDestination.equalsIgnoreCase(readFile.get(i))) { // Si existe el destino. continúa.
                            break;
                        } else if (i == readFile.size() - 1) { // Si no, vuelve a intentarlo.
                            System.out.println("No se encuentra el destino especificado. Inténtalo de nuevo: ");
                            tripDestination = scn.nextLine();
                        }
                    }
                    System.out.println("Ingrese '1' para VIP o '2' para estándar.");
                    String tripClass;
                    if (scn.nextInt() == 1)
                        tripClass = "VIP";
                    else
                        tripClass = "Estándar";
                    int unitForTrip = -1;
                    if (Autobuses.exists()) { // Si el archivo de autobuses existe:
                        readFile = Reader.reading(busPath, busFile); // Lee el archivo de autobuses.

                        for (int i = 1; i < readFile.size(); i++) { // Si coincide la clase y el destino:
                            if (tripDestination.equalsIgnoreCase(readFile.get(i)) && tripClass.equalsIgnoreCase(readFile.get(i + 1))) {
                                unitForTrip = Integer.parseInt(readFile.get(i - 1));
                                System.out.println("Su autobús será la unidad " + readFile.get(i - 1)); // Se imprime la unidad del autobús del usuario.
                                System.out.println("Presione 'Enter' para continuar: ");
                                System.in.read();
                                break;
                            } else {
                                i += 5; // Aumenta en 5 para seguir comparando.
                            }
                        }

                        TheCreator.trips(tripID, forTripID, tripDestination, unitForTrip, tripClass, Trips); // Se crea o modifica el archivo de viajes.
                        readFile = Reader.reading(usersPath, usersFile); // Se lee el archivo de usuarios.

                        for (int i = 0; i < readFile.size(); i++) {
                            if (forTripID == Integer.parseInt(readFile.get(i))) { // Si coinciden las IDs:
                                readFile.set(i + 4, (Integer.parseInt(readFile.get(i + 4)) + 1 + "")); // Aumenta el número de viajes realizados.
                                break;
                            } else {
                                i += 5; // Aumenta en 5 para seguir comparando.
                            }
                        }

                        Modifier.toModify(readFile, Users); // Modifica el archivo de usuarios.
                        ticket = ToGet.ticketNumber(PathStorage.getSalesPath(), PathStorage.getSalesFile(), Sales); // Obtiene el número de ticket.
                        TheCreator.sales(ticket, forTripID, tripDestination, Sales); // Crea o modifica el archivo de ventas.
                    } else { // Si no:
                        System.out.println("No hay autobuses disponibles. Presione 'Enter' para continuar.");
                        System.in.read();
                    }
                } else { // Si no:
                    System.out.println("No hay destinos disponibles. Presione 'Enter' para continuar.");
                    System.in.read();
                }
            }
        } else { // Si no:
            System.out.println("No hay usuarios registrados aún. Presione 'Enter' para continuar.");
            System.in.read();
        }

    }

    public static void toDelete(ArrayList<String> readFile, String tripsPath, String tripsFile, File Trips) throws IOException {
        // Elimina un viaje.
        System.out.println("Ingresa tu ID de usuario: ");
        boolean userExists = false;
        boolean tripExists = false;
        int ID_forTrip;
        try { // Si el usuario ingresa un valor no entero:
            ID_forTrip = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Parámetro inválido: solo se admiten números enteros. Inténtelo de nuevo: ");
            ID_forTrip = scn.nextInt();
        }
        for (int i = 0; i < readFile.size(); i++) {
            if (ID_forTrip == (Integer.parseInt(readFile.get(i)))) { // Compara la ID ingresada con las IDs existentes.
                userExists = true; // El usuario existe.
                break;
            } else { // Si no:
                i += 5; // Se incrementa en 5 para seguir comparando.
            }
        }
        if (!userExists) { // Si el usuario no existe:
            System.out.println("El usuario no existe.");
            System.out.println("Presione 'Enter' para continuar.");
            System.in.read();
        } else { // Si existe:
            readFile = Reader.reading(tripsPath, tripsFile); // Lee el archivo de viajes.
            for (int i = 1; i < readFile.size(); i++) {
                if (ID_forTrip == (Integer.parseInt(readFile.get(i)))) { // Si coinciden las IDs:
                    System.out.println("Se encontró la siguiente reservación: "); // Se imprime la información.
                    System.out.println("ID de viaje: " + readFile.get(i - 1));
                    System.out.println("Unidad de autobús: " + readFile.get(i + 1));
                    System.out.println("Destino: " + readFile.get(i + 2));
                    System.out.println("----------------------");
                    i += 5; // Se aumenta en 5 para seguir comparando.
                    tripExists = true;
                } else { // Si no:
                    if (i == (readFile.size() - 4)) { // Si es la última iteración:
                        break;
                    } else { // Si no:
                        i += 5; // Aumenta en 5 para seguir comparando las IDs.
                    }
                }
            }
            if (!tripExists) { // Si no existen viajes:
                System.out.println("No se encontraron viajes programados.");
            } else { // Si existe:
                System.out.println("Ingrese el ID del viaje que desea cancelar: ");
                int tripToDel;
                try {
                    tripToDel = scn.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Parámetro inválido: solo se admiten números enteros. Inténtelo de nuevo: ");
                    tripToDel = scn.nextInt();
                }
                for (int i = 0; i < readFile.size(); i++) {
                    if (tripToDel == (Integer.parseInt(readFile.get(i)))) { // Compara los viajes.
                        for (int j = 0; j < 6; j++) {
                            readFile.remove(i); // Elimina la misma línea seis veces.
                        }
                        break;
                    } else {
                        i += 5; // Aumenta en 5 para seguir comparando.
                    }
                }
                Modifier.toModify(readFile, Trips); // Modifica el archivo.
            }
        }
    }
}

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.