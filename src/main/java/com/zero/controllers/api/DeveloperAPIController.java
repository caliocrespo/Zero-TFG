package com.zero.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.auxiliar.DeveloperAPI;
import com.zero.auxiliar.GenreAPI;
import com.zero.auxiliar.PlatformAPI;
import com.zero.service.DeveloperService;
import com.zero.service.GenreService;
import com.zero.service.PlatformService;
@RestController
@RequestMapping("/api/developers")
public class DeveloperAPIController {

	@Autowired
	private DeveloperService developerService;
	
	
	@GetMapping
	public Collection<DeveloperAPI> getPlatforms(@RequestParam(required = false) Integer id, @RequestParam (required=false) String name){
		List<DeveloperAPI> developers= developerService.developerApi();
		
		
		
		developers = developers.stream().filter(developer -> (id == null || developer.getId() == id.intValue()) 
				&& name == null || developer.getSlug().equalsIgnoreCase(name)
				).collect(Collectors.toList());
		
		return  developers;
	}
}
