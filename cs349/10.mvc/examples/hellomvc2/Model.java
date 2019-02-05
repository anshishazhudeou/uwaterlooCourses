import java.util.ArrayList;

// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model {	

	// the data in the model, just a counter
	private int counter;	
	
	// all views of this model
	private ArrayList<IView> views = new ArrayList<IView>();
	
	// set the view observer
	public void addView(IView view) {
		views.add(view);
		view.updateView();
	}
	
	public int getCounterValue() {
		return counter;
	}
	
	public void incrementCounter() {
		if (counter < 5) {
			counter++;
			System.out.println("Model: increment counter to " + counter);
			notifyObservers();
		}
	} 	
	
	// notify the IView observer
	private void notifyObservers() {
		for (IView view : this.views) {
			System.out.println("Model: notify View");
			view.updateView();
		}
	}
}
