// Purpose: The game runs a game of monopoly with all the neccessary features to enjoy with your friends(Buying, Upgrading, Rent, Rolling, Ending Turn), and even features individual jail features(double role, pay 50, wait turn)!
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Monopoly extends JFrame implements ActionListener
{
    //Array for all the property houses
    JButton mediterraneanhouses[], Baltichouses[], orientalhouses[], vermonthouses[], connecticuthouses[], StCharlesHouses[], StatesAveHouses[], VirginiaAveHouses[], StJamesHouses[], TennesseHouses[], NewYorkHouses[], KentuckyHouses[], IndianaHouses[], IllinoisHouses[], AtlanticHouses[], VentnorHouses[], MarvinHouses[], PacificAvenueHouses[], NorthCarolinaHouses[], PennsylvaniaAveHouses[], parkPlaceHouses[], boardwalkHouses[];
    static JButton posButtonsBlue[] = new JButton [40]; //Array for player 2 positions along the board
    static JButton posButtonsRed[] = new JButton [40]; //Array for player 1 positions along the board
    static ImageIcon images[] = new ImageIcon [40]; //All images for each space on the board
    JTextArea commentary, p1List, p2List; //text areas for the ingame commentary and player properties owned
    boolean turn = true; //to differenitiate player turns
    int p1Pos = 0, p2Pos = 0; //positions for players
    static int p1Money = 500, p2Money = 500; //player money
    static boolean player1Roll = true; //flag so the fiirst thing player does is roll
    static boolean player2Roll = true; //same for player 2
    //all the properties that can be bought
    boolean buyableProperties[] = {false, true, false, true, false, true, true, false, true, true, false, true, true, true, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, false, true, false, true};
    //the amount they cost
    int PropertyPrice[] = {0, 60, 0, 60, 0, 200, 100, 0, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240, 200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400};
    //The names of all the properties
    String[] properties = {"Go", "Mediterranean Avenue", "Community Chest", "Baltic Avenue", "Income Tax", "Reading Railroad", "Oriental Avenue", "Chance", "Vermont Avenue", "Connecticut Avenue", "Jail/Just Visiting", "St. Charles Place", "Electric Company", "States Avenue", "Virginia Avenue", "Pennsylvania Railroad", "St. James Place", "Community Chest", "Tennessee Avenue", "New York Avenue", "Free Parking", "Kentucky Avenue", "Chance", "Indiana Avenue", "Illinois Avenue", "B&O Railroad", "Atlantic Avenue", "Ventnor Avenue", "Water Works", "Marvin Gardens", "Go to Jail", "Pacific Avenue", "North Carolina Avenue", "Community Chest", "Pennsylvania Avenue", "Short Line", "Chance", "Park Place", "Luxury Tax", "Boardwalk"};
    //panel for the title deeds to be added on
    JPanel DeedCard;
    //vectors for player properites owned
    Vector player1Assets = new Vector ();
    Vector player2Assets = new Vector ();
    int dice = 0; //dice
    //all the title deed cards
    String titleDeedNames[] = {"blank.jpg", "MediterraneanTD.jpg", "blank.jpg", "BalticTD.jpg", "blank.jpg", "ReadingRRTD.jpg", "orientalTD.jpg", "blank.jpg", "vermontTD.jpg", "connecticutTD.jpg", "blank.jpg", "StCharlesTD.jpg", "electricTD.jpg", "StatesTD.jpg", "virginiaTD.jpg", "PennsylvaniaRRTD.jpg", "StJamesTD.jpg", "blank.jpg", "TennesseeTD.jpg", "NewYorkTD.jpg", "blank.jpg", "kentuckyTD.jpg", "blank.jpg", "IndianaTD.jpg", "illinoisTD.jpg", "B_ORRTD.jpg", "atlanticTD.jpg", "ventnorTD.jpg", "waterTD.jpg", "marvinTD.jpg", "blank.jpg", "pacificTD.jpg", "carolinaTD.jpg", "blank.jpg", "pennsylvaniaTD.jpg", "ShortLineRRTD.png", "blank.jpg", "parkPlaceTD.jpg", "blank.jpg", "boardwalkTD.jpg"};
    //imageicons holding the names of title deed cards
    ImageIcon titleDeedCards[] = new ImageIcon [40];
    LandingPage25 abc = new LandingPage25 (); //starting page
    String p1Name; //names
    String p2Name;
    //community chest and chance cards
    String CommunityChest[] = {"BankErrorCommunityChest.jpg", "BeautyContestCommunityChest.jpg", "CC_Advance_to_GoCommunityChest.jpg", "ConsultancyFeeCommunityChest.jpg", "DoctorFeeCommunityChest.jpg", "GetOutOfJailCommunityChest.jpg", "GoToJailCommunityChest.jpg", "HolidayFundMatureCommunityChest.jpg", "IncomeTaxRefundCommunityChest.jpg", "Inherit100CommunityChest.jpg", "LifeInsuranceMaturesCommunityChest.jpg", "PayHospitalCommunityChest.jpg", "StockSaleCommunityChest.jpg"};
    String ChanceCards[] = {"Advance_To_BoardwalkChance.jpg", "Advance_To_GoChance.jpg", "AdvanceIllinoisAvenueChance.jpg", "AdvanceToStCharlesChance.jpg", "BackThreeChance.jpg", "ChairMaxChance.jpg", "DividenDChance.jpg", "GoToJailChance.jpg", "LoanMatureChance.jpg", "OutOfJailChance.jpg", "tripreadingrailroadChance.jpg"};
    int p1numRR = 0; //num railroads owned
    int p1numUtilities = 0; //num of utitlites
    int p2numRR = 0;
    int p2numUtilities = 0;
    static ImageIcon[] Chest = new ImageIcon [13]; //imageicon for community cards
    static ImageIcon[] Chance = new ImageIcon [11]; //imageicons for chance
    boolean jp1Rights = false; //rights to check if you chose an outcome in jail for both players
    boolean jp2Rights = false;
    boolean jailallowed = false; // ensures when p1 is jail only their options are accessed and vice versa for p2

    boolean p1flag = false; //To ensure actionlistneres are activated twice
    boolean p2flag = false;
    EndScreen efg = new EndScreen (); //ending screen

    boolean winner; //to see who won
    int score1 = 0; //score for overall wins
    int score2 = 0;


    boolean p1jailCard = false; //get out jail free cards
    boolean p2jailCard = false;
    int propertyUpgrades[] = {6, 0, 6, 0, 6, 6, 0, 6, 0, 0, 6, 0, 6, 0, 0, 6, 0, 6, 0, 0, 6, 0, 6, 0, 0, 6, 0, 0, 6, 0, 6, 0, 0, 6, 0, 6, 6, 0, 6, 0}; //num of upgrades for each property
    ImageIcon backChest; //image of the back card of the community chest
    JLabel chestBack; //label to hold image
    boolean p1Jail = false, p2Jail = false; //if players in jail
    int p1jailturn = 0; //amount of turns spent in jail
    int p2jailturn = 0;
    ImageIcon backChance; //same thing as community chest but for chance
    JLabel chanceBack;

    ImageIcon backDeed; //same thing as community chest but for chance
    JLabel DeedBack;
    boolean p1Rights, p2Rights; //rights ensure the player has rolled before perfomring an action
    String word; //to get the existing text in the commentary
    String prop1, prop2; //string to get existing texts in the property owned textfields
    int[] [] rentData = {
	// Rent for a property with no house, 1 house, 2 houses, 3 houses, 4 houses, hotel
	    {0, 0, 0, 0, 0, 0},  // Go
	    {2, 10, 30, 90, 160, 250},  // Mediterranean Avenue
	    {0, 0, 0, 0, 0, 0},  // Community Chest
	    {4, 20, 60, 180, 320, 450},  // Baltic Avenue
	    {0, 0, 0, 0, 0, 0},  // Income Tax
	    {0, 0, 0, 0, 0, 0},  // Reading Railroad
	    {6, 30, 90, 270, 400, 550},  // Oriental Avenue
	    {0, 0, 0, 0, 0, 0},  // Chance
	    {6, 30, 90, 270, 400, 550},  // Vermont Avenue
	    {8, 40, 100, 300, 450, 600},  // Connecticut Avenue
	    {0, 0, 0, 0, 0, 0},  // Jail
	    {10, 50, 150, 450, 625, 750},  // St. Charles Place
	    {0, 0, 0, 0, 0, 0},  // Electric Company
	    {10, 50, 150, 450, 625, 750},  // States Avenue
	    {12, 60, 180, 500, 700, 900},  // Virginia Avenue
	    {0, 0, 0, 0, 0, 0},  // Pennsylvania Railroad
	    {14, 70, 200, 550, 750, 950},  // St. James Place
	    {0, 0, 0, 0, 0, 0},  // Community Chest
	    {14, 70, 200, 550, 750, 950},  // Tennessee Avenue
	    {16, 80, 220, 600, 800, 1000},  // New York Avenue
	    {0, 0, 0, 0, 0, 0},  // Free Parking
	    {18, 90, 250, 700, 875, 1050},  // Kentucky Avenue
	    {0, 0, 0, 0, 0, 0},  // Chance
	    {18, 90, 250, 700, 875, 1050},  // Indiana Avenue
	    {20, 100, 300, 750, 925, 1100},  // Illinois Avenue
	    {0, 0, 0, 0, 0, 0},  // B. & O. Railroad
	    {22, 110, 330, 800, 975, 1150},  // Atlantic Avenue
	    {22, 110, 330, 800, 975, 1150},  // Ventnor Avenue
	    {0, 0, 0, 0, 0, 0},  // Water Works
	    {24, 120, 360, 850, 1025, 1200},  // Marvin Gardens
	    {0, 0, 0, 0, 0, 0},  // Go to Jail
	    {26, 130, 390, 900, 1100, 1275},  // Pacific Avenue
	    {26, 130, 390, 900, 1100, 1275},  // North Carolina Avenue
	    {0, 0, 0, 0, 0, 0},  // Community Chest
	    {28, 150, 450, 1000, 1200, 1400},  // Pennsylvania Avenue
	    {0, 0, 0, 0, 0, 0},  // Short Line Railroad
	    {0, 0, 0, 0, 0, 0},  // Chance
	    {35, 175, 500, 1200, 1400, 1700},  // Park Place
	    {0, 0, 0, 0, 0, 0},  // Luxury Tax
	    {50, 200, 600, 1400, 1700, 2000}};  // Boardwalk


    public static void main (String args[])
    {
	new Monopoly ();
    }





    public Monopoly ()
    {

	JFrame frame = new JFrame ();
	// Create a JFrame (main window)
	JPanel MainPanel = (JPanel) frame.getContentPane ();

	frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());

	frame.setVisible (true);

	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	backChest = new ImageIcon ("./assets/CommunityChestBackCard.jpg");
	chestBack = new JLabel (backChest); //adding back card of community chest

	backChance = new ImageIcon ("./assets/ChanceBackCard.jpg");
	chanceBack = new JLabel (backChance); //adding back card of chance

	backDeed = new ImageIcon ("./assets/blank.jpg");
	DeedBack = new JLabel (backDeed);


	String names[] = abc.LandingPage22 (); //calling landing page and choosing names
	p1Name = names [0]; //storing names
	p2Name = names [1];

	JPanel panel = new JPanel (new GridBagLayout ()); //Grid Bag layout: Allows you to store components in a grid-like structure, however it also gives you the flexibitliy of placing components where you want using constraints and we can also use it make componenets span across different rows and columns
	GridBagConstraints constraints = new GridBagConstraints (); // Constraints for layout
	//names of all the properties
	String imageNames[] = {"./assets/Go.jpg", "./assets/Mediterranean.jpg", "./assets/community_chest.jpg", "./assets/Baltic.jpg", "./assets/IncomeTax.jpg", "./assets/Reading Railroad.jpg", "./assets/Oriental.jpg", "./assets/Chance.jpg", "./assets/vermont.jpg", "./assets/Connecticut Avenue.jpg", "./assets/In Jail.jpg", "./assets/St. Charles Place.jpg", "./assets/Electric Company.jpg", "./assets/States Avenue.jpg", "./assets/Virginia Avenue.jpg", "./assets/Pennsylvania RailRoad.jpg", "./assets/St. James Place.jpg", "./assets/community_chest2.jpg", "./assets/Tennessee Avenue.jpg", "./assets/New York Avenue.jpg", "./assets/FreeParking.jpg", "./assets/Kentucky Avenue.jpg", "./assets/Chance2.jpg", "./assets/Indiana Avenue.jpg", "./assets/Illinois Avenue.jpg", "./assets/B_O RailRoad.jpg", "./assets/Atlantic Avenue.jpg", "./assets/Ventnor Avenue.jpg", "./assets/Water Works.jpg", "./assets/Marvin Gardens.jpg", "./assets/GoToJail.jpg", "./assets/Pacific Avenue.jpg", "./assets/North Carolina Avenue.jpg", "./assets/CommunityChest3.jpg", "./assets/Pennsylvania Avenue.jpg", "./assets/Short Line railroad.jpg", "./assets/Chance3.jpg", "./assets/Park Place.jpg", "./assets/Luxury Tax.jpg", "./assets/Boardwalk.jpg"};
	for (int count = 0 ; count < 40 ; count++) //connecting the file names with the image icons for the title deed
	{
	    titleDeedCards [count] = new ImageIcon (titleDeedNames [count]);

	}

	//Community Chest
	for (int count = 0 ; count < 13 ; count++) //doing the same thing for community chest
	{
	    Chest [count] = new ImageIcon (CommunityChest [count]);

	}
	//Chance
	for (int count = 0 ; count < 11 ; count++) //doing the same thing for chance
	{
	    Chance [count] = new ImageIcon (ChanceCards [count]);

	}
	//Board Panelss
	for (int count = 0 ; count < 40 ; count++) //doing the same thing for the board panels
	{
	    images [count] = new ImageIcon (imageNames [count]);

	}
	//storing each image into the labels
	JLabel pos[] = new JLabel [40];

	for (int count = 0 ; count < 40 ; count++)
	{
	    pos [count] = new JLabel (images [count]);
	}
	//for pos buttons
	for (int count = 0 ; count < 40 ; count++)
	{
	    posButtonsBlue [count] = new JButton (""); //creating 40 buttons for each pos for blue and red
	    posButtonsRed [count] = new JButton ("");
	    if (count <= 10) // horizontal buttons for bottom side
	    {
		posButtonsRed [count].setPreferredSize (new Dimension (28, 14)); //changing size of buttons
		posButtonsBlue [count].setPreferredSize (new Dimension (28, 14));
	    }

	    if (count > 10 && count <= 20) //vertical buttons for left side
	    {
		posButtonsRed [count].setPreferredSize (new Dimension (14, 28));
		posButtonsBlue [count].setPreferredSize (new Dimension (14, 28));
	    }

	    if (count > 20 && count <= 30) //horizontal for top buttons
	    {
		posButtonsRed [count].setPreferredSize (new Dimension (28, 14));
		posButtonsBlue [count].setPreferredSize (new Dimension (28, 14));
	    }


	    if (count > 30 && count <= 40) //vertical buttons for right side
	    {
		posButtonsRed [count].setPreferredSize (new Dimension (14, 28));
		posButtonsBlue [count].setPreferredSize (new Dimension (14, 28));
	    }
	}

	//Go panel
	JPanel GoPanel = new JPanel ();
	GoPanel.add (pos [0]); //adding the label on the panel

	JPanel GOPos = new JPanel (); //creating a panel for the buttons
	GOPos.setLayout (new GridLayout (1, 2)); //setting a layout
	posButtonsRed [0].setPreferredSize (new Dimension (40, 14)); //buttons on GO, JAIL, FREE PARKING, JUST VISITING, are these sizes
	posButtonsBlue [0].setPreferredSize (new Dimension (40, 14));
	GOPos.add (posButtonsRed [0]); //adding buttons onto the panel
	GOPos.add (posButtonsBlue [0]);




	//mediterranean card - pos = 1
	JPanel mediterraneanCard = new JPanel (); //creating panel for the actual image card
	mediterraneanCard.add (pos [1]);

	//mediterranean Houses - pos = 1
	JPanel mediterraneanHouse = new JPanel (); //creating panel the houses
	mediterraneanHouse.setLayout (new GridLayout (1, 5)); //creating layout
	mediterraneanhouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++) //adding buttons to the panel
	{
	    mediterraneanhouses [count] = new JButton ();
	    mediterraneanhouses [count].setPreferredSize (new Dimension (11, 13));
	    mediterraneanHouse.add (mediterraneanhouses [count]);
	}

	//mediterranean player pos = 1
	JPanel mediterraneanPos = new JPanel (); //creating panel for the position buttons
	mediterraneanPos.setLayout (new GridLayout (1, 2));

	mediterraneanPos.add (posButtonsRed [1]); //adding buttons to the panel
	mediterraneanPos.add (posButtonsBlue [1]);
	//This process above is for all the properties except for railroad, utilites, Income and Luxury Tax, community chest and chance.
	//The specificed space will not have houses


	//community chest  pos = 2
	JPanel CCCard = new JPanel ();
	CCCard.add (pos [2]);

	//community chest pos  pos = 2
	JPanel CCPos = new JPanel ();
	CCPos.setLayout (new GridLayout (1, 2));


	CCPos.add (posButtonsRed [2]);
	CCPos.add (posButtonsBlue [2]);



	//baltic card - pos = 3
	JPanel balticCard = new JPanel ();
	balticCard.add (pos [3]);

	//baltic Houses - pos = 3
	JPanel balticHouse = new JPanel ();
	balticHouse.setLayout (new GridLayout (1, 5));
	Baltichouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    Baltichouses [count] = new JButton ();
	    Baltichouses [count].setPreferredSize (new Dimension (11, 13));
	    balticHouse.add (Baltichouses [count]);
	}

	//baltic player pos =3
	JPanel balticPos = new JPanel ();
	balticPos.setLayout (new GridLayout (1, 2));

	balticPos.add (posButtonsRed [3]);
	balticPos.add (posButtonsBlue [3]);


	//Income Tax pos = 4
	JPanel IncomeTax = new JPanel ();
	IncomeTax.add (pos [4]);

	//Income Tax pos = 4
	JPanel ITPos = new JPanel ();
	ITPos.setLayout (new GridLayout (1, 2));

	ITPos.add (posButtonsRed [4]);
	ITPos.add (posButtonsBlue [4]);

	//Reading railroad pos = 5
	JPanel ReadingRR = new JPanel ();
	ReadingRR.add (pos [5]);

	//community chest pos  pos = 2
	JPanel ReadingRRPos = new JPanel ();
	ReadingRRPos.setLayout (new GridLayout (1, 2));

	ReadingRRPos.add (posButtonsRed [5]);
	ReadingRRPos.add (posButtonsBlue [5]);



	JPanel orientalCard = new JPanel ();
	orientalCard.add (pos [6]);

	//oriental  houses pos = 6
	JPanel OrientalHouse = new JPanel ();
	OrientalHouse.setLayout (new GridLayout (1, 5));
	orientalhouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    orientalhouses [count] = new JButton ();
	    orientalhouses [count].setPreferredSize (new Dimension (11, 13));
	    OrientalHouse.add (orientalhouses [count]);
	}

	//oriental player pos = 6
	JPanel orientalPos = new JPanel ();
	orientalPos.setLayout (new GridLayout (1, 2));


	orientalPos.add (posButtonsRed [6]);
	orientalPos.add (posButtonsBlue [6]);


	//Chance Cards
	JPanel ChanceCard = new JPanel ();
	ChanceCard.add (pos [7]);

	//chance card pos = 7
	JPanel ChancePos = new JPanel ();
	ChancePos.setLayout (new GridLayout (1, 2));

	ChancePos.add (posButtonsRed [7]);
	ChancePos.add (posButtonsBlue [7]);

	//vermont Card - pos = 8
	JPanel vermontCard = new JPanel ();
	vermontCard.add (pos [8]);

	//vermont  houses pos = 8
	JPanel VermontHouse = new JPanel ();
	VermontHouse.setLayout (new GridLayout (1, 5));
	vermonthouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    vermonthouses [count] = new JButton ();
	    vermonthouses [count].setPreferredSize (new Dimension (11, 13));
	    VermontHouse.add (vermonthouses [count]);
	}

	//vermont player pos = 8
	JPanel vermontPos = new JPanel ();
	vermontPos.setLayout (new GridLayout (1, 2));

	vermontPos.add (posButtonsRed [8]);
	vermontPos.add (posButtonsBlue [8]);


	//connecticut
	JPanel connecticutCard = new JPanel ();
	connecticutCard.add (pos [9]);

	//connecticut  houses pos = 9
	JPanel connecticutHouse = new JPanel ();
	connecticutHouse.setLayout (new GridLayout (1, 5));
	connecticuthouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    connecticuthouses [count] = new JButton ();
	    connecticuthouses [count].setPreferredSize (new Dimension (11, 13));
	    connecticutHouse.add (connecticuthouses [count]);
	}

	//connecticut player pos = 9
	JPanel connecticutPos = new JPanel ();
	connecticutPos.setLayout (new GridLayout (1, 2));

	connecticutPos.add (posButtonsRed [9]);
	connecticutPos.add (posButtonsBlue [9]);


	//Jail Card = 10
	JPanel JailCard = new JPanel ();
	JailCard.add (pos [10]);

	//Jail Card = 10
	JPanel JailPos = new JPanel ();
	JailPos.setLayout (new GridLayout (1, 2));
	posButtonsRed [10].setPreferredSize (new Dimension (40, 14));
	posButtonsBlue [10].setPreferredSize (new Dimension (40, 14));
	JailPos.add (posButtonsRed [10]);
	JailPos.add (posButtonsBlue [10]);

	//St Charles
	JPanel StCharlesCard = new JPanel ();
	StCharlesCard.add (pos [11]);

	//St Charles Houses - pos = 11
	JPanel StCharlesHouse = new JPanel ();
	StCharlesHouse.setLayout (new GridLayout (5, 1));
	StCharlesHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    StCharlesHouses [count] = new JButton ();
	    StCharlesHouses [count].setPreferredSize (new Dimension (13, 11));
	    StCharlesHouse.add (StCharlesHouses [count]);
	}

	//St Charles player pos = 11
	JPanel StCharlesPos = new JPanel ();
	StCharlesPos.setLayout (new GridLayout (2, 1));

	StCharlesPos.add (posButtonsRed [11]);
	StCharlesPos.add (posButtonsBlue [11]);


	//Electric Company
	JPanel ElectricCard = new JPanel ();
	ElectricCard.add (pos [12]);

	JPanel ElectricPos = new JPanel ();
	ElectricPos.setLayout (new GridLayout (2, 1));

	ElectricPos.add (posButtonsRed [12]);
	ElectricPos.add (posButtonsBlue [12]);


	//States Avenue
	JPanel StatesAveCard = new JPanel ();
	StatesAveCard.add (pos [13]);

	JPanel StatesAveHouse = new JPanel ();
	StatesAveHouse.setLayout (new GridLayout (5, 1));
	StatesAveHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    StatesAveHouses [count] = new JButton ();
	    StatesAveHouses [count].setPreferredSize (new Dimension (13, 11));
	    StatesAveHouse.add (StatesAveHouses [count]);
	}

	JPanel StatesAvePos = new JPanel ();
	StatesAvePos.setLayout (new GridLayout (2, 1));

	StatesAvePos.add (posButtonsRed [13]);
	StatesAvePos.add (posButtonsBlue [13]);
	//Virigina Avenue
	JPanel VirginiaAveCard = new JPanel ();
	VirginiaAveCard.add (pos [14]);

	JPanel VirginiaAveHouse = new JPanel ();
	VirginiaAveHouse.setLayout (new GridLayout (5, 1));
	VirginiaAveHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    VirginiaAveHouses [count] = new JButton ();
	    VirginiaAveHouses [count].setPreferredSize (new Dimension (13, 11));
	    VirginiaAveHouse.add (VirginiaAveHouses [count]);
	}

	JPanel VirginiaAvePos = new JPanel ();
	VirginiaAvePos.setLayout (new GridLayout (2, 1));

	VirginiaAvePos.add (posButtonsRed [14]);
	VirginiaAvePos.add (posButtonsBlue [14]);

	//Pennsylvania railroad
	JPanel PennsylvaniaRRCard = new JPanel ();
	PennsylvaniaRRCard.add (pos [15]);

	JPanel PennsylvaniaRRPos = new JPanel ();
	PennsylvaniaRRPos.setLayout (new GridLayout (2, 1));

	PennsylvaniaRRPos.add (posButtonsRed [15]);
	PennsylvaniaRRPos.add (posButtonsBlue [15]);

	//St James
	JPanel StJamesCard = new JPanel ();
	StJamesCard.add (pos [16]);

	JPanel StJamesHouse = new JPanel ();
	StJamesHouse.setLayout (new GridLayout (5, 1));
	StJamesHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    StJamesHouses [count] = new JButton ();
	    StJamesHouses [count].setPreferredSize (new Dimension (13, 11));
	    StJamesHouse.add (StJamesHouses [count]);
	}

	JPanel StJamesPos = new JPanel ();
	StJamesPos.setLayout (new GridLayout (2, 1));

	StJamesPos.add (posButtonsRed [16]);
	StJamesPos.add (posButtonsBlue [16]);

	//Community Chest 2
	JPanel CCCard2 = new JPanel ();
	CCCard2.add (pos [17]);

	JPanel CCCard2Pos = new JPanel ();
	CCCard2Pos.setLayout (new GridLayout (2, 1));

	CCCard2Pos.add (posButtonsRed [17]);
	CCCard2Pos.add (posButtonsBlue [17]);
	//Tennesse Avenue
	JPanel TennesseCard = new JPanel ();
	TennesseCard.add (pos [18]);

	JPanel TennesseHouse = new JPanel ();
	TennesseHouse.setLayout (new GridLayout (5, 1));
	TennesseHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    TennesseHouses [count] = new JButton ();
	    TennesseHouses [count].setPreferredSize (new Dimension (13, 11));
	    TennesseHouse.add (TennesseHouses [count]);
	}

	JPanel TennessePos = new JPanel ();
	TennessePos.setLayout (new GridLayout (2, 1));

	TennessePos.add (posButtonsRed [18]);
	TennessePos.add (posButtonsBlue [18]);
	//New York Avenue
	JPanel NewYorkCard = new JPanel ();
	NewYorkCard.add (pos [19]);

	JPanel NewYorkHouse = new JPanel ();
	NewYorkHouse.setLayout (new GridLayout (5, 1));
	NewYorkHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    NewYorkHouses [count] = new JButton ();
	    NewYorkHouses [count].setPreferredSize (new Dimension (13, 11));
	    NewYorkHouse.add (NewYorkHouses [count]);
	}

	JPanel NewYorkPos = new JPanel ();
	NewYorkPos.setLayout (new GridLayout (2, 1));

	NewYorkPos.add (posButtonsRed [19]);
	NewYorkPos.add (posButtonsBlue [19]);
	//Free Parking
	JPanel FreeParking = new JPanel ();
	FreeParking.add (pos [20]);

	JPanel FreeParkingPos = new JPanel ();
	FreeParkingPos.setLayout (new GridLayout (2, 1));
	posButtonsRed [20].setPreferredSize (new Dimension (14, 40));
	posButtonsBlue [20].setPreferredSize (new Dimension (14, 40));
	FreeParkingPos.add (posButtonsRed [20]);
	FreeParkingPos.add (posButtonsBlue [20]);
	//Kentucky Avenue

	JPanel KentuckyCard = new JPanel ();
	KentuckyCard.add (pos [21]);

	JPanel KentuckyHouse = new JPanel ();
	KentuckyHouse.setLayout (new GridLayout (1, 5));
	KentuckyHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    KentuckyHouses [count] = new JButton ();
	    KentuckyHouses [count].setPreferredSize (new Dimension (11, 13));
	    KentuckyHouse.add (KentuckyHouses [count]);
	}

	JPanel KentuckyPos = new JPanel ();
	KentuckyPos.setLayout (new GridLayout (1, 2));

	KentuckyPos.add (posButtonsRed [21]);
	KentuckyPos.add (posButtonsBlue [21]);



	//Chance 2
	JPanel ChanceCard2 = new JPanel ();
	ChanceCard2.add (pos [22]);

	JPanel ChanceCard2Pos = new JPanel ();
	ChanceCard2Pos.setLayout (new GridLayout (1, 2));

	ChanceCard2Pos.add (posButtonsRed [22]);
	ChanceCard2Pos.add (posButtonsBlue [22]);

	//Indiana Avenue
	JPanel IndianaCard = new JPanel ();
	IndianaCard.add (pos [23]);

	JPanel IndianaHouse = new JPanel ();
	IndianaHouse.setLayout (new GridLayout (1, 5));
	IndianaHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    IndianaHouses [count] = new JButton ();
	    IndianaHouses [count].setPreferredSize (new Dimension (11, 13));
	    IndianaHouse.add (IndianaHouses [count]);
	}
	JPanel IndianaPos = new JPanel ();
	IndianaPos.setLayout (new GridLayout (1, 2));

	IndianaPos.add (posButtonsRed [23]);
	IndianaPos.add (posButtonsBlue [23]);

	//Illinois
	JPanel IllinoisCard = new JPanel ();
	IllinoisCard.add (pos [24]);

	JPanel IllinoisHouse = new JPanel ();
	IllinoisHouse.setLayout (new GridLayout (1, 5));
	IllinoisHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    IllinoisHouses [count] = new JButton ();
	    IllinoisHouses [count].setPreferredSize (new Dimension (11, 13));
	    IllinoisHouse.add (IllinoisHouses [count]);
	}

	JPanel IllinoisPos = new JPanel ();
	IllinoisPos.setLayout (new GridLayout (1, 2));

	IllinoisPos.add (posButtonsRed [24]);
	IllinoisPos.add (posButtonsBlue [24]);

	//B&O Railroad
	JPanel B_0RRCard = new JPanel ();
	B_0RRCard.add (pos [25]);

	JPanel B_0RRPos = new JPanel ();
	B_0RRPos.setLayout (new GridLayout (1, 2));

	B_0RRPos.add (posButtonsRed [25]);
	B_0RRPos.add (posButtonsBlue [25]);

	//Atlantic
	JPanel AtlanticCard = new JPanel ();
	AtlanticCard.add (pos [26]);

	JPanel AtlanticHouse = new JPanel ();
	AtlanticHouse.setLayout (new GridLayout (1, 5));
	AtlanticHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    AtlanticHouses [count] = new JButton ();
	    AtlanticHouses [count].setPreferredSize (new Dimension (11, 13));
	    AtlanticHouse.add (AtlanticHouses [count]);
	}

	JPanel AtlanticPos = new JPanel ();
	AtlanticPos.setLayout (new GridLayout (1, 2));

	AtlanticPos.add (posButtonsRed [26]);
	AtlanticPos.add (posButtonsBlue [26]);
	//Ventnor
	JPanel VentnorCard = new JPanel ();
	VentnorCard.add (pos [27]);

	JPanel VentnorHouse = new JPanel ();
	VentnorHouse.setLayout (new GridLayout (1, 5));
	VentnorHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    VentnorHouses [count] = new JButton ();
	    VentnorHouses [count].setPreferredSize (new Dimension (11, 13));
	    VentnorHouse.add (VentnorHouses [count]);
	}

	JPanel VentnorPos = new JPanel ();
	VentnorPos.setLayout (new GridLayout (1, 2));

	VentnorPos.add (posButtonsRed [27]);
	VentnorPos.add (posButtonsBlue [27]);
	//Water Works
	JPanel WaterWorkCard = new JPanel ();
	WaterWorkCard.add (pos [28]);

	JPanel WaterWorkPos = new JPanel ();
	WaterWorkPos.setLayout (new GridLayout (1, 2));

	WaterWorkPos.add (posButtonsRed [28]);
	WaterWorkPos.add (posButtonsBlue [28]);
	//Marvin Gardens

	JPanel MarvinCard = new JPanel ();
	MarvinCard.add (pos [29]);

	JPanel MarvinHouse = new JPanel ();
	MarvinHouse.setLayout (new GridLayout (1, 5));
	MarvinHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    MarvinHouses [count] = new JButton ();
	    MarvinHouses [count].setPreferredSize (new Dimension (11, 13));
	    MarvinHouse.add (MarvinHouses [count]);
	}

	JPanel MarvinPos = new JPanel ();
	MarvinPos.setLayout (new GridLayout (1, 2));

	MarvinPos.add (posButtonsRed [29]);
	MarvinPos.add (posButtonsBlue [29]);

	//Go to Jail
	JPanel GotoJailCard = new JPanel ();
	GotoJailCard.add (pos [30]);

	JPanel GotoJailCardPos = new JPanel ();
	GotoJailCardPos.setLayout (new GridLayout (2, 1));
	posButtonsRed [30].setPreferredSize (new Dimension (14, 40));
	posButtonsBlue [30].setPreferredSize (new Dimension (14, 40));
	GotoJailCardPos.add (posButtonsRed [30]);
	GotoJailCardPos.add (posButtonsBlue [30]);


	//Pacific Avenue
	JPanel PacificAvenueCard = new JPanel ();
	PacificAvenueCard.add (pos [31]);

	JPanel PacificAvenueHouse = new JPanel ();
	PacificAvenueHouse.setLayout (new GridLayout (5, 1));
	PacificAvenueHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    PacificAvenueHouses [count] = new JButton ();
	    PacificAvenueHouses [count].setPreferredSize (new Dimension (13, 11));
	    PacificAvenueHouse.add (PacificAvenueHouses [count]);
	}

	JPanel PacificAvenuepos = new JPanel ();
	PacificAvenuepos.setLayout (new GridLayout (2, 1));
	PacificAvenuepos.add (posButtonsRed [31]);
	PacificAvenuepos.add (posButtonsBlue [31]);


	//North Carolina
	JPanel NorthCarolinaCard = new JPanel ();
	NorthCarolinaCard.add (pos [32]);

	JPanel NorthCarolinaHouse = new JPanel ();
	NorthCarolinaHouse.setLayout (new GridLayout (5, 1));
	NorthCarolinaHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    NorthCarolinaHouses [count] = new JButton ();
	    NorthCarolinaHouses [count].setPreferredSize (new Dimension (13, 11));
	    NorthCarolinaHouse.add (NorthCarolinaHouses [count]);
	}

	JPanel NorthCarolinapos = new JPanel ();
	NorthCarolinapos.setLayout (new GridLayout (2, 1));
	NorthCarolinapos.add (posButtonsRed [32]);
	NorthCarolinapos.add (posButtonsBlue [32]);




	//Community Chest
	JPanel CCCard3 = new JPanel ();
	CCCard3.add (pos [33]);

	JPanel CCCard3pos = new JPanel ();
	CCCard3pos.setLayout (new GridLayout (2, 1));
	CCCard3pos.add (posButtonsRed [33]);
	CCCard3pos.add (posButtonsBlue [33]);



	//Pennsylvania pos = 34
	JPanel PennsylvaniaAveCard = new JPanel ();
	PennsylvaniaAveCard.add (pos [34]);

	JPanel PennsylvaniaAveHouse = new JPanel ();
	PennsylvaniaAveHouse.setLayout (new GridLayout (5, 1));
	PennsylvaniaAveHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    PennsylvaniaAveHouses [count] = new JButton ();
	    PennsylvaniaAveHouses [count].setPreferredSize (new Dimension (13, 11));
	    PennsylvaniaAveHouse.add (PennsylvaniaAveHouses [count]);
	}

	JPanel PennsylvaniaAvepos = new JPanel ();
	PennsylvaniaAvepos.setLayout (new GridLayout (2, 1));
	PennsylvaniaAvepos.add (posButtonsRed [34]);
	PennsylvaniaAvepos.add (posButtonsBlue [34]);


	//Short Line pos = 35
	JPanel shortlineRR = new JPanel ();
	shortlineRR.add (pos [35]);

	//Short Line pos = 35
	JPanel shortlineRRPos = new JPanel ();
	shortlineRRPos.setLayout (new GridLayout (2, 1));
	shortlineRRPos.add (posButtonsRed [35]);
	shortlineRRPos.add (posButtonsBlue [35]);



	//Chance- Pos = 36
	JPanel ChanceCard3 = new JPanel ();
	ChanceCard3.add (pos [36]);

	JPanel ChanceCard3Pos = new JPanel ();
	ChanceCard3Pos.setLayout (new GridLayout (2, 1));
	ChanceCard3Pos.add (posButtonsRed [36]);
	ChanceCard3Pos.add (posButtonsBlue [36]);

	//Park Place Card
	JPanel parkPlaceCard = new JPanel ();
	parkPlaceCard.add (pos [37]);

	JPanel parkPlaceHouse = new JPanel ();
	parkPlaceHouse.setLayout (new GridLayout (5, 1));
	JButton parkPlaceHouses[] = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    parkPlaceHouses [count] = new JButton ();
	    parkPlaceHouses [count].setPreferredSize (new Dimension (13, 11));
	    parkPlaceHouse.add (parkPlaceHouses [count]);
	}

	JPanel parkPlacepos = new JPanel ();
	parkPlacepos.setLayout (new GridLayout (2, 1));

	parkPlacepos.add (posButtonsRed [37]);
	parkPlacepos.add (posButtonsBlue [37]);

	//luxury tax
	JPanel LuxuryTaxCard = new JPanel ();
	LuxuryTaxCard.add (pos [38]);

	//Luxury Tax pos = 38
	JPanel LuxuryTaxPos = new JPanel ();
	LuxuryTaxPos.setLayout (new GridLayout (2, 1));

	LuxuryTaxPos.add (posButtonsRed [38]);
	LuxuryTaxPos.add (posButtonsBlue [38]);



	//boardwalk card
	JPanel boardwalkCard = new JPanel ();
	boardwalkCard.add (pos [39]);

	JPanel boardwalkHouse = new JPanel ();
	boardwalkHouse.setLayout (new GridLayout (5, 1));
	boardwalkHouses = new JButton [5];
	for (int count = 0 ; count < 5 ; count++)
	{
	    boardwalkHouses [count] = new JButton ();
	    boardwalkHouses [count].setPreferredSize (new Dimension (13, 11));
	    boardwalkHouse.add (boardwalkHouses [count]);
	}

	JPanel boardwalkPos = new JPanel ();
	boardwalkPos.setLayout (new GridLayout (2, 1));

	boardwalkPos.add (posButtonsRed [39]);
	boardwalkPos.add (posButtonsBlue [39]);

	//Text Areas and Button Panels
	JPanel textArea = new JPanel ();
	//text area panel for ingame commentary
	//text area
	commentary = new JTextArea ("Welcome to Monopoly, this is where the in-game commentary will happen\nAny action you perform (Rolling, Upgrading or Buying) must follow with an End Turn.\nThe game has no color sets, as this is the express version.\nNo other actions before rolling on your turn.\nThe orange buttons are for Jail.\nLet the games begin!", 30, 30);
	JScrollPane scrollPane = new JScrollPane (commentary); //adding a scroll pane
	commentary.setLineWrap (true);        // causes the lines to wrap around; word may be split
	commentary.setWrapStyleWord (true); //
	textArea.add (scrollPane);
	commentary.setEditable (false); //making it uneditable

	JPanel buttonPanel = new JPanel (); //adding the button panel
	buttonPanel.setLayout (new GridLayout (2, 2)); //setting the layout
	JButton rollButton = new JButton ("ROLL");
	JButton upgButton = new JButton ("UPGRADE");
	JButton buyButton = new JButton ("BUY");
	JButton EndTurnButton = new JButton ("END TURN");
	buttonPanel.add (rollButton); //adding buttons to panel
	buttonPanel.add (upgButton);
	buttonPanel.add (buyButton);
	buttonPanel.add (EndTurnButton);


	JPanel moneyPanel = new JPanel (); //adding a panel to show P1 and P2 money
	moneyPanel.setLayout (new GridLayout (1, 2));
	JButton moneyButton = new JButton (p1Money + "");
	moneyButton.setBackground (Color.red); //changing coulour to show p1 money and p2 money
	JButton money2Button = new JButton (p2Money + "");
	money2Button.setBackground (Color.blue);

	JPanel p1Props = new JPanel (); //adding text areas for p1 and p2 propeties they buy
	p1List = new JTextArea ("Properties List For: " + p1Name, 3, 15);
	JScrollPane scrollList1 = new JScrollPane (p1List);
	p1List.setLineWrap (true);        // causes the lines to wrap around; word may be split
	p1List.setWrapStyleWord (true);
	p1Props.add (scrollList1);
	p1List.setEditable (false);


	JPanel p2Props = new JPanel ();
	p2List = new JTextArea ("Properties List For: " + p2Name, 3, 15);
	JScrollPane scrollList2 = new JScrollPane (p2List);
	p2List.setLineWrap (true);        // causes the lines to wrap around; word may be split
	p2List.setWrapStyleWord (true);
	p2Props.add (scrollList2);
	p2List.setEditable (false);

	JPanel jailButtons = new JPanel ();   //special buttons for when either player goes to jail
	jailButtons.setLayout (new GridLayout (1, 3));
	JButton jRoll = new JButton ("JAIL-ROLL");
	jRoll.setBackground (Color.orange);
	JButton jPay = new JButton ("PAY-$50");
	jPay.setBackground (Color.orange);
	JButton jEndTurn = new JButton ("Wait Turn");
	jEndTurn.setBackground (Color.orange);
	jailButtons.add (jRoll); //adding those buttons to panel
	jailButtons.add (jPay);
	jailButtons.add (jEndTurn);

	moneyPanel.add (moneyButton);
	moneyPanel.add (money2Button);

	//GO panel, pos = 0
	constraints.gridx = 12; //constraints allows us to place certain components in certin spots
	constraints.gridy = 20; //started with the pos (12,20), at GO and as we worked inwards changed the x or y depending on the space
	panel.add (GoPanel, constraints); //adding the GO card to the panel with the gridBag layout

	constraints.gridx = 12;
	constraints.gridy = 21; //adding the position marker one unit below the GO panel

	panel.add (GOPos, constraints); //adding it to the main gridbag panel

	//mediterranean player pos = 1

	constraints.gridx = 11; //same logic as GO Panel
	constraints.gridy = 20;
	panel.add (mediterraneanCard, constraints);

	constraints.gridx = 11;
	constraints.gridy = 21;
	panel.add (mediterraneanPos, constraints);

	constraints.gridx = 11; //but houses are 2 units underneath the card panel
	constraints.gridy = 22;
	panel.add (mediterraneanHouse, constraints);
	//REPEATED process for all the spaces, if they have no houses, the third section is not included
	//community chest pos = 2

	constraints.gridx = 10;
	constraints.gridy = 20;

	panel.add (CCCard, constraints);

	constraints.gridx = 10;
	constraints.gridy = 21;

	panel.add (CCPos, constraints);

	//baltic card - pos = 3

	constraints.gridx = 9;
	constraints.gridy = 20;
	panel.add (balticCard, constraints);

	constraints.gridx = 9;
	constraints.gridy = 21;
	panel.add (balticPos, constraints);

	constraints.gridx = 9;
	constraints.gridy = 22;
	panel.add (balticHouse, constraints);

	// //income tax - pos = 4
	constraints.gridx = 8;
	constraints.gridy = 20;
	panel.add (IncomeTax, constraints);

	constraints.gridx = 8;
	constraints.gridy = 21;

	panel.add (ITPos, constraints);

	//reading railroad - pos = 5
	constraints.gridx = 7;
	constraints.gridy = 20;

	panel.add (ReadingRR, constraints);

	constraints.gridx = 7;
	constraints.gridy = 21;

	panel.add (ReadingRRPos, constraints);

	//
	// //oriental avenue - pos = 6
	constraints.gridx = 6;
	constraints.gridy = 20;
	panel.add (orientalCard, constraints);

	constraints.gridx = 6;
	constraints.gridy = 21;
	panel.add (orientalPos, constraints);


	constraints.gridx = 6;
	constraints.gridy = 22;
	panel.add (OrientalHouse, constraints);
	//
	// //chance card - pos = 7
	constraints.gridx = 5;
	constraints.gridy = 20;

	panel.add (ChanceCard, constraints);

	constraints.gridx = 5;
	constraints.gridy = 21;

	panel.add (ChancePos, constraints);

	// //oriental - pos = 8
	constraints.gridx = 4;
	constraints.gridy = 20;
	panel.add (vermontCard, constraints);

	constraints.gridx = 4;
	constraints.gridy = 21;
	panel.add (vermontPos, constraints);


	constraints.gridx = 4;
	constraints.gridy = 22;
	panel.add (VermontHouse, constraints);

	// connecticut - pos = 9
	constraints.gridx = 3;
	constraints.gridy = 20;
	panel.add (connecticutCard, constraints);

	constraints.gridx = 3;
	constraints.gridy = 21;
	panel.add (connecticutPos, constraints);


	constraints.gridx = 3;
	constraints.gridy = 22;
	panel.add (connecticutHouse, constraints);

	// //Jail - pos = 10
	constraints.gridx = 2;
	constraints.gridy = 20;
	panel.add (JailCard, constraints);

	constraints.gridx = 2;
	constraints.gridy = 21;

	panel.add (JailPos, constraints);

	//St Charles - pos = 11
	constraints.gridx = 2;
	constraints.gridy = 19;
	panel.add (StCharlesCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 19;
	panel.add (StCharlesPos, constraints);


	constraints.gridx = 0;
	constraints.gridy = 19;
	panel.add (StCharlesHouse, constraints);

	//Electric Company
	constraints.gridx = 2;
	constraints.gridy = 18;
	panel.add (ElectricCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 18;
	panel.add (ElectricPos, constraints);

	//States Avenue
	constraints.gridx = 2;
	constraints.gridy = 17;
	panel.add (StatesAveCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 17;
	panel.add (StatesAvePos, constraints);


	constraints.gridx = 0;
	constraints.gridy = 17;
	panel.add (StatesAveHouse, constraints);
	//Virigina Avenue
	constraints.gridx = 2;
	constraints.gridy = 16;
	panel.add (VirginiaAveCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 16;
	panel.add (VirginiaAvePos, constraints);


	constraints.gridx = 0;
	constraints.gridy = 16;
	panel.add (VirginiaAveHouse, constraints);
	//Pennsylvania railroad
	constraints.gridx = 2;
	constraints.gridy = 15;
	panel.add (PennsylvaniaRRCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 15;
	panel.add (PennsylvaniaRRPos, constraints);
	//St James
	constraints.gridx = 2;
	constraints.gridy = 14;
	panel.add (StJamesCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 14;
	panel.add (StJamesPos, constraints);

	constraints.gridx = 0;
	constraints.gridy = 14;
	panel.add (StJamesHouse, constraints);
	//Community Chest 2
	constraints.gridx = 2;
	constraints.gridy = 13;
	panel.add (CCCard2, constraints);

	constraints.gridx = 1;
	constraints.gridy = 13;
	panel.add (CCCard2Pos, constraints);
	//Tennesse Avenue
	constraints.gridx = 2;
	constraints.gridy = 12;
	panel.add (TennesseCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 12;
	panel.add (TennessePos, constraints);

	constraints.gridx = 0;
	constraints.gridy = 12;
	panel.add (TennesseHouse, constraints);
	//New York Avenue
	constraints.gridx = 2;
	constraints.gridy = 11;
	panel.add (NewYorkCard, constraints);

	constraints.gridx = 1;
	constraints.gridy = 11;
	panel.add (NewYorkPos, constraints);

	constraints.gridx = 0;
	constraints.gridy = 11;
	panel.add (NewYorkHouse, constraints);
	//Free Parking
	constraints.gridx = 2;
	constraints.gridy = 10;

	panel.add (FreeParking, constraints);

	constraints.gridx = 1;
	constraints.gridy = 10;

	panel.add (FreeParkingPos, constraints);
	//Kentucky Avenue

	constraints.gridx = 3;
	constraints.gridy = 10;

	panel.add (KentuckyCard, constraints);

	constraints.gridx = 3;
	constraints.gridy = 9;

	panel.add (KentuckyPos, constraints);

	constraints.gridx = 3;
	constraints.gridy = 8;
	panel.add (KentuckyHouse, constraints);


	//Chance 2
	constraints.gridx = 4;
	constraints.gridy = 10;

	panel.add (ChanceCard2, constraints);

	constraints.gridx = 4;
	constraints.gridy = 9;

	panel.add (ChanceCard2Pos, constraints);

	//Indiana Avenue
	constraints.gridx = 5;
	constraints.gridy = 10;
	panel.add (IndianaCard, constraints);

	constraints.gridx = 5;
	constraints.gridy = 9;
	panel.add (IndianaPos, constraints);

	constraints.gridx = 5;
	constraints.gridy = 8;
	panel.add (IndianaHouse, constraints);

	//Illinois
	constraints.gridx = 6;
	constraints.gridy = 10;
	panel.add (IllinoisCard, constraints);

	constraints.gridx = 6;
	constraints.gridy = 9;
	panel.add (IllinoisPos, constraints);

	constraints.gridx = 6;
	constraints.gridy = 8;
	panel.add (IllinoisHouse, constraints);
	//B&O Railroad
	constraints.gridx = 7;
	constraints.gridy = 10;
	panel.add (B_0RRCard, constraints);

	constraints.gridx = 7;
	constraints.gridy = 9;
	panel.add (B_0RRPos, constraints);
	//Atlantic
	constraints.gridx = 8;
	constraints.gridy = 10;
	panel.add (AtlanticCard, constraints);

	constraints.gridx = 8;
	constraints.gridy = 9;
	panel.add (AtlanticPos, constraints);

	constraints.gridx = 8;
	constraints.gridy = 8;
	panel.add (AtlanticHouse, constraints);
	//Ventnor
	constraints.gridx = 9;
	constraints.gridy = 10;
	panel.add (VentnorCard, constraints);

	constraints.gridx = 9;
	constraints.gridy = 9;
	panel.add (VentnorPos, constraints);

	constraints.gridx = 9;
	constraints.gridy = 8;
	panel.add (VentnorHouse, constraints);

	//Water Works
	constraints.gridx = 10;
	constraints.gridy = 10;
	panel.add (WaterWorkCard, constraints);

	constraints.gridx = 10;
	constraints.gridy = 9;
	panel.add (WaterWorkPos, constraints);
	//Marvin Gardens
	constraints.gridx = 11;
	constraints.gridy = 10;
	panel.add (MarvinCard, constraints);

	constraints.gridx = 11;
	constraints.gridy = 9;
	panel.add (MarvinPos, constraints);

	constraints.gridx = 11;
	constraints.gridy = 8;
	panel.add (MarvinHouse, constraints);



	//Go to Jail
	constraints.gridx = 12;
	constraints.gridy = 10;
	panel.add (GotoJailCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 10;
	panel.add (GotoJailCardPos, constraints);




	//Pacific Avenue
	constraints.gridx = 12;
	constraints.gridy = 11;
	panel.add (PacificAvenueCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 11;
	panel.add (PacificAvenuepos, constraints);

	constraints.gridx = 14;
	constraints.gridy = 11;
	panel.add (PacificAvenueHouse, constraints);




	//North Carolina
	constraints.gridx = 12;
	constraints.gridy = 12;
	panel.add (NorthCarolinaCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 12;
	panel.add (NorthCarolinapos, constraints);

	constraints.gridx = 14;
	constraints.gridy = 12;
	panel.add (NorthCarolinaHouse, constraints);

	//CC Card 3
	constraints.gridx = 12;
	constraints.gridy = 13;
	panel.add (CCCard3, constraints);

	constraints.gridx = 13;
	constraints.gridy = 13;
	panel.add (CCCard3pos, constraints);





	//pennsylvania avenue
	constraints.gridx = 12;
	constraints.gridy = 14;
	panel.add (PennsylvaniaAveCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 14;
	panel.add (PennsylvaniaAvepos, constraints);

	constraints.gridx = 14;
	constraints.gridy = 14;
	panel.add (PennsylvaniaAveHouse, constraints);


	//short line - pos 35
	constraints.gridx = 12;
	constraints.gridy = 15;

	panel.add (shortlineRR, constraints);

	constraints.gridx = 13;
	constraints.gridy = 15;

	panel.add (shortlineRRPos, constraints);


	// //Chance 3
	constraints.gridx = 12;
	constraints.gridy = 16;

	panel.add (ChanceCard3, constraints);

	constraints.gridx = 13;
	constraints.gridy = 16;

	panel.add (ChanceCard3Pos, constraints);
	//
	//
	// //park place
	constraints.gridx = 12;
	constraints.gridy = 17;
	panel.add (parkPlaceCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 17;
	panel.add (parkPlacepos, constraints);

	constraints.gridx = 14;
	constraints.gridy = 17;
	panel.add (parkPlaceHouse, constraints);
	//
	// //luxury tax
	constraints.gridx = 12;
	constraints.gridy = 18;
	panel.add (LuxuryTaxCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 18;
	panel.add (LuxuryTaxPos, constraints);




	//BoardWalk, pos = 39
	constraints.gridx = 12;
	constraints.gridy = 19;
	panel.add (boardwalkCard, constraints);

	constraints.gridx = 13;
	constraints.gridy = 19;
	panel.add (boardwalkPos, constraints);

	constraints.gridx = 14;
	constraints.gridy = 19;
	panel.add (boardwalkHouse, constraints);
	//Text Area and Buttons

	constraints.gridx = 25;
	constraints.gridy = 10;
	constraints.gridheight = 20;
	constraints.gridwidth = 2;
	panel.add (textArea, constraints);

	constraints.gridx = 25;
	constraints.gridy = 20;
	panel.add (buttonPanel, constraints);

	constraints.gridx = 25;
	constraints.gridy = 21;
	panel.add (jailButtons, constraints);


	constraints.gridx = 25;
	constraints.gridy = 18;
	panel.add (moneyPanel, constraints);


	posButtonsRed [p1Pos].setBackground (Color.red);
	posButtonsBlue [p2Pos].setBackground (Color.blue);
	MainPanel.add (panel);


	JPanel DeedCard = new JPanel (); //add a blank image over the spot of where the title deed cards are supposed to be, because players start on GO, so its going to be blank
	JLabel posMiddle = new JLabel (backDeed);
	DeedCard.add (posMiddle);
	constraints.gridx = 6;
	constraints.gridy = 15;
	constraints.gridwidth = 3;
	panel.add (DeedCard, constraints);
	MainPanel.add (panel);


	JPanel ChestCard = new JPanel (); //Adding the back photo the community chest to the panel
	JLabel posChest = new JLabel (backChest);
	ChestCard.add (posChest);
	constraints.gridx = 9;
	constraints.gridy = 12;
	constraints.gridwidth = 3; //since any images placed determine the length or the width of the column or row respectively, so I had to add this to say that this panel will take up 3 columns
	panel.add (ChestCard, constraints);
	MainPanel.add (panel);




	JPanel ChanceCardA = new JPanel (); //did same thing with Chance
	JLabel posChance = new JLabel (backChance);
	ChanceCardA.add (posChance);
	constraints.gridx = 3;
	constraints.gridy = 12;
	constraints.gridwidth = 3;
	panel.add (ChanceCardA, constraints);
	MainPanel.add (panel);

	constraints.gridx = 3;
	constraints.gridy = 3; //added the properties list for both players above the chest and community cheest cards
	panel.add (p1Props, constraints);
	MainPanel.add (panel);

	constraints.gridx = 9;
	constraints.gridy = 3;
	panel.add (p2Props, constraints);
	
	MainPanel.add (panel);
	frame.setVisible (true);
	while (p1Money >= 0 && p2Money >= 0) //loop will run as long as both players arent bankrupt
	{
	    if (p1Money < 0 || p2Money < 0) //if any player is bankrupt, game end
	    {
		break;
	    }
	    p1Rights = false; //before they roll they cannot purchase or upgrade anything
	    p2Rights = false;
	    jp1Rights = true;
	    jp2Rights = true;
	    moneyButton.setText (p1Money + ""); //showcase updated money
	    money2Button.setText (p2Money + "");

	    if (turn == true) //turn = true means its player 1'a turn
	    {

		if (p1Jail == true) //first check if they are in jail
		{
		    word = commentary.getText ();
		    commentary.setText (word + "\n\n" + p1Name + ": You are in Jail,(Use the orange buttons)\nAny action(Jail-Roll or Pay-$50) must be followed by a wait turn.\n\nThe \"Jail-Roll\" button allows you to roll a double and get out of jail for free on your next turn\n\nThe \"Pay-$50\" button allows you to get out of jail by paying $50 on your next turn.\n\nThe \"Wait-Turn\" button allows you to wait 3 turns and get out of jail for free.\n\nWhatever you chose, you must click \"wait-turn\" at the end.(Unless you clicked wait turn)");

		    while (turn == true) //as long its pl 1 and they are in jail, only give them the options of Jail buttons
		    {
			jailallowed = true; //p1 turn so allowed is true
			jRoll.addActionListener (this);
			jPay.addActionListener (this);
			jEndTurn.addActionListener (this);
			player2Roll = true;
			if (turn == false) //once they click the wait turn button, there turn is over so once that happens we break out of loop
			{
			    break;
			}
		    }


		}
		else //if player 1 is not in jail
		{

		    p1flag = false; //Rolling will only work if p1 flag is false
		    while (player1Roll == true) //as long as tjhe player can roll
		    {
			rollButton.addActionListener (this); //once the roll button is clicked
		    }

		    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY); //take the current position back to normal
		    p1Pos += dice; //add the dice roll the position
		    
		    if (p1Pos == 30) //if they land on the GO-TO-JAIl
		    {
			word = commentary.getText (); //take existing text
			commentary.setText (word + "\n" + p1Name + ": You have been sent to Jail, your turn automatically ends"); //updating the commentary field
			p1Pos = 10; //they get sent to Jail
			if (p1jailCard == false) //if they dont have a get out of jail free card
			{
			    player2Roll = true; //player 2 can roll
			    p1Jail = true; //player one is in jail
			    turn = false; //player 1 flag ends immedieatly
			    p2flag = true; //rolling for pplayer 2 will only work if player 2 flag is false, this avoid pl1 and pl2 rolling at the same time
			}
			else //if they do, they are in just visiting
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\n" + p1Name + ": You would have been sent to Jail, but you had a Get-Out-of-Jail free card, so you are just visiting");
			    p1Jail = false; //removes their jail status
			    p1jailCard = false; //takes away their card

			}

		    }

		    if (p1Pos >= 40) // if the player passes GO(G0 - 0, Boardwalk - 39)
		    {
			p1Pos -= 40;
			p1Money += 200; //add 200
			word = commentary.getText ();
			commentary.setText (word + "\n" + p1Name + " Passed GO, Collect $200");
		    }
		    posButtonsRed [p1Pos].setBackground (Color.red); //whatever the pos is from above, color in the next position

		    if (p1Pos == 7 || p1Pos == 22 || p1Pos == 36) //IF they land on a  chance position on the board.Chance Start
		    {
			int randChoice = (int) (Math.random () * 10) + 1; //random number
			randChoice -= 1;
			if (randChoice == 0) //Advance to boardwalk
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Advance to Boardwalk");
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY); //moves player to boardwalk
			    p1Pos = 39;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			}
			else if (randChoice == 1) //Advance to GO
			{
			    p1Money += 200; //B
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Advance to GO, collect $200");
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY); //Moves to GO
			    p1Pos = 0;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			}
			else if (randChoice == 2) //Advance to Illinois Avenue Chance
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Advance to Illinois Avenue, if you pass GO, collect $200");
			    if (p1Pos >= 24) //moves to illinois avenue, if they are ahead of it, they get 200
			    {
				p1Money += 200;
			    }
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos = 24;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			}
			else if (randChoice == 3) //Advance to ST.Charles
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Advance to St.Charles Place, if you pass GO, collect $200");
			    if (p1Pos >= 11)
			    {
				p1Money += 200;
			    }
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos = 11;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			}
			else if (randChoice == 4) //Back Three Spaces
			{
			    //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance:" + p1Name + ": Move Back 3 Spaces");
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos -= 3;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			}
			else if (randChoice == 5) //Chairman
			{

			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Elected chairman of the board, pay $50 to other player");
			    p1Money -= 50;
			    p2Money += 50; //pay everyone 50 dollars from your account
			}

			else if (randChoice == 6) //Dividend
			{

			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Bank pays you divdend, collect $50");
			    p1Money += 50;
			}
			else if (randChoice == 7) //Go tO Jail
			{
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos = 10;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Go to Jail");
			    if (p1jailCard == true) //if they have a get out of jail free card
			    {
				p1Jail = false; //dont sent to jail
				p1jailCard = false;
			    }
			    else
			    {
				word = commentary.getText ();
				commentary.setText (word + "\n" + p1Name + ": You have been sent to Jail, your turn automatically ends"); //if they dont, they arte in jail, similar to above
				player2Roll = true;
				p1Jail = true;
				turn = false;
				p2flag = true;
			    }

			    //p1inJail = true; //B
			}
			else if (randChoice == 8) //Loan Matures
			{
			    p1Money += 150;
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Loan Matures, collect S150");
			}
			else if (randChoice == 9) //Out of Jail Free card, will be used right away
			{
			    p1jailCard = true; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p1Name + ": Get out of Jail Free Card(will be used at first instance of jail");

			}

			ChanceCardA = new JPanel (); //creating a panel to add the actual chance card
			posChance = new JLabel (Chance [randChoice]); //creating a label with the images file of all the chance as the randChoice as index to ensure card and action are the same
			ChanceCardA.add (posChance); //add the card to the panel
			constraints.gridx = 3;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			panel.add (ChanceCardA, constraints); //add the chance card
			MainPanel.add (panel);
			frame.setVisible (true);
		    } //Chancce End
		    if (p1Pos == 2 || p1Pos == 17 || p1Pos == 33) //Community Chest Start
		    {
			int randChoice = (int) (Math.random () * 13) + 1; //same as the chance just different actions, jail and out of jail free cards are the same
			randChoice -= 1;
			if (randChoice == 0) //Bank Error
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Bank Error in your favour, collect $200");
			    p1Money += 200; //A

			}
			else if (randChoice == 1) //Beauty Contest
			{
			    p1Money += 10; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": You placed second place in a beauty contest, collect $10");
			}
			else if (randChoice == 2) //Advance to go
			{
			    p1Money += 200; //B
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Advance to GO, collect $200");
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos = 0;
			    posButtonsRed [p1Pos].setBackground (Color.red);


			}
			else if (randChoice == 3) //Consultancy Fee
			{
			    p1Money += 25; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Receive for service, collect $25");
			}
			else if (randChoice == 4) //Doctor Fee
			{
			    p1Money -= 50; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Doctor's fee, pay $50");
			}
			else if (randChoice == 5) //Get Out of Jail
			{
			    p1jailCard = true; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Get out of Jail Free Card(will be used at first instance of jail");
			}
			else if (randChoice == 6) //Go to Jail Community Chest
			{
			    posButtonsRed [p1Pos].setBackground (Color.LIGHT_GRAY);
			    p1Pos = 10;
			    posButtonsRed [p1Pos].setBackground (Color.red);
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Go to Jail");
			    if (p1jailCard == true)
			    {
				p1Jail = false;
				p1jailCard = false;
			    }


			    else
			    {
				word = commentary.getText ();
				commentary.setText (word + "\n" + p1Name + ": You have been sent to Jail, your turn automatically ends");
				player2Roll = true;
				p1Jail = true;
				turn = false;
				p2flag = true;

			    }


			}
			else if (randChoice == 7) //Holiday Fund Matures
			{
			    p1Money += 100; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Holiday Fund Matures, collect S100");
			}
			else if (randChoice == 8) //Income Tax
			{
			    p1Money += 20;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Income Tax Refund, collect S20");
			}
			else if (randChoice == 9) //Inherit 100
			{
			    p1Money += 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Inherit $100");

			}
			else if (randChoice == 10) //Life Insurance
			{
			    p1Money += 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Life Insurance matures, collect $100");
			}
			else if (randChoice == 11) //Pay Hospital
			{
			    p1Money -= 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": Hospital fees, pay $100");

			}
			else if (randChoice == 12) //Sale of Stock
			{
			    p1Money += 45;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest:" + p1Name + ": From Sales of Stock, collect $45");
			}
			ChestCard = new JPanel ();
			posChest = new JLabel (Chest [randChoice]); //adding the card same way as chance
			ChestCard.add (posChest);
			constraints.gridx = 9;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			panel.add (ChestCard, constraints);
			MainPanel.add (panel);
			frame.setVisible (true);
		    } //Community Chest end
		    if (p1Pos == 4) //if they land on income tax
		    {
			p1Money -= 200;
			word = commentary.getText ();
			commentary.setText (word + "\n" + p1Name + " Income Tax, pay $200");

		    }
		    if (p1Pos == 38) //if they land on luxury tax
		    {
			p1Money -= 100;
			word = commentary.getText ();
			commentary.setText (word + "\n" + p1Name + " Luxury Tax, pay $100");

		    }
		    if (p1Pos != 5 && p1Pos != 15 && p1Pos != 25 && p1Pos != 35 && p1Pos != 12 && p1Pos != 28) //Checking if player lands on a property
		    {
			if (buyableProperties [p1Pos] == false) //checking if its owned or not
			{

			    String p1Check[] = new String [player2Assets.size ()]; //checking player 2's vector to see if they own the property
			    for (int count = 0 ; count < p1Check.length ; count++)
			    {
				p1Check [count] = (String) player2Assets.elementAt (count);
			    }
			    MySorts ms = new MySorts ();
			    int found = ms.LinearSearch (p1Check, properties [p1Pos]);
			    if (found >= 0)
			    {

				p1Money -= rentData [p1Pos] [propertyUpgrades [p1Pos]]; //if they do, using the 2d array, subtract the rent, row is the position p1 is on, and column is the rent
				p2Money += rentData [p1Pos] [propertyUpgrades [p1Pos]];
				word = commentary.getText ();
				commentary.setText (word + "\n " + p1Name + " pays rent to " + p2Name + " at " + p1Check [found] + " for " + (rentData [p1Pos] [propertyUpgrades [p1Pos]]));
			    }
			}
		    }
		    if (p1Pos == 5 || p1Pos == 15 || p1Pos == 25 || p1Pos == 35) //if p1 lands on a railroad owned by p2
		    {
			String p1Check[] = new String [player2Assets.size ()];
			for (int count = 0 ; count < p1Check.length ; count++)
			{
			    p1Check [count] = (String) player2Assets.elementAt (count);
			}
			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p1Check, properties [p1Pos]); //check if they own it
			if (found >= 0)
			{
			    int total = 25;
			    for (int count = 1 ; count <= p1numRR ; count++) //depending on the number of railraod we double the rent each time
			    {
				total = total * 2;
			    }
			    p1Money -= total;
			    p2Money += total; //p1 gives p2 rent
			    word = commentary.getText ();
			    commentary.setText (word + "\n " + p1Name + " pays rent to " + p2Name + " at " + p1Check [found] + " for " + total);
			    total = 25;

			}

		    }
		    if (p1Pos == 12 || p1Pos == 28) //if they land on utilites
		    {
			String p1Check[] = new String [player2Assets.size ()];
			for (int count = 0 ; count < p1Check.length ; count++)
			{
			    p1Check [count] = (String) player2Assets.elementAt (count);
			}
			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p1Check, properties [p1Pos]); //check if they own it
			if (found >= 0)
			{
			    int utilityDice = diceRoll (); //get a random number

			    if (p2numUtilities == 1) //if p2 owns 1, multiply by 4
			    {
				utilityDice *= 4;

			    }
			    if (p2numUtilities == 2) //if p2 owns 2, multiply by 10
			    {
				utilityDice *= 10;
			    }
			    p1Money -= utilityDice; //give rent
			    p2Money += utilityDice;
			    word = commentary.getText ();
			    commentary.setText (word + "\n " + p1Name + " pays rent to " + p2Name + " at " + p1Check [found] + " for " + utilityDice);
			}

		    }

		    DeedCard = new JPanel (); //add the image of the property player 1 land on, the title deed
		    posMiddle = new JLabel (titleDeedCards [p1Pos]);

		    DeedCard.add (posMiddle);
		    constraints.gridx = 6;
		    constraints.gridy = 15;
		    constraints.gridwidth = 3;
		    panel.add (DeedCard, constraints);
		    MainPanel.add (panel);
		    frame.setVisible (true);

		    while (turn == true) //as long as its player 1's turn
		    {
			buyButton.addActionListener (this); //give them option to buy, upgrade, or end turn
			upgButton.addActionListener (this);
			EndTurnButton.addActionListener (this);


			player2Roll = true; //to activate p2 ability to roll


			if (turn == false) //once they end turn, chance and community chest are returned back to the back side of the card and the title deed card is blank before the next players turn
			{
			    DeedCard = new JPanel ();
			    posMiddle = new JLabel (backDeed);
			    DeedCard.add (posMiddle);
			    constraints.gridx = 6;
			    constraints.gridy = 15;
			    constraints.gridwidth = 3;
			    panel.add (DeedCard, constraints);
			    MainPanel.add (panel);
			    frame.setVisible (true);


			    ChestCard = new JPanel ();
			    posChest = new JLabel (backChest);
			    ChestCard.add (posChest);
			    constraints.gridx = 9;
			    constraints.gridy = 12;
			    constraints.gridwidth = 3;
			    panel.add (ChestCard, constraints);
			    MainPanel.add (panel);
			    frame.setVisible (true);

			    ChanceCardA = new JPanel ();
			    posChance = new JLabel (backChance);
			    ChanceCardA.add (posChance);
			    constraints.gridx = 3;
			    constraints.gridy = 12;
			    constraints.gridwidth = 3;
			    panel.add (ChanceCardA, constraints);
			    MainPanel.add (panel);
			    frame.setVisible (true);
			    break;
			}

		    }
		}
	    }

	    else //All of player 1's options are mirrored in player 2's  rolling
	    {
		if (p2Jail == true)
		{
		    word = commentary.getText ();
		    commentary.setText (word + "\n\n" + p2Name + ": You are in Jail,(Use the orange buttons)\n\nAny action(Jail-Roll or Pay-$50) must be followed by a wait turn.\nThe \"Jail-Roll\" button allows you to roll a double and get out of jail for free on your next turn\n\nThe \"Pay-$50\" button allows you to get out of jail by paying $50 on your next turn.\n\nThe \"Wait-Turn\" button allows you to wait 3 turns and get out of jail for free.\n\nWhatever you chose, you must click \"wait-turn\" at the end.(Unless you clicked wait turn)");


		    while (turn == false)
		    {
			jailallowed = false; //p2 turn so its false for p2
			jRoll.addActionListener (this);
			jPay.addActionListener (this);
			jEndTurn.addActionListener (this);
			player1Roll = true;
			if (turn == true)
			{
			    break;
			}
		    }
		}
		else
		{
		    p2flag = false;
		    while (player2Roll == true)
		    {
			rollButton.addActionListener (this);
		    }



		    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
		    p2Pos += dice;
		    
		    if (p2Pos == 30)
		    {
			p2Pos = 10;
			if (p2jailCard == false)
			{
			    p2Jail = true;
			    player1Roll = true;
			    turn = true;
			    p1flag = true;
			}
			else
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\n" + p1Name + ": You would have been sent to Jail, but you had a Get-Out-of-Jail free card, so you are just visiting");
			    p2Jail = false;
			    p2jailCard = false;

			}

		    }
		    if (p2Pos >= 40)
		    {
			p2Pos -= 40;
			p2Money += 200;
			word = commentary.getText ();
			commentary.setText (word + "\n" + p2Name + " passed GO, collect $200");
		    }

		    posButtonsBlue [p2Pos].setBackground (Color.blue);




		    if (p2Pos == 7 || p2Pos == 22 || p2Pos == 36) //Chance Start
		    {
			int randChoice = (int) (Math.random () * 10) + 1;
			randChoice -= 1;
			if (randChoice == 0) //Advance to boardwalk
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Advance to Boardwalk");
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 39;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			}
			else if (randChoice == 1) //Advance to GO
			{
			    p2Money += 200; //B
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Advance to GO, collect $200");
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 0;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			}
			else if (randChoice == 2) //Advance to Illinois Avenue Chance
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Advance to Illinois Avenue, if you pass GO, collect $200");
			    if (p2Pos >= 24)
			    {
				p2Money += 200;
			    }
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 24;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			}
			else if (randChoice == 3) //Advance to ST.Charles
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Advance to St.Charles Place, if you pass GO, collect $200");
			    if (p2Pos >= 11)
			    {
				p2Money += 200;
			    }
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 11;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);

			}
			else if (randChoice == 4) //Back Three Spaces
			{
			    //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Move Back 3 Spaces");
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos -= 3;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			}
			else if (randChoice == 5) //Chairman
			{

			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Elected chairman of the board, pay 50 to other player");
			    p2Money -= 50;
			    p1Money += 50;
			}

			else if (randChoice == 6) //Dividend
			{

			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Bank pays you divdend, collect $50");
			    p2Money += 50;
			}
			else if (randChoice == 7) //Go tO Jail
			{
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 10;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Go to Jail");
			    if (p2jailCard == true)
			    {
				////("You had a get out jail card!")
				p2Jail = false;
				p2jailCard = false;
			    }
			    else
			    {
				word = commentary.getText ();
				commentary.setText (word + "\n" + p2Name + ": You have been sent to Jail, your turn automatically ends");

				player1Roll = true;
				p2Jail = true;
				turn = true;
				p1flag = true;
			    }
			    //p1inJail = true; //B
			}
			else if (randChoice == 8) //Loan Matures
			{
			    p2Money += 150;
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Loan Matures, collect S150");
			}
			else if (randChoice == 9) //Out of Jail
			{
			    p2jailCard = true; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nChance: " + p2Name + ": Get out of Jail Free Card(will be used at first instance of jail");

			}

			ChanceCardA = new JPanel ();
			posChance = new JLabel (Chance [randChoice]);
			ChanceCardA.add (posChance);
			constraints.gridx = 3;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			panel.add (ChanceCardA, constraints);
			MainPanel.add (panel);
			frame.setVisible (true);
		    } //Chancce End

		    if (p2Pos == 2 || p2Pos == 17 || p2Pos == 33) //Community Chest Start
		    {
			int randChoice = (int) (Math.random () * 13) + 1;
			randChoice -= 1;
			if (randChoice == 0) //Bank Error
			{
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Bank Error in your favour, collect $200");
			    p2Money += 200; //A

			}
			else if (randChoice == 1) //Beauty Contest
			{
			    p2Money += 10; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": You placed second place in a beauty contest, collect $10");
			}
			else if (randChoice == 2) //Advance to go
			{
			    p2Money += 200; //B
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Advance to GO, collect $200");
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 0;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);


			}
			else if (randChoice == 3) //Consultancy Fee
			{
			    p2Money += 25; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Receive for service, collect $25");
			}
			else if (randChoice == 4) //Doctor Fee
			{
			    p2Money -= 50; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Doctor's fee, pay $50");
			}
			else if (randChoice == 5) //Get Out of Jail
			{
			    p2jailCard = true; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Get out of Jail Free Card(will be used at first instance of jail");

			}
			else if (randChoice == 6) //Go to Jail
			{
			    posButtonsBlue [p2Pos].setBackground (Color.LIGHT_GRAY);
			    p2Pos = 10;
			    posButtonsBlue [p2Pos].setBackground (Color.blue);
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Go to Jail");
			    if (p2jailCard == true)
			    {
				////("You had a get out jail card!")
				p2Jail = false;
				p2jailCard = false;
			    }
			    else
			    {
				word = commentary.getText ();
				commentary.setText (word + "\n" + p2Name + ": You have been sent to Jail, your turn automatically ends");
				player1Roll = true;
				p2Jail = true;
				turn = true;
				p1flag = true;
			    }
			    //p1inJail = true; //B
			}
			else if (randChoice == 7) //Holiday Fund Matures
			{
			    p2Money += 100; //A
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Holiday Fund Matures, collect S100");
			}
			else if (randChoice == 8) //Income Tax
			{
			    p2Money += 20;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Income Tax Refund, collect S20");
			}
			else if (randChoice == 9) //Inherit 100
			{
			    p2Money += 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Inherit $100");

			}
			else if (randChoice == 10) //Life Insurance
			{
			    p2Money += 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Life Insurance matures, collect $100");
			}
			else if (randChoice == 11) //Pay Hospital
			{
			    p2Money -= 100;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": Hospital fees, pay $100");

			}
			else if (randChoice == 12) //Sale of Stock
			{
			    p2Money += 45;
			    word = commentary.getText ();
			    commentary.setText (word + "\nCommunity Chest: " + p2Name + ": From Sales of Stock, collect $45");
			}
			ChestCard = new JPanel ();
			posChest = new JLabel (Chest [randChoice]);
			ChestCard.add (posChest);
			constraints.gridx = 9;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			panel.add (ChestCard, constraints);
			MainPanel.add (panel);
			frame.setVisible (true);
		    } //Community Chest end
		    if (p2Pos == 4)
		    {
			p2Money -= 200;
			word = commentary.getText ();
			commentary.setText (word + "\n" + p2Name + " Income tax, pay $200");

		    }
		    if (p2Pos == 38)
		    {
			p2Money -= 100;
			word = commentary.getText ();
			commentary.setText (word + "\n" + p2Name + " Luxury Tax, pay $100");

		    }
		    if (p2Pos != 5 && p2Pos != 15 && p2Pos != 25 && p2Pos != 35 && p2Pos != 12 && p2Pos != 28) //12,28 utilites, others railroad
		    {
			if (buyableProperties [p2Pos] == false)
			{
			    String p2Check[] = new String [player1Assets.size ()];
			    for (int count = 0 ; count < p2Check.length ; count++)
			    {
				p2Check [count] = (String) player1Assets.elementAt (count);
			    }
			    MySorts ms = new MySorts ();
			    int found = ms.LinearSearch (p2Check, properties [p2Pos]);
			    if (found >= 0)
			    {
				p2Money -= rentData [p2Pos] [propertyUpgrades [p2Pos]];
				p1Money += rentData [p2Pos] [propertyUpgrades [p2Pos]];
				word = commentary.getText ();
				commentary.setText (word + "\n " + p2Name + " pays rent to " + p1Name + " at " + p2Check [found] + " for " + (rentData [p2Pos] [propertyUpgrades [p2Pos]]));
			    }
			}
		    }
		    if (p2Pos == 5 || p2Pos == 15 || p2Pos == 25 || p2Pos == 35)
		    {
			String p2Check[] = new String [player1Assets.size ()];
			for (int count = 0 ; count < p2Check.length ; count++)
			{
			    p2Check [count] = (String) player1Assets.elementAt (count);
			}


			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p2Check, properties [p2Pos]);
			if (found >= 0)
			{
			    int total = 25;
			    for (int count = 1 ; count <= p2numRR ; count++)
			    {
				total = total * 2;
			    }
			    p2Money -= total;
			    p1Money += total;
			    word = commentary.getText ();
			    commentary.setText (word + "\n " + p2Name + " pays rent to " + p1Name + " at " + p2Check [found] + " for " + total);
			    total = 25;

			}
		    }
		    if (p2Pos == 12 || p2Pos == 28)
		    {
			String p2Check[] = new String [player1Assets.size ()];
			for (int count = 0 ; count < p2Check.length ; count++)
			{
			    p2Check [count] = (String) player1Assets.elementAt (count);
			}


			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p2Check, properties [p2Pos]);
			if (found >= 0)
			{
			    int utilityDice = diceRoll ();

			    if (p1numUtilities == 1)
			    {
				utilityDice *= 4;

			    }
			    if (p1numUtilities == 2)
			    {
				utilityDice *= 10;
			    }
			    p2Money -= utilityDice;
			    p1Money += utilityDice;
			    word = commentary.getText ();
			    commentary.setText (word + "\n " + p2Name + " pays rent to " + p1Name + " at " + p2Check [found] + " for " + utilityDice);
			}
		    }

		    DeedCard = new JPanel ();
		    posMiddle = new JLabel (titleDeedCards [p2Pos]);
		    DeedCard.add (posMiddle);
		    constraints.gridx = 6;
		    constraints.gridy = 15;
		    constraints.gridwidth = 3;
		    panel.add (DeedCard, constraints);
		    MainPanel.add (panel);
		    frame.setVisible (true);

		    while (turn == false)
		    {
			buyButton.addActionListener (this);
			upgButton.addActionListener (this);

			EndTurnButton.addActionListener (this);

			player1Roll = true;

			if (turn == true)
			{
			    DeedCard = new JPanel (); //add the image of the property player 1 land on, the title deed
			    posMiddle = new JLabel (backDeed);
			    DeedCard.add (posMiddle);
			    constraints.gridx = 6;
			    constraints.gridy = 15;
			    constraints.gridwidth = 3;
			    panel.add (DeedCard, constraints);
			    MainPanel.add (panel);

			    frame.setVisible (true);
			    ChestCard = new JPanel ();
			    posChest = new JLabel (backChest);
			    ChestCard.add (posChest);
			    constraints.gridx = 9;
			    constraints.gridy = 12;
			    constraints.gridwidth = 3;
			    panel.add (ChestCard, constraints);
			    MainPanel.add (panel);
			    frame.setVisible (true);

			    ChanceCardA = new JPanel ();
			    posChance = new JLabel (backChance);
			    ChanceCardA.add (posChance);
			    constraints.gridx = 3;
			    constraints.gridy = 12;
			    constraints.gridwidth = 3;
			    panel.add (ChanceCardA, constraints);
			    MainPanel.add (panel);
			    frame.setVisible (true);
			    break;
			}
		    }
		}
	    }
	    frame.setVisible (true);
	}
	frame.setVisible (false); //once the game ends, make the screen invisible
	efg.EndingScreen (p1Money, p2Money, p1Name, p2Name); //call the ending screen




    }



    public void actionPerformed (ActionEvent evt)
    {

	if (evt.getActionCommand ().equals ("ROLL")) //whenever the roll button is clicked
	{
	    if (player1Roll == true && turn == true && p1Jail == false && p1flag == false) //p1 must be able to roll, it must be there turn, they cannot be in jail
	    {
		dice = diceRoll (); //gets a number back
		word = commentary.getText ();
		commentary.setText (word + "\n" + p1Name + " Just Rolled A " + dice);
		player1Roll = false; //turns off player ones ability to roll again
		p1Rights = true; //allows them to buy, upgrade, or end turn
	    }
	    else if (player2Roll == true && turn == false && p2Jail == false && p2flag == false) //same thing but for player 2
	    {
		dice = diceRoll ();
		word = commentary.getText ();
		commentary.setText (word + "\n" + p2Name + " Just Rolled A " + dice);
		player2Roll = false;
		p2Rights = true;
	    }

	}
	if (evt.getActionCommand ().equals ("END TURN")) //whenever they want to end turn
	{
	    if (turn == true && player1Roll == false && p1Jail == false) //if they cannot roll, its their turn, and they are not in jail
	    {
		word = commentary.getText ();
		commentary.setText (word + "\n" + p1Name + ": ends their turn\n");
		turn = false; //let player 2 roll
	    }
	    else if (turn == false && player2Roll == false && p2Jail == false) //same thing but for player 1
	    {
		word = commentary.getText ();
		commentary.setText (word + "\n" + p2Name + ": ends their turn\n");
		turn = true;
	    }

	}



	if (evt.getActionCommand ().equals ("BUY"))
	{
	    if (turn == true && p1Jail == false) //if p1 isnt in jaul and its their turn
	    {
		if (buyableProperties [p1Pos] == true) //if the position they want to buy is even buyable
		{
		    Buying (); //let them buy it
		}


	    }

	    if (turn == false) //same thing as above
	    {
		if (buyableProperties [p2Pos] == true && p2Jail == false)
		{
		    Buying ();
		}


	    }
	}


	if (evt.getActionCommand ().equals ("UPGRADE"))
	{
	    if (turn == true && p1Jail == false) //not in jail, their turn
	    {
		if (p1Rights == true) //already rolled
		{
		    if (buyableProperties [p1Pos] == false && propertyUpgrades [p1Pos] < 6) //property is owned and also allowed to be upgraded
		    {
			String p1Check[] = new String [player1Assets.size ()]; //check if player 1 owns it
			for (int count = 0 ; count < p1Check.length ; count++)
			{
			    p1Check [count] = (String) player1Assets.elementAt (count);
			}
			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p1Check, properties [p1Pos]);
			if (found >= 0)
			{
			    p1Rights = false; // once they upgrade, they cant upgrade again
			    UpgradingP1 (); //call the upgrading method
			    word = commentary.getText ();
			    commentary.setText (word + "\n" + p1Name + " You just upgraded your property");
			}

		    }
		}

	    }


	    if (turn == false && p2Jail == false) //same thing for player 2 as player 1
	    {
		if (p2Rights == true)
		{
		    if (buyableProperties [p2Pos] == false && propertyUpgrades [p2Pos] < 6)
		    {
			String p2Check[] = new String [player2Assets.size ()];
			for (int count = 0 ; count < p2Check.length ; count++)
			{
			    p2Check [count] = (String) player2Assets.elementAt (count);
			}
			MySorts ms = new MySorts ();
			int found = ms.LinearSearch (p2Check, properties [p2Pos]);
			if (found >= 0)
			{
			    p2Rights = false;
			    UpgradingP2 ();
			    word = commentary.getText ();
			    commentary.setText (word + "\n" + p2Name + " You just upgraded your property");
			}

		    }
		}

	    }
	}

	if (evt.getActionCommand ().equals ("JAIL-ROLL")) //rolling jail
	{
	    if (p1Jail == true && turn == true && jp1Rights == true) //if you are in jail and its ur turn in jail,
	    {
		jp1Rights = false; //they can either roll or pay 50, not both
		int d1 = (int) (Math.random () * 6) + 1;
		int d2 = (int) (Math.random () * 6) + 1;
		d1 = d2; //check if they got double
		if (d1 == d2) //out if they did , if not still in jail
		{
		    p1jailturn = 4;
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p1Name + ": You rolled a DOUBLE in jail, you are out!");

		}
		else
		{
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p1Name + ": You could not roll a DOUBLE in Jail");

		}
	    }
	    else if (p2Jail == true && turn == false && jp2Rights == true) //same thing as player 1
	    {
		jp2Rights = false;
		int d1 = (int) (Math.random () * 6) + 1;
		int d2 = (int) (Math.random () * 6) + 1;
		if (d1 == d2)
		{
		    p2jailturn = 4;
		    p2Jail = false;
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p2Name + ": You rolled a DOUBLE in jail, you are out!");
		}
		else
		{
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p2Name + ": You could not roll a DOUBLE in Jail");
		}
	    }

	}
	if (evt.getActionCommand ().equals ("PAY-$50")) //they can pay 50
	{
	    if (p1Jail == true && turn == true && jp1Rights == true)
	    {
		jp1Rights = false;
		p1Money -= 50;
		p1jailturn = 4; //spent all their time in jail
		word = commentary.getText ();
		commentary.setText (word + "\n" + p1Name + ": You paid $50 to get out of Jail");
	    }


	    if (p2Jail == true && turn == false && jp2Rights == true) //same thing as player 1
	    {
		jp2Rights = false;
		p2Money -= 50;
		p2jailturn = 4;
		word = commentary.getText ();
		commentary.setText (word + "\n" + p2Name + ": You paid $50 to get out of Jail");
	    }



	}


	if (evt.getActionCommand ().equals ("Wait Turn")) //must be clicked after any action
	{
	    if (p1Jail == true && turn == true && jailallowed == true)
	    {
		p1jailturn += 1;
		word = commentary.getText ();
		commentary.setText (word + "\n" + p1Name + ": You waited this turn in Jail");
		if (p1jailturn >= 3) // if they waited 3 or more turns, they are out
		{
		    p1Jail = false;
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p1Name + ": You are out");
		    p1jailturn = 0;
		}
		turn = false; //once they click wait turn, its pl2 turn

	    }
	    else if (p2Jail == true && turn == false && jailallowed == false) //same thing as player 1
	    {
		p2jailturn += 1;
		word = commentary.getText ();
		commentary.setText (word + "\n" + p2Name + ": You waited this turn in Jail");
		if (p2jailturn >= 3)
		{
		    p2Jail = false;
		    word = commentary.getText ();
		    commentary.setText (word + "\n" + p2Name + ": You are out");
		    p2jailturn = 0;
		}
		turn = true;

	    }



	}
    }


    public void UpgradingP1 ()  
    {
	String word = "";
	if (p1Pos >= 0 && p1Pos <= 10) //depending on the side of the board, the money of the price to upgrade increases
	{
	    if (50 > p2Money) //if they don't have enough money, give the error message
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p1Pos] += 1; //increase the level of the property by 1
		p1Money -= 50;
		if (p1Pos == 1) //If you buy the upgrade, depending on the level of the property at that position, we colour in the corresponding house
		{
		    mediterraneanhouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red); // red because player one is red
		}
		if (p1Pos == 3)
		{
		    Baltichouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 6)
		{
		    orientalhouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 8)
		{
		    vermonthouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 9)
		{
		    connecticuthouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
	    }

	}
	///Same logic, but different costs depending on the side of the board

	if (p1Pos >= 10 && p1Pos <= 20)
	{
	    if (100 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p1Pos] += 1;
		p1Money -= 100;
		if (p1Pos == 11)
		{
		    StCharlesHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 13)
		{
		    StatesAveHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 14)
		{
		    VirginiaAveHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 16)
		{
		    StJamesHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 18)
		{
		    TennesseHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 19)
		{
		    NewYorkHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}

	    }

	}


	if (p1Pos >= 20 && p1Pos <= 30)
	{
	    if (150 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p1Pos] += 1;
		p1Money -= 150;
		if (p1Pos == 21)
		{
		    KentuckyHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 1)
		{
		    mediterraneanhouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 23)
		{
		    IndianaHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 24)
		{
		    IllinoisHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 26)
		{
		    AtlanticHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 27)
		{
		    VentnorHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 29)
		{
		    MarvinHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
	    }

	}


	if (p1Pos >= 30 && p1Pos <= 40)
	{
	    if (200 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p1Pos] += 1;
		p1Money -= 200;
		if (p1Pos == 31)
		{
		    PacificAvenueHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 32)
		{
		    NorthCarolinaHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 34)
		{
		    PennsylvaniaAveHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 37)
		{
		    parkPlaceHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
		if (p1Pos == 39)
		{
		    boardwalkHouses [propertyUpgrades [p1Pos] - 1].setBackground (Color.red);
		}
	    }

	}
    }


    public void UpgradingP2 ()  //same as player 1 but respective to player 2
    {
	String word = "";
	if (p2Pos >= 0 && p2Pos <= 10)
	{
	    if (50 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p2Pos] += 1;
		p2Money -= 50;
		if (p2Pos == 1)
		{
		    mediterraneanhouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 3)
		{
		    Baltichouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 6)
		{
		    orientalhouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 8)
		{
		    vermonthouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 9)
		{
		    connecticuthouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
	    }

	}


	if (p2Pos >= 10 && p2Pos <= 20)
	{
	    if (100 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p2Pos] += 1;
		p2Money -= 100;
		if (p2Pos == 11)
		{
		    StCharlesHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 13)
		{
		    StatesAveHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 14)
		{
		    VirginiaAveHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 16)
		{
		    StJamesHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 18)
		{
		    TennesseHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 19)
		{
		    NewYorkHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}

	    }

	}


	if (p2Pos >= 20 && p2Pos <= 30)
	{
	    if (150 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p2Pos] += 1;
		p2Money -= 150;
		if (p2Pos == 21)
		{
		    KentuckyHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 1)
		{
		    mediterraneanhouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 23)
		{
		    IndianaHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 24)
		{
		    IllinoisHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 26)
		{
		    AtlanticHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 27)
		{
		    VentnorHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 29)
		{
		    MarvinHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
	    }

	}


	if (p2Pos >= 30 && p2Pos <= 40)
	{
	    if (200 > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
	    }
	    else
	    {
		propertyUpgrades [p2Pos] += 1;
		p2Money -= 200;
		if (p2Pos == 31)
		{
		    PacificAvenueHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 32)
		{
		    NorthCarolinaHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 34)
		{
		    PennsylvaniaAveHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 37)
		{
		    parkPlaceHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
		if (p2Pos == 39)
		{
		    boardwalkHouses [propertyUpgrades [p2Pos] - 1].setBackground (Color.blue);
		}
	    }

	}
    }



    int diceRoll ()  //returns a random number
    {
	dice = 0;
	int dice1 = (int) (Math.random () * 6) + 1;
	int dice2 = (int) (Math.random () * 6) + 1;

	dice = dice1 + dice2;
	return dice;

    }


    public void Buying ()  //
    {
	if (turn == true && p1Rights == true) //if the player can buy and its their turn
	{
	    String word = "";
	    if (PropertyPrice [p1Pos] > p1Money) //if they have enough money
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
		p1Rights = false;

	    }
	    else //if they do, do the transaction
	    {
		p1Rights = false;
		p1Money -= PropertyPrice [p1Pos];
		buyableProperties [p1Pos] = false;
		player1Assets.addElement (properties [p1Pos]);
		word = commentary.getText ();
		commentary.setText (word + "\n" + p1Name + " just bought " + properties [p1Pos]);
		prop1 = p1List.getText ();
		p1List.setText (prop1 + "\n" + properties [p1Pos]);

		if (p1Pos == 5 || p1Pos == 15 || p1Pos == 25 || p1Pos == 35) //if its a railraod increase the number of railroads owned
		{
		    p1numRR++;
		}
		if (p1Pos == 12 || p1Pos == 28) //if its a utility increase the number of utilites owned
		{
		    p1numUtilities++;
		}
	    }
	}


	if (turn == false && p2Rights == true) //same thing for player2 as pl1.
	{
	    String word = "";
	    if (PropertyPrice [p2Pos] > p2Money)
	    {
		word = commentary.getText ();
		commentary.setText (word + "\nNot enough funds in bank to carry out the transaction");
		p2Rights = false;
	    }
	    else
	    {
		p2Rights = false;
		p2Money -= PropertyPrice [p2Pos];
		buyableProperties [p2Pos] = false;
		player2Assets.addElement (properties [p2Pos]);
		word = commentary.getText ();
		commentary.setText (word + "\n" + p2Name + " just bought " + properties [p2Pos]);
		prop2 = p2List.getText ();
		p2List.setText (prop2 + "\n" + properties [p2Pos]);
		if (p1Pos == 5 || p1Pos == 15 || p1Pos == 25 || p1Pos == 35)
		{
		    p2numRR++;
		}
		if (p1Pos == 12 || p1Pos == 28)
		{
		    p2numUtilities++;
		}
	    }
	}
    }
}
class LandingPage25 extends JFrame implements ActionListener //landing page
{
    String names[] = new String [2];
    JTextField p1Name;
    JTextField p2Name;
    JFrame frame;
    boolean clickedFlag = false;
    public static void main (String args[])
    {
	new LandingPage25 ();
    }


