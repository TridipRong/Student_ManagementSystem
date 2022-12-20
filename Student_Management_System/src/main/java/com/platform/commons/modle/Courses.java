package com.platform.commons.modle;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;

	private String courseName;
	private String description;
	private String courseType;
	private String duration;
	private String topics;
	
	@ManyToMany(cascade = CascadeType.ALL)
	List<Students> students = new ArrayList<>();

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", description=" + description
				+ ", courseType=" + courseType + ", duration=" + duration + ", topics=" + topics + "]";
	}
}
