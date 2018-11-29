package co.com.google.pages;

//Importamos el controlador para utilizar el navegador internet explorer
import org.openqa.selenium.ie.InternetExplorerDriver;
//Importamos el controlador para utilizar el navegador firefox
import org.openqa.selenium.firefox.FirefoxDriver;
//Importamos el controlador para utilizar el navegador chrome
import org.openqa.selenium.chrome.ChromeDriver;
//Importamos la clase que nos permite "navegar" por el navegador
import org.openqa.selenium.WebDriver;
//Importamos la clase que nos permite manipular elementos de la web
import org.openqa.selenium.WebElement;

//Importamos la clase que nos permite arrojar la excepción
import java.awt.AWTException;
//Importamos la clase que nos permite manejar dimensiones (ej: la resolucion de la pantalla)
import java.awt.Dimension;
//Importamos la clase  que nos permite manejar rectangulos, en este caso
//La usaremos para crear un rectangulo del tamaño de la pantalla para poder tomar la foto
import java.awt.Rectangle;
//Importamos la clase que nos permite manejar un robot el cual podemos usar
//para generar eventos automaticos (ej: mover el raton, clikear en algun sitio, pulsar una tecla, etc)
import java.awt.Robot;
//Importamos la clase que nos permite obtener una caja de herramientas con la cual
//podemos obtener acceso a diferentes opciones que nos permiten manejar la pantalla
import java.awt.Toolkit;
//Importamos la clase que nos permite manejar imagenes temporales que como su nombre
//lo indica, se montan en el buffer (que es como un carrito al que le agregas cosas y
//obviamente tiene su limite)
import java.awt.image.BufferedImage;
//Importamos la libreria que nos permite manipular archivos
import java.io.File;
//Importamos la clase que nos permite manejar exceptiones de entrada y salida.
import java.io.IOException;
//Importamos la clase que nos ayudara a iterar sobre colecciones
import java.util.Iterator;
//Importamos la clase que nos permite crear y manipular una coleccion,
//en este caso, set es una coleccion sin elementos repetidos
import java.util.Set;
//Importamos la clase que nos permite manipular imagenes
import javax.imageio.ImageIO;

//Importamos el localizador
import org.openqa.selenium.By;
//Importamos la clase que nos permite decidir el tipo de archivo que se obtendra
//import org.openqa.selenium.OutputType;
//Importamos la clase que nos permite tomar la captura de pantalla del navegador
//import org.openqa.selenium.TakesScreenshot;
//Importamos la clase que nos permite mover archivos
//import com.google.common.io.Files;
//Importamos la clase que nos permite fijar unidades de tiempo (ej: nanosegundos, milis, etc)
import java.util.concurrent.TimeUnit;


public class BuscarEnBancolombiaPOM {
	
	// Llave para internet explorer
	private static final String INTERNET_EXPLORER_KEY = "webdriver.ie.driver";
	// Llave para firefox
	private static final String FIREFOX_KEY = "webdriver.gecko.driver";
	// Llave para chrome
	private static final String CHROME_KEY = "webdriver.chrome.driver";
	
	// Ruta de acceso al driver de internet explorer
	private static final String DIRECCION_COMPLEMENTO_INTERNET_EXPLORER = 
			"S:\\Mis_Proyectos\\Java\\Eclipse AutoTesting\\"
			+ "Complementos\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe";
	// Ruta de acceso al driver de firefox	
	private static final String DIRECCION_COMPLEMENTO_FIREFOX = 
			"S:\\Mis_Proyectos\\Java\\Eclipse AutoTesting\\Complementos"
			+ "\\geckodriver-v0.23.0-win32\\geckodriver.exe";
	// Ruta de acceso al driver de chrome
	private static final String DIRECCION_COMPLEMENTO_CHROME = 
			"D:\\Proyectos\\Complementos\\chromedriver_win32\\chromedriver.exe";
	// xpath para la barra de busqueda
	private final String XPATH_BARRA_DE_BUSQUEDA = "//*[@id=\"terminoBusqueda2\"]";
	// xpath para el boton de busqueda
	private final String XPATH_BOTON_DE_BUSQUEDA = "//*[@id=\"btn_search\"]";
	// xpath para el primer resultado de la busqueda
	private final String XPATH_PRIMER_RESULTADO_DE_BUSQUEDA = 
			"//*[@id=\"main-content\"]/div[1]/div/div/div[2]/table[1]/tbody/tr[1]/td/a";
	// xpath para el div de la barra de busqueda
	private final String XPATH_DIV_BARRA_DE_BUSQUEDA =
			"//*[@id=\"barra-navegacion\"]"; 
	
