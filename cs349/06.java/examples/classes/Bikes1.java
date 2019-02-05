// Simple class hierarchy
// Demonstrates abstract classes and containment.

// Bike container class
class Bikes1 {

	// base class
	abstract class Bike {
		int wheels = 0;
		int speed = 0;

		void setWheels(int val) { wheels = val; }
		void setSpeed(int val) { speed = val; }
		void show() {
			System.out.println("wheels  = " + wheels);
		}
	}

	// derived two-wheel bike
	class Bicycle extends Bike {
		Bicycle() { 
			setWheels(2); 
		}
	}

	// derived two-wheel bike
	class Tricycle extends Bike {
		Tricycle() { 
			setWheels(3); 
		}
	}
	
	// container class
	Bikes1() {
		Bicycle b = new Bicycle();
		b.show();

		Tricycle t = new Tricycle();
		t.show();
	}

	// entry point
	public static void main(String[] args) {
		Bikes1 b = new Bikes1();
	}
}