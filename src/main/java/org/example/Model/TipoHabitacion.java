package org.example.Model;

public class TipoHabitacion {
    private int idTipoHabitacion;
    private String nombre;

    public TipoHabitacion(int idTipoHabitacion, String nombre) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
