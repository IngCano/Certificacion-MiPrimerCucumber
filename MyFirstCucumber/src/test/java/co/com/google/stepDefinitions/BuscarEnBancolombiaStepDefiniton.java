package co.com.google.stepDefinitions;

//Importamos la etiqueta de cucumber @Given
import cucumber.api.java.en.Given;
//Importamos la etiqueta de cucumber @When
import cucumber.api.java.en.When;
//Importamos la etiqueta de cucumber @Then
import cucumber.api.java.en.Then;
//Importamos el metodo estatico assertThat() el cual
//funciona como verificador de una afirmacion
import static org.junit.Assert.assertThat;

//Importamos unicamente el metodo is de hamcrest que nos ayuda
//a hacer mas legible el codigo en el verificador
import static org.hamcrest.CoreMatchers.is;
//Importamos las teclas especiales de selenium
import org.openqa.selenium.Keys;
//Importamos nuestro POM
import co.com.google.pages.BuscarEnBancolombiaPOM;

public class BuscarEnBancolombiaStepDefiniton {
	
	// Según la convención, es importante declarar todas las cadenas que se utilizen
	// en el codigo en la parte superior, y llamarlas como variables finales.
	// En este caso, declaramos el portal web que se utilizara para la prueba
	private final String PORTAL_WEB =
			"https://www.grupobancolombia.com/wps/portal/personas";
	// Declaramos nuestra referencia y le asignamos una instancia
		// lista para utilizar el navegador chrome
	BuscarEnBancolombiaPOM navegadorWebBancolombia = 
			BuscarEnBancolombiaPOM.crearInstanciaParaChrome();
	
	@Given("que abro el navegador")
	public void que_abro_el_navegador() {
		// Abrimos el navegador
		navegadorWebBancolombia.abrirNavegadorUtilizandoChrome();
	}

	@When("voy al portal de bancolombia")
	public void voy_al_portal_de_bancolombia() {
		// Navegamos hacia la pagina web indicada:
		// Notar que el codigo se hace un poco mas legible
	    navegadorWebBancolombia.navegarHaciaPaginaWeb(PORTAL_WEB);
	}
	
	// Para la ejecucion, es necesario remover los "\\" de la etiqueta,
	// ya que dara error si se ejecuta de esta manera
	@When("busco \\{string\\}")
	public void busco(String string) {
		// Obtenemos el elemento web indicado y hacemos click sobre el 
		navegadorWebBancolombia.buscarElementoPorLocalizador(
				navegadorWebBancolombia.obtenerBotonDeBusqueda()).click();
		// Escribimos sobre el elemento web seleccionado el texto indicado
	    navegadorWebBancolombia.escribirTexto(
	    		navegadorWebBancolombia.buscarElementoPorLocalizador(
	    		navegadorWebBancolombia.obtenerBarraDeBusqueda()), string);
	    // Presionamos la tecla especial sobre el elemento web indicado
	    navegadorWebBancolombia.presionarTeclaEspecial(
	    		navegadorWebBancolombia.buscarElementoPorLocalizador(
	    		navegadorWebBancolombia.obtenerBarraDeBusqueda()), Keys.RETURN+"");
	}
	
	@When("ingreso en el primer resultado")
	public void ingreso_en_el_primer_resultado() {
		// Clickeamos sobre el elemeto web indicado
		navegadorWebBancolombia.buscarElementoPorLocalizador(
				navegadorWebBancolombia.obtenerPrimerResultadoDeBusqueda()).click();
	}

	@Then("valido que el resultado sea el esperado")
	public void valido_que_el_resultado_sea_el_esperado() {
		// Verificamos que el titulo de la pagina coincida con el titulo indicado
	    assertThat(navegadorWebBancolombia.obtenerTituloDeLaPaginaWeb(),
	    		is("Tarjeta Prepago"));
	}
	
	@Then("cierro el navegador")
	public void cierro_el_navegador() {
		// Por ultimo, cerramos el navegador.
		navegadorWebBancolombia.cerrarNavegador();
	}

}
