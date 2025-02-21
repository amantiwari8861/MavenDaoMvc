package com.training.model;

import java.sql.Date;
import java.time.LocalDate;

public class Student 
{
	private long id;
	private String name;
	private long mobileNo;
	private char gender;
	private float gradPerc;
	private double fee;
	private boolean isMember;
	private Date dob;
	private LocalDate joinedAt;
	
	public Student() {
	}
	public Student(long id, String name, long mobileNo, char gender, float gradPerc, double fee, boolean isMember,
			Date dob, LocalDate joinedAt) {
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.gradPerc = gradPerc;
		this.fee = fee;
		this.isMember = isMember;
		this.dob = dob;
		this.joinedAt = joinedAt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public float getGradPerc() {
		return gradPerc;
	}
	public void setGradPerc(float gradPerc) {
		this.gradPerc = gradPerc;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public LocalDate getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(LocalDate joinedAt) {
		this.joinedAt = joinedAt;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + ", gender=" + gender + ", gradPerc="
				+ gradPerc + ", fee=" + fee + ", isMember=" + isMember + ", dob=" + dob + ", joinedAt=" + joinedAt
				+ "]";
	}
}
