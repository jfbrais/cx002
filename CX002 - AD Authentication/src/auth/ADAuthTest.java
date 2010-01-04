package auth;

import java.util.Map;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

public class ADAuthTest 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ADAuth ada = new ADAuth("transax.net", "ldap://tx-pdc.transax.net", "dc=transax,dc=net");
		Map umap;
		
		final String username = JOptionPane.showInputDialog(
	    		null,
	    		"Username :",
	    		"Username",
	    		JOptionPane.QUESTION_MESSAGE);
		
		final String password = JOptionPane.showInputDialog(
	    		null,
	    		"Password :",
	    		"Password",
	    		JOptionPane.QUESTION_MESSAGE);
		
		try 
		{
			umap = ada.authenticate(username, password);
			
			if (umap == null)
				System.out.println("Login failed.");
			else 
			{
				System.out.println("Login suceeded.");
				// umap has three attributes: sn, givenName, mail
			}
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
}
