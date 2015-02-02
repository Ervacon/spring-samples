package org.pimpmyshirt.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.Serializable;

import org.pimpmyshirt.domain.Color;
import org.pimpmyshirt.domain.ColorEditor;
import org.pimpmyshirt.domain.ImagePrint;
import org.pimpmyshirt.domain.Print;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.domain.TextPrint;
import org.pimpmyshirt.service.ShirtService;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.ScopeType;
import org.springframework.webflow.action.FormAction;

/**
 * Multi-action that backs the "compose shirt flow".
 */
public class ComposeShirtAction extends FormAction {
	
	private ShirtService shirtService;
	
	public void setShirtService(ShirtService shirtService) {
		this.shirtService = shirtService;
	}
	
	public ComposeShirtAction() {
		setFormObjectClass(ShirtFbo.class); //custom form backing object class
		setFormObjectName("shirtFbo");
		setFormObjectScope(ScopeType.FLOW); //this is a multi-page wizard!
		setValidator(new ShirtFboValidator());
	}
	
	protected void initBinder(RequestContext context, DataBinder binder) {
		binder.registerCustomEditor(Color.class, new ColorEditor());
		
		/*
		 * Register a custom property editor to create a new Print. This
		 * will create an ImagePrint if it detects a file upload. When
		 * a text 'upload' is detected, a TextPrint will be created.
		 */
		binder.registerCustomEditor(Print.class, new PropertyEditorSupport() {
			public void setValue(Object value) {
				if (value instanceof MultipartFile) {
					//an image print
					MultipartFile multipartFile = (MultipartFile) value;
					ImagePrint print = new ImagePrint();
					try {
						print.setImage(multipartFile.getBytes());
						print.setContentType(multipartFile.getContentType());
						print.setFilename(multipartFile.getOriginalFilename());
					}
					catch (IOException ex) {
						throw new IllegalArgumentException("Cannot read contents of multipart file: " + ex.getMessage());
					}
					super.setValue(print);
				}
				else if (value instanceof String && StringUtils.hasText((String)value)) {
					//a text print
					TextPrint print = new TextPrint();
					print.setText((String)value);
					super.setValue(print);
				}
				else if (value instanceof Print) {
					//no problemo
					super.setValue((Print)value);
				}
				else {
					//forget about it
					super.setValue(null);
				}
			}
			
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(text);
			}
			
			public String getAsText() {
				if (getValue() instanceof ImagePrint) {
					return ((ImagePrint)getValue()).getFilename();
				}
				if (getValue() instanceof TextPrint) {
					return ((TextPrint)getValue()).getText();
				}
				else {
					return null;
				}
			}
		});
	}

	/**
	 * Simple action to setup a bit of reference data in flow scope.
	 */
	public Event referenceData(RequestContext context) throws Exception {
		context.getFlowScope().setAttribute("colors", Color.values());
		return success();
	}
	
	/**
	 * Action to actually create the shirt object in the database.
	 */
	public Event createShirt(RequestContext context) throws Exception {
		Shirt shirt = ((ShirtFbo)getFormObject(context)).getShirt();
		shirtService.saveShirt(shirt);
		return success();
	}

	/**
	 * Our form backing object class: a simple DTO.
	 */
	public static class ShirtFbo implements Serializable {
		
		private transient Shirt shirt;
		private boolean graphical;
		
		/**
		 * Get the shirt that we're composing.
		 * This returns the object the data binder binds onto.
		 */
		public Shirt getShirt() {
			if (shirt == null) {
				shirt = new Shirt();
			}
			return shirt;
		}
		
		public boolean isGraphical() {
			return graphical;
		}
		
		public void setGraphical(boolean graphical) {
			this.graphical = graphical;
		}
	}
	
	/**
	 * A validator for our form backing object.
	 * Note that this validator supports individual validation
	 * of each step in the "compose shirt flow".
	 */
	public static class ShirtFboValidator implements Validator {
		
		public boolean supports(Class clazz) {
			return ShirtFbo.class.isAssignableFrom(clazz);
		}
		
		public void validate(Object obj, Errors errors) {
			ShirtFbo shirtFbo = (ShirtFbo)obj;
			validateStep1(shirtFbo, errors);
			validateStep2(shirtFbo, errors);
		}
		
		/**
		 * Validate first step of wizard.
		 */
		public void validateStep1(ShirtFbo shirtFbo, Errors errors) {
			ValidationUtils.rejectIfEmpty(errors, "shirt.color", "error.shirt.color.missing");
		}

		/**
		 * Validate second step of wizard.
		 */
		public void validateStep2(ShirtFbo shirtFbo, Errors errors) {
			ValidationUtils.rejectIfEmpty(errors, "shirt.print", "error.shirt.print.missing");
		}
	}
}
