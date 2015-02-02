package com.ervacon.spring2sample.xml;

import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.w3c.dom.Element;

import com.ervacon.spring2sample.web.SampleHandlerInterceptor;

public class SampleNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("interceptor", new InterceptorBeanDefinitionParser());
	}
	
	public static class InterceptorBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
		
		protected Class getBeanClass(Element element) {
			return SampleHandlerInterceptor.class;
		}
	}

}