    String[] LandingPage22 ()
    {
	frame = new JFrame ();
	JPanel LandingPageMain = (JPanel) frame.getContentPane ();
	frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());
	JPanel panel = new JPanel (new GridBagLayout ());
	GridBagConstraints constraints = new GridBagConstraints ();
	ImageIcon Logo = new ImageIcon ("MonopolyLogo.jpg"); //getting the monopoly logo
	panel.setBackground (Color.white);
	JLabel label2 = new JLabel ("Full Screen After You Press Start, Enjoy!"); //user prompt

	JLabel LogoLabel = new JLabel (Logo); //adding image to label
	JButton startButton = new JButton ("START");

	JLabel P1Label = new JLabel ("PLAYER ONE");
	JLabel P2Label = new JLabel ("PLAYER TWO");

	Font currentFont = P1Label.getFont ();
	Font label = new Font ("Serif", Font.BOLD, 20); //changin font
	P1Label.setFont (label);
	P2Label.setFont (label);

	JButton p1 = new JButton (""); //buttons to show what color each player is
	JButton p2 = new JButton ("");
	p1.setBackground (Color.blue);
	p2.setBackground (Color.red);
	p1.setPreferredSize (new Dimension (30, 30));
	p2.setPreferredSize (new Dimension (30, 30));
	p1Name = new JTextField ("Enter your name", 15); //textareas to choose pl1 and pl2 names
	p2Name = new JTextField ("Enter your name", 15);

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

