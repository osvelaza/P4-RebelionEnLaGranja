package utilidades;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConexionBD {
    public static Connection conectar() {
        Connection conexion = null;
        String url = "jdbc:mysql://db:3306/granja";
        String usuario = "usuario";
        String contrasena = "1234";
 
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
        }
 
        return conexion;
    }
}