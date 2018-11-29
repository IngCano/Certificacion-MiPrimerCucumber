package co.com.google.stepDefinitions;

// Importamos la etiqueta de cucumber @Given
import cucumber.api.java.en.Given;
// Importamos la etiqueta de cucumber @When
import cucumber.api.java.en.When;
// Importamos la etiqueta de cucumber @Then
import cucumber.api.java.en.Then;
// Importamos el metodo estatico assertThat() el cual
// funciona como verificador de una afirmacion
import static org.junit.Assert.assertThat;

// Importamos unicamente el metodo is de hamcrest que nos ayuda
// a hacer mas legible el codigo en el verificador
import static org.hamcrest.CoreMatchers.is;
// Importamos las teclas especiales de selenium
import org.openqa.selenium.Keys;
// Importamos nuestro POM
import co.com.google.pages.BuscarEnGooglePOM;

public class BuscarEnGoogleStepDefinition {
	// Declaramos nuestra referencia y le asignamos una instancia
	// lista para utilizar el navegador chrome
	BuscarEnGooglePOM navegadorWebGoogle = 
			BuscarEnGooglePOM.crearInstanciaParaChrome();
	
	@Given("que abro el navegador")
	public void que_puedo_abrir_el_navegador() {
		// Abrimos el navegador
		navegadorWebGoogle.abrirNavegadorUtilizandoChrome();
	}

	@When("voy a google")
	public void cuando_voy_a_google() {
		// Navegamos hacia la pagina web indicada:
	    navegadorWebGoogle.navegarHaciaPaginaWeb("http://www.google.com/");
	}

	@When("realizo una busqueda")
	public void realizo_una_busqueda() {
		// Escribimos sobre el elemento web indicado el texto: "Java"
	    navegadorWebGoogle.escribirTexto(
	    		navegadorWebGoogle.buscarElementoPorLocalizador(
	    		navegadorWebGoogle.obtenerBarraDeBusqueda()), "Java");
	    // Buscamos el boton y presionamos sobre el la tecla especial: "Retorno"
	    navegadorWebGoogle.presionarTeclaEspecial(
	    		navegadorWebGoogle.buscarElementoPorLocalizador(
	    		navegadorWebGoogle.obtenerBotonDeBusqueda()), Keys.RETURN+"");
	}

	@Then("valido que el resultado sea el esperado")
	public void valido_que_el_resultado_sea_el_esperado() {
		// Verificamos que el título de la página web sea el indicado
	    assertThat(navegadorWebGoogle.obtenerTituloDeLaPaginaWeb(),
	    		is("Java - Buscar con Google"));
	}
	
	@Then("cierro el navegador")
	public void cierro_el_navegador() {
		// Para finalizar, cerramos el navegador.
		navegadorWebGoogle.cerrarNavegador();
	}

}
