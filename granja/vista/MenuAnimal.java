package vista;

import controlador.AnimalDAO;
import exception.ErrorBorrAnimal;
import exception.ErrorEscrituraLog;
import modelo.Animal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAnimal {

    public static void registrarAnimal() throws ErrorEscrituraLog {
        Scanner sc = new Scanner(System.in);
        AnimalDAO dao = new AnimalDAO();

        System.out.println("~Registro de nuevo animal~");
        
        System.out.print("Especie: ");
        String especie = sc.nextLine();

        System.out.print("Raza: ");
        String raza = sc.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        Date fechaNacimiento = Date.valueOf(fechaStr);

        System.out.print("Arete: ");
        String arete = sc.nextLine();
        System.out.print("Estado de salud: ");
        String salud = sc.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        String estadoActual = "activo"; // por defecto

        Animal nuevoAnimal = new Animal(especie, raza, fechaNacimiento, arete, salud, ubicacion, estadoActual);
        dao.insertarAnimal(nuevoAnimal);
    }

    // Método para borrar un animal
    public static void borrarAnimal() throws ErrorBorrAnimal {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el ID del animal a borrar: ");
        int id = scanner.nextInt();  // Leemos el ID del animal a borrar
        scanner.close();
        // Llamamos al método borrarAnimal de AnimalDAO
        boolean eliminado = false;
		try {
			eliminado = AnimalDAO.borrarAnimal(id);
		} catch (Exception e) {
			throw new ErrorBorrAnimal("Error inesperado al eliminar el animal");
		}
        if (eliminado) {
            System.out.println("Animal borrado correctamente.");
        } else {
            System.out.println("No se encontró el animal o hubo un error al borrarlo.");
        }
        scanner.close();
    }
    
    public static void mostrarAnimales() {
        AnimalDAO dao = new AnimalDAO();
        ArrayList<Animal> lista = dao.listarAnimales();

        System.out.println("\n** Lista de Animales **");
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay animales registrados.");
        } else {
            for (Animal animal : lista) {
                System.out.println(animal); // Asegúrate de que `Animal` tenga un método `toString()` adecuado
            }
        }
    }
}
