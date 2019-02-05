# Java event demos
These demos show all of the possible ways to handle events in Java, as discussed in class. The _preferred_ mechanism uses listener adapters.

1. `EventLoop.java` Binding events by tapping into the low-level java event queue	
2. `InheritanceEvents.java` Binding events by extending base class with  event callback methods
3. `InterfaceEvents.java` Binding events by implementing event listener interface
4. `ListenerEvents.java` Binding events by creating a child object which implements the listener interface
5. `AdapterEvents.java` Binding events by creating a child listener adapter object