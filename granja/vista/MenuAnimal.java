package vista;

import controlador.AnimalDAO;
import modelo.Animal;
import utilidades.LoggerSistema;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAnimal {

    public static void registrarAnimal(){
        Scanner sc = new Scanner(System.in);
        AnimalDAO dao = new AnimalDAO();

        System.out.println("~Registro de nuevo animal~");
        
        System.out.print("Especie: ");
        String especie = sc.nextLine();

        System.out.print("Raza: ");
        String raza = sc.nextLine();

        Date fechaNacimiento = null;
        try {
            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fechaStr = sc.nextLine();
            fechaNacimiento = Date.valueOf(fechaStr);

            System.out.print("Arete: ");
            String arete = sc.nextLine();
            
            System.out.print("Estado de salud: ");
            String salud = sc.nextLine();

            System.out.print("Ubicación: ");
            String ubicacion = sc.nextLine();

            String estadoActual = "activo"; // por defecto

            Animal nuevoAnimal = new Animal(especie, raza, fechaNacimiento, arete, salud, ubicacion, estadoActual);
            dao.insertarAnimal(nuevoAnimal);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la fecha. Asegúrate de tener el formato YYYY-MM-DD "+e.getMessage());
        }
    }

    // Método para borrar un animal
    public static void borrarAnimal(){
        Scanner scanner = new Scanner(System.in);
        mostrarAnimales();
        System.out.print("Introduce el ID del animal a borrar: ");
        int id = scanner.nextInt();  // Leemos el ID del animal a borrar
        // Llamamos al método borrarAnimal de AnimalDAO
        boolean eliminado = false;
		try {
			eliminado = AnimalDAO.borrarAnimal(id);
		} catch (Exception e) {
            System.out.println("Error inesperado al eliminar el animal"+e.getMessage());
		}
        if (eliminado) {
            System.out.println("Animal borrado correctamente.");
            LoggerSistema.registrar("Eliminado animal con id "+id);
        } else {
            System.out.println("No se encontró el animal o hubo un error al borrarlo.");
        }
    }

    public static void mostrarAnimales(){
        AnimalDAO dao = new AnimalDAO();
        ArrayList<Animal> lista = dao.listarAnimales();
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay animales registrados.");
        }
    }
}
