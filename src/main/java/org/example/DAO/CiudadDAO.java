package org.example.DAO;

import org.example.Model.Ciudad;
import org.example.Model.Pais;
import org.example.DAO.ConnectionDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {
    private ConnectionDAO connectionDAO;

    public CiudadDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<Ciudad> obtenerCiudadesPorPais(int idPais) {
        List<Ciudad> ciudades = new ArrayList<>();
        String query = "SELECT idCiudad, nombre FROM Ciudad WHERE pais = ?";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idPais); // Usar el id del país para filtrar
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idCiudad = resultSet.getInt("idCiudad");
                String nombre = resultSet.getString("nombre");
                ciudades.add(new Ciudad(idCiudad, nombre, null)); // Puedes asignar el objeto Pais más tarde si es necesario
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return ciudades;
    }
}
