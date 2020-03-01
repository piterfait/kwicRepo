package es.uned.master.java.POJO;
import java.util.*;
/**
 * Clase que extiende CadenaKwic.
 * 
 * Esta clase comprende las keywords. Incluye una relación de las frases vinculadas a cada KeyWord
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public class KeyWordKwic extends CadenaKwic{
	/**
	 * Variable que almacena las frases vinculadas a un token.
	 */
	private Set<String> frasesVinculadas;
	/**
	 * Geter que devuelve las frases vinculadas
	 * @return 	Las frases vinculadas
	 */
	@Override
	public Set<String> getFrasesVinculadas(){
		return this.frasesVinculadas;
	}
	/**
	 * Añade las frases vinculadas y reemplaza por los caracteres '...' el token indexado
	 * @param	frase	La frase a añadir
	 */
	@Override
	public void addFrasesVinculadas(String frase) {
		if(this.frasesVinculadas == null)
		{
			this.frasesVinculadas = new TreeSet<String>();
		}
		this.frasesVinculadas.add(reemplaza(frase));
	}
	/**
	 * Llamo al constructor parent junto al parámetro de keyword, que se encarga de almacenar
	 * en mayúsculas el token.
	 * @param 	keyword	Token en cuestión.
	 * @see	#CadenaKwic.CadenaKwic	Constructor superior
	 */
	public KeyWordKwic(String keyword){
		super(keyword);
	}
	/**
	 * Devuelve el token, lo usamos al computar el índice. Así sabemos si el KeyWord está en el listado
	 * @see	#IndiceKwic.computaIndice
	 */
	public String getTk() {
		return this.tk;
	}
	/**
	 * Este metodo introduce una frase y cambia el token a reemplazar por los caracteres ' ...'
	 * @param frase -> Frase en la que sustituimos la variable (super) tk por ' ...'
	 * @return String -> La frase sin la keyword actual (super)
	 * @see	#addFrasesVinculadas	Lo usamos en addFrasesVinculadas
	 */
	public String reemplaza(String frase){
		StringTokenizer strk= new StringTokenizer(frase," ,");
		String resultado="";
		while (strk.hasMoreTokens()){
			String palabraAComparar= strk.nextToken();
			KeyWordKwic tk= new KeyWordKwic(palabraAComparar);
			if (super.tk.equals(tk.toString())){
				resultado += "... ";
			}else{
				resultado += palabraAComparar+" ";
			}
		}
		return resultado;
	}
	/**
	 * OJO, este método es importante sobreescribirlo porque lo que nos interesa es la variable 
	 * del token!! así que devuelvo esta variable
	 * @return El token
	 */
	@Override
	public String toString() {
		return this.tk.toUpperCase();
	}
}

