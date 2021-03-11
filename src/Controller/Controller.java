package Controller;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.sun.xml.internal.ws.handler.HandlerChainsModel;

import Model.Card.BigCitiesCard;
import Model.Card.CardColor;
import Model.Card.DestinationCard;
import Model.Card.Player;
import Model.Card.TrainCard;
import Model.Deck.Deck;
import Model.Exception.InvalidMoveException;
import View.DestinationCardButton;
import View.GameView;
import View.PlayerArea;
import View.TrainCardButton;
import javafx.stage.Popup;
import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;

/**
 * <b>this is the controller class</b><br>
 * @author Nikos Gounakis
 *
 */
public class Controller {

	
	private GameView View;
	// public for testing
	public Player p1 , p2;
	public Deck deck;
	private int roundPhase; // 0 no move yet , 1 has drawn one card , 2 has drawn two cards 
	private boolean HasMoveOnTheTrack;
	private int MAX_POINTS;
	private ArrayList<TrainCard> trainCardsToPlay;
	
	/**
	 * <b>Constructor :</b><b>
	 * <b>Precondition</b>all parameters must be valid objects<b>
	 * <b>Postcondition</b>costructs a new Controller object that it is the core of the game<br>
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public Controller() throws NumberFormatException, IOException
	{
		View = new GameView();
		p1 = new Player();
		p2 = new Player();
		deck = new Deck();
		roundPhase = 0;
		HasMoveOnTheTrack = false;
		trainCardsToPlay = new ArrayList<>();
		
		// reading the wining score
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
		try
		{
			MAX_POINTS = Integer.parseInt(JOptionPane.showInputDialog("Wining Score (0-100)",50)); // getting input
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(View,"The wining points are set to 50","Points",JOptionPane.INFORMATION_MESSAGE);
			MAX_POINTS = 50;
		}
		if(MAX_POINTS > 100 || MAX_POINTS < 0) MAX_POINTS = 50;
		
		// initializing cards
		initializeGame();
		
		// giving cards
		giveCardstoPlayers();
		
		// information
		JOptionPane.showMessageDialog(View,"Player 1 plays first","First Round",JOptionPane.INFORMATION_MESSAGE);
		
		// listener for drawing train card from deck
		this.View.getDeck().listenerDrawTrainCard(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					// player 1 turn
					if(p1.getTurn() == true && HasMoveOnTheTrack)
					{
						if(roundPhase < 2)
						{
							deck.getNewTrainCard(p1);					
							roundPhase++;
						}
						
						if(roundPhase >= 2)
						{
							roundPhase = 0;
							p1.setTurn(false);
							p2.setTurn(true);
							HasMoveOnTheTrack = false;
							JOptionPane.showMessageDialog(View,"It's Player 2 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					// player 2 turn
					else if(p2.getTurn() == true && HasMoveOnTheTrack)
					{
						if(roundPhase < 2)
						{	
							deck.getNewTrainCard(p2);
							roundPhase++;
						}
						
						if(roundPhase >= 2)
						{
							roundPhase = 0;
							p2.setTurn(false);
							p1.setTurn(true);
							HasMoveOnTheTrack = false;
							JOptionPane.showMessageDialog(View,"It's Player 1 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(View,"Press the button to move the Cards to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(InvalidMoveException invalid)
				{
					JOptionPane.showMessageDialog(null,invalid,"Error",JOptionPane.ERROR_MESSAGE);
				}
				// update turn
				View.getp1Area().getplayerInfoArea().updatePlayerTurn(p1.getTurn());
				View.getp2Area().getplayerInfoArea().updatePlayerTurn(p2.getTurn());
				// update number of cards
				View.getDeck().updateAvailableTrainCards(deck.getTrainCards().size());
				// update GUI 
				updatePlayerCards(p1,View.getp1Area());
				updatePlayerCards(p2,View.getp2Area());
			}
		});
		
		// listener for drawing destination card from deck
		this.View.getDeck().listenerDrawDestCard(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					// player 1 turn
					if(p1.getTurn() == true && HasMoveOnTheTrack && roundPhase == 0)
					{
						drawDestinationCards(p1,View.getp1Area(),"PLayer 1");
						p1.setTurn(false);
						p2.setTurn(true);
						JOptionPane.showMessageDialog(View,"It's Player 2 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
					}
					// player 2 turn
					else if(p2.getTurn() == true && HasMoveOnTheTrack && roundPhase == 0)
					{
						drawDestinationCards(p2,View.getp2Area(),"PLayer 2");
						p2.setTurn(false);
						p1.setTurn(true);
						JOptionPane.showMessageDialog(View,"It's Player 1 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(!HasMoveOnTheTrack && roundPhase == 0)
					{
						JOptionPane.showMessageDialog(View,"Press the button to move the Cards to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(HasMoveOnTheTrack && roundPhase != 0)
					{
						JOptionPane.showMessageDialog(View,"You can't draw Destination Cards , you must wait till the next round","Error",JOptionPane.ERROR_MESSAGE);
					}
					HasMoveOnTheTrack = false;
					roundPhase = 0;
				}
				catch(InvalidMoveException invalid)
				{
					JOptionPane.showMessageDialog(null,invalid,"Error",JOptionPane.ERROR_MESSAGE);
				}
				// update score
				p1.updateScore();
				p2.updateScore();
				View.getp1Area().getplayerInfoArea().updateScore(p1.getScore());
				View.getp2Area().getplayerInfoArea().updateScore(p2.getScore());
				// update turn
				View.getp1Area().getplayerInfoArea().updatePlayerTurn(p1.getTurn());
				View.getp2Area().getplayerInfoArea().updatePlayerTurn(p2.getTurn());
				// update number of cards
				View.getDeck().updateAvailableDestCards(deck.getDestinationCards().size());
				// update GUI 
				updatePlayerCards(p1,View.getp1Area());
				updatePlayerCards(p2,View.getp2Area());
			}
		});
		
		// listener for drawing card from 5 train cards in deck
		this.View.getDeck().listenerDrawFrom5TrainCards(new train5CardsActionlistener());
		
		// p1 buttons
		// play cards button
		this.View.getp1Area().gettrainCardsOnHandArea().listenPlayCardsButton(new PlayCardActionListener(p1,View.getp1Area(),p2,View.getp2Area()));
		
		// move to OnTheTrack Button
		this.View.getp1Area().getrailyardArea().listeMoveToOntheTrack(new OnTheTrackActionListener(p1,View.getp1Area()));
		
		// MyBigCities Button
		this.View.getp1Area().getplayerInfoArea().listenMyBigCities(new MyBigCitiesActionListener(p1,View.getp1Area()));
		
		// MyDestCards Button
		this.View.getp1Area().getplayerInfoArea().listenMydestTickets(new MyDestCardsActionListener(p1,View.getp1Area()));
		
		// p2 buttons
		// play cards button
		this.View.getp2Area().gettrainCardsOnHandArea().listenPlayCardsButton(new PlayCardActionListener(p2,View.getp2Area(),p1,View.getp1Area()));
				
		// move to OnTheTrack Button
		this.View.getp2Area().getrailyardArea().listeMoveToOntheTrack(new OnTheTrackActionListener(p2,View.getp2Area()));
				
		// MyBigCities Button
		this.View.getp2Area().getplayerInfoArea().listenMyBigCities(new MyBigCitiesActionListener(p2,View.getp2Area()));
				
		// MyDestCards Button
		this.View.getp2Area().getplayerInfoArea().listenMydestTickets(new MyDestCardsActionListener(p2,View.getp2Area()));
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>initializes the game<br>
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public void initializeGame() throws NumberFormatException, IOException
	{
		CardColor[] color = {CardColor.black,CardColor.blue,CardColor.green,CardColor.orange,CardColor.purple,CardColor.red,CardColor.white,CardColor.yellow,CardColor.locomotive};
		// creating train cards
		ArrayList<TrainCard> tmpTrainCards = new ArrayList<>();
		for(int i=0;i<9;i++)
		{
			if(color[i] == CardColor.locomotive)
			{
				// we will give 2 locomotives to the players later so they will be 16
				for(int j=0;j<14;j++)
				{
					tmpTrainCards.add(new TrainCard(null,"/resources/images/trainCards/"+color[i].toString()+".jpg", color[i]));
				}
			}
			else
			{
				// 10 cards of each color
				for(int j=0;j<10;j++)
				{
					tmpTrainCards.add(new TrainCard(null,"/resources/images/trainCards/"+color[i].toString()+".jpg", color[i]));
				}
			}
		}
		
		// shuffling the cards
		Collections.shuffle(tmpTrainCards);
		
		for(TrainCard card : tmpTrainCards)
		{
			deck.getTrainCards().push(card);
		}
		// updating number of cards in graphics
		View.getDeck().updateAvailableTrainCards(deck.getTrainCards().size());
		
		// reading destination cards
		ArrayList<DestinationCard> tmpDestcards = new ArrayList<>();
		 BufferedReader br = new BufferedReader(new FileReader(new File("destinationCards.csv")));
	        String sCurrentLine = "";
	        int i = -1;
	        while ((sCurrentLine = br.readLine()) != null) {
	            if (i == -1) {
	                i = 0;
	                continue;
	            }
	            String[] splitLine = sCurrentLine.split(",");
	            int id = Integer.parseInt(splitLine[0]);
	            String from = splitLine[1];
	            String to = splitLine[2];
	            int score = Integer.parseInt(splitLine[3]);
	            String colorsList = splitLine[4];
	            String[] splitColors = colorsList.split("-");
	            ArrayList<CardColor> colors = new ArrayList<>();
	            for(i=0;i<splitColors.length;i++)
	            {
	            	String neededcolor = splitColors[i];
	            	switch(neededcolor)
	            	{
	            		case "Black":
	            		{
	            			colors.add(CardColor.black);
	            			break;
	            		}
	            		case "Blue" :
	            		{
	            			colors.add(CardColor.blue);
	            			break;
	            		}
	            		case "Red" :
	            		{
	            			colors.add(CardColor.red);
	            			break;
	            		}
	            		case "Green" :
	            		{
	            			colors.add(CardColor.green);	
	            			break;
	            		}
	            		case "Purple" :
	            		{
	            			colors.add(CardColor.purple);
	            			break;
	            		}
	            		case "White" :
	            		{
	            			colors.add(CardColor.white);
	            			break;
	            		}
	            		case "Yellow" :
	            		{
	            			colors.add(CardColor.yellow);
	            			break;
	            		}
	            		case "Orange" :
	            		{
	            			colors.add(CardColor.orange);
	            			break;
	            		}
	            	}
	            }
	            String imagePath = "/resources/images/destination_Tickets/"+splitLine[5];
	            
	            tmpDestcards.add(new DestinationCard(null, imagePath,colors, score, id,from,to));
	            
	        }
	        
	        // suffling the cards
	        Collections.shuffle(tmpDestcards);
	        
	        for(DestinationCard card : tmpDestcards)
	        {
	        	deck.getDestinationCards().push(card);
	        } 
	        // updating number of cards in graphics
	        View.getDeck().updateAvailableDestCards(deck.getDestinationCards().size());
	        
	    // setting up Bonus card
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/Chicago.jpg",12,"Chicago"));
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/Dallas.jpg",10,"Dallas"));
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/LosAngeles.jpg",12,"Los Angeles"));
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/Miami.jpg",8,"Miami"));
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/NewYork.jpg",15,"New York"));
	    deck.getOnDeckBigCitiesCards().add(new BigCitiesCard(null,"/resources/images/bigCitiesCards/Seattle.jpg",8,"Seattle"));
	        
	    // player turn
	    p1.setTurn(true);
	    View.getp1Area().getplayerInfoArea().updatePlayerTurn(true);
	    p2.setTurn(false);
	    View.getp2Area().getplayerInfoArea().updatePlayerTurn(false);
	    
	    // update labels of players
	    View.getp1Area().getplayerInfoArea().setPlayerInfo(1);
	    View.getp2Area().getplayerInfoArea().setPlayerInfo(2);
	 
	     
}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>giving cards to the players<br>
	 */
	public void giveCardstoPlayers()
	{
		// giving two locomotives
		p1.getTrainCardsOnHand().add(new TrainCard(p1,"/resources/images/trainCards/locomotive.jpg",CardColor.locomotive));
		p2.getTrainCardsOnHand().add(new TrainCard(p2,"/resources/images/trainCards/locomotive.jpg",CardColor.locomotive));
		
		// the train cards are already suffled
		for(int i=0;i<7;i++)
		{
			deck.getNewTrainCard(p1);
			deck.getNewTrainCard(p2);
				
			if(i<5)
			{
				deck.getOnDeckTrainCards().add(deck.getTrainCards().pop());
			}
		}
		
		// setting the 5 train cards in deck
		for(int i=0;i<deck.getOnDeckTrainCards().size();i++)
		{
			TrainCardButton cardbutton = new TrainCardButton(deck.getOnDeckTrainCards().get(i).getColor());
			cardbutton.setBounds(330+i*75,43,70,100);
			
			// adding to arraylist of buttons
			View.getDeck().get5TrainCards().add((TrainCardButton) cardbutton);
			// adding to GUI
			View.getDeck().add(cardbutton);
		}
		
		// updating the train deck
		View.getDeck().updateAvailableTrainCards(deck.getTrainCards().size());
		
		// choosing destination cards
		drawDestinationCards(p1,View.getp1Area(),"Player 1");
		drawDestinationCards(p2,View.getp2Area(),"Player 2");
		
		// updating Destination deck
		View.getDeck().updateAvailableDestCards(deck.getDestinationCards().size());
		
		// update score after choosing destination cards
		p1.updateScore();
		View.getp1Area().getplayerInfoArea().updateScore(p1.getScore());
		p2.updateScore();
		View.getp2Area().getplayerInfoArea().updateScore(p2.getScore());
		
		// update GUI 
		updatePlayerCards(p1,View.getp1Area());
		updatePlayerCards(p2,View.getp2Area());
		
		
	}
	
