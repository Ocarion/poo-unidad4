
package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.ContenidoAudiovisual;
import modelo.ConversorContenidoCSV;
import modelo.ConversorPelicula;
import modelo.ConversorSerie;
import modelo.ConversorDocumental;
import modelo.ConversorVideoYoutube;
import modelo.ConversorCortometraje;
import modelo.Pelicula;
import modelo.SerieDeTV;
import modelo.Documental;
import modelo.VideoYoutube;
import modelo.Cortometraje;

public class ConversoresTest {

    @Test
    public void testConversorPelicula() {

        ConversorContenidoCSV conversor =
                new ConversorPelicula();

        Pelicula pelicula = new Pelicula(
                "Interestelar", 169,
                "Ciencia ficción", "Paramount");

        assertEquals("Pelicula", conversor.obtenerTipo());

        assertEquals("Paramount",
                conversor.obtenerDato(pelicula));

        ContenidoAudiovisual resultado =
                conversor.crearContenido(
                        10, "Interestelar", 169,
                        "Ciencia ficción", "Paramount");

        assertTrue(resultado instanceof Pelicula);
        assertEquals(10, resultado.getId());
        assertEquals("Interestelar", resultado.getTitulo());
        assertEquals("Paramount", resultado.obtenerValorDato());
    }

    @Test
    public void testConversorSerie() {

        ConversorContenidoCSV conversor =
                new ConversorSerie();

        SerieDeTV serie = new SerieDeTV(
                "Dark", 60, "Suspenso", 3);

        assertEquals("SerieDeTV", conversor.obtenerTipo());
        assertEquals("3", conversor.obtenerDato(serie));

        ContenidoAudiovisual resultado =
                conversor.crearContenido(
                        11, "Dark", 60, "Suspenso", "3");

        assertTrue(resultado instanceof SerieDeTV);
        assertEquals(11, resultado.getId());
        assertEquals("3", resultado.obtenerValorDato());
    }

    @Test
    public void testConversorDocumental() {

        ConversorContenidoCSV conversor =
                new ConversorDocumental();

        Documental documental = new Documental(
                "El océano", 50,
                "Naturaleza", "Vida marina");

        assertEquals("Documental", conversor.obtenerTipo());

        assertEquals("Vida marina",
                conversor.obtenerDato(documental));

        ContenidoAudiovisual resultado =
                conversor.crearContenido(
                        12, "El océano", 50,
                        "Naturaleza", "Vida marina");

        assertTrue(resultado instanceof Documental);
        assertEquals(12, resultado.getId());
        assertEquals("Vida marina", resultado.obtenerValorDato());
    }

    @Test
    public void testConversorVideoYoutube() {

        ConversorContenidoCSV conversor =
                new ConversorVideoYoutube();

        VideoYoutube video = new VideoYoutube(
                "Introducción a Java", 15,
                "Educativo", "Canal de programación");

        assertEquals("VideoYoutube", conversor.obtenerTipo());

        assertEquals("Canal de programación",
                conversor.obtenerDato(video));

        ContenidoAudiovisual resultado =
                conversor.crearContenido(
                        13, "Introducción a Java", 15,
                        "Educativo", "Canal de programación");

        assertTrue(resultado instanceof VideoYoutube);
        assertEquals(13, resultado.getId());

        assertEquals("Canal de programación",
                resultado.obtenerValorDato());
    }

    @Test
    public void testConversorCortometraje() {

        ConversorContenidoCSV conversor =
                new ConversorCortometraje();

        Cortometraje corto = new Cortometraje(
                "El viaje", 20, "Drama", "Carlos Pérez");

        assertEquals("Cortometraje", conversor.obtenerTipo());

        assertEquals("Carlos Pérez",
                conversor.obtenerDato(corto));

        ContenidoAudiovisual resultado =
                conversor.crearContenido(
                        14, "El viaje", 20,
                        "Drama", "Carlos Pérez");

        assertTrue(resultado instanceof Cortometraje);
        assertEquals(14, resultado.getId());
        assertEquals("Carlos Pérez", resultado.obtenerValorDato());
    }

    @Test
    public void testTemporadasInvalidas() {

        ConversorContenidoCSV conversor =
                new ConversorSerie();

        assertThrows(NumberFormatException.class, () -> {

            conversor.crearContenido(
                    15, "Dark", 60,
                    "Suspenso", "abc");
        });
    }
}
