import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * Create the Student and Priorities classes here.
 */
 class Student{
    private Integer id;
    private String name;
    private Double cgpa;
    public Student(int id, String name, double cgpa) {
        super();
        this.id = Integer.valueOf(id);
        this.name = name;
        this.cgpa = Double.valueOf(cgpa);
    }
    public Integer getID() {
        return Integer.valueOf(id);
    }
    public String getName() {
        return name;
    }
    public Double getCGPA() {
        return Double.valueOf(cgpa);
    }
    @Override
    public String toString() {
        return name + " " + cgpa + " "+ id;
    }
}

class Priorities {
    
    Comparator<Student> compare = (student1, student2) -> {
        int cgpaCom = student1.getCGPA().compareTo(student2.getCGPA());
        int nameCom = student1.getName().compareTo(student2.getName());
        int idCom = student1.getID().compareTo(student2.getID());
        if(cgpaCom != 0) return cgpaCom==1?-1:1;
        if(nameCom != 0) return nameCom;
        return idCom;
    };
    List<Student> getStudents(List<String> events){
        PriorityQueue<Student> queue = new PriorityQueue<>(1000, compare);
        for(String event : events) {
            String[] line = event.split(" ");
            String action = line[0];
            if(action.equalsIgnoreCase("served")) {
                if(!queue.isEmpty()) {
                    queue.remove();
                    // System.out.println(queue);
                }
            } else {
                String name = line[1];
                double cgpa = Double.parseDouble(line[2]);
                int id = Integer.parseInt(line[3]);
                Student student = new Student(id, name, cgpa);
                queue.offer(student);
                // System.out.println(queue);
            }
        }
        List<Student> list = new ArrayList<>();
        while(!queue.isEmpty()) list.add(queue.remove());
        return list;
    }
}



public class JavaPriorityQueue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String n;
		try {
			n = bufferedReader.readLine();
	        BigInteger bigInt = new BigInteger(n);
	        boolean isPrime = bigInt.isProbablePrime(1);
	        System.out.println(isPrime ? "prime": "not prime");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}