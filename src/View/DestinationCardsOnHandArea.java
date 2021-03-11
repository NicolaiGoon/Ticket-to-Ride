package View;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * <b>a class that represents the GUI of the destination cards that the player has</b><br>
 * @author Nikos Gounakis
 *
 */
public class DestinationCardsOnHandArea extends JLayeredPane {

	private ArrayList<DestinationCardButton> cards;
	private JLabel areaName;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new DestinationCardsOnHandsArea objecet
	 * and initializes ist components<br>
	 */
	public DestinationCardsOnHandArea()
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBounds(0,0,340,137);
		
		// initializing the label
		areaName = new JLabel("Destination Tickets On Hand");
		areaName.setBounds(12, 115, 167, 16);
		add(areaName);
		
		// initializing the card buttons but they will be fully initialized when the game starts
		cards = new ArrayList<>();
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the arraylist must be returned<br>
	 * @return returns the arraylist of the buttons that represents the cards
	 */
	public ArrayList<DestinationCardButton> getCards()
	{
		return cards;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the areaName must be returned<br>
	 * @return returns the JLabel that represents the name of the area
	 */
	public JLabel getareaName()
	{
		return areaName;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>the parameter must be the correct Actionlistener<br>
	 * <b>Postcondition :</b>adds and action listener to the buton<br>
	 * @param choosedestcard the action listener to be added
	 */
	public void listenerChooseToPlayCard(ActionListener choosedestcard)
	{
		for(int i=0;i<cards.size();i++)
		{
			cards.get(i).addActionListener(choosedestcard);
		}
	}
}
