package es.uned.master.java.POJO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Interfaz que contiene las cadenas que procesa la clase Kwic.La versión 0.1 carecía de interfaz, 
 * aquí la empleamos para mayor independencia de los KeyWord. Con esta abstracción los keywords sólo 
 * necesitan saber que son de tipo CadenaKwic y preocuparse de gestionar las frases vinculadas.Se ha 
 * optado por anidar las frases vinculadas dentro de los keywords puesto que las frases están vinculadas 
 * a los keywords. Extiende Comparable para permitir implementar los Treeset cuando se trabaja con
 * colecciones de keywords. Se incorpora un tipoAnónimo para mayor flexibilidad y permitir  adaptarnos 
 * a nuevos tipos de cadenas que pudieran darse en el futuro.
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public interface ICadenaKwic<T> extends Comparable<T>{
	/**Los Indices tienen frases vinculadas que deberán poder recuperarse 
	 * @return	El listado de frases Vinculadas*/
	Set<String> getFrasesVinculadas();
	/**Los Indices tienen frases vinculadas que deberán poder incluirse
	 * @param	frase	La frase a añadir
	 * */
	void addFrasesVinculadas(String frase);
}
