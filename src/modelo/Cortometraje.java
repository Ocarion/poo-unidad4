
package modelo;

public class Cortometraje extends ContenidoAudiovisual {

    private String director;

    public Cortometraje(String titulo, int duracionEnMinutos,
            String genero, String director) {

        super(titulo, duracionEnMinutos, genero);
        this.director = director;
    }

    public Cortometraje(String titulo, int duracionEnMinutos,
            String genero, String director, int id) {

        super(titulo, duracionEnMinutos, genero, id);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String obtenerTipoContenido() {
        return "cortometraje";
    }

    @Override
    public String obtenerNombreDato() {
        return "Director";
    }

    @Override
    public String obtenerValorDato() {
        return director;
    }
}
