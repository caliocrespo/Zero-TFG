package com.zero.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.service.ProgressService;
import com.zero.auxiliar.ProgressAPI;

@RestController
@RequestMapping("/api/progress")
public class ProgressAPIController {

	@Autowired
	ProgressService progressService;
	
	@GetMapping
	public List<ProgressAPI> getProgress(@RequestParam(required = false) Integer id, @RequestParam (required=false) String gameName,
			@RequestParam(required = false) Integer gameId, @RequestParam(required = false) Double minRating,
			@RequestParam (required = false) Double maxRating, @RequestParam (required = false) Boolean review,
			@RequestParam (required = false) Integer reviewId, @RequestParam (required = false) String username){
		List<ProgressAPI> progressList = progressService.progressAPI();
		
		progressList = progressList.stream().filter(progress -> (id == null || progress.getId() == id.intValue())
				&& (gameName == null || progress.getGame().getSlug().equalsIgnoreCase(gameName))
				&& (gameId == null || progress.getGame().getId() == gameId.intValue())
				&& (minRating == null || progress.getRating() >= minRating)
				&& (maxRating == null || progress.getRating() <= maxRating)
				&& (review == null || (review && progress.getReview()!=null))
				&& (reviewId == null || (progress.getReview()!=null && progress.getReview().getId() == reviewId.intValue()))
				&& (username == null || progress.getUsername().equalsIgnoreCase(username))).collect(Collectors.toList());
		
		return progressList;
	}
}
