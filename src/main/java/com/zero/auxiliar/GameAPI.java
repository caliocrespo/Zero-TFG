package com.zero.auxiliar;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GameAPI {
    private Integer id;
    private String slug;
    private String name;
    private String description;
    private String released;
    @JsonProperty("background_image")
    private String backgroundImage; 
   
    private Double rating;
    
    public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	private List<PlatformListAPI> platforms;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

   

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

   

    public List<PlatformListAPI> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformListAPI> platforms) {
        this.platforms = platforms;
    }
}

