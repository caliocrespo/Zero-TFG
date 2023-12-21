package com.zero.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.DeveloperAPI;
import com.zero.auxiliar.DeveloperList;
import com.zero.auxiliar.PlatformAPI;
import com.zero.domain.Developer;
import com.zero.domain.Platform;
import com.zero.repository.DeveloperRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeveloperService {
	
	private final String RAWG_URL= "https://api.rawg.io/api/developers?key=e63df09f5ae744498fb5a5ee6d3ca236";
	
	@Autowired
	private final RestTemplate restTemplate;
	//Own Repository
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public DeveloperService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//Create method
	
	//Finds method
	
	public Collection<Developer> findAll(){
		
		Collection<Developer> developers;
		
		developers = developerRepository.findAll();
		
		return developers;
	}
	
	public Page<Developer> findAllPagining(Pageable pageable){
		Page<Developer> pDeveloper;
		
		pDeveloper = this.developerRepository.findAll(pageable);
		
		return pDeveloper;
	}
	
	public Developer findById(int id) {
		Developer result;
		
		result = this.developerRepository.findById(id);
		
		return result;
	}
	
	//Others method
	
	public void getAPIDeveloper() {
		int page=1;
		
		while(true) {
			ResponseEntity<DeveloperList> responseEntity= restTemplate.exchange(RAWG_URL + "&page=" + page + "&page_size=15",
					HttpMethod.GET, null,DeveloperList.class);
			
			if(responseEntity.getStatusCode().is2xxSuccessful() && page< 3) {
				DeveloperList developerList = responseEntity.getBody();
				
				if(developerList!=null && developerList.getResults()!=null) {
					List<DeveloperAPI> developers = developerList.getResults();
					
					
					for(DeveloperAPI dAPI : developers) {
						
						if(developerRepository.findBySlug(dAPI.getSlug())==null) {
						
							String RAWG_URL2= "https://api.rawg.io/api/developers/"+ dAPI.getId()+"?key=e63df09f5ae744498fb5a5ee6d3ca236";
							
							ResponseEntity<DeveloperAPI> responseEntity2= restTemplate.exchange(RAWG_URL2,
									HttpMethod.GET, null,DeveloperAPI.class);
							
							DeveloperAPI aux = responseEntity2.getBody();
							
							Developer addDeveloper = new Developer();
							
							addDeveloper.setDescription(aux.getDescription());
							addDeveloper.setSlug(aux.getSlug());
							addDeveloper.setName(aux.getName());
							
							developerRepository.save(addDeveloper);
						}
					}
				}
				page=page+1;
			}else {
				break;
			}
			
		}
	}
	
	public List<DeveloperAPI> developerApi(){
		List<DeveloperAPI> developersAPI = new ArrayList<DeveloperAPI>();
		
		Collection<Developer> developers = this.developerRepository.findAll();
		
		for(Developer developer : developers) {
			DeveloperAPI d = new DeveloperAPI();
			
			d.setId(developer.getId());
			d.setName(developer.getName());
			d.setSlug(developer.getSlug());
			d.setDescription(developer.getDescription());
			d.setGames_count(developer.getGames().size());
			
			developersAPI.add(d);
		}
		
		return developersAPI;
	}

}
