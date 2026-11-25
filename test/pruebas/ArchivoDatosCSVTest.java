
package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import modelo.Actor;
import modelo.Temporada;
import modelo.Investigador;
import modelo.ArchivoDatosCSV;

public class ArchivoDatosCSVTest {

    @TempDir
    Path carpetaTemporal;

    @Test
    public void testGuardarYCargarActores()
            throws IOException {

        Path archivo = carpetaTemporal.resolve("actores.csv");

        List<Actor> actores = List.of(
                new Actor("Tom Holland", 30),
                new Actor("Zendaya", 29)
        );

        ArchivoDatosCSV.guardarActores(actores, archivo);

        List<Actor> resultado =
                ArchivoDatosCSV.cargarActores(archivo);

        assertEquals(2, resultado.size());

        assertEquals("Tom Holland",
                resultado.get(0).getNombre());

        assertEquals(30, resultado.get(0).getEdad());

        assertEquals("Zendaya",
                resultado.get(1).getNombre());

        assertEquals(29, resultado.get(1).getEdad());
    }

    @Test
    public void testGuardarYCargarTemporadas()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("temporadas.csv");

        List<Temporada> temporadas = List.of(
                new Temporada(1, 10),
                new Temporada(2, 8)
        );

        ArchivoDatosCSV.guardarTemporadas(
                temporadas, archivo);

        List<Temporada> resultado =
                ArchivoDatosCSV.cargarTemporadas(archivo);

        assertEquals(2, resultado.size());

        assertEquals(1, resultado.get(0).getNumero());
        assertEquals(10, resultado.get(0).getEpisodios());

        assertEquals(2, resultado.get(1).getNumero());
        assertEquals(8, resultado.get(1).getEpisodios());
    }

    @Test
    public void testGuardarYCargarInvestigadores()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("investigadores.csv");

        List<Investigador> investigadores = List.of(
                new Investigador("Carlos", "Astronomía"),
                new Investigador("María", "Biología")
        );

        ArchivoDatosCSV.guardarInvestigadores(
                investigadores, archivo);

        List<Investigador> resultado =
                ArchivoDatosCSV.cargarInvestigadores(archivo);

        assertEquals(2, resultado.size());

        assertEquals("Carlos",
                resultado.get(0).getNombre());

        assertEquals("Astronomía",
                resultado.get(0).getEspecialidad());

        assertEquals("María",
                resultado.get(1).getNombre());

        assertEquals("Biología",
                resultado.get(1).getEspecialidad());
    }

    @Test
    public void testArchivosInexistentes()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("inexistente.csv");

        assertTrue(
                ArchivoDatosCSV.cargarActores(archivo)
                        .isEmpty());

        assertTrue(
                ArchivoDatosCSV.cargarTemporadas(archivo)
                        .isEmpty());

        assertTrue(
                ArchivoDatosCSV.cargarInvestigadores(archivo)
                        .isEmpty());
    }

    @Test
    public void testListasVacias()
            throws IOException {

        Path actores =
                carpetaTemporal.resolve("actores.csv");

        Path temporadas =
                carpetaTemporal.resolve("temporadas.csv");

        Path investigadores =
                carpetaTemporal.resolve("investigadores.csv");

        ArchivoDatosCSV.guardarActores(
                List.of(), actores);

        ArchivoDatosCSV.guardarTemporadas(
                List.of(), temporadas);

        ArchivoDatosCSV.guardarInvestigadores(
                List.of(), investigadores);

        assertTrue(
                ArchivoDatosCSV.cargarActores(actores)
                        .isEmpty());

        assertTrue(
                ArchivoDatosCSV.cargarTemporadas(temporadas)
                        .isEmpty());

        assertTrue(
                ArchivoDatosCSV.cargarInvestigadores(investigadores)
                        .isEmpty());
    }

    @Test
    public void testEdadInvalida()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("actores.csv");

        Files.writeString(archivo,
                "nombre;edad\nTom Holland;abc");

        assertThrows(IOException.class, () -> {

            ArchivoDatosCSV.cargarActores(archivo);
        });
    }

    @Test
    public void testTemporadaInvalida()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("temporadas.csv");

        Files.writeString(archivo,
                "numero;episodios\n1;abc");

        assertThrows(IOException.class, () -> {

            ArchivoDatosCSV.cargarTemporadas(archivo);
        });
    }

    @Test
    public void testInvestigadorInvalido()
            throws IOException {

        Path archivo =
                carpetaTemporal.resolve("investigadores.csv");

        Files.writeString(archivo,
                "nombre;especialidad\nCarlos");

        assertThrows(IOException.class, () -> {

            ArchivoDatosCSV.cargarInvestigadores(archivo);
        });
    }
}
