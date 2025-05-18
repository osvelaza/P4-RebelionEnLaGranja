package vista;

import controlador.ActividadControlador;
import controlador.AnimalDAO;
import controlador.EmpleadoControlador;
import enumJ.activDiaria;
import enumJ.saludAnimal;
import modelo.Actividad;
import modelo.Animal;
import modelo.Empleado;
import utilidades.LoggerSistema;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuActividad {

    private static final Scanner sc = new Scanner(System.in);
    private static final ActividadControlador controlador = new ActividadControlador();

    public static void registrarActividad() {
        System.out.println("\n📝 REGISTRO DE ACTIVIDAD");

        try {
            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());

            System.out.print("Hora (HH:MM): ");
            LocalTime hora = LocalTime.parse(sc.nextLine());

            System.out.print("Tipo de actividad: ");
            String tipo;
            do {
                System.out.print("Introduce el tipo de actividad: ");
                for (activDiaria value : activDiaria.values()){
                    System.out.print(value.toString()+", ");
                }
                System.out.println();
                tipo = sc.nextLine();
            } while (!enumJ.general.checkActivDiaria(tipo));

            System.out.print("ID del empleado: ");
            int empleadoId=0;
            ArrayList<Empleado> empleados=new ArrayList<>();
            empleados=EmpleadoControlador.obtenerTodos();
            if(empleados.isEmpty()){
                System.out.println("⚠️ No hay empleados registrados.");
            }else{
                for (Empleado e : empleados) {
                    System.out.println(e);
                }
                System.out.print("Introduce el ID del empleado:");
                empleadoId = Integer.parseInt(sc.nextLine());
            }

            System.out.print("IDs de los animales (separados por comas, o pulsa Enter para omitir): ");
            String inputAnimales="";
            ArrayList<Animal> animalesList=new ArrayList<>();
            animalesList=AnimalDAO.listarAnimales();
            if(animalesList.isEmpty()){
                System.out.println("⚠️ No hay empleados registrados.");
            }else{
                System.out.print("Introduce el ID del los animales separados por comas: ");
                inputAnimales = sc.nextLine();
            }

            Actividad actividad = new Actividad();
            actividad.setFecha(fecha);
            actividad.setHora(hora);
            actividad.setTipoActividad(tipo);
            actividad.setEmpleadoId(empleadoId);

            if (!inputAnimales.isEmpty()) {
                String[] partes = inputAnimales.split(",");
                ArrayList<Integer> animales = new java.util.ArrayList<>();

                for (String parte : partes) {
                    animales.add(Integer.parseInt(parte.trim()));
                }

                actividad.setAnimalesID(animales);
            }

            controlador.registrarActividad(actividad);

        } catch (DateTimeException e) {
            System.out.println("❌ Error al registrar actividad. Revisa los datos introducidos."+e.getMessage());
        }
    }

    public static void mostrarActividades() {
        System.out.println("\n📋 CONSULTA DE ACTIVIDADES");

        try {
            System.out.print("Introduce la fecha límite(YYYY-MM-DD). Se mostrarán todas fechas inferiores.");
            LocalDate fecha = LocalDate.parse(sc.nextLine());

            ArrayList<Actividad> lista = controlador.obtenerPorFecha(fecha);
            if (lista.isEmpty()) {
                System.out.println("No hay actividades para esa fecha.");
            } else {
                for (Actividad act : lista) {
                    System.out.println(act);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar actividades."+e.getMessage());
        }
    }

    public static void eliminarActividad() {
        System.out.println("\n🗑 ELIMINAR ACTIVIDAD");
        try {
            System.out.print("Introduce el ID de la actividad a eliminar: ");
            int id = Integer.parseInt(sc.nextLine());
            controlador.eliminarActividad(id);  // Llama al método del controlador
            LoggerSistema.registrar("Eliminada la actividad con id "+id);
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar actividad. Asegúrate de introducir un número válido."+e.getMessage());
        }
    }
}