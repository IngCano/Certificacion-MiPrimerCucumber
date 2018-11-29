package co.com.google.runners;

//Importamos la etiqueta que nos especifica que clase debe ejecutar el runner
import org.junit.runner.RunWith;
//Importamos la etiqueta que nos especifica las opciones del runner, en este caso cucumber
import cucumber.api.CucumberOptions;
//Importamos la clase que se ejecutara con el @runWith
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		//Especificamos que feature queremos que se ejecute
		features = "features/BuscarEnGoogle.Feature",
		//Especificamos cuál o cuáles stepDefinitions se utilizaran para la ejecucion
		glue = "co.com.google.stepDefinitions.BuscarEnGoogleStepDefinition.java",
		//Especificamos el tag que se ejecutará
		tags = "@tag1")
public class BuscarEnGoogleRunner {
	
}
