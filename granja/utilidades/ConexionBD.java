package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection conectar(){
        Connection conexion = null;
        String url = "jdbc:mysql://localhost:3305/granja";
        String usuario = "root";
        String contrasena = "password";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos. Verifica que la base de datos esté iniciada y vuelva a ejecutar el programa. " + e.getStackTrace());
        }
        return conexion;
    }
}