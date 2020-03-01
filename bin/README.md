# KWIC

# COMENTARIOS SOBRE LA RESOLUCIÓN DE LA PRÁCTICA
![Representación Kwic](https://i.pinimg.com/originals/66/77/88/667788e0b1f08ff1e1cfce11d303b203.gif)
(...jugando con los READMEs...)

## Inclusión de comentarios everywhere
  * Se han añadido comentarios sobre absolutamente todos los elementos que aparecen en el código, así como en las cabeceras de las clases e interfaces.

## Modificación del diseño de la aplicación para promover la legibilidad y el encapsulamiento

#### Inclusión de la interfaz ICadenaKwic
```
public interface ICadenaKwic<T> extends Comparable<T>{
	Set<String> getFrasesVinculadas();
	void addFrasesVinculadas(String frase);
}
```
Interfaz que contiene las cadenas que procesa la clase Kwic.La versión 0.1 carecía de interfaz, aquí la empleamos para mayor independencia de los KeyWord. Con esta abstracción los keywords sólo necesitan saber que son de tipo CadenaKwic y preocuparse de gestionar las frases vinculadas (añadir frases, y recuperar las frases ya incluidas.Se ha optado por anidar las frases vinculadas dentro de los keywords puesto que las frases están vinculadas a los keywords. Extiende Comparable para permitir implementar los Treeset cuando se trabaja con colecciones de keywords. Se incorpora un tipoAnónimo para mayor flexibilidad y permitir  adaptarnos a nuevos tipos de cadenas que pudieran darse en el futuro.

#### Inclusión de la clase abstracta CadenaKwic que implementa ICadenaKwic
```
protected CadenaKwic(String str){
	this.setTk(str);
}
```
Clase que implementa la interfaz ICadenaKwic. De ella hereda la clase KeyWordKwic. Sobreescribe los métodos de la interfaz Comparable para permitir implementar los Treeset. Se crea un nivel de abstracción intermedio entre la interfaz y la clase KeyWordKwic para mayor independencia y poder gestionar la implementación de IComparable de forma independiente. Debido a su carácter de clase intermedia se crea como clase Abstracta.

#### Sustitución de la clase TituloKwic por KeyWordKwic
#####Se emplea tanto para las keywords indexables como para las no indexables. Puesto que ambas keywords deben ser comparadas entre sí, se ha decidido no encapsular su singularidad.
Esta clase comprende las keywords. Incluye una relación de las frases vinculadas a cada KeyWord. Implementa los método abstractos de CadenaKwic (getFrasesVinculadas y addFrasesVinculadas). Sustituye a TituloKwic para adecuar la semántica a la readaptacón de la clase.

#### Inclusión de la clase IIndiceKwic
```
	Set<KeyWordKwic> getKwic();
	void generaLosIndices(ArrayList<String> frases);
```
Interfaz que se encarga de sentar las bases de la lógica de la gestión de KeyWords. En el constructor se 
incluyen las "noclaves" y con ellas se puede ejecutar el generaLosIndices. getKwic será necesario para poder lanzar el método toString().

#### Inclusión de la clase IndiceKwic
Clase que implementa la interfaz IIndiceKwic. Es la clase que se encarga de procesar las frases y generar los índices.

####	 Inclusión de métodos públicos de acceso (getters y setters)
Para las variables internas que deben ser accedidas desde fuera. Por ejemplo, el listado de KeyWords indexadas de la clase IndiceKwic.

## Inclusión de pruebas unitarias
Comprueban el funcionamiento de las KeyWOrds y de la aplicación Kwic, verificando que se están controlando los errores posibles.

## Gestión de errores mejorada
 * Se ha incluído un parámetro que hereda directamente del tipo Exception para poder mostrar las excepciones de Java y así poder complementar la información del error.

## Recolección de claves y noclaves en archivos de texto y procesarlas

 * Se han incluido ambos archivos.
 * Se controlan asimismo aquellos casos en los que salte una excepción.

## Crear contenedor en hub docker y subirlo a Github
TODO - MODIFICAR ESTE PUNTO UNA VEZ HECHO!!!!!!!!!




