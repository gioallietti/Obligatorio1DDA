package org.example.DAO;
import org.example.Model.Hotel;
import org.example.Model.Tarifa;
import org.example.Model.TipoHabitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {
    private ConnectionDAO connectionDAO;

    public TarifaDAO() {
        this.connectionDAO = new ConnectionDAO();
    }


    public List<Tarifa> obtenerTodasLasTarifas() {
        List<Tarifa> tarifas = new ArrayList<>();
        String query = "SELECT t.idTarifa, t.precio, t.fechaInicio, t.fechaFin, th.idTipoHabitacion, th.nombre AS nombreTipo, " +
                "h.idHotel, h.nombre AS nombreHotel " +
                "FROM tarifa t " +
                "JOIN tipo_habitacion th ON t.idTipoHabitacion = th.idTipoHabitacion " +
                "JOIN hotel h ON t.idHotel = h.idHotel";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idTarifa = resultSet.getInt("idTarifa");
                double precio = resultSet.getDouble("precio");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("FechaFin");

                // Obtener el tipo de habitación
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                String nombreTipoHabitacion = resultSet.getString("nombreTipo");
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, nombreTipoHabitacion);

                // Obtener el hotel
                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");
                Hotel hotel = new Hotel(idHotel, nombreHotel, null, null, 0);

                Tarifa tarifa = new Tarifa(idTarifa, precio, fechaInicio, fechaFin, tipoHabitacion, hotel);
                tarifas.add(tarifa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifas;
    }

    public boolean agregarTarifa(Tarifa tarifa) {
        String query = "INSERT INTO tarifa (precio, fechaInicio, fechaFin, tipoHabitacion, hotel) VALUES (?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query,
                tarifa.getPrecio(),
                tarifa.getFechaInicio(),
                tarifa.getFechaFin(),
                tarifa.getTipoHabitacion().getIdTipoHabitacion(),
                tarifa.getHotel().getIdHotel()
        );
    }

    public boolean eliminarTarifa(int idTarifa) {
        String query = "DELETE FROM tarifa WHERE idTarifa = ?";
        return connectionDAO.executeUpdate(query, idTarifa);
    }

    public boolean actualizarTarifa(Tarifa tarifa) {
        String query = "UPDATE tarifa SET precio = ?, fechaInicio = ?, fechaFin = ?,  tipoHabitacion = ?, hotel = ? WHERE idTarifa = ?";
        return connectionDAO.executeUpdate(query,
                tarifa.getPrecio(),
                tarifa.getFechaInicio(),
                tarifa.getFechaFin(),
                tarifa.getTipoHabitacion().getIdTipoHabitacion(),
                tarifa.getHotel().getIdHotel(),
                tarifa.getIdTarifa());
    }

    public Tarifa obtenerTarifaPorId(int idTarifa) {
        Tarifa tarifa = null;
        String query = "SELECT t.idTarifa, t.precio, t.fechaInicio, t.fechaFin, th.idTipoHabitacion, th.nombre AS nombreTipo, " +
                "h.idHotel, h.nombre AS nombreHotel " +
                "FROM tarifa t " +
                "JOIN tipohabitacion th ON t.tipoHabitacion = th.idTipoHabitacion " +
                "JOIN hotel h ON t.hotel = h.idHotel " +
                "WHERE t.idTarifa = " + idTarifa;

        try (Connection connection = connectionDAO.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int idTarifaResult = resultSet.getInt("idTarifa");
                double precio = resultSet.getDouble("precio");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");

                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                String nombreTipoHabitacion = resultSet.getString("nombreTipo");
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, nombreTipoHabitacion);

                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");
                Hotel hotel = new Hotel(idHotel, nombreHotel, null, null, 0);

                tarifa = new Tarifa(idTarifaResult, precio, fechaInicio, fechaFin, tipoHabitacion, hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifa;
    }
}
