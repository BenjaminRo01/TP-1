package persistencia;

import actividad2.RegistroDeCostos;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnBBDDRegistoCostos implements RegistroDeCostos {
    private String url;
    private String sqlInsert = "INSERT INTO costos (fecha, costo) VALUES (?,?);";
    public EnBBDDRegistoCostos(String url){
        this.url = url;
    }
    @Override
    public void registrar(LocalDate fecha, Double costo) {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement pstm = conn.prepareStatement(sqlInsert)){
            pstm.setDate(1, Date.valueOf(fecha));
            pstm.setDouble(2, costo);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("No se ha podido registrar.", e);
        }
    }
}
