package org.pimpmyshirt.domain;

/**
 * Textual print for a shirt. Just maintains the print text.
 */
public class TextPrint extends Print {
	
	private String text;
	
	public TextPrint() {
	}

	public TextPrint(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean isGraphical() {
		return false;
	}
}
