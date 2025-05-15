package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.ErrorAnadirAnimal;
import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;
import exception.ErrorListarAnimal;
import modelo.Animal;
import utilidades.ConexionBD;

public class AnimalDAO {

    public boolean insertarAnimal(Animal animal) throws ErrorEscrituraLog, ErrorConexionBD, ErrorAnadirAnimal {
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
            throw new ErrorAnadirAnimal("❌ Error añadir el animal a la base de datos");
        }
    }
    
    //CONSULTAR ANIMALES
    public ArrayList<Animal> listarAnimales() throws ErrorEscrituraLog, ErrorConexionBD, ErrorListarAnimal {
        ArrayList<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM animales";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal(
                    rs.getInt("id"),
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
            throw new ErrorListarAnimal("❌ Error al obtener los animales para listar");
        }

        return lista;
    }
    
    public static boolean borrarAnimal(int id) throws ErrorEscrituraLog, ErrorConexionBD {
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
    