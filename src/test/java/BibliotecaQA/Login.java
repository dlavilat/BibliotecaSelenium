package BibliotecaQA;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class Login extends Autenticacion {
	
	
	public Login(){
		super();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Se llama al método padre que construye el ChromeDriver y
	 * accede a la url de la biblioteca.
	 */
	@Before
	public void configurar() {
		inicializar();
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
	 * Se realiza el flujo completo desde el registro hasta el login.
	 */
	@Test
	@Parameters(method = "dataExitoso")
	public void loginExitoso( String corr, String cont) {
		seleccionarOpcionRegistro();
		llenarFormularioRegistro("Diana", "Avila", corr, "2222222222", cont);
		seleccionarOpcionLogin();	
		llenarFormularioLogin(corr, cont);
		Boolean isDisplayed = driver.findElement(By.cssSelector(".alert-success")).isDisplayed();
		
		Assert.assertTrue(isDisplayed);
		driver.close();
	}
	
	
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Este método, recibe como parámetros la data que retorna el
	 * método dataNoExitoso, con el fin de ejecutar el flujo con esos datos y probar
	 * su respuesta para validar si realmente se obtuvo la respuesta esperada para
	 * los casos positivos.
	 */
	@Test
	@Parameters(method = "dataNoExitoso")
	public void loginNoExitoso( String corr, String cont) {
		seleccionarOpcionLogin();
		llenarFormularioLogin(corr, cont);
		Boolean isDisplayed = driver.findElement(By.cssSelector(".alert-warning")).isDisplayed();
		
		Assert.assertTrue(isDisplayed);
		driver.close();
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
	public void llenarFormularioLogin(String corr, String cont) {
		WebElement correo = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement contrasena = driver.findElement(By.cssSelector("input[id=password]"));
		WebElement btnIniciaSesion = driver.findElement(By.id("login"));
		
		correo.sendKeys(corr);
		contrasena.sendKeys(cont);
		
		btnIniciaSesion.click();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Cierra el proceso del webdriver
	 */
	@After
  	public void tearDown(){
         	System.out.println("----We're now closing our test----");
         	driver.quit();
   	}
}
