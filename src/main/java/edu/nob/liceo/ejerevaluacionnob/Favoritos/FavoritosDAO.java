package edu.nob.liceo.ejerevaluacionnob.Favoritos;

import edu.nob.liceo.ejerevaluacionnob.model.Golfistas;

import java.util.List;

public interface FavoritosDAO {
    List<Golfistas> getFavoritosPorUsuario(int usuario_id);

    void quitarFavorito(int usuarioId, int idGolfista);

    boolean yaEsFavorito(int usuarioId, int idGolfista);

    void addFavorito(int usuarioId, int idGolfista);
}
