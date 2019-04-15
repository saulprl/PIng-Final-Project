// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

package finalProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TheCreator {

    static void toMakeDir(String pathName, String dirName) {
        File Directory = new File(pathName + dirName);
        if (!Directory.exists()) Directory.mkdir();
    }

    static boolean central(String name, int busNum, int baths, int lobby, int seats, int taqNum, boolean hasTV, boolean hasAC, File CentralFile) {
        boolean isWritten = true;
        FileWriter Central;
        try {
            Central = new FileWriter(CentralFile, true);
            Central.write(name + "\r\n");
            Central.write(busNum + "\r\n");
            Central.write(baths + "\r\n");
            Central.write(lobby + "\r\n");
            Central.write(seats + "\r\n");
            Central.write(taqNum + "\r\n");
            Central.write(hasTV + "\r\n");
            Central.write(hasAC + "\r\n");
            Central.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("La central ha sido creada. Ahora puede comenzar a usar el sistema. Presione 'Enter' para " +
                "continuar.");
        return isWritten;
    }

    static boolean buses(String destino, String model, int unidad, int seats, boolean hasTV, boolean hasWiFi, boolean hasAC, File Buses) {
        boolean isWritten = true;
        FileWriter BusAspects;
        String clase;
        if (hasTV && hasWiFi && hasAC)
            clase = "VIP";
        else
            clase = "Estándar";
        try {
            BusAspects = new FileWriter(Buses, true);
            BusAspects.write(unidad + "\r\n");
            BusAspects.write(destino + "\r\n");
            BusAspects.write(clase + "\r\n");
            BusAspects.write(seats + "\r\n");
            BusAspects.write(model + "\r\n");
            BusAspects.write("--------------------------------\r\n");
            BusAspects.close();
        } catch (IOException e) {
            e.printStackTrace();
            isWritten = false;
        }
        return isWritten;
    }

    public static boolean users(String name, String gender, int age, int tripNum, int ID, File Usuarios) {
        boolean isWritten = true;
        FileWriter UserInfo;
        try {
            UserInfo = new FileWriter(Usuarios, true);
            UserInfo.write(ID + "\r\n");
            UserInfo.write(name + "\r\n");
            UserInfo.write(gender + "\r\n");
            UserInfo.write(age + "\r\n");
            UserInfo.write(tripNum + "\r\n");
            UserInfo.write("--------------------------------\r\n");
            UserInfo.close();
        } catch (IOException e) {
            e.printStackTrace();
            isWritten = false;
        }
        return isWritten;
    }

    public static boolean destinations(String destino, File Destinos) {
        boolean isWritten = true;
        FileWriter destInfo;
        try {
            destInfo = new FileWriter(Destinos, true);
            destInfo.write(destino + "\r\n");
            destInfo.close();
        } catch (IOException e) {
            e.printStackTrace();
            isWritten = false;
        }
        return isWritten;
    }

    public static void trips(int tripID, int ID, String destino, int unit, String claseBus, File Viajes) {
        FileWriter tripInfo;
        try {
            tripInfo = new FileWriter(Viajes, true);
            tripInfo.write(tripID + "\r\n");
            tripInfo.write(ID + "\r\n");
            tripInfo.write(unit + "\r\n");
            tripInfo.write(destino + "\r\n");
            tripInfo.write(claseBus + "\r\n");
            tripInfo.write("---------------\r\n");
            tripInfo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sales(int ticket, int userID, String destino, File Ventas) {
        FileWriter SalesInfo;
        try {
            SalesInfo = new FileWriter(Ventas, true);
            SalesInfo.write(ticket + "\r\n");
            SalesInfo.write(userID + "\r\n");
            SalesInfo.write(destino + "\r\n");
            SalesInfo.write("---------------\r\n");
            SalesInfo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
