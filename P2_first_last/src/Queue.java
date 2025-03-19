
public class Queue<A>{
	
	//data - must only use stack(s)
	//instance vars
	private Stack<A> stackA;
	private Stack<A> stackB;
	
	public Queue() {
		
		//job of the const is to init the instance vars
		stackA = new Stack<A>();
		stackB = new Stack<A>();
		
	}
	
	//add to the queue
	public void enqueue(A el) {
		
		stackA.push(el);
		
	}
	
	//remove from the queue
	//return null if empty
	
	public A dequeue() {
		
		//if (queue is empty){
		// don't try to access stuff 
		//}
		if (stackA.size() == 0) {
			return null;
		}
		int size = stackA.size();
		for (int i = 0; i < size-1; i++) {
			stackB.push(stackA.pop());
		}
		A temp = stackA.pop();
		for (int i = 0; i < size-1; i++) {
			stackA.push(stackB.pop());
		}
		return temp;
	}
	
	public int size() {
		return stackA.size();
	}
	
	public String toString() {
		String res = "]";
		int size = stackA.size();
		for (int i = 0; i < size; i++) {
			if (i != size-1) {
				res = ", " + stackA.pop() + res;
			}else {
				res = stackA.pop() + res;
			}
		}
		res = "[" + res;
		return res;
	}
	
	//return next element in the queue
	//but do not remove
	public A peek() {
		if (stackA.size() == 0) {
			return null;
		}
		int size = stackA.size();
		for (int i = 0; i < size-1; i++) {
			stackB.push(stackA.pop());
		}
		A temp = stackA.peek();
		for (int i = 0; i < size-1; i++) {
			stackA.push(stackB.pop());
		}
		return temp;
	}
	
	public boolean empty() {
		if (stackA.size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	public A poll() {
		// TODO Auto-generated method stub
		if (size() > 0) {
			return dequeue();
		}else {
			return null;
		}
	}
	
}
