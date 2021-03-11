package Test;

import junit.framework.Test;
import org.junit.*;
import junit.framework.TestCase;
import Model.Card.*;
import Model.Deck.*;
import Model.Exception.*;

import java.io.IOException;
import java.util.ArrayList;

import Controller.*;
import View.*;

public class testerClass extends TestCase {
	
	public void testToStringCards()
	{
		// train card to string 
		TrainCard Tcard = new TrainCard(null, "/resources/images/trainCards/red.jpg", CardColor.red);
		// Destination card to String
		ArrayList<CardColor> colors = new ArrayList<>();
		colors.add(CardColor.blue);
		DestinationCard Dcard = new DestinationCard(null, "/resources/images/destination_Tickets/desCard1",colors, 12, 1,"Chicago", "New York");
		// BigCitie card to String 
		BigCitiesCard Bcard = new BigCitiesCard(null, "/resources/images/bigCitiesCards/Chicago",12 ,"Chicago");
		System.out.println(Tcard);
		System.out.println(Dcard);
		System.out.println(Bcard);
	}
	
	public void testRailYardValidMoves()
	{
		Player p1 =  new Player();
		Player p2 = new Player();
		
		// adding 3 different cards to p1 hands
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		p1.getTrainCardsOnHand().add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		p1.getTrainCardsOnHand().add(new TrainCard(p1, "/resources/images/trainCards/black.jpg", CardColor.black));
		
		// the cards that the player has chosen to play are saved in a different list
		ArrayList<TrainCard> chosenCards = new ArrayList<>();
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/black.jpg", CardColor.black));
		
		// playing cards
		p1.getRailyard().playCards(chosenCards, p2.getRailyard());
		// checking if the cards have arrived in the RailYard
		assertEquals(true, !p1.getRailyard().getCards(CardColor.red).isEmpty() && !p1.getRailyard().getCards(CardColor.blue).isEmpty() && !p1.getRailyard().getCards(CardColor.black).isEmpty());
		// checking if the player cards on hand is empty
		assertEquals(true, p1.getTrainCardsOnHand().isEmpty());
		
		// adding 2 cards of the same color
		p1.getTrainCardsOnHand().add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		p1.getTrainCardsOnHand().add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		
		// resetting chosen cards
		chosenCards.clear();
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/blue.jpg", CardColor.blue));
		// playing cards
		// removing blue cards from railyard so we can play blue cards
		p1.getRailyard().getCards(CardColor.blue).removeAllElements();
		p1.getRailyard().playCards(p1.getTrainCardsOnHand(), p2.getRailyard());
		assertEquals(true,p1.getRailyard().getCards(CardColor.blue).size() == 2);
		
		// testing train robbing , adding 2 black cards on p2 railyard
		p2.getRailyard().getCards(CardColor.black).add(new TrainCard(p2,"/resources/images/trainCards/black.jpg", CardColor.black));
		p2.getRailyard().getCards(CardColor.black).add(new TrainCard(p2,"/resources/images/trainCards/black.jpg", CardColor.black));
		// adding play cards
		p1.getRailyard().getCards(CardColor.black).removeAllElements();
		chosenCards = new ArrayList<>();
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/black.jpg", CardColor.black));
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/black.jpg", CardColor.black));
		chosenCards.add(new TrainCard(p1, "/resources/images/trainCards/locomotive.jpg", CardColor.locomotive));
		p1.getRailyard().playCards(chosenCards, p2.getRailyard());
		assertEquals(true, p2.getRailyard().getCards(CardColor.black).isEmpty() && (p1.getRailyard().getCards(CardColor.black).size() == 3));
		
	}
	
	public void testRailYardInvalidMoves() throws InvalidMoveException
	{
		Player p1 =  new Player();
		Player p2 = new Player();
		
		// adding a card to p1 hands and RailYard
		p1.getRailyard().getCards(CardColor.red).add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/yellow.jpg", CardColor.yellow));
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/black.jpg", CardColor.black));
		
		// playcards
		ArrayList<TrainCard> chosenCards = new ArrayList<>();
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/yellow.jpg", CardColor.yellow));
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/black.jpg", CardColor.black));
		// playing cards but having  already a card of the same color in your railyard
		try
		{
			p1.getRailyard().playCards(chosenCards, p2.getRailyard());
		}
		catch(InvalidMoveException e)
		{
			System.out.println(e);
		}
		assertEquals(true, p1.getTrainCardsOnHand().size() == 3);
		// playing cards but the opponents has already a card of a color you are playing in his RailYard
		p1.getRailyard().getCards(CardColor.red).removeAllElements();
		p2.getRailyard().getCards(CardColor.yellow).add(new TrainCard(p2,"/resources/images/trainCards/yellow.jpg", CardColor.yellow));
		
		try
		{
			p1.getRailyard().playCards(chosenCards,p2.getRailyard());
		}
		catch(InvalidMoveException e)
		{
			System.out.println(e);
		}
		assertEquals(true,p1.getTrainCardsOnHand().size() == 3);
		// playing cards of the same color but the opponent has more on his RailYard
		// now p2 has 3 yellow cards
		p2.getRailyard().getCards(CardColor.yellow).add(new TrainCard(p2,"/resources/images/trainCards/yellow.jpg", CardColor.yellow));
		p2.getRailyard().getCards(CardColor.yellow).add(new TrainCard(p2,"/resources/images/trainCards/yellow.jpg", CardColor.yellow));
		// now p1 has 1 yellow card and 1 locomotive in his hand
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/locomotive.jpg",CardColor.locomotive));
		// reseting chooseCards
		chosenCards = new ArrayList<>();
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/yellow.jpg",CardColor.yellow));
		chosenCards.add(new TrainCard(p1,"/resources/images/trainCards/locomotive.jpg",CardColor.locomotive));
		try
		{
			p1.getRailyard().playCards(chosenCards, p2.getRailyard());
		}
		catch(InvalidMoveException e)
		{
			System.out.println(e);
		}
		assertEquals(true, p1.getTrainCardsOnHand().size() == 4);
	}
	
