
package modelo;

public class Documental extends ContenidoAudiovisual {

    private String tema;

    public Documental(String titulo, int duracionEnMinutos,
            String genero, String tema) {

        super(titulo, duracionEnMinutos, genero);
        this.tema = tema;
    }

    public Documental(String titulo, int duracionEnMinutos,
            String genero, String tema, int id) {

        super(titulo, duracionEnMinutos, genero, id);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String obtenerTipoContenido() {
        return "documental";
    }

    @Override
    public String obtenerNombreDato() {
        return "Tema";
    }

    @Override
    public String obtenerValorDato() {
        return tema;
    }
}
