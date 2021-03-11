package Model.Deck;

import java.util.ArrayList;
import java.util.Stack;

import Model.Card.BigCitiesCard;
import Model.Card.DestinationCard;
import Model.Card.Player;
import Model.Card.TrainCard;
import Model.Exception.InvalidMoveException;

/**
 * <b>a class that represent the central deck of the game</b><br>
 * @author Nikos Gounakis
 *
 */
public class Deck {

	private Stack<TrainCard> trainCards;
	private Stack<DestinationCard> destinationCards;
	private ArrayList<TrainCard> onDeckTrainCards;
	private ArrayList<BigCitiesCard> onDeckBigCitiesCards;
	
	/**
	 *<b>Constructor:</b><br>
	 *<b>Postcondition :</b>Creates a deck and initializes the stacks of traincards and destination cards<br>
	 */
	public Deck()
	{
		trainCards = new Stack<>();
		destinationCards = new Stack<>();
		onDeckBigCitiesCards = new ArrayList<>();
		onDeckTrainCards = new ArrayList<>();
	}
	
	/**
	 *<b>Transformer:</b><br>
	 *<b>Precondition :</b>p must be a valid object and the stack of train cards must not be empty<br>
	 *<b>Postcondition :</b>adds 2 or 1 train cards on the player hands<br>
	 * @param p the player who chose to get new cards
	 * @throws InvalidMoveException if the stack of cards is empty or there are no 5 cards on the deck
	 */
	public void getNewTrainCard(Player p) throws InvalidMoveException
	{
		if(trainCards.isEmpty()) throw new InvalidMoveException("The TrainCard stack is empty you cant draw cards");
		TrainCard card = trainCards.pop();
		card.setPlayer(p);
		p.getTrainCardsOnHand().add(card);
	}
	
	/**
	 *<b>Transformer:</b><br>
	 *<b>Precondition :</b>p must be a valid object and the stack of destination cards must not be empty<br>
	 *<b>Postcondition :</b>adds 1 destination cards on the player hands<br>
	 * @param p the player who chose to get new cards
	 * @param number the number of cards the player want to draw
	 * @throws InvalidMoveException if the stack is empty
	 */
	public void getNewDestinationCard(DestinationCard card,Player p) throws InvalidMoveException
	{
		card.setPlayer(p);
		p.getDestinationCardsOnHand().add(card);
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the traincards are returned<br>
	 * @return returns the stack of the traincards
	 */
	public Stack<TrainCard> getTrainCards() {
		return trainCards;
	}
	
	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>traincards must be a valid object<br>
	 *<b>Postcondition :</b>sets the 'trainCards' to trainCards<br>
	 * @param trainCards the stack of the train cards
	 */
	public void setTrainCards(Stack<TrainCard> trainCards) {
		this.trainCards = trainCards;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the destinationcards are returned<br>
	 * @return returns the stack of the destination cards
	 */
	public Stack<DestinationCard> getDestinationCards() {
		return destinationCards;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>destinationcards must be a valid object<br>
	 *<b>Postcondition :</b>sets the 'destinationCards' to destinationCards<br>
	 * @param destinationCards the stack of the train cards
	 */
	public void setDestinationCards(Stack<DestinationCard> destinationCards) {
		this.destinationCards = destinationCards;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the train cards on deck are returned<br>
	 * @return returns the list of the train cards on deck
	 */
	public ArrayList<TrainCard> getOnDeckTrainCards() {
		return onDeckTrainCards;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>onDeckTrainCards must be a valid object<br>
	 *<b>Postcondition :</b>sets the 'onDeckTrainCards' to onDeckTrainCards<br>
	 * @param onDeckTrainCards the list of the train cards on deck
	 */
	public void setOnDeckTrainCards(ArrayList<TrainCard> onDeckTrainCards) {
		this.onDeckTrainCards = onDeckTrainCards;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the big cities cards on deck are returned<br>
	 * @return returns the list of the big cities cards on deck
	 */
	public ArrayList<BigCitiesCard> getOnDeckBigCitiesCards() {
		return onDeckBigCitiesCards;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>onDeckTrainCards must be a valid object<br>
	 *<b>Postcondition :</b>sets the 'onDeckBigCitiesCards' to onDeckBigCitiesCards<br>
	 * @param onDeckBigCitiesCards the list of the big cities cards on deck
	 */
	public void setOnDeckBigCitiesCards(ArrayList<BigCitiesCard> onDeckBigCitiesCards) {
		this.onDeckBigCitiesCards = onDeckBigCitiesCards;
	}
	
}
