package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Actividad;
import utilidades.ConexionBD;

public class MenuActividad {

    public static void registrarActividad(Actividad act){
        String sql = "INSERT INTO actividades (fecha, hora, tipo_actividad, id_empleado,id_animal) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, act.getFecha());
            stmt.setObject(2, act.getHora());
            stmt.setString(3, act.getTipoActividad());
            stmt.setInt(4, act.getEmpleadoId());

            stmt.executeUpdate();
            System.out.println("✅ Animal registrado correctamente.");
            utilidades.LoggerSistema.registrar("registrar");
            //Implementar usuarios
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar la actividad:"+e.getMessage());
        }
    }

    public static void mostrarActividades(LocalDate fecha){
        ArrayList <Actividad> lista=controlador.ActividadControlador.obtenerPorFecha(fecha);

        System.out.println("\n** Lista de Animales **");
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay animales registrados.");
        } else {
            for (Actividad animal : lista) {
                System.out.println(animal); // Asegúrate de que `Animal` tenga un método `toString()` adecuado
            }
        }
    }

    public static void borrarActividad() {
        System.out.println("🗑 Función de borrar actividad (pendiente de implementar)");
        // Aquí irá el código real para borrar una actividad
    }
}