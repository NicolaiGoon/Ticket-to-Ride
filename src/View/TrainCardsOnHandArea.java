package View;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import Controller.Controller.SelectCardActionListener;

import java.awt.Color;

/**
 * <b>a calss that represents the area of the train cards of the player</b><br>
 * @author Nikos Gounakis
 *
 */
public class TrainCardsOnHandArea extends JLayeredPane {

	private JButton playCards;
	private ArrayList<TrainCardButton> cards;
	private JLabel areaName;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new TrainCardsOnHandsArea and initializes its components<br>
	 */
	public TrainCardsOnHandArea()
	{
		setBorder(new LineBorder(Color.BLACK, 2));
		setBounds(0,0,657,137);
		
		// initializing the label
		areaName = new JLabel("TrainCards on Hand");
		areaName.setBounds(12, 115, 167, 16);
		add(areaName);
				
		// initializing the button
		playCards = new JButton("Play Cards");
		playCards.setLocation(524, 99);
		playCards.setSize(121, 25);
		add(playCards);
		
		cards = new ArrayList<>(); 
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>playCards must be returned<br>
	 * @return returns the JButton that is its used to play the cards that are chosen
	 */
	public JButton getplayCards()
	{
		return playCards;
	}
	
	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>add an action listener to the button play cards<br>
	 * @param playcards the action listener to be added
	 */
	public void listenPlayCardsButton(ActionListener playcards)
	{
		this.playCards.addActionListener(playcards);
	}

	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>the arraylist of cards must be returned<br>
	 * @return returns the arraylist that contains the buttons of the cards that represents the traincards on hand
	 */
	public ArrayList<TrainCardButton> getCards() {
		return cards;
	}

	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>sets the cards<br>
	 * @param cards the arraylist to be set
	 */
	public void setCards(ArrayList<TrainCardButton> cards) {
		this.cards = cards;
	}

	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondiiton :<b>sets action listener to every button of the arraylist cards<br>
	 * @param selectCardActionListener the actionlistener to be added
	 */
	public void listenerChooseToPlayCard(ActionListener selectCardActionListener) 
	{
		for(TrainCardButton cardbutton : cards)
		{
			cardbutton.addActionListener(selectCardActionListener);
		}
	}

	
}
