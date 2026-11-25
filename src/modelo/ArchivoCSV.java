
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchivoCSV implements RepositorioContenido {

    private final Path ruta;

    private final Map<String, ConversorContenidoCSV> conversores =
            new HashMap<>();

    // Constructor para el funcionamiento normal.
    public ArchivoCSV(ConversorContenidoCSV... listaConversores) {

        this(Paths.get("contenidos.csv"), listaConversores);
    }

    // Constructor que permite utilizar una ruta personalizada.
    public ArchivoCSV(Path ruta,
            ConversorContenidoCSV... listaConversores) {

        this.ruta = ruta;

        for (ConversorContenidoCSV conversor : listaConversores) {

            conversores.put(conversor.obtenerTipo(), conversor);
        }
    }

    @Override
    public void guardarContenidos(ContenidoAudiovisual[] contenidos)
            throws IOException {

        try (BufferedWriter escritor = Files.newBufferedWriter(
                ruta, StandardCharsets.UTF_8)) {

            escritor.write("id;tipo;titulo;duracion;genero;dato");
            escritor.newLine();

            for (ContenidoAudiovisual contenido : contenidos) {

                escritor.write(convertirLinea(contenido));
                escritor.newLine();
            }
        }
    }

    private String convertirLinea(ContenidoAudiovisual contenido)
            throws IOException {

        String tipo = contenido.getClass().getSimpleName();

        ConversorContenidoCSV conversor = obtenerConversor(tipo);

        String dato = conversor.obtenerDato(contenido);

        return contenido.getId() + ";"
                + tipo + ";"
                + limpiar(contenido.getTitulo()) + ";"
                + contenido.getDuracionEnMinutos() + ";"
                + limpiar(contenido.getGenero()) + ";"
                + limpiar(dato);
    }

    @Override
    public List<ContenidoAudiovisual> cargarContenidos()
            throws IOException {

        List<ContenidoAudiovisual> contenidos = new ArrayList<>();

        if (!Files.exists(ruta)) {
            return contenidos;
        }

        try (BufferedReader lector = Files.newBufferedReader(
                ruta, StandardCharsets.UTF_8)) {

            lector.readLine();

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (!linea.trim().isEmpty()) {

                    contenidos.add(convertirContenido(linea));
                }
            }
        }

        return contenidos;
    }

    private ContenidoAudiovisual convertirContenido(String linea)
            throws IOException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 6) {

            throw new IOException("Línea CSV inválida: " + linea);
        }

        try {

            int id = Integer.parseInt(datos[0]);
            String tipo = datos[1];
            String titulo = datos[2];
            int duracion = Integer.parseInt(datos[3]);
            String genero = datos[4];
            String dato = datos[5];

            ConversorContenidoCSV conversor = obtenerConversor(tipo);

            return conversor.crearContenido(
                    id, titulo, duracion, genero, dato);

        } catch (NumberFormatException e) {

            throw new IOException(
                    "Número inválido en la línea: " + linea, e);
        }
    }

    private ConversorContenidoCSV obtenerConversor(String tipo)
            throws IOException {

        ConversorContenidoCSV conversor = conversores.get(tipo);

        if (conversor == null) {

            throw new IOException(
                    "Tipo de contenido no compatible: " + tipo);
        }

        return conversor;
    }

    private String limpiar(String texto) {

        return texto.replace(";", ",")
                    .replace("\n", " ")
                    .replace("\r", " ");
    }
}
