
package controlador;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import modelo.ContenidoAudiovisual;
import modelo.RepositorioContenido;
import vista.VistaConsola;

public class ContenidoControlador {

    private final VistaConsola vista;
    private final RepositorioContenido repositorio;

    public ContenidoControlador(VistaConsola vista,
            RepositorioContenido repositorio) {

        this.vista = vista;
        this.repositorio = repositorio;
    }

    public void ejecutar(ContenidoAudiovisual[] contenidos) {

        vista.mostrarTitulo("SISTEMA DE CONTENIDO AUDIOVISUAL");

        mostrarContenidos(Arrays.asList(contenidos));

        try {

            repositorio.guardarContenidos(contenidos);

            vista.mostrarMensaje(
                    "Contenidos guardados correctamente");

            List<ContenidoAudiovisual> contenidosLeidos =
                    repositorio.cargarContenidos();

            vista.mostrarTitulo(
                    "CONTENIDOS LEÍDOS DESDE EL REPOSITORIO");

            mostrarContenidos(contenidosLeidos);

        } catch (IOException e) {

            vista.mostrarError(e.getMessage());
        }

        vista.mostrarMensaje("Prueba finalizada.");
    }

    private void mostrarContenidos(
            List<ContenidoAudiovisual> contenidos) {

        for (ContenidoAudiovisual contenido : contenidos) {

            vista.mostrarContenido(contenido);
        }
    }
}
