package vista;

import java.util.Scanner;

import utilidades.LoggerSistema;

public class SubMenuEmpleado {

    public static void iniciar(Scanner sc){
        int opcion=1;
        boolean testint;

        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar nuevo empleado");
            System.out.println("2. Editar rol empleado");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Eliminar empleado");
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
                    MenuEmpleado.registrarEmpleado();
                    break;
                case 2:
                    MenuEmpleado.editarRol();
                    break;
                case 3:
                    MenuEmpleado.mostrarEmpleados();
                    LoggerSistema.registrar("Mostrar empleados");
                    break;
                case 4:
                    MenuEmpleado.eliminarEmpleado();
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
