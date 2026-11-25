
package vista;

import modelo.ContenidoAudiovisual;

public class VistaConsola {

    public void mostrarTitulo(String titulo) {

        System.out.println("\n" + titulo);
        System.out.println("--------------------------------");
    }

    public void mostrarContenido(ContenidoAudiovisual contenido) {

        System.out.println("Detalles del contenido: "
                + contenido.obtenerTipoContenido());

        System.out.println("ID: " + contenido.getId());

        System.out.println("Título: " + contenido.getTitulo());

        System.out.println("Duración en minutos: "
                + contenido.getDuracionEnMinutos());

        System.out.println("Género: " + contenido.getGenero());

        System.out.println(contenido.obtenerNombreDato()
                + ": " + contenido.obtenerValorDato());

        System.out.println();
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {

        System.out.println("Error: " + mensaje);
    }
}
