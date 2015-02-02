package org.pimpmyshirt.domain;

import junit.framework.TestCase;

public class ShirtRatingTest extends TestCase {
	
	private ShirtRating shirtRating;
	
	@Override
	protected void setUp() throws Exception {
		shirtRating =  new ShirtRating(new Shirt(Color.WHITE, false, new TextPrint("FUBAR")));
	}

	public void testVoting() {
		assertEquals(0, shirtRating.getTotalNumberOfVotes());
		shirtRating.vote(Rating.LOW);
		shirtRating.vote(Rating.MEDIUM);
		assertEquals(2, shirtRating.getTotalNumberOfVotes());
		assertEquals(1, shirtRating.getNumberOfLowVotes());
		assertEquals(1, shirtRating.getNumberOfMediumVotes());
		assertEquals(0, shirtRating.getNumberOfHighVotes());
		shirtRating.vote(Rating.HIGH, Rating.MEDIUM, Rating.HIGH);
		assertEquals(5, shirtRating.getTotalNumberOfVotes());
		assertEquals(1, shirtRating.getNumberOfLowVotes());
		assertEquals(2, shirtRating.getNumberOfMediumVotes());
		assertEquals(2, shirtRating.getNumberOfHighVotes());
	}
}
