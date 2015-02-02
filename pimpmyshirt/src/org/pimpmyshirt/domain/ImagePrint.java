package org.pimpmyshirt.domain;

/**
 * Graphical print for a shirt. Contains the actual
 * image data and metadata describing that image.
 */
public class ImagePrint extends Print {
	
	private String filename;
	private String contentType;
	private byte[] image;

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Override
	public boolean isGraphical() {
		return true;
	}
}
