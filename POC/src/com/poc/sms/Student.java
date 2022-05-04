package com.poc.sms;

import java.util.Date;
import java.util.Objects;

public class Student {
	
	private String id,rollNo,name,course,address,prevEducation,courseFeeStatus,resultStatus,admissionYear,email;	
	private int age,courseDuration,attendance;	
	private double submittedFees,totalCourseFees;
    private Date admDate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrevEducation() {
		return prevEducation;
	}

	public void setPrevEducation(String prevEducation) {
		this.prevEducation = prevEducation;
	}

	public double getSubmittedFees() {
		return submittedFees;
	}

	public void setSubmittedFees(double submittedFees) {
		this.submittedFees = submittedFees;
	}

	public double getTotalCourseFees() {
		return totalCourseFees;
	}

	public void setTotalCourseFees(double totalCourseFees) {
		this.totalCourseFees = totalCourseFees;
	}

	public String getCourseFeeStatus() {
		return courseFeeStatus;
	}

	public void setCourseFeeStatus(String courseFeeStatus) {
		this.courseFeeStatus = courseFeeStatus;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public String getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}

	public Date getAdmDate() {
		return admDate;
	}

	public void setAdmDate(Date admDate) {
		this.admDate = admDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
		return "Student [id=" + id + " | rollNo=" + rollNo + " | name=" + name + " | age=" + age + " | email=" + email
				+ " | course=" + course + " | address=" + address + " | prevEducation=" + prevEducation
				+ " | submittedFees=" + submittedFees + " | totalCourseFees=" + totalCourseFees + " | courseFeeStatus="
				+ courseFeeStatus + " | resultStatus=" + resultStatus + " | courseDuration=" + courseDuration
				+ " | attendance=" + attendance + " | admissionYear=" + admissionYear + " | admDate=" + admDate + "]";
	
	}
	

}
