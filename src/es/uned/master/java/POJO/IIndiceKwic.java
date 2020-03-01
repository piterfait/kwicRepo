package es.uned.master.java.POJO;

import java.util.ArrayList;
import java.util.Set;
/**
 * Interfaz que se encarga de sentar las bases de la lógica de los KeyWord. En el constructor se 
 * incluyen las "noclaves" y con ellas se puede ejecutar el generaLosIndices. getKwic será necesario 
 * para poder lanzar el método toString().
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public interface IIndiceKwic {
	/**
	 * Permite lanzar el toString()
	 * @return	la clase mismamente
	 */
	Set<KeyWordKwic> getKwic();
	/**
	 * El método mágico que generará los índices y sus frases vinculadas
	 * @param frases	las frases a indexar
	 */
	void generaLosIndices(ArrayList<String> frases);
}
