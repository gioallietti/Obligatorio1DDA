package org.example.Model;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Huesped huesped;
    private List<Reserva_Habitacion> habitacionesReservadas; // Asegúrate que esto sea de tipo Reserva_Habitacion
    private int cantidadPersonas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaReserva;

    public Reserva(int idReserva, Huesped huesped, List<Reserva_Habitacion> habitacionesReservadas,
                   int cantidadPersonas, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaReserva) {
        this.idReserva = idReserva;
        this.huesped = huesped;
        this.habitacionesReservadas = habitacionesReservadas;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaReserva = fechaReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public List<Reserva_Habitacion> getHabitacionesReservadas() {
        return habitacionesReservadas;
    }

    public void setHabitacionesReservadas(List<Reserva_Habitacion> habitacionesReservadas) {
        this.habitacionesReservadas = habitacionesReservadas;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
