import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import es.uned.master.java.POJO.*;
import es.uned.master.java.POJO.KwicException.TipoExcepcion;
import es.uned.master.java.Utilidades.Utilidades;
import es.uned.master.java.coleccion.*;

/**
 * Clase de entradas del programa Kwic.
 * Esta clase es el punto de entrada de la aplicación. Recoge la variable de "no claves"
 * así como la variable de frases a partir de dos archivos de texto.
 * 
 * @author	Peter Fight
 * @version 0.2
 * @link <a href="https://github.com/redeskako/EjemplosJ2SE/tree/master/MavenDocker/kwic">Proyecto original de redeskako</a>
 * 
 */
public class Driver {
	/**
	 * Método Main de la aplicación, contiene el flujo principal del programa. Lee
	 * los archivos noclaves.txt y frases.txt, los procesa y a continuación extrae
	 * las palabra clave. Finalmente pinta el resultado en la consola.
	 * 
	 * @param arg El parámetro arg del método main no se trata. En futuras versiones
	 * se podría usar para incluir la ruta de los archivos
	 * 
	 */
	public static void main(String[] arg) {
		//Para mayor información sobre cada método, pase el cursor por encima de los mismos.
		String noclaves;
		ArrayList<String> frases;
		noclaves = Utilidades.leerFichero("src/noclaves.txt").get(0);
		frases = Utilidades.leerFichero("src/frases.txt");
		Kwic kwic = new Kwic(noclaves);
		kwic.computaIndices(frases);
		System.out.println("[PsicoUltraMega] Kwic v0.2 (by Peter Fight):" + kwic.toString());
	}
}
