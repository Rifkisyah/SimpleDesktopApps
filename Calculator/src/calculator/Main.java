package calculator;

public class Main {

	public static void main(String[] args) {
		Calculation calc = new Calculation();

		calc.addToQueueValue("5");
		calc.addToQueueValue("*");
		calc.addToQueueValue("5");
		calc.addToQueueValue("+");
		calc.addToQueueValue("5");
		calc.addToQueueValue("-");
		calc.addToQueueValue("5");
		
		calc.calculatingValue();
	}

}