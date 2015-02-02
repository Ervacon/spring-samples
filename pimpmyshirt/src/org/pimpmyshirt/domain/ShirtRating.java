package org.pimpmyshirt.domain;

import java.util.EnumMap;
import java.util.Map;
import static org.pimpmyshirt.domain.Rating.*;

/**
 * Container for rating information associated with a shirt.
 */
public class ShirtRating extends Entity {

	private Map<Rating, Integer> votes = new EnumMap<Rating, Integer>(Rating.class);
	private Shirt shirt;
	
	public ShirtRating(Shirt shirt) {
		this.shirt=shirt;
	}

	/**
	 * Cast a vote for a particular rating of the shirt.
	 */
	public void vote(Rating rating) {
		votes.put(rating, getNumberOfVotes(rating) + 1);
	}
	
	/**
	 * Cast multiple votes for particular ratings of the shirt.
	 */
	public void vote(Rating... ratings) {
		for (Rating rating : ratings) {
			vote(rating);
		}
	}

	/**
	 * Returns the number of votes cast for a specified
	 * rating of this shirt.
	 */
	public int getNumberOfVotes(Rating rating) {
		return votes.containsKey(rating) ? votes.get(rating) : 0;
	}
	
	public int getTotalNumberOfVotes() {
		int total = 0;
		for (Rating r : Rating.values()) {
			total += getNumberOfVotes(r);
		}
		return total;
	}
	
	public int getNumberOfLowVotes() {
		return getNumberOfVotes(LOW);
	}
	
	public int getNumberOfMediumVotes() {
		return getNumberOfVotes(MEDIUM);
	}
	
	public int getNumberOfHighVotes() {
		return getNumberOfVotes(HIGH);
	}

	public Shirt getShirt() {
		return shirt;
	}
}
