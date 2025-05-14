package utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerSistema {
    private static final String RUTA_LOG = "../log.txt";

    public static void registrar(String usuario, String accion) {
        try (FileWriter fw = new FileWriter(RUTA_LOG, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            fw.write("[" + timestamp + "] Usuario: " + usuario + " - Acción: " + accion + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el log. revisar permisos de escritura " + e.getMessage());
        }
    }
}
