package main;

import java.util.Scanner;

import exception.ErrorAnadeEmp;
import exception.ErrorAnadirAnimal;
import exception.ErrorBorrAnimal;
import exception.ErrorBorrEmp;
import exception.ErrorConexionBD;
import exception.ErrorEscrituraLog;
import exception.ErrorListarAnimal;
import exception.ErrorListarEmp;
import exception.ErrorSQL;
import vista.*;

public class Main {
    public static void main(String[] args) throws ErrorEscrituraLog, ErrorBorrAnimal, ErrorSQL, ErrorConexionBD, ErrorAnadirAnimal, ErrorListarAnimal, ErrorAnadeEmp, ErrorListarEmp, ErrorBorrEmp {
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
                    utilidades.ExportadorCSV.exportarTabla("empleados", "csv/empleados");
                    utilidades.ExportadorCSV.exportarTabla("animales", "csv/animales");
                    utilidades.ExportadorCSV.exportarTabla("actividades", "csv/actividades");
                    utilidades.ExportadorCSV.exportarTabla("actividad-animal", "csv/act-anim");
                    break;
                case 5:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);
    }
}