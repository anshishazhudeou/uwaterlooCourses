// Simple class hierarchy
// Contrasts interface and abstract class implementations

public class Animals2 {

    // interface
    interface Pet { 
        String talk();
    }

    // inner classes
    class Cat implements Pet {
        public String talk() { return "Meow!"; }
    }

    class Dog implements Pet {
        public String talk() { return "Woof!"; }
    }

    // container class methods
    Animals2() {
        speak(new Cat());
        speak(new Dog()); 
    }

    void speak(Pet a) {
        System.out.println( a.talk() );
    }

    // static main methods -- entry point
    public static void main(String[] args) {
        new Animals2();
    }
}

