/**
 * 
 */
package es.uned.master.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import es.uned.master.java.POJO.KeyWordKwic;
import es.uned.master.java.POJO.IndiceKwic;
import es.uned.master.java.POJO.KwicException;
import es.uned.master.java.coleccion.Kwic;

/**
 * @author piterfait
 *
 */
class KwicTest {
	/*
	 * Al inicializar estas variables en un Beforeall y tener que estar compartidas por todos los tests
	 * las variables tienen que ser estáticas, a diferencia de si las inicializamos con el BeforeEach
	 */
	
	protected static ArrayList<String> frases = new ArrayList<String>();
	protected static String noclaves;
	protected static IndiceKwic kwic;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		frases.add("Keyword noclave1 noclave2");
		noclaves = "noclave1 noclave2";
		kwic = new IndiceKwic(noclaves);
	}


	@org.junit.jupiter.api.Nested
	@DisplayName("Leer fichero")
	class LeerFichero {
		@Test
		@DisplayName("La lectura de ficheros funciona correctamente")
		void LeerFichero()
		{
			try {
				
				Class driverClase = Class.forName("es.uned.master.java.Utilidades.Utilidades");
				Method leerFichero = driverClase.getMethod("leerFichero", String.class);
				ArrayList<String> resultado = (ArrayList<String>)leerFichero.invoke(driverClase.newInstance(), "src/frases.txt");
				assertTrue(resultado.size() > 0);
			}
			catch(Exception e) {
				fail("Error al leer el fichero");
			}
		}
		@Test
		@DisplayName("Leer fichero que no existe lo tenemos controlado")
		void LeerFicheroNoExiste()
		{
			try {
				
				Class driverClase = Class.forName("es.uned.master.java.Utilidades.Utilidades");
				Method leerFichero = driverClase.getMethod("leerFichero", String.class);
				ArrayList<String> resultado = (ArrayList<String>)leerFichero.invoke(driverClase.newInstance(), "noexisto.hackme");
				fail("Aquí debería saltar una excepción y no ha saltado");
			}
			catch(Exception e) {
				//La reflexión tiene estas cosas, hay que averiguar la causa de la excepcion para 
				//saber la excepción que ha provocado la excepción, en el mundo de las excepciones.
				assertTrue(e.getCause() instanceof KwicException);
			}
		}
		
		@Test
		@DisplayName("Si paso fichero nulo lo tengo controlado")
		void LeerFicheroParametroNulo()
		{
			try {
				
				Class driverClase = Class.forName("es.uned.master.java.Utilidades.Utilidades");
				Method leerFichero = driverClase.getMethod("leerFichero", String.class);
				ArrayList<String> resultado = (ArrayList<String>)leerFichero.invoke(driverClase.newInstance(), new Object[]{ null });
				fail("Aquí debería saltar una excepción y no ha saltado");
			}
			catch(Exception e) {
				//La reflexión tiene estas cosas, hay que averiguar la causa de la excepcion para 
				//saber la excepción que ha provocado la excepción, en el mundo de las excepciones.
				assertTrue(e.getCause() instanceof KwicException);
			}
		}
		
		@Test
		@DisplayName("Si paso parámetro de fichero vacío lo tengo controlado")
		void LeerFicheroParametroVacío()
		{
			try {
				
				Class driverClase = Class.forName("es.uned.master.java.Utilidades.Utilidades");
				Method leerFichero = driverClase.getMethod("leerFichero", String.class);
				ArrayList<String> resultado = (ArrayList<String>)leerFichero.invoke(driverClase.newInstance(), "");
				fail("Aquí debería saltar una excepción y no ha saltado");
			}
			catch(Exception e) {
				//La reflexión tiene estas cosas, hay que averiguar la causa de la excepcion para 
				//saber la excepción que ha provocado la excepción, en el mundo de las excepciones.
				assertTrue(e.getCause() instanceof KwicException);
			}
		}
	}
	
	
	
	
	
	@org.junit.jupiter.api.Nested
	@DisplayName("INICIALIZACIÓN DE LA CLASE")
	class Inicializacion {
		@Test
		@DisplayName("La clase Kwic inicializa correctamente")
		void Inicializacion()
		{
			try {
				assertTrue(kwic.clavesExcluir.size() == 2);
			}
			catch(Exception e) {
				fail("Error al inicializar la clase");
			}
		}
		
		@Test
		@DisplayName("Se controla el parámetro vacío")
		void ParametroVacio()
		{
			try {
				Kwic k = new Kwic("");
			}
			catch(Exception e) {
				assertTrue(e instanceof KwicException);
			}
		}
		
		@Test
		@DisplayName("Se controla el parámetro nulo")
		void ParametroNulo()
		{
			try {
				Kwic k = new Kwic(null);
			}
			catch(Exception e) {
				assertTrue(e instanceof KwicException);
			}
		}
	}
	
	
	@org.junit.jupiter.api.Nested
	@DisplayName("CÓMPUTO DE KEYWORD IN CONTEXT")
	class Computo {
		@Test
		@DisplayName("El cómputo funciona correctamente")
		void Computo()
		{
			try {
				kwic.generaLosIndices(frases);
				assertTrue(kwic.getKwic().size() == 1);
			}
			catch(Exception e) {
				fail("Error al computar las keywords");
			}
		}
	}
	
	
	@org.junit.jupiter.api.Nested
	@DisplayName("DEVOLVER LOS RESULTADOS EN FORMA DE CADENA PARA IMPRIMIR POR CONSOLA")
	class Devolver {
		@Test
		@DisplayName("Los resultados se imprimen correctamente")
		void Devolver()
		{
			try {
				String cadenaResultante = kwic.toString();
				boolean keyExiste = Pattern.compile("\n0.- KEYWORD\n").matcher(cadenaResultante).find();
				boolean noClavesExisten = Pattern.compile(".*NOCLAVE1, NOCLAVE2.*").matcher(cadenaResultante).find();
				assertTrue(keyExiste && noClavesExisten);
			}
			catch(Exception e) {
				fail("Error al pintar el resultado");
			}
		}
	}
}
