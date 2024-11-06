package org.example.DAO;
import org.example.Model.Ciudad;
import org.example.Model.Hotel;
import org.example.Model.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private ConnectionDAO connectionDAO = new ConnectionDAO();

    public HotelDAO() {
        this.connectionDAO = new ConnectionDAO();
    }


        public List<Hotel> obtenerTodosLosHoteles() {
            List<Hotel> hoteles = new ArrayList<>();
            String query = "SELECT DISTINCT h.idHotel, h.nombre, c.idCiudad, c.nombre AS nombreCiudad, p.idPais, p.nombre AS nombrePais, h.estrellas " +
                    "FROM hotel h " +
                    "JOIN Pais p ON h.pais = p.idPais " +
                    "JOIN Ciudad c ON c.pais = p.idPais";

            try (Connection connection = connectionDAO.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int idHotel = resultSet.getInt("idHotel");
                    String nombreHotel = resultSet.getString("nombre");
                    int idCiudad = resultSet.getInt("idCiudad");
                    String nombreCiudad = resultSet.getString("nombreCiudad");
                    int idPais = resultSet.getInt("idPais");
                    String nombrePais = resultSet.getString("nombrePais");
                    int estrellas = resultSet.getInt("estrellas");

                    Pais pais = new Pais(idPais, nombrePais);
                    Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                    Hotel hotel = new Hotel(idHotel, nombreHotel, ciudad, pais, estrellas);

                    hoteles.add(hotel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hoteles;
        }

    public List<Hotel> obtenerHotelesPorPais(String nombrePais) {
        List<Hotel> hoteles = new ArrayList<>();
        String query = "SELECT h.idHotel, h.nombre, c.idCiudad, c.nombre AS nombreCiudad, p.idPais, p.nombre AS nombrePais, h.estrellas " +
                "FROM hotel h " +
                "JOIN Ciudad c ON h.ciudad = c.idCiudad " +
                "JOIN Pais p ON c.pais = p.idPais " +
                "WHERE LOWER(p.nombre) = LOWER(?)"; // esto es para que sea min

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombrePais);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                int idPais = resultSet.getInt("idPais");
                String nombrePaisDB = resultSet.getString("nombrePais");
                int estrellas = resultSet.getInt("estrellas");

                Pais pais = new Pais(idPais, nombrePaisDB);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                Hotel hotel = new Hotel(idHotel, nombreHotel, ciudad, pais, estrellas);

                hoteles.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteles;
    }

    public List<Hotel> obtenerHotelesPorNombre(String nombre) {
        List<Hotel> hoteles = new ArrayList<>();
        String query = "SELECT h.idHotel, h.nombre, c.idCiudad, c.nombre AS nombreCiudad, p.idPais, p.nombre AS nombrePais, h.estrellas " +
                "FROM hotel h " +
                "JOIN Ciudad c ON h.ciudad = c.idCiudad " +
                "JOIN Pais p ON c.pais = p.idPais " +
                "WHERE LOWER(h.nombre) = LOWER(?)";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idHotel = resultSet.getInt("idHotel");
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                int estrellas = resultSet.getInt("estrellas");

                Pais pais = new Pais(idPais, nombrePais);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                Hotel hotel = new Hotel(idHotel, resultSet.getString("nombre"), ciudad, pais, estrellas);

                hoteles.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteles;
    }

    public List<Hotel> obtenerHotelesPorEstrellas(int estrellas) {
        List<Hotel> hoteles = new ArrayList<>();
        String query = "SELECT h.idHotel, h.nombre, c.idCiudad, c.nombre AS nombreCiudad, p.idPais, p.nombre AS nombrePais, h.estrellas " +
                "FROM hotel h " +
                "JOIN Ciudad c ON h.ciudad = c.idCiudad " +
                "JOIN Pais p ON c.pais = p.idPais " +
                "WHERE h.estrellas = ?";

        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, estrellas);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idHotel = resultSet.getInt("idHotel");
                    String nombreHotel = resultSet.getString("nombre");
                    int idCiudad = resultSet.getInt("idCiudad");
                    String nombreCiudad = resultSet.getString("nombreCiudad");
                    int idPais = resultSet.getInt("idPais");
                    String nombrePais = resultSet.getString("nombrePais");

                    Pais pais = new Pais(idPais, nombrePais);
                    Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                    Hotel hotel = new Hotel(idHotel, nombreHotel, ciudad, pais, estrellas);

                    hoteles.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteles;
    }


    public boolean agregarHotel(Hotel hotel) {
        String query = "INSERT INTO hotel (nombre, ciudad, pais, estrellas) VALUES (?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query,
                hotel.getNombre(),
                hotel.getCiudad().getIdCiudad(), //Tengo que poner las ciudades
                hotel.getPais().getIdPais(),
                hotel.getEstrellas()
        );
    }


    public boolean eliminarHotel(int idHotel) {
        String query = "DELETE FROM hotel WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, idHotel);
    }

    public boolean actualizarHotel(Hotel hotel) {
        String query = "UPDATE hotel SET nombre = ?, ciudad = ?, pais = ?, estrellas = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query,
                hotel.getNombre(),
                hotel.getCiudad().getIdCiudad(),
                hotel.getPais().getIdPais(),
                hotel.getEstrellas(),
                hotel.getIdHotel());
    }
}
