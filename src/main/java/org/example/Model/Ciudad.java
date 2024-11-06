package org.example.Model;

public class Ciudad {
    private int idCiudad;
    private String nombre;
    private Pais pais;

    public Ciudad(int idCiudad, String nombre, Pais pais) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
