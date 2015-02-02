package org.pimpmyshirt.domain;

/**
 * Shirt class.
 */
public class Shirt extends Entity {
	
	private Color color;
	private boolean longSleeve;
	private Print print;
	
	public Shirt() {
	}
	
	public Shirt(Color color, boolean longSleeve, Print print) {
		this.color = color;
		this.longSleeve = longSleeve;
		this.print = print;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isLongSleeve() {
		return longSleeve;
	}
	
	public void setLongSleeve(boolean longSleeve) {
		this.longSleeve = longSleeve;
	}

	public Print getPrint() {
		return print;
	}
	
	public void setPrint(Print print) {
		this.print = print;
	}

}
