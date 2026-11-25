
package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.ContenidoAudiovisual;
import modelo.Pelicula;
import modelo.SerieDeTV;
import modelo.Documental;
import modelo.VideoYoutube;
import modelo.Cortometraje;

public class ContenidoAudiovisualTest {

    @Test
    public void testPelicula() {

        Pelicula pelicula = new Pelicula(
                "Interestelar", 169,
                "Ciencia ficción", "Paramount");

        assertEquals("Interestelar", pelicula.getTitulo());
        assertEquals(169, pelicula.getDuracionEnMinutos());
        assertEquals("Ciencia ficción", pelicula.getGenero());
        assertEquals("Paramount", pelicula.getEstudio());
    }

    @Test
    public void testSerieDeTV() {

        SerieDeTV serie = new SerieDeTV(
                "Dark", 60, "Suspenso", 3);

        assertEquals("Dark", serie.getTitulo());
        assertEquals(3, serie.getTemporadas());
    }

    @Test
    public void testDocumental() {

        Documental documental = new Documental(
                "El océano", 50, "Naturaleza", "Vida marina");

        assertEquals("El océano", documental.getTitulo());
        assertEquals("Vida marina", documental.getTema());
    }

    @Test
    public void testVideoYoutube() {

        VideoYoutube video = new VideoYoutube(
                "Introducción a Java", 15,
                "Educativo", "Canal de programación");

        assertEquals("Canal de programación", video.getCanal());
        assertEquals(15, video.getDuracionEnMinutos());
    }

    @Test
    public void testCortometraje() {

        Cortometraje corto = new Cortometraje(
                "El viaje", 20, "Drama", "Carlos Pérez");

        assertEquals("Carlos Pérez", corto.getDirector());
        assertEquals("El viaje", corto.getTitulo());
    }

    @Test
    public void testConservarId() {

        Pelicula pelicula = new Pelicula(
                "Interestelar", 169,
                "Ciencia ficción", "Paramount", 100);

        assertEquals(100, pelicula.getId());
    }

    @Test
    public void testIdentificadoresDiferentes() {

        Pelicula pelicula1 = new Pelicula(
                "Película 1", 90, "Acción", "Estudio 1");

        Pelicula pelicula2 = new Pelicula(
                "Película 2", 120, "Drama", "Estudio 2");

        assertNotEquals(pelicula1.getId(), pelicula2.getId());
    }

    @Test
    public void testPolimorfismo() {

        ContenidoAudiovisual[] contenidos = {

            new Pelicula("Interestelar", 169,
                    "Ciencia ficción", "Paramount"),

            new SerieDeTV("Dark", 60, "Suspenso", 3),

            new Documental("El océano", 50,
                    "Naturaleza", "Vida marina"),

            new VideoYoutube("Introducción a Java", 15,
                    "Educativo", "Canal de programación"),

            new Cortometraje("El viaje", 20,
                    "Drama", "Carlos Pérez")
        };

        for (ContenidoAudiovisual contenido : contenidos) {

            assertNotNull(contenido.getTitulo());
            assertNotNull(contenido.obtenerTipoContenido());
            assertNotNull(contenido.obtenerNombreDato());
            assertNotNull(contenido.obtenerValorDato());
        }
    }
}
