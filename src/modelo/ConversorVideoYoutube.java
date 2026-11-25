
package modelo;

public class ConversorVideoYoutube implements ConversorContenidoCSV {

    @Override
    public String obtenerTipo() {
        return "VideoYoutube";
    }

    @Override
    public String obtenerDato(ContenidoAudiovisual contenido) {

        VideoYoutube video = (VideoYoutube) contenido;

        return video.getCanal();
    }

    @Override
    public ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato) {

        return new VideoYoutube(
                titulo, duracion, genero, dato, id);
    }
}
