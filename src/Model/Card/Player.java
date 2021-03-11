package Model.Card;

import java.util.ArrayList;

import Model.Deck.OnTheTrack;
import Model.Deck.RailYard;

/**
 * <b>represents a player in the game</b><br>
 * @author Nikos Gounakis
 *
 */
public class Player {
	
	private ArrayList<TrainCard> TrainCardsOnHand;
	private ArrayList<DestinationCard> DestinationCardsOnHand; //must be 4 max
	private ArrayList<BigCitiesCard> MyBigCitiesCards;
	//added Mydestination tickets
	private ArrayList<DestinationCard> MyDestinationCards; 
	private RailYard railyard;
	private OnTheTrack onthetrack;
	private int score;
	boolean turn;
	
	/**
	 *<b>Constructor :</b><br>
	 *<b>Postcondition :</b>creates a new player<br>
	 */
	public Player()
	{
		railyard = new RailYard(this);
		onthetrack =  new OnTheTrack(this);
		score = 0;
		turn = false;
		TrainCardsOnHand = new ArrayList<>();
		DestinationCardsOnHand = new ArrayList<>();
		MyBigCitiesCards = new ArrayList<>();
		MyDestinationCards = new ArrayList<>();
	}
	
	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the list of the cards are returned<br>
	 * @return returns a list of cards that is in the hand of the player
	 */
	public ArrayList<TrainCard> getTrainCardsOnHand() {
		return TrainCardsOnHand;
	}
	
	/**
	 *<b>Tranformer :</b><br>
	 *<b>Precondition :</b>trainCardsOnHand must be a valid object<br>
	 *<b>Postcondition :</b>sets the cards of a player<br>
	 * @param trainCardsOnHand the list of the player's cards
	 */
	public void setTrainCardsOnHand(ArrayList<TrainCard> trainCardsOnHand) {
		TrainCardsOnHand = trainCardsOnHand;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the Destination cards are returned<br>
	 * @return returns a list of the Destination cards of the player
	 */
	public ArrayList<DestinationCard> getDestinationCardsOnHand() {
		return DestinationCardsOnHand;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>destinationCardsOnHand must be a valid object<br>
	 *<b>Postcondition :</b>sets the destination cards of a player<br>
	 * @param destinationCardsOnHand the list of the Destination cards of the player
	 */
	public void setDestinationCardsOnHand(ArrayList<DestinationCard> destinationCardsOnHand) {
		DestinationCardsOnHand = destinationCardsOnHand;
	}
	
	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the Destination cards are returned<br>
	 * @return returns a list of the redeemed Destination cards of the player
	 */
	public ArrayList<DestinationCard> getMyDestinationCards() {
		return MyDestinationCards;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>MydestinationCards must be a valid object<br>
	 *<b>Postcondition :</b>sets the destination cards of a player<br>
	 * @param MydestinationCards the list of the redeemed Destination cards of the player
	 */
	public void setMyDestinationCardsOnHand(ArrayList<DestinationCard> MydestinationCards) {
		MyDestinationCards = MydestinationCards;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the Railyard of the player is returned<br>
	 * @return returns the RailYard field of the player
	 */
	public RailYard getRailyard() {
		return railyard;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>railyard must be  a valid object<br>
	 *<b>Postcondition :</b>sets the value of the railyard<br>
	 * @param railyard the Raiyard of the player
	 */
	public void setRailyard(RailYard railyard) {
		this.railyard = railyard;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the onthetrack must be returned<br>
	 * @return returns the onthetrack field of the player
	 */
	public OnTheTrack getOnthetrack() {
		return onthetrack;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>onthetrack must be a valid object<br>
	 *<b>Postcondition :</b>sets the value of onthetrack<br>
	 * @param onthetrack the onthetrack field of the player
	 */
	public void setOnthetrack(OnTheTrack onthetrack) {
		this.onthetrack = onthetrack;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the score is returned<br>
	 * @return returns the current score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the list of the bigcitiescard is returned<br>
	 * @return returns the list of the bigcitiescard that the player owns
	 */
	public ArrayList<BigCitiesCard> getMyBigCitiesCards() {
		return MyBigCitiesCards;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>bigcitiescardsonhand must be a valid object<br>
	 *<b>Postcondition :</b>sets the list of big cities card<br>
	 * @param bigCitiesCardsOnHand the list of the bigcitiescards that the player owns
	 */
	public void setMyBigCitiesCardsOnHand(ArrayList<BigCitiesCard> MybigCitiesCardsOnHand) {
		MyBigCitiesCards = MybigCitiesCardsOnHand;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition :</b>the 'turn' is set to the value of turn<br>
	 * @param turn if it's the player's turn , turn must be true, otherwise false
	 */
	public void setTurn(boolean turn)
	{
		this.turn = turn;
	}
	
	/**
	 * <b>Observer :</b><br>
	 * <b>Postcondition :</b>turn must be returned<br>
	 * @return returns the value of turn that indicates of it's the player's turn
	 */
	public boolean getTurn()
	{
		return turn;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>the arraylists must not be empty and the cards must bre valid cards<br>
	 * <b>Postcondition :</b>the score must be updated<br>
	 * @param destcards the destination cards of the player
	 * @param bigcitiescard the big cities cards of the player
	 */
	public void updateScore()
	{
		score = 0;
		for(DestinationCard Dcard : DestinationCardsOnHand)
		{
			score-=Dcard.getScores();
		}
		for(DestinationCard redeemedDcard : MyDestinationCards)
		{
			score+=redeemedDcard.getScores();
		}
		for(BigCitiesCard Bcard : MyBigCitiesCards)
		{
			score+=Bcard.getScores();
		}
	}
	
}
