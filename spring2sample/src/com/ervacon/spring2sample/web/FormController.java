package com.ervacon.spring2sample.web;

import java.beans.PropertyEditorSupport;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.ervacon.spring2sample.domain.Colour;
import com.ervacon.spring2sample.domain.Country;
import com.ervacon.spring2sample.domain.Skill;
import com.ervacon.spring2sample.domain.User;
import com.ervacon.spring2sample.service.UserManager;

public class FormController extends SimpleFormController {

	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
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

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		return
			new ModelMap(this.userManager.findAllCountries())
				.addObject("skills", Skill.values())
				.addObject("colours", Colour.values());
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		return this.userManager.findUserById(id);
	}

	protected void doSubmitAction(Object command) throws Exception {
		this.userManager.saveUser((User) command);
	}

}
