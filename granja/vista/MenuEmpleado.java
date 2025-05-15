package vista;

import controlador.EmpleadoControlador;
import exception.ErrorAnadeEmp;
import exception.ErrorBorrEmp;
import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;
import exception.ErrorListarEmp;
import modelo.Empleado;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuEmpleado {

    private static Scanner scanner = new Scanner(System.in);
    private static EmpleadoControlador controlador = new EmpleadoControlador();

    public static void mostrarMenu() throws ErrorEscrituraLog, ErrorConexionBD, ErrorAnadeEmp, ErrorListarEmp, ErrorBorrEmp {
        int opcion=1;
        boolean testint;
        do {
            System.out.println("\n--- Menú de Empleados ---");
            System.out.println("1. Registrar nuevo empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Eliminar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");

            do{
                testint=(!scanner.hasNextInt());
                if(testint){
                    System.out.println("Introduce un número entero");
                    scanner.nextLine();
                }else{
                    opcion = scanner.nextInt();
                }
            }while(testint);

            switch (opcion) {
                case 1:
                    registrarEmpleado();
                    break;
                case 2:
                    mostrarEmpleados();
                    break;
                case 3:
                    eliminarEmpleado();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    public static void registrarEmpleado() throws ErrorEscrituraLog, ErrorConexionBD, ErrorAnadeEmp {
        System.out.println("\n~ Registro de nuevo empleado ~");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Rol: ");
        String rol = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fechaContratacion = LocalDate.parse(fechaStr);

        Empleado emp = new Empleado(0, nombre, rol, telefono, fechaContratacion);
        controlador.agregarEmpleado(emp);
    }

    public static void mostrarEmpleados() throws ErrorEscrituraLog, ErrorConexionBD, ErrorListarEmp {
        List<Empleado> empleados = controlador.obtenerTodos();

        System.out.println("\n** Lista de empleados **");
        if (empleados.isEmpty()) {
            System.out.println("⚠️ No hay empleados registrados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }

    public static void eliminarEmpleado() throws ErrorEscrituraLog, ErrorConexionBD, ErrorBorrEmp {
        System.out.print("Introduce el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        boolean eliminado = controlador.eliminarEmpleado(id);
        if (eliminado) {
        } else {
            System.out.println("❌ No se pudo eliminar el empleado (puede que no exista).");
        }
    }
}