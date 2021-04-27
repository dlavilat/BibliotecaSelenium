package BibliotecaQA;

import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RegistroUsuario {

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
	 * Description: Este método, recibe como parámetros la data que retorna el
	 * método dataNoExitoso, con el fin de ejecutar el flujo con esos datos y probar
	 * su respuesta para validar si realmente se obtuvo la respuesta esperada para
	 * los casos negativos.
	 */
	@Test(expected = NoSuchElementException.class)
	@Parameters(method = "dataNoExitoso")
	public void registroNoExitoso(String nom, String apel, String corr, String tel, String cont) {

		WebElement menu = driver.findElement(By.xpath("//*[@id=\"navbarText\"]/ul/li[3]/a"));
		menu.click();

		formularioRegistro(nom, apel, corr, tel, cont);

		String respuesta = driver.findElement(By.xpath("//*[@id=\"msg\"]/div")).getText();

		driver.quit();
	}

	/*
	 * Developer: Diana Lucia Avila Date: April-26-2021 Email: diluavila@gmail.com
	 * Description: Método que prepara y retorna los datos según cada caso de prueba
	 * negativo planteado para el escenario de Registro de Usuario. Estos casos
	 * corresponden en donde el sistema no debe permitir crear el registro. 
	 * Id Caso-1: Registrar un usuario con un Nombre con mas de 10 caracteres y la
	 * Contraseña no cumple con la letra mayúscula, número y carácter especial. 
	 * Id Caso-2: Registrar un usuario con un Nombre con menos de 3 caracteres y la
	 * Contraseña no cumple con la letra mayúscula y carácter especial. 
	 * Id Caso-4: Registrar un usuario con un Apellido con Longitud menor a 3 caracteres y la
	 * Contraseña no cumple con la letra mayúscula y carácter especial. 
	 * Id Caso-5: Registrar un usuario con un Correo que ya está registrado en otro usuario y
	 * la Contraseña no cumple con la letra mayúscula y carácter especial. 
	 * Id Caso-6: Registrar un usuario con un Telefono con longitud menor a 10 numeros,
	 * Contraseña cumple con la letra mayúscula, el carácter especial y los números.
	 * Id Caso-7: Registrar un usuario con Telefono con caracteres alfanuméricos,
	 * Contraseña cumple con la letra mayúscula, el carácter especial y los números.
	 * Id Caso-8: Registrar un usuario con Telefono con un valor negativo,
	 * Contraseña no cumple con la letra mayúscula y carácter especial.
	 * Id Caso-10: Registrar un usuario sólo ingresando el campo Nombre
	 * 
	 */
	private Object[] dataNoExitoso() {
		
		return new Object[][] { { "maria del mar", "Arias", "a@a.com", "2222222222", "pruebamariadelmar" },
								{ "al", "Prueba3", "b@b.com", "2222222222", "prueba123al" },
								{ "Diana", "a", "d@d.com", "2222222222", "prueba123" },
								{ "Lucia", "Avila", "d@d.com", "2222222222", "prueba123" },
								{ "Lucia", "Avila", "e@e.com", "123456", "Prueba.123" },
								{ "Lucia", "Avila", "f@f.com", "pcr*.1", "Prueba.123" },
								{ "Lucia", "Avila", "g@g.com", "-4", "prueba123" }, 
								{ "Marcela", "", "", "", "" } };
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
	public void registroExitoso(String nom, String apel, String corr, String tel, String cont) {
		
		WebElement menu = driver.findElement(By.xpath("//*[@id=\"navbarText\"]/ul/li[3]/a"));
		menu.click();
		formularioRegistro(nom, apel, corr, tel, cont);

		String respuesta = driver.findElement(By.xpath("//*[@id=\"msg\"]/div")).getText();
		System.out.println("respuesta: " + respuesta);

		Assert.assertThat(respuesta, StringContains.containsString("El registro ha sido exitoso"));
		driver.quit();
	}

	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que prepara y retorna los datos según cada caso de prueba
	 * positivo planteado para el escenario de Registro de Usuario. Estos casos
	 * corresponden en donde el sistema debe permitir crear el registro. 
	 * Id Caso-3:Registrar un usuario con un Nombre con 5 caracteres y la Contraseña cumple
	 * con la letra mayúscula y carácter especial. Telefono de 10 numeros y correo
	 * válido. 
	 * Id Caso 9: Registrar un usuario sin diligenciar los campos
	 * obligatorios
	 * 
	 */
	private Object[] dataExitoso() {
		return new Object[][] { { "Diana", "Avila", "c@c.com", "2222222222", "Prueba.123" },
								{ "", "", "", "", "prueba123" } };
	}

	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que identifica los componentes del formulario del
	 * registro de usuario y realiza el flujo de diligenciamiento hasta enviar los
	 * datos.
	 */
	public void formularioRegistro(String nom, String apel, String corr, String tel, String cont) {

		WebElement nombre = driver.findElement(By.id("nombre"));
		WebElement apellidos = driver.findElement(By.id("apellidos"));
		WebElement correo = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement telefono = driver.findElement(By.xpath("//*[@id=\"telefono\"]"));
		WebElement contrasena = driver.findElement(By.cssSelector("input[id=password]"));
		WebElement btnRegistrate = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/a[1]"));

		nombre.sendKeys(nom);
		apellidos.sendKeys(apel);
		correo.sendKeys(corr);
		telefono.sendKeys(tel);
		contrasena.sendKeys(cont);

		btnRegistrate.click();

	}

	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que cierra el chromedriver
	 */
	@After
	public void tearDown() {
		System.out.println("----We're now closing our test----");
		driver.quit();
	}

}
