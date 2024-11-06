package org.example.Controller;

import org.example.DAO.CiudadDAO;
import org.example.DAO.HotelDAO;
import org.example.DAO.PaisDAO;
import org.example.Model.Ciudad;
import org.example.Model.Hotel;
import org.example.Model.Pais;

import java.util.List;

public class HotelController {
    private HotelDAO hotelDAO;
    private PaisDAO paisDAO;
    private CiudadDAO ciudadDAO;

    public HotelController() {
        this.hotelDAO = new HotelDAO();
        this.paisDAO = new PaisDAO();
        this.ciudadDAO = new CiudadDAO();
    }

    public List<Pais> obtenerTodosLosPaises() {
        return paisDAO.obtenerTodosLosPaises();
    }

    public List<Ciudad> obtenerCiudadesPorPais(int idPais) {
        return ciudadDAO.obtenerCiudadesPorPais(idPais);
    }

    public List<Hotel> obtenerHotelesPorNombre(String nombre) {
        return hotelDAO.obtenerHotelesPorNombre(nombre);
    }

    public List<Hotel> obtenerHotelesPorEstrellas(int estrellas) {
        return hotelDAO.obtenerHotelesPorEstrellas(estrellas);
    }

    public List<Hotel> obtenerHotelesPorPais(String nombrePais) {
        return hotelDAO.obtenerHotelesPorPais(nombrePais);
    }

    public boolean agregarHotel(Hotel hotel) {
        return hotelDAO.agregarHotel(hotel);
    }

    public boolean eliminarHotel(int idHotel) {
        return hotelDAO.eliminarHotel(idHotel);
    }

    public boolean actualizarHotel(Hotel hotel) {
        return hotelDAO.actualizarHotel(hotel);
    }

    public List<Hotel> obtenerTodosLosHoteles(){
        return hotelDAO.obtenerTodosLosHoteles();
    }
}
