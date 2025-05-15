package controlador;

import modelo.Empleado;
import utilidades.ConexionBD;
import utilidades.LoggerSistema;

import java.sql.*;
import java.util.ArrayList;

import exception.ErrorAnadeEmp;
import exception.ErrorBorrEmp;
import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;
import exception.ErrorListarEmp;

public class EmpleadoControlador {

    public void agregarEmpleado(Empleado empleado) throws ErrorEscrituraLog, ErrorConexionBD, ErrorAnadeEmp {
        String sql = "INSERT INTO empleados (nombre, rol, telefono, fecha_contratacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTelefono());
            ps.setDate(4, Date.valueOf(empleado.getFechaContratacion()));

            ps.executeUpdate();
            LoggerSistema.registrar("Registró nuevo empleado: " + empleado.getNombre());
            System.out.println("Empleado registrado correctamente.");

        } catch (SQLException e) {
            throw new ErrorAnadeEmp("❌ Error al Error al insertar empleado.");
        }
    }

    public ArrayList<Empleado> obtenerTodos() throws ErrorEscrituraLog, ErrorConexionBD, ErrorListarEmp {
        ArrayList<Empleado> empleados = new ArrayList<>();
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
            throw new ErrorListarEmp("❌ Error al obtener empleados.");
        }

        return empleados;
    }

    public boolean eliminarEmpleado(int id) throws ErrorEscrituraLog, ErrorConexionBD, ErrorBorrEmp {
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
            throw new ErrorBorrEmp("❌ Error al eliminar el empleado.");
        }
    }
}