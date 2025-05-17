package enumJ;

public class general {
    public static boolean checkEnumAnimal(String input) {
    for (ubicacionAnimal value : ubicacionAnimal.values()) {
        if (value.name().equalsIgnoreCase(input.trim())) {
            return true; 
        }
    }
    return false;
    }

    public static boolean checkEnumSaludAnimal(String input) {
    for (saludAnimal value : saludAnimal.values()) {
        if (value.name().equalsIgnoreCase(input.trim())) {
            return true; 
        }
    }
    return false;
    }

    public static boolean checkEnumRolEmpleado(String input) {
    for (rolEmpleado value : rolEmpleado.values()) {
        if (value.name().equalsIgnoreCase(input.trim())) {
            return true; 
        }
    }
    return false;
    }
}
