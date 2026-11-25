
package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoDatosCSV {

    private static final Path ACTORES =
            Paths.get("actores.csv");

    private static final Path TEMPORADAS =
            Paths.get("temporadas.csv");

    private static final Path INVESTIGADORES =
            Paths.get("investigadores.csv");

    // Guardar actores.

    public static void guardarActores(List<Actor> actores)
            throws IOException {

        guardarActores(actores, ACTORES);
    }

    public static void guardarActores(
            List<Actor> actores, Path ruta)
            throws IOException {

        List<String> lineas = new ArrayList<>();

        lineas.add("nombre;edad");

        for (Actor actor : actores) {

            lineas.add(limpiar(actor.getNombre()) + ";"
                    + actor.getEdad());
        }

        Files.write(ruta, lineas, StandardCharsets.UTF_8);
    }

    // Cargar actores.

    public static List<Actor> cargarActores()
            throws IOException {

        return cargarActores(ACTORES);
    }

    public static List<Actor> cargarActores(Path ruta)
            throws IOException {

        List<Actor> actores = new ArrayList<>();

        for (String linea : leerLineas(ruta)) {

            String[] datos = separarDatos(linea);

            try {

                actores.add(new Actor(
                        datos[0],
                        Integer.parseInt(datos[1])));

            } catch (NumberFormatException e) {

                throw new IOException(
                        "Edad inválida: " + linea, e);
            }
        }

        return actores;
    }

    // Guardar temporadas.

    public static void guardarTemporadas(
            List<Temporada> temporadas)
            throws IOException {

        guardarTemporadas(temporadas, TEMPORADAS);
    }

    public static void guardarTemporadas(
            List<Temporada> temporadas, Path ruta)
            throws IOException {

        List<String> lineas = new ArrayList<>();

        lineas.add("numero;episodios");

        for (Temporada temporada : temporadas) {

            lineas.add(temporada.getNumero() + ";"
                    + temporada.getEpisodios());
        }

        Files.write(ruta, lineas, StandardCharsets.UTF_8);
    }

    // Cargar temporadas.

    public static List<Temporada> cargarTemporadas()
            throws IOException {

        return cargarTemporadas(TEMPORADAS);
    }

    public static List<Temporada> cargarTemporadas(Path ruta)
            throws IOException {

        List<Temporada> temporadas = new ArrayList<>();

        for (String linea : leerLineas(ruta)) {

            String[] datos = separarDatos(linea);

            try {

                temporadas.add(new Temporada(
                        Integer.parseInt(datos[0]),
                        Integer.parseInt(datos[1])));

            } catch (NumberFormatException e) {

                throw new IOException(
                        "Temporada inválida: " + linea, e);
            }
        }

        return temporadas;
    }

    // Guardar investigadores.

    public static void guardarInvestigadores(
            List<Investigador> investigadores)
            throws IOException {

        guardarInvestigadores(
                investigadores, INVESTIGADORES);
    }

    public static void guardarInvestigadores(
            List<Investigador> investigadores, Path ruta)
            throws IOException {

        List<String> lineas = new ArrayList<>();

        lineas.add("nombre;especialidad");

        for (Investigador investigador : investigadores) {

            lineas.add(limpiar(investigador.getNombre()) + ";"
                    + limpiar(investigador.getEspecialidad()));
        }

        Files.write(ruta, lineas, StandardCharsets.UTF_8);
    }

    // Cargar investigadores.

    public static List<Investigador> cargarInvestigadores()
            throws IOException {

        return cargarInvestigadores(INVESTIGADORES);
    }

    public static List<Investigador> cargarInvestigadores(
            Path ruta) throws IOException {

        List<Investigador> investigadores =
                new ArrayList<>();

        for (String linea : leerLineas(ruta)) {

            String[] datos = separarDatos(linea);

            investigadores.add(new Investigador(
                    datos[0], datos[1]));
        }

        return investigadores;
    }

    // Lee los registros sin incluir el encabezado.

    private static List<String> leerLineas(Path ruta)
            throws IOException {

        List<String> resultado = new ArrayList<>();

        if (!Files.exists(ruta)) {
            return resultado;
        }

        List<String> lineas = Files.readAllLines(
                ruta, StandardCharsets.UTF_8);

        for (int i = 1; i < lineas.size(); i++) {

            if (!lineas.get(i).trim().isEmpty()) {

                resultado.add(lineas.get(i));
            }
        }

        return resultado;
    }

    // Comprueba que existan las columnas necesarias.

    private static String[] separarDatos(String linea)
            throws IOException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 2) {

            throw new IOException(
                    "Línea CSV inválida: " + linea);
        }

        return datos;
    }

    private static String limpiar(String texto) {

        return texto.replace(";", ",")
                    .replace("\n", " ")
                    .replace("\r", " ");
    }
}