	panel.add (P1Label, constraints);


	constraints.gridx = 6;
	constraints.gridy = 3;

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


	constraints.gridx = 5;
	constraints.gridy = 5;
	panel.add (label2, constraints);

	LandingPageMain.add (panel); //adding game to panel
	frame.setVisible (true);
	while (clickedFlag == false)
	{
	    startButton.addActionListener (this);
	}

	return names;


    }


    public void actionPerformed (ActionEvent evt)
    {
	doMyButtonAction ();
	repaint ();
    }


    public void doMyButtonAction ()  //once start is clicked, add the names from the textfield into the array and send the array back to the main code
    {
	names [0] = p2Name.getText ();
	names [1] = p1Name.getText ();
	clickedFlag = true;
	frame.setVisible (false);

    }
}


class EndScreen extends JFrame //end screen
{
    String names[] = new String [2];
    JTextField p1Name;
    JTextField p2Name;
    JFrame frame;
    boolean winner;
    int score1 = 0;
    int score2 = 0;
    JLabel P1Label;
    JLabel P2Label;

    public static void main (String args[])
    {
	new EndScreen ();
    }


    void EndingScreen (int p1Money, int p2Money, String p1Name, String p2Name)
    {
	frame = new JFrame ();
	JPanel LandingPageMain = (JPanel) frame.getContentPane ();
	frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());
	JPanel panel = new JPanel (new GridBagLayout ());
	GridBagConstraints constraints = new GridBagConstraints ();
	JLabel P1Label = new JLabel ("");
	JLabel P2Label = new JLabel ("");
	JLabel scores = new JLabel (""); //blank labels to be updated after
	ImageIcon Logo = new ImageIcon ("MonopolyLogo.jpg"); //adding logo
	panel.setBackground (Color.white);
	JLabel LogoLabel = new JLabel (Logo);
	if (p1Money > p2Money) //determines winner and loser
	{

	    P1Label = new JLabel (p1Name + " - WINNER");
	    P2Label = new JLabel (p2Name + " - LOSER");
	    winner = true;

	}
	else
	{
	    P1Label = new JLabel (p1Name + " - LOSER");
	    P2Label = new JLabel (p2Name + " - WINNER");
	    winner = false;
	}

