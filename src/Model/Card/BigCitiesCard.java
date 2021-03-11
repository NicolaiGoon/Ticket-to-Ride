package Model.Card;

/**
 * <b>A class that represents a BigCity card in the game</b><br>
 * @author Nikos Gounakis
 */
public class BigCitiesCard extends PointsCard{
	
	private String city;
	private int[] numberOfvisits = new int[2]; // each cell represents the number of visit of each player [0] p1, [1] p2
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Precondition :</b>p must be a valid object or null,imagepath must lead to the correct jpg file, score must be valid<br>
	 * <b>Postcondition :</b>creates a new bigcitycard<br>
	 * @param p the player owner of the card
	 * @param imagepath the path of the card's image
	 * @param score the score of the card
	 */
	public BigCitiesCard(Player p,String imagepath,int score,String city) {
		super(p, imagepath, score);
		this.city = city;
	}

	/**
	 * <b>Postcondition :</b>returns a string representation of a bigcitycard<br>
	 */
	@Override
	public String toString() {
		String redeem = "Yes";
		if(getPlayer() == null) redeem = "No";
		String output = "Type : BigCitiesCard\n"
				+ "City : "+city+"\n"
				+ "ImagePath : "+getImagepath()+"\n"
				+ "Redeemed : " +redeem;
		
		return output;
	}

	/**
	 * <b>Accessor :</b><br>
	 * <b>Precondition :</b> flag must be true or false<br>
	 * <b>Postcondition :</b> returns the number of visits of p1 if flag is true, otherwise the number of visits of p2<br>
	 * @param flag a boolean to be sure wich of two cells of the array the function will get access
	 * @return the number of visits of the player
	 */
	public int getNumberOfvisits(boolean flag) {
		if(flag == false)
		{
			// player 1 visit
			return numberOfvisits[0];
		}
		else
		{
			// player 2 visit
			return numberOfvisits[1];
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>Precondition :</b> flag must be true or false<br>
	 * <b>Postcondition :</b> increases the visit number of the selected player by one<br>
	 * @param flag if flag is true increases the visits for player 1 , otherwise for player 2
	 */
	public void setNumberOfvisits(boolean flag) {
		if(flag == false)
		{
			// player 1 visit
			numberOfvisits[0]++;
		}
		else
		{
			// player 2 visit
			numberOfvisits[1]++;
		}
	}

	/**
	 * <b>Accessor :</b><br>
	 * <b>Postconditon :</b>the city is returned<br>
	 * @return the string representation of the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b>city must be a city of the game<br>
	 * <b>Postconditon :</b>sets the value of 'city' to city<br>
	 * @param city the name of the city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
