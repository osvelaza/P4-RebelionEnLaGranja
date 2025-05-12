package modelo;

import java.time.LocalDate;

public class Empleado {
    private int id;
    private String nombre;
    private String rol;
    private String telefono;
    private LocalDate fechaContratacion;

    public Empleado() {}

    public Empleado(int id, String nombre, String rol, String telefono, LocalDate fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters y Setters
    
    //id
    public int getId() { 
    	return id;
    }
    public void setId(int id) { 
    	this.id = id; 
    }

    //nombre
    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }

    //rol
    public String getRol() { 
    	return rol; 
    }
    public void setRol(String rol) { 
    	this.rol = rol; 
    }

    //teléfono
    public String getTelefono() { 
    	return telefono; 
    }
    public void setTelefono(String telefono) { 
    	this.telefono = telefono; 
    }

    //fecha-contratación
    public LocalDate getFechaContratacion() { 
    	return fechaContratacion; 
    }
    public void setFechaContratacion(LocalDate fechaContratacion) { 
    	this.fechaContratacion = fechaContratacion; 
    }

    
    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre='" + nombre + '\'' + ", rol='" + rol + '\'' + ", telefono='" + telefono + '\'' + ", fechaContratacion=" + fechaContratacion + '}';
    }
}
