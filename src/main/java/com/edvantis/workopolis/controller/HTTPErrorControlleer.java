package com.edvantis.workopolis.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTTPErrorControlleer {
	
	static Logger log = Logger.getLogger(HTTPErrorControlleer.class.getName());
	
	String path = "errors/";
	
	@RequestMapping(value="/403")
	 public ModelAndView error403(){
	  log.info("403");
	  return new ModelAndView(path+"403");
	 }
	  
	 @RequestMapping(value="/404")
	 public ModelAndView error404(){
	  log.info("404");
	  return new ModelAndView(path+"404");
	 }
	 
	 @RequestMapping(value="/error")
	 public ModelAndView error(){
	  log.info("custom error handler");
	  return new ModelAndView(path+"error");
	 }
	  
	 

}
