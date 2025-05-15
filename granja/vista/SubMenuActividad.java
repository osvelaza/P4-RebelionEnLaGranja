package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import exception.ErrorEscrituraLog;
import exception.ErrorSQL;
import modelo.Actividad;

public class SubMenuActividad {

    public static void iniciar(Scanner sc) throws ErrorEscrituraLog, ErrorSQL {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE ACTIVIDADES ---");
            System.out.println("1. Registrar actividad diaria");
            System.out.println("2. Mostrar actividades");
            System.out.println("3. Borrar actividad");
            System.out.println("4. Volver al menú principal");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("~Registro de una nueva actividad");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    System.out.print("Introduce la fecha en formato YYYY-MM-DD");
                    String fechaStr = sc.nextLine();
                    LocalDate date = LocalDate.parse(fechaStr, formatter);

                    System.out.print("Introduce la hora de la actividad: ");
                    //Proxmimamente

                    System.out.print("Actividad: ");
                    String tipoAct = sc.nextLine();

                    System.out.print("Id del empleado");
                    int idEmp = sc.nextInt();

                    System.out.print("Id del animal");
                    int idAnimal = sc.nextInt();

                    Actividad act = new Actividad(date, null, tipoAct, idEmp, idAnimal);
                    MenuActividad.registrarActividad(act);
                    break;
                case 2:
                    MenuActividad.mostrarActividades();
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
