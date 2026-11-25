
package modelo;

public class ConversorDocumental implements ConversorContenidoCSV {

    @Override
    public String obtenerTipo() {
        return "Documental";
    }

    @Override
    public String obtenerDato(ContenidoAudiovisual contenido) {

        Documental documental = (Documental) contenido;

        return documental.getTema();
    }

    @Override
    public ContenidoAudiovisual crearContenido(
            int id,
            String titulo,
            int duracion,
            String genero,
            String dato) {

        return new Documental(
                titulo, duracion, genero, dato, id);
    }
}
