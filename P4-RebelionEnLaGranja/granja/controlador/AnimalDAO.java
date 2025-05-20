package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Animal;
import utilidades.ConexionBD;
import utilidades.LoggerSistema;

public class AnimalDAO {

    public boolean insertarAnimal(Animal animal){
        String sql = "INSERT INTO animales (especie, raza, fecha_nacimiento, arete, estado_salud, ubicacion, estado_actual) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getEspecie());
            stmt.setString(2, animal.getRaza());
            stmt.setDate(3, animal.getFechaNacimiento());
            stmt.setString(4, animal.getArete());
            stmt.setString(5, animal.getEstadoSalud());
            stmt.setString(6, animal.getUbicacion());
            stmt.setString(7, animal.getEstadoActual());

            stmt.executeUpdate();
            System.out.println("✅ Animal registrado correctamente.");
            LoggerSistema.registrar("Agregar animal"+System.lineSeparator()+animal);
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error añadir el animal a la base de datos"+e.getMessage());
            return false;
        }
    }
    
    //CONSULTAR ANIMALES
    public static ArrayList<Animal> listarAnimales(){
        ArrayList<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM animales";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n** Lista de Animales **");
            while (rs.next()) {
                Animal animal = new Animal(
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("arete"),
                    rs.getString("estado_salud"),
                    rs.getString("ubicacion"),
                    rs.getString("estado_actual")
                );
                lista.add(animal);
                System.out.println("Animal: "+rs.getInt("id"));
                System.out.println(animal);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener los animales para listar"+e.getMessage());
        }
        return lista;
    }

    public static void camEstadoBD(int id,String estado){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "UPDATE animales SET estado_salud=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, estado);  // Aquí ponemos el string de la salud
            stmt.setInt(2   , id);  // Aquí ponemos el id del animal a editar
            stmt.executeUpdate();
            utilidades.LoggerSistema.registrar("Cambiar estado de salud del animal "+id+" a "+estado);
        } catch (SQLException e) {  
            System.out.println("❌ Error al conectar con la base de datos. "+e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al cerrar la conexión. s. "+e.getMessage());
            }
        }
    }
    
    public static boolean borrarAnimal(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;

        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM animales WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);  // Aquí ponemos el id del animal a borrar

            int filasAfectadas = stmt.executeUpdate(); // Ejecutamos la consulta
            if (filasAfectadas > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos."+e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al cerrar la conexión"+e.getMessage());
            }
        }
        return eliminado;
    }
}
