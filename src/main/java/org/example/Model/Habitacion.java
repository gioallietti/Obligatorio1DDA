package org.example.Model;

public class Habitacion {
    private int idHabitacion;
    private Hotel hotel;
    private int cantidadCamas;
    private boolean camaDoble;
    private boolean aireAcondicionado;
    private boolean balcon;
    private boolean vista;
    private boolean ocupada;
    private TipoHabitacion tipoHabitacion;

    public Habitacion(int idHabitacion, Hotel hotel, int cantidadCamas, boolean camaDoble, boolean aireAcondicionado, boolean balcon, boolean vista, boolean ocupada, TipoHabitacion tipoHabitacion) {
        this.idHabitacion = idHabitacion;
        this.hotel = hotel;
        this.cantidadCamas = cantidadCamas;
        this.camaDoble = camaDoble;
        this.aireAcondicionado = aireAcondicionado;
        this.balcon = balcon;
        this.vista = vista;
        this.ocupada = ocupada;
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public boolean isCamaDoble() {
        return camaDoble;
    }

    public void setCamaDoble(boolean camaDoble) {
        this.camaDoble = camaDoble;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
}
