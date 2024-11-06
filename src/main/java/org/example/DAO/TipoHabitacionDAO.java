package org.example.DAO;

import org.example.Model.TipoHabitacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Model.TipoHabitacion;

public class TipoHabitacionDAO {
    private ConnectionDAO connectionDAO;

    public TipoHabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<TipoHabitacion> obtenerTodosLosTiposHabitacion() {
        List<TipoHabitacion> tipoHabitacion = new ArrayList<>();
        String query = "SELECT idTipoHabitacion, nombre FROM tipoHabitacion";
        try (ResultSet rs = connectionDAO.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("idTipoHabitacion");
                String nombre = rs.getString("nombre");
                tipoHabitacion.add(new TipoHabitacion(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoHabitacion;
    }
}