	public void testCollectFromRailYard()
	{
		Player p1 = new Player();
		p1.getRailyard().getCards(CardColor.red).add(new TrainCard(p1,"/resources/images/trainCards/red.jpg", CardColor.red));
		p1.getRailyard().getCards(CardColor.blue).add(new TrainCard(p1,"/resources/images/trainCards/blue.jpg", CardColor.blue));
		p1.getOnthetrack().collectFromRailYard(p1.getRailyard());
		assertEquals(true, p1.getOnthetrack().getCards(CardColor.red).size() == 1 && p1.getOnthetrack().getCards(CardColor.blue).size() == 1);
		assertEquals(true, p1.getRailyard().getCards(CardColor.red).isEmpty() && p1.getRailyard().getCards(CardColor.blue).isEmpty());
		
	}
	
	public void testBuySuccesfullyDestinationCard()
	{
		Player p1 = new Player();
		Deck deck = new Deck();
		
		p1.getOnthetrack().getCards(CardColor.locomotive).add(new TrainCard(p1,"/resources/images/trainCards/locomotive.jpg", CardColor.locomotive));
		p1.getOnthetrack().getCards(CardColor.black).add(new TrainCard(p1,"/resources/images/trainCards/black.jpg", CardColor.black));
		
		ArrayList<CardColor> colors = new ArrayList<>();
		colors.add(CardColor.black);
		colors.add(CardColor.red);
		
		DestinationCard Dcard =  new DestinationCard(p1,"",colors,11,1,"NewYork","Chicago");
		BigCitiesCard Bcard = new BigCitiesCard(null,"",15,"Chicago");
		
		p1.getDestinationCardsOnHand().add(Dcard);
		System.out.println("p1 score : 0 cards , 1 destination card, has reedemed 1 destination card and 1 bigcity card");
		System.out.println("Score p1 :"+p1.getScore() );
		p1.updateScore();
		System.out.println("Score p1 :"+p1.getScore() );
		assertEquals(true, p1.getScore() == -Dcard.getScores());
		// adding 2 visits so the player can redeem it in the 3rd visit
		Bcard.setNumberOfvisits(false);
		Bcard.setNumberOfvisits(false);
		
		
		deck.getOnDeckBigCitiesCards().add(Bcard);
		try
		{
			p1.getOnthetrack().buyDestinationCard(p1.getDestinationCardsOnHand().get(0),false,deck);
		}
		catch(InvalidMoveException e)
		{
			System.out.println(e);
		}
		assertEquals(true, Bcard.getNumberOfvisits(false) == 3 && Bcard.getNumberOfvisits(true) == 0);
		assertEquals(true, p1.getDestinationCardsOnHand().isEmpty() && p1.getOnthetrack().getCards(CardColor.locomotive).isEmpty() && p1.getOnthetrack().getCards(CardColor.black).isEmpty());
		assertEquals(true, Bcard.getPlayer() != null);
		System.out.println("Score p1 :"+p1.getScore() );
		assertEquals(true,p1.getScore() == Dcard.getScores()+Bcard.getScores());
	}
	
	public void testBuyUnsuccesfullyDestinationCard()
	{
		Player p1 = new Player();
		Deck deck = new Deck();
		
		p1.getOnthetrack().getCards(CardColor.locomotive).add(new TrainCard(p1,"/resources/images/trainCards/locomotive.jpg", CardColor.locomotive));
		p1.getOnthetrack().getCards(CardColor.black).add(new TrainCard(p1,"/resources/images/trainCards/black.jpg", CardColor.black));
		
		ArrayList<CardColor> colors = new ArrayList<>();
		colors.add(CardColor.black);
		colors.add(CardColor.red);
		colors.add(CardColor.purple);
		DestinationCard Dcard =  new DestinationCard(p1,"",colors,13,1,"NewYork","Chicago");
		
		p1.getDestinationCardsOnHand().add(Dcard);
		p1.updateScore();
		
		try
		{
			p1.getOnthetrack().buyDestinationCard(p1.getDestinationCardsOnHand().get(0),false,deck);
		}
		catch(InvalidMoveException e)
		{
			System.out.println(e);
		}
		assertEquals(true, p1.getScore() == -13);
	}
	
	public void testgameStart() throws NumberFormatException, IOException
	{
		Controller c = new Controller();
		// the cards are initialized correct
		assertEquals(true,c.deck.getDestinationCards().size() == 46 && c.deck.getTrainCards().size() == 75 && c.p1.getTrainCardsOnHand().size() == 8 && c.p2.getTrainCardsOnHand().size() == 8);
	}
	
	public void testFinishGame() throws NumberFormatException, IOException
	{
		Controller c = new Controller();
		c.p1.getMyDestinationCards().add(new DestinationCard(c.p1,"",null,100,0,"",""));
		c.p1.updateScore();
		// player 1 won
		assertEquals(true, c.gameFinish());
	}

}
