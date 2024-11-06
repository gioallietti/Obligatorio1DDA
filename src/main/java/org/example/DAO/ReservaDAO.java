package org.example.DAO;

import org.example.Model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private ConnectionDAO connectionDAO;

    public ReservaDAO() {
        this.connectionDAO = new ConnectionDAO();
    }


    public boolean agregarReserva(Reserva reserva) {
        String query = "INSERT INTO reserva (huesped, cantidadPersonas, fechaInicio, fechaFin, fechaReserva) VALUES (" +
                reserva.getHuesped().getIdHuesped() + ", " +
                reserva.getCantidadPersonas() + ", '" +
                reserva.getFechaInicio() + "', '" +
                reserva.getFechaFin() + "', '" +
                reserva.getFechaReserva() + "')";
        return connectionDAO.executeUpdate(query);
    }

    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM reserva WHERE idReserva = " + idReserva;
        return connectionDAO.executeUpdate(query);
    }

    public boolean actualizarReserva(Reserva reserva) {
        String query = "UPDATE reserva SET huesped = " + reserva.getHuesped().getIdHuesped() +
                ", cantidadPersonas = " + reserva.getCantidadPersonas() +
                ", fechaInicio = '" + reserva.getFechaInicio() + "'" +
                ", fechaFin = '" + reserva.getFechaFin() + "'" +
                ", fechaReserva = '" + reserva.getFechaReserva() + "'" +
                " WHERE idReserva = " + reserva.getIdReserva();
        return connectionDAO.executeUpdate(query);
    }

    public int obtenerUltimoIdReserva() {
        String query = "SELECT idReserva FROM reserva ORDER BY idReserva DESC LIMIT 1";
        int idReserva = -1;
        try (Connection connection = connectionDAO.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            if (resultSet.next()) {
                idReserva = resultSet.getInt("idReserva");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idReserva;
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        Reserva reserva = null;
        String query = "SELECT r.idReserva, r.cantidadPersonas, r.fechaInicio, r.fechaFin, r.fechaReserva, h.idHuesped, h.nombre AS nombreHuesped, " +
                "h.apellidoPaterno, h.apellidoMaterno, tp.idTipoDocumento, tp.descripcion as descripcionDocumento, " +
                "h.numeroDocumento, h.telefono, p.idPais, p.nombre as nombrePais, h.fechaNacimiento " +
                "FROM reserva r " +
                "JOIN huesped h ON r.huesped = h.idHuesped " +
                "JOIN pais p ON h.pais = p.idPais " +
                "JOIN tipoDocumento tp ON h.tipoDocumento = tp.idTipoDocumento " +
                "WHERE r.idReserva = " + idReserva;

        try (Connection connection = connectionDAO.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int idReservaResult = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                LocalDate fechaInicio = resultSet.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = resultSet.getDate("fechaFin").toLocalDate();
                LocalDate fechaReserva = resultSet.getDate("fechaReserva").toLocalDate();

                int idHuesped = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombreHuesped");
                String apellidoPaterno = resultSet.getString("apellidoPaterno");
                String apellidoMaterno = resultSet.getString("apellidoMaterno");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento");
                String descripcionDocumento = resultSet.getString("descripcionDocumento");
                String numeroDocumento = resultSet.getString("numeroDocumento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");

                Pais pais = new Pais(idPais, nombrePais);
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, descripcionDocumento);
                Huesped huesped = new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento);

                reserva = new Reserva(idReservaResult, huesped, new ArrayList<>(), cantidadPersonas, fechaInicio, fechaFin, fechaReserva);
                Reserva_HabitacionDAO reservaHabitacionDAO = new Reserva_HabitacionDAO();
                List<Reserva_Habitacion> habitacionesReservadas = reservaHabitacionDAO.obtenerHabitacionesPorReserva(idReservaResult);
                reserva.setHabitacionesReservadas(habitacionesReservadas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    public List<Reserva> obtenerReservaPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT r.idReserva, r.cantidadPersonas, r.fechaInicio, r.fechaFin, r.fechaReserva, " +
                "h.idHuesped, h.nombre AS nombreHuesped, h.apellidoPaterno, h.apellidoMaterno, " +
                "tp.idTipoDocumento, tp.descripcion AS descripcionDocumento, h.numeroDocumento, h.telefono, " +
                "p.idPais, p.nombre AS nombrePais, h.fechaNacimiento " +
                "FROM reserva r " +
                "JOIN huesped h ON r.huesped = h.idHuesped " +
                "JOIN pais p ON h.pais = p.idPais " +
                "JOIN tipoDocumento tp ON h.tipoDocumento = tp.idTipoDocumento " +
                "WHERE r.fechaInicio >= '" + fechaInicio + "' AND r.fechaFin <= '" + fechaFin + "'";

        try (Connection connection = connectionDAO.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idReservaResult = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");

                // aca es donde se convierte la fecha
                LocalDate fechaReserva = resultSet.getDate("fechaReserva").toLocalDate();
                LocalDate fechaInicioReserva = resultSet.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFinReserva = resultSet.getDate("fechaFin").toLocalDate();

                int idHuesped = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombreHuesped");
                String apellidoPaterno = resultSet.getString("apellidoPaterno");
                String apellidoMaterno = resultSet.getString("apellidoMaterno");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento");
                String descripcionDocumento = resultSet.getString("descripcionDocumento");
                String numeroDocumento = resultSet.getString("numeroDocumento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");

                Pais pais = new Pais(idPais, nombrePais);
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, descripcionDocumento);
                Huesped huesped = new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento);

                Reserva reserva = new Reserva(idReservaResult, huesped, new ArrayList<>(), cantidadPersonas, fechaInicioReserva, fechaFinReserva, fechaReserva);

                Reserva_HabitacionDAO reservaHabitacionDAO = new Reserva_HabitacionDAO();
                List<Reserva_Habitacion> habitacionesReservadas = reservaHabitacionDAO.obtenerHabitacionesPorReserva(idReservaResult);
                reserva.setHabitacionesReservadas(habitacionesReservadas);

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }
}
