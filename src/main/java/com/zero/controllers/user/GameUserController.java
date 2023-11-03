package com.zero.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.UserEntity;
import com.zero.service.UserService;

@Controller
public class GameUserController {
	
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/MyGames")
	public ModelAndView myGames(@RequestParam String username) {
		ModelAndView mav;
		
		UserEntity user=userService.findByUsername(username);
		
		mav=new ModelAndView("/user/MyGames");
		
		return mav;
		
		
		
	}
	
	
}
