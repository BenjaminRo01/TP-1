package persistencia;

import actividad1.Concurso;
import actividad1.Participante;
import actividad1.RegistroDeInscripcion;

import java.sql.*;
import java.time.LocalDate;

public class EnBBDDRegistroInscripcion implements RegistroDeInscripcion {
    private String url;
    private String sqlInsert = "INSERT INTO inscripciones (fecha, id_participante, id_concurso) VALUES (?,?,?);";

    public EnBBDDRegistroInscripcion(String url){
        this.url = url;
    }
    @Override
    public void registrar(LocalDate fecha, Participante participante, Concurso concurso) {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement pstm = conn.prepareStatement(sqlInsert)){
            pstm.setDate(1, Date.valueOf(fecha));
            pstm.setString(2, participante.obtenerNombre());
            pstm.setString(3, concurso.obtenerNombre());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("No se ha podido registrar.", e);
        }
    }
}
