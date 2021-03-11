package Model.Card;

import java.util.ArrayList;

/**
 * <b>a class that represents a Destination card in the game</b><br>
 * @author Nikos Gounakis
 */
public class DestinationCard extends PointsCard {

	// from string changed to int
	private int id;
	
	private String from;
	private String to;
	private ArrayList<CardColor> colors;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Precondition :</b>p must be a valid player object,imagepath must lead to the correct jpg file,score must be valid<br>
	 * <b>Postcondition :</b>creates a new destination card<br>
	 * @param p the player that the card will belongs to
	 * @param imagepath the path of the card's image
	 * @param score the score of the card
	 * @param colors the arraylist of the colors needed to buy this card
	 */
	public DestinationCard(Player p,String imagepath,ArrayList<CardColor> colors,int score,int id,String From,String To) {
		super(p, imagepath, score);
		this.colors = colors;
		this.id = id;
		this.from = From;
		this.to = To;
	}

	/**
	 * <b>Postcondition :</b>returns a string representation of a destinationcard<br>
	 */
	@Override
	public String toString() {
		String output;
		if(!isActive())
		{
			output = "Type : DestinationCard\n"
				+ "From : "+from+"\n"
				+"To : "+to+"\n"
				+ "ImagePath : "+getImagepath()+"\n";
		}
		else
		{
			output = "Type : DestinationCard\n"
					+ "From : "+from+"\n"
					+"To : "+to+"\n"
					+ "Colors : "+colors+"\n"
					+ "ImagePath : "+getImagepath()+"\n";
		}
		return super.toString()+"\n"+output;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the id will be returned<br>
	 * @return returns the id of the card
	 */
	public int getId() {
		return id;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>id must be = {1,...,46}<br>
	 *<b>Postcondition :</b>sets the 'id' to the value of id<br>
	 * @param id the number of the id
	 */
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the string will be returned<br>
	 * @return returns the town from where the train begins
	 */
	public String getFrom() {
		return from;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>from must be a town name from the game<br>
	 *<b>Postcondition :</b>sets the 'from' to the given town<br>
	 * @param from represents the town that the train will begin
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>'to' must be returned<br>
	 * @return returns the town where the train will arrive
	 */
	public String getTo() {
		return to;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>'to' must be a town from the game<br>
	 *<b>Postcondition :</b>sets the value of 'to' to the given town<br>
	 * @param to represent the town that the train will arrive
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 *<b>Accessor :</b><br>
	 *<b>Postcondition :</b>the array must be returned<br>
	 * @return returns the array that represents the color cards needed to travel to this destination
	 */
	public ArrayList<CardColor> getColors() {
		return colors;
	}

	/**
	 *<b>Transformer :</b><br>
	 *<b>Precondition :</b>the array must have only colours of the game<br>
	 *<b>Postcondition :</b>the colors that are requaired to redempt the destination card<br>
	 * @param colors the array of the colors required
	 */
	public void setColors(ArrayList<CardColor> colors) {
		this.colors = colors;
	}
	
}
