package Model;

public class LoginModel 
{
	
	private String userlastname;
	
	public boolean validateUser(String usernameinput) 
	{
		if(usernameinput.equals("artem"))
		{
			userlastname= "benzler";
			return true;
		}
		else
		{
			userlastname = "weiﬂ ich doch nicht";
			return false;
		}
	}

	public String getUserLastname() {
		return userlastname;
	}
	
	

}
