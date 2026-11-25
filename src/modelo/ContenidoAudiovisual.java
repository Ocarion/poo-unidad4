
package modelo;

public abstract class ContenidoAudiovisual {

    private static int contador = 0;

    private String titulo;
    private int duracionEnMinutos;
    private String genero;
    private int id;

    public ContenidoAudiovisual(String titulo, int duracionEnMinutos,
            String genero) {

        this(titulo, duracionEnMinutos, genero, contador++);
    }

    public ContenidoAudiovisual(String titulo, int duracionEnMinutos,
            String genero, int id) {

        this.id = id;
        this.titulo = titulo;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;

        if (contador <= id) {
            contador = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public abstract String obtenerTipoContenido();

    public abstract String obtenerNombreDato();

    public abstract String obtenerValorDato();
}
