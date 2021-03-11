package Model.Card;

/**
 * <b>a class that represents cards with score</b><br>
 * @author Nikos Gounakis
 */
public class PointsCard extends Card{

	private int score;
	
	/**
	 * <b>Constructor :</b><br>
	 * <b>Precondition :</b> p must be a valid object or null,imagepath must lead to the correct jpg file,score must be valid<br>
	 * <b>Postcondition :</b> creates a new point card<br>
	 * @param p the player that the card will belong or null
	 * @param imagepath the path of the card's image
	 * @param score the score of the card
	 */
	public PointsCard(Player p,String imagepath,int score) {
		super(p,imagepath);
		this.score = score;
	}

	/**
	 * <b>Postcondition :</b>returns a string representation of a pointcard<br>
	 */
	@Override
	public String toString() {
		String output = "GeneralType : PointCard\n"
				+ "Score : "+score; 
		return output;
	}

	/**
	 * <b>Accessor :</b><br>
	 * <b>Postcondition :</b>the score has been returned<br>
	 * @return returns the score of the card
	 */
	public int getScores() {
		return score;
	}

	/**
	 * <b>Transformer :</b><br>
	 * <b>Precondition :</b> points must be greater or equal to 0<br>
	 * <b>Postcondition :</b> sets the value of the card score to points<br>
	 * @param points the amount of points
	 */
	public void setPoints(int points){
		this.score = points;
	}

}
