# 1. Abstract Classes

Abstract classes are classes that cannot be instantiated and are meant to be subclassed. They can contain both abstract methods (methods without implementation) and concrete methods (methods with implementation).

An abstract superclass can define a generalized form of behavior without providing an implementation for certain methods. Such methods declare what subclasses are expected to provide, but do not contain a method body. **These are known as abstract methods.** 

When a method is abstract, a concrete subclass must provide its implementation. A subclass that does not implement all inherited abstract methods must itself be declared `abstract`.

* This is done by **overriding**. A concrete child class must implement all inherited abstract methods that it is required to implement. These are methods declared in the superclass but without an implementation. 

    If a subclass does not implement all inherited abstract methods, it must also be declared `abstract`. A concrete subclass cannot be instantiated until all applicable abstract methods have implementations.

**NOTE:** Any class that declares or inherits an abstract method must be abstract (subject to the language rules for whether an inherited method has been overridden). A normal class cannot contain an abstract method declaration.

# 2. Abstract Methods
An **abstract method** is a method that has only a declaration (signature) and **no body / implementation**. It ends with a semicolon `;`.


### Key Rules of Abstract Classes:

1. **Cannot be instantiated**: You cannot create objects using `new Parent()` ❌.
>- Reason: Abstract classes are incomplete by design. They are meant to be subclassed, and their abstract methods must be implemented in concrete subclasses before they can be instantiated. 
>- Java prevents this at compile time. An abstract class may contain abstract methods, so Java does not allow a direct instance of that class to be created.

2. **Constructors allowed**: Even though it cannot be instantiated directly, an abstract class **can have constructors**. They are called when a subclass object is instantiated via `super()`.
>- Reason: Constructors in abstract classes are used to initialize common properties or perform setup tasks that are shared among all subclasses. 
>- When a subclass is instantiated, it can call the constructor of the abstract superclass to ensure that the inherited fields are properly initialized.

3. **Can have normal (concrete) and `static` methods**: An abstract class does not need to be 100% abstract. It can have normal instance methods and static methods with full implementations.
>- Reason: Abstract classes can provide default behavior through concrete methods, allowing subclasses to inherit and use these methods without needing to implement them.

4. **Mandatory override**: Any concrete subclass extending an abstract class **must override all** of its abstract methods; otherwise, the subclass must also be declared `abstract`.
>- Reason: This ensures that all abstract methods are implemented, providing complete functionality in the subclass. If a subclass does not implement all abstract methods, it remains incomplete and must also be declared abstract.

5. **Cannot be `final`**: An abstract class MUST be inherited, whereas a `final` class CANNOT be inherited. Therefore, `final abstract` is illegal.
>- Reason: The purpose of an abstract class is to serve as a base for other classes. Declaring it as `final` would contradict this purpose, as it would prevent any subclassing, making the abstract class unusable.

6. **Abstract methods cannot be `static`**: A static method belongs to the class rather than an object and is not overridden. An abstract method requires a concrete implementation through overriding, so the two modifiers cannot be combined.
>- Reason: Abstract methods are meant to be overridden in subclasses to provide specific implementations. Static methods belong to the class itself and cannot be overridden, which conflicts with the purpose of abstract methods.

7. **Abstract methods cannot be `private`**: Private methods are not inherited or overridden by subclasses, so a subclass cannot provide the required implementation of a private abstract method.
>- Reason: Abstract methods are intended to be implemented by subclasses, which requires them to be accessible. Declaring an abstract method as private would prevent subclasses from accessing and overriding it, defeating the purpose of abstraction.

---

### Code Implementation (`abstractDemo`)

#### File 1: `Parent.java` (Abstract Superclass)
```java
package Lecture5.abstractDemo;

public abstract class Parent {
    int age;
    final int VALUE;

    // Constructor: initialized via subclasses through super(age)
    public Parent(int age) {
        this.age = age;
        VALUE = 32456789;
    }

    // Static method allowed in abstract class
    static void hello() {
        System.out.println("hey");
    }

    // Normal method with implementation
    void normal() {
        System.out.println("this is a normal method");
    }

    // Abstract methods: Subclasses MUST provide implementations
    abstract void career();
    abstract void partner();
}
```

#### File 2: `Son.java` (Subclass 1)
```java
package Lecture5.abstractDemo;

public class Son extends Parent {
    public Son(int age) {
        super(age);
    }

    @Override
    void career() {
        System.out.println("I am going to be a doctor");
    }

    @Override
    void partner() {
        System.out.println("I love Pepper Potts");
    }
}
```

