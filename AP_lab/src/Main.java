import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;

// Company class to store:-
/* Name of Company
 * Course criteria required for company
 * Application Status(real - time)
 * Displaying company-data function
 *
 */
class Company
{
	private String name;
	private String[] course_criteria;
	private int reqd_students;
	private String app_status;	
	public Company(String name, String[] course_criteria, int reqd_students, String app_status)
	{
		this.name = name;
		this.course_criteria = course_criteria;
		this.reqd_students = reqd_students;
		this.app_status = app_status;
	}
	
	public int getReqd_students() {
		return reqd_students;
	}
	public void setReqd_students(int reqd_students) {
		this.reqd_students = reqd_students;
	}
	public String getApp_status() {
		return app_status;
	}
	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}
	public String getName() {
		return name;
	}
	
	public void seven_display()
	{
		System.out.println(name);
		System.out.println("Course Criteria");
		for (String k : course_criteria)
		{
			System.out.println(k);
		}
		System.out.println("Number of Required Students = " + reqd_students);
		System.out.println("Application Status = " + app_status);
	}
}



//Student class to store:-
/* Name of Student
* CGPA and BRANCH of Student
* Placement Status(real - time) && company placed(if any)
* Displaying student-data function
* Each student mapped with respective company and score in technical round
*/
class Student
{
	private int rollno;
	private float cgpa;
	private String branch;
	private String placement_status;
	private String placement_company;
	public HashMap<String, Integer> map = new HashMap<>();
	public Student()
	{
		
	}
	public Student(int rollno,float cgpa, String branch,String placement_status,String placement_company)
	{
		this.rollno = rollno;
		this.cgpa = cgpa;
		this.branch = branch;
		this.placement_status = placement_status;
		this.placement_company = placement_company;
	}
	
	public void eight_display()
	{
		System.out.println(rollno);
		System.out.println(cgpa);
		System.out.println(branch);
		System.out.println("Placement Status: "+ placement_status);
		if (placement_status != "Not placed")
		{
			System.out.println(placement_company);
		}
	}
	
	public void setPlacement_company(String placement_company) {
		this.placement_company = placement_company;
	}
	public String getBranch() {
		return branch;
	}
	public int getRollno()
	{
		return rollno;
	}
	public float getCgpa() {
		return cgpa;
	}
	public String getPlacement_status() {
		return placement_status;
	}
	public void setPlacement_status(String placement_status) {
		this.placement_status = placement_status;
	}
	
}

// Driver classes for all 9 queries till placement finishes



