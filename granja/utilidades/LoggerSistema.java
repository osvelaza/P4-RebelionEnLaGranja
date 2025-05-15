package utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.ErrorEscrituraLog;

public class LoggerSistema {
    private static final String RUTA_LOG = "../log.txt";

    public static void registrar(String accion) throws ErrorEscrituraLog {
        try (FileWriter fw = new FileWriter(RUTA_LOG, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            fw.write("[" + timestamp + "] - Acción realizada: " + accion + "\n");
        } catch (IOException e) {
            throw new ErrorEscrituraLog("Error al escribir el log. revisar permisos de escritura.");
        }
    }
}
