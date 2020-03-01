package es.uned.master.java.POJO;
import java.util.*;

import es.uned.master.java.POJO.KwicException.TipoExcepcion;
import es.uned.master.java.Utilidades.Utilidades;
/**
 * Clase que implementa la interfaz ICadenaKwic. De ella hereda la clase keyword
 * Sobreescribe los métodos de la interfaz Comparable para permitir implementar los Treeset. Se crea un nivel de abstracción
 * intermedio entre la interfaz y la clase KeyWordKwic para mayor independencia y poder gestionar
 * la implementación de IComparable de forma independiente. Debido a su carácter de clase intermedia
 * se crea como clase Abstracta.
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public abstract class CadenaKwic implements ICadenaKwic<CadenaKwic>{
	/**Variable tk que almacena la KeyWord a indexar. Modificador de acceso protected para que 
	 * la variable sea accesible desde KeyWordKwic de modo que se evite duplicidad de código.*/
	protected String tk;

	/** Getter de la cadena-variable tk
	 * @return	el token*/
	protected String getTk() {
		return tk;
	}
	
	/** Seter de la cadena-variable tk. La almacenamos en Mayúsculas.
	 * @param	tk	el tokata 
	 * */
	protected void setTk(String tk) {
		if(Utilidades.esNuloesBlanco(tk))
		{
			throw new KwicException(TipoExcepcion.CONSTRUCTOR_NULO, Thread.currentThread().getStackTrace()[0].toString());
		}
		this.tk = tk.toUpperCase();
	}
	/**
	 * Constructor de la clase
	 * @param 	str 	la cadena 
	 */
	protected CadenaKwic(String str){
		this.setTk(str);
	}
	
	/**
	 * La relación de igualdad la basamos en la cadena de la variable tk
	 * @param 	o 	Debe ser una instancia de CadenaKwic
	 * @throws 	KwicException 	Si no es CaenaKwic
	 */
	@Override
	public boolean equals(Object o) throws KwicException{
		if (o instanceof CadenaKwic){
			CadenaKwic tk= (CadenaKwic) o;
			return this.tk.equals(tk.toString());//La clase sólo tiene una variable, que es la que usamo para la comparación
		}
		else{
			throw new KwicException(TipoExcepcion.TITULO_INVALIDO,
					Thread.currentThread().getStackTrace()[0].toString());
		}
	}
	/**
	 * Al parecer, la implementación de IComparable lo requiería, pero por algún motivo si se incluye
	 * el @Override se queja, así que lo pongo comno deprecated y todo sigue funcionando. Luego se queda
	 * como curiosidad histórica. 
	 * @see	loquepongo	Pónle la anotación @Override y verás que el compilador se queja
	 */
	@Deprecated
	public int hasCode(){
		return this.tk.hashCode();//Hash para distinguir de otras variables tk
	}
	/**
	 * Comparamos sin atender a mayúsculas y minúsculas
	 * @param 	tk 	token que ordenaremos alfabéticamente (compareToIgnoreCase)
	 */
	@Override
	public int compareTo(CadenaKwic tk){
		return this.tk.compareToIgnoreCase(tk.toString().toUpperCase());
	}
	
	/**Los KeyWord tienen frases vinculadas que deberán poder recuperarse 
	 * @return	El listado de frases Vinculadas*/
	public abstract Set<String> getFrasesVinculadas();
	/**Los KeyWord tienen frases vinculadas que deberán poder incluirse
	 * @param	frase	La frase a añadir
	 * */
	public abstract void addFrasesVinculadas(String frase);
}

