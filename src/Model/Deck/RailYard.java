package Model.Deck;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import Model.Card.*;
import Model.Exception.InvalidMoveException;

/**
 * <b>a class that represents the RailYard field of a player</b><br>
 * @author Nikos Gounakis
 *
 */
public class RailYard {
	private Player player;
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
	 * <b>Precondition :</b>p must be a valid object and not null<br>
	 * <b>Postcondition :</b>creates a new RailYard with empty stacks of cards and belongs to player p<br>
	 * @param p the player that the RailYard will belong to
	 * @throws NullPointerException if p is null
	 */
	public RailYard(Player p) throws NullPointerException
	{
		if(p == null) throw new NullPointerException("RailYard must belong to a player");
		player = p;
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
	 * <b>Precondition :</b>color must be one of the 8 colors of cards,card must be a valid TrainCard object<br>
	 * <b>Postcondition :</b>pushes in the specific color stack the TrainCard<br>
	 * @param color the color of the card
	 * @param card the object that represents a TrainCard
	 */
	public void setCards(TrainCard card,CardColor color)
	{
		switch(color)
		{
			case red :
			{
				red.push(card);
				break;
			}
			case black :
			{
				black.push(card);
				break;
			}
			case blue :
			{
				blue.push(card);
				break;
			}
			case green :
			{
				green.push(card);
				break;
			}
			case purple :
			{
				purple.push(card);
				break;
			}
			case white :
			{
				white.push(card);
				break;
			}
			case yellow :
			{
				yellow.push(card);
				break;
			}
			case orange :
			{
				orange.push(card);
				break;
			}
		}
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Precondition :</b>color must be one of the colors of the game<br>
	 * <b>Postcondition :</b>one of the 8 stacks must be returned<br>
	 * @param color the name of the stack that will be returned
	 * @return returns the stack of the 'color' color
	 * @throws InvalidMoveException if the color is locomotive
	 */
	public Stack<TrainCard> getCards(CardColor color) throws InvalidMoveException
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
			default :
			{
				throw new InvalidMoveException("there is no locomotve stack in RailYard");
			}
		}
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>player must have chosen some cards to play , oponent's RailYard must be a valid object<br>
	 * <b>Postcondition :</b>checks if the cards can be trasfered to OnTheTrack and then moves the card to OnTheTrack, uses trainRobbing to check for TrainRobbing<br>
	 * @param cards the cards that the player has chosen to play
	 * @param oponent the oponent's RailYard
	 * @throws InvalidMoveException if the player has not chosen cards
	 */
	public void playCards(ArrayList<TrainCard> cards,RailYard oponent) throws InvalidMoveException
	{
		// playing 3 cards different colors
		if(cards.size() ==  3 && (cards.get(0).getColor() != cards.get(1).getColor() && cards.get(0).getColor() != cards.get(2).getColor() && cards.get(1).getColor() != cards.get(2).getColor()))
		{
			for(int i = 0;i < cards.size();i++)
			{ 
				CardColor color = cards.get(i).getColor();
				
				if(color == CardColor.locomotive) throw new InvalidMoveException("locomotives can be played only in same color cards");
				
				String e = "The oponent's or your RailYard has already a "+color.toString()+" card you are playing";
				switch(color)
				{
				case black:
					if(!oponent.black.isEmpty() || !black.isEmpty()) throw new InvalidMoveException(e);
					break;
				case blue:
					if(!oponent.blue.isEmpty() || !blue.isEmpty()) throw new InvalidMoveException(e);
					break;
				case green:
					if(!oponent.green.isEmpty() || !green.isEmpty()) throw new InvalidMoveException(e);
					break;
				case orange:
					if(!oponent.orange.isEmpty() || !orange.isEmpty()) throw new InvalidMoveException(e);
					break;
				case purple:
					if(!oponent.purple.isEmpty() || !purple.isEmpty()) throw new InvalidMoveException(e);
					break;
				case red:
					if(!oponent.red.isEmpty() || !red.isEmpty() ) throw new InvalidMoveException(e);
					break;
				case white:
					if(!oponent.white.isEmpty() || !white.isEmpty()) throw new InvalidMoveException(e);
					break;
				case yellow:
					if(!oponent.yellow.isEmpty() || !yellow.isEmpty()) throw new InvalidMoveException(e);
					break;
				}
			}
			
			// now that the arraylist has passed all the checks it is time to make the transfer
			
			// removing from player's hand
			for(TrainCard card : cards)
			{
				CardColor color = card.getColor();
				for(TrainCard j : this.player.getTrainCardsOnHand())
				{
					if(color == j.getColor())
					{
						this.player.getTrainCardsOnHand().remove(j);
						break;
					}
				}
				
				// adding to RailYard
				switch(color)
				{
				case black:
					black.push(card);
					break;
				case blue:
					blue.push(card);
					break;
				case green:
					green.push(card);
					break;
				case orange:
					orange.push(card);
					break;
				case purple:
					purple.push(card);
					break;
				case red:
					red.push(card);
					break;
				case white:
					white.push(card);
					break;
				case yellow:
					yellow.push(card);
					break;
				}
			
			}
		}
		// playing 2 or more cards of the same color
		else if(cards.size() >= 2)
		{
			boolean shouldRemoveCards = false;
			//----checking for only locomotive cards----//
			int locomotiveCards = 0;
			// the first color is not locomotive because locomotives always are last
			CardColor color = cards.get(0).getColor();
			
			for(TrainCard i : cards)
			{
				if(i.getColor() == CardColor.locomotive)
				{
					locomotiveCards++;
				}
				else if(i.getColor() != color)
				{
					throw new InvalidMoveException("the cards you want to play are not the same color");
				}
			}
			
			if(locomotiveCards == cards.size()) throw new InvalidMoveException("you cant play only locomotive cards");
			
			//-------------------checking for train robbing-------------------//	
			
			// display message
			if(trainRobbing(cards,oponent,color) && !oponent.getCards(color).isEmpty()) JOptionPane.showMessageDialog(null,"TrainRobbing on color : "+color.toString(),"TrainRobbing", JOptionPane.INFORMATION_MESSAGE);
			
				// checking if the cards can be played and if trainRobbing is happening oponent's cards with color 'color' are removed
				switch(color)
				{
				case black:
					if(black.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.black.isEmpty())
						{
							for(TrainCard card : cards)
							{
								black.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.black.clear();
								for(TrainCard card : cards)
								{
									black.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!black.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case blue:
					if(blue.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.blue.isEmpty())
						{
							for(TrainCard card : cards)
							{
								blue.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.blue.clear();
								for(TrainCard card : cards)
								{
									blue.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!blue.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case green:
					if(green.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.green.isEmpty())
						{
							for(TrainCard card : cards)
							{
								green.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.green.clear();
								for(TrainCard card : cards)
								{
									green.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!green.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case orange:
					if(orange.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.orange.isEmpty())
						{
							for(TrainCard card : cards)
							{
								orange.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.orange.clear();
								for(TrainCard card : cards)
								{
									orange.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!orange.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case purple:
					if(purple.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.purple.isEmpty())
						{
							for(TrainCard card : cards)
							{
								purple.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.purple.clear();
								for(TrainCard card : cards)
								{
									purple.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!purple.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case red:
					if(red.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.red.isEmpty())
						{
							for(TrainCard card : cards)
							{
								red.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.red.clear();
								for(TrainCard card : cards)
								{
									red.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!red.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case white:
					if(white.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.white.isEmpty())
						{
							for(TrainCard card : cards)
							{
								white.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.white.clear();
								for(TrainCard card : cards)
								{
									white.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!white.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				case yellow:
					if(yellow.isEmpty())
					{
						// empty railyard just playing the cards
						if(oponent.yellow.isEmpty())
						{
							for(TrainCard card : cards)
							{
								yellow.push(card);
							}
							shouldRemoveCards = true;
						}
						// checking for train robbing
						else
						{
							if(trainRobbing(cards,oponent,color))
							{
								oponent.yellow.clear();
								for(TrainCard card : cards)
								{
									yellow.push(card);
								}
								shouldRemoveCards = true;
							}
							else
							{
								throw new InvalidMoveException("the oponent has more "+color.toString()+" cards on his RailYard");
							}
						}
					}
					else if(!yellow.isEmpty())
					{
						throw new InvalidMoveException("you already have "+color.toString()+" cards in your RailYard");
					}
					break;
				}
				
			if(shouldRemoveCards)
			{
				// removing cards from player's hands //
				
				for(TrainCard playCard : cards)
				{
					player.getTrainCardsOnHand().remove(playCard);
				}
			}
			
		}
		else
		{
			throw new InvalidMoveException("The cards that are selected are not valid");
		}
	}
	
	/**
	 * <b>Observer :</b><br>
	 * <b>Precondition :</b>cards must not be empty , oponent's RailYard must be a valid object<br>
	 * <b>Postcondition :</b>checks for TrainRobbing<br>
	 * @return true if TrainRobbing is happening , false otherwise
	 */
	public boolean trainRobbing(ArrayList<TrainCard> cards,RailYard oponent,CardColor color)
	{
		switch(color)
		{
		case black:
			if(oponent.black.size() < cards.size()) return true;
			else return false;
		case blue:
			if(oponent.blue.size() < cards.size()) return true;
			else return false;
		case green:
			if(oponent.green.size() < cards.size()) return true;
			else return false;
		case orange:
			if(oponent.orange.size() < cards.size()) return true;
			else return false;
		case purple:
			if(oponent.purple.size() < cards.size()) return true;
			else return false;
		case red:
			if(oponent.red.size() < cards.size()) return true;
			else return false;
		case white:
			if(oponent.white.size() < cards.size()) return true;
			else return false;
		case yellow:
			if(oponent.yellow.size() < cards.size()) return true;
			else return false;
		default:
			return false;
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
	
}
