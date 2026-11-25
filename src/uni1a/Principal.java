
package uni1a;

import modelo.ArchivoCSV;
import modelo.RepositorioContenido;
import modelo.ContenidoAudiovisual;
import modelo.Cortometraje;
import modelo.Documental;
import modelo.Pelicula;
import modelo.SerieDeTV;
import modelo.VideoYoutube;

import modelo.ConversorPelicula;
import modelo.ConversorSerie;
import modelo.ConversorDocumental;
import modelo.ConversorVideoYoutube;
import modelo.ConversorCortometraje;

import vista.VistaConsola;
import controlador.ContenidoControlador;

public class Principal {

    public static void main(String[] args) {

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

        VistaConsola vista = new VistaConsola();

        RepositorioContenido repositorio = new ArchivoCSV(
                new ConversorPelicula(),
                new ConversorSerie(),
                new ConversorDocumental(),
                new ConversorVideoYoutube(),
                new ConversorCortometraje()
        );

        ContenidoControlador controlador =
                new ContenidoControlador(vista, repositorio);

        controlador.ejecutar(contenidos);
    }
}
