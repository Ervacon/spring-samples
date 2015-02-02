package org.pimpmyshirt.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import org.pimpmyshirt.domain.Color;
import org.pimpmyshirt.domain.Rating;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.domain.TextPrint;
import org.pimpmyshirt.service.ShirtService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class RateShirtsControllerTest extends TestCase {
	
	public void testRating() throws Exception {
		Shirt s = new Shirt(Color.BLUE, true, new TextPrint("Alef"));
		
		ShirtService service = createMock(ShirtService.class);
		expect(service.getShirt(1L)).andReturn(s);
		service.rateShirts(eqRatingMap(new HashMap<Shirt,Rating>()));
		expectLastCall();
		
		replay(service);
		
		RateShirtsController rsc = new RateShirtsController();
		rsc.setShirtService(service);
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET","test");
		request.addParameter("id_1", "MEDIUM");
		
		ModelAndView mav = rsc.handleRate(request, new MockHttpServletResponse());
		
		assertTrue(mav.getViewName().startsWith("redirect:"));
		
		verify(service);
	}
	
	public static Map<Shirt,Rating> eqRatingMap(Map<Shirt,Rating> in) {
		EasyMock.reportMatcher(new IArgumentMatcher() {
			public void appendTo(StringBuffer buffer) {};
			public boolean matches(Object o) {
				Map m = (Map)o;
				return m.size() == 1;
			};
		});
		return in;
	}
}
