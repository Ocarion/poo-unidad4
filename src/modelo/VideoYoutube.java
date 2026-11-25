
package modelo;

public class VideoYoutube extends ContenidoAudiovisual {

    private String canal;

    public VideoYoutube(String titulo, int duracionEnMinutos,
            String genero, String canal) {

        super(titulo, duracionEnMinutos, genero);
        this.canal = canal;
    }

    public VideoYoutube(String titulo, int duracionEnMinutos,
            String genero, String canal, int id) {

        super(titulo, duracionEnMinutos, genero, id);
        this.canal = canal;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    @Override
    public String obtenerTipoContenido() {
        return "video de YouTube";
    }

    @Override
    public String obtenerNombreDato() {
        return "Canal";
    }

    @Override
    public String obtenerValorDato() {
        return canal;
    }
}
