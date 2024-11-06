package org.example.Controller;

import org.example.DAO.HotelDAO;
import org.example.DAO.TarifaDAO;
import org.example.DAO.TipoHabitacionDAO;
import org.example.Model.Hotel;
import org.example.Model.Tarifa;
import org.example.Model.TipoHabitacion;

import java.util.List;

public class TarifaController {
    private static TarifaDAO tarifaDAO;
    private HotelDAO hotelDAO;
    private TipoHabitacionDAO tipoHabitacionDAO;


    public TarifaController() {
        this.tarifaDAO = new TarifaDAO();
        this.hotelDAO = new HotelDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO();
    }

    public static boolean agregarTarifa(Tarifa tarifa) {
        return tarifaDAO.agregarTarifa(tarifa);
    }

    public boolean eliminarTarifa(int idTarifa) {
        return tarifaDAO.eliminarTarifa(idTarifa);
    }

    public boolean actualizarTarifa(Tarifa tarifa) {
        return tarifaDAO.actualizarTarifa(tarifa);
    }

    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelDAO.obtenerTodosLosHoteles();
    }

    public List<TipoHabitacion> obtenerTodosLosTiposHabitacion() {
        return tipoHabitacionDAO.obtenerTodosLosTiposHabitacion();
    }

    public Tarifa obtenerTarifaPorId(int idTarifa) {
        return tarifaDAO.obtenerTarifaPorId(idTarifa);
    }
}
