package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.ErrorEscrituraLog;
import modelo.Animal;
import utilidades.ConexionBD;

public class AnimalDAO {

    public boolean insertarAnimal(Animal animal) throws ErrorEscrituraLog {
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
            utilidades.LoggerSistema.registrar("registrar");
            //Implementar usuarios
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar el animal:");
            e.printStackTrace();
            return false;
        }
    }
    
    //CONSULTAR ANIMALES
    public ArrayList<Animal> listarAnimales() {
        ArrayList<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM animales";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal(
                    rs.getInt(1),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("arete"),
                    rs.getString("estado_salud"),
                    rs.getString("ubicacion"),
                    rs.getString("estado_actual")
                );
                lista.add(animal);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener los animales:");
            e.printStackTrace();
        }

        return lista;
    }
    
    public static boolean borrarAnimal(int id) {
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
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eliminado;
    }
}
    