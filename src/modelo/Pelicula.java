
package modelo;

public class Pelicula extends ContenidoAudiovisual {

    private String estudio;

    public Pelicula(String titulo, int duracionEnMinutos,
            String genero, String estudio) {

        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio;
    }

    public Pelicula(String titulo, int duracionEnMinutos,
            String genero, String estudio, int id) {

        super(titulo, duracionEnMinutos, genero, id);
        this.estudio = estudio;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Override
    public String obtenerTipoContenido() {
        return "película";
    }

    @Override
    public String obtenerNombreDato() {
        return "Estudio";
    }

    @Override
    public String obtenerValorDato() {
        return estudio;
    }
}
