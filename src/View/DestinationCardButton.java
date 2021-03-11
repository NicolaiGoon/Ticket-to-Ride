package View;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * <b>a Class that represent a Destination Card as a Button<b><br>
 * @author Nikos Gounakis
 *
 */
public class DestinationCardButton extends JButton {
	
	int id;
	
	/**
	 * <b>Constructor :<b><br>
	 * <b>Postcondition :<b>creates a new destination card button
	 * with id so it will be connected to its image<br>
	 * @param id the id of the card that the button represents
	 * @param imageUrl the url of the image of the button
	 */
	public DestinationCardButton(int id,URL imageUrl)
	{
		this.id = id;
		Image destinationImage = new ImageIcon(imageUrl).getImage();
		destinationImage = destinationImage.getScaledInstance(70, 100,Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(destinationImage));
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>returns the id of the card<br>
	 * @return
	 */
	public int getid()
	{
		return id;
	}

}
