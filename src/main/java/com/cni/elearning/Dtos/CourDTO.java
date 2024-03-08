package com.cni.elearning.Dtos;

import java.util.List;

public class CourDTO {
    private int id;
    private String title;
    private String description;
    private String category;
    private int difficulty;
    private Boolean premium;
    private String recommendedLevel;
    private List<String> recommendedCourses;
    private Byte[] image;
  
    public CourDTO(int id, String title, String description, String category, int difficulty, Boolean premium,
                   String recommendedLevel, List<String> recommendedCourses,Byte[] image) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.category = category;
      this.difficulty = difficulty;
      this.premium = premium;
      this.recommendedLevel = recommendedLevel;
      this.recommendedCourses = recommendedCourses;
      this.image=image;
    }
  
  
    public int getId() {
      return id;
    }
  
    public void setId(int id) {
      this.id = id;
    }
  
    public String getTitle() {
      return title;
    }
  
    public void setTitle(String title) {
      this.title = title;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }
  
    public String getCategory() {
      return category;
    }
  
    public void setCategory(String category) {
      this.category = category;
    }
  
    public int getDifficulty() {
      return difficulty;
    }
  
    public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
    }
  
    public Boolean getPremium() {
      return premium;
    }
  
    public void setPremium(Boolean premium) {
      this.premium = premium;
    }
  
    public String getRecommendedLevel() {
      return recommendedLevel;
    }
  
    public void setRecommendedLevel(String recommendedLevel) {
      this.recommendedLevel = recommendedLevel;
    }
  
    public List<String> getRecommendedCourses() {
      return recommendedCourses;
    }
  
    public void setRecommendedCourses(List<String> recommendedCourses) {
      this.recommendedCourses = recommendedCourses;
    }

    public Byte[] getImage() {
      return image;
    }

    public void setImage(Byte[] image) {
      this.image = image;
    }
  }
