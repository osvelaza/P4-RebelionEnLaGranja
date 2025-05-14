package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicioBD {
    public static void inicio(){
        try {
            Connection conexion = null;
        String url = "jdbc:mysql://localhost:3305";
        String usuario = "root";
        String contrasena = "urano";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos.");
            e.printStackTrace();
        }
            Connection conn=conexion;
            String q1 = "create schema `granja` ;\n" + //
                                "create table animales(\n" + //
                                "\tespecie varchar(25),\n" + //
                                "    raza varchar(25),\n" + //
                                "    fecha_nacimiento date,\n" + //
                                "    arete varchar(25),\n" + //
                                "    estado_salud varchar(25),\n" + //
                                "    ubicacion varchar(25),\n" + //
                                "    estado_actual varchar(25)\n" + //
                                ");";
            PreparedStatement stmt = conn.prepareStatement(q1);
            int x = stmt.executeUpdate(q1);
            if (x > 0)            
                System.out.println("Password Successfully Updated");            
            else           
                System.out.println("ERROR OCCURRED :(");
             
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Error al inicializar.");
            e.printStackTrace();
        }
    } 
}
