package es.uned.master.java.POJO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import es.uned.master.java.Utilidades.Utilidades;
/**
 * Clase que implementa la interfaz IIndiceKwic. Es la clase que se encarga de procesar las frases
 * y generar los índices.
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public class IndiceKwic implements IIndiceKwic{
	/**
	 * Aquí se almacenan los keywords Kwic que a su vez tienen frases vinculadas.
	 */
	private Set<KeyWordKwic> kwic;
	/**
	 * Método que devuelve la variable
	 * @return	la variable... Si ya lo he dicho!!
	 */
	@Override
	public Set<KeyWordKwic> getKwic(){
		if(this.kwic == null)
		{
			this.kwic = new TreeSet<KeyWordKwic>();
		}
		return this.kwic;
	}
	/**
	 * Método que añade un keyword a la variable con todos los keywords y sus frases
	 * @param t	El keyword a añadir
	 */
	private void addKwic(KeyWordKwic t) {
		this.kwic.add(t);
	}
	/**
	 * Aquí almacenamos las claves a excluir, son de uso interno de esta clase por lo que una vez
	 * generadas con el constructor, ya no necesitamos geter ni seter.
	 * (public para poder lanzar tests unitarios)
	 */
	public Set<KeyWordKwic> clavesExcluir;
	/**
	 * Constructor de clase, necesita las claves a Excluir puesto que son una pieza fundamental para
	 * la indexación que nos ocupa. 
	 * @param 	clavesExcluir	Las claves en línea de texto a convertir en TreeSet
	 */
	public IndiceKwic(String clavesExcluir)
	{
		setclavesExcluir(clavesExcluir);
	}
	/**
	 * Tokenizo la cadena de "no claves".
	 * 
	 * @param 	clavesExcluir	Concepto introducido por Schopenhauer. En él fundamenta
	 * la corriente filosófica que inció: el pesimismo filosófico... es que a veces la humanidad deprime.
	 * @see	#IndiceKwic.IndiceKwic	Constructor de clase
	 */
	public void setclavesExcluir(String clavesExcluir) {
		this.clavesExcluir = new TreeSet();
		StringTokenizer strk = new StringTokenizer(clavesExcluir, Utilidades.MegaSplitter);

		while (strk.hasMoreTokens()) {
			this.clavesExcluir.add(new KeyWordKwic(strk.nextToken()));
		}
	}	
	/**
	 * Este método interno de la clase se encarga de recuperar el keyword pre-existente en caso de
	 * existir, después añade las frases al keyword y lo añade a la variable de keyword, si existía
	 * previamente, reemplaza el valor.
	 * @param palabra	El keyword al que añadir frases
	 * @param frase	La frase a vincular
	 */
	private void computaIndice(KeyWordKwic palabra, String frase) {
		for(KeyWordKwic keyword: getKwic())
		{
			if(keyword.getTk().contentEquals(palabra.getTk()))
			{
				palabra = keyword;
			}
		}
		palabra.addFrasesVinculadas(frase);
		if(getKwic().contains(palabra))
		{
			getKwic().remove(palabra);
		}
		palabra.addFrasesVinculadas(frase);
		this.addKwic(palabra);

	}
	/**
	 * Método clave. Tokeniza las frases, recorre los tokens y en caso de no existir en el listado
	 * de "no claves" los procesa como keyword a indexar.
	 * @param	frases	Las frases a indexar
	 */
	@Override
	public void generaLosIndices(ArrayList<String> frases)
	{
		for (String frase : frases) {
			StringTokenizer strk = new StringTokenizer(frase, Utilidades.MegaSplitter);
			while (strk.hasMoreTokens()) {
				String token = strk.nextToken();
				KeyWordKwic palabra = new KeyWordKwic(token);
				if (!(this.clavesExcluir.contains(palabra))) {
					this.computaIndice(palabra, frase);
				}
			}
		}
	}
	/**
	 * @param	s	escribe las frases asociadas a una clave
	 * @return 	Una cadena con las frases asociadas a cada clave para poder imprimirlas por la pantalla
	 * @see	#IndiceKwic.toString	Método toString 
	 */
	private String escribeKwic(Set<String> s) {
		String str = "";
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			str += "\t" + it.next() + "\n";
		}
		return str;
	}
	/**
	 * Este método devuelve una cadena con las palabras no indexadas
	 * @return 	Una cadena con las palabras no indexadas
	 * @see	#IndiceKwic.toString	Método toString 
	 */
	private String escribeClavesExcluir() {
		String str = "\n\n--TOKENS NO INDEXADOS-- \n";
		Iterator<KeyWordKwic> it = this.clavesExcluir.iterator();
		while (it.hasNext()) {
			str += ", " + it.next().toString();
		}
		return str.replaceFirst(", ", "");
	}
	/**
	 * Método que devuelve el resultado de la indexación
	 * @return	El resultado de la indexación en String
	 */
	@Override
	public String toString() {
		String str = "";
		str += this.escribeClavesExcluir();
		str += "\n\n--INDICE--\n";
		for(int i = 0; i < getKwic().size(); i++)
		{
			KeyWordKwic t = (KeyWordKwic)getKwic().toArray()[i];
			str += i+".- " + t + "\n";
			str += escribeKwic(t.getFrasesVinculadas());
		}
		return str;
	}
}
