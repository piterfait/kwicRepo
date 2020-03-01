package es.uned.master.java.POJO;
/**
 * Implementacíon propia de excepciones.
 * La idea es tener una relación de tipos de excepciones y lanzarlas identificando el tipo y la
 * localización del error
 * 
 * @author	Peter Fight
 * @version 0.2
 * @link	<a href="https://thumbs.dreamstime.com/b/excepción-del-texto-de-la-escritura-palabra-concepto-negocio-para-persona-o-cosa-que-se-excluyen-diversos-mensajes-las-ideas-120462349.jpg">Excepción</a> 
 * 
 */
public class KwicException extends RuntimeException{
	/**
	 * Variable ¿chorras? que pide el compilador
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Enumeración de tipos de excepciones a controlar
	 */
	public static enum TipoExcepcion{
		CONSTRUCTOR_NULO,
		ERROR_ARCHIVO,
		TITULO_INVALIDO
	}
	/**
	 * Dado que hay que llamar al constructor super directamente en el constructor, construyo el
	 * parámetro mediante este método
	 * @param tipo	Tipo de excepción dentro de la enumeración
	 * @return	El mensaje vinculado a la excepción
	 * @see	#KwicException.KwicException Ir al constructor
	 */
	private static String getMensajeError(TipoExcepcion tipo)
	{
		String respuesta = "";
		switch(tipo) {
			case CONSTRUCTOR_NULO:
				respuesta = "\nCONSTRUCTOR VACÍO!!!";
				break;
			case ERROR_ARCHIVO:
				respuesta = "\nERROR MUY MUY GRAVE RELACIONADO CON UN ARCHIVO.\nExiste peligro de muerte, "
						+ "o peor aún, de despido!!\nAcciones recomendadas: \n\t2.- Cabeza bajo el ala, "
						+ "\n\t3.- Delegar en el nuevo, \n\t1.- Baja por coronavirus!!!\n";
				break;
			case TITULO_INVALIDO:
				respuesta = "\nTÍTULO INVÁLIDO!!!";
				break;
			default:
				respuesta = "";
				break;
		}
		return respuesta;
	}
	/**
	 * Constructor de la excepción que llama al constructor super con el parámetro del error a mostrar
	 * @param tipo	El tipo de excepción para segmentar el mensaje
	 * @param donde	La ubicación del error
	 */
	public KwicException(TipoExcepcion tipo, String donde)
	{
		super(
				getMensajeError(tipo) +
				"Localizado en: " + donde
		);
		
	}
}