	// Declaramos aqui la referencia de nuestro navegador 
	private WebDriver navegador;
	// Declaramos aqui los componentes de la pagina web
	// Barra de busqueda principal
	private By barraDeBusqueda = By.xpath(XPATH_BARRA_DE_BUSQUEDA);
	// Boton para buscar
	private By botonDeBusqueda = By.xpath(XPATH_BOTON_DE_BUSQUEDA);	
	// Primer resultado de la busqueda
	private By primerResultadoDeBusqueda = By.xpath(XPATH_PRIMER_RESULTADO_DE_BUSQUEDA);
	// Div de la barra de busqueda
	private By divBarraDeBusqueda = By.xpath(XPATH_DIV_BARRA_DE_BUSQUEDA);
	/**
	 * Método estatico que especifica la ubicacion para internet explorer.
	 * @return La nueva instancia.
	 */
	public static BuscarEnBancolombiaPOM crearInstanciaParaIE() {
		// El controlador para internet explorer se encuentra
		// en la dirección especificada
		System.setProperty(INTERNET_EXPLORER_KEY,
				DIRECCION_COMPLEMENTO_INTERNET_EXPLORER);
		return new BuscarEnBancolombiaPOM();
	}
	
	/**
	 * Método estatico que especifica la ubicacion para mozilla firefox.
	 * @return La nueva instancia.
	 */
	public static BuscarEnBancolombiaPOM crearInstanciaParaFirefox() {
		// El controlador para mozilla firefox se encuentra
		// en la dirección especificada
		System.setProperty(FIREFOX_KEY,
				DIRECCION_COMPLEMENTO_FIREFOX);
		return new BuscarEnBancolombiaPOM();
	}
	
	/**
	 * Método estatico que especifica la ubicacion para google chrome.
	 * @return La nueva instancia.
	 */
	public static BuscarEnBancolombiaPOM crearInstanciaParaChrome() {
		// El controlador para google chrome se encuentra en la dirección especificada
		System.setProperty(CHROME_KEY,
				DIRECCION_COMPLEMENTO_CHROME);
		return new BuscarEnBancolombiaPOM();
	}
	
	/**
	 * Constructor de la clase.
	 * Utilizado para crear de una nueva instancia
	 * @see BancolombiaPOM
	 */
	private BuscarEnBancolombiaPOM() {}
	
	/**
	 * El método crea una nueva instancia, al hacerlo abrimos el navegador,
	 * este queda automaticamente asignado a la variable navegador.
	 * @see BancolombiaPOM
	 */
	public void abrirNavegadorUtilizandoInternetExplorer() {
		// Le asignamos a nuestra referencia una nueva instancia de tipo
		// InternetExplorerDriver
		navegador = new InternetExplorerDriver();
	}
	
	/**
	 * El método crea una nueva instancia, al hacerlo abrimos el navegador,
	 * este queda automaticamente asignado a la variable navegador.
	 * @see BancolombiaPOM
	 */
	public void abrirNavegadorUtilizandoFirefox() {
		// Le asignamos a nuestra referencia una nueva instancia de tipo
		// InternetExplorerDriver
		navegador = new FirefoxDriver();
	}
	
	/**
	 * El método crea una nueva instancia, al hacerlo abrimos el navegador,
	 * este queda automaticamente asignado a la variable navegador.
	 * @see BancolombiaPOM
	 */
	public void abrirNavegadorUtilizandoChrome() {
		// Le asignamos a nuestra referencia una nueva instancia de tipo
		// InternetExplorerDriver
		navegador = new ChromeDriver();
	}
	
	/**
	 * Método utilizado para navegar hacia una pagina web
	 * @param paginaWeb es la url o direccion a especificar
	 * @see BuscarEnGooglePOM
	 */
	public void navegarHaciaPaginaWeb(String paginaWeb) {
		// Obtenemos la pagina web deseada
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		navegador.get(paginaWeb);
	}
	
	/**
	 * Método utilizado para cerrar el navegador al momento de terminar nuestro test
	 * @see BancolombiaPOM
	 */
	public void cerrarNavegador() {
		// Cerramos el navegador
		navegador.close();
	}
	
	/**
	 * Método utilizado para buscar un elemento en la pagina web actual por su id.
	 * @param id del elemento.
	 * @return El elemento web encontado.
	 * @see BancolombiaPOM
	 */
	public WebElement buscarElementoPorID(String id) {
		// Retornamos el elemento web encontrado por id
		return navegador.findElement(By.id(id));
	}
	
	/**
	 * Método utilizado para buscar un elemento en la pagina web actual por su nombre.
	 * @param name del elemento.
	 * @return El elemento web encontrado.
	 * @see BancolombiaPOM
	 */
	public WebElement buscarElementoPorNombre(String name) {
		// Retornamos el elemento web encontrado por nombre
		return navegador.findElement(By.name(name));
	}
	
	/**
	 * Método utilizado para buscar un elemento en la pagina web actual por su nombre.
	 * @param name del elemento.
	 * @return El elemento web encontrado.
	 * @see BancolombiaPOM
	 */
	public WebElement buscarElementoPorLocalizador(By elemento) {
		// Retornamos el elemento web encontrado por nombre
		return navegador.findElement(elemento);
	}
	
	/**
	 * Método utilizado para devolver el titulo de la pagina actual.
	 * @return El titulo encontrado.
	 */
	public String obtenerTituloDeLaPaginaWeb() {
		return navegador.getTitle();
	}
	
