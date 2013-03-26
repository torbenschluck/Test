
//import of classes
import Controller.Controller;
import Model.*;
import View.*;

public class Application 
{
	//login view and model
	private LoginView loginview;
	private LoginModel loginmodel;

	//main view and model
	private MainView mainview;
	private MainModel mainmodel;
	//controller
	private Controller controller;

		
    public Application()
    {
    	    	
    	loginmodel = new LoginModel();
    	loginview = new LoginView();
    	mainmodel= new MainModel();
    	mainview= new MainView();
    	controller = new Controller(loginview, loginmodel,mainview,mainmodel);
    	loginview.validate();
    	loginview.setVisible(true);
    	
    	mainview.validate();
	    mainview.setVisible(false);		   	    	
    }
    
	public static void main(String[] args) 
	{
		Application application= new Application();		
	}

}
