package finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class Destinos {
    private static Scanner scn = new Scanner(System.in);

    public static void toAdd(String pathName, String fileName, File Destinations) throws IOException { // Agrega un destino.
        boolean destExists = false;
        String dest;
        if (Destinations.exists()) { // Si el archivo existe:
            ArrayList<String> readFile = Reader.reading(pathName, fileName); // Lee el archivo.
            System.out.println("Ingrese el nuevo destino: ");
            dest = scn.nextLine();
            for (int i = 0; i < readFile.size(); i++) { // Evalúa si el archivo existe.
                if (dest.equals(readFile.get(i))) {
                    System.out.println("El destino ingresado ya existe.");
                    destExists = true;
                    break;
                }
            }
        } else { // Si el archivo no existe:
            System.out.println("Ingrese el nuevo destino: ");
            dest = scn.nextLine();
        }
        if (!destExists) { // Si el destino existe:
            TheCreator.destinations(dest, Destinations); // Crea o modifica el archivo.
        }
    }

    public static void toDelete(ArrayList<String> readFile, File Destinations) throws IOException { // Elimina un destino existente.
        for (int i = 0; i < readFile.size(); i++) { // Imprime todos los destinos.
            if (i == (readFile.size() - 1)) {
                System.out.println(readFile.get(i) + ".");
            } else {
                System.out.print(readFile.get(i) + ", ");
            }
        }
        System.out.println("Escriba el número correspondiente al destino que desea eliminar: ");
        int toDelete;
        try {
            toDelete = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Parámetro inválido: solo se admiten números enteros. Inténtelo de nuevo: ");
            toDelete = scn.nextInt();
        }
        for (int i = 0; i < readFile.size(); i++) {
            if (toDelete == i + 1) { // Si coincide:
                readFile.remove(i); // Elimina el destino.
            }
        }
        System.out.println("El destino ha sido eliminado. Presione 'Enter' para continuar: ");
        System.in.read();
        Modifier.toModify(readFile, Destinations); // Modifica el archivo de destinos.
    }
}
