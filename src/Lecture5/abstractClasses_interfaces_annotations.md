# 1. Abstract Classes

Abstract classes are classes that cannot be instantiated and are meant to be subclassed. They can contain both abstract methods (methods without implementation) and concrete methods (methods with implementation).

The Parent class is only going to give a generalized form of it, not the body of the method. Such a class will determine the nature of the methods the subclass/child class must implement. The parent class is just giving the definition of the method, not the body of the method. **These are known as abstract methods.** 

Now when a function does not have a body, it totally depends on the child class to implement it. The child class is going to give the body of that function.

* This is done by **overriding**. We have to make sure the child class overrides all the methods. These are the methods that are defined in the parent class but not implemented. 

    If we do not override all the methods, then the child class will also become an abstract class, and inorder to used them the child class must override it.

**NOTE:** Any class that has at least one abstract method must also be declared as an abstract class.

# 2. Abstract Static Methods
An **abstract method** is a method that has only a declaration (signature) and **no body / implementation**. It ends with a semicolon `;`.


### Key Rules of Abstract Classes:

1. **Cannot be instantiated**: You cannot create objects using `new Parent()` ❌.
2. **Constructors allowed**: Even though it cannot be instantiated directly, an abstract class **can have constructors**. They are called when a subclass object is instantiated via `super()`.
3. **Can have normal (concrete) and `static` methods**: An abstract class does not need to be 100% abstract. It can have normal instance methods and static methods with full implementations.
4. **Mandatory override**: Any concrete subclass extending an abstract class **must override all** of its abstract methods; otherwise, the subclass must also be declared `abstract`.
5. **Cannot be `final`**: An abstract class MUST be inherited, whereas a `final` class CANNOT be inherited. Therefore, `final abstract` is illegal.
6. **Abstract methods cannot be `static`**: Static methods cannot be overridden, but abstract methods require overriding.
7. **Abstract methods cannot be `private`**: Private methods cannot be inherited or overridden.

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
An **Interface** in Java is a blueprint of a class containing abstract methods and static constants. It specifies **what** a class must do, but not **how** it does it.

### Why do we need Interfaces?
1. **Multiple Inheritance**: Java does not support multiple inheritance with classes (to avoid the "Diamond Problem"), but a class can implement **multiple interfaces**.
2. **Total Abstraction**: Achieves 100% loose coupling.

### Key Rules of Interfaces:
- All variables in an interface are implicitly **`public static final`** (constants).
- All methods are implicitly **`public abstract`** (prior to Java 8).
- A class uses the **`implements`** keyword to implement an interface.
- A class must provide **`public`** implementations for all interface methods.
- The variables are **`static`** and **`final`** by default in interfaces, so they cannot be changed. They are constants. 
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

// Multiple inheritance achieved through interfaces
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

### The Flaw in Direct Interface Implementation:
In `Car.java`, both `Engine` and `Media` have `start()` and `stop()` methods. When we call `car.start()`, it is ambiguous whether it starts the car engine or starts the media player.

### Better Design Pattern: Composition over Inheritance (`NiceCar.java`)
Instead of having `Car` implement everything directly, we inject modular engine and media components using **Composition**:

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
| **Speed** | Slightly faster | Slower due to search/lookup in implementation table |
| **Multiple Inheritance** | ❌ No (Single class inheritance only) | ✅ Yes (A class can implement multiple interfaces) |
| **Variables** | Can have instance variables, `static`, `final`, etc. | Only `public static final` (constants) |
| **Constructors** | ✅ Can have constructors (called via `super`) | ❌ Cannot have constructors |
| **Methods** | Can have abstract, concrete, `final`, and `static` methods | Abstract methods, `default` & `static` methods (Java 8+) |
| **Access Modifiers** | Can have `private`, `protected`, `public` | Everything is implicitly `public` |
| **Keywords** | `abstract class`, `extends` | `interface`, `implements`, `extends` |

---

## 5. Annotations

Annotations are metadata tags prefixed with `@` that provide data about a program without altering its bytecode logic directly.

### Common Standard Annotations:
1. **`@Override`**: Ensures a method is actually overriding a method from a superclass/interface; generates a compile error if the signature doesn't match.
2. **`@Deprecated`**: Marks a method or class as obsolete, warning developers not to use it.
3. **`@SuppressWarnings("...")`**: Instructs the compiler to suppress specific warnings (e.g. `@SuppressWarnings("unchecked")`).
4. **`@FunctionalInterface`**: Ensures an interface has **exactly one** abstract method (used with Lambda expressions).
