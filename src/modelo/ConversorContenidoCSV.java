
package modelo;

public interface ConversorContenidoCSV {

    String obtenerTipo();

    String obtenerDato(ContenidoAudiovisual contenido);

    ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato);
}
