package BibliotecaQA;

import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junitparams.Parameters;

public class Login {
	
	private WebDriver driver;

	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Instancia el web driver en la variable driver para utilizarlo en
	 * toda la clase. Se maximiza en chrome y carga la url de la bliblioteca
	 */
	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://bibliotecaqa.s3-website-us-east-1.amazonaws.com/");
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Instancia el web driver en la variable driver para utilizarlo en
	 * toda la clase. Se maximiza en chrome y carga la url de la bliblioteca
	 */
	public void opcionLogin() {
		WebElement menuLogin = driver.findElement(By.xpath("//*[@id=\"navbarText\"]/ul/li[2]/a"));
		menuLogin.click();
				
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Este método, recibe como parámetros la data que retorna el
	 * método dataExitoso, con el fin de ejecutar el flujo con esos datos y probar
	 * su respuesta para validar si realmente se obtuvo la respuesta esperada para
	 * los casos positivos.
	 */
	@Test
	@Parameters(method = "dataExitoso")
	public void loginExitoso( String corr, String cont) {
		
		opcionLogin();	
		ingresoLogin(corr, cont);

		String respuesta = driver.findElement(By.xpath("//*[@id=\"msg\"]/div")).getText();
		System.out.println("respuesta: " + respuesta);

		Assert.assertThat(respuesta, StringContains.containsString("Bienvenido"));
		driver.quit();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que prepara y retorna los datos según cada caso de prueba
	 * positivo planteado para el escenario de Inicio de sesión. Estos casos
	 * corresponden en donde el sistema debe permitir crear el registro. 
	 * Id Caso 1: Ingresar sesion con el último correo registrado exitosamente
	 * 
	 */
	private Object[] dataExitoso() {		
		return new Object[][] { { "c@c.com", "Prueba.123" } };
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Este método, recibe como parámetros la data que retorna el
	 * método dataExitoso, con el fin de ejecutar el flujo con esos datos y probar
	 * su respuesta para validar si realmente se obtuvo la respuesta esperada para
	 * los casos positivos.
	 */
	@Test(expected = NoSuchElementException.class)
	@Parameters(method = "dataNoExitoso")
	public void loginNoExitoso( String corr, String cont) {
				
		ingresoLogin(corr, cont);

		String respuesta = driver.findElement(By.xpath("//*[@id=\"msg\"]/div")).getText();
		System.out.println("respuesta: " + respuesta);

		driver.quit();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que prepara y retorna los datos según cada caso de prueba
	 * positivo planteado para el escenario de Inicio de sesión. Estos casos
	 * corresponden en donde el sistema debe permitir crear el registro. 
	 * Id Caso 1: Ingresar sesion con un correo no registrado.
	 * 
	 */
	private Object[] dataNoExitoso() {		
		return new Object[][] { { "z@a.com", "prueba123" } };
	}
	
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que identifica los componentes del formulario del
	 * login de usuario y realiza el flujo de diligenciamiento para iniciar sesión.
	 */
	public void ingresoLogin(String corr, String cont) {
		
		WebElement correo = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement contrasena = driver.findElement(By.cssSelector("input[id=password]"));
		WebElement btnIniciaSesion = driver.findElement(By.id("login"));
		
		correo.sendKeys(corr);
		contrasena.sendKeys(cont);
		btnIniciaSesion.click();
	}
	
	
	
	@After
  	public void tearDown(){
         	System.out.println("----We're now closing our test----");
         	driver.quit();
   	}

}
