package org.pimpmyshirt.domain;

/**
 * Typesafe enumeration defining possible shirt ratings.
 */
public enum Rating {
	
	LOW,
	MEDIUM,
	HIGH;
		
	public int getLevel() {
		return ordinal();
	}

}
