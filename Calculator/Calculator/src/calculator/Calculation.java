package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculation {
	private int value1, value2, Total;
	private String temp, operation;
	private Queue<String> queueValue = new LinkedList<>();
	
	
	public void addToQueueValue(String inputValue) {
		this.queueValue.add(inputValue);
	}
	
	private String decreaseQueue() {
		return this.temp = queueValue.remove();
	}
	
	public void clearQueue() {
		queueValue.clear();
	}
	
	public void calculatingValue() {
		
		this.value1 = Integer.parseInt(decreaseQueue());
		this.operation = decreaseQueue();
		this.value2 = Integer.parseInt(decreaseQueue());
		operation(value1, operation, value2);
		
		while(queueValue.size() > 1) {
			this.operation = decreaseQueue();
			this.value2 = Integer.parseInt(decreaseQueue());
			operation(this.Total, this.operation, this.value2);
		}
		
		System.out.println("total : " + getTotal());
		
	}
	
	private void operation(int val1, String opr, int val2) {		
		switch(opr) {
			case "+" : { this.Total = val1 + val2; break;}
			case "-" : { this.Total = val1 - val2; break;}
			case "*" : { this.Total = val1 * val2; break;}
			case "/" : { this.Total = val1 / val2; break;}
			default : System.err.println("cant defined");
		}
	}
	
	public String getTotal() {
		String STotal = Integer.toString(this.Total);
		return STotal;
	}
	
}
