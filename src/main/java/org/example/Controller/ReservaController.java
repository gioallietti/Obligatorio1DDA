package org.example.Controller;

import org.example.DAO.*;
import org.example.Model.*;

import java.time.LocalDate;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;
    private Reserva_HabitacionDAO reservaHabitacionDAO;
    private HuespedDAO huespedDAO;
    private HabitacionDAO habitacionDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.reservaHabitacionDAO = new Reserva_HabitacionDAO();
        this.huespedDAO = new HuespedDAO();
        this.habitacionDAO = new HabitacionDAO();
    }

    public boolean agregarReserva(Reserva reserva) {
        return reservaDAO.agregarReserva(reserva);
    }

    public boolean eliminarReserva(int idReserva) {
        return reservaDAO.eliminarReserva(idReserva);
    }

    public boolean actualizarReserva(Reserva reserva) {
        return reservaDAO.actualizarReserva(reserva);
    }

    public boolean agregarReservaHabitacion(int idReserva, Habitacion habitacion) {
        return reservaHabitacionDAO.agregarReservaHabitacion(idReserva, habitacion.getIdHabitacion());
    }

    public List<Huesped> obtenerTodosLosHuespedes(){
        return huespedDAO.obtenerTodosLosHuespedes();
    }

    public List<Habitacion> obtenerTodasLasHabitaciones(){
        return habitacionDAO.obtenerTodasLasHabitaciones();
    }

    public int obtenerUltimoIdReserva(){
        return reservaDAO.obtenerUltimoIdReserva();
    }

    public List<Habitacion> obtenerHabitacionesConReserva(){
        return habitacionDAO.obtenerHabitacionesConReserva();
    }

    public List<Habitacion> obtenerHabitacionesSinReserva(){
        return habitacionDAO.obtenerHabitacionesSinReserva();
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        return reservaDAO.obtenerReservaPorId(idReserva);
    }

    public List<Reserva> obtenerReservaPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return reservaDAO.obtenerReservaPorFecha(fechaInicio, fechaFin);
    }
}
