package org.pimpmyshirt.service;

import java.util.List;
import java.util.Map;

import org.pimpmyshirt.domain.Rating;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.domain.ShirtRating;

/**
 * Main business facade for the "Pimp My Shirt" application.
 */
public interface ShirtService {

	/**
	 * Returns a list of all shirts defined in the database.
	 */
	List<Shirt> getAllShirts();
	
	/**
	 * Rate shirts.
	 * @param shirtRatings a map containing shirts and the rating
	 * they should receive 
	 */
	void rateShirts(Map<Shirt, Rating> shirtRatings);
	
	/**
	 * Returns the current rating information for given shirt.
	 * Returns null if the shirt is not defined in the database.
	 */
	ShirtRating getRating(Shirt s);
	
	/**
	 * Returns all shirt rating information available in the
	 * database.
	 */
	List<ShirtRating> getShirtRatings();

	/**
	 * Save given shirt in the database. The shirt and it's associated
	 * objects will be assigned unique ids.
	 */
	void saveShirt(Shirt s);

	/**
	 * Returns identified shirt from the database. Returns null if
	 * the shirt is not defined in the database.
	 */
	Shirt getShirt(Long id);
	
	/**
	 * Delete identified shirt from the database. If a shirt with given id
	 * does not exist, nothing happens.
	 */
	void deleteShirt(Long id);

}
