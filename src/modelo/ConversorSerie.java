
package modelo;

public class ConversorSerie implements ConversorContenidoCSV {

    @Override
    public String obtenerTipo() {
        return "SerieDeTV";
    }

    @Override
    public String obtenerDato(ContenidoAudiovisual contenido) {

        SerieDeTV serie = (SerieDeTV) contenido;

        return String.valueOf(serie.getTemporadas());
    }

    @Override
    public ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato) {

        int temporadas = Integer.parseInt(dato);

        return new SerieDeTV(
                titulo, duracion, genero, temporadas, id);
    }
}
