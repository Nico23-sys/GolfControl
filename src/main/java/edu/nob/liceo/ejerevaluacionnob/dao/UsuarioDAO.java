package edu.nob.liceo.ejerevaluacionnob.dao;

import edu.nob.liceo.ejerevaluacionnob.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    boolean validarCredenciales(String username, String password);
    Usuario getUsuarioPorUsername(String username);

    List<Usuario> getAllUsuarios();
    List<Usuario> buscarUsuario(String terminoBusq);

}
