package com.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.GenreList;
import com.zero.auxiliar.PlatformAPI;
import com.zero.auxiliar.PlatformList;
import com.zero.domain.Platform;
import com.zero.repository.PlatformRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlataformService {
	
	private final String RAWG_URL= "https://api.rawg.io/api/platforms?key=bc332b7707a14fefbfbdc66dc64bb588";
	
	@Autowired
	private final RestTemplate restTemplate;
	//Own Repository
	
	@Autowired
	private PlatformRepository platformRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public PlataformService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//Create method
	
	//Finds method
	
	//Others method
	
	public void getAPIPlatforms() {
		System.out.println("hola");
		ResponseEntity<PlatformList> responseEntity = restTemplate.exchange(RAWG_URL,HttpMethod.GET,null,PlatformList.class);
		System.out.println("Hola2");
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			PlatformList plataformList=responseEntity.getBody();
			if(plataformList != null && plataformList.getResults() != null) {
				List<PlatformAPI> platforms= plataformList.getResults();
				for(PlatformAPI pAPI : platforms) {
					Platform addPlataform= new Platform();
					addPlataform.setName(pAPI.getName());
					addPlataform.setRelease_year(pAPI.getYearStart());
					addPlataform.setDescription(pAPI.getDescription());
					platformRepository.save(addPlataform);
				}
			}
		}
	}

}
