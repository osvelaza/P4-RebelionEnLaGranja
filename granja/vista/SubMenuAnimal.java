package vista;

import java.util.Scanner;

public class SubMenuAnimal {

    public static void iniciar(Scanner sc
    
    ) {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE ANIMALES ---");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Consultar animales");
            System.out.println("3. Borrar animal");
            System.out.println("4. Volver al menú principal");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    MenuAnimal.registrarAnimal();
                    break;
                case 2:
                    MenuAnimal.mostrarAnimales();
                    break;
                case 3:
                    MenuAnimal.borrarAnimal();
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
