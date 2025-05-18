package vista;

import controlador.EmpleadoControlador;
import enumJ.rolEmpleado;
import enumJ.saludAnimal;
import modelo.Empleado;
import utilidades.LoggerSistema;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuEmpleado {

    private static Scanner scanner = new Scanner(System.in);
    private static EmpleadoControlador controlador = new EmpleadoControlador();

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
            rol=rol.toLowerCase();

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

    public static void editarRol(){
        Scanner scanner = new Scanner(System.in);
        int id;
        String rol;
        mostrarEmpleados();
        System.out.println("Introduce el id del empleado:");
        id=scanner.nextInt();
        do {
            System.out.println("Introduce el nuevo rol del empleado");
            for (rolEmpleado value : rolEmpleado.values()){
                System.out.print(value.toString());
                System.out.println();
            }
            System.out.println();
            scanner.nextLine();
            rol = scanner.nextLine();
        } while (!enumJ.general.checkEnumRolEmpleado(rol));
        rol=rol.toLowerCase();
        controlador.camEstadoBD(id,rol);
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