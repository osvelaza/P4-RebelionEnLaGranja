package vista;

import controlador.EmpleadoControlador;
import enumJ.rolEmpleado;
import enumJ.ubicacionAnimal;
import modelo.Empleado;
import utilidades.LoggerSistema;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuEmpleado {

    private static Scanner scanner = new Scanner(System.in);
    private static EmpleadoControlador controlador = new EmpleadoControlador();

    public static void mostrarMenu(){
        int opcion=1;
        boolean testint;
        do {
            System.out.println("\n--- Menú de Empleados ---");
            System.out.println("1. Registrar nuevo empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Volver al menú principal");
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
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    public static void registrarEmpleado(){
        try {
            System.out.println("\n~ Registro de nuevo empleado ~");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Rol: ");
            String rol;
            do {
                System.out.print("Introduce el rol del empleado: ");
                for (rolEmpleado value : rolEmpleado.values()){
                    System.out.print(value.toString());
                }
                System.out.println();
                rol = scanner.nextLine();
            } while (!enumJ.general.checkEnumAnimal(rol));

            String telefono;
            do{
                System.out.print("Introduce el número de teléfono: ");
                telefono=scanner.nextLine();
            }while(telefono.length()!=9);

            System.out.print("Fecha de contratación (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            LocalDate fechaContratacion = LocalDate.parse(fechaStr);
            Empleado emp = new Empleado(nombre, rol, telefono, fechaContratacion);
            controlador.agregarEmpleado(emp);
            LoggerSistema.registrar("Agregar empleado"+System.lineSeparator()+emp);

        } catch (DateTimeException e) {
            System.out.println("❌ Asegúrate de introducir la fecha en formato YYYY-MM-DD "+e.getMessage());
        }
    }

    public static void mostrarEmpleados(){
        ArrayList<Empleado> empleados = controlador.obtenerTodos();

        System.out.println("\n** Lista de empleados **");
        if (empleados.isEmpty()) {
            System.out.println("⚠️ No hay empleados registrados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }

    public static void eliminarEmpleado(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados=EmpleadoControlador.obtenerTodos();
        if(empleados.isEmpty()){
            System.out.println("⚠️ No hay empleados registrados.");
        }else{
            for (Empleado e : empleados) {
                System.out.println(e);
            }
            System.out.print("Introduce el ID del empleado a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

        boolean eliminado = controlador.eliminarEmpleado(id);
        if (eliminado) {
            LoggerSistema.registrar("Eliminado empleado con id "+id);
        } else {
            System.out.println("❌ No se pudo eliminar el empleado (puede que no exista).");
        }
        }
    }
}