	/**
	 * Método utilizado para escribir un texto sobre un elemento web.
	 * @param elemento web donde vamos a escribir.
	 * @param texto a escribir en el elemento.
	 * @see BancolombiaPOM
	 */
	public void escribirTexto(WebElement elemento, String texto) {
		// Escribimos sobre el elemento el texto asignado.
		elemento.sendKeys(texto);
	}
	
	/**
	 * Método utilizado para pulsar una tecla especial sobre un elemento web.
	 * @param elemento web donde vamos a pulsar la tecla.
	 * @param tecla a pulsar en el elemento
	 * @see BancolombiaPOM
	 */
	public void presionarTeclaEspecial(WebElement elemento, String tecla) {
		// Pulsamos la tecla especificada sobre el elemento.
		elemento.sendKeys(tecla);
	}
	
	/**
	 * Método utilizado para clickear sobre un elemento web.
	 * @param elemento web donde se realizará la acción.
	 * @see BancolombiaPOM
	 */
	public void clickearSobreElemento(WebElement elemento) {
		// Clikeamos sobre el elemento asignado.
		elemento.click();
	}
	
	/**
	 * Método utilizado para obtener elemento.
	 * @return El elemento web localizado.
	 * @see BancolombiaPOM
	 */
	public By obtenerBarraDeBusqueda() {
		return barraDeBusqueda;
	}
	
	/**
	 * Método utilizado para obtener elemento.
	 * @return El elemento web localizado.
	 * @see BancolombiaPOM
	 */
	public By obtenerBotonDeBusqueda() {
		return botonDeBusqueda;
	}
	
	/**
	 * Método utilizado para obtener elemento.
	 * @return El elemento web localizado.
	 * @see BancolombiaPOM
	 */
	public By obtenerPrimerResultadoDeBusqueda() {
		return primerResultadoDeBusqueda;
	}
	
	/**
	 * Método utilizado para obtener elemento.
	 * @return El elemento web localizado.
	 * @see BancolombiaPOM
	 */
	public By obtenerDivBarraDeBusqueda() {
		return divBarraDeBusqueda;
	}
	
	/**
	 * Método para cambiar de una ventana a otra.
	 * @param indice de la ventana a la que queremos pasar
	 * (ej: indice=1 => pagina principal, indice=2 => pagina secundaria).
	 */
	public void cambiarAVentana(int indice) {
		// Iniciamos la variable con un valor nulo
		String idDeVentana = null;
		// Luego creamos una nueva referencia a la coleccion de ventanas
		// que maneja nuestro driver ó navegador
		Set<String> coleccionDeIdsDeVentanas = navegador.getWindowHandles();
		// Tambien creamos un iterador que nos ayuda a iterar sobre colecciones,
		// en este caso, nos ayudara con la que hemos creado anteriormente
		Iterator<String> iterador = coleccionDeIdsDeVentanas.iterator();
		// Iteramos desde 1 hasta el valor que nos asigne el indice
		for(int i = 1; i <= indice; i++) {
			// Asignamos la id de la ventana correspondiente cada vez que
			// hagamos una iteracion
			idDeVentana = iterador.next();
		}
		//Por ultimo hacemos el cambio de ventana
		navegador.switchTo().window(idDeVentana);
	}
	
	/**
	 * Método para maximizar la ventana
	 */
	public void maximizarPantalla() {
		navegador.manage().window().maximize();
	}
	
	/**
	 * Método para tomar una captura de pantalla 
	 */
	public void tomarFoto() {
		// Primera Forma:
		
		// Creamos una referencia para nuestro robot
		Robot robot = null;
		
		try {
			// Instanciamos nuestro robot y manejamos la excepcion en caso de no poder crearlo
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Si fue posible crear un robot, procedemos
		if(robot != null) {
			// Creamos una nueva referencia a una dimension y le pasamos la resolucion de nuestra pantalla
			Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
			// Creamos una referencia de una imagen temporal y le pasamos una captura de pantalla que tomamos con el robot,
			// utlilizando la resolucion de la pantalla obtenida previamente.
			BufferedImage foto = robot.createScreenCapture(new Rectangle(resolucion.width, resolucion.height));
			// Creamos el archivo de destino de la foto que tomamos y le asignamos la instancia
			// a nuestra referencia.
			File archivoDestino = new File("foto.png");
			
			try {
				// Tratamos de escribir la foto que tomamos a nuestro archivo de destino
				ImageIO.write(foto, "png", archivoDestino);
			} catch (IOException e) {
				// Si no fue posible, que escriba que ocurrio:
				e.printStackTrace();
				System.out.println("No fue posible tomar la foto.");
			}
		}
		
		
		
		// Segunda forma:
		
		/*
		// Tomamos una foto del navegador y se la asignamos como un archivo sin destino
		// a nuestra referencia
		File src = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
		// Creamos un archivo con destino y lo asignamos a otra referencia
		File destino = new File("foto2.png");
		
		try {
			// Luego lo que hacemos es tratar de "copiar" el archivo que contiene la captura de pantalla
			// al archivo que creamos con destino y listo.
			Files.move(src, destino);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		*/
		
	}
}

