
package modelo;

import java.io.IOException;

public interface EscritorContenido {

    void guardarContenidos(ContenidoAudiovisual[] contenidos)
            throws IOException;
}
