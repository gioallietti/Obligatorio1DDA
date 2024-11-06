package org.example.Controller;
import org.example.DAO.HuespedDAO;
import org.example.DAO.PaisDAO;
import org.example.Model.Huesped;
import org.example.Model.Pais;
import org.example.Model.TipoDocumento;
import org.example.DAO.TipoDocumentoDAO;

import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;
    private PaisDAO paisDAO;
    private TipoDocumentoDAO tipoDocumentoDAO;

    public HuespedController() {
        this.huespedDAO = new HuespedDAO();
        this.paisDAO = new PaisDAO();
        this.tipoDocumentoDAO = new TipoDocumentoDAO();
    }

    public List<TipoDocumento> obtenerTodosLosTiposDocumentos(){
        return tipoDocumentoDAO.obtenerTodosLosTiposDocumentos();
    }

    public List<Pais> obtenerTodosLosPaises() {
        return paisDAO.obtenerTodosLosPaises();
    }

    public boolean agregarHuesped(Huesped huesped) {
        return huespedDAO.insertHuesped(huesped);
    }

    public boolean eliminarHuesped(int idHuesped) {
        return huespedDAO.eliminarHuesped(idHuesped);
    }

    public boolean actualizarHuesped(Huesped huesped) {
        return huespedDAO.actualizarHuesped(huesped);
    }

    public List<Huesped> obtenerTodosLosHuespedes(){
        return huespedDAO.obtenerTodosLosHuespedes();
    }
}
