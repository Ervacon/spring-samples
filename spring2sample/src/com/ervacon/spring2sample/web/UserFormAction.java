package com.ervacon.spring2sample.web;

import java.beans.PropertyEditorSupport;

import org.springframework.validation.DataBinder;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.action.FormAction;

import com.ervacon.spring2sample.domain.Colour;
import com.ervacon.spring2sample.domain.Country;
import com.ervacon.spring2sample.domain.Skill;
import com.ervacon.spring2sample.domain.User;
import com.ervacon.spring2sample.service.UserManager;

public class UserFormAction extends FormAction {
	
	private UserManager userManager;
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	protected void initBinder(RequestContext context, DataBinder binder) {
		binder.registerCustomEditor(Country.class, new PropertyEditorSupport() {
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(userManager.findCountryByCode(text));
			}

			public String getAsText() {
				return getValue() == null ? "" : ((Country) getValue()).getCode();
			}
		});
		binder.registerCustomEditor(Colour.class, new PropertyEditorSupport() {
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(Colour.valueOf(text));
			}
		});
		binder.registerCustomEditor(Skill.class, new PropertyEditorSupport() {
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(Skill.valueOf(text));
			};
		});
	}
	
	protected Object loadFormObject(RequestContext context) throws Exception {
		long id = context.getRequestParameters().getRequiredLong("id");
		return this.userManager.findUserById(id);
	}

	public Event referenceData(RequestContext context) throws Exception {
		context.getRequestScope().put("countryList", this.userManager.findAllCountries());
		context.getRequestScope().put("skills", Skill.values());
		context.getRequestScope().put("colours", Colour.values());
		return success();
	}

	public Event saveUser(RequestContext context) throws Exception {
		this.userManager.saveUser((User)getFormObject(context));
		return success();
	}

}
