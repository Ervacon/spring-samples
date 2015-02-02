package org.pimpmyshirt.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pimpmyshirt.domain.Rating;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.service.ShirtService;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.multiaction.ParameterMethodNameResolver;
import org.springframework.web.util.WebUtils;

/**
 * Multi-action controller to handle the 'index' page. This involves
 * several tasks:
 * 	- handle displaying the shirt list (the actual front page)
 *  - handle shirt rating
 *  - handle shirt deletion
 */
public class RateShirtsController extends MultiActionController {
	
	private ShirtService shirtService;
	
	public RateShirtsController() {
		/*
		 * Identify handler methods using the "op" request parameter.
		 * By default just display the front page.
		 */
		ParameterMethodNameResolver methodNameResolver = new ParameterMethodNameResolver();
		methodNameResolver.setParamName("op");
		methodNameResolver.setDefaultMethodName("handleFrontPage");
		setMethodNameResolver(methodNameResolver);
	}

	public void setShirtService(ShirtService shirtService) {
		this.shirtService = shirtService;
	}

	/**
	 * Display the front page: a list of all shirts in the database.
	 */
	public ModelAndView handleFrontPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index", getModel());
	}
	
	/**
	 * Handle a rating request.
	 */
	public ModelAndView handleRate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * We're expecting request parameters of the form
		 * 	id_<shirtId>=<rating>
		 * for instance
		 * 	id_123=0
		 * which would mean: rate shirt with id "123" as "low" (0).
		 * Could also be that we receive
		 * 	id_123=
		 * in which case no rating was done for identified shirt.
		 */
		Map idParams = WebUtils.getParametersStartingWith(request, "id_");

		Map<Shirt,Rating> ratings = new HashMap<Shirt,Rating>();

		//parse out input
		for (Object o : idParams.keySet()) {
			//could be that no rating was selected
			if (StringUtils.hasText((String)idParams.get(o))) {
				String s = (String)o;
				Rating rating = Rating.valueOf((String)idParams.get(s));
				
				String shirtId = StringUtils.delete(s, "id_");			
				Shirt shirt = shirtService.getShirt(Long.parseLong(shirtId));
				
				ratings.put(shirt, rating);						
			}
		}
		
		if (ratings.isEmpty()) {
			//no shirts have been rated, this is wrong!
			BindException be = new BindException(new Object(), "shirtRatings");
			be.reject("error.ratings.missing");
			Map<String, Object> model = getModel();
			model.putAll((Map<? extends String, ? extends Object>)be.getModel());

			return new ModelAndView("index", model);
		}
		else {
			shirtService.rateShirts(ratings);	
			
			//redirect after processing the submission
			return new ModelAndView("redirect:/index.html");
		}
	}

	/**
	 * Handle a shirt deletion request.
	 */
	public ModelAndView handleDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtils.getLongParameter(request, "id");
		shirtService.deleteShirt(id);
		
		//redirect after processing the submission
		return new ModelAndView("redirect:/index.html");
	}

	/**
	 * Internal helper to setup the model backing the 'index' page.
	 */
	private Map<String, Object> getModel() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("shirtRatings", shirtService.getShirtRatings());
		m.put("ratings", Rating.values());
		return m;
	}
	
}
