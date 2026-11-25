
package modelo;

public class ConversorPelicula implements ConversorContenidoCSV {

    @Override
    public String obtenerTipo() {
        return "Pelicula";
    }

    @Override
    public String obtenerDato(ContenidoAudiovisual contenido) {

        Pelicula pelicula = (Pelicula) contenido;

        return pelicula.getEstudio();
    }

    @Override
    public ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato) {

        return new Pelicula(
                titulo, duracion, genero, dato, id);
    }
}
