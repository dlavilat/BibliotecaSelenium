package BibliotecaQA;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CreacionUsuario extends Autenticacion{
	
	
	public CreacionUsuario(){
		super();
	}
	
	@Before
	public void configurar() {
		inicializar();
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
	@Test
	@Parameters(method = "dataNoExitoso")
	public void creacionNoExitoso(String nom, String apel, String corr, String tel, String cont) {
		try {
			seleccionarOpcionRegistro();
			llenarFormularioRegistro(nom, apel, corr, tel, cont);
			Boolean isDisplayed = driver.findElement(By.cssSelector(".alert-warning")).isDisplayed();
			Assert.assertTrue(isDisplayed);
		}catch(Exception e) {
			Assert.fail();
		}
		
		driver.close();
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
	 * Id Caso 9: Registrar un usuario sin diligenciar los campos
	 * obligatorios
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
								{ "Marcela", "", "", "", "" }, 
								{ "", "", "", "", "prueba123" }};
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
	public void creacionExitoso(String nom, String apel, String corr, String tel, String cont) {
		seleccionarOpcionRegistro();
		llenarFormularioRegistro(nom, apel, corr, tel, cont);
		Boolean isDisplayed = driver.findElement(By.cssSelector(".alert-success")).isDisplayed();
		Assert.assertTrue(isDisplayed);
		driver.close();
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
	 * 
	 */
	private Object[] dataExitoso() {
		return new Object[][] { { "Diana", "Avila", "c@c.com", "2222222222", "Prueba.123" }};
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
