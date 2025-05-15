package utilidades;
 
import java.io.FileWriter;

import java.io.IOException;

import java.sql.*;

import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;
 
public class ExportadorCSV {
 
    public static void exportarTabla(String nombreTabla, String rutaArchivo) throws ErrorEscrituraLog, ErrorConexionBD {
        String sql = "SELECT * FROM " + nombreTabla;
 
        try (Connection conn = ConexionBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            FileWriter writer = new FileWriter(rutaArchivo)) {
            ResultSetMetaData metaData = rs.getMetaData();

            int columnas = metaData.getColumnCount();
            // Escribir cabecera
            for (int i = 1; i <= columnas; i++) {
                writer.append(metaData.getColumnName(i));
                if (i < columnas) writer.append(",");
            }

            writer.append("\n");
            // Escribir datos
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    writer.append(rs.getString(i));
                    if (i < columnas) writer.append(",");
                }
                writer.append("\n");
            }
            System.out.println("Datos exportados correctamente a " + rutaArchivo);
        } catch (SQLException | IOException e) {
            System.out.println("Error al exportar CSV: " + e.getMessage());
        }
    }
}