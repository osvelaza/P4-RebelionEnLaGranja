package controlador;

import modelo.Actividad;
import utilidades.ConexionBD;
import utilidades.LoggerSistema;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ActividadControlador {

    public void registrarActividad(Actividad actividad) {
        String sqlActividad = "INSERT INTO actividades (fecha, hora, tipo_actividad, id_empleado) VALUES (?, ?, ?, ?)";
        String sqlRelacion = "INSERT INTO actividad_animal (id_actividad, id_animal) VALUES (?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement psActividad = conn.prepareStatement(sqlActividad, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psRelacion = conn.prepareStatement(sqlRelacion)) {

            // Insertar actividad
            psActividad.setDate(1, Date.valueOf(actividad.getFecha()));
            psActividad.setTime(2, Time.valueOf(actividad.getHora()));
            psActividad.setString(3, actividad.getTipoActividad());
            psActividad.setInt(4, actividad.getEmpleadoId());
            psActividad.executeUpdate();

            // Obtener el ID generado
            ResultSet rs = psActividad.getGeneratedKeys();
            int idActividad = -1;
            if (rs.next()) {
                idActividad = rs.getInt(1);
            }

            // Insertar relaciones con animales
            if (actividad.getAnimalesID() != null) {
                for (Integer idAnimal : actividad.getAnimalesID()) {
                    psRelacion.setInt(1, idActividad);
                    psRelacion.setInt(2, idAnimal);
                    psRelacion.executeUpdate();
                }
            }

            LoggerSistema.registrar("admin Registró actividad: " + actividad.getTipoActividad());
            System.out.println("Actividad registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
        }
    }

    public ArrayList<Actividad> obtenerPorFecha(LocalDate fecha) {
        Map<Integer, Actividad> actividadMap = new HashMap<>();

        String sql = """
            SELECT a.*, aa.id_animal
            FROM actividades a
            LEFT JOIN actividad_animal aa ON a.id = aa.id_actividad
            WHERE a.fecha <= ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idActividad = rs.getInt("id");

                Actividad act = actividadMap.get(idActividad);
                if (act == null) {
                    act = new Actividad();
                    act.setId(idActividad);
                    act.setFecha(rs.getDate("fecha").toLocalDate());
                    act.setHora(rs.getTime("hora").toLocalTime());
                    act.setTipoActividad(rs.getString("tipo_actividad"));
                    act.setEmpleadoId(rs.getInt("id_empleado"));
                    act.setAnimalesID(new ArrayList<>());
                    actividadMap.put(idActividad, act);
                }

                int idAnimal = rs.getInt("id_animal");
                if (!rs.wasNull()) {
                    act.getAnimalesID().add(idAnimal);
                }
            }
            LoggerSistema.registrar("Agregar actividad");

        } catch (SQLException e) {
            System.out.println("Error al consultar actividades: " + e.getMessage());
        }

        return new ArrayList<>(actividadMap.values());
    }

		public void eliminarActividad(int id) {
		    String sqlEliminarRelacion = "DELETE FROM actividad_animal WHERE id_actividad = ?";
		    String sqlEliminarActividad = "DELETE FROM actividades WHERE id = ?";

		    try (Connection conn = ConexionBD.conectar();
		         PreparedStatement psRelacion = conn.prepareStatement(sqlEliminarRelacion);
		         PreparedStatement psActividad = conn.prepareStatement(sqlEliminarActividad)) {

		        // Borrar relaciones primero (por clave foránea)
		        psRelacion.setInt(1, id);
		        psRelacion.executeUpdate();

		        // Borrar actividad
		        psActividad.setInt(1, id);
		        int filas = psActividad.executeUpdate();

		        if (filas > 0) {
		            LoggerSistema.registrar("admin Eliminó actividad con ID: " + id);
		            System.out.println("✅ Actividad eliminada correctamente.");
		        } else {
		            System.out.println("⚠️ No se encontró una actividad con ese ID.");
		        }

		    } catch (SQLException e) {
		        System.out.println("❌ Error al eliminar actividad: " + e.getMessage());
		    }
		}

	}
