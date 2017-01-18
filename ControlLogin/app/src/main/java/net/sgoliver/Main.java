package net.sgoliver;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity 
{
	private ControlLogin ctlLogin;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ctlLogin = (ControlLogin)findViewById(R.id.CtlLogin);
        
        ctlLogin.setOnLoginListener(new OnLoginListener() 
        {
			@Override
			public void onLogin(String usuario, String password) 
			{
				//Validamos el usuario y la contraseña
				if (usuario.equals("demo") && password.equals("demo"))
					ctlLogin.setMensaje("Login correcto!");
				else
					ctlLogin.setMensaje("Vuelva a intentarlo.");
			}
		});
    }
}