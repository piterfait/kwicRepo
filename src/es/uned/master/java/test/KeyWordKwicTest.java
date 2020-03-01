/**
 * 
 */
package es.uned.master.java.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.uned.master.java.POJO.CadenaKwic;
import es.uned.master.java.POJO.KeyWordKwic;
import es.uned.master.java.POJO.KwicException;
//import es.uned.master.java.test.CadenaKwicTest.subCadenaKwic;

/**
 * @author piterfait
 *
 */
class KeyWordKwicTest {
	protected String cadenaNoCoincide;
	protected String cadenaCoincide;
	protected es.uned.master.java.POJO.KeyWordKwic keywordKwic;

	@BeforeEach
	void setUp() throws Exception {
		// Inicializo los objetos queheredan de CadenaKwic para poder evaluar las variables internas
		cadenaNoCoincide = "No coincide";
		cadenaCoincide = "Coincide";
	}

	@org.junit.jupiter.api.Nested

	@DisplayName("Comparación")
	class Comparacion {

		@Test

		@DisplayName("La comparación entre distintos funciona")
		void comparadorEntreDistintos() {
			assertFalse(new KeyWordKwic(cadenaCoincide).equals(new KeyWordKwic(cadenaNoCoincide)));
		}

		@Test

		@DisplayName("La comparación entre iguales funciona")
		void comparadorEntreIguales() {
			assertTrue(new KeyWordKwic(cadenaCoincide).equals(new KeyWordKwic(cadenaCoincide)));
		}
	}

	@org.junit.jupiter.api.Nested

	@DisplayName("Constructor")
	class Constructor {

		@Test

		@DisplayName("El constructor funciona")
		void constructor() {
			try {
				keywordKwic = new es.uned.master.java.POJO.KeyWordKwic(cadenaCoincide);
				assertTrue(keywordKwic != null);
			} catch (Exception e) {
				fail("El constructor no funciona");
			}
		}

		@Test

		@DisplayName("El constructor con parametro null está controlado")
		void constructorParametroNull() {
			try {
				keywordKwic = new es.uned.master.java.POJO.KeyWordKwic(null);
			} catch (Exception e) {
				assertTrue(e instanceof KwicException);
			}
		}

		@Test

		@DisplayName("El constructor crea instancias de KeyWordKwic")
		void constructorInstancia() {
			assertTrue(new KeyWordKwic(cadenaCoincide) instanceof KeyWordKwic);
		}

		@Test

		@DisplayName("El constructor convierte la variable tk a UpperCase")
		void constructorUppercase() {
			KeyWordKwic KeyWordKwic = new KeyWordKwic(cadenaCoincide);
			boolean uppercases = true;
			for (char c : KeyWordKwic.getTk().toCharArray()) {
				if (Character.isLetter(c) && !Character.isUpperCase(c)) {
					uppercases = false;
					break;
				}
			}
			assertTrue(uppercases);
		}
	}

	@org.junit.jupiter.api.Nested

	@DisplayName("ToString()")
	class CadenaKwicTests {

		@Test

		@DisplayName("ToString() En Uppercase")
		void ToStringEnUppercase() {
			boolean uppercases = true;
			for (char c : new KeyWordKwic(cadenaCoincide).toString().toCharArray()) {
				if (Character.isLetter(c) && !Character.isUpperCase(c)) {
					uppercases = false;
					break;
				}
			}
			assertTrue(uppercases);
		}
	}

	@org.junit.jupiter.api.Nested

	@DisplayName("Método reemplaza")
	class Reemplaza {

		@Test

		@DisplayName("Reemplaza funciona correctamente")
		void ReemplazaFunciona() {
			KeyWordKwic keykwic = new KeyWordKwic(cadenaCoincide);
			assertEquals("... ", keykwic.reemplaza(cadenaCoincide));
		}
	}

}
