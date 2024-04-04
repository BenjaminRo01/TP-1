package actividad1;

import persistencia.EnBBDDRegistroInscripcion;
import persistencia.EnvioMailRegistroInscripcion;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Actividad 3
//        String url = "jdbc:mysql://localhost:3306/tp_2";
//        LocalDate fechaInscripcion = LocalDate.of(2024,3,14); //14/03/2024
//        Participante participante = new Participante("Benjamin","43434032");
//        Concurso concurso = new Concurso("Concurso 1",
//                LocalDate.of(2024,3,1),  //01/03/2024
//                LocalDate.of(2024,3,31));//31/03/2024
//        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, new EnBBDDRegistroInscripcion(url));

        //Actividad 4
        LocalDate fechaInscripcion = LocalDate.of(2024,3,14); //14/03/2024
        Participante participante = new Participante("Benjamin","43434032");
        Concurso concurso = new Concurso("Concurso 1",
                LocalDate.of(2024,3,1),  //01/03/2024
                LocalDate.of(2024,3,31));//31/03/2024

        String to = "to@example.com";
        String from = "from@example.com";
        final String username = "c276e21b0ab06a";
        final String password = "3416ef01d5a717";
        String host = "sandbox.smtp.mailtrap.io";
        String port = "2525";

        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion,
                new EnvioMailRegistroInscripcion(host, port, username, password, from, to));
    }
}
