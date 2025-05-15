package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;

public class ConexionBD {
    public static Connection conectar() throws ErrorEscrituraLog, ErrorConexionBD {
        Connection conexion = null;
        String url = "jdbc:mysql://localhost:3305/granja";
        String usuario = "root";
        String contrasena = "urano";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            throw new ErrorConexionBD("❌ Error al conectar con la base de datos.");
        }
        return conexion;
    }
}