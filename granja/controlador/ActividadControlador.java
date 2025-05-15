package controlador;

import modelo.Actividad;
import utilidades.ConexionBD;
import utilidades.LoggerSistema;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import exception.ErrorEscrituraLog;

public class ActividadControlador {

    public void registrarActividad(Actividad actividad) throws ErrorEscrituraLog {
        String sql = "INSERT INTO Actividad (fecha, hora, tipo_actividad, empleado_id, animal_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, actividad.getFecha());
            ps.setTime(2, Time.valueOf(actividad.getHora()));
            ps.setString(3, actividad.getTipoActividad());
            ps.setInt(4, actividad.getEmpleadoId());

            if (actividad.getAnimalId() != null) {
                ps.setInt(5, actividad.getAnimalId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.executeUpdate();
            LoggerSistema.registrar("Registró actividad: " + actividad.getTipoActividad());
            System.out.println("Actividad registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
        }
    }
/*
    public ArrayList<Actividad> obtenerPorFecha(LocalDate fecha) {
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT * FROM Actividad WHERE fecha = ?";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                act.setId(rs.getInt("id"));
                act.setFecha(rs.getDate("fecha").toLocalDate());
                act.setHora(rs.getTime("hora").toLocalTime());
                act.setTipoActividad(rs.getString("tipo_actividad"));
                act.setEmpleadoId(rs.getInt("empleado_id"));
                int animalId = rs.getInt("animal_id");
                if (!rs.wasNull()) act.setAnimalId(animalId);
                Actividad act = new Actividad();
                actividades.add(act);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar actividades: " + e.getMessage());
        }

        return actividades;
    }
        */
}