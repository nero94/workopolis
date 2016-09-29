package com.edvantis.workopolis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.edvantis.workopolis.controller.HomeController;
import com.edvantis.workopolis.controller.VacanciesController;

public class HomeControllerTest {
	public static class MockSecurityContext implements SecurityContext {

        private Authentication authentication;
        
        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }
        
		@Override
		public Authentication getAuthentication() {
			return this.authentication;
		}

		@Override
		public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;	
		}
		
	}
	
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
	
	@InjectMocks
	private VacanciesController vacanciesController;
	
	@InjectMocks
	private HomeController homeController;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(homeController)
				.setHandlerExceptionResolvers(exceptionResolver())
				.setValidator(validator())
				.setViewResolvers(viewResolver())
				.build();
	}
	
	private HandlerExceptionResolver exceptionResolver(){
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties exceptionMappings = new Properties();
		
		exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
        
        exceptionResolver.setExceptionMappings(exceptionMappings);
        
        Properties statusCodes = new Properties();
        
        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
	}
	
	 
	 private LocalValidatorFactoryBean validator() {
	        return new LocalValidatorFactoryBean();
	    }
	 
	 private ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
	        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
	        return viewResolver;
	    }
	
	@Test
    public void testContactForm() throws Exception {
        this.mockMvc.perform(get("/contact_form"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/jsp/contact_form.jsp"));
    }
	
	@Test
    public void testAboutUs() throws Exception {
        this.mockMvc.perform(get("/about_us"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/jsp/about_us.jsp"));
    }
	

	
	
}
