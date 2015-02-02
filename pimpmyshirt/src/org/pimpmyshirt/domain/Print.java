package org.pimpmyshirt.domain;

/**
 * Abstract superclass for all possible prints for shirts.
 */
public abstract class Print extends Entity {

	/**
	 * Returns whether or not this print is graphical in nature.
	 * Subclasses should implement this method.
	 */
	public abstract boolean isGraphical();
	
}
