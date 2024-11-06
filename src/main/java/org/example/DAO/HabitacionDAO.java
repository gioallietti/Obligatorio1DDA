package org.example.DAO;
import org.example.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HabitacionDAO {
    private ConnectionDAO connectionDAO;

    public HabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean agregarHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO habitacion (hotel, cantidadCamas, camaDoble, balcon, aireAcondicionado, vista, ocupada, tipoHabitacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query,
                habitacion.getHotel().getIdHotel(),
                habitacion.getCantidadCamas(),
                habitacion.isCamaDoble(),
                habitacion.isBalcon(),
                habitacion.isAireAcondicionado(),
                habitacion.isVista(),
                habitacion.isOcupada(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion());
    }

    public boolean eliminarHabitacion(int id) {
        String query = "DELETE FROM habitacion WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, id);
    }

    public boolean actualizarHabitacion(Habitacion habitacion) {
        String query = "UPDATE habitacion SET hotel = ?, cantidadCamas = ?, camaDoble = ?, balcon = ?, aireAcondicionado = ?, vista = ?, ocupada = ?, tipoHabitacion = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query,
                habitacion.getHotel().getIdHotel(),
                habitacion.getCantidadCamas(),
                habitacion.isCamaDoble(),
                habitacion.isAireAcondicionado(),
                habitacion.isBalcon(),
                habitacion.isVista(),
                habitacion.isOcupada(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion(),
                habitacion.getIdHabitacion());
    }

    public boolean ocuparHabitacion(int idHabitacion) {
        String query = "UPDATE habitacion SET ocupada = true WHERE idHabitacion = " + idHabitacion;
        return connectionDAO.executeUpdate(query);
    }

    public boolean desocuparHabitacion(int idHabitacion) {
        String query = "UPDATE habitacion SET ocupada = false WHERE idHabitacion = " + idHabitacion;
        return connectionDAO.executeUpdate(query);
    }

    public List<Habitacion> obtenerHabitacionesConReserva() {
        List<Habitacion> habitacionesReservadas = new ArrayList<>();
        String query = "SELECT DISTINCT h.* FROM habitacion h " +
                "JOIN reserva_habitacion rh ON h.idHabitacion = rh.habitacion";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion(
                        resultSet.getInt("idHabitacion"),
                        null,
                        resultSet.getInt("cantidadCamas"),
                        resultSet.getBoolean("camaDoble"),
                        resultSet.getBoolean("aireAcondicionado"),
                        resultSet.getBoolean("balcon"),
                        resultSet.getBoolean("vista"),
                        resultSet.getBoolean("ocupada"),
                        null
                );
                habitacionesReservadas.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacionesReservadas;
    }

    public List<Habitacion> obtenerHabitacionesSinReserva() {
        List<Habitacion> habitacionesNoReservadas = new ArrayList<>();
        String query = "SELECT * FROM habitacion h " +
                "WHERE h.idHabitacion NOT IN (SELECT habitacion FROM reserva_habitacion)";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion(
                        resultSet.getInt("idHabitacion"),
                        null,
                        resultSet.getInt("cantidadCamas"),
                        resultSet.getBoolean("camaDoble"),
                        resultSet.getBoolean("aireAcondicionado"),
                        resultSet.getBoolean("balcon"),
                        resultSet.getBoolean("vista"),
                        resultSet.getBoolean("ocupada"),
                        null
                );
                habitacionesNoReservadas.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacionesNoReservadas;
    }

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String query = "SELECT DISTINCT h.idHabitacion, h.cantidadCamas, h.camaDoble, h.aireAcondicionado, " +
                "h.balcon, h.vista, h.ocupada, h.tipoHabitacion, " +
                "ht.idHotel, ht.nombre AS nombreHotel, " +
                "c.idCiudad, c.nombre AS nombreCiudad, " +
                "p.idPais, p.nombre AS nombrePais, ht.estrellas, " +
                "th.idTipoHabitacion, th.nombre AS nombreTipoHabitacion " +
                "FROM habitacion h " +
                "JOIN TipoHabitacion th ON h.tipoHabitacion = th.idTipoHabitacion " +
                "JOIN hotel ht ON h.hotel = ht.idHotel " +
                "JOIN ciudad c ON ht.ciudad = c.idCiudad " +
                "JOIN pais p ON c.pais = p.idPais";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // habitacion
                int idHabitacion = resultSet.getInt("idHabitacion");
                int cantidadCamas = resultSet.getInt("cantidadCamas");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");
                boolean vista = resultSet.getBoolean("vista");
                boolean ocupada = resultSet.getBoolean("ocupada");

                // hotel
                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                int estrellas = resultSet.getInt("estrellas");

                // tipo de habitacion
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                String nombreTipoHabitacion = resultSet.getString("nombreTipoHabitacion");

                // creo objetos de modelo
                Pais pais = new Pais(idPais, nombrePais);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                Hotel hotel = new Hotel(idHotel, nombreHotel, ciudad, pais, estrellas);
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, nombreTipoHabitacion);

                // creo la habitacion
                Habitacion habitacion = new Habitacion(idHabitacion, hotel, cantidadCamas, camaDoble,
                        aireAcondicionado, balcon, vista, ocupada, tipoHabitacion);

                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }
}