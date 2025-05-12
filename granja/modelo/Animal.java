package modelo;
import java.sql.Date;

public class Animal {
    private int id;
    private String especie;
    private String raza;
    private String arete;
    private String estadoSalud;
    private String ubicacion;
    private String estadoActual;
    private Date fechaNacimiento;

 // Constructor sin ID (para insertar)
    public Animal(String especie, String raza, Date fechaNacimiento, String arete, String estadoSalud, String ubicacion, String estadoActual) {
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.arete = arete;
        this.estadoSalud = estadoSalud;
        this.ubicacion = ubicacion;
        this.estadoActual = estadoActual;
    }

    // Getters (puedes generar los setters también si los necesitas)
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public String getArete() { return arete; }
    public String getEstadoSalud() { return estadoSalud; }
    public String getUbicacion() { return ubicacion; }
    public String getEstadoActual() { return estadoActual; }
    public int getId() {
    	return id;
    }

    public void setId(int id) {
    	this.id = id;
    }

@Override
public String toString() {
    return "Arete: " + arete + " | Especie: " + especie + " | Raza: " + raza + " | Fecha Nacimiento: " + fechaNacimiento +
           " | Estado Salud: " + estadoSalud + " | Ubicación: " + ubicacion + " | Estado: " + estadoActual;
}

}
