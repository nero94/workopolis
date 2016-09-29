package com.edvantis.workopolis;

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
import com.edvantis.workopolis.controller.HomeController;
import com.edvantis.workopolis.controller.VacanciesController;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;

import com.edvantis.workopolis.model.vacancy.Vacancy;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class VacanciesControllerTest {

	public static class MockSecurityContext implements SecurityContext {

		private static final long serialVersionUID = -1386535243513362694L;

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
	private VacancyDAO vacancyDAO;
	
	@Mock
	private EmployerDAO employerDAO;
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private VacanciesController vacanciesController;
	
	@InjectMocks
	private HomeController homeController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		
		mockMvc = MockMvcBuilders.standaloneSetup(vacanciesController)
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
	public void testVacancyDescription() throws Exception{
		Integer id = 1;
		Vacancy vacancy = new Vacancy();
		
		when(vacancyDAO.getVacancyById(id)).thenReturn(vacancy);
		
		this.mockMvc.perform(get("/vacancy/" + id))
			.andExpect(status().isOk())
			.andExpect(view().name("vacancyDescription"))
			.andExpect(model().attribute("vacancy", instanceOf(Vacancy.class)));
	}
	
	@Test
	public void testGetAddVacancy() throws Exception {
		
		this.mockMvc.perform(get("/add_vacancy"))
			.andExpect(status().isOk())
			.andExpect(view().name("employer/add_vacancy"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/employer/add_vacancy.jsp"))
			.andExpect(model().attribute("vacancy", hasProperty("id", instanceOf(Integer.class))))
			.andExpect(model().attribute("vacancy", hasProperty("title", isEmptyOrNullString())))
			.andExpect(model().attribute("vacancy", hasProperty("description", isEmptyOrNullString())));
		
		verifyZeroInteractions(vacancyDAO);
	}
	
	@Test
	public void testErrorAddVacacny() throws Exception{
		
		this.mockMvc.perform(post("/add_vacancy")
				.contentType(MediaType.ALL)
				.sessionAttr("vacancy", new Vacancy())
				)
				.andExpect(status().is5xxServerError())
				.andExpect(forwardedUrl("/WEB-INF/jsp/error/error.jsp"));
	}
	
	@Test
	public void testPageNotFound() throws Exception{
		
		this.mockMvc.perform(get("/not_ds"))
			.andExpect(status().is4xxClientError());
	}
	
	
	
}
	

	

