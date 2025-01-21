import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class LandingPage extends JFrame //implements ActionListener
{

    public static void main (String args[])
    {
	new LandingPage ();
    }


    public LandingPage ()
    {
	JFrame frame = new JFrame ();
	JPanel LandingPageMain = (JPanel) frame.getContentPane ();
	frame.setSize (600, 400);
	JPanel panel = new JPanel (new GridBagLayout ());
	GridBagConstraints constraints = new GridBagConstraints ();
	ImageIcon Logo = new ImageIcon ("./assets/MonopolyLogo.jpg");
	JLabel LogoLabel = new JLabel (Logo);
	JButton startButton = new JButton ("START");
	JLabel P1Label = new JLabel ("PLAYER ONE");
	JLabel P2Label = new JLabel ("PLAYER TWO");
	Font currentFont = P1Label.getFont ();
	//int newSize = currentFont.getSize () + 2;
	Font label = new Font ("Serif", Font.BOLD, 20);
	P1Label.setFont (label);
	P2Label.setFont (label);


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

	LandingPageMain.add (panel);
	frame.setVisible (true);

    }
}

