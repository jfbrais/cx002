package auth;

import java.io.File;
import java.io.IOException;

public class FileMaker 
{
	public FileMaker()
	{
		
	}
	
	public void createFile(String sDir)
	{
		try 
		{
		    File file = new File(sDir);

		    // Create file if it does not exist
		    boolean success = file.createNewFile();
		    
		    if (success) 
		    {
		    	System.out.println("File did not exist and was created");
		    } 
		    else 
		    {
		        System.out.println("File already exists");
		    }
		} 
		catch (IOException e) 
		{
			System.out.println("TEH FAIL.");
			e.printStackTrace();
		}
	}
}
