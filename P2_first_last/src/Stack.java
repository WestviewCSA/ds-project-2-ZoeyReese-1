import java.util.ArrayList;

public class Stack<J> {

	//Data
	private ArrayList<J> data;
	
	//add a constructor - default constructor
	
	public Stack() {
		
		//initialize the instance vars
		data = new ArrayList<J>();
		
	}
	
	//add the element to the stack
	public void push(J el) {
		data.add(el); //end of the list is the "top"
	}
	
	//remove and return the top of the Stack
	//return null if empty
	public J pop() {
		J val = data.get(data.size()-1);
		data.remove(data.size()-1);
		return val;
	}
	
	//return the size of the stack
	public int size() {
		return data.size();
	}
	
	//use the arraylist toString to return a String
	//representation of the data
	public String toString() {
		return data.toString();
	}
	
	public J peek() {
		return data.get(data.size()-1);
	}
	
	public boolean empty() {
		if (data.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
