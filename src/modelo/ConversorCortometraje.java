
package modelo;

public class ConversorCortometraje implements ConversorContenidoCSV {

    @Override
    public String obtenerTipo() {
        return "Cortometraje";
    }

    @Override
    public String obtenerDato(ContenidoAudiovisual contenido) {

        Cortometraje cortometraje = (Cortometraje) contenido;

        return cortometraje.getDirector();
    }

    @Override
    public ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato) {

        return new Cortometraje(
                titulo, duracion, genero, dato, id);
    }
}
