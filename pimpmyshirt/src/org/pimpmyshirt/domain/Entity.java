package org.pimpmyshirt.domain;

/**
 * Superclass for all persistent entities in the application.
 * Defines the identity field to maintain identity between
 * the in-memory object and its representation in the database.
 */
public class Entity {
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
