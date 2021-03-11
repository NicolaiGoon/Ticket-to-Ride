package View;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;

//including this enum only for color reasons
import Model.Card.CardColor;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * <b> a calss that represents the GUI of the RailYard area of the player</b><br>
 * @author Nikos Gounakis
 *
 */
public class RailYardArea extends JLayeredPane {
	
	private ArrayList<JLabel> red;
	private ArrayList<JLabel> black;
	private ArrayList<JLabel> blue;
	private ArrayList<JLabel> green;
	private ArrayList<JLabel> purple;
	private ArrayList<JLabel> white;
	private ArrayList<JLabel> yellow;
	private ArrayList<JLabel> orange;
	private JLabel colornames , areaname;
	private JButton movetoOnTheTrack;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new RailYardArea object and initializes its components<br>
	 */
	public RailYardArea()
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBounds(0,0,657,171);
		
		// initializing area name label
		areaname =  new JLabel("RailYard");
		areaname.setBounds(12, 142,53, 16);
		add(areaname);
		
		// initializing color names 
		colornames = new JLabel("red               	     black      	  	     blue                 green                purple   	            white                     yellow                   orange");
		colornames.setBounds(12,13,633,16);
		add(colornames);
		
		// initializing button
		movetoOnTheTrack = new JButton("Move Cards to On-The-Track");
		movetoOnTheTrack.setBounds(439,140,206,25);
		add(movetoOnTheTrack);
		
		// initializing arraylists
		red = new ArrayList<>();
		black = new ArrayList<>();
		blue = new ArrayList<>();
		green = new ArrayList<>();
		purple = new ArrayList<>();
		white = new ArrayList<>();
		yellow = new ArrayList<>();
		orange = new ArrayList<>();
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Precondition :</b>color must be a valid CardColor<br>
	 * <b>Postcondition :</b>1 of 8 arraylists must be returned<br>
	 * @param color the color of the arraylist
	 * @return returns the arraylist with 'color' color
	 */
	public ArrayList<JLabel> getCardList(CardColor color)
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
		default:
			return null;
		}
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>the button movetoOnTheTrack must be returned<br>
	 * @return returns the button that move the cards to one the track
	 */
	public JButton getmovetoOnTheTrackButton()
	{
		return movetoOnTheTrack;
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>colornames must be returned<br>
	 * @return returns the colornames that represent
	 *  all the name of the colors in the raiyard
	 */
	public JLabel getColorNames()
	{
		return colornames;
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>area name must be returned<br>
	 * @return returns the area name
	 */
	public JLabel getAreaName()
	{
		return areaname;
	}
	
	/**
	 * <b>Accessor :<b><br>
	 * <b>Postcondition :<b>adds an action listener to the button
	 * that moves the cards to on the track<br>
	 * @param onthetrack the action listener to be added
	 */
	public void listeMoveToOntheTrack(ActionListener onthetrack)
	{
		movetoOnTheTrack.addActionListener(onthetrack);
	}
	
}
