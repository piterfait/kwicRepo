package es.uned.master.java.coleccion;

import java.util.*;
import java.util.Map.Entry;

import es.uned.master.java.POJO.*;
import es.uned.master.java.POJO.KwicException.TipoExcepcion;
import es.uned.master.java.Utilidades.Utilidades;
/**
 * Clase principal de Kwic. Esta clase contiene la lógica abstracta de la aplicación. Se encarga de 
 * todo el procesado de las claves. La clase está dividida en tres regiones: REGIÓN 1: Inicialización
 * de la clase. REGIÓN 2: Cómputo de los índices. REGIÓN 3: Devolver los resultados en forma de cadena
 * para imprimir por la pantalla.
 * 
 * @author	Peter Fight
 * @version 0.2
 * @link <a href="https://github.com/redeskako/EjemplosJ2SE/tree/master/MavenDocker/kwic">Proyecto original de redeskako</a>
 * 
 */
public class Kwic {
	/**
	 * REGIÓN 1: INICIALIZACIÓN DE LA CLASE
	 */
	/**
	 * Variable de la clase que almacena la lógica de la aplicación
	 * @see #IndiceKwic
	 */
	private IIndiceKwic indiceKwic;
	/**
	 * Geter para poder llamar al método toString
	 * @return	La instancia SINGLETON de la clase
	 * @see	#toString
	 */
	public IIndiceKwic getKwic() {
		return this.indiceKwic;
	}
	/**
	 * Constructor de clase. La aplicación requiere sí o sí, conocer las claves a excluir. Si no se
	 * proporcionan, petardazo.
	 * @param clavesExcluir	La cadena con las claves a excluir
	 * @throws	KwicException
	 */
	public Kwic(String clavesExcluir) throws KwicException {
		if (Utilidades.esNuloesBlanco(clavesExcluir)) {
			throw new KwicException(TipoExcepcion.CONSTRUCTOR_NULO,
					Thread.currentThread().getStackTrace()[0].toString());
		}
		this.indiceKwic = new IndiceKwic(clavesExcluir);
	}

	
	/**
	 * REGIÓN 2: HAGO EL CÁLCULO FABULOSO Y CLARAMENTE FUTURISTA
	 */
	/**@deprecated Porque yo lo valgo 
	 * @see #computaIndice */
	public void computaclavesExcluir(String clavesExcluir) {
	}
	/**@deprecated Se ha trasladado la lógica a IndiceKwic*/
	@Deprecated
	public void computaIndice(String frase) {
	}
	/**
	 * La instancia SINGLETON de IndiceKwic tiene este método público para generar los índices
	 * @param frases	Las frases a procesar
	 * @see	#IndiceKwic
	 */
	public void computaIndices(ArrayList<String> frases) {
		this.indiceKwic.generaLosIndices(frases);
	}

	
	/**
	 * REGIÓN 3: DEVOLVER LOS RESULTADOS EN FORMA DE CADENA PARA IMPRIMIR POR CONSOLA
	 */
	/**
	 * Método toString
	 * @return 	Una cadena perfectamente confeccionada, lista para ser impresa por pantalla
	 */
	@Override
	public String toString() {
		String str = "";
		str += this.getKwic().toString();
		return str;
	}
}
