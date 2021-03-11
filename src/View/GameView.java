package View;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.text.AttributedCharacterIterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

import com.sun.javafx.tk.Toolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;

/**
 * <b>a class that represents the whole GUI of the game</b><br>
 * @author Nikos Gounakis
 *
 */
public class GameView extends JFrame {

	PlayerArea p1,p2; // areas of the players
	DeckArea deck;
	JMenu menu;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>Initializes the components of the calss and makes a new GameView object<br>
	 */
	public GameView()
	{
		menu = new JMenu();
		p1 = new PlayerArea();
		p2 = new PlayerArea();
		deck = new DeckArea();
		
		setTitle("Ticket To Ride The Game");
		setResizable(false);
		getContentPane().setLayout(null);
		setVisible(true);
		setSize(1280, 929);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p1.setBounds(0,549,1274,347);
		p2.setBounds(0,28,1274,347);
		menu.setBounds(0, 0, 1274, 30);

		getContentPane().add(p1);
		getContentPane().add(p2);
		getContentPane().add(deck);
		getContentPane().add(menu);
		
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>Player 1 area must be returned<br>
	 * @return returns the area of the player so we can manipulate it
	 */
	public PlayerArea getp1Area()
	{
		return p1;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>Player 2 area must be returned<br>
	 * @return returns the area of the player so we can manipulate it
	 */
	public PlayerArea getp2Area()
	{
		return p2;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the deck must be returned<br>
	 * @return returns the deck of the view
	 */
	public DeckArea getDeck()
	{
		return deck;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the menu must be returned<br>
	 * @return returns the menu JMenu
	 */
	public JMenu getMenu()
	{
		return menu;
	}
	
	
}
