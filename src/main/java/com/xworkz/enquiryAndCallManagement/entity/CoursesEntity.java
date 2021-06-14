package com.xworkz.enquiryAndCallManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "courses")
@NamedQueries({ @NamedQuery(name = "getAllCourses", query = "select ce.courseName from CoursesEntity ce") })
public class CoursesEntity {

	@Id
	@Column(name = "id")
	@GenericGenerator(name = "course", strategy = "increment")
	@GeneratedValue(generator = "course")
	private int id;
	@Column(name = "course_name")
	private String courseName;

}
