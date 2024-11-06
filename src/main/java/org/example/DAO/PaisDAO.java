package org.example.DAO;
import org.example.Model.Pais;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO {
    private ConnectionDAO connectionDAO;

    public PaisDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<Pais> obtenerTodosLosPaises() {
        List<Pais> paises = new ArrayList<>();
        String query = "SELECT idPais, nombre FROM pais";
        try (ResultSet rs = connectionDAO.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("idPais");
                String nombre = rs.getString("nombre");
                paises.add(new Pais(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }
}
