package com.training.model;

public class Courses {

	private int course_id;
	private String name ;
	private double price;
	private double duration;
	private String technologies;
	private String affiliatedBy;
	
	public Courses(int course_id, String name, double price, double duration, String technologies,
			String affiliatedBy) {
		
		this.course_id = course_id;
		this.name = name;
		this.price = price;
		this.duration = duration;
		this.technologies = technologies;
		this.affiliatedBy = affiliatedBy;
	}
	
	public Courses() {
		
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getTechnologies() {
		return technologies;
	}
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	public String getAffiliatedBy() {
		return affiliatedBy;
	}
	public void setAffiliatedBy(String affiliatedBy) {
		this.affiliatedBy = affiliatedBy;
	}
	
	
	@Override
	public String toString() {
		return "Courses [course_id=" + course_id + ", name=" + name + ", price=" + price + ", duration=" + duration
				+ ", technologies=" + technologies + ", affiliatedBy=" + affiliatedBy + "]";
	}
	
	
}
