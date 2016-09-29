package com.edvantis.workopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/location")
public class LocationController {

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public String getCountries(Model model) {
		return "location/country";
	}
	
	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public String getStates(Model model, @RequestParam String country) {
		return "location/state";
	}
	
	@RequestMapping(value = "/city", method = RequestMethod.GET)
	public String getCities(Model model, @RequestParam String state) {
		return "location/city";
	}
	
	@RequestMapping(value = "/ed_country", method = RequestMethod.GET)
	public String getEdCountries(Model model) {
		return "location/ed_country";
	}
	
	@RequestMapping(value = "/ed_state", method = RequestMethod.GET)
	public String getEdStates(Model model, @RequestParam String country) {
		return "location/ed_state";
	}
	
	@RequestMapping(value = "/ed_city", method = RequestMethod.GET)
	public String getEdCities(Model model, @RequestParam String state) {
		return "location/ed_city";
	}
}
