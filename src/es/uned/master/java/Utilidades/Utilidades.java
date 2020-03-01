package es.uned.master.java.Utilidades;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import es.uned.master.java.POJO.KwicException;
/**
 * Clase con métodos estáticos auxiliares para toda la aplicación. Consta de una implementación del
 * string.isNullOrEmpty de C#, una cadena con el separador que se emplea en la lectura de las "no claves",
 * así como el método que se emplea para leer ficheros.
 * 
 * @author	Peter Fight
 * @version 0.2
 * 
 */
public class Utilidades {
	/**
	 * Comprueba si la cadena es nula o está vacía
	 * @param param	la cadena a comparar
	 */
	public static boolean esNuloesBlanco(String param) { 
	    return param == null || param.trim().length() == 0;
	}
	
	/**
	 * El separador que se emplea en la lectura de las "no claves"
	 */
	public static String MegaSplitter = " ,:";
	
	/**
	 * Método estático para la lectura de ficheros en texto plano. Implementación muy simple que se 
	 * podría complicar todo lo que quisiéramos. Dado que el enunciado del ejercicio pide exclusivamente 
	 * leer los archivos del proyecto. Entiendo que esta implementación se adecúa a la solicitud.
	 * @param fichero -> La ruta del fichero que queremos procesar
	 * @throws 	KwicException	Si el archivo no existe 
	 * 			o existe peligro de muerte 
	 * 				o, peor aún, de despido: 
	 * 					Catapumba!
	 */
	public static ArrayList<String> leerFichero(String fichero) throws KwicException {
		try {
			if(esNuloesBlanco(fichero))
			{
				//El archivo no existe, que reviente todo
				throw new Exception();
			}
			ArrayList<String> respuesta = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			String linea = br.readLine();
			if(esNuloesBlanco(linea))
			{
				//El archivo no existe, que reviente todo. Destrucción!!!!
				throw new Exception();
			}
			while (linea != null) {
				respuesta.add(linea);
				linea = br.readLine();
			}
			return respuesta;
		}
		catch(Exception e)
		{
			throw new KwicException(KwicException.TipoExcepcion.ERROR_ARCHIVO, Thread.currentThread().getStackTrace()[0].toString());
		}
	}
}
