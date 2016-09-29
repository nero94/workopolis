package com.edvantis.workopolis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import com.edvantis.workopolis.controller.LoginController;
import com.edvantis.workopolis.dao.user.UserDAO;

public class LoginControllerTest {

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
	
	@Mock
	private UserDAO userDAO;
	
	
	
	@InjectMocks
	private LoginController loginController;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(loginController)
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
    public void testLogin() throws Exception {
        this.mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
    }
	
	@Test
    public void testLoginNeg() throws Exception {
        this.mockMvc.perform(get("/login")
        		.param("login_error", "")	)
        
            .andExpect(status().isOk())
            
            .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
    }
	
	


	
	


}
