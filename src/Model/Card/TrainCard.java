package Model.Card;

/**
 * <b>a class that represents TrainCards in the game</b><br>
 * @author Nikos Gounakis
 */
public class TrainCard extends Card {

	private CardColor color;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Precondition :</b> p must be a valid object or null,imagepath must lead to the correct jpg file,color must be the correct color<br>
	 * <b>Postcondition :</b> creates a new train card<br>
	 * @param p the player that the card will belong or null
	 * @param imagepath the path of the card's image
	 * @param color the string that represents the color of the card
	 */
	public TrainCard(Player p,String imagepath,CardColor color) {
		super(p,imagepath);
		this.color = color;
	}

	/**
	 * <b>Postcondition :</b>returns a string representation of a Traincard<br>
	 */
	@Override
	public String toString() {
		String output = "Type : TrainCard\n"
				+ "Color : "+color.toString()+"\n"
				+ "ImagePath : "+getImagepath()+"\n";
		return output;
	}

	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondiiton :</b>the color is returned<br>
	 * @return returns the color of the card
	 */
	public CardColor getColor() {
		return color;
	}

	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>color must be an enum<br>
	 * <b>Postcondiiton :</b>sets the value of the 'color' to color<br>
	 * @param color the color of the card
	 */
	public void setColor(CardColor color) {
		this.color = color;
	}
	
	

}