#### File 3: `Daughter.java` (Subclass 2)
```java
package Lecture5.abstractDemo;

public class Daughter extends Parent {
    public Daughter(int age) {
        super(age);
    }

    @Override
    void career() {
        System.out.println("I am going to be a coder");
    }

    @Override
    void partner() {
        System.out.println("I love Iron Man");
    }
}
```

#### File 4: `Main.java` (Execution)
```java
package Lecture5.abstractDemo;

public class Main {
    public static void main(String[] args) {
        Son son = new Son(30);
        son.career();  // Output: I am going to be a doctor
        son.partner(); // Output: I love Pepper Potts

        Daughter daughter = new Daughter(28);
        daughter.career();  // Output: I am going to be a coder
        daughter.partner(); // Output: I love Iron Man

        // ❌ COMPILE ERROR: Cannot instantiate abstract class directly
        // Parent mom = new Parent(45);

        // ✅ Static methods called via class name:
        Parent.hello(); // Output: hey

        // ✅ Upcasting (Parent reference holding Child object):
        Parent p = new Son(25);
        p.career(); // Output: I am going to be a doctor (Dynamic Method Dispatch)
    }
}
```

---

## 2. Interfaces (`interfaces`)

### What is an Interface?
An **interface** in Java defines a contract that classes can implement. It can declare abstract methods as well as constants, and modern Java interfaces can also contain `default`, `static`, and `private` methods with implementations. It primarily specifies **what** behavior a type exposes, while allowing implementations to determine **how** that behavior is carried out.

### Why do we need Interfaces?
1. **Multiple Type Inheritance**: Java does not allow a class to extend multiple classes, but a class can implement **multiple interfaces**. This allows a class to inherit multiple interface contracts.
2. **Abstraction and Loose Coupling**: Interfaces can help separate a contract from its implementation and can make designs more flexible. They do not automatically guarantee "100% loose coupling".

### Key Rules of Interfaces:
- All variables in an interface are implicitly **`public static final`** (constants).
>- Reason: Interfaces are meant to define a contract for behavior, not state. By making variables `static` and `final`, they become constants that cannot be modified, ensuring that the interface remains a pure specification of behavior without maintaining any mutable state.

- Methods without `default`, `static`, or `private` are implicitly **`public abstract`**. Java 8 introduced `default` and `static` interface methods, and Java 9 introduced `private` interface methods.

- A class uses the **`implements`** keyword to implement an interface.
>- Reason: The `implements` keyword clearly indicates that a class is providing concrete implementations for the abstract methods defined in the interface, establishing a contract between the interface and the implementing class.

- A class must provide **`public`** implementations for all interface methods.
>- Reason: Interface methods are implicitly public, and the implementing class must maintain this visibility to fulfill the contract defined by the interface. If the methods were not public, it would violate the interface's specification and lead to access issues.
- The variables (fields) declared in an interface are implicitly **`public static final`**, so they are constants and cannot be reassigned. 
>- Reason: Interfaces are meant to define a contract for behavior, not state. By making variables `static` and `final`, they become constants that cannot be modified, ensuring that the interface remains a pure specification of behavior without maintaining any mutable state.
---

### Code Implementation (`interfaces`)

#### The Component Interfaces:
```java
package Lecture5.interfaces;

public interface Engine {
    static final int PRICE = 78000;
    void start();
    void stop();
    void acc();
}

public interface Brake {
    void brake();
}

public interface Media {
    void start();
    void stop();
}
```

#### File 1: Direct Implementation (`Car.java`)
```java
package Lecture5.interfaces;

// A class can implement multiple interfaces
public class Car implements Engine, Brake, Media {

    @Override
    public void brake() {
        System.out.println("I brake like a normal Car");
    }

    @Override
    public void start() {
        System.out.println("I start engine like a normal Car");
    }

    @Override
    public void stop() {
        System.out.println("I stop engine like a normal Car");
    }

    @Override
    public void acc() {
        System.out.println("I accelerate like a normal Car");
    }
}
```

---

### Design Limitation of Direct Interface Implementation:
In `Car.java`, both `Engine` and `Media` declare `start()` and `stop()`. This is **not a compile-time ambiguity** in this example: one `Car.start()` implementation can satisfy both interface contracts because the methods have the same signature. However, the design does not distinguish whether `start()` means starting the engine or starting the media player, so the API can be unclear. Composition gives the two responsibilities separate method names such as `start()` and `startMusic()`.

### Better Design Pattern: Composition over Inheritance (`NiceCar.java`)
Instead of putting all component responsibilities directly on `Car`, we can inject modular engine and media components using **Composition**:

