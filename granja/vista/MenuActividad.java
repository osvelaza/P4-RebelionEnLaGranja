package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Actividad;
import modelo.Animal;
import utilidades.ConexionBD;

public class MenuActividad {

    public static void registrarActividad(Actividad act) {
        String sql = "INSERT INTO actividad (fecha, hora, tipo_actividad, id_empleado,id) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, act.getFecha());
            stmt.setTime(2, act.getHora());
            stmt.setString(3, act.getTipoActividad());
            stmt.setInt(4, act.getEmpleadoId());

            stmt.executeUpdate();
            System.out.println("✅ Animal registrado correctamente.");
            utilidades.LoggerSistema.registrar(null,"registrar");
            //Implementar usuarios
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar el animal:");
            e.printStackTrace();
        }
    }

    public static void mostrarActividades() {
        ArrayList<Animal> lista = dao.listarAnimales();

        System.out.println("\n** Lista de Animales **");
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay animales registrados.");
        } else {
            for (Animal animal : lista) {
                System.out.println(animal); // Asegúrate de que `Animal` tenga un método `toString()` adecuado
            }
        }
    }

    public static void borrarActividad() {
        System.out.println("🗑 Función de borrar actividad (pendiente de implementar)");
        // Aquí irá el código real para borrar una actividad
    }
}