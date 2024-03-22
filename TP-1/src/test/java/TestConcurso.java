import actividad1.Concurso;
import actividad1.Inscripcion;
import actividad1.Participante;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestConcurso {
    @Test
    public void testInscripcion(){
        //Inicialización
        LocalDate fechaInscripcion = LocalDate.of(2024,3,14); //14/03/2024
        Participante participante = new Participante("Benjamin","43434032");
        Concurso concurso = new Concurso("Concurso 1",
                LocalDate.of(2024,3,1),  //01/03/2024
                LocalDate.of(2024,3,31));//31/03/2024
        //Ejercitación
        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion);
        //Verificación
        assertTrue(concurso.estaInscripto(participante));
    }
    @Test
    public void testInscripcionPrimerDia(){
        //Inicialización
        LocalDate fechaInscripcion = LocalDate.of(2024,3,1); //01/03/2024
        Participante participante = new Participante("Benjamin","43434032");
        Concurso concurso = new Concurso("Concurso 1",
                LocalDate.of(2024,3,1),  //01/03/2024
                LocalDate.of(2024,3,31));//31/03/2024
        //Ejercitación
        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion);
        //Verificación
        assertTrue(concurso.estaInscripto(participante));
        assertEquals(10, concurso.puntosGanados(participante));
    }
    @Test
    public void testInscripcionFueraDelRango(){
        //Inicialización
        LocalDate fechaInscripcion = LocalDate.of(2024,4,1); //01/04/2024
        Participante participante = new Participante("Benjamin","43434032");
        Concurso concurso = new Concurso("Concurso 1",
                LocalDate.of(2024,3,1),  //01/03/2024
                LocalDate.of(2024,3,31));//31/03/2024
        //Ejercitación
        Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion);
        //Verificación
        assertTrue(!concurso.estaInscripto(participante));
        /*
        assertThrows(RuntimeException.class, ()->{
            Inscripcion.inscribirAEn(participante, concurso, fechaInscripcion);
        });
        */
    }
}
