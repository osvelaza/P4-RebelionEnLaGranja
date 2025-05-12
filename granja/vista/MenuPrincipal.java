package vista;

import java.util.Scanner;

public class MenuPrincipal {

    public static void iniciarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n🐷 MENÚ PRINCIPAL - GRANJA DIGITAL 🐮");
            System.out.println("1. Gestión de animales");
            System.out.println("2. Gestión de empleados");
            System.out.println("3. Registrar actividad diaria");
            System.out.println("4. Salir");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    SubMenuAnimal.iniciar(sc);
                    break;
                case 2:
                    SubMenuEmpleado.iniciar(sc);
                    break;
                case 3:
                	 SubMenuActividad.iniciar(sc);
                    break;
                case 4:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 4);

        sc.close();
    }
}