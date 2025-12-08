package edu.nob.liceo.ejerevaluacionnob.dao;

import edu.nob.liceo.ejerevaluacionnob.model.Torneo;

import java.util.List;

public interface TorneoDAO {
    List<Torneo> getAllTorneos();

    List<Torneo> filtrarTorneos(String terminoBusq);


    void addTorneos(Torneo newTorneo);

    void actuTorneos(Torneo torneoSeleccionado);

    void eliminarTorneo(int idTorneo);
}
