
public class Counter {

	private static  int count;
	
	public Counter(){
		count++;
	}
	
	public static void printCount(){
		System.out.println("Number of instance so far os: " + count);
	}
	
	
	public static void main(String[] args) {

		Counter anInstance = new Counter();
		anInstance.printCount();
		Counter.printCount();
		Counter anotherInstance = new Counter();
		anotherInstance.printCount();
		anotherInstance.printCount();
		anotherInstance.printCount();
		anotherInstance.printCount();
	}

}
