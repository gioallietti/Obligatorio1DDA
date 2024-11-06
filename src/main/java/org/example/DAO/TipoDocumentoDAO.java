package org.example.DAO;

import org.example.Model.TipoDocumento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {
    private ConnectionDAO connectionDAO;

    public TipoDocumentoDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<TipoDocumento> obtenerTodosLosTiposDocumentos() {
        List<TipoDocumento> tipoDocumento = new ArrayList<>();
        String query = "SELECT idTipoDocumento, descripcion FROM tipoDocumento";
        try (ResultSet rs = connectionDAO.executeQuery(query)) {
            while (rs.next()) {
                int idTipoDocumento = rs.getInt("idTipoDocumento");
                String descripcion = rs.getString("descripcion");
                tipoDocumento.add(new TipoDocumento(idTipoDocumento, descripcion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoDocumento;
    }
}
