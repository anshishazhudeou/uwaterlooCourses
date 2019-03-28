// Simple class hierarchy
// Demonstrates abstract classes, containment

public class Animals1 {

    // base class
    abstract class Pet {
        abstract String talk();
    }

    // derived classes
    class Cat extends Pet {
        String talk() { return "Meow!"; }
    }

    class Dog extends Pet {
        String talk() { return "Woof!"; }
    }

    // container class methods
    Animals1() {
        speak(new Cat());
        speak(new Dog()); 
    }

    void speak(Pet a) {
        System.out.println( a.talk() );
    }

    // static main methods -- entry point
    public static void main(String[] args) {
        new Animals1();
    }
}

