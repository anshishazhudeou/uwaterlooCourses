// Simple class hierarchy + interface
// Demonstrates abstract classes, containment, interfaces.

// Bike container class
class Bikes2 {

	// base class
	abstract class Bike {
		int wheels = 0;
		int speed = 0;

		void setWheels(int val) { wheels = val; }
		void setSpeed(int val) { speed = val; }
		void show() { 
			System.out.println("wheels  = " + wheels);
			System.out.println("speed  = " + speed);
		}
	}

	// interface for ANYTHING driveable
	// could be applied to car, scooter etc.
	interface Driveable {
		void accelerate();
		void brake();
	}

	// derived two-wheel bike
	class Bicycle extends Bike implements Driveable {

		Bicycle() { setWheels(2); }

		// interface methods
		// need to make public to match access level for interface (above)
		public void accelerate() { setSpeed(speed += 5); }
		public void brake() { setSpeed(speed -= 5); }
	}

	// derived two-wheel bike
	class Tricycle extends Bike implements Driveable {
		Tricycle() { setWheels(3); }

		// interface methods
		// need to make public to match access level for interface (above)
		public void accelerate() { setSpeed(speed += 1); }
		public void brake() { setSpeed(speed -= 1); }		
	}
	
	// container class
	Bikes2() {
		Bicycle b = new Bicycle();
		b.accelerate();
		b.show();

		Tricycle t = new Tricycle();
		t.accelerate();
		t.show();
	}

	// entry point
	public static void main(String[] args) {
		Bikes2 b = new Bikes2();
	}
}