package main;

import java.util.Scanner;

import vista.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion=1;
        boolean testint;
        do {
            System.out.println("\n🐷 MENÚ PRINCIPAL - GRANJA DIGITAL 🐮");
            System.out.println("1. Gestión de animales");
            System.out.println("2. Gestión de empleados");
            System.out.println("3. Registrar actividad diaria");
            System.out.println("4. Exportar base de datos a csv");
            System.out.println("5. Salir");

            System.out.print("Elige una opción entre 1 y 4:");
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
                    SubMenuAnimal.iniciar(sc);
                    break;
                case 2:
                    SubMenuEmpleado.iniciar(sc);
                    break;
                case 3:
                	SubMenuActividad.iniciar(sc);
                    break;
                case 4:
                    utilidades.ExportadorCSV.exportarTabla("empleados", "empleados.csv");
                    utilidades.ExportadorCSV.exportarTabla("animales", "animales.csv");
                    utilidades.ExportadorCSV.exportarTabla("actividades", "actividades.csv");
                    break;
                case 5:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    }
}