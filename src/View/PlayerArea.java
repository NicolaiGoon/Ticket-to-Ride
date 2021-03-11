package View;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
 * <b>a class that represents the area of the player</b><br>
 * @author Nikos Gounakis
 *
 */
public class PlayerArea extends JLayeredPane {
	
	private RailYardArea railyardArea;
	private OnTheTrackArea onTheTrackArea;
	private TrainCardsOnHandArea trainCardsOnHandArea;
	private DestinationCardsOnHandArea destinationCardsOnHandArea;
	private PlayerInfoArea playerInfoArea;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Postcondition :</b>a new object PlayerArea is created and its componets are initialized<br>
	 */
	public PlayerArea()
	{
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.DARK_GRAY, 7, true));
		setBounds(0,0,1274,347);
		
		// initializing destination cards on hand area
		destinationCardsOnHandArea = new DestinationCardsOnHandArea();
		add(destinationCardsOnHandArea);
		destinationCardsOnHandArea.setBounds(681,197,340,137);
		
		// initializing train cards on hand area 
		trainCardsOnHandArea = new TrainCardsOnHandArea();
		trainCardsOnHandArea.setBounds(12,197,657,137);
		add(trainCardsOnHandArea);
		
		// initializing 
		railyardArea = new RailYardArea();
		railyardArea.setBounds(12,13,657,171);
		add(railyardArea);
		
		// initializing on the track area
		onTheTrackArea = new OnTheTrackArea();
		onTheTrackArea.setBounds(681,13,581,171);
		add(onTheTrackArea);
		
		// initializing player Info area
		playerInfoArea =  new PlayerInfoArea();
		playerInfoArea.setBounds(1033, 197, 229, 137);
		add(playerInfoArea);
		
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the railyardarea must be returned<br>
	 * @return returns the RailYardArea of the player
	 */
	public RailYardArea getrailyardArea()
	{
		return railyardArea;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the onthetrack area must be returned<br>
	 * @return returns the OnTheTrackArea of the player
	 */
	public OnTheTrackArea getonThetrackArea()
	{
		return onTheTrackArea;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the Traincardsonhand must be returned<br>
	 * @return returns the TrainCardsOnHand of the player
	 */
	public TrainCardsOnHandArea gettrainCardsOnHandArea()
	{
		return trainCardsOnHandArea;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the destinationcardsonhandarea must be returned<br>
	 * @return returns the DestinationCardsOnHandArea of the player
	 */
	public DestinationCardsOnHandArea getdestinationCardsOnHandArea()
	{
		return destinationCardsOnHandArea;
	}
	
	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the playerinfoarea must be returned<br>
	 * @return retunrs the PlayerInfoArea of the player
	 */
	public PlayerInfoArea getplayerInfoArea()
	{
		return playerInfoArea;
	}
	
}
