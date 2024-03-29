package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
public class Cour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false )
    private String category;
    @Column(nullable = false )
    private int difficulty;
    @Column(nullable = false )
    private Boolean premium;
    @Column(nullable = true )
    private String recommendedLevel;
	@ManyToMany
    @JoinTable(name = "recommended_courses",
            joinColumns = @JoinColumn(name = "cour_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Cour> recommendedCourses;
    @Column(nullable = true )
    @OneToMany(mappedBy = "cour")
    private List<Lesson> lessons;
    @Column(nullable = true )
    @Lob
    private byte[] image;


	public Cour(int id, String title, String description, String category, int difficulty, Boolean premium,
			String recommendedLevel, List<Cour> recommendedCourses, List<Lesson> lessons, byte[] image) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.difficulty = difficulty;
		this.premium = premium;
		this.recommendedLevel = recommendedLevel;
		this.recommendedCourses = recommendedCourses;
		this.lessons = lessons;
        this.image = image;
	}
	public Cour() {
		super();
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
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<Cour> getRecommendedCourses() {
		return recommendedCourses;
	}

	public void setRecommendedCourses(List<Cour> recommendedCourses) {
		this.recommendedCourses = recommendedCourses;
	}
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
