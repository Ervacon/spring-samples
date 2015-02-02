package org.pimpmyshirt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pimpmyshirt.domain.ImagePrint;
import org.pimpmyshirt.domain.Shirt;
import org.pimpmyshirt.service.ShirtService;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;
import org.springframework.webflow.execution.FlowExecution;
import org.springframework.webflow.execution.FlowExecutionManager;

/**
 * Simple controller to send an uploaded image back to the
 * client browser. 
 */
public class ImageController extends AbstractController {
	
	private ShirtService shirtService;

	public void setShirtService(ShirtService shirtService) {
		this.shirtService = shirtService;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImagePrint print;
		
		String flowExecutionId = request.getParameter(FlowExecutionManager.FLOW_EXECUTION_ID_PARAMETER);
		if (StringUtils.hasText(flowExecutionId)) {
			//pull unsaved image from flow scope (we obviously can't get it from
			//the database since it's unsaved!)
			FlowExecution flowExecution = (FlowExecution)WebUtils.getSessionAttribute(request, FlowExecution.class.getName() + "." + flowExecutionId);
			Shirt shirt = ((ComposeShirtAction.ShirtFbo)flowExecution.getActiveSession().getScope().getAttribute("shirtFbo")).getShirt();
			print = (ImagePrint)shirt.getPrint();
		}
		else {
			//pull a saved image from the database
			Shirt shirt = shirtService.getShirt(RequestUtils.getLongParameter(request, "id"));
			print = (ImagePrint)shirt.getPrint();
		}

		//send the image to the client
		response.setBufferSize(print.getImage().length);
		response.setContentType(print.getContentType());
		response.setContentLength(print.getImage().length);
		FileCopyUtils.copy(print.getImage(), response.getOutputStream());

		//we've already sent the response so tell the dispatcher servlet
		//to leave it alone
		return null;
	}
}

