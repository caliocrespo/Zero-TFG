package com.zero.auxiliar;

public class DeveloperAPI {
	private Integer id;
    private String name;
    private String slug;
    private Integer games_count;
    private String image_background;
    private String description;
    
    
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSlug() {
		return slug;
	}
	public Integer getGames_count() {
		return games_count;
	}
	public String getImage_background() {
		return image_background;
	}
	public String getDescription() {
		return description;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public void setGames_count(Integer games_count) {
		this.games_count = games_count;
	}
	public void setImage_background(String image_background) {
		this.image_background = image_background;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