	/**
	 * <b>Observer :</b><br>
	 * <b>Postcondition</b>true or false must be returned<br>
	 * @return returns true if the game is finished , false otherwise
	 */
	public boolean gameFinish()
	{
		
		if(deck.getOnDeckTrainCards().isEmpty() || p1.getScore() >= MAX_POINTS || p2.getScore() >= MAX_POINTS)
		{
			// more points
			if(p1.getScore() > p2.getScore())
			{
				JOptionPane.showMessageDialog(View,"Player 1 Won\nScore : "+p1.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(p2.getScore() > p1.getScore())
			{
				JOptionPane.showMessageDialog(View,"Player 2 Won\nScore : "+p2.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
			}
			// equal points , more redeemed destination cards
			else
			{
				if(p1.getMyDestinationCards().size() > p2.getMyDestinationCards().size())
				{
					JOptionPane.showMessageDialog(View,"Player 1 Won\nScore : "+p1.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(p2.getMyDestinationCards().size() > p1.getMyDestinationCards().size())
				{
					JOptionPane.showMessageDialog(View,"Player 2 Won\nScore : "+p2.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
				}
				// more big cities bonus cards
				else
				{
					if(p1.getMyBigCitiesCards().size() > p2.getMyBigCitiesCards().size())
					{
						JOptionPane.showMessageDialog(View,"Player 1 Won\nScore : "+p1.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(p2.getMyBigCitiesCards().size() > p1.getMyBigCitiesCards().size())
					{
						JOptionPane.showMessageDialog(View,"Player 2 Won\nScore : "+p2.getScore()+"/"+MAX_POINTS,"Winner",JOptionPane.INFORMATION_MESSAGE);
					}
					// draw
					else
					{
						JOptionPane.showMessageDialog(View,"Draw!","No Winner",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>the player gets the cards he chose<br>
	 * @param p the player to get the cards
	 * @param Dcards
	 * @param pName it is used only at the first time to seperate players
	 */
	public void drawDestinationCards(Player p,PlayerArea area,String pName)
	{
		if(deck.getDestinationCards().isEmpty()) throw new InvalidMoveException("the DestinationCard stack is empty you cant draw cards");
		
		DestinationCard Dcard[] = new DestinationCard[4];
		
		Dcard[0] = deck.getDestinationCards().pop();
		Dcard[1] = deck.getDestinationCards().pop();
		Dcard[2]= deck.getDestinationCards().pop();
		Dcard[3] = deck.getDestinationCards().pop();
		
		JDialog popup = new JDialog(View,pName+" Choose Destination Cards",true);
		
		popup.setTitle(pName+" Choose Destination Cards");
		popup.setSize(600, 400);
		popup.setLayout(null);
		popup.setResizable(false);
		
		JLabel[] card = new JLabel[4];
		
		card[0] = new JLabel();
		URL card1Url = Controller.class.getResource(Dcard[0].getImagepath());
		Image card1Image = new ImageIcon(card1Url).getImage();
		card1Image = card1Image.getScaledInstance(100,130,Image.SCALE_SMOOTH);
		card[0].setIcon(new ImageIcon(card1Image));
		card[0].setBounds(17,45,100,130);
		popup.add(card[0]);
		
		card[1] = new JLabel();
		URL card2Url = Controller.class.getResource(Dcard[1].getImagepath());
		Image card2Image = new ImageIcon(card2Url).getImage();
		card2Image = card2Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
		card[1].setIcon(new ImageIcon(card2Image));
		card[1].setBounds(144,45,100,130);
		popup.add(card[1]);
		
		card[2] = new JLabel();
		URL card3Url = Controller.class.getResource(Dcard[2].getImagepath());
		Image card3Image = new ImageIcon(card3Url).getImage();
		card3Image = card3Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
		card[2].setIcon(new ImageIcon(card3Image));
		card[2].setBounds(311,45,100,130);
		popup.add(card[2]);
		
		card[3] = new JLabel();
		URL card4Url = Controller.class.getResource(Dcard[3].getImagepath());
		Image card4Image = new ImageIcon(card4Url).getImage();
		card4Image = card4Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
		card[3].setIcon(new ImageIcon(card4Image));
		card[3].setBounds(470,45,100,130);
		popup.add(card[3]);
		
		JCheckBox[] check = new JCheckBox[4];
		
		check[0] = new JCheckBox("keepCard1");
		check[0].setBounds(17,250,91,25);
		popup.add(check[0]);
		
		check[1] = new JCheckBox("keepCard2");
		check[1].setBounds(142,250,102,25);
		popup.add(check[1]);
		
		check[2] = new JCheckBox("keepCard3");
		check[2].setBounds(309,250,102,25);
		popup.add(check[2]);
		
		check[3] = new JCheckBox("keepCard4");
		check[3].setBounds(474,250,100,25);
		popup.add(check[3]);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(226,291,112,38);
		popup.add(submit);
		
		submit.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Stack<DestinationCard> tmpDesCardsStack = new Stack<>();
				
				for(int i=0;i<check.length;i++)
				{
					if(check[i].isSelected())
					{
						deck.getNewDestinationCard(Dcard[i], p);
						int last = p.getDestinationCardsOnHand().size() - 1;
						URL imageurl = Controller.class.getResource(p.getDestinationCardsOnHand().get(last).getImagepath());
						area.getdestinationCardsOnHandArea().getCards().add(new DestinationCardButton(p.getDestinationCardsOnHand().get(last).getId(),imageurl));
					}
					else
					{
						tmpDesCardsStack.push(Dcard[i]);
					}
				}
				// the cards that are not selected are placed at the bottom of the stack
				for(DestinationCard DCard : deck.getDestinationCards())
				{
					tmpDesCardsStack.push(DCard);
				}
				deck.setDestinationCards(tmpDesCardsStack);
				popup.dispose();
			}
		});
		
		popup.setVisible(true);
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Postcondition</b>updates the graphics of the cards<br>
	 * @param player the player
	 * @param pArea the player area
	 */
	public void updatePlayerCards(Player player,PlayerArea pArea)
	{
		// removing all train card buttons
		for(TrainCardButton button : pArea.gettrainCardsOnHandArea().getCards())
		{
			pArea.gettrainCardsOnHandArea().remove(button);
		}
		pArea.gettrainCardsOnHandArea().getCards().clear();
		// TrainCards on hand
		for(int i=0;i<player.getTrainCardsOnHand().size();i++)
		{
			TrainCardButton cardbutton = new TrainCardButton(player.getTrainCardsOnHand().get(i).getColor());
			// cards may overflow
			cardbutton.setBounds((int)(12+i*35),13,70,100);
			cardbutton.setUp(false);
			cardbutton.addActionListener(new SelectCardActionListener(player, pArea));
			pArea.gettrainCardsOnHandArea().getCards().add(cardbutton);
			
			// adding to GUI
			pArea.gettrainCardsOnHandArea().add(pArea.gettrainCardsOnHandArea().getCards().get(i));
			pArea.gettrainCardsOnHandArea().repaint();
		}
		
		// removing all destination card buttons
		for(DestinationCardButton button : pArea.getdestinationCardsOnHandArea().getCards())
		{
			pArea.getdestinationCardsOnHandArea().remove(button);
		}
		pArea.getdestinationCardsOnHandArea().getCards().clear();
		// Destination Cards on hand
		for(int i=0;i<player.getDestinationCardsOnHand().size();i++)
		{
			int id = player.getDestinationCardsOnHand().get(i).getId();
			URL imageUrl = Controller.class.getResource(player.getDestinationCardsOnHand().get(i).getImagepath());
			DestinationCardButton cardbutton = new DestinationCardButton(id, imageUrl);
			// cards may overflow
			cardbutton.setBounds((12+i*20),13,70,100);
			cardbutton.addActionListener(new BuyDestinationCardListener(player,pArea));
			pArea.getdestinationCardsOnHandArea().getCards().add(cardbutton);
			
			// adding to GUI
			pArea.getdestinationCardsOnHandArea().add(pArea.getdestinationCardsOnHandArea().getCards().get(i));
			pArea.repaint();
		}
		
		
		// Railyard cards
		CardColor[] cardcolor = {CardColor.black,CardColor.blue,CardColor.green,CardColor.orange,CardColor.purple,CardColor.red,CardColor.white,CardColor.yellow,CardColor.locomotive};
		for(CardColor color : cardcolor)
		{
			// removing all cards in railyard
			if(color == CardColor.locomotive) break;
			for(JLabel label : pArea.getrailyardArea().getCardList(color))
			{
				pArea.getrailyardArea().remove(label);
			}
			pArea.getrailyardArea().getCardList(color).clear();
			pArea.getrailyardArea().repaint();
			for(int i=0;i<player.getRailyard().getCards(color).size();i++)
			{
				JLabel cardlabel =  new JLabel();
				URL imageURL =  Controller.class.getResource(player.getRailyard().getCards(color).get(i).getImagepath());
				Image trainImage = new ImageIcon(imageURL).getImage();
				trainImage = trainImage.getScaledInstance(50, 80,Image.SCALE_SMOOTH);
				cardlabel.setIcon(new ImageIcon(trainImage));
				pArea.getrailyardArea().getCardList(color).add(cardlabel);
				pArea.getrailyardArea().add(pArea.getrailyardArea().getCardList(color).get(i));
				
				// for every color set different bounds
				switch(color)
				{
				case black:
					cardlabel.setBounds(82,29+10*i,50,80);
					break;
				case blue:
					cardlabel.setBounds(152,29+10*i,50,80);
					break;
				case green:
					cardlabel.setBounds(232,29+10*i,50,80);
					break;
				case orange:
					cardlabel.setBounds(582,29+10*i,50,80);
					break;
				case purple:
					cardlabel.setBounds(312,29+10*i,50,80);
					break;
				case red:
					cardlabel.setBounds(12,29+10*i,50,80);
					break;
				case white:
					cardlabel.setBounds(392,29+10*i,50,80);
					break;
				case yellow:
					cardlabel.setBounds(486,29+10*i,50,80);
					break;
				}
			}
		}
		
		// On-The-Track
		for(CardColor color : cardcolor)
		{
			View.getp1Area().getonThetrackArea().updateNumberOfCards(color,p1.getOnthetrack().getCards(color).size());
			View.getp2Area().getonThetrackArea().updateNumberOfCards(color,p2.getOnthetrack().getCards(color).size());
		}

		
		// bonus cards
		for(int i=0;i<deck.getOnDeckBigCitiesCards().size();i++)
		{
			if(deck.getOnDeckBigCitiesCards().get(i).isActive())
			{
				View.getDeck().getBigCitiesCards().get(i).setIcon(null);
				View.getDeck().repaint();
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Controller controller = new Controller();
	}
	



//--------- ACTION LISTENERS ----------//

class train5CardsActionlistener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		// to undentify the button
		Object src = e.getSource();
		
		// player 1 turn
		if(p1.getTurn() == true && HasMoveOnTheTrack)
		{
			if(roundPhase < 2)
			{	
				for(int i=0;i<View.getDeck().get5TrainCards().size();i++)
				{
					TrainCardButton cardbutton = View.getDeck().get5TrainCards().get(i);
					if(src == cardbutton)
					{
						String Imagepath = "/resources/images/trainCards/"+cardbutton.getColor().toString()+".jpg";
						
						p1.getTrainCardsOnHand().add(new TrainCard(p1,Imagepath,cardbutton.getColor()));
						
						TrainCardButton newCardbutton = new TrainCardButton(cardbutton.getColor());
						newCardbutton.addActionListener(new SelectCardActionListener(p1, View.getp1Area()));												
						View.getp1Area().gettrainCardsOnHandArea().getCards().add(newCardbutton);
						
						if(!deck.getTrainCards().isEmpty())
						{
							cardbutton.setImageAndColor(deck.getTrainCards().pop().getColor());
						}
						else
						{
							View.getDeck().remove(cardbutton);
							View.getDeck().repaint();
						}
						roundPhase++;
					}	
				}
			}
			
			if(roundPhase == 2)
			{
				roundPhase = 0;
				p1.setTurn(false);
				p2.setTurn(true);
				HasMoveOnTheTrack = false;
				JOptionPane.showMessageDialog(View,"It's Player 2 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		// player 2 turn
		else if(p2.getTurn() == true && HasMoveOnTheTrack)
		{
			if(roundPhase < 2)
			{	
				for(int i=0;i<View.getDeck().get5TrainCards().size();i++)
				{
					TrainCardButton cardbutton = View.getDeck().get5TrainCards().get(i);
					if(src == cardbutton)
					{
						String Imagepath = "/resources/images/trainCards/"+cardbutton.getColor().toString()+".jpg";
						
						p2.getTrainCardsOnHand().add(new TrainCard(p2,Imagepath,cardbutton.getColor()));
						
						TrainCardButton newCardbutton = new TrainCardButton(cardbutton.getColor());
						newCardbutton.addActionListener(new SelectCardActionListener(p2, View.getp2Area()));
						View.getp2Area().gettrainCardsOnHandArea().getCards().add(newCardbutton);
						
						if(!deck.getTrainCards().isEmpty())
						{
							cardbutton.setImageAndColor(deck.getTrainCards().pop().getColor());
						}
						else
						{
							View.getDeck().remove(cardbutton);
							View.getDeck().repaint();
						}
						roundPhase++;
					}	
				}
			}
			
			if(roundPhase == 2)
			{
				roundPhase = 0;
				p2.setTurn(false);
				p1.setTurn(true);
				HasMoveOnTheTrack = false;
				JOptionPane.showMessageDialog(View,"It's Player 1 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(View,"Press the button to move the Cards to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		// update
		View.getp1Area().getplayerInfoArea().updatePlayerTurn(p1.getTurn());
		View.getp2Area().getplayerInfoArea().updatePlayerTurn(p2.getTurn());
		updatePlayerCards(p1,View.getp1Area());
		updatePlayerCards(p2, View.getp2Area());
		View.getDeck().updateAvailableTrainCards(deck.getTrainCards().size());
	}
	
}

/**
 * <b>a class that is in charge doing operations 
 * when a card is selected</b><br>
 * @author Nikos Gounakis
 *
 */
public class SelectCardActionListener implements ActionListener
{

	public Player p;
	public PlayerArea area;
	
	public SelectCardActionListener(Player p,PlayerArea area) {
		this.p = p;
		this.area = area;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// to undentify the button
		Object src = e.getSource();
		
		if(p.getTurn())
		{
			for(int i=0;i<area.gettrainCardsOnHandArea().getCards().size();i++)
			{
				if(src == area.gettrainCardsOnHandArea().getCards().get(i) && src instanceof TrainCardButton)
				{
					Rectangle pos = area.gettrainCardsOnHandArea().getCards().get(i).getBounds();
					if(!area.gettrainCardsOnHandArea().getCards().get(i).isUp())
					{
						pos.setLocation((int)pos.getX(),(int)pos.getY()-15);
						area.gettrainCardsOnHandArea().getCards().get(i).setUp(true);
						trainCardsToPlay.add(p.getTrainCardsOnHand().get(i));
					}
					else
					{
						pos.setLocation((int)pos.getX(),(int)pos.getY()+15);
						area.gettrainCardsOnHandArea().getCards().get(i).setUp(false);
						trainCardsToPlay.remove(p.getTrainCardsOnHand().get(i));
					}
					area.gettrainCardsOnHandArea().getCards().get(i).setBounds(pos);
					break;
				}
			}
		}
	}
}

class BuyDestinationCardListener implements ActionListener
{

	public Player p;
	public PlayerArea area;
	
	public BuyDestinationCardListener(Player p,PlayerArea area) {
		this.p = p;
		this.area = area;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// to undentify the button
		Object src = e.getSource();
		
		if(p.getTurn() && HasMoveOnTheTrack && roundPhase == 0)
		{
			for(int i=0;i<area.getdestinationCardsOnHandArea().getCards().size();i++)
			{
				if(src == area.getdestinationCardsOnHandArea().getCards().get(i))
				{
					int id = area.getdestinationCardsOnHandArea().getCards().get(i).getid();
					for(int j=0;j<p.getDestinationCardsOnHand().size();j++)
					{
						if(p.getDestinationCardsOnHand().get(i).getId() == id)
						{
								
								// boolean for buydestination cards
								boolean player;
								if(p == p1) player = false;
								else player = true;
								
								DestinationCard Dcard = p.getDestinationCardsOnHand().get(i);
								
								JDialog BuyDestCard = new JDialog(View,"Buy Destination Card", true);
								BuyDestCard.setSize(450,230);
								
								BuyDestCard.setLayout(null);
								BuyDestCard.setResizable(false);
								
								JLabel lblCard = new JLabel("card");
								URL imgurl = Controller.class.getResource(Dcard.getImagepath());
								Image img = new ImageIcon(imgurl).getImage();
								img = img.getScaledInstance(120 , 160, Image.SCALE_SMOOTH);
								lblCard.setIcon(new ImageIcon(img));
								lblCard.setBounds(12,13,120,160);
								
								BuyDestCard.add(lblCard);
								
								JLabel lblAreYouSure = new JLabel("Are you sure you want to redeem this card?");
								lblAreYouSure.setBounds(151, 44, 269, 49);
								BuyDestCard.add(lblAreYouSure);
								
								JButton btnYes = new JButton("Yes");
								btnYes.setBounds(168, 147, 97, 25);
								BuyDestCard.add(btnYes);
								
								JButton btnNo = new JButton("No");
								btnNo.setBounds(307, 147, 97, 25);
								BuyDestCard.add(btnNo);
								
								// yes button (Buycard)
								btnYes.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										
										try
										{
											p.getOnthetrack().buyDestinationCard(Dcard,player, deck);
											JOptionPane.showMessageDialog(null,"You bought succesfully this card :\n"+Dcard.toString(),"Destination Card Bought",JOptionPane.INFORMATION_MESSAGE);
											// setting turns
											if(p == p1)
											{
												roundPhase = 0;
												p1.setTurn(false);
												p2.setTurn(true);
												HasMoveOnTheTrack = false;
												JOptionPane.showMessageDialog(View,"It's Player 2 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
											}
											else if(p == p2)
											{
												roundPhase = 0;
												p2.setTurn(false);
												p1.setTurn(true);
												HasMoveOnTheTrack = false;
												JOptionPane.showMessageDialog(View,"It's Player 1 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
											}
											BuyDestCard.dispose();	
											
										}
										catch(Exception ex)
										{
											BuyDestCard.dispose();
											JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
										}
									}
								});
								
							// no button closes the wins
							btnNo.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									BuyDestCard.dispose();	
								}
							});
							
							BuyDestCard.setVisible(true);
							break;
						}
					}
					break;
				}
			}
			// update GUI
			View.getp1Area().getplayerInfoArea().updatePlayerTurn(p1.getTurn());
			View.getp2Area().getplayerInfoArea().updatePlayerTurn(p2.getTurn());
			updatePlayerCards(p1,View.getp1Area());
			updatePlayerCards(p2, View.getp2Area());
			// update score
			p.updateScore();
			area.getplayerInfoArea().updateScore(p.getScore());
			if(gameFinish())
			{
				System.exit(0);
			}
		}
		else if(p.getTurn() && !HasMoveOnTheTrack && roundPhase == 0)
		{
			JOptionPane.showMessageDialog(View,"Press the button to move the Cards to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(View,"You have drawn a card , you must wait till the next round to buy destination cards","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}

/**
 * <b>a class that is in charge of doing the operation 
 * when the button MoveToOnTheTrack is pressed</b><br>
 * @author Nikos Gounakis
 *
 */
class OnTheTrackActionListener implements ActionListener
{
	public Player p;
	public PlayerArea area;
	
	public OnTheTrackActionListener(Player p,PlayerArea area) {
		this.p = p;
		this.area = area;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(p.getTurn() && HasMoveOnTheTrack == false)
		{
			trainCardsToPlay.clear();
			HasMoveOnTheTrack = true;
			p.getOnthetrack().collectFromRailYard(p.getRailyard());
			updatePlayerCards(p,area);
		}
		else if(p.getTurn() && HasMoveOnTheTrack == true)
		{
			JOptionPane.showMessageDialog(null,"You already have moved your cards from RailYard to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

/**
 * <b>a class that is in charge of doing the operations when the Button
 * PlayCards is pressed</b><br> 
 * @author Nikos Gounakis
 *
 */
class PlayCardActionListener implements ActionListener
{

	public Player p;
	public PlayerArea area;
	public Player oponent;
	public PlayerArea oponent_area;
	
	public PlayCardActionListener(Player p,PlayerArea area,Player oponent,PlayerArea oponent_area){
		this.p = p;
		this.area = area;
		this.oponent =  oponent;
		this.oponent_area = oponent_area;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(p.getTurn() && HasMoveOnTheTrack && roundPhase == 0)
		{
			// locomotives always last
			ArrayList<TrainCard> cards = new ArrayList<>();
			for(TrainCard card1 : trainCardsToPlay)
			{
				if(card1.getColor()!=CardColor.locomotive)
				{
					cards.add(card1);
				}
			}
			for(TrainCard card2 : trainCardsToPlay)
			{
				if(card2.getColor() == CardColor.locomotive)
				{
					cards.add(card2);
				}
			}
			
			try
			{
				p.getRailyard().playCards(cards, oponent.getRailyard());
				trainCardsToPlay.clear();
				
				// update turn
				p.setTurn(false);
				oponent.setTurn(true);
				View.getp1Area().getplayerInfoArea().updatePlayerTurn(p1.getTurn());
				View.getp2Area().getplayerInfoArea().updatePlayerTurn(p2.getTurn());
				
				HasMoveOnTheTrack = false;
				
				updatePlayerCards(p, area);
				updatePlayerCards(oponent,oponent_area);
				
				if(p1.getTurn()) JOptionPane.showMessageDialog(null,"It's player 1 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null,"It's player 2 turn","Next Round",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(p.getTurn() && !HasMoveOnTheTrack && roundPhase == 0)
		{
			JOptionPane.showMessageDialog(View,"Press the button to move the Cards to On-The-Track","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(roundPhase != 0)
		{
			JOptionPane.showMessageDialog(View,"You have drawn a card you cant play cards right now, you must wait till the next round","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}

/**
 * <b>a class that is in charge of doing the operations when the Button
 * MyBigCitiesCards is pressed</b><br>
 * @author Nikos Gounakis
 *
 */
class MyBigCitiesActionListener implements ActionListener
{
	public Player p;
	public PlayerArea area;
	
	public MyBigCitiesActionListener(Player p,PlayerArea area) {
		this.p = p;
		this.area = area;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(p.getTurn())
		{
			JDialog bonus =  new JDialog(View,"Big Cities",true);
			
			bonus.setLayout(null);
			bonus.setBounds(0,0,700,220);
			bonus.setResizable(false);
			
			int visit1;
			int visit2;
			int visit3;
			int visit4;
			int visit5;
			int visit6;
			
			if(p == p1)
			{
				visit1 = deck.getOnDeckBigCitiesCards().get(0).getNumberOfvisits(false);
				visit2 = deck.getOnDeckBigCitiesCards().get(1).getNumberOfvisits(false);
				visit3 = deck.getOnDeckBigCitiesCards().get(2).getNumberOfvisits(false);
				visit4 = deck.getOnDeckBigCitiesCards().get(3).getNumberOfvisits(false);
				visit5 = deck.getOnDeckBigCitiesCards().get(4).getNumberOfvisits(false);
				visit6 = deck.getOnDeckBigCitiesCards().get(5).getNumberOfvisits(false);		
			}
			else
			{
				visit1 = deck.getOnDeckBigCitiesCards().get(0).getNumberOfvisits(true);
				visit2 = deck.getOnDeckBigCitiesCards().get(1).getNumberOfvisits(true);
				visit3 = deck.getOnDeckBigCitiesCards().get(2).getNumberOfvisits(true);
				visit4 = deck.getOnDeckBigCitiesCards().get(3).getNumberOfvisits(true);
				visit5 = deck.getOnDeckBigCitiesCards().get(4).getNumberOfvisits(true);
				visit6 = deck.getOnDeckBigCitiesCards().get(5).getNumberOfvisits(true);
			}
			
			JLabel lbl1 = new JLabel();
			URL big1Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(0).getImagepath());
			Image big1Image = new ImageIcon(big1Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(0).getPlayer() != p) big1Image = GrayFilter.createDisabledImage(big1Image);
			big1Image = big1Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl1.setIcon(new ImageIcon(big1Image));
			lbl1.setBounds(12, 13, 100, 130);
			JLabel vis1 = new JLabel("Visited : "+visit1+" times");
			vis1.setBounds(12,150,100,10);
			bonus.add(vis1);
			bonus.add(lbl1);
			
			JLabel lbl2 = new JLabel();
			URL big2Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(1).getImagepath());
			Image big2Image = new ImageIcon(big2Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(1).getPlayer() != p) big2Image = GrayFilter.createDisabledImage(big2Image);
			big2Image = big2Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl2.setIcon(new ImageIcon(big2Image));
			lbl2.setBounds(124, 13, 100, 130);
			JLabel vis2 = new JLabel("Visited : "+visit2+" times");
			vis2.setBounds(124,150,100,10);
			bonus.add(vis2);
			bonus.add(lbl2);
			
			JLabel lbl3 = new JLabel();
			URL big3Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(2).getImagepath());
			Image big3Image = new ImageIcon(big3Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(2).getPlayer() != p) big3Image = GrayFilter.createDisabledImage(big3Image);
			big3Image = big3Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl3.setIcon(new ImageIcon(big3Image));
			lbl3.setBounds(231, 13, 100, 130);
			JLabel vis3 = new JLabel("Visited : "+visit3+" times");
			vis3.setBounds(231,150,100,10);
			bonus.add(vis3);
			bonus.getContentPane().add(lbl3);
			
			JLabel lbl4 = new JLabel();
			URL big4Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(3).getImagepath());
			Image big4Image = new ImageIcon(big4Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(3).getPlayer() != p) big4Image = GrayFilter.createDisabledImage(big4Image);
			big4Image = big4Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl4.setIcon(new ImageIcon(big4Image));
			lbl4.setBounds(343, 13, 100, 130);
			JLabel vis4 = new JLabel("Visited : "+visit4+" times");
			vis4.setBounds(343,150,100,10);
			bonus.add(vis4);
			bonus.getContentPane().add(lbl4);
			
			JLabel lbl5 = new JLabel();
			URL big5Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(4).getImagepath());
			Image big5Image = new ImageIcon(big5Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(4).getPlayer() != p) big5Image = GrayFilter.createDisabledImage(big5Image);
			big5Image = big5Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl5.setIcon(new ImageIcon(big5Image));
			lbl5.setBounds(455, 13, 100, 130);
			JLabel vis5 = new JLabel("Visited : "+visit5+" times");
			vis5.setBounds(455,150,100,10);
			bonus.add(vis5);
			bonus.getContentPane().add(lbl5);
			
			JLabel lbl6 = new JLabel();
			URL big6Url = Deck.class.getResource(deck.getOnDeckBigCitiesCards().get(5).getImagepath());
			Image big6Image = new ImageIcon(big6Url).getImage();
			if(deck.getOnDeckBigCitiesCards().get(5).getPlayer() != p) big6Image = GrayFilter.createDisabledImage(big6Image);
			big6Image = big6Image.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
			lbl6.setIcon(new ImageIcon(big6Image));
			lbl6.setBounds(565, 13, 100, 130);
			JLabel vis6 = new JLabel("Visited : "+visit6+" times");
			vis6.setBounds(565,150,100,10);
			bonus.add(vis6);
			bonus.getContentPane().add(lbl6);
			
			bonus.setVisible(true);
		}
	}
}

/**
 * <b>a class that is in charge of doing operations when the button
 * MyDestinationCards is pressed</b><br>
 * @author Nikos Gounakis
 *
 */
class MyDestCardsActionListener implements ActionListener
{
	public Player p;
	public PlayerArea area;
	
	public MyDestCardsActionListener(Player p,PlayerArea area) {
		this.p = p;
		this.area = area;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(p.getTurn() && !p.getMyDestinationCards().isEmpty())
		{
			JDialog mydestcards  = new JDialog(View,"My Destination Cards",true);
			
			int height = 200;
			int width = 110*p.getMyDestinationCards().size();
					
			mydestcards.setBounds(0,0,width,height);
			mydestcards.setResizable(false);
			
			for(int i=0;i<p.getMyDestinationCards().size();i++)
			{
				JLabel card = new JLabel();
				URL destUrl = Deck.class.getResource(p.getMyDestinationCards().get(i).getImagepath());
				Image destImage = new ImageIcon(destUrl).getImage();
				destImage = destImage.getScaledInstance(100, 130,Image.SCALE_SMOOTH);
				card.setIcon(new ImageIcon(destImage));
				
				card.setBounds(110*(i+1),16,100,130);
				
				mydestcards.add(card);
			}
			
			mydestcards.setVisible(true);
		}
		else if(p.getTurn() && p.getMyBigCitiesCards().isEmpty())
		{
			JOptionPane.showMessageDialog(View,"you dont have any Destination Cards redeemed","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

} // controller
