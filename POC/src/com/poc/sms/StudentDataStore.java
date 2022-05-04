package com.poc.sms;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class StudentDataStore {
	
	public static void dateGenerator(Student sobj)
	{
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date curDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		sobj.setAdmDate(curDate);
	}
	
	public static Set<Student> populateStudentsInfo(String objFormStrategy) throws Exception {
		Set<Student> listStudents = new HashSet<>();
		if(objFormStrategy.equalsIgnoreCase(SmsConstants.STUD_INSTANCES_DYNAMIC)) {
			Student std1 = new Student();
			String uniqueID = UUID.randomUUID().toString();
			std1.setId(uniqueID);
			std1.setRollNo(std1.getId());	
			std1.setName("Jacob");
			std1.setAge(24);
			std1.setEmail("jacob.marsh@gmail.com");
			std1.setCourse("PGD-ADVJAVA");
			std1.setAddress("Mumbai, India");
			std1.setPrevEducation("BTECH-CS");
			std1.setSubmittedFees(60000);
			std1.setTotalCourseFees(80000);
			std1.setCourseFeeStatus(CourseFeeStatus.PENDING.name());
			std1.setResultStatus(ResultStatus.CONTINUEING.name());
			std1.setCourseDuration(365);
			std1.setAttendance(64);
			std1.setAdmissionYear("2018");
			dateGenerator(std1);

			Student std2 = new Student();
			String uniqueID2 = UUID.randomUUID().toString();
			std2.setId(uniqueID2);
			std2.setRollNo(std2.getId());	
			std2.setName("Peter");
			std2.setAge(26);
			std2.setEmail("peter.couel@gmail.com");
			std2.setCourse("PGD-ASPDOTNET");
			std2.setAddress("Kolkata, India");
			std2.setPrevEducation("BTECH-ELE");
			std2.setSubmittedFees(80000);
			std2.setTotalCourseFees(80000);
			std2.setCourseFeeStatus(CourseFeeStatus.SUBMITTED.name());
			std2.setResultStatus(ResultStatus.CONTINUEING.name());
			std2.setCourseDuration(365);
			std2.setAttendance(120);
			std2.setAdmissionYear("2016");
			dateGenerator(std2);		
			
			Student std3 = new Student();
			String uniqueID3 = UUID.randomUUID().toString();
			std3.setId(uniqueID3);
			std3.setRollNo(std3.getId());	
			std3.setName("John");
			std3.setAge(28);
			std3.setEmail("john.marsh@gmail.com");
			std3.setCourse("PGD-ADVJAVA");
			std3.setAddress("Bangalore, India");
			std3.setPrevEducation("BTECH-ELE");
			std3.setSubmittedFees(50000);
			std3.setTotalCourseFees(80000);
			std3.setCourseFeeStatus(CourseFeeStatus.PENDING.name());
			std3.setResultStatus(ResultStatus.CONTINUEING.name());
			std3.setCourseDuration(365);
			std3.setAttendance(126);
			std3.setAdmissionYear("2018");
			dateGenerator(std3);			
			
			Student std4 = new Student();
			String uniqueID4 = UUID.randomUUID().toString();
			std4.setId(uniqueID4);
			std4.setRollNo(std4.getId());	
			std4.setName("Rahul");
			std4.setAge(25);
			std4.setEmail("rahul.kumar@gmail.com");
			std4.setCourse("PGD-PHP");
			std4.setAddress("Pune, India");
			std4.setPrevEducation("BTECH-IT");
			std4.setSubmittedFees(80000);
			std4.setTotalCourseFees(80000);
			std4.setCourseFeeStatus(CourseFeeStatus.SUBMITTED.name());
			std4.setResultStatus(ResultStatus.FAILED.name());
			std4.setCourseDuration(365);
			std4.setAttendance(160);
			std4.setAdmissionYear("2016");
			dateGenerator(std4);			
			
			Student std5 = new Student();
			String uniqueID5 = UUID.randomUUID().toString();
			std5.setId(uniqueID5);
			std5.setRollNo(std5.getId());	
			std5.setName("Wilson");
			std5.setAge(22);
			std5.setEmail("wilson.kate@gmail.com");
			std5.setCourse("PGD-ADVJAVA");
			std5.setAddress("Pune, India");
			std5.setPrevEducation("BTECH-CS");
			std5.setSubmittedFees(60000);
			std5.setTotalCourseFees(80000);
			std5.setCourseFeeStatus(CourseFeeStatus.PENDING.name());
			std5.setResultStatus(ResultStatus.CONTINUEING.name());
			std5.setCourseDuration(365);
			std5.setAttendance(64);
			std5.setAdmissionYear("2018");
			dateGenerator(std5);
						
			listStudents.add(std1);
			listStudents.add(std2);
			listStudents.add(std3);
			listStudents.add(std4);
			listStudents.add(std5);
			
		}else if(objFormStrategy.equalsIgnoreCase(SmsConstants.STUD_INSTANCES_STATIC)) {
			//Load from file
			
		}else {
			//Do nothing and display warning by throwing exception
			throw new Exception("Please provide strategy to load students information..");
		}
		
		return listStudents;
	}

}
