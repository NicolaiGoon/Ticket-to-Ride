package View;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.Card.CardColor;
/**
 * <b>a class that represents a TrainCard as a button<b><br>
 * @author Nikos Gounakis
 *
 */
public class TrainCardButton extends JButton {

	CardColor color;
	boolean Up;
	
	/**
	 * <b>Constructor :<b><br>
	 * <b>Precondition :<b>color must be a valid CardColor<br>
	 * <b>Postcondition :<b>creates a new train card button<br>
	 * @param color
	 */
	public TrainCardButton(CardColor color)
	{
		Up = false;
		this.color = color;
		URL imageUrl = TrainCardButton.class.getResource("/resources/images/trainCards/"+color.toString()+".jpg");
		Image cardImage = new ImageIcon(imageUrl).getImage();
		cardImage = cardImage.getScaledInstance(70, 100,Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(cardImage));
		setBounds(0,0,70,100);
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>color must be returned<b>
	 * @return returns the color of the cardbutton
	 */
	public CardColor getColor()
	{
		return color;
	}
	
	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>sets the image of the button according to the color<br>
	 * @param color the color of the image that the card button will have
	 */
	public void setImageAndColor(CardColor color)
	{
		this.color = color;
		URL imageUrl = TrainCardButton.class.getResource("/resources/images/trainCards/"+color.toString()+".jpg");	
		Image cardImage = new ImageIcon(imageUrl).getImage();
		cardImage = cardImage.getScaledInstance(70, 100,Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(cardImage));
	}
	
	/**
	 * <b>Observer :<b><br>
	 * @return returns true if the card is up to be played , false otherwise
	 */
	public boolean isUp()
	{
		if(this.Up) return true;
		else return false;
	}
	
	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>sets the card up or down<br>
	 * @param flag if flag os true the card button is set up , other wise set down
	 */
	public void setUp(boolean flag)
	{
		this.Up = flag;
	}
}
