//Complete this code or write your own from scratch
import java.util.*;
import java.io.*;

class JavaMap{
	public static void map(String []argh)
	{
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		in.nextLine();
        Map<String, Integer> phoneBook = new HashMap<>();
		for(int i=0;i<n;i++)
		{
			String name=in.nextLine();
			int phone=in.nextInt();
			phoneBook.put(name, phone);
			in.nextLine();
		}
		while(in.hasNext())
		{
			String s=in.nextLine();
			Integer phone = phoneBook.get(s);
			if(null == phone) System.out.println("not found"); 
			else System.out.print(s+"="+phone);
		}
	}
	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String input=sc.next();
		    Stack<Character> stack = new Stack<>();
            //Complete the code
            for (int i=0;i<input.length();i++){
            	if(!stack.isEmpty()) {
            		switch(input.charAt(i)){
	                    case '}':
	                        if(stack.peek() == '{') stack.pop();
	                        break;
	                    case ']':
	                    if(stack.peek() == '[') stack.pop();
	                        break;
	                    case ')':
	                    if(stack.peek() == '(') stack.pop();
	                        break;
	                    default : stack.push(input.charAt(i));
	                }
            	} else {
            		stack.push(input.charAt(i));
            	}
                
            }
            System.out.println(stack.isEmpty());
		}
		
	}
}
