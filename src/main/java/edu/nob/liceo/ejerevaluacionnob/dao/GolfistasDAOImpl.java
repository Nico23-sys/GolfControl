package edu.nob.liceo.ejerevaluacionnob.dao;

import edu.nob.liceo.ejerevaluacionnob.db.DataBaseConnection;
import edu.nob.liceo.ejerevaluacionnob.model.Golfistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GolfistasDAOImpl {

    public List<Golfistas> getAllGolfistas() {
        List<Golfistas> golfistasBD= new ArrayList<>();
        String sql = "select * from golfistas";

        try(Connection conn= DataBaseConnection.getConnection();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery()){

            while(rs.next()){
               Golfistas golfista = new Golfistas(
                        rs.getInt("id_golfista"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getString("tipopalo"),
                        rs.getString("pais")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return golfistasBD;
    }
}
