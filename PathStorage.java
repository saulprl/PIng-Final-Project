package finalProject;

import java.io.File;

class PathStorage {
    private static String usersFile = "Usuarios.txt"; // Nombre del archivo de usuarios.
    private static String busFile = "Autobuses.txt"; // Nombre del archivo de autobuses.
    private static String destFile = "Destinos.txt"; // Nombre del archivo de destinos.
    private static String tripsFile = "Viajes.txt"; // Nombre del archivo de viajes.
    private static String salesFile = "Ventas " + ToGet.date() + ".txt"; // Nombre de cada archivo de ventas.
    private static String centralFile = "Central.txt"; // Nombre del archivo de la Central.
    private static String currentUsersHomeDir = System.getProperty("user.home"); // Directorio del usuario.
    private static String folderName = File.separator + "Central de Autobuses\\"; // Nombre de la carpeta raíz.
    private static String fullDirectory = currentUsersHomeDir + folderName; // Directorio completo.
    private static String salesPath = fullDirectory + File.separator + "Ventas\\"; // Carpeta de ventas.
    private static String tripsPath = fullDirectory + File.separator + "Viajes\\"; // Carpeta de viajes.
    private static String destPath = fullDirectory + File.separator + "Destinos\\"; // Carpeta de destinos.
    private static String busPath = fullDirectory + File.separator + "Autobuses\\"; // Carpeta de autobuses.
    private static String usersPath = fullDirectory + File.separator + "Usuarios\\"; // Carpeta de usuarios.
    private static String centralPath = fullDirectory + File.separator + "Central\\"; // Carpeta de la Central.
    private static File SalesFolder = new File(salesPath); // Declarada la carpeta de Ventas.

    public static String getUsersPath() {
        return usersPath;
    }

    public static String getUsersFile() {
        return usersFile;
    }

    public static String getBusPath() {
        return busPath;
    }

    public static String getBusFile() {
        return busFile;
    }

    public static String getDestPath() {
        return destPath;
    }

    public static String getDestFile() {
        return destFile;
    }

    public static String getTripsPath() {
        return tripsPath;
    }

    public static String getTripsFile() {
        return tripsFile;
    }

    public static String getSalesPath() {
        return salesPath;
    }

    public static String getSalesFile() {
        return salesFile;
    }

    public static String getFolderName() {
        return folderName;
    }

    public static String getCurrentUsersHomeDir() {
        return currentUsersHomeDir;
    }

    public static String getFullDirectory() {
        return fullDirectory;
    }

    public static File getSalesFolder() {
        return SalesFolder;
    }

    public static String getCentralFile() { return centralFile; }

    public static String getCentralPath() { return centralPath; }
}

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.