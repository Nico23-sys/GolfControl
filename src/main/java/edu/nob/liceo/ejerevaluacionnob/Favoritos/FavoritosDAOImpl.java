package edu.nob.liceo.ejerevaluacionnob.Favoritos;

import edu.nob.liceo.ejerevaluacionnob.db.DataBaseConnection;
import edu.nob.liceo.ejerevaluacionnob.model.Golfistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritosDAOImpl implements FavoritosDAO {
    @Override
    public List<Golfistas> getFavoritosPorUsuario(int usuario_id) {
        List<Golfistas> listafav = new ArrayList<>();
        String sql = "SELECT g.* FROM golfistas g " +
                "INNER JOIN favoritos f ON g.id_golfista = f.id_jugador " +
                "WHERE f.usuario_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listafav.add(new Golfistas(
                            rs.getInt("id_golfista"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("edad"),
                            rs.getString("pais"),
                            rs.getString("tipopalo")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listafav;
    }

    @Override
    public void quitarFavorito(int usuarioId, int idGolfista) {
        String sql = "DELETE FROM favoritos WHERE usuario_id = ? AND id_jugador = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, idGolfista);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean yaEsFavorito(int usuarioId, int idGolfista) {
        String sql = "SELECT 1 FROM favoritos WHERE usuario_id = ? AND id_jugador = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, idGolfista);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void addFavorito(int usuarioId, int idGolfista) {
        if (yaEsFavorito(usuarioId, idGolfista)) return; // Evitar duplicados

        String sql = "INSERT INTO favoritos (usuario_id, id_jugador) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, idGolfista);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



