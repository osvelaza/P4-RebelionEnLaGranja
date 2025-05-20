package vista;

import java.util.Scanner;
import utilidades.LoggerSistema;

public class SubMenuAnimal {
    public static void iniciar(Scanner sc){
        int opcion=0;
        boolean testint;
        do {
            System.out.println("\n--- GESTIÓN DE ANIMALES ---");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Cambiar el estado de un animal");
            System.out.println("3. Consultar animales");
            System.out.println("4. Borrar animal");
            System.out.println("5. Volver al menú principal");

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
                    MenuAnimal.registrarAnimal();
                    break;
                case 2:
                    MenuAnimal.cambEstado();
                    break;
                case 3:
                    MenuAnimal.mostrarAnimales();
                    LoggerSistema.registrar("mostrar animales");
                    break;
                case 4:
                    MenuAnimal.borrarAnimal();
                    break;
                case 5:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 5);
    }
}
