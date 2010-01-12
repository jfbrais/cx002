package parser;

import java.io.*;
import java.awt.*; 
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;  
import javax.swing.event.*;

public class LogParser
{
	public static int COLONNES = 12;
	
    public JFrame         jf;
    public JScrollPane 	scroll;
    public JList          list;
    public String[][]     columnData;
    
    public String temp = "C:\\traffic.log";
     
    public String file = (String) JOptionPane.showInputDialog(
    		null,
    		"Location of the log file :",
    		"Log file",
    		JOptionPane.QUESTION_MESSAGE,
    		null,
    		null,
    		(Object)temp);
     
    public FileReader fr = new FileReader();
     
     public LogParser()
     {
          //create the frame and JList JPanel
          jf = new aFrame();
          //create element List array
          addElements();
          //set list for JList
          list.setListData(columnData);
          //create Renderer and display
          list.setCellRenderer(new MyCellRenderer());
     }
     
     public void addElements()
     {
         columnData = fr.parseLog(file);
     }
     
	public class aFrame extends JFrame
	{
		public aFrame()
		{
			super("Multi-Column JList Example");
			getContentPane().add(new PanelBuilder());
			 
			// display rules
			setResizable(true);
			setLocation(0, 0);
			setBackground(Color.lightGray);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1000, 800);
			setVisible(true);
			