#### Supporting Implementations:
```java
// PowerEngine.java
public class PowerEngine implements Engine {
    public void start() { System.out.println("Power engine start"); }
    public void stop() { System.out.println("Power engine stop"); }
    public void acc() { System.out.println("Power engine accelerate"); }
}

// ElectricEngine.java
public class ElectricEngine implements Engine {
    public void start() { System.out.println("Electric engine start"); }
    public void stop() { System.out.println("Electric engine stop"); }
    public void acc() { System.out.println("Electric engine accelerate"); }
}

// CDPlayer.java
public class CDPlayer implements Media {
    public void start() { System.out.println("Music start"); }
    public void stop() { System.out.println("Music stop"); }
}
```

#### Assembled Class: `NiceCar.java`
```java
package Lecture5.interfaces;

public class NiceCar {
    private Engine engine;
    private Media player = new CDPlayer();

    public NiceCar() {
        engine = new PowerEngine();
    }

    public NiceCar(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }

    public void stop() {
        engine.stop();
    }

    public void startMusic() {
        player.start();
    }

    public void stopMusic() {
        player.stop();
    }

    public void upgradeEngine(Engine newEngine) {
        this.engine = newEngine;
    }
}
```

#### Execution:
```java
public class Main {
    public static void main(String[] args) {
        NiceCar car = new NiceCar();

        car.start();      // Output: Power engine start
        car.startMusic(); // Output: Music start

        // Upgrade engine dynamically at runtime:
        car.upgradeEngine(new ElectricEngine());
        car.start();      // Output: Electric engine start
    }
}
```

---

## 3. Advanced Interface Features (Java 8+)

### 1. `default` Methods in Interfaces
Java 8 introduced `default` methods with full bodies to allow adding new methods to interfaces without breaking existing implementing classes:
```java
public interface A {
    default void fun() {
        System.out.println("I am in A default fun");
    }
}
```

### 2. `static` Methods in Interfaces
Interfaces can contain static methods that must have a method body. They are called directly via the interface name and cannot be overridden:
```java
public interface A {
    static void greeting() {
        System.out.println("Hey I am static in Interface");
    }
}
// Called as: A.greeting();
```

### 3. Nested Interfaces
An interface can be declared inside another interface or class:
```java
public class A {
    // Nested interface inside class A
    public interface NestedInterface {
        boolean isOdd(int num);
    }
}

class B implements A.NestedInterface {
    @Override
    public boolean isOdd(int num) {
        return (num & 1) == 1;
    }
}
```

### 4. Interface Extending Interface
An interface can inherit from another interface using the **`extends`** keyword (not `implements`):
```java
public interface A {
    void fun();
}

public interface B extends A {
    void greet();
}
// Any class implementing B must implement BOTH fun() and greet().
```

---

## 4. Comparison: Abstract Class vs. Interface

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Speed** | No general language-level speed guarantee | No general language-level speed guarantee |
| **Multiple Inheritance** | ❌ No (Single class inheritance only) | ✅ Yes (A class can implement multiple interfaces) |
| **Variables** | Can have instance variables, `static`, `final`, etc. | Only `public static final` (constants) |
| **Constructors** | ✅ Can have constructors (called via `super`) | ❌ Cannot have constructors |
| **Methods** | Can have abstract, concrete, `final`, and `static` methods | Abstract methods, `default` & `static` methods (Java 8+) |
| **Access Modifiers** | Can have `private`, `protected`, `public` | Interface fields are `public`; ordinary interface methods are `public` by default, while `private` interface methods are also allowed |
| **Keywords** | `abstract class`, `extends` | `interface`, `implements`, `extends` |

---

## 5. Annotations

Annotations are metadata associated with program declarations or other program elements. They are prefixed with `@` and can be used by the compiler, tools, frameworks, or runtime reflection depending on the annotation's retention and target.

### Common Standard Annotations:
1. **`@Override`**: Ensures a method is actually overriding a method from a superclass/interface; generates a compile error if the signature doesn't match.
2. **`@Deprecated`**: Marks a method or class as obsolete, warning developers not to use it.
3. **`@SuppressWarnings("...")`**: Instructs the compiler to suppress specific warnings (e.g. `@SuppressWarnings("unchecked")`).
4. **`@FunctionalInterface`**: Tells the compiler that the interface is intended to be a **functional interface**, meaning it has exactly one abstract method (after considering inherited methods). The compiler reports an error if that requirement is violated. Functional interfaces can be used as targets for lambda expressions and method references.
