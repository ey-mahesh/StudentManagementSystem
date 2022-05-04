package com.poc.sms;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentOperations {

public static Set<Student> lstObj;
	
	public static Map<String, Student> mpObj = new HashMap<>();
	
	public static Map<Student, Student> mpObjOne = new HashMap<>();
	
	public static Scanner sc = new Scanner(System.in);
	
	static {
			try {
				lstObj = StudentDataStore.populateStudentsInfo(SmsConstants.STUD_INSTANCES_DYNAMIC);
				populateMapObjWithUpdatedStudentInfo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }	
	
	public static void insertStudent() {
		System.out.println("Please provide new student information as instrcted below..");
		
		Student sObj = new Student();
		
		String uniqueID = UUID.randomUUID().toString();
		sObj.setId(uniqueID);
		sObj.setRollNo(sObj.getId());
		
		System.out.println("Please input Student name");
		String name = sc.next();
		if(null!=name)
			sObj.setName(name);
		
		System.out.println("Please input Student age");
		String age = sc.next();
		if(null!=age)
			sObj.setAge(Integer.valueOf(age));
		
		System.out.println("Please input Student email");
		String email = sc.next();
		if(null!=email)
			sObj.setEmail(email);
		sc.nextLine();
		
		System.out.println("Please input Student course");
		String course = sc.nextLine();
		if(null!=course)
			sObj.setCourse(course);
			
		System.out.println("Please input Student address");
		String adr = sc.nextLine();
		if(null!=adr)
			sObj.setAddress(adr);
		
		System.out.println("Please input Student prev education");
		String prevEdu = sc.nextLine();
		if(null!=prevEdu)
			sObj.setPrevEducation(prevEdu);
		
		System.out.println("Please input Student submitted fees");
		String subFees = sc.next();
		if(null!=subFees)
			sObj.setSubmittedFees(Double.valueOf(subFees));
		
		System.out.println("Please input Student total course fees");
		String totalCourseFees = sc.next();
		if(null!=totalCourseFees)
			sObj.setTotalCourseFees(Double.valueOf(totalCourseFees));
		
		
		System.out.println("Please input Student course fee status");
		String courseFeeStatus = sc.next();
		if(null!=courseFeeStatus)
			sObj.setCourseFeeStatus(courseFeeStatus);
		
		
		System.out.println("Please input Student result status");
		String resultStatus = sc.next();
		if(null!=resultStatus)
			sObj.setResultStatus(resultStatus);
		
		System.out.println("Please input Student course duration");
		String courseDur = sc.next();
		if(null!=courseDur)
			sObj.setCourseDuration(Integer.valueOf(courseDur));
		
		
		System.out.println("Please input Student attendance");
		String studAttendance = sc.next();
		if(null!=studAttendance)
			sObj.setAttendance(Integer.valueOf(studAttendance));
		
		
		System.out.println("Please input Student admission year");
		String admYear = sc.next();
		if(null!=admYear)
			sObj.setAdmissionYear(admYear);
		
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date curDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		sObj.setAdmDate(curDate);
			
		boolean studRegStatus = lstObj.add(sObj);
		
		mpObj.put(sObj.getId(), sObj);
				
		if(studRegStatus)
			System.out.println("Student was registered successfull");
		else
			System.out.println("Student registration was unsuccessfull");
	}
	
	public static void displayStudents() throws Exception {
		if(lstObj==null || lstObj.isEmpty())
			throw new Exception("Student list is empty or null..");
		
		System.out.println();
		System.out.println("All available Students are listed below..");
		lstObj.forEach(stdObj->System.out.println(stdObj));
		
	}
	
	public static void updateStudent() throws Exception {
		boolean updStatus = true;
		displayStudents();
		System.out.println("Please provide the student id/roll no to edit/update the student..");
		String stdIdOrRoll = sc.next();
				
		//Get a particular student by using id or roll
		Student s = mpObj.get(stdIdOrRoll);
		
		if(s==null)
			throw new Exception("Student information was not found..");
		
		
		boolean isStudObjDirty = selectAndInputStudentFieldsForEdit(s);
				
		if(isStudObjDirty) {
			//Update above Student in the collection.
			updStatus = lstObj.add(s);
		}
		
		if(!updStatus)
			System.out.println("Student was updated successfully..");
		else
			System.out.println("Updation was unsuccessful..");
		
		displayStudents();
	}
	
	public static boolean selectAndInputStudentFieldsForEdit(Student s) throws Exception {
		Map<Integer, String> mpObj = new HashMap<>();
		boolean isStudObjDirty = true;
		while(true) {
			printStudFieldSelInstructions();

			String regex = "\\d+";
			String str = sc.next();
			while(!str.matches(regex)) {
				System.out.println("Invalid input..Please select one of the below numbers only..");
				printStudFieldSelInstructions();
				str = sc.next();
			}
			
			Integer fieldToUpdate = Integer.valueOf(str);
			
			while(fieldToUpdate<1 || fieldToUpdate>13) {
				System.out.println("Invalid input.Please select one of numbers in below range..");
				printStudFieldSelInstructions();
				fieldToUpdate = sc.nextInt();
			}
			
			System.out.println("Please input your value for selected student property..");
			String valForField = sc.next();
			
			mpObj.put(fieldToUpdate, valForField);			
			
			System.out.println("do you want to update any more field..? Y OR N");
			
			String usrStatus = sc.next();
			
			if(usrStatus.equalsIgnoreCase("Y"))
				//Nothing to do here
				continue;
			else if(usrStatus.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid input..");
		}
		for(int fieldSel:mpObj.keySet()) {
			switch(fieldSel){
				case 1:
					s.setName(mpObj.get(fieldSel));
					break;
				case 2:
					s.setAge(Integer.valueOf(mpObj.get(fieldSel)));
					break;
				case 3:
					s.setEmail(mpObj.get(fieldSel));
					break;
				case 4:
					s.setCourse(mpObj.get(fieldSel));
					break;
				case 5:
					s.setAddress(mpObj.get(fieldSel));
					break;
				case 6:
					s.setPrevEducation(mpObj.get(fieldSel));
					break;
				case 7:
					s.setSubmittedFees(Double.valueOf(mpObj.get(fieldSel)));
					break;
				case 8:
					s.setTotalCourseFees(Double.valueOf(mpObj.get(fieldSel)));
					break;
				case 9:
					s.setCourseFeeStatus(mpObj.get(fieldSel));
					break;
				case 10:
					s.setResultStatus(mpObj.get(fieldSel));
					break;
				case 11:
					s.setCourseDuration(Integer.valueOf(mpObj.get(fieldSel)));
					break;
				case 12:
					s.setAttendance(Integer.valueOf(mpObj.get(fieldSel)));
					break;
				case 13:
					s.setAdmissionYear(mpObj.get(fieldSel));
					break;
				default:
					System.out.println("No matched operation found..");
					break;
			}
		}
		if(mpObj==null || mpObj.isEmpty() || mpObj.keySet()==null || mpObj.keySet().isEmpty()) {
			isStudObjDirty = false;
		}	
		return isStudObjDirty;
	}
	
	public static void deleteStudent() throws Exception {
		
		displayStudents();
		System.out.println("Please provide the student id/roll no to delete the student..");
		String stdIdOrRoll = sc.next();
		
		//Get a particular student by using id or roll
		Student s = mpObj.get(stdIdOrRoll);
		
		//Delete above Student from the collection.
		boolean delStatus = lstObj.remove(s);
		
		if(delStatus)
			System.out.println("Student was deleted successfully..");
		else
			System.out.println("Deletion was unsuccessful..");
		displayStudents();
	}
	
	public static void verifyStudentObjIdentityThroughMap() {
		
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return;
		}
		
		System.out.println("Please input exact year at console..");
		String admYear = sc.next();
		
		System.out.println("Please input exact course name at console..");
		String courseName = sc.next();
		Set<Student> studList = getStudentsByYearAndCourse(admYear, courseName);
		Map<Student, Student> mp = new HashMap<>();
		
		if(studList!=null && !studList.isEmpty()) {
			for(Student s:studList) {
				mp.put(s, s);
			}
			
			studList.forEach(System.out::println);
			
			Student sObjOne = getStudentById();
			Student sOrg = mp.get(sObjOne);
			
			System.out.println("Original Student object from map before updation: "+sOrg);
			
			
			sObjOne.setSubmittedFees(4000);
			mp.put(sObjOne, sObjOne);			
			
			Student sUpd = mp.get(sObjOne);

			
			System.out.println("Student object from map after updation: "+sUpd);
			System.out.println("sOrg==sUpd: "+(sOrg==sUpd));
			System.out.println("Original Student object from map after updation: "+sOrg);

			System.out.println("sObjOne==sOrg: "+(sObjOne==sOrg));
			System.out.println("sObjOne==sUpd: "+(sObjOne==sUpd));
        }

 }

		public static void custViewOfStudentsByYearAndCourse() {
			if(lstObj==null || lstObj.isEmpty()) {
				System.out.println("Student data store is empty");
				return;
			}
			System.out.println("Please input exact year at console..");
			String admYear = sc.next();
			
			System.out.println("Please input exact course name at console..");
			String courseName = sc.next();
			Set<Student> studList = getStudentsByYearAndCourse(admYear, courseName);
			
			if(studList!=null && !studList.isEmpty()) {
			
				List<String> lstCustInfo = studList.stream().map(s-> "Roll: "+s.getRollNo()+", Address: "+s.getAddress()
				+", Course: "+s.getCourse()+", Admission Year: "+s.getAdmissionYear()).collect(Collectors.toList());
				
				lstCustInfo.forEach(System.out::println);
				
			}else {
				System.out.println("No Students found on your search criteria, Please choose other search options..");
			}
		}

		public static void sortAndViewStudentsByImpFactors() {
		
			if(lstObj==null || lstObj.isEmpty()) {
				System.out.println("Student data store is empty");
				return;
			}
			
			Set<Student> studList = null;
			Set<Student> studListTemp = null;
			
			System.out.println("Do you wish to sort & view students by admission year & course ?Y OR N");
			String usrPref = sc.next();
			
			if(usrPref.equalsIgnoreCase("Y")) {
				System.out.println("Please input exact year at console..");
				String admYear = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseName = sc.next();
				studList = getStudentsByYearAndCourse(admYear, courseName);
			}else if(usrPref.equalsIgnoreCase("N")) {
				studList = lstObj;
			}else {
				System.out.println("Please provide a valid input..");
				return;
			}
							
			printOptionsForStudSorting();
			
			String searchOpt = sc.next();	
			System.out.println("Please enter Order to be in : [ 1.ASC | 2.DESC] ");
			int ordChoice = sc.nextInt();
			
			switch(searchOpt) {
			
				case "1":				
					if(studList!=null && !studList.isEmpty() &&ordChoice==1) {
						studListTemp = studList.stream().sorted(Comparator.comparingInt(Student::getAge)).collect(Collectors.toCollection(LinkedHashSet::new));
						studListTemp.forEach(System.out::println);
					}
					else if(studList!=null && !studList.isEmpty() && ordChoice==2){
						studListTemp = studList.stream().sorted(Comparator.comparingInt(Student::getAge)).collect(Collectors.toCollection(LinkedHashSet::new));
						List<Student> list = studListTemp.stream().collect(Collectors.toList());
						Collections.reverse(list);
						list.forEach(System.out::println);
						}
					else {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}								
					break;
					
				case "2":				
					if(studList!=null && !studList.isEmpty()&&ordChoice==1) {
						studListTemp = studList.stream().sorted(Comparator.comparingDouble(Student::getSubmittedFees)).collect(Collectors.toCollection(LinkedHashSet::new));
						studListTemp.forEach(System.out::println);
					}
					else if(studList!=null && !studList.isEmpty() && ordChoice==2){
						studListTemp = studList.stream().sorted(Comparator.comparingDouble(Student::getSubmittedFees)).collect(Collectors.toCollection(LinkedHashSet::new));
						List<Student> list = studListTemp.stream().collect(Collectors.toList());
						Collections.reverse(list);
						list.forEach(System.out::println);
						}
					else {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}								
					break;
					
				case "3":				
					if(studList!=null && !studList.isEmpty()&& ordChoice==1) {
						studListTemp = studList.stream().sorted(Comparator.comparingInt(Student::getAttendance)).collect(Collectors.toCollection(LinkedHashSet::new));
						studListTemp.forEach(System.out::println);
					}
					else if(studList!=null && !studList.isEmpty() && ordChoice==2){
						studListTemp = studList.stream().sorted(Comparator.comparingInt(Student::getAttendance)).collect(Collectors.toCollection(LinkedHashSet::new));
						List<Student> list = studListTemp.stream().collect(Collectors.toList());
						Collections.reverse(list);
						list.forEach(System.out::println);
						}else {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}								
					break;
					
				case "4":				
					if(studList!=null && !studList.isEmpty() && ordChoice==1) {
						studListTemp = studList.stream().sorted(Comparator.comparing(Student::getCourseFeeStatus)).collect(Collectors.toCollection(LinkedHashSet::new));
						studListTemp.forEach(System.out::println);
					}
					else if(studList!=null && !studList.isEmpty() && ordChoice==2){
						studListTemp = studList.stream().sorted(Comparator.comparing(Student::getCourseFeeStatus)).collect(Collectors.toCollection(LinkedHashSet::new));
						List<Student> list = studListTemp.stream().collect(Collectors.toList());
						Collections.reverse(list);
						list.forEach(System.out::println);
						}else {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}								
					break;
					
				case "5":				
					if(studList!=null && !studList.isEmpty()&& ordChoice==1) {
						studListTemp = studList.stream().sorted(Comparator.comparing(Student::getResultStatus)).collect(Collectors.toCollection(LinkedHashSet::new));
						studListTemp.forEach(System.out::println);
					}
					else if(studList!=null && !studList.isEmpty() && ordChoice==2){
						studListTemp = studList.stream().sorted(Comparator.comparing(Student::getResultStatus)).collect(Collectors.toCollection(LinkedHashSet::new));
						List<Student> list = studListTemp.stream().collect(Collectors.toList());
						Collections.reverse(list);
						list.forEach(System.out::println);
						}else {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}								
					break;
					
				default:
					System.out.println("No matched option provided, Please input correctly again");
					break;
				
			}
		
		}

	public static Set<Student> getStudentsByYearAndCourse(String admYear, String courseName){
		Set<Student> studListTemp = lstObj.stream().filter(std->{
			if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
				return true;
			else
				return false;
		}).collect(Collectors.toSet());
		
		return studListTemp;
	}

	public static void sortStudentsBySubFeesUsingMapKeySorting() {
		if(lstObj==null || lstObj.isEmpty() || mpObj==null || mpObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return;
			}
		
		Set<Student> searchedstudents = new HashSet<>();
		
		Map<Student, Integer> stMpObj = new HashMap<>();
		
		System.out.println("Please input exact year at console..");
		String admYear = sc.next();
		
		System.out.println("Please input exact course name at console..");
		String courseName = sc.next();
		
		Set<Student> studListTemp = lstObj.stream().filter(std->{
			if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
				return true;
			else
				return false;
		}).collect(Collectors.toSet());
		
		if(studListTemp!=null && !studListTemp.isEmpty()) {
			for(Student stdObj:studListTemp) {
				stMpObj.put(stdObj, stdObj.getAttendance());
			}
			System.out.println("Before sorting: ");
			stMpObj.entrySet().forEach(e->System.out.println(e.getKey().getRollNo()+" : "+e.getKey().getName()+" SubFees: "+e.getKey().getSubmittedFees()+" Attend: "+e.getValue()));
		
			Comparator<Student> bySubFeesStudComp = (Student o1, Student o2)-> Double.valueOf(o1.getSubmittedFees()).compareTo(Double.valueOf(o2.getSubmittedFees()));
			stMpObj = stMpObj.entrySet().stream().sorted(Map.Entry.comparingByKey(bySubFeesStudComp))
					.collect(Collectors.toMap(x->x.getKey(), x->x.getValue(), (oldVal,newVal)->oldVal, LinkedHashMap::new));
			
			System.out.println("=====================================================================================");
			System.out.println("After sorting: ");
			stMpObj.entrySet().forEach(e->System.out.println(e.getKey().getRollNo()+" : "+e.getKey().getName()+" SubFees: "+e.getKey().getSubmittedFees()+" Attend: "+e.getValue()));
		}else {
			System.out.println("No Students found on your search criteria, Please choose other search options..");
		}
	
	}

		public static void sortStudentsByAttendanceUsingMapValSorting() {		
				if(lstObj==null || lstObj.isEmpty() || mpObj==null || mpObj.isEmpty()) {
					System.out.println("Student data store is empty");
					return;
				}
				
				Set<Student> searchedstudents = new HashSet<>();
				
				Map<Student, Integer> stMpObj = new HashMap<>();
				
				System.out.println("Please input exact year at console..");
				String admYear = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseName = sc.next();
				
				Set<Student> studListTemp = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
						return true;
					else
						return false;
				}).collect(Collectors.toSet());
				
				if(studListTemp!=null && !studListTemp.isEmpty()) {
					for(Student stdObj:studListTemp) {
						stMpObj.put(stdObj, stdObj.getAttendance());
					}
								
					System.out.println("Before sorting: ");
					stMpObj.entrySet().forEach(e->System.out.println(e.getKey().getRollNo()+" : "+e.getKey().getName()+" Attend: "+e.getValue()));
					
					stMpObj = stMpObj.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
							.collect(Collectors.toMap(x->x.getKey(), x->x.getValue(), (oldVal,newVal)->oldVal, LinkedHashMap::new));
					
					System.out.println("======================================================================================");
					System.out.println("After sorting: ");
					stMpObj.entrySet().forEach(e->System.out.println(e.getKey().getRollNo()+" : "+e.getKey().getName()+" Attend: "+e.getValue()));
				
				}else {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}
		}
		
		public static Set<Student> getStudentsAsFeeDefaultersWithMaxSubFees(){
		
				Set<Student> searchedstudents = new HashSet<>();
				
				if(lstObj==null || lstObj.isEmpty()) {
					System.out.println("Student data store is empty");
					return searchedstudents;
				}
				
				System.out.println("Please input exact year at console..");
				String admYear = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseName = sc.next();
				
				Set<Student> studListTemp = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
						return true;
					else
						return false;
				}).filter(s->s.getCourseFeeStatus().equalsIgnoreCase("PENDING"))
				.collect(Collectors.toSet());
				
				if(studListTemp != null && !studListTemp.isEmpty()) {
					double maxSubFees = studListTemp.stream().max(Comparator.comparingDouble(Student::getSubmittedFees)).get().getSubmittedFees();
					
					searchedstudents = studListTemp.stream().filter(s->s.getSubmittedFees()==maxSubFees)
					.collect(Collectors.toSet());	
					
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}else {
						searchedstudents.forEach(System.out::println);
					}
				}else {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}
				return searchedstudents;
		}
		
		public static Set<Student> getStudentsAsFeeDefaultersWithMinSubFees(){
		Set<Student> searchedstudents = new HashSet<>();
		
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return searchedstudents;
		}
		
		System.out.println("Please input exact year at console..");
		String admYear = sc.next();
		
		System.out.println("Please input exact course name at console..");
		String courseName = sc.next();
		
		Set<Student> studListTemp = lstObj.stream().filter(std->{
			if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
				return true;
			else
				return false;
		}).filter(s->s.getCourseFeeStatus().equalsIgnoreCase("PENDING"))
		.collect(Collectors.toSet());
		
		if(studListTemp != null && !studListTemp.isEmpty()) {
			double minSubFees = studListTemp.stream().min(Comparator.comparingDouble(Student::getSubmittedFees)).get().getSubmittedFees();
			
			searchedstudents = studListTemp.stream().filter(s->s.getSubmittedFees()==minSubFees)
			.collect(Collectors.toSet());	
			
			if(searchedstudents == null || searchedstudents.isEmpty()) {
				System.out.println("No Students found on your search criteria, Please choose other search options..");
			}else {
				searchedstudents.forEach(System.out::println);
			}
		}else {
			System.out.println("No Students found on your search criteria, Please choose other search options..");
		}
		return searchedstudents;
		
		}
		
		public static void populateMapObjWithUpdatedStudentInfo() {
		if(lstObj!=null || !lstObj.isEmpty()) {
			lstObj.forEach(std->
			{
					mpObj.put(std.getId(), std);
					mpObjOne.put(std, std);
			});
		}else {
			System.out.println("Student data store is empty..");
			return;
		}
		}

		public static long getStudentsCount() {
		
		long count = 0;
		
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return count;
		}
		
		printOptionsForStudCount();
		
		String searchOpt = sc.next();		
		
		switch(searchOpt) {
		
			case "1":				
				System.out.println("Please input admission year at console..");
				String admYearOpt = sc.next();
				
				count = lstObj.stream().filter(std->std.getAdmissionYear().equals(admYearOpt)).count();
				
				if(count==0) {
					System.out.println("No Students were admitted on provided year..");
				}else {
					System.out.println("No of Students admited on the year "+admYearOpt+" is: "+count);
				}				
				break;
				
			case "2":				
				System.out.println("Please input exact course name at console..");
				String optedCourse = sc.next();
				
				count = lstObj.stream().filter(std->std.getCourse().equalsIgnoreCase(optedCourse)).count();
				
				if(count==0) {
					System.out.println("No Students were admitted on provided course..");
				}else {
					System.out.println("No of Students admitted in the course  "+optedCourse+" is: "+count);
				}				
				break;
				
			case "3":				
				System.out.println("Please input admission year at console..");
				String admYear = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseName = sc.next();
				
				count = lstObj.stream().filter(std->std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName)).count();
				
				if(count==0) {
					System.out.println("No Students were admitted on provided year and course..");
				}else {
					System.out.println("No of Students admited is: "+count);
				}				
				break;
				
			default:
				System.out.println("No matched option provided, Please input correctly again");
				break;
			
		}
		return count;
		}

		public static void viewTotalClassBunksOfStud() {
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return;
		}
		
		System.out.println("Please input exact student roll no at console..");
		String rollNoOnly = sc.next();
		
		Optional<Student> stdOpt = lstObj.stream().filter(std->std.getRollNo().equalsIgnoreCase(rollNoOnly)).findAny();
		
		if(stdOpt.isPresent()) {
			Student std = stdOpt.get();
			int daysBunked = std.getCourseDuration()-std.getAttendance();
			System.out.println("Classed bunked in days: "+daysBunked+" : "+std);
		}else {
			System.out.println("No Student found against provided roll no..");
		}
		
	}
		
		public static void viewPendingFeesOfStud() {
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			return;
		}
		
		System.out.println("Please input exact student roll no at console..");
		String rollNoOnly = sc.next();
		
		Optional<Student> stdOpt = lstObj.stream().filter(std->std.getRollNo().equalsIgnoreCase(rollNoOnly)).findAny();
		
		if(stdOpt.isPresent()) {
			Student std = stdOpt.get();
			double pendFees = std.getTotalCourseFees()-std.getSubmittedFees();
			System.out.println("Pending Fees: Rs."+pendFees+" : "+std);
		}else {
			System.out.println("No Student found against provided roll no..");
		}
		
		}
		
		public static Set<Student> searchStudents(){
		
		Set<Student> searchedstudents = null;
		
		if(lstObj==null || lstObj.isEmpty()) {
			System.out.println("Student data store is empty");
			searchedstudents = new HashSet<>();
			return searchedstudents;
		}
		
		printSearchStudentsOptions();
		String searchOpt = sc.next();		
		
		switch(searchOpt) {
		
			case "1":				
				System.out.println("Please input admission year at console..");
				String admYearOpt = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->std.getAdmissionYear().equals(admYearOpt)).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}				
				break;
				
			case "2":				
				System.out.println("Please input exact course name at console..");
				String optedCourse = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->std.getCourse().equalsIgnoreCase(optedCourse)).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}
				
				break;
				
			case "3":				
				System.out.println("Please input exact year at console..");
				String admYear = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseName = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYear) && std.getCourse().equalsIgnoreCase(courseName))
						return true;
					else
						return false;
				}).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}				
				break;
			
			case "4":				
				System.out.println("Please input exact year at console..");
				String admYearForName = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForName = sc.next();
				
				System.out.println("Please input exact student name provided at admission time..");
				String stdName = sc.next();
				
				//This is just to make the operation user friendly, no performance improvement here, 
				//indexing is still required at database level.
				searchedstudents = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYearForName) && 
							std.getCourse().equalsIgnoreCase(courseNameForName) &&
							std.getName().equalsIgnoreCase(stdName))
						return true;
					else
						return false;
				}).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}				
				break;
				
			case "5":				
				System.out.println("Please input exact year at console..");
				String admYearForEmail = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForEmail = sc.next();
				
				System.out.println("Please input exact email id at console..");
				String emailId = sc.next();
				
				//This is just to make the operation user friendly, no performance improvement here, 
				//indexing is still required at database level.
				searchedstudents = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYearForEmail) && 
							std.getCourse().equalsIgnoreCase(courseNameForEmail) &&
							std.getEmail().equalsIgnoreCase(emailId))
						return true;
					else
						return false;
				}).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}				
				break;
				
			case "6":				
				System.out.println("Please input exact year at console..");
				String admYearForRoll = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForRole = sc.next();
				
				System.out.println("Please input exact roll no at console..");
				String rollNo = sc.next();
				
				//This is just to make the operation user friendly, no performance improvement here, 
				//indexing is still required at database level.
				searchedstudents = lstObj.stream().filter(std->{
					if(std.getAdmissionYear().equals(admYearForRoll) && 
							std.getCourse().equalsIgnoreCase(courseNameForRole) &&
							std.getRollNo().equalsIgnoreCase(rollNo))
						return true;
					else
						return false;
				}).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}				
				break;
				
			case "7":				
				System.out.println("Please input exact name provided at admission time..");
				String stdNameOnly = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->std.getName().equalsIgnoreCase(stdNameOnly)).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}
				
				break;
				
			case "8":				
				System.out.println("Please input exact email at console..");
				String stdEmailOnly = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->std.getEmail().equalsIgnoreCase(stdEmailOnly)).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}
				
				break;
				
			case "9":				
				System.out.println("Please input exact student roll no at console..");
				String rollNoOnly = sc.next();
				
				searchedstudents = lstObj.stream().filter(std->std.getRollNo().equalsIgnoreCase(rollNoOnly)).collect(Collectors.toSet());
				
				if(searchedstudents == null || searchedstudents.isEmpty()) {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}else {
					searchedstudents.forEach(System.out::println);
				}
				
				break;
				
			case "10":				
				System.out.println("Please input exact year at console..");
				String admYearForFeeStatus = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForFeeStatus = sc.next();
				
				Set<Student> stdList = getStudentsByYearAndCourse(admYearForFeeStatus, courseNameForFeeStatus);
				
				if(stdList!=null && !stdList.isEmpty()) {
					
					System.out.println("Please input fee status either as PENDING or SUBMITTED");
					String stdFeeStatus = sc.next();
				
					searchedstudents = stdList.stream().filter(std->std.getCourseFeeStatus().equalsIgnoreCase(stdFeeStatus)).collect(Collectors.toSet());
					
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}else {
						searchedstudents.forEach(System.out::println);
					}
				}else {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}
				break;
				
			case "11":				
				System.out.println("Please input exact year at console..");
				String admYearForResultStatus = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForResultStatus = sc.next();
				
				Set<Student> stdListForResStatus = getStudentsByYearAndCourse(admYearForResultStatus, courseNameForResultStatus);
				
				if(stdListForResStatus!=null && !stdListForResStatus.isEmpty()) {
					
					System.out.println("Please input result status as PASSED, FAILED or CONTINUEING");
					String stdResStatus = sc.next();
				
					searchedstudents = stdListForResStatus.stream().filter(std->std.getResultStatus().equalsIgnoreCase(stdResStatus)).collect(Collectors.toSet());
					
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found on your search criteria, Please choose other search options..");
					}else {
						searchedstudents.forEach(System.out::println);
					}
				}else {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}				
				break;
				
			case "12":				
				System.out.println("Please input exact year at console..");
				String admYearForAge = sc.next();
				
				System.out.println("Please input exact course name at console..");
				String courseNameForAge = sc.next();
				
				Set<Student> stdListForAge = getStudentsByYearAndCourse(admYearForAge, courseNameForAge);
				
				if(stdListForAge!=null && !stdListForAge.isEmpty()) {
					
					System.out.println("Please input age to view students");
					String stdAge = sc.next();
					
					searchedstudents = stdListForAge.stream().filter(std->(std.getAge()<Integer.valueOf(stdAge))).collect(Collectors.toSet());
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found below provided age..");
					}else {
						System.out.println("Students found below provided age are..");
						searchedstudents.forEach(System.out::println);
					}
					
					searchedstudents = stdListForAge.stream().filter(std->(std.getAge()>Integer.valueOf(stdAge))).collect(Collectors.toSet());
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found above provided age..");
					}else {
						System.out.println("Students found above provided age are..");
						searchedstudents.forEach(System.out::println);
					}
					
					searchedstudents = stdListForAge.stream().filter(std->(std.getAge()==Integer.valueOf(stdAge))).collect(Collectors.toSet());
					
					if(searchedstudents == null || searchedstudents.isEmpty()) {
						System.out.println("No Students found having age equal to provided age..");
					}else {
						System.out.println("Students found at provided age are..");
						searchedstudents.forEach(System.out::println);
					}
				}else {
					System.out.println("No Students found on your search criteria, Please choose other search options..");
				}				
				break;
				
			default:
				System.out.println("No matched option provided, Please input correctly again");
				break;
			}	
		
		
				return searchedstudents;
		}

	public static Student getStudentById() {
	System.out.println("Please provide the student id..");
	String stdIdOrRoll = sc.next();
	
	//Get a particular student by using id or roll
	Student s = mpObj.get(stdIdOrRoll);
	
	//Optional<Student> stdOpt = lstObj.stream().filter(std->std.getId().equalsIgnoreCase(stdIdOrRoll)).findAny();
	//if(stdOpt.isPresent()) {
	//	System.out.println(stdOpt.get());
	//	return stdOpt.get();
	//	
	//}else {
	//	System.out.println("Student was not found");
	//}
	
	if(null==s)
		System.out.println("Student was not found");
	else
		System.out.println(s);
	
	return s;
	}
	public static void printStudFieldSelInstructions() {
		
		System.out.println("Please select below options to update fields");
		
		System.out.println("Press 1 & hit enter key to input name");
		
		System.out.println("Press 2 & hit enter key to input age");
		
		System.out.println("Press 3 & hit enter key to input email");
		
		System.out.println("Press 4 & hit enter key to input course name");
		
		System.out.println("Press 5 & hit enter key to input address");
		
		System.out.println("Press 6 & hit enter key to input prev education");
		
		System.out.println("Press 7 & hit enter key to input submitted fees");
		
		System.out.println("Press 8 & hit enter key to input total course fees");
				
		System.out.println("Press 9 & hit enter key to input course fee status");
		
		System.out.println("Press 10 & hit enter key to input result status");
		
		System.out.println("Press 11 & hit enter key to input course duration");
		
		System.out.println("Press 12 & hit enter key to input attendance");
		
		System.out.println("Press 13 & hit enter key to input admission year");
		
	}
	
	
	
	public static void printInstructions() {
		
		
		System.out.println("\t\tPress Options & hit Enter Key to perform below Operations");

		System.out.println();

		System.out.println("1.View  all students");
		
		System.out.println("2.Add a new student");
		
		System.out.println("3.Update an existing student");
		
		System.out.println("4.Delete an existing student");
		
		System.out.println("5.Get a student by ID or Roll No");
		
		System.out.println("6.Search students");
		
		System.out.println("7.View pending fees of any student");
		
		System.out.println("8.View total class bunked in days by any student");
		
		System.out.println("9.View student counts on the basis of admission year and course");
		
		System.out.println("10.View students having fee status pending but submitted max amounts..");
		
		System.out.println("11.View students having fee status pending but submitted min amounts..");
		
		System.out.println("12.View students sorted[map val sorting] by their attendance through map data structure");
		
		System.out.println("13.View students sorted by their[map key sorting] submitted fees through map data structure ");
		
		System.out.println("14.Sort & view students on various improtant factors");
		
		System.out.println("15.Get custom view of students by year & course");
		
		System.out.println("16.Verify student object identity through map ds");
		
		System.out.println("Type 'q' & hit enter key to exit");
		
	}
	
	public static void printSearchStudentsOptions() {
		
		System.out.println("Please Choose options to Perform Serach based on below conditions ");
		
		System.out.println("1.find students those admitted on a particular year");
		
		System.out.println("2.find students those opted a particular course");
		
		System.out.println("3.find students on the basis of both course & year ");
		
		System.out.println("4.find students on the basis of course, year & name");
		
		System.out.println("5.find a student on the basis of course, year & email");
		
		System.out.println("6.find a student on the basis of course, year & roll no");
		
		System.out.println("7.find a student on the basis of only name");
		
		System.out.println("8.find a student on the basis of only email");
		
		System.out.println("9.find a student on the basis of only roll no");
		
		System.out.println("10.find a student on the basis of year, course & fee status");
		
		System.out.println("11.find a student on the basis of year, course & result status");
		
		System.out.println("12.find a student on the basis of year, course & age");
				
	}
	
	public static void printOptionsForStudCount() {
		
		System.out.println("Please select any of below options at console..");
		
		System.out.println("Get Students Count on following basis:\n1.Admitted on a particular year\n2.Against a particular course\n3.Basis of both course & year");
		
		
				
	}
	
	public static void printOptionsForStudSorting() {
		
		System.out.println("Please select any of below options at console ,\nsort & view based on below fields: ");
		
		System.out.println("1.Age\t2.submitted fees\t3.Attendance\t4.Fee status\t5.Result status");
		

		
	}
	
}
