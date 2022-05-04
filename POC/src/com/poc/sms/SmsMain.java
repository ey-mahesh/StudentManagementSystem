package com.poc.sms;

import java.time.ZoneId;
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

public class SmsMain {
	
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~STUDENT MANAGEMENT SYSTEM~~~~~~~~~~~~~~~~~~~~~");
						
		try {
			
			StudentOperations.printInstructions();
								
			String userStatus = null;
			
			while(true) { 
				System.out.print("Your Choice: ");
				userStatus = sc.next();
				
				switch(userStatus) {
				
					case "1":
						StudentOperations.displayStudents();
						while(true) {
							System.out.println("Do you want to view student again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.displayStudents();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}
							
						}
						break;
						
					case "2":
						StudentOperations.insertStudent();
						while(true) {
							System.out.println("Do you want to add student again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.insertStudent();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}
							
						}
						break;
					case "3":
						StudentOperations.updateStudent();
						while(true) {
							System.out.println("Do you want to update/edit any more student again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.updateStudent();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "4":
						StudentOperations.deleteStudent();
						while(true) {
							System.out.println("Do you want to delete student again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.deleteStudent();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "5":
						StudentOperations.getStudentById();
						while(true) {
							System.out.println("Do you want to fetch student again by Id ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.getStudentById();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "6":
						StudentOperations.searchStudents();
						while(true) {
							System.out.println("Do you want to search students again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.searchStudents();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "7":
						StudentOperations.viewPendingFeesOfStud();
						while(true) {
							System.out.println("Do you want to view pending fees info of any other student ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.viewPendingFeesOfStud();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "8":
						StudentOperations.viewTotalClassBunksOfStud();
						while(true) {
							System.out.println("Do you want to view total class bunked in days by any other student ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.viewTotalClassBunksOfStud();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;

					case "9":
						StudentOperations.getStudentsCount();
						while(true) {
							System.out.println("Do you want to view student counts on the basis of admission year and course again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.getStudentsCount();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "10":
						StudentOperations.getStudentsAsFeeDefaultersWithMaxSubFees();
						while(true) {
							System.out.println("Do you want to view students again those are having pending fee status but maximum amount deposited ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.getStudentsAsFeeDefaultersWithMaxSubFees();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "11":
						StudentOperations.getStudentsAsFeeDefaultersWithMinSubFees();
						while(true) {
							System.out.println("Do you want to view students again those are having pending fee status but minimum amount deposited ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.getStudentsAsFeeDefaultersWithMinSubFees();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "12":
						StudentOperations.sortStudentsByAttendanceUsingMapValSorting();
						while(true) {
							System.out.println("Do you want to sort and view students again on the basis of attendacne ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.sortStudentsByAttendanceUsingMapValSorting();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "13":
						StudentOperations.sortStudentsBySubFeesUsingMapKeySorting();
						while(true) {
							System.out.println("Do you want to sort and view students again on the basis of submitted fees ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.sortStudentsBySubFeesUsingMapKeySorting();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "14":
						StudentOperations.sortAndViewStudentsByImpFactors();
						while(true) {
							System.out.println("Do you want to sort and view students again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.sortAndViewStudentsByImpFactors();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "15":
						StudentOperations.custViewOfStudentsByYearAndCourse();
						while(true) {
							System.out.println("Do you want to get custom view of students by year & course again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.custViewOfStudentsByYearAndCourse();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;
						
					case "16":
						StudentOperations.verifyStudentObjIdentityThroughMap();
						while(true) {
							System.out.println("Do you want to verify student object identity again ? Y OR N");
							String oprStatus = sc.next();
							if(oprStatus.equalsIgnoreCase("Y")) {
								StudentOperations.verifyStudentObjIdentityThroughMap();
							}else if(oprStatus.equalsIgnoreCase("N")) {
								break;
							}else {
								System.out.println("Please provide a valid input..");
							}							
						}
						break;

					case "q":
						System.out.println("~~~~~~~~~~~~Thank You...Exited from the SMS App~~~~~~~~~~~~~~~~~~~");
						break;
						
					default:
						System.out.println("No matched operations found");
						break;
					
					}
				
				if(userStatus.equalsIgnoreCase("q")) {
					break;
				}else {
					StudentOperations.printInstructions();
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			sc.close();
		}
		
	}
	
	
	
	
}
