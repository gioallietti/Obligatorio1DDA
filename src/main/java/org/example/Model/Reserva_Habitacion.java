package org.example.Model;

public class Reserva_Habitacion {
    private int idReservaHabitacion;
    private Reserva reserva;
    private Habitacion habitacion;

    public Reserva_Habitacion(int idReservaHabitacion, Reserva reserva, Habitacion habitacion) {
        this.idReservaHabitacion = idReservaHabitacion;
        this.reserva = reserva;
        this.habitacion = habitacion;
    }

    public int getIdReservaHabitacion() {
        return idReservaHabitacion;
    }

    public void setIdReservaHabitacion(int idReservaHabitacion) {
        this.idReservaHabitacion = idReservaHabitacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
}