	try
	{
	    FileReader fr = new FileReader ("GameScores.txt");
	    BufferedReader bfr = new BufferedReader (fr); //getting overall win scores
	    String a, b;
	    a = bfr.readLine ();
	    b = bfr.readLine ();
	    score1 = Integer.parseInt (a);
	    score2 = Integer.parseInt (b); //storing them
	    fr.close ();

	    if (winner == true) //increasing overall win based on who won
	    {

		score1 += 1;
	    }
	    if (winner == false)
	    {
		score2 += 1;
	    }
	    scores = new JLabel ("Overall Wins till now:        Player 1:   " + score1 + "     Player 2:   " + score2); //gets scores from file and showcases


	    FileWriter fw = new FileWriter ("GameScores.txt");
	    fw.write (score1 + "\r\n");
	    fw.write (score2 + "\r\n"); //rewrites updated scores
	    fw.close ();

	}

	catch (IOException exe)
	{
	    System.out.println ();

	}

	Font label = new Font ("Serif", Font.BOLD, 20);
	P1Label.setFont (label);
	P2Label.setFont (label);
	constraints.gridx = 3;
	constraints.gridy = 5;
	panel.add (P1Label, constraints);
	constraints.gridx = 5;
	constraints.gridy = 5;
	panel.add (P2Label, constraints);

	constraints.gridx = 4;
	constraints.gridy = 7;
	panel.add (scores, constraints);

	JPanel LogoPane = new JPanel ();
	LogoPane.add (LogoLabel);

	constraints.gridx = 4;
	constraints.gridy = 1;
	panel.add (LogoPane, constraints);

	LandingPageMain.add (panel);
	frame.setVisible (true); //adds everything to panel

    }
}








