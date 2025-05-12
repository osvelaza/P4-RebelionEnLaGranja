package vista;

import java.util.Scanner;

public class SubMenuActividad {

    public static void iniciar(Scanner sc) {
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
                    MenuActividad.registrarActividad();
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
