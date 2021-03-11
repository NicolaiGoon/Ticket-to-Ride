package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * <b>a class that represents the GUI of the Player info area in the game</b><br>
 * @author Nikos Gounakis
 *
 */
public class PlayerInfoArea extends JLayeredPane {

	private JLabel playerInfo;
	private JLabel playerTurn;
	private JLabel playerScore;
	private JButton myDestTickets;
	private JButton myBigCitiesCards;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>creates a new PlayerInfoArea Object and initializes its components<br>
	 */
	public PlayerInfoArea()
	{
		setBorder(new LineBorder(Color.BLACK, 2));
		setBounds(0,0,229,137);
		
		playerInfo =  new JLabel("Player ScoreBoard");
		playerInfo.setBounds(12,13,180,16);
		add(playerInfo);
		
		playerTurn =  new JLabel("Player Turn : ");
		playerTurn.setBounds(12,31,170,20);
		add(playerTurn);
		
		playerScore = new JLabel("Score : ");
		playerScore.setBounds(12,54,94,16);
		add(playerScore);
		
		myDestTickets =  new JButton("My Destination Tickets");
		myDestTickets.setBounds(12, 79, 193, 23);
		add(myDestTickets);
		
		myBigCitiesCards =  new JButton("My Big Cities Cards");
		myBigCitiesCards.setBounds(12,101,193,23);
		add(myBigCitiesCards);
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the player info must be returned<br>
	 * @return retunrs the JLabel that represents the player name 
	 */
	public JLabel getplayerInfo()
	{
		return playerInfo;	
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>sets the player info jlabel to the name of the player<br>
	 * @param player the number of the player (player 1,player 2)
	 */
	public void setPlayerInfo(int player)
	{
		playerInfo.setText("Player "+player+" ScoreBoard");
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>player turns must be returned<br>
	 * @return returns the JLabel that represents the player's turn
	 */
	public JLabel getplayerTurn()
	{
		return playerTurn;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>sets the turn of the player to true or false<br>
	 * @param turn if turn is true the turn is set to yes ,otherwise no
	 */
	public void updatePlayerTurn(boolean turn)
	{
		if(turn)
		{
			playerTurn.setText("Player Turn : Yes");
		}
		else
		{
			playerTurn.setText("Player Turn : No");
		}
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the playerscore must be returned<br>
	 * @return returns the JLabel that represents the player's score
	 */
	public JLabel getplayerScore()
	{
		return playerScore;	
	}
	
	/**
	 * <b>Transformer</b><br>
	 * <b>Postcondition</b>updates the score<br>
	 * @param score the current score of the player
	 */
	public void updateScore(int score)
	{
		playerScore.setText("Score : "+score);
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>myDestTickets must be returned<br>
	 * @return returns the JButton that it is used to display the Destination Cards
	 */
	public JButton getmyDestTickets()
	{
		return myDestTickets;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>myBigCitiesCards must be returned<br>
	 * @return returns the JButton that it is used to display the big cities cards
	 */
	public JButton getmyBigCitiesCards()
	{
		return myBigCitiesCards;
	}
	
	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>adds an action listener to MyDestinationCards button<br>
	 * @param desttickets the action listener to be added
	 */
	public void listenMydestTickets(ActionListener desttickets)
	{
		myDestTickets.addActionListener(desttickets);
	}
	
	/**
	 * <b>Transformer :<b><br>
	 * <b>Postcondition :<b>add an action listener to MyBigCities button<br>
	 * @param bigcities the action listener to be added
	 */
	public void listenMyBigCities(ActionListener bigcities)
	{
		myBigCitiesCards.addActionListener(bigcities);
	}
}

