package org.example.DAO;
import org.example.Model.Huesped;
import org.example.Model.Pais;
import org.example.Model.TipoDocumento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    private ConnectionDAO connectionDAO;

    public HuespedDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<Huesped> obtenerTodosLosHuespedes() {
        List<Huesped> huespedes = new ArrayList<>();
        String query = "SELECT h.*, td.idTipoDocumento, td.descripcion AS descripcionDocumento, p.idPais, p.nombre AS nombrePais " +
                "FROM Huesped h " +
                "JOIN TipoDocumento td ON h.tipoDocumento = td.idTipoDocumento " +
                "JOIN Pais p ON h.pais = p.idPais";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idHuesped = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String apellidoPaterno = resultSet.getString("apellidoPaterno");
                String apellidoMaterno = resultSet.getString("apellidoMaterno");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento"); // Ahora existe en el SELECT
                String descripcionDocumento = resultSet.getString("descripcionDocumento");
                String numeroDocumento = resultSet.getString("numeroDocumento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais"); // Ahora existe en el SELECT
                String nombrePais = resultSet.getString("nombrePais"); // Ahora existe en el SELECT
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");

                Pais pais = new Pais(idPais, nombrePais);
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, descripcionDocumento);
                Huesped huesped = new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento);
                huespedes.add(huesped);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return huespedes;
    }


    public boolean insertHuesped(Huesped huesped) {
        String query = "INSERT INTO huesped (nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query,
                huesped.getNombre(),
                huesped.getApellidoPaterno(),
                huesped.getApellidoMaterno(),
                huesped.getTipoDocumento().getIdTipoDocumento(),
                huesped.getNumDocumento(),
                huesped.getTelefono(),
                huesped.getPais().getIdPais(),
                huesped.getFechaNacimiento()
        );
    }

    public boolean eliminarHuesped(int idHuesped) {
        String query = "DELETE FROM huesped WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, idHuesped);
    }



    public boolean actualizarHuesped(Huesped huesped) {
        String query = "UPDATE huesped SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, tipoDocumento = ?, numeroDocumento = ?, telefono = ?, pais = ?, fechaNacimiento = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query,
                huesped.getNombre(),
                huesped.getApellidoPaterno(),
                huesped.getApellidoMaterno(),
                huesped.getTipoDocumento().getIdTipoDocumento(),
                huesped.getNumDocumento(),
                huesped.getTelefono(),
                huesped.getPais().getIdPais(),
                huesped.getFechaNacimiento(),
                huesped.getIdHuesped()
        );
    }
}
