package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Actividad;
import modelo.Animal;
import modelo.Empleado;

public class SubMenuActividad {

    public static void iniciar(Scanner sc){
        int opcion=0;
        boolean testint;

        do {
            System.out.println("\n--- GESTIÓN DE ACTIVIDADES ---");
            System.out.println("1. Registrar actividad diaria");
            System.out.println("2. Mostrar actividades");
            System.out.println("3. Borrar actividad");
            System.out.println("4. Volver al menú principal");

            System.out.print("Elige una opción: ");
            do{
                testint=(!sc.hasNextInt());
                if(testint){
                    System.out.println("Introduce un número entero");
                    sc.nextLine();
                }else{
                    opcion = sc.nextInt();
                }
            }while(testint);

            switch (opcion) {
                case 1:
                    sc.nextLine();
                    System.out.println("~Registro de una nueva actividad");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    System.out.print("Introduce la fecha en formato YYYY-MM-DD");
                    String fechaStr = sc.nextLine();
                    LocalDate date = LocalDate.parse(fechaStr, formatter);

                    System.out.print("Introduce la hora de la actividad: ");
                    //Proxmimamente

                    System.out.print("Actividad: ");
                    String tipoAct = sc.nextLine();

                    System.out.print("Introduce el id del empleado encargado de la actividad");
                    ArrayList<Empleado> empleados = new ArrayList<>();
                    empleados=controlador.EmpleadoControlador.obtenerTodos();
                    if(empleados.isEmpty()){
                        MenuEmpleado.registrarEmpleado();
                    }
                    
                    System.out.print("Introduce el id del empleado encargado de la actividad");
                    for(Empleado e:empleados){
                        System.out.println(e);
                    }
                    int idEmp = sc.nextInt();

                    System.out.print("Introduce el id del animal");
                    ArrayList<Animal> animales = new ArrayList<>();
                    animales=controlador.AnimalDAO.listarAnimales();
                    if(animales.isEmpty()){
                        MenuAnimal.registrarAnimal();
                    }
                    int idAnimal = sc.nextInt();

                    Actividad act = new Actividad(date, null, tipoAct, idEmp, idAnimal);
                    controlador.ActividadControlador.registrarActividad(act);
                    break;
                case 2:
                    LocalDate fech = null;
                    MenuActividad.mostrarActividades(fech);
                    break;
                case 3:
                    MenuActividad.borrarActividad();
                    break;
                case 4:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 4);
    }
}
