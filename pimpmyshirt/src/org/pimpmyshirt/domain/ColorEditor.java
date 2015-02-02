package org.pimpmyshirt.domain;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * JavaBeans property editory for Color objects.
 */
public class ColorEditor extends PropertyEditorSupport {
	
	@Override
	public String getAsText() {
		Color c = (Color)getValue();
		return c == null ? null : c.toString();
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) setValue(Color.valueOf(text));
	}

}
