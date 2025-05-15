package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Actividad {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private String tipoActividad;
    private int empleadoId;
    private Integer animalId; // Puede ser null

    public Actividad(LocalDate fecha, LocalTime hora, String tipoActividad, int empleadoId, int animalId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoActividad = tipoActividad;
        this.empleadoId = empleadoId;
        this.animalId = animalId;
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
    public Integer getAnimalId() { 
    	return animalId; 
    }
    public void setAnimalId(Integer animalId) { 
    	this.animalId = animalId; 
    }

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tipoActividad='" + tipoActividad + '\'' + ", empleadoId=" + empleadoId + ", animalId=" + animalId + '}';
    }
}