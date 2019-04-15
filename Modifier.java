package finalProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Modifier {

// Autor: Saúl Alberto Ramos Laborín.
// Expediente: 217200160.
// Fecha: 17-05-2018.

    public static boolean toModify(ArrayList<String> written, File toModify) {
        boolean isWritten = true;
        FileWriter toModInfo;
        try {
            toModInfo = new FileWriter(toModify, false);
            for (int i = 0; i < written.size(); i++) {
                toModInfo.write(written.get(i) + "\r\n");
            }
            toModInfo.close();
        } catch (IOException e) {
            e.printStackTrace();
            isWritten = false;
        }
        return isWritten;
    }

}
