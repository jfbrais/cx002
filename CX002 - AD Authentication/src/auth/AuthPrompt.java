package auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Map;

public class AuthPrompt extends JPanel implements ActionListener 
{
	private Map umap;
	private String password = "";
	
	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; // needed for dialogs
	private TextField usernameField;
	private JPasswordField passwordField;

	public AuthPrompt(JFrame f) 
	{
		// Use the default FlowLayout.
		controllingFrame = f;
		
		// Create everything.
		usernameField = new TextField(20);
		usernameField.addActionListener(this);
				
		passwordField = new JPasswordField(20);
		passwordField.setActionCommand(OK);
		passwordField.addActionListener(this);

		JLabel lUser = new JLabel("Username: ");
		lUser.setLabelFor(usernameField);
		
		JLabel lPwd = new JLabel("Password: ");
		lPwd.setLabelFor(passwordField);

		JComponent buttonPane = createButtonPanel();

		// Lay out everything.
		JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		textPane.add(lUser);
		textPane.add(usernameField);
		textPane.add(lPwd);
		textPane.add(passwordField);

		add(textPane);
		add(buttonPane);
	}

	protected JComponent createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		JButton okButton = new JButton("OK");
		JButton helpButton = new JButton("Help");

		okButton.setActionCommand(OK);
		helpButton.setActionCommand(HELP);
		okButton.addActionListener(this);
		helpButton.addActionListener(this);

		p.add(okButton);
		p.add(helpButton);

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) 
		{ // Process the password.
			
			ADAuth ada = new ADAuth();
			
			char[] input = passwordField.getPassword();
			
			for (int i = 0; i < input.length; i++)
				password += input[i];
			
			umap = ada.authenticate(usernameField.getText(), password);
			
			System.out.println("Username    : " + usernameField.getText());
			if (umap != null)
				System.out.println("User's name : " + umap.values().toString());
			
			if (umap == null)
			{
				JOptionPane.showMessageDialog(controllingFrame,
						"Login failed: " + ada.getResult(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				JOptionPane.showMessageDialog(controllingFrame,
				  "Login succeeded.");
			}

			// Zero out the possible password, for security.
			Arrays.fill(input, '0');

			password = "";
			passwordField.setText("");
			passwordField.selectAll();
			resetFocus();
		} else { // The user has asked for help.
			JOptionPane
					.showMessageDialog(
							controllingFrame,
										"You need to use the authentication information\n"
									+ 	"provided by your systems administrator that lets\n"
									+ 	"you log in to the domain.\n"
									+ 	"Created by: Jean-François Brais-Villemur");
		}
	}

	// Must be called from the event dispatch thread.
	protected void resetFocus() {
		if (usernameField.getText().equals(""))
			usernameField.requestFocusInWindow();
		else
			passwordField.requestFocusInWindow();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame frame = new JFrame("Log Parser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(frame.getWidth());
		
		// Create and set up the content pane.
		final AuthPrompt newContentPane = new AuthPrompt(frame);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Make sure the focus goes to the right component
		// whenever the frame is initially given the focus.
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				newContentPane.resetFocus();
			}
		});

		// Display the window.
		frame.pack();
		frame.setLocation((int)((screenSize.getWidth() / 2) - frame.getWidth() / 2), 
				  (int)((screenSize.getHeight() / 2) - frame.getHeight() / 2));

		frame.setVisible(true);
	}
}
