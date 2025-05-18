package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Actividad {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private String tipoActividad;
    private int empleadoId;
    private ArrayList<Integer>animalesID; // Puede ser null

    public Actividad() {}

    public Actividad(int id, LocalDate fecha, LocalTime hora, String tipoActividad, int empleadoId, Integer animalId, ArrayList<Integer> animalesID) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoActividad = tipoActividad;
        this.empleadoId = empleadoId;
        this.animalesID = animalesID;
    }

    // Getters y Setters
    //id
    public int getId() {
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }

    //fecha
    public LocalDate getFecha() { 
    	return fecha; 
    }
    public void setFecha(LocalDate fecha) { 
    	this.fecha = fecha; 
    }

    //hora
    public LocalTime getHora() { 
    	return hora; 
    }
    public void setHora(LocalTime hora) { 
    	this.hora = hora; 
    }

    //actividad
    public String getTipoActividad() { 
    	return tipoActividad; 
    }
    public void setTipoActividad(String tipoActividad) { 
    	this.tipoActividad = tipoActividad; 
    }

    //empleado-id
    public int getEmpleadoId() { 
    	return empleadoId; 
    }
    public void setEmpleadoId(int empleadoId) { 
    	this.empleadoId = empleadoId; 
    }

    //animal-id
    public ArrayList<Integer> getAnimalesID() {
        return animalesID;
    }
    public void setAnimalesID(ArrayList<Integer> animalesID) {
        this.animalesID = animalesID;
    }

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tipoActividad='" + tipoActividad + '\'' + ", empleadoId=" + empleadoId + ", animalId=" + animalesID + '}';
    }
}