# Sistema de contenido audiovisual – Unidad 4

Proyecto académico de Programación Orientada a Objetos. El objetivo es mejorar un sistema audiovisual mediante programación limpia, principios SOLID, arquitectura MVC, persistencia CSV y pruebas unitarias.

## Funcionalidades

* Manejo de películas, series de televisión, documentales, videos de YouTube y cortometrajes.
* Escritura y lectura de contenidos audiovisuales en CSV, conservando sus identificadores.
* Almacenamiento independiente de actores, temporadas e investigadores.
* Presentación de información mediante una vista de consola.
* Manejo de errores de lectura y escritura.

## Tecnologías

* Java 25 (entorno utilizado durante las pruebas).
* Eclipse IDE.
* JUnit 6 (requiere Java 17 o superior).
* Git y GitHub.
* Draw.io para los diagramas UML.

## Estructura del proyecto

```text
poo-unidad4/
├── src/
│   ├── modelo/
│   ├── controlador/
│   ├── vista/
│   ├── poo/
│   └── uni1a/
├── test/
│   └── pruebas/
├── docs/
│   └── Diagrama\_UML.drawio
├── actores.csv
├── contenidos.csv
├── investigadores.csv
├── temporadas.csv
└── README.md
```

## Arquitectura MVC

* **Modelo:** clases audiovisuales, repositorios, conversores y almacenamiento de datos.
* **Vista:** `VistaConsola`, responsable de presentar los datos y mensajes.
* **Controlador:** `ContenidoControlador`, coordina la vista y el repositorio.
* **Punto de entrada:** `uni1a.Principal`.

## Aplicación de SOLID

|Principio|Aplicación en el proyecto|
|-|-|
|SRP|Separación de presentación, coordinación, persistencia y conversión de datos.|
|OCP|Incorporación de conversores mediante la interfaz `ConversorContenidoCSV`, sin modificar la lógica principal del repositorio para cada tipo registrado.|
|LSP|Uso polimórfico de las subclases de `ContenidoAudiovisual` mediante un contrato común.|
|ISP|Separación de las operaciones de lectura y escritura en `LectorContenido` y `EscritorContenido`.|
|DIP|El controlador recibe un `RepositorioContenido` en lugar de depender directamente de `ArchivoCSV`.|

## Ejecutar el proyecto

1. Clonar el repositorio:

```bash
   git clone https://github.com/Ocarion/poo-unidad4.git
   ```

2. En Eclipse, seleccionar **File > Import > General > Existing Projects into Workspace**.
3. Seleccionar la carpeta clonada e importar el proyecto.
4. Configurar un JDK compatible y comprobar que JUnit 6 esté en el Classpath.
5. Ejecutar `src/uni1a/Principal.java` mediante **Run As > Java Application**.
6. Para comprobar los datos adicionales, ejecutar `src/controlador/PruebaArchivos.java`.

## Archivos CSV

|Archivo|Información|
|-|-|
|`contenidos.csv`|ID, tipo, título, duración, género y dato específico.|
|`actores.csv`|Nombre y edad.|
|`temporadas.csv`|Número y cantidad de episodios.|
|`investigadores.csv`|Nombre y especialidad.|

Se utiliza UTF-8 y el punto y coma como separador. Los archivos de ejemplo se generan en el directorio de trabajo al ejecutar los programas. La escritura reemplaza el contenido anterior.

## Pruebas unitarias

Las pruebas se encuentran en `test/pruebas`. Para ejecutarlas, hacer clic derecho sobre el paquete `pruebas` y seleccionar **Run As > JUnit Test**.

|Clase de prueba|Pruebas|
|-|-:|
|ContenidoAudiovisualTest|8|
|ArchivoCSVTest|8|
|ConversoresTest|6|
|ContenidoControladorTest|3|
|ArchivoDatosCSVTest|8|
|**Total**|**33**|

Resultado comprobado en Eclipse: **33 pruebas exitosas, 0 errores y 0 fallos**. Se incluyen casos normales, casos límite y excepciones. Las pruebas de persistencia utilizan archivos temporales y las del controlador utilizan dependencias simuladas.

## Diagrama UML

El diagrama editable se encuentra en [docs/Diagrama\_UML.drawio](docs/Diagrama_UML.drawio). Puede abrirse con [diagrams.net](https://app.diagrams.net/).

## Limitaciones y mejoras futuras

* Los actores, temporadas e investigadores se almacenan independientemente, sin relaciones persistidas con los contenidos audiovisuales.
* Los puntos y comas y saltos de línea dentro de los textos se sustituyen al guardar; no se implementa el escapado CSV completo.
* Las pruebas verifican los casos definidos, pero no garantizan cobertura de todos los escenarios posibles.

## Autor

Marcos Galarza — Programación Orientada a Objetos, Unidad 4.

