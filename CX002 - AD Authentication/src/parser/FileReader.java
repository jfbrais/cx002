package parser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JList;

/**
 * This program reads a text file line by line and print to the console. It uses
 * FileOutputStream to read the file.
 * 
 */
public class FileReader  
{
	public File file;
	public FileInputStream fis = null;
	public BufferedInputStream bis = null;
	public DataInputStream dis = null;
    
	public JList list;
    
	private String[][] listData;
	private String[] data;
	private String line;
	public int i = 1;

    public FileReader()
    {
    	System.out.println("FileReader - Constructor");
    }
    
	public String[][] parseLog(String cFile)
	{
		file = new File(cFile);
		
		listData = new String[getFileLength(file) + 1][LogParser.COLONNES];
		
		listData[0][0] = 	"ID";
		listData[0][1] = 	"Month";
		listData[0][2] = 	"Date";
		listData[0][3] = 	"Time";
		listData[0][4] = 	"Year";
		listData[0][5] = 	"Action";
		listData[0][6] = 	"Source IP";
		listData[0][7] = 	"Dest. IP";
		listData[0][8] = 	"Protocol";
		listData[0][9] = 	"Source Port";
		listData[0][10] = 	"Dest. Port";
		
		try
	    {	    	
	    	fis = new FileInputStream(file);
	
	    	// Here BufferedInputStream is added for fast reading.
	    	bis = new BufferedInputStream(fis);
	    	dis = new DataInputStream(bis);
	    	
	    	// dis.available() returns 0 if the file does not have more lines.
	    	while (dis.available() != 0) 
	    	{  	
	    		line = dis.readLine();
		    	data = line.split(" ");
		    	listData[i][0] = Integer.toString(i);
		    	
	    		for (int j = 1; j < data.length; j++)
	    		{
	    			listData[i][j] = data[j - 1];
	    			
	    			System.out.print(data[j - 1] + "\t");
//	    			System.out.print(listData[i][j] + "\t");
	    		}
	    		
	    		System.out.println("");
	    		i++;
	    	}	
	    	
			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
	      e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	      e.printStackTrace();
	    }
	    
	    return listData;
	}
	
	private int getFileLength(File file)
	{
		int length = 0;
		
		try
	    {	    	
	    	fis = new FileInputStream(file);
	    	bis = new BufferedInputStream(fis);
	    	dis = new DataInputStream(bis);
	    	
	    	// dis.available() returns 0 if the file does not have more lines.
	    	while (dis.available() != 0) 
	    	{  	
	    		dis.readLine();
	    		length++;
	    	}	

			fis.close();
			bis.close();
			dis.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
	      e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	      e.printStackTrace();
	    }
		
		return length;
	}
	
	public String getListData(int i, int j)
	{
		return listData[i][j];
	}
}
