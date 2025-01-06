import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class LandingPage2 extends JFrame implements ActionListener
{

    public static void main (String args[])
    {
	new LandingPage2 ();
    }


    public LandingPage2 ()
    {
	JFrame frame = new JFrame ();
	JPanel LandingPageMain = (JPanel) frame.getContentPane ();
	frame.setSize (600, 400);
	JPanel panel = new JPanel (new GridBagLayout ());
	GridBagConstraints constraints = new GridBagConstraints ();
	ImageIcon Logo = new ImageIcon ("./assets/MonopolyLogo.jpg");
	
	String names [] = new String [2];
	
	JLabel LogoLabel = new JLabel (Logo);
	JButton startButton = new JButton ("START");

	JLabel P1Label = new JLabel ("PLAYER ONE");
	JLabel P2Label = new JLabel ("PLAYER TWO");

	Font currentFont = P1Label.getFont ();
	Font label = new Font ("Serif", Font.BOLD, 20);
	P1Label.setFont (label);
	P2Label.setFont (label);

	JButton p1 = new JButton ("");
	JButton p2 = new JButton ("");
	p1.setBackground (Color.blue);
	p2.setBackground (Color.red);
	p1.setPreferredSize (new Dimension (30, 30));
	p2.setPreferredSize (new Dimension (30, 30));
	JTextField p1Name = new JTextField ("Enter your name", 15);
	JTextField p2Name = new JTextField ("Enter your name", 15);

	JPanel LogoPane = new JPanel ();
	LogoPane.add (LogoLabel);



	constraints.gridx = 5;
	constraints.gridy = 1;
	panel.add (LogoPane, constraints);

	constraints.gridx = 5;
	constraints.gridy = 2;
	panel.add (startButton, constraints);


	constraints.gridx = 4;
	constraints.gridy = 3;
	//constraints.gridheight = 2;
	//constraints.gridwidth = 2;
	panel.add (P1Label, constraints);


	constraints.gridx = 6;
	constraints.gridy = 3;
	//constraints.gridheight = 2;
	//constraints.gridwidth = 2;
	panel.add (P2Label, constraints);

	constraints.gridx = 6;
	constraints.gridy = 2;
	panel.add (p1, constraints);

	constraints.gridx = 4;
	constraints.gridy = 2;
	panel.add (p2, constraints);

	constraints.gridx = 6;
	constraints.gridy = 4;
	panel.add (p1Name, constraints);

	constraints.gridx = 4;
	constraints.gridy = 4;
	panel.add (p2Name, constraints);


	startButton.addActionListener (this);
	LandingPageMain.add (panel);
	frame.setVisible (true);

    }


    public void actionPerformed (ActionEvent evt)
    {
	doMyButtonAction ();
	repaint ();
    }


    public void doMyButtonAction ()
    {
	names[1] = p1Name.getText();
	names[2] = p2Name.getText();
	return names;
    }
}

