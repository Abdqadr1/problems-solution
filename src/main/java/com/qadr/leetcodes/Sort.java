package com.qadr.leetcodes;

import java.util.*;

//Complete the code
public class Sort
{
    static class Student{
        private Integer id;
        private String fname;
        private Double cgpa;
        public Student(int id, String fname, double cgpa) {
            super();
            this.id = Integer.valueOf(id);
            this.fname = fname;
            this.cgpa = Double.valueOf(cgpa);
        }
        public Integer getId() {
            return id;
        }
        public String getFname() {
            return fname;
        }
        public Double getCgpa() {
            return cgpa;
        }
    }

	private static final Comparator<Student> STUDENT_ORDER = (student1, student2) -> {
        // TODO Auto-generated method stub
        int cgpaComp = student1.getCgpa().compareTo(student2.getCgpa());
        int fNameComp = student1.getFname().compareTo(student2.getFname());
        int idComp = student1.getId().compareTo(student2.getId());
        if(cgpaComp != 0) return cgpaComp > 0 ? -1 : 1;
        if(fNameComp != 0) return fNameComp;
        return idComp > 0 ? -1 : 1;
    };

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());

		List<Student> studentList = new ArrayList<>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();

			Student st = new Student(id, fname, cgpa);
			studentList.add(st);

			testCases--;
		}
		studentList.sort(STUDENT_ORDER);

      	for(Student st: studentList){
			System.out.println(st.getFname());
		}

	}
}



