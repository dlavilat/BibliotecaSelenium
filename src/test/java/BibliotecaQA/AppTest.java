package BibliotecaQA;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
	public static void main(String[] agrs) {
		
		Result res = JUnitCore.runClasses(RegistroUsuario.class);
		Result resultLogin = JUnitCore.runClasses(Login.class);
		
     	for(Failure fail:res.getFailures()){
     	  	System.out.println("Falla Registro: "+fail.toString());
     	}
      	  	System.out.println("Registro: "+res.wasSuccessful());
      	  	
      	  for(Failure fail:resultLogin.getFailures()){
       	  	System.out.println("Falla Login: "+fail.toString());
       	}
        	  	System.out.println("Login: "+resultLogin.wasSuccessful());
		
	}
	
}
