package Model.Deck;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.addressing.model.InvalidAddressingHeaderException;

import Model.Card.BigCitiesCard;
import Model.Card.CardColor;
import Model.Card.DestinationCard;
import Model.Card.Player;
import Model.Card.TrainCard;
import Model.Exception.InvalidMoveException;

/**
 * <b>a class that represents the OnTheTrack field of a player</b><br>
 * @author Nikos Gounakis
 */
public class OnTheTrack{
	
	private Player player;
	private Stack<TrainCard> locomotive;
	private Stack<TrainCard> red;
	private Stack<TrainCard> black;
	private Stack<TrainCard> blue;
	private Stack<TrainCard> green;
	private Stack<TrainCard> purple;
	private Stack<TrainCard> white;
	private Stack<TrainCard> yellow;
	private Stack<TrainCard> orange;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Precondition :</b>p must be player and not null<br>
	 * <b>Postcondition :</b>creates an OnTheTrack object<br>
	 * @param p the player that the OnTheTrack belongs
	 * @throws NullPointerException if p is null
	 */
	public OnTheTrack(Player p) throws NullPointerException {
		
		if(p == null) throw new NullPointerException("player is null");
		player = p;
		locomotive = new Stack<>();
		red = new Stack<>();
		black = new Stack<>();
		blue = new Stack<>();
		green = new Stack<>();
		purple =  new Stack<>();
		white = new Stack<>();
		yellow =  new Stack<>();
		orange = new Stack<>();
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>railyard must be a valid object<br>
	 * <b>Postcondition :</b>collects 1 card for every color of cards<br>
	 * @param railyard the RailYard of the player
	 */
	public void collectFromRailYard(RailYard railyard)
	{
		ArrayList<CardColor> colors = new ArrayList<>();
		colors.add(CardColor.black);
		colors.add(CardColor.blue);
		colors.add(CardColor.green);
		colors.add(CardColor.red);
		colors.add(CardColor.purple);
		colors.add(CardColor.white);
		colors.add(CardColor.yellow);
		colors.add(CardColor.orange);
		for(CardColor color : colors)
		{
			
			if(!railyard.getCards(color).isEmpty() && railyard.getCards(color).lastElement().getColor() == CardColor.locomotive)
			{
					locomotive.push(railyard.getCards(color).pop());
			}
			else if(!railyard.getCards(color).isEmpty())
			{
				switch(color)
				{
				case black:
					black.push(railyard.getCards(color).pop());
					break;
				case blue:
					blue.push(railyard.getCards(color).pop());
					break;
				case green:
					green.push(railyard.getCards(color).pop());
					break;
				case orange:
					orange.push(railyard.getCards(color).pop());
					break;
				case purple:
					purple.push(railyard.getCards(color).pop());
					break;
				case red:
					red.push(railyard.getCards(color).pop());
					break;
				case white:
					white.push(railyard.getCards(color).pop());
					break;
				case yellow:
					yellow.push(railyard.getCards(color).pop());
					break;
				}
			}
		}
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>OnTheTrack must have at least the number of cards 
	 * of each color that are demanded to buy the Destination card<br>
	 * <b>Postcondition :</b>the DestinationCard and OnTheTrack cards that 
	 * were used to buy it are off the game and the score is updated<br>
	 * @param card the Destination card to be bought
	 * @param p if p is false the function will access p1 visits of bigcities cards , else p2
	 * @param Deck the central deck for accessing the bonus cards
	 * @throws InvalidMoveException if OnTheTrack has not enough cards to buy a DestinationCard
	 */
	public void buyDestinationCard(DestinationCard card,boolean p,Deck deck) throws InvalidMoveException
	{
		// colors of the card
		ArrayList<CardColor>neededColors = card.getColors();
		// colors that are missing from player's OnTheTrack and can be replaced by locomotive cards
		ArrayList<CardColor>missingColors = new ArrayList<>();
		
		// checking if the player has the colors to buy the destination card
		for(int i=0;i<neededColors.size();i++)
		{
			switch(neededColors.get(i))
			{
			case black:
				if(black.isEmpty())
				{
					missingColors.add(CardColor.black);
				}
				break;
			case blue:
				if(blue.isEmpty())
				{
					missingColors.add(CardColor.blue);
				}
				break;
			case green:
				if(green.isEmpty())
				{
					missingColors.add(CardColor.green);
				}
				break;
			case orange:
				if(orange.isEmpty())
				{
					missingColors.add(CardColor.orange);
				}
				break;
			case purple:
				if(purple.isEmpty())
				{
					missingColors.add(CardColor.purple);
				}
				break;
			case red:
				if(red.isEmpty())
				{
					missingColors.add(CardColor.red);
				}
				break;
			case white:
				if(white.isEmpty())
				{
					missingColors.add(CardColor.white);
				}
				break;
			case yellow:
				if(yellow.isEmpty())
				{
					missingColors.add(CardColor.yellow);
				}
				break;
			}
		}
			
			// if the player can buy the card even with the help of locomotive cards then he buys it
			if(missingColors.size() == 0 || locomotive.size() >= missingColors.size())
			{
				
				// if the player has enough visits the BigCityCard is redeemed
				for(BigCitiesCard Bcard : deck.getOnDeckBigCitiesCards())
				{
					if(card.getFrom().equals(Bcard.getCity()) || card.getTo().equals(Bcard.getCity()))
					{
						// updating visits
						Bcard.setNumberOfvisits(p);
						if(Bcard.getNumberOfvisits(p) == 3 && !Bcard.isActive())
						{
							Bcard.setPlayer(getPlayer());
							getPlayer().getMyBigCitiesCards().add(Bcard);
							getPlayer().updateScore();
							// message
							JOptionPane.showMessageDialog(null,"You have succesfully redeemed :\n "+Bcard.toString(),"Bonus",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				
				for(int j=0;j<neededColors.size();j++)
				{
					switch(neededColors.get(j))
					{
						case black:
							if(!black.isEmpty())
							{
								black.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case blue:
							if(!blue.isEmpty())
							{
								blue.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case green:
							if(!green.isEmpty())
							{
								green.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case orange:
							if(!orange.isEmpty())
							{
								orange.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case purple:
							if(!purple.isEmpty())
							{
								purple.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case red:
							if(!red.isEmpty())
							{
								red.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case white:
							if(!white.isEmpty())
							{
								white.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
						case yellow:
							if(!yellow.isEmpty())
							{
								yellow.pop();
							}
							else
							{
								locomotive.pop();
							}
							break;
					}
				}
				
				// removing the card from player's hand
				for(int Dcard = 0;Dcard < getPlayer().getDestinationCardsOnHand().size();Dcard++)
				{
					if(card.getId() ==  getPlayer().getDestinationCardsOnHand().get(Dcard).getId())
					{
						getPlayer().getMyDestinationCards().add(getPlayer().getDestinationCardsOnHand().get(Dcard));
						getPlayer().getDestinationCardsOnHand().remove(Dcard);
						getPlayer().updateScore();
						break;
					}
				}
			}
			else
			{
				// if the player has not the colors the card goes back to his hand
				throw new InvalidMoveException("you dont have the colors : "+missingColors.toString()+" in your OnTheTrack area");
			}
		}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the player is returned<br>
	 * @return returns the player that OnTheTrack belongs
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>player must be a valid object adn not null<br>
	 * <b>Postcondition :</b>sets 'player' to the value of player<br>
	 * @param player the plays that OnTheTrack belongs
	 * @throws NullPointerException if p is null
	 */
	public void setPlayer(Player player) throws NullPointerException {
		if(player == null) throw new NullPointerException("player must not be null");
		this.player = player;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Precondition :</b>color must be one of the colors of the game<br>
	 * <b>Postcondition :</b>one of the 8 stacks must be returned<br>
	 * @param color the name of the stack that will be returned
	 * @return returns the stack of the 'color' color , is the color does not exits returns null
	 */
	public Stack<TrainCard> getCards(CardColor color)
	{
		switch(color)
		{
			case red :
			{
				return red;
			}
			case black :
			{
				return black;
			}
			case blue :
			{
				return blue;
			}
			case green :
			{
				return green;
			}
			case purple :
			{
				return purple;
			}
			case white :
			{
				return white;
			}
			case yellow :
			{
				return yellow;
			}
			case orange:
			{
				return orange;
			}
			case locomotive:
			{
				return locomotive;
			}
			default :
			{
				return null;
			}
		}
	}
		
}