			this.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					e.getWindow().dispose();
		        }
			});
        }
          
		private class PanelBuilder extends JPanel
		{
			public PanelBuilder()
			{
			    GridBagLayout layout = new GridBagLayout(); 
			    GridBagConstraints layoutConstraints = new GridBagConstraints(); 
			    setLayout(layout);
			    
			    scroll     = new JScrollPane();
			    list      = new JList();
			    layoutConstraints.gridx      = 0; layoutConstraints.gridy = 0; 
			    layoutConstraints.gridwidth = 2; layoutConstraints.gridheight = 1; 
			    layoutConstraints.fill           = GridBagConstraints.BOTH; 
			    layoutConstraints.insets      = new Insets(1, 1, 1, 1); 
			    layoutConstraints.anchor      = GridBagConstraints.CENTER; 
			    layoutConstraints.weightx      = 1.0; layoutConstraints.weighty = 1.0;
			    scroll = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			    JScrollPane. HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			    layout.setConstraints(scroll, layoutConstraints);
			    add(scroll);
			}
		}      
	}
     
     static class MyCellRenderer extends JPanel implements ListCellRenderer{
          JLabel lID, lYear, lMonth, lDay, lTime, lProtocol, lSourceIP, lDestIP, lSourcePort, lDestPort, lRule;
          
          MyCellRenderer() {
               setLayout(new GridLayout(1, 12));
               lID = new JLabel();
               lYear = new JLabel();
               lMonth = new JLabel(); 
               lDay = new JLabel(); 
               lTime = new JLabel();
               lProtocol = new JLabel(); 
               lSourceIP = new JLabel(); 
               lDestIP = new JLabel(); 
               lSourcePort = new JLabel(); 
               lDestPort = new JLabel(); 
               lRule = new JLabel();
               
               lID.setOpaque(true);
               lYear.setOpaque(true);
               lMonth.setOpaque(true);
               lDay.setOpaque(true);
               lTime.setOpaque(true);
               lProtocol.setOpaque(true);
               lSourceIP.setOpaque(true);
               lDestIP.setOpaque(true);
               lSourcePort.setOpaque(true);
               lDestPort.setOpaque(true);
               lRule.setOpaque(true);

               add(lID);
               add(lYear);
               add(lMonth);
               add(lDay);
               add(lTime);
               add(lProtocol);
               add(lSourceIP);
               add(lDestIP);
               add(lSourcePort);
               add(lDestPort);
               add(lRule);
          }
          
          public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus){
        	  String sID = 			((String[])value)[0];
        	  String sYear = 		((String[])value)[4];
        	  String sMonth = 		((String[])value)[1];
        	  String sDay = 		((String[])value)[2];
        	  String sTime = 		((String[])value)[3];
        	  String sProtocol = 	((String[])value)[8];
        	  String sSourceIP = 	((String[])value)[6];
        	  String sDestIP = 		((String[])value)[7];
        	  String sSourcePort = 	((String[])value)[9];
        	  String sDestPort = 	((String[])value)[10];
        	  String sRule = 		((String[])value)[5];

        	  lID.setText(sID);
        	  lYear.setText(sYear);
              lMonth.setText(sMonth);
              lDay.setText(sDay);
              lTime.setText(sTime);
              lProtocol.setText(sProtocol);
              lSourceIP.setText(sSourceIP);
              lDestIP.setText(sDestIP);
              lSourcePort.setText(sSourcePort);
              lDestPort.setText(sDestPort);
              lRule.setText(sRule);

               if(isSelected)
               {
            	   	lID.setBackground(list.getSelectionBackground());
           	   		lID.setForeground(list.getSelectionForeground());
            	   	lYear.setBackground(list.getSelectionBackground());
            	   	lYear.setForeground(list.getSelectionForeground());
					lMonth.setBackground(list.getSelectionBackground());
					lMonth.setForeground(list.getSelectionForeground());
					lDay.setBackground(list.getSelectionBackground());
					lDay.setForeground(list.getSelectionForeground());
					lTime.setBackground(list.getSelectionBackground());
					lTime.setForeground(list.getSelectionForeground());
					lProtocol.setBackground(list.getSelectionBackground());
					lProtocol.setForeground(list.getSelectionForeground());
					lSourceIP.setBackground(list.getSelectionBackground());
					lSourceIP.setForeground(list.getSelectionForeground());
					lDestIP.setBackground(list.getSelectionBackground());
					lDestIP.setForeground(list.getSelectionForeground());
					lSourcePort.setBackground(list.getSelectionBackground());
					lSourcePort.setForeground(list.getSelectionForeground());
					lDestPort.setBackground(list.getSelectionBackground());
					lDestPort.setForeground(list.getSelectionForeground());
					lRule.setBackground(list.getSelectionBackground());
					lRule.setForeground(list.getSelectionForeground());
               }
               else
               {
            	   	lID.setBackground(list.getBackground());
          	   		lID.setForeground(list.getForeground());
            	   	lYear.setBackground(list.getBackground());
           	   		lYear.setForeground(list.getForeground());
					lMonth.setBackground(list.getBackground());
					lMonth.setForeground(list.getForeground());
					lDay.setBackground(list.getBackground());
					lDay.setForeground(list.getForeground());
					lTime.setBackground(list.getBackground());
					lTime.setForeground(list.getForeground());
					lProtocol.setBackground(list.getBackground());
					lProtocol.setForeground(list.getForeground());
					lSourceIP.setBackground(list.getBackground());
					lSourceIP.setForeground(list.getForeground());
					lDestIP.setBackground(list.getBackground());
					lDestIP.setForeground(list.getForeground());
					lSourcePort.setBackground(list.getBackground());
					lSourcePort.setForeground(list.getForeground());
					lDestPort.setBackground(list.getBackground());
					lDestPort.setForeground(list.getForeground());
					lRule.setBackground(list.getBackground());
					lRule.setForeground(list.getForeground());
               }
               setEnabled(list.isEnabled());
               setFont(list.getFont());
               
               for (int i = 0; i < COLONNES; i++)
         	  {
         		  if (((String[])value)[5].matches("DENY"))
         		  {
         			  lID.setBackground(Color.RED);
         			  lYear.setBackground(Color.RED);
 	                  lMonth.setBackground(Color.RED);
 	                  lDay.setBackground(Color.RED);
 	                  lTime.setBackground(Color.RED);
 	                  lProtocol.setBackground(Color.RED);
 	                  lSourceIP.setBackground(Color.RED);
 	                  lDestIP.setBackground(Color.RED);
 	                  lSourcePort.setBackground(Color.RED);
 	                  lDestPort.setBackground(Color.RED);
 	                  lRule.setBackground(Color.RED);
         		  }
         	  }
               
              return this;
          }
     }
}