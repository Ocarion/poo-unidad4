
package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import modelo.ArchivoCSV;
import modelo.ContenidoAudiovisual;
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

public class ArchivoCSVTest {

    @TempDir
    Path carpetaTemporal;

    private Path archivo;
    private ArchivoCSV repositorio;

    @BeforeEach
    public void prepararPrueba() {

        archivo = carpetaTemporal.resolve("prueba.csv");

        repositorio = new ArchivoCSV(
                archivo,
                new ConversorPelicula(),
                new ConversorSerie(),
                new ConversorDocumental(),
                new ConversorVideoYoutube(),
                new ConversorCortometraje()
        );
    }

    @Test
    public void testGuardarArchivo() throws IOException {

        ContenidoAudiovisual[] contenidos = {

            new Pelicula("Interestelar", 169,
                    "Ciencia ficción", "Paramount")
        };

        repositorio.guardarContenidos(contenidos);

        assertTrue(Files.exists(archivo));
    }

    @Test
    public void testGuardarYCargarContenidos() throws IOException {

        ContenidoAudiovisual[] contenidos = {

            new Pelicula("Interestelar", 169,
                    "Ciencia ficción", "Paramount"),

            new SerieDeTV("Dark", 60,
                    "Suspenso", 3),

            new Documental("El océano", 50,
                    "Naturaleza", "Vida marina"),

            new VideoYoutube("Introducción a Java", 15,
                    "Educativo", "Canal de programación"),

            new Cortometraje("El viaje", 20,
                    "Drama", "Carlos Pérez")
        };

        repositorio.guardarContenidos(contenidos);

        List<ContenidoAudiovisual> resultado =
                repositorio.cargarContenidos();

        assertEquals(5, resultado.size());

        for (int i = 0; i < contenidos.length; i++) {

            assertEquals(contenidos[i].getId(),
                    resultado.get(i).getId());

            assertEquals(contenidos[i].getTitulo(),
                    resultado.get(i).getTitulo());

            assertEquals(contenidos[i].getGenero(),
                    resultado.get(i).getGenero());

            assertEquals(contenidos[i].getDuracionEnMinutos(),
                    resultado.get(i).getDuracionEnMinutos());

            assertEquals(contenidos[i].obtenerValorDato(),
                    resultado.get(i).obtenerValorDato());

            assertEquals(contenidos[i].getClass(),
                    resultado.get(i).getClass());
        }
    }

    @Test
    public void testArchivoInexistente() throws IOException {

        List<ContenidoAudiovisual> resultado =
                repositorio.cargarContenidos();

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testGuardarListaVacia() throws IOException {

        ContenidoAudiovisual[] contenidos =
                new ContenidoAudiovisual[0];

        repositorio.guardarContenidos(contenidos);

        assertTrue(repositorio.cargarContenidos().isEmpty());
    }

    @Test
    public void testArchivoVacio() throws IOException {

        Files.createFile(archivo);

        assertTrue(repositorio.cargarContenidos().isEmpty());
    }

    @Test
    public void testLineaInvalida() throws IOException {

        Files.writeString(archivo,
                "id;tipo;titulo;duracion;genero;dato\n"
                + "datos incorrectos");

        assertThrows(IOException.class, () -> {

            repositorio.cargarContenidos();
        });
    }

    @Test
    public void testNumeroInvalido() throws IOException {

        Files.writeString(archivo,
                "id;tipo;titulo;duracion;genero;dato\n"
                + "0;Pelicula;Interestelar;abc;Drama;Paramount");

        assertThrows(IOException.class, () -> {

            repositorio.cargarContenidos();
        });
    }

    @Test
    public void testTipoDesconocido() throws IOException {

        Files.writeString(archivo,
                "id;tipo;titulo;duracion;genero;dato\n"
                + "0;Desconocido;Ejemplo;90;Drama;Dato");

        assertThrows(IOException.class, () -> {

            repositorio.cargarContenidos();
        });
    }
}
