package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import Model.Card.Player;
import Model.Card.TrainCard;
import Model.Deck.Deck;
import Model.Exception.InvalidMoveException;

import javax.swing.ImageIcon;

/**
 * <b>a class that represents the GUI of the central deck of the game</b><br>
 * @author Nikos Gounakis
 *
 */
public class DeckArea extends JLayeredPane {

	private JButton trainCards;
	private JButton destCards;
	// changed to TrainCardButton
	private ArrayList<TrainCardButton> train5Cards;
	private ArrayList<JLabel> bigCitiesCards;
	private JLabel availableTrainCards;
	private JLabel availableDestCards;
	private JLabel bonusCards;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new DeckArea object and initializes its components<br>
	 */
	public DeckArea()
	{
		setBorder(new LineBorder(new Color(64, 64, 64), 5));
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(12, 13, 1250, 154);
		this.setBounds(0,372,1274,180);
		add(lblNewLabel);
		
		ImageIcon image = new ImageIcon(DeckArea.class.getResource("/resources/images/background.jpg"));
		// converting imageicon to image
		Image image2 = image.getImage(); 
		// scalimng the image
		Image newimage = image2.getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		// the final scaled image
		ImageIcon image3 = new ImageIcon(newimage);
		lblNewLabel.setIcon(image3);
		
		// initializing label for train cards on deck
		availableTrainCards = new JLabel("Train Deck : ");
		availableTrainCards.setForeground(Color.WHITE);
		lblNewLabel.add(availableTrainCards);
		availableTrainCards.setBounds(38,126,90,31);
		
		// initializing label for destination card on deck
		availableDestCards = new JLabel("Destination Deck : ");
		availableDestCards.setForeground(Color.WHITE);
		lblNewLabel.add(availableDestCards);
		availableDestCards.setBounds(168,126,121,31);
		
		// initializing big cities cards
		bigCitiesCards = new ArrayList<>(6);
		URL BigCitieUrl1 = DeckArea.class.getResource("/resources/images/bigCitiesCards/Chicago.jpg");
		URL BigCitieUrl2 = DeckArea.class.getResource("/resources/images/bigCitiesCards/Dallas.jpg");
		URL BigCitieUrl3 = DeckArea.class.getResource("/resources/images/bigCitiesCards/LosAngeles.jpg");
		URL BigCitieUrl4 = DeckArea.class.getResource("/resources/images/bigCitiesCards/Miami.jpg");
		URL BigCitieUrl5 = DeckArea.class.getResource("/resources/images/bigCitiesCards/NewYork.jpg");
		URL BigCitieUrl6 = DeckArea.class.getResource("/resources/images/bigCitiesCards/Seattle.jpg");
		
		java.awt.Image bigcity1 = new ImageIcon(BigCitieUrl1).getImage();
		java.awt.Image bigcity2 = new ImageIcon(BigCitieUrl2).getImage();
		java.awt.Image bigcity3 = new ImageIcon(BigCitieUrl3).getImage();
		java.awt.Image bigcity4 = new ImageIcon(BigCitieUrl4).getImage();
		java.awt.Image bigcity5 = new ImageIcon(BigCitieUrl5).getImage();
		java.awt.Image bigcity6 = new ImageIcon(BigCitieUrl6).getImage();
		
		bigcity1 = bigcity1.getScaledInstance(70, 100,java.awt.Image.SCALE_SMOOTH);
		bigcity2 = bigcity2.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH);
		bigcity3 = bigcity3.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH);
		bigcity4 = bigcity4.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH);
		bigcity5 = bigcity5.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH);
		bigcity6 = bigcity6.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH);
		
		JLabel label1 = new JLabel();
		label1.setIcon(new ImageIcon(bigcity1));
		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon(bigcity2));
		JLabel label3 = new JLabel();
		label3.setIcon(new ImageIcon(bigcity3));
		JLabel label4 = new JLabel();
		label4.setIcon(new ImageIcon(bigcity4));
		JLabel label5 = new JLabel();
		label5.setIcon(new ImageIcon(bigcity5));
		JLabel label6 = new JLabel();
		label6.setIcon(new ImageIcon(bigcity6));
	
		bigCitiesCards.add(label1);
		bigCitiesCards.add(label2);
		bigCitiesCards.add(label3);
		bigCitiesCards.add(label4);
		bigCitiesCards.add(label5);
		bigCitiesCards.add(label6);
		
		for(int i=0;i<bigCitiesCards.size();i++)
		{
			bigCitiesCards.get(i).setBounds(775+i*80,30,70,100);
			lblNewLabel.add(bigCitiesCards.get(i));
		}
		
		// initializing label big cities cards
		bonusCards = new JLabel("Available Big Cities Bonus Cards");
		bonusCards.setForeground(Color.WHITE);
		bonusCards.setBounds(907,126,197,31);
		lblNewLabel.add(bonusCards);
		
		// initializing  the 5 train cards those cards will be initialised fully when the game starts
		train5Cards = new ArrayList<>(5);
		
		// initializing train card button stack
		trainCards = new JButton();
		URL trainUrl = Deck.class.getResource("/resources/images/trainCards/trainBackCard.jpg");
		Image trainImageback = new ImageIcon(trainUrl).getImage();
		trainImageback = trainImageback.getScaledInstance(70, 100,Image.SCALE_SMOOTH);
		trainCards.setIcon(new ImageIcon(trainImageback));
		trainCards.setBounds(40,30,70,100);
		lblNewLabel.add(trainCards);
		
		// initializing destination card button stack
		destCards = new JButton();
		URL destUrl = Deck.class.getResource("/resources/images/destination_Tickets/desBackCard.jpg");
		java.awt.Image destImageback = new ImageIcon(destUrl).getImage();
		destImageback = destImageback.getScaledInstance(70, 100,java.awt.Image.SCALE_SMOOTH);
		destCards.setIcon(new ImageIcon(destImageback));
		destCards.setBounds(185,30,70,100);
		lblNewLabel.add(destCards);
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the trainCards must be returned<br>
	 * @return returns the button that is used to draw TrainCards of the deck
	 */
	public JButton getTrainCards()
	{
		return trainCards;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the destinationCards must be returned<br>
	 * @return returns the button that is use to draw DestinationCards from the deck
	 */
	public JButton getDestCards()
	{
		return destCards;	
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the arraylist must be returned<br>
	 * @return returns the list of buttons that includes the 5 train cards on the deck
	 */
	public ArrayList<TrainCardButton> get5TrainCards()
	{
		return train5Cards;	
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the bigCitiesCards must be returned<br>
	 * @return returns the arrallist of the BigCitiesCards on the deck
	 */
	public ArrayList<JLabel> getBigCitiesCards()
	{
		return bigCitiesCards;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the available train cards must be returned<br>
	 * @return returns the JLabel that represents the amount of available train cards in the deck
	 */
	public JLabel getAvailableTrainCards()
	{
		return availableTrainCards;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition :</b>updates the number of traincards on deck<br>
	 * @param cards the amount of cards int the deck
	 */
	public void updateAvailableTrainCards(int cards)
	{
		availableTrainCards.setText("Train Deck : "+cards);
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the available cards must be returned<br>
	 * @return returns the JLabel that represents the amount of available destination cards in the deck
	 */
	public JLabel getAvailableDestCards()
	{
		return availableDestCards;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition :</b>updates the number of destination cards on deck<br>
	 * @param cards the amount of cards int the deck
	 */
	public void updateAvailableDestCards(int cards)
	{
		availableDestCards.setText("Destination Deck : "+cards);
	}

	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the available bonus cards must be returned<br>
	 * @return returns the JLabel that represents the available big cities bonus cards
	 */
	public JLabel getBonusCards() 
	{
		return bonusCards;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>the parameter must be the correct Actionlistener<br>
	 * <b>Postcondition :</b>adds an action listener to the button<br>
	 * @param drawtraincard the action listener to add
	 */
	public void listenerDrawTrainCard(ActionListener drawtraincard)
	{
		trainCards.addActionListener(drawtraincard);
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>the parameter must be the correct Actionlistener<br>
	 * <b>Postcondition :</b>adds an action listener to the button<br>
	 * @param drawdestcard the action listener to add
	 */
	public void listenerDrawDestCard(ActionListener drawdestcard)
	{
		destCards.addActionListener(drawdestcard);
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>the parameter must be the correct Actionlistener<br>
	 * <b>Postcondition :</b>adds an action listener to the buttons<br>
	 * @param draw5train the action listener to add
	 */
	public void listenerDrawFrom5TrainCards(ActionListener draw5train)
	{
		for(int i=0;i<train5Cards.size();i++)
		{
			train5Cards.get(i).addActionListener(draw5train);
		}
	}
	
}
