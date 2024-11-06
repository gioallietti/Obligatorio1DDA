package org.example.DAO;

import org.example.Model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reserva_HabitacionDAO {
    private static ConnectionDAO connectionDAO;

    public Reserva_HabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean agregarReservaHabitacion(int idReserva, int idHabitacion) {
        String query = "INSERT INTO Reserva_Habitacion (reserva, habitacion) VALUES (" + idReserva + ", " + idHabitacion + ")";
        return connectionDAO.executeUpdate(query);
    }

    public List<Reserva_Habitacion> obtenerHabitacionesPorReserva(int idReserva) {
        List<Reserva_Habitacion> habitacionesReservadas = new ArrayList<>();
        String query = "SELECT h.idHabitacion, h.cantidadCamas, h.camaDoble, h.aireAcondicionado, h.balcon, h.vista, " +
                "h.ocupada, th.idTipoHabitacion, th.nombre AS nombreTipo, " +
                "ht.idHotel, ht.nombre AS nombreHotel, c.idCiudad, c.nombre AS nombreCiudad, " +
                "p.idPais, p.nombre AS nombrePais, ht.estrellas " +
                "FROM habitacion h " +
                "JOIN reserva_habitacion rh ON h.idHabitacion = rh.habitacion " +
                "JOIN tipoHabitacion th ON h.tipoHabitacion = th.idTipoHabitacion " +
                "JOIN hotel ht ON h.hotel = ht.idHotel " +
                "JOIN ciudad c ON ht.ciudad = c.idCiudad " +
                "JOIN pais p ON c.pais = p.idPais " +
                "WHERE rh.reserva = " + idReserva;

        try (Connection connection = connectionDAO.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int cantidadCamas = resultSet.getInt("cantidadCamas");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");
                boolean vista = resultSet.getBoolean("vista");
                boolean ocupada = resultSet.getBoolean("ocupada");

                // tipo habitacion
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                String nombreTipo = resultSet.getString("nombreTipo");
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, nombreTipo);

                // hotel
                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                int estrellas = resultSet.getInt("estrellas");

                Pais pais = new Pais(idPais, nombrePais);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                Hotel hotel = new Hotel(idHotel, nombreHotel, ciudad, pais, estrellas);

                //habitacion
                Habitacion habitacion = new Habitacion(idHabitacion, hotel, cantidadCamas, camaDoble,
                        aireAcondicionado,
                        balcon,
                        vista,
                        ocupada,
                        tipoHabitacion
                );


                Reserva_Habitacion reservaHabitacion = new Reserva_Habitacion(habitacionesReservadas.size() + 1, null, habitacion);
                habitacionesReservadas.add(reservaHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habitacionesReservadas;
    }
}