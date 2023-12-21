package com.zero.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.auxiliar.GenreAPI;
import com.zero.auxiliar.PlatformAPI;
import com.zero.service.GenreService;
import com.zero.service.PlatformService;
@RestController
@RequestMapping("/api/platforms")
public class PlatformAPIController {

	@Autowired
	private PlatformService platformService;
	
	
	@GetMapping
	public Collection<PlatformAPI> getPlatforms(@RequestParam(required = false) Integer id, @RequestParam (required=false) String name){
		List<PlatformAPI> platforms= platformService.platformsApi();
		
		
		
		platforms = platforms.stream().filter(platform -> (id == null || platform.getId() == id.intValue()) 
				&& name == null || platform.getSlug().equalsIgnoreCase(name)
				).collect(Collectors.toList());
		
		return  platforms;
	}
}
