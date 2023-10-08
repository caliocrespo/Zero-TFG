package com.zero.auxiliar;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GameAPI {
    private Integer id;
    private String slug;
    private String name;
    private String released;
    private Boolean tba;
    @JsonProperty("background_image")
    private String backgroundImage;
    private Double rating;
    @JsonProperty("rating_top")
    private Integer ratingTop;
    @JsonProperty("ratings_count")
    private Integer ratingsCount;
    @JsonProperty("reviews_text_count")
    private String reviewsTextCount;
    private Integer added;
    @JsonProperty("added_by_status")
    private Map<String, Integer> addedByStatus;
    private Integer metacritic;
    private Integer playtime;
    @JsonProperty("suggestions_count")
    private Integer suggestionsCount;
    private String updated;
    @JsonProperty("esrb_rating")
    private EsrbRating esrbRating;
    private List<PlatformAPI> platforms;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Boolean getTba() {
        return tba;
    }

    public void setTba(Boolean tba) {
        this.tba = tba;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingTop() {
        return ratingTop;
    }

    public void setRatingTop(Integer ratingTop) {
        this.ratingTop = ratingTop;
    }

  

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getReviewsTextCount() {
        return reviewsTextCount;
    }

    public void setReviewsTextCount(String reviewsTextCount) {
        this.reviewsTextCount = reviewsTextCount;
    }

    public Integer getAdded() {
        return added;
    }

    public void setAdded(Integer added) {
        this.added = added;
    }

    public Map<String, Integer> getAddedByStatus() {
        return addedByStatus;
    }

    public void setAddedByStatus(Map<String, Integer> addedByStatus) {
        this.addedByStatus = addedByStatus;
    }

    public Integer getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Integer getSuggestionsCount() {
        return suggestionsCount;
    }

    public void setSuggestionsCount(Integer suggestionsCount) {
        this.suggestionsCount = suggestionsCount;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public EsrbRating getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(EsrbRating esrbRating) {
        this.esrbRating = esrbRating;
    }

    public List<PlatformAPI> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformAPI> platforms) {
        this.platforms = platforms;
    }
}

