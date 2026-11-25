
package modelo;

import java.io.IOException;
import java.util.List;

public interface LectorContenido {

    List<ContenidoAudiovisual> cargarContenidos()
            throws IOException;
}
