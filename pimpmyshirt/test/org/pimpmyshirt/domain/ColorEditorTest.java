package org.pimpmyshirt.domain;

import junit.framework.TestCase;

public class ColorEditorTest extends TestCase {

	public void testColorEditorSetAsText() {
		
		ColorEditor editor = new ColorEditor();
		editor.setAsText("BLUE");
		
		assertSame(Color.BLUE, editor.getValue());
	}
	
	public void testcolorEditorGetAsText() {
		
		ColorEditor editor = new ColorEditor();
		editor.setValue(Color.BLUE);
		
		assertEquals("BLUE", editor.getAsText());
		
	}
}
