package finalProject;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

class Reader {
    static ArrayList<String> reading(String path, String fileName) throws IOException{
        String inputEncoding = "ISO-8859-1";

        BufferedReader input;

        input = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileName), inputEncoding));
        String line = input.readLine();
        ArrayList<String> fileLines = new ArrayList<>();

        while(line != null){
            fileLines.add(line);
            line = input.readLine();
        }
        input.close();
        return fileLines;
    }

    static ArrayList<String> reading(File fileName) throws IOException {
        String inputEncoding = "ISO-8859-1";
        BufferedReader input;
        input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), inputEncoding));
        String line = input.readLine();
        ArrayList<String> fileLines = new ArrayList<>();
        while(line != null){
            fileLines.add(line);
            line = input.readLine();
        }
        input.close();
        return fileLines;
    }

    static File[] readFolder(@NotNull File folderName) {
        return folderName.listFiles();
    }
}
