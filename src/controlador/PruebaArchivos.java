
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Actor;
import modelo.Temporada;
import modelo.Investigador;
import modelo.ArchivoDatosCSV;

public class PruebaArchivos {

    public static void main(String[] args) {

        try {
            // Crear actores.
            List<Actor> actores = new ArrayList<>();
            actores.add(new Actor("Tom Holland", 30));
            actores.add(new Actor("Zendaya", 29));

            // Crear temporadas.
            List<Temporada> temporadas = new ArrayList<>();
            temporadas.add(new Temporada(1, 10));
            temporadas.add(new Temporada(2, 8));

            // Crear investigadores.
            List<Investigador> investigadores = new ArrayList<>();
            investigadores.add(new Investigador("Carlos", "Astronomía"));
            investigadores.add(new Investigador("María", "Biología"));

            // Guardar los datos.
            ArchivoDatosCSV.guardarActores(actores);
            ArchivoDatosCSV.guardarTemporadas(temporadas);
            ArchivoDatosCSV.guardarInvestigadores(investigadores);

            System.out.println("DATOS GUARDADOS CORRECTAMENTE");

            // Leer los datos.
            System.out.println("\nACTORES:");
            for (Actor actor : ArchivoDatosCSV.cargarActores()) {
                System.out.println(actor.getNombre() + " - " + actor.getEdad());
            }

            System.out.println("\nTEMPORADAS:");
            for (Temporada temporada : ArchivoDatosCSV.cargarTemporadas()) {
                System.out.println(
                    "Temporada " + temporada.getNumero() +
                    " - Episodios: " + temporada.getEpisodios()
                );
            }

            System.out.println("\nINVESTIGADORES:");
            for (Investigador investigador : ArchivoDatosCSV.cargarInvestigadores()) {
                System.out.println(
                    investigador.getNombre() + " - " +
                    investigador.getEspecialidad()
                );
            }

        } catch (IOException e) {
            System.out.println("Error al manejar los archivos: " + e.getMessage());
        }
    }
}
