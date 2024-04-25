package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<Cour> getRecommendedCourses() {
		return recommendedCourses;
	}

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<Lesson> getLessons() {
		return lessons;
	}


}
