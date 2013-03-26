package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginView extends JFrame 
{

	private JPanel login = new JPanel();
	
	private JLabel username = new JLabel("Username:");
	private JLabel password = new JLabel("Password:");
	private JLabel welcome = new JLabel("RefMan");
	
	private JTextField usernametextfield = new JTextField("artem");
	private JPasswordField passwordtextfield = new JPasswordField();
	
	private JButton loginbutton = new JButton("Login");
	private JButton registerbutton = new JButton("New User");
	
	public LoginView()
	{
		init();
	}

	private void  init()
	{
		this.setSize(300,200);
		this.setTitle("Login");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
//		login.add(welcome);
//		this.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
//		welcome.setAlignmentY(CENTER_ALIGNMENT);
		
		login.setLayout(null);
		login.add(username);
		username.setBounds(10,12,80,10);
		login.add(usernametextfield);
		usernametextfield.setBounds(85,10,150,20);
		login.add(password);
		password.setBounds(10,55,100,10);
		login.add(passwordtextfield);
		passwordtextfield.setBounds(85,50,150,20);
		login.add(loginbutton);
		loginbutton.setBounds(40,100,80,25);
		login.add(registerbutton);
		registerbutton.setBounds(145,100,100,25);
		registerbutton.setVisible(false);
		
		welcome.setFont(new Font("Arial", Font.BOLD, 15));
		welcome.setBounds(10, 20, 100, 50);
		welcome.setLocation(300/2-100/2, 200/2-50/2);

		this.setContentPane(login);
	}

	public boolean canI()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//getter
	public String getUsername(){
		return usernametextfield.getText();
	}
	
	public char[] getPassword(){
		return passwordtextfield.getPassword();
	}
	
	//setter
	public void setUsername(String userLastname) 
	{	
		usernametextfield.setText(userLastname);
	}

	//listener for buttons etc.
	public void addLoginListener(ActionListener loginactionlistener)
	{
		loginbutton.addActionListener(loginactionlistener);
	}
	
	public void addRegisterListner(ActionListener registeractionlistener)
	{
		registerbutton.addActionListener(registeractionlistener);
	}

	public void showError(String errormessage) 
	{		
		JOptionPane.showMessageDialog(this, errormessage);
	}
	public void closeLoginView(boolean valid)
	{		
		if(valid==true)
		{
			this.setVisible(false);
		}
			
	}
}
