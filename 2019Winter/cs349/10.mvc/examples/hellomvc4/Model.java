import java.util.Observable;

// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model extends Observable {	
	// the data in the model, just a counter
	private int counter;	
	
	Model() {
		setChanged();
	}
	
	public int getCounterValue() {
		return counter;
	}
	
	public void incrementCounter() {
		if (counter < 5) {
			counter++;
			System.out.println("Model: increment counter to " + counter);
			setChanged();
			notifyObservers();
		}
	} 	
}
