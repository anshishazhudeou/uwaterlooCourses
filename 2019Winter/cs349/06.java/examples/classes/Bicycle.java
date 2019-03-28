
class Bicycle { 
    String owner = null;
    int speed = 0;
    int gear = 1;

    // constructor
    Bicycle() { }
    Bicycle(String name) { owner = name; }

    // methods
    void changeSpeed(int newValue) { speed = newValue; }
    void changeGear(int newValue) { gear = newValue; }
    int  getSpeed() { return speed; }
    int  getGear() { return gear; }
    
    // static entry point - main method
    public static void main(String[] args) {

        Bicycle adultBike = new Bicycle("Jeff");
        adultBike.changeSpeed(20);
        System.out.println("speed=" + adultBike.getSpeed());

        Bicycle kidsBike = new Bicycle("Austin");
        kidsBike.changeSpeed(25);
        System.out.println("speed=" + kidsBike.getSpeed());
    }    
}
