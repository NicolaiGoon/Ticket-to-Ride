package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Model.Card.Card;
import Model.Card.CardColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.SwingConstants;

/**
 * <b>a class that represents the GUI of OnTheTrack area of the player</b><br>
 * @author Nikos Gounakis
 *
 */
public class OnTheTrackArea extends JLayeredPane{

	private JLabel red;
	private JLabel black;
	private JLabel blue;
	private JLabel green;
	private JLabel purple;
	private JLabel white;
	private JLabel yellow;
	private JLabel orange;
	private JLabel locomotive;
	private JLabel areaName;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new onTheTrack area<br>
	 */
	public OnTheTrackArea()
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBounds(0,0,581,171);
		
		// red cards
		red = new JLabel("red : ");
		red.setBounds(12,13,101,60);
		URL redUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/red.jpg");
		Image redimage = new ImageIcon(redUrl).getImage();
		redimage = redimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		red.setIcon(new ImageIcon(redimage));
		add(red);
		
		// black cards
		black = new JLabel("black : ");
		black.setBounds(117,13,113,60);
		URL blackUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/black.jpg");
		Image blackimage = new ImageIcon(blackUrl).getImage();
		blackimage = blackimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		black.setIcon(new ImageIcon(blackimage));
		add(black);
		
		// blue cards
		blue = new JLabel("blue : ");
		blue.setBounds(12,98,101,60);
		URL blueUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/blue.jpg");
		Image blueimage = new ImageIcon(blueUrl).getImage();
		blueimage = blueimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		blue.setIcon(new ImageIcon(blueimage));
		add(blue);
		
		// green cards
		green = new JLabel("green : ");
		green.setBounds(117,98,113,60);
		URL greenUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/green.jpg");
		Image greenimage = new ImageIcon(greenUrl).getImage();
		greenimage = greenimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		green.setIcon(new ImageIcon(greenimage));
		add(green);
		
		// purple cards
		purple = new JLabel("purple : ");
		purple.setBounds(226,13,113,60);
		URL purpleUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/purple.jpg");
		Image purpleimage = new ImageIcon(purpleUrl).getImage();
		purpleimage = purpleimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		purple.setIcon(new ImageIcon(purpleimage));
		add(purple);
		
		// white cards
		white = new JLabel("white : ");
		white.setBounds(226,98,113,60);
		URL whiteUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/white.jpg");
		Image whiteimage = new ImageIcon(whiteUrl).getImage();
		whiteimage = whiteimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		white.setIcon(new ImageIcon(whiteimage));
		add(white);
		
		// yellow cards
		yellow = new JLabel("yellow : ");
		yellow.setBounds(339,13,113,60);
		URL yellowUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/yellow.jpg");
		Image yellowimage = new ImageIcon(yellowUrl).getImage();
		yellowimage = yellowimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		yellow.setIcon(new ImageIcon(yellowimage));
		add(yellow);
		
		// orange cards
		orange = new JLabel("orange : ");
		orange.setBounds(339,98,127,60);
		URL orangeUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/orange.jpg");
		Image orangeimage = new ImageIcon(orangeUrl).getImage();
		orangeimage = orangeimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		orange.setIcon(new ImageIcon(orangeimage));
		add(orange);
		
		// locomotive cards
		locomotive = new JLabel("locomotive : ");
		locomotive.setBounds(433,58,136,60);
		URL locomotiveUrl = OnTheTrackArea.class.getResource("/resources/images/trainCards/locomotive.jpg");
		Image locomotiveimage = new ImageIcon(locomotiveUrl).getImage();
		locomotiveimage = locomotiveimage.getScaledInstance(50,60, Image.SCALE_SMOOTH);
		locomotive.setIcon(new ImageIcon(locomotiveimage));
		add(locomotive);
		
		areaName = new JLabel("On-The-Track");
		areaName.setHorizontalAlignment(SwingConstants.CENTER);
		areaName.setBounds(478,135,91,23);
		add(areaName);
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Precondition :</b><br>
	 * <b>Postcondition :</b><br>
	 * @param color
	 * @return
	 */
	public JLabel getLabel(CardColor color)
	{
		switch(color)
		{
		case black:
			return black;
		case blue:
			return blue;
		case green:
			return green;
		case orange:
			return orange;
		case purple:
			return purple;
		case red:
			return red;
		case white:
			return white;
		case yellow:
			return yellow;
		case locomotive:
			return locomotive;
		default:
			return null;
		}
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the areaName must be returned<br>
	 * @return returns the JLabel that represents the area name
	 */
	public JLabel getareaName()
	{
		return areaName;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>color must be a valid color , num must be a valid number<br>
	 * <b>Postcondition :</b>updates the label of color 'color' with the current number of cards in it<br>
	 * @param color the color of the stack
	 * @param num the number of cards in the stack
	 */
	public void updateNumberOfCards(CardColor color,int num)
	{
		switch(color)
		{
		case black:
			black.setText("black : "+num);
			break;
		case blue:
			blue.setText("blue : "+num);
			break;
		case green:
			green.setText("green : "+num);
			break;
		case locomotive:
			locomotive.setText("locomotive : "+num);
			break;
		case orange:
			orange.setText("orange : "+num);
			break;
		case purple:
			purple.setText("purple : "+num);
			break;
		case red:
			red.setText("red : "+num);
			break;
		case white:
			white.setText("white : "+num);
			break;
		case yellow:
			yellow.setText("yellow : "+num);
			break;
		}
	}
}
