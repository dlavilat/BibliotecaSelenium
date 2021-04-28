package BibliotecaQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Autenticacion {
	
	protected WebDriver driver;
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Configura los elementos necesarios para realizar la prueba
	 */
	public void inicializar() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://bibliotecaqa.s3-website-us-east-1.amazonaws.com/");
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Realiza la navegacion a la pagina de login
	 */
	public void seleccionarOpcionLogin() {
		WebElement menu = driver.findElement(By.xpath("//*[@id=\"navbarText\"]/ul/li[2]/a"));
		menu.click();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Realiza la navegacion a la pagina de registro 
	 */
	public void seleccionarOpcionRegistro() {
		WebElement menu = driver.findElement(By.xpath("//*[@id=\"navbarText\"]/ul/li[3]/a"));
		menu.click();
	}
	
	/*
	 * Developer: Diana Lucia Avila 
	 * Date: April-26-2021 
	 * Email: diluavila@gmail.com
	 * Description: Método que identifica los componentes del formulario del
	 * registro de usuario y realiza el flujo de diligenciamiento hasta enviar los
	 * datos.
	 */
	public void llenarFormularioRegistro(String nom, String apel, String corr, String tel, String cont) {
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
}
