package controlador;

import modelo.Empleado;
import utilidades.ConexionBD;
import utilidades.LoggerSistema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoControlador {

    public void agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, rol, telefono, fecha_contratacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTelefono());
            ps.setDate(4, Date.valueOf(empleado.getFechaContratacion()));

            ps.executeUpdate();
            LoggerSistema.registrar("admin", "Registró nuevo empleado: " + empleado.getNombre());
            System.out.println("Empleado registrado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    public List<Empleado> obtenerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setRol(rs.getString("rol"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setFechaContratacion(rs.getDate("fecha_contratacion").toLocalDate());

                empleados.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener empleados: " + e.getMessage());
        }

        return empleados;
    }

    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Empleado con ID " + id + " eliminado correctamente.");
                return true; // El empleado fue eliminado correctamente
            } else {
                System.out.println("No se encontró un empleado con ese ID.");
                return false; // No se encontró el empleado con el ID especificado
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el empleado: " + e.getMessage());
            return false; // Hubo un error al intentar eliminar
        }
    }
}