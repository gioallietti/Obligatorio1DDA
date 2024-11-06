package org.example.Controller;
import org.example.DAO.HabitacionDAO;
import org.example.DAO.HotelDAO;
import org.example.DAO.TipoHabitacionDAO;
import org.example.Model.Habitacion;
import org.example.Model.Hotel;
import org.example.Model.TipoHabitacion;

import java.util.List;


public class HabitacionController {
    private static HabitacionDAO habitacionDAO;
    private HotelDAO hotelDAO;
    private TipoHabitacionDAO tipoHabitacionDAO;


    public HabitacionController() {
        this.habitacionDAO = new HabitacionDAO();
        this.hotelDAO = new HotelDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO();
    }

    public static boolean agregarHabitacion(Habitacion habitacion) {
        return habitacionDAO.agregarHabitacion(habitacion);
    }

    public boolean eliminarHabitacion(int idHabitacion) {
        return habitacionDAO.eliminarHabitacion(idHabitacion);
    }

    public boolean actualizarHabitacion(Habitacion habitacion) {
        return habitacionDAO.actualizarHabitacion(habitacion);
    }

    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelDAO.obtenerTodosLosHoteles();
    }

    public List<TipoHabitacion> obtenerTodosLosTiposHabitacion() {
        return tipoHabitacionDAO.obtenerTodosLosTiposHabitacion();
    }

    public boolean ocuparHabitacion(int idHabitacion) {
        return habitacionDAO.ocuparHabitacion(idHabitacion);
    }

    public boolean desocuparHabitacion(int idHabitacion) {
        return habitacionDAO.desocuparHabitacion(idHabitacion);
    }

    public List<Habitacion> obtenerTodasLasHabitaciones(){
        return habitacionDAO.obtenerTodasLasHabitaciones();
    }
}
