
package poo;

import modelo.ContenidoAudiovisual;
import modelo.Pelicula;
import modelo.SerieDeTV;
import modelo.Documental;
import modelo.VideoYoutube;
import modelo.Cortometraje;
import vista.VistaConsola;

public class PruebaAudioVisual {

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

        for (ContenidoAudiovisual contenido : contenidos) {
            vista.mostrarContenido(contenido);
        }
    }
}
