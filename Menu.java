// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

package finalProject;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Menu {
    private static Scanner scn = new Scanner(System.in);
    private static int ID = 1;
    private static int readID = 0;
    private static int unit = 1;
    private static int readUnit = 0;
    private static int tripID = 1;
    private static int readTripID = 0;
    private static int ticket = 1;
    private static int readTicket = 0;
    private static boolean control = true;
    private static File Users = new File(PathStorage.getUsersPath() + PathStorage.getUsersFile());
    // Archivo de los usuarios.
    private static File Autobuses = new File(PathStorage.getBusPath() + PathStorage.getBusFile());
    // Archivo de los autobuses.
    private static File Destinations = new File(PathStorage.getDestPath() + PathStorage.getDestFile());
    // Archivo de los destinos.
    private static File Trips = new File(PathStorage.getTripsPath() + PathStorage.getTripsFile());
    // Archivo de los viajes hechos.
    private static File Sales = new File(PathStorage.getSalesPath() + PathStorage.getSalesFile());
    // Archivo de ventas.
    private static File Central = new File(PathStorage.getCentralPath() + PathStorage.getCentralFile());
    // Archivo de la central.

    public static void main(String[] args) throws IOException {
        // Creación de los directorios.
        TheCreator.toMakeDir(PathStorage.getCurrentUsersHomeDir(), PathStorage.getFolderName());
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Usuarios\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Autobuses\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Destinos\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Viajes\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Ventas\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Central\\");
        int option = -2;
        do {
            // Primero se evalúa si el archivo de la central existe. Si no, te obliga a crearlo.
            try {
                if (option == -1)
                    scn.nextLine();
                option = -1;
                if (Central.exists()) {
                    System.out.println("Elige una opción:\n1. Usuarios.\n2. Viajes.\n3. Autobuses.\n4. Destinos.\n" +
                            "5. Control de ventas.\n6. Central.\n7. Acerca de.\n0. Salir.");
                    option = scn.nextInt();
                } else {
                    option = 6;
                }
                // Menú principal.
                switch (option) {
                    case 1:
                        String opt;
                        do {
                            System.out.println("Elige una opción:\na. Agregar usuarios.\nb. Modificar usuario.\n" +
                                    "c. Consultar información de un usuario.\nd. Eliminar usuario.\ne. Regresar.");
                            opt = scn.next();
                            switch (opt.toLowerCase()) {
                                case "a":
                                    // Agregar usuarios. Primero consigue la ID mayor actual, la incrementa y la asigna al usuario.
                                    ID = ToGet.ID(PathStorage.getUsersPath(), PathStorage.getUsersFile(), readID, ID, Users);
                                    Usuarios.toAdd(ID, Users);
                                    break;
                                case "b":
                                    // Llama al método para modificar usuarios.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toModify(readFile, Users);
                                    break;
                                case "c":
                                    // Llamada al método para consultar información.
                                    readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toConsult(readFile);
                                    break;
                                case "d":
                                    // Elimina a un usuario con su ID por medio del método toDelete.
                                    readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toDelete(readFile, Users);
                                    break;
                                case "e":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 2:
                        String tripOpt;
                        do {
                            System.out.println("Elija una opción:\na. Agregar reservación.\nb. Eliminar reservación.\nc. Regresar.");
                            tripOpt = scn.next();
                            switch (tripOpt.toLowerCase()) {
                                case "a":
                                    // Agregar una reservación.
                                    tripID = ToGet.tripID(PathStorage.getTripsPath(), PathStorage.getTripsFile(), readTripID, tripID, Trips);
                                    Viajes.toAdd(PathStorage.getUsersPath(), PathStorage.getUsersFile(), PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getBusPath(), PathStorage.getBusFile(), tripID, ticket, Sales, Users, Trips, Destinations, Autobuses);
                                    break;
                                case "b":
                                    // Eliminar una reservación.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Viajes.toDelete(readFile, PathStorage.getTripsPath(), PathStorage.getTripsFile(), Trips);
                                    break;
                                case "c":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 3:
                        String busOpt;
                        do {
                            System.out.println("Elija una opción: \na. Agregar autobuses.\nb. Modificar un autobús.\nc. Quitar un autobús.\nd. Regresar.");
                            busOpt = scn.next();
                            switch (busOpt.toLowerCase()) {
                                case "a":
                                    // Agregar un autobús. Primero consigue la mayor unidad actual y la incrementa.
                                    unit = ToGet.unit(PathStorage.getBusPath(), PathStorage.getBusFile(), readUnit, unit, Autobuses);
                                    Bus.toAdd(unit, Autobuses);
                                    break;
                                case "b":
                                    // Modifica un autobús por el método toModify.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getBusPath(), PathStorage.getBusFile());
                                    Bus.toModify(readFile, Autobuses);
                                    break;
                                case "c":
                                    // Elimina un autobús con el método toDelete.
                                    readFile = Reader.reading(PathStorage.getBusPath(), PathStorage.getBusFile());
                                    Bus.toDelete(readFile, Autobuses);
                                    break;
                                case "d":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 4:
                        String destOpt;
                        do {
                            System.out.println("Elige una opción:\na. Agregar destino.\nb. Quitar destino.\nc. Regresar.");
                            destOpt = scn.next();
                            switch (destOpt.toLowerCase()) {
                                case "a":
                                    // Agrega un destino a la central con el método toAdd.
                                    Destinos.toAdd(PathStorage.getDestPath(), PathStorage.getDestFile(), Destinations);
                                    break;
                                case "b":
                                    // Elimina un destino existente de la central con el método toDelete.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getDestPath(), PathStorage.getDestFile());
                                    Destinos.toDelete(readFile, Destinations);
                                    break;
                                case "c":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 5:
                        String salesOpt;
                        do {
                            System.out.println("Elija una opción:\na. Número de boletos vendidos totales.\nb. Número de boletos " +
                                    "vendidos por día.\nc. Número de boletos vendidos en un rango de fechas.\nd. Número de boletos " +
                                    "vendidos por rango de edad.\ne. Máximo, mínimo y promedio de ventas en un rango de fechas.\n" +
                                    "f. Regresar.");
                            salesOpt = scn.next();
                            switch (salesOpt.toLowerCase()) {
                                case "a":
                                    // Llama al método totalSales, que lleva a un submenú extra con opciones específicas.
                                    ArrayList<File> readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.totalSales(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "b":
                                    // Llama al método salesByDay, que lleva a un submenú extra con opciones específicas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByDay(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "c":
                                    // Llama al método salesByRange, que lleva a un submenú extra con opciones específicas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByRange(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "d":
                                    // Llama al método salesByAge, que lleva a un submenú extra con opciones específicas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByAge(readFolder, PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "e":
                                    // Llama al método maxMinAvByRange, que lleva a un submenú extra con opciones específicas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.maxMinAvByRange(readFolder);
                                    break;
                                case "f":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 6:
                        String sixthOpt = null;
                        ArrayList<String> readFile;
                        // Primero evalúa la existencia de Central. Si no, te obliga a crearlo.
                        if (Central.exists()) {
                            System.out.println("Elija una opción:\na. Modificar número de salas de recepción.\n" +
                                    "b. Modificar número de asientos en recepción.\nc. Modificar número de taquillas.\n" +
                                    "d. Modificar comodidades.\ne. Regresar.");
                            sixthOpt = scn.next();
                            switch (sixthOpt.toLowerCase()) {
                                case "a":
                                    // Modifica el número de salas.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el número de salas: ");
                                    int lobby = scn.nextInt();
                                    readFile.set(3, lobby + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el número de salas de recepción." +
                                            " Presione 'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "b":
                                    // Modifica el número de asientos.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el número de asientos en recepción: ");
                                    readFile.set(4, scn.nextInt() + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el número de asientos. Presione " +
                                            "'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "c":
                                    // Modifica el número de taquillas.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el número de taquillas: ");
                                    readFile.set(5, scn.nextInt() + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el número de taquillas. Presione " +
                                            "'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "d":
                                    // Modifica las comodidades.
                                    System.out.println("Elija una opción:\n1. Modificar número de televisiones.\n2. " +
                                            "Aire acondicionado.");
                                    switch (scn.nextInt()) {
                                        case 1:
                                            // Televisión.
                                            readFile = Reader.reading(Central);
                                            System.out.println("¿Hay televisión en recepción? Escriba 1 para 'Sí'" +
                                                    " o 2 para 'No'.");
                                            if (scn.nextInt() == 1)
                                                readFile.set(6, "true");
                                            else
                                                readFile.set(6, "false");
                                            break;
                                        case 2:
                                            // Aire acondicionado.
                                            readFile = Reader.reading(Central);
                                            System.out.println("¿Hay aire acondicionado en recepción? Escriba 1 para 'Sí'" +
                                                    " o 2 para 'No'.");
                                            if (scn.nextInt() == 1)
                                                readFile.set(7, "true");
                                            else
                                                readFile.set(7, "false");
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case "e":
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opción inválida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } else {
                            // Proceso de creación del archivo Central.
                            boolean hasTV = false, hasAC = false; int busNum, baths, lobby, seats, taqNum;
                            System.out.println("Ingrese el nombre de la central: ");
                            String name = scn.nextLine();
                            scn.nextLine();
                            System.out.println("Ingrese el número de autobuses: ");
                            try {
                                busNum = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un parámetro inválido: solo se admiten números " +
                                        "enteros. Vuelva a intentarlo: ");
                                busNum = scn.nextInt();
                            }
                            System.out.println("Ingrese el número de baños: ");
                            try {
                                baths = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un parámetro inválido: solo se admiten números " +
                                        "enteros. Vuelva a intentarlo: ");
                                baths = scn.nextInt();
                            }
                            System.out.println("Ingrese el número de salas: ");
                            try {
                                lobby = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un parámetro inválido: solo se admiten números " +
                                        "enteros. Vuelva a intentarlo: ");
                                lobby = scn.nextInt();
                            }
                            System.out.println("Ingrese el número de asientos por sala: ");
                            try {
                                seats = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un parámetro inválido: solo se admiten números " +
                                        "enteros. Vuelva a intentarlo: ");
                                seats = scn.nextInt();
                            }
                            System.out.println("Ingrese el número de taquillas: ");
                            try {
                                taqNum = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un parámetro inválido: solo se admiten números " +
                                        "enteros. Vuelva a intentarlo: ");
                                taqNum = scn.nextInt();
                            }
                            System.out.println("Escriba 1 si tiene TV, o 2 si no: ");
                            if (scn.nextInt() == 1)
                                hasTV = true;
                            System.out.println("Escriba 1 si tiene AC, o 2 si no: ");
                            if (scn.nextInt() == 1)
                                hasAC = true;
                            TheCreator.central(name, busNum, baths, lobby, seats, taqNum, hasTV, hasAC, Central);
                        }
                        break;
                    case 7:
                        System.out.println("Proyecto final para Programación para Ingenieros I: Central de Autobuses.\n" +
                                "Integrantes:\n-Forte López, Gabriel Arturo.\n-Ramos Laborín, Saúl Alberto.\n" +
                                "-Zatarain Palma, Carlos Alberto.");
                        System.out.println("Presione 'Enter' para continuar.");
                        System.in.read();
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                option = -1;
                System.out.println("Ha ingresado una opción inválida. Presione una 'Enter' " +
                        "para continuar y vuelva a intentarlo.");
                System.in.read();
            }
        } while (option != 0);
    }
}