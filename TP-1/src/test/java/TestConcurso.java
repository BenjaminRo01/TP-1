import actividad1.Concurso;
import actividad1.Inscripcion;
import actividad1.Participante;
import org.junit.Test;
import persistencia.EnDiscoRegistroInscripcion;
import persistencia.FakeRegistroInscripcion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestConcurso {
    @Test
    public void testInscripcion(){
        //String path = "C:/Registros/Inscripciones.txt";
        LocalDate fechaInscripcion = LocalDate.of(2024,3,14); //14/03/2024
        LocalDate fechaInicio = LocalDate.of(2024,3,1); //01/03/2024
        LocalDate fechaFin = LocalDate.of(2024, 3, 31); //31/03/2024
        Participante participante = new Participante("Benjamin","43434032");
        Concurso concurso = new Concurso("Concurso 1", fechaInicio, fechaFin);

        //Actividad 1
        //Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, new EnDiscoRegistroInscripcion(path));
        //assertTrue(concurso.estaInscripto(participante));

        //Actividad 5
        FakeRegistroInscripcion fakeRegistroInscripcion = new FakeRegistroInscripcion();
        String str = "14/03/2024 || Benjamin || Concurso 1";
        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, fakeRegistroInscripcion);

        assertTrue(fakeRegistroInscripcion.startWith(str));
    }
    @Test
    public void testInscripcionPrimerDia(){
        //String path = "C:/Registros/Inscripciones.txt";
        LocalDate fechaInscripcion = LocalDate.of(2024,3,1); //01/03/2024
        LocalDate fechaInicio = LocalDate.of(2024,3,1); //01/03/2024
        LocalDate fechaFin = LocalDate.of(2024,3,31); //31/03/2024
        Participante participante = new Participante("Juan","40444022");
        Concurso concurso = new Concurso("Concurso 2", fechaInicio, fechaFin);

        //Actividad 1
        //Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, new EnDiscoRegistroInscripcion(path));
        //assertTrue(concurso.estaInscripto(participante));
        //assertEquals(10, concurso.puntosGanados(participante));

        //Actividad 5
        FakeRegistroInscripcion fakeRegistroInscripcion = new FakeRegistroInscripcion();
        String str = "01/03/2024 || Juan || Concurso 2";
        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, fakeRegistroInscripcion);

        assertTrue(fakeRegistroInscripcion.startWith(str));
    }
    @Test
    public void testInscripcionFueraDelRango(){
        //String path = "C:/Registros/Inscripciones.txt";
        LocalDate fechaInscripcion = LocalDate.of(2024,4,1); //01/04/2024
        LocalDate fechaInicio = LocalDate.of(2024, 3, 1); //01/03/2024
        LocalDate fechaFin = LocalDate.of(2024,3,31); //31/03/2024
        Participante participante = new Participante("Pedro","30123456");
        Concurso concurso = new Concurso("Concurso 3", fechaInicio, fechaFin);

        //Actividad 1
        //Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion);
        //assertThrows(RuntimeException.class, ()->{
        //    Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, new EnDiscoRegistroInscripcion(path));
        //});

        //Actividad 5
        FakeRegistroInscripcion fakeRegistroInscripcion = new FakeRegistroInscripcion();
        assertThrows(RuntimeException.class, ()->{
            Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion, fakeRegistroInscripcion);
        });
    }
}
