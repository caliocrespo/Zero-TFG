package com.zero.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class APIController {

	@GetMapping("/api/documentation")
	public ModelAndView documentation() {
		ModelAndView mav;
		
		mav = new ModelAndView("api/documentation");
		
		return mav;
	}
	
	
}
