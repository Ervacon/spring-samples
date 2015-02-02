package org.pimpmyshirt.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pimpmyshirt.domain.Color;
import org.pimpmyshirt.domain.Entity;
import org.pimpmyshirt.domain.Rating;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.domain.ShirtRating;
import org.pimpmyshirt.domain.TextPrint;
import org.springframework.util.Assert;

/**
 * Dummy implementation of the ShirtService.
 * Uses a simple map as an in-memory database. A real implementation
 * would delegate to the DAO layer for data access.
 */
public class ShirtServiceImpl implements ShirtService {
	
	//primary key generator
	private long lastId = 0;
	
	//in memory "database": all shirts and their rating information
	private Map<Shirt, ShirtRating> shirtsToRatings = new HashMap<Shirt, ShirtRating>();
	
	public ShirtServiceImpl() {
		//setup some sample data
		saveShirt(new Shirt(Color.BLUE, true, new TextPrint("Spring Rocks!")));
		saveShirt(new Shirt(Color.WHITE, false, new TextPrint("JavaPolis")));
	}
	
	public ShirtRating getRating(Shirt s) {
		return shirtsToRatings.get(s);
	}
	
	public void rateShirts(Map<Shirt, Rating> shirtRatings) {
		for (Shirt shirt : shirtRatings.keySet()) {
			Rating r = shirtRatings.get(shirt);
			getRating(shirt).vote(r);
		}		
	}
	
	public List<Shirt> getAllShirts() {
		return new LinkedList<Shirt>(shirtsToRatings.keySet());
	}
	
	public List<ShirtRating> getShirtRatings() {
		return new LinkedList<ShirtRating>(shirtsToRatings.values());
	}
	
	
	public void saveShirt(Shirt s) {
		//manual persistence and cascading
		setEntityId(s);
		if (s.getPrint() != null) {
			setEntityId(s.getPrint());
		}
		
		shirtsToRatings.put(s, new ShirtRating(s));
	}
	
	//internal helper that takes care of assigning a persistent id
	private void setEntityId(Entity e) {
		Assert.state(e.getId() == null);
		e.setId(++lastId);
	}
	
	public Shirt getShirt(Long id) {
		for (Shirt s : getAllShirts()) {
			if (id.equals(s.getId())) {
				return s;
			}
		}
		return null;
	}
	
	public void deleteShirt(Long id) {
		shirtsToRatings.remove(getShirt(id));
	}
}
