package Model.Card;

/**
 * <b>An abstarct class that represents a card model</b><br>
 * @author Nikos Gounakis
 */
public abstract class Card {
	
	private Player player;
	private String Imagepath;
	
	/**
	 *<b>Constructor :</b><br>
	 *<b>Precondition :</b> player must be a valid object or null, imagepath must lead to the correct jpg file<br>
	 *<b>Postcondition :</b> creates a new card<br>
	 *@param p the player
	 *@param imagepath the path of the image
	 */
	public Card(Player p,String imagepath)
	{
		player = p;
		Imagepath = imagepath;
	}
	
	/**
	 *<b>Observer :</b><br>
	 * @return returns true if the card is active in the game, false otherwise
	 * that is determined if the player of the card is null or not
	 */
	public boolean isActive() 
	{
		if(player == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 *<b>Accessor :</b><br>
	 * @return returns the owner of the card
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b> Player p must be a valid object<br>
	 * <b>Postcondition :</b> sets the player field of the card to p so the card belong to the player p,
	 * if p is null the card does not belongs to anyone<br>
	 * @param p the player new owner of the card
	 */
	public void setPlayer(Player p) {
		player = p;
	}
	
	/**
	 * <b>Postcondition :</b>returns a string representation of a card<br>
	 * @return returns a string representation of the card
	 */
	public abstract String toString();

	/**
	 * <b>Accessor :</b><br>
	 * @return returns the path of the image of the card
	 */
	public String getImagepath() {
		return Imagepath;
	}

	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b> cardImage must be a path leading to a jpg file<br>
	 * <b>Postcondition :</b> sets the 'cardImage' to cardImage<br>
	 * @param Imagepath the path of the image
	 */
	public void setImagepath(String Imagepath) {
		this.Imagepath = Imagepath;
	}
	
}
