
package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import controlador.ContenidoControlador;
import modelo.ContenidoAudiovisual;
import modelo.Pelicula;
import modelo.RepositorioContenido;
import vista.VistaConsola;

public class ContenidoControladorTest {

    // Repositorio simulado para realizar las pruebas.
    private static class RepositorioPrueba
            implements RepositorioContenido {

        private List<ContenidoAudiovisual> contenidos =
                new ArrayList<>();

        private boolean guardado = false;
        private boolean cargado = false;
        private boolean generarError = false;

        @Override
        public void guardarContenidos(
                ContenidoAudiovisual[] contenidos)
                throws IOException {

            if (generarError) {
                throw new IOException("Error de prueba");
            }

            this.contenidos =
                    new ArrayList<>(Arrays.asList(contenidos));

            guardado = true;
        }

        @Override
        public List<ContenidoAudiovisual> cargarContenidos()
                throws IOException {

            cargado = true;

            return contenidos;
        }
    }

    // Vista simulada para comprobar los mensajes.
    private static class VistaPrueba extends VistaConsola {

        private List<String> mensajes = new ArrayList<>();

        private int contenidosMostrados = 0;

        private String error;

        @Override
        public void mostrarTitulo(String titulo) {

            mensajes.add(titulo);
        }

        @Override
        public void mostrarContenido(
                ContenidoAudiovisual contenido) {

            contenidosMostrados++;
        }

        @Override
        public void mostrarMensaje(String mensaje) {

            mensajes.add(mensaje);
        }

        @Override
        public void mostrarError(String mensaje) {

            error = mensaje;
        }
    }

    @Test
    public void testEjecutarControlador() {

        VistaPrueba vista = new VistaPrueba();

        RepositorioPrueba repositorio =
                new RepositorioPrueba();

        ContenidoControlador controlador =
                new ContenidoControlador(vista, repositorio);

        ContenidoAudiovisual[] contenidos = {

            new Pelicula("Interestelar", 169,
                    "Ciencia ficción", "Paramount")
        };

        controlador.ejecutar(contenidos);

        assertTrue(repositorio.guardado);
        assertTrue(repositorio.cargado);

        assertEquals(1, repositorio.contenidos.size());

        assertEquals(2, vista.contenidosMostrados);

        assertTrue(vista.mensajes.contains(
                "Contenidos guardados correctamente"));

        assertTrue(vista.mensajes.contains(
                "Prueba finalizada."));

        assertNull(vista.error);
    }

    @Test
    public void testControladorSinContenidos() {

        VistaPrueba vista = new VistaPrueba();

        RepositorioPrueba repositorio =
                new RepositorioPrueba();

        ContenidoControlador controlador =
                new ContenidoControlador(vista, repositorio);

        ContenidoAudiovisual[] contenidos =
                new ContenidoAudiovisual[0];

        controlador.ejecutar(contenidos);

        assertTrue(repositorio.guardado);
        assertTrue(repositorio.cargado);

        assertTrue(repositorio.contenidos.isEmpty());

        assertEquals(0, vista.contenidosMostrados);

        assertNull(vista.error);
    }

    @Test
    public void testControladorConError() {

        VistaPrueba vista = new VistaPrueba();

        RepositorioPrueba repositorio =
                new RepositorioPrueba();

        repositorio.generarError = true;

        ContenidoControlador controlador =
                new ContenidoControlador(vista, repositorio);

        ContenidoAudiovisual[] contenidos = {

            new Pelicula("Interestelar", 169,
                    "Ciencia ficción", "Paramount")
        };

        controlador.ejecutar(contenidos);

        assertEquals("Error de prueba", vista.error);

        assertFalse(repositorio.guardado);
        assertFalse(repositorio.cargado);

        assertFalse(vista.mensajes.contains(
                "Contenidos guardados correctamente"));

        assertTrue(vista.mensajes.contains(
                "Prueba finalizada."));
    }
}