public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int t_students = sc.nextInt();
		int placed_students = 1;
		ArrayList<Student> all_students = new ArrayList<Student>();
		ArrayList<Company> all_company = new ArrayList<Company>();
		int first_student_roll = 1;
		//////////////////////////////////////////////
		for ( int i = 0 ; i < t_students; i++)
		{
			Student student = new Student(first_student_roll++, sc.nextFloat(), sc.next(),"Not placed","");
			all_students.add(student);
		}
		/////////////////////////////////////////////
		while(placed_students < t_students)
		{
			switch(sc.nextInt())
			{
			
//			1) Add company
//			Inputs: name, course criteria, number of required students
//			The course criteria input will be taken in the following manner: number of courses eligible
//			(say x), the next x strings will be the courses.
//			Display details of the company (Company Name, Course Criteria, Number of required
//			students, Application Status)		
			case 1:
				String name = sc.next();
				System.out.print("Number Of Eligible Courses = ");
				int no_courses = sc.nextInt();
				String[] course_criteria_list = new String[no_courses];
				for ( int x = 0; x < no_courses; x++)
				{
					course_criteria_list[x] = sc.next();
				}
				System.out.print("Number of Required Students = ");
				int no_reqd_students = sc.nextInt();
				System.out.println(name);
				System.out.println("Course Criteria");
				for ( String k : course_criteria_list)
				{
					System.out.println(k);
				}
				System.out.println("Number of Required Students = "+ no_reqd_students);
				String app_status;
				if ( no_reqd_students == 0)
				{
					System.out.println("Application Status = CLOSED");
					app_status = "CLOSED";
				}else{
					System.out.println("Application Status = OPEN");
					app_status = "OPEN";
				}
				
				
				Company company = new Company(name, course_criteria_list,no_reqd_students,app_status);
				System.out.println("Enter scores for the technical round.");
				for (Student k : all_students)
				{
					for (String kk : course_criteria_list)
					{	
						if(kk.equals(k.getBranch()))
						{
							System.out.println("Enter score for Roll no. " + k.getRollno());
							k.map.put(name, sc.nextInt());
						}
					}
				}
				all_company.add(company);
				break;
				
				
//	2) Remove the accounts of the placed students				
			case 2:
				int flag2 = 0;boolean isRemoved = false;
				System.out.println("Accounts removed for");
				ArrayList<Student> not_placed = new ArrayList<>();
				for (Student k : all_students)
				{
					if ( k.getPlacement_status() == "Not placed")
					{
						not_placed.add(all_students.get(flag2));
						
					}else{
						System.out.println(k.getRollno());
						isRemoved = true;
					}
					flag2++;
				}
				all_students = not_placed;
				if ( !isRemoved )
				{
					System.out.println("No Students");
				}
				break;
				
//3) Remove the accounts of companies whose applications are closed			
			case 3:
				int flag3 = 0;boolean isRemovedC = false;
				System.out.println("Accounts removed for");
				ArrayList<Company> all_closed = new ArrayList<>();
				for (Company k : all_company)
				{
					if ( k.getApp_status() != "CLOSED")
					{
						all_closed.add(all_company.get(flag3));
						
					}else{
						System.out.println(k.getName());
						isRemovedC = true;
					}
					flag3++;
				}
				all_company = all_closed;
				if ( !isRemovedC )
				{
					System.out.println("No Company");
				}
				break;
				
//4) Display number of unplaced students
			case 4:
				int count_unplaced = 0;
				for (Student k : all_students)
				{
					if ( k.getPlacement_status() == "Not placed")
					{
						count_unplaced++;
					}
				}
				System.out.println(count_unplaced + " students left.");
				break;
				
//	5) Display names of companies whose applications are open		
			case 5:
				for (Company k : all_company)
				{
					if ( k.getApp_status() == "OPEN")
					{
						System.out.println(k.getName());
					}
				}
				break;

//				6) Select students
//				Input: Company name
//				Output: Roll numbers of selected students
				
			case 6:
				String company_name = sc.next();
				System.out.println("Roll Number of Selected Students");
				PriorityQueue<Integer> pq = new PriorityQueue<>();
				for (Company k : all_company)
				{
					if (k.getName().equals(company_name) && k.getReqd_students() > 0)
					{
						if ( k.getApp_status().equals("CLOSED"))
						{
							System.out.println("Application Status Closed, can't place any Student.");
							break;
						}
						ArrayList<Student> placing_students = new ArrayList<>();
						for (Student pika : all_students)
						{
							if (pika.map.containsKey(company_name))
							{
								placing_students.add(pika);
								pq.add(100 - pika.map.get(company_name));
							}
						}
						while(!pq.isEmpty())
						{
							int score = 100 - pq.poll();
							Student jka = new Student();
							if ( k.getReqd_students() > 0)
							{
								float temp = -1;
								for (Student kp : placing_students )
								{
									//System.out.println(kp.getCgpa());
									if (kp.map.get(company_name) == score)
									{
										if ( temp < kp.getCgpa())
										{
											temp = kp.getCgpa();
											jka = kp;
										}
									}
								}
							}
							if (jka != null && k.getReqd_students() > 0)
							{
								System.out.println(jka.getRollno());
								placed_students++;
								k.setReqd_students(k.getReqd_students() - 1);
								for(Student kpk : all_students)
								{
									if (jka.equals(kpk))
									{
										kpk.setPlacement_status("placed");
										kpk.setPlacement_company(company_name);
									}
								}
							}
							if (k.getReqd_students() == 0 )
							{
								k.setApp_status("CLOSED");
								//System.out.println("Placement for this company terminated.");
							}
						}
					}
				}
				break;
	
//7) Display details of the company				
			case 7:
				String company_name_to_be_displayed = sc.next();
				for (Company k : all_company)
				{
					if (k.getName().equals(company_name_to_be_displayed));
					{
						k.seven_display();
					}
				}
				break;
	
				
//8) Display details of the student
			case 8:
				int roll_no = sc.nextInt();
				for (Student k : all_students)
				{
					if (k.getRollno() == roll_no)
					{
						k.eight_display();
					}	
				}
				break;
	
				
//9) Display names of companies for which the student has applied and their scores in
//				technical round of that company				
			case 9:
				int roll_no_for_getting_scores = sc.nextInt();
				boolean any_left = false;
				for (Student k : all_students)
				{
					if ( k.getRollno() == roll_no_for_getting_scores)
					{
				        for (HashMap.Entry<String,Integer> entry : k.map.entrySet())  
				        {
				            System.out.println(entry.getKey() + " " + entry.getValue());
				        }
				        any_left = true;
					}
				}
				if ( !any_left )
				{
					System.out.println("No student with the given roll number has an account");
				}
				break;
			}
		}
		sc.close();
	}
	
	
}
