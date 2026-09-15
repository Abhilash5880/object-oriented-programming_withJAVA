#  Inheritance, Polymorphism, Encapsulation, Abstraction

# 1. Inheritance
- Inheritance is a mechanism in which one class acquires the properties (fields) and behaviors (methods) of another class. 
    
    The class that inherits the properties of another is called the subclass (or derived class, child class), and the class whose properties are inherited is called the superclass (or base class, parent class).

* Example: 
```java
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}   

class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks.");
    }
}   

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Inherited method from Animal class
        dog.bark(); // Method from Dog class
    }
}
```
### Output:
``` 
This animal eats food.
The dog barks.
```
Here, the `Dog` class inherits the `eat()` method from the `Animal` class.
`Dog` is the subclass/child class, and `Animal` is the superclass/parent class.

## `extends` Keyword
- In Java, the `extends` keyword is used to indicate that a class is inheriting from another class. The subclass can access the public and protected members of the superclass.

### Although a subclass includes the members of it's superclass, it will not be able to access the private members

* Also, a child can access members of it's parent but if the object directly created from the parent class, it will not be able to access the members of it's child class.

### `super` Keyword
- The `super` keyword in Java is a reference variable that is used to refer to the immediate parent class object. It can be used to access methods and constructors of the parent class.

### Example: `extends` and `super` Keywords (`Box.java` & `BoxWeight.java`)

**Inheritance** allows a child class (subclass) to inherit fields and methods from a parent class (superclass) using the **`extends`** keyword, while the **`super`** keyword is used to refer to or invoke the parent class's members and constructors.

---

#### 1. Core Concepts

##### The `extends` Keyword:
- Establishes an **IS-A** relationship (e.g., `BoxWeight` **is a** `Box`).
- Syntax: `public class BoxWeight extends Box`
- The child class automatically inherits all accessible (non-`private`) fields and methods of the parent class.
- In Java, a class can only extend **one** parent class (single inheritance).

##### The `super` Keyword:
1. **To invoke the superclass constructor**:
   - Syntax: `super(l, h, w);`
   - It **must be the very first statement** inside the child class constructor.
   - It delegates the initialization of parent class fields (`l`, `h`, `w`) to the parent constructor.
   - If `super(...)` is not explicitly called, the Java compiler automatically inserts a call to the default parent constructor `super()`.
2. **To access superclass variables or methods**:
   - Used when a child class defines a variable or method with the exact same name as the parent class (shadowing/overriding).
   - `this.weight` refers to the child class variable.
   - `super.weight` refers to the parent class variable.

---

#### 2. Code Implementation

##### File 1: Parent Class (`Box.java`)
```java
package Lecture3.inheritance;

public class Box {
    double l;
    double h;
    double w;

    // Default Constructor
    Box() {
        this.h = -1;
        this.l = -1;
        this.w = -1;
    }

    // Cube Constructor
    Box(double side) {
        this.w = side;
        this.l = side;
        this.h = side;
    }

    // Parameterized Constructor
    Box(double l, double h, double w) {
        this.l = l;
        this.h = h;
        this.w = w;
    }

    // Copy Constructor
    Box(Box old) {
        this.h = old.h;
        this.l = old.l;
        this.w = old.w;
    }

    public void information() {
        System.out.println("Running the box");
    }
}
```

##### File 2: Child Class (`BoxWeight.java`)
```java
package Lecture3.inheritance;

// 'extends' inherits all non-private members of Box
public class BoxWeight extends Box {
    double weight;

    // Default Constructor
    public BoxWeight() {
        this.weight = -1;
        // Compiler automatically calls super(); here
    }

    // Parameterized Constructor
    public BoxWeight(double l, double h, double w, double weight) {
        // 'super' calls the parent constructor Box(l, h, w)
        // MUST be the first statement in this constructor!
        super(l, h, w); 
        this.weight = weight;
    }

    // Constructor with cube side and weight
    public BoxWeight(double side, double weight) {
        super(side); // calls Box(side)
        this.weight = weight;
    }

    // Copy Constructor
    public BoxWeight(BoxWeight other) {
        super(other); // passes BoxWeight instance to Box(Box old)
        this.weight = other.weight;
    }
}
```

##### File 3: Main Execution (`Main.java`)
```java
package Lecture3.inheritance;

public class Main {
    public static void main(String[] args) {
        // 1. Normal Child Object
        BoxWeight box1 = new BoxWeight();
        System.out.println(box1.h + " " + box1.weight); // -1.0 -1.0

        // 2. Child Object with Parameterized Constructor
        BoxWeight box2 = new BoxWeight(2, 3, 4, 8);
        System.out.println(box2.l + " " + box2.h + " " + box2.w + " " + box2.weight); // 2.0 3.0 4.0 8.0

        // 3. Parent Reference pointing to Child Object
        Box box3 = new BoxWeight(2, 3, 4, 8);
        System.out.println(box3.l + " " + box3.h + " " + box3.w); // 2.0 3.0 4.0
        // System.out.println(box3.weight); ❌ COMPILE ERROR!
    }
}
```

---

#### 3. Crucial Interview Concept: Reference Type vs Object Type

In Java, **it is the type of the reference variable (not the type of the object) that determines what members can be accessed:**

| Code Snippet | Valid? | Accessible Members | Explanation |
| :--- | :---: | :--- | :--- |
| `BoxWeight b = new BoxWeight(2, 3, 4, 8);` | ✅ Yes | `l, h, w, weight` | Both reference and object are `BoxWeight`. |
| `Box b = new BoxWeight(2, 3, 4, 8);` | ✅ Yes | `l, h, w` (Cannot access `weight`) | The reference `b` is of type `Box`, so the compiler only allows access to fields defined in `Box`. |
| `BoxWeight b = new Box(2, 3, 4);` | ❌ No | **Compile Error** | You cannot assign a parent object to a child reference because the child reference expects `weight` to exist, but the parent object has no knowledge of it. |

---

#### 4. Summary Rules for `super`:
1. `super(...)` calls the constructor of the direct parent class.
2. `super(...)` **must be the very first line** inside the subclass constructor.
3. You cannot use both `this(...)` and `super(...)` in the same constructor because both require being on the first line.
4. If a parent class variable is shadowed by a child class variable, use `super.variableName` to access the parent's copy.

## Types of Inheritance in Java
1. **Single Inheritance**: A class inherits from one superclass.

* Example:
```java 
public class BoxWeight extends Box {
    // BoxWeight inherits from Box
}
```
2. **Multilevel Inheritance**: A class inherits from a superclass, and another class inherits from the first subclass.

* Example:
```java 
public class BoxWeight extends Box {
    // BoxWeight inherits from Box
}
public class BoxPrice extends BoxWeight {
    // BoxPrice inherits from BoxWeight, which inherits from Box
}
```
* Here, access will go bottom up, i.e., ```BoxPrice -> BoxWeight -> Box.```
    
    If a method is not found in BoxPrice, it will look for it in BoxWeight, and if not found there, it will look in Box.

3. **Multiple Inheritance**: A class can inherit from multiple superclasses. (multiple parent classes). 

**However, Java does not support multiple inheritance with classes to avoid ambiguity. Instead, it can be achieved using **interfaces**.**

4. **Hierarchical Inheritance**: Multiple classes inherit from a single superclass. (muultiple child classes from one parent class)

* Example:
```java 
public class Dog extends Animal {
    // Dog inherits from Animal
}
public class Cat extends Animal {
    // Cat inherits from Animal
}
```
* Here, both `Dog` and `Cat` inherit from the same parent class `Animal` and function independently of each other.

5. **Hybrid Inheritance**: A combination of two or more types of inheritance. (e.g., multilevel + hierarchical) 
In Java, since multiple inheritance is not supported with classes, hybrid inheritance can be achieved using interfaces.


# 2. Polymorphism
Poly -> means many || Morphism -> ways to  represent something.
- Polymorphism is the ability of an object to take on many forms. 

    In Java, polymorphism allows methods to do different things based on the object that it is acting upon. It is one of the core concepts of Object-Oriented Programming (OOP).

### Example: Runtime Polymorphism & Method Overriding (`Shapes`, `Circle`, `Square`, `Triangle`)

**Polymorphism** comes from Greek (*poly* = many, *morph* = forms). It is the ability of an entity (like a method or object) to take on multiple forms.


---

### 1. Compile-Time Polymorphism (Static Polymorphism / Early Binding)

In compile-time polymorphism, the compiler decides which method to call **at compile time** based on method signatures (name, number, types, and order of parameters).

- **How it is achieved**: Via **Method Overloading**.
- **Rules for Method Overloading**:
  1. Methods **must have the same name**.
  2. Methods **must have different parameter lists**:
     - Different number of parameters (`sum(int, int)` vs `sum(int, int, int)`).
     - Different types of parameters (`sum(int, int)` vs `sum(double, double)`).
     - Different order of types (`sum(int, String)` vs `sum(String, int)`).
  3. **Return type does NOT matter**: Changing only the return type without changing parameter types will result in a **compile error**.

#### Code Example (`Numbers.java`):
```java
package Lecture3.polymorphism;

public class Numbers {
    // 1. Method with 2 integer arguments
    int sum(int a, int b) {
        return a + b;
    }

    // 2. Overloaded method with 3 integer arguments
    int sum(int a, int b, int c) {
        return a + b + c;
    }

    // 3. Overloaded method with double arguments
    double sum(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Numbers obj = new Numbers();

        // The compiler determines at compile time which method to bind based on arguments:
        System.out.println(obj.sum(2, 3));        // Calls sum(int, int) -> Output: 5
        System.out.println(obj.sum(1, 3, 7));     // Calls sum(int, int, int) -> Output: 11
        System.out.println(obj.sum(2.5, 3.5));    // Calls sum(double, double) -> Output: 6.0
    }
}
```



### 2. **Runtime Polymorphism** (Dynamic Polymorphism) is achieved through **Method Overriding**, where a subclass provides its own specific implementation of a method that is already defined in its superclass.



#### 1. Code Implementation

##### File 1: Base / Super Class (`Shapes.java`)
```java
package Lecture3.polymorphism;

public class Shapes {
    void area() {
        System.out.println("I am in shapes");
    }
}
```

##### File 2: Child Class (`Circle.java`)
```java
package Lecture3.polymorphism;

public class Circle extends Shapes {
    // This overrides the area() method of the parent Shapes class
    @Override // Annotation: verifies this method actually overrides a superclass method
    void area() {
        System.out.println("Area is pi * r * r");
    }
}
```

##### File 3: Child Class (`Square.java`)
```java
package Lecture3.polymorphism;

public class Square extends Shapes {
    @Override
    void area() {
        System.out.println("Area is side * side");
    }
}
```

##### File 4: Child Class (`Triangle.java`)
```java
package Lecture3.polymorphism;

public class Triangle extends Shapes {
    @Override
    void area() {
        System.out.println("Area is 0.5 * base * height");
    }
}
```

##### File 5: Execution Class (`Main.java`)
```java
package Lecture3.polymorphism;

public class Main {
    public static void main(String[] args) {
        Shapes shape = new Shapes();
        Shapes circle = new Circle();  // Upcasting: Parent reference -> Child object
        Shapes square = new Square();  // Upcasting: Parent reference -> Child object
        Shapes triangle = new Triangle();

        shape.area();    // Output: I am in shapes
        circle.area();   // Output: Area is pi * r * r
        square.area();   // Output: Area is side * side
        triangle.area(); // Output: Area is 0.5 * base * height
    }
}
```

---

#### 2. Output:
```text
I am in shapes
Area is pi * r * r
Area is side * side
Area is 0.5 * base * height
```

---

#### 3. How Dynamic Method Dispatch Works (Under the Hood)

When you write:
```java
Shapes circle = new Circle();
circle.area();
```

1. **At Compile Time**: 
   - The Java compiler checks the **reference type** (`Shapes`).
   - It verifies whether the method `area()` exists in `Shapes`. If it does not exist in `Shapes`, the code will **not compile**.
2. **At Runtime (Dynamic Method Dispatch)**:
   - The JVM looks at the **actual object type created in heap memory** (`new Circle()`).
   - If the method is overridden in the child class (`Circle`), the JVM calls `Circle`'s version of `area()`, **not** `Shapes`'s version.
   - This runtime decision mechanism is called **Dynamic Method Dispatch** (or Late Binding).

---

#### 4. The `@Override` Annotation
- The `@Override` keyword is an annotation that instructs the compiler to check whether the method below it is **actually overriding** a method from the parent class.
- If you make a typo in the method name (e.g. `void Area()` instead of `void area()`), the compiler will throw an error immediately:
  > *"The method Area() of type Circle must override or implement a supertype method"*

---

#### 5. Important Rules & Interview Questions for Method Overriding:

1. **Can static methods be overridden?**
   - **No!** Static methods belong to the class, not instances. When a child class defines a static method with the exact same signature as a parent static method, it is called **Method Hiding** (resolved at compile time), not overriding.

        Overriding depends on objects -> Static methods do not depend on objects, so static methods cannot be overridden. Instead, they are **hidden**.
2. **Can `final` methods be overridden?**
   - **No.** The `final` keyword prevents method overriding (enables early binding / compiler inlining for performance).
3. **Can `final` classes be inherited?**
   - **No.** A `final` class cannot be extended by any other class (all its methods implicitly become non-overridable).
4. **Can `private` methods be overridden?**
   - **No.** `private` methods are not accessible or visible outside their own class.
5. **Access Modifiers Rule**:
   - An overriding method in a child class **cannot assign weaker access privileges** than the parent method (e.g. if parent is `public`, the child method cannot be `protected` or `default`). It can only stay the same or become more accessible.

### What is Upcasting?

**Upcasting** is the process of assigning a **child class object** to a **parent class reference variable** (casting "up" the inheritance hierarchy).

```java
Parent obj = new Child();
// Example:
Shapes shape = new Circle();
Box box = new BoxWeight(2, 3, 4, 8);
```

---

### Key Characteristics:

1. **Automatic / Implicit & Safe**:
   * Java does it **automatically** without needing explicit type casting `(Shapes) new Circle()`.
   * It is 100% type-safe because a child object **IS-A** parent (e.g., a `Circle` is always a `Shape`).

2. **The Golden Rule of Access**:
   * **Reference Type (`Shapes`)** determines **what** you can access: You can only access fields and methods defined in the parent class. Child-specific variables cannot be accessed.
   * **Object Type (`Circle`)** determines **which method runs**: If a method is overridden, Java calls the child class version at runtime (**Runtime Polymorphism / Dynamic Method Dispatch**).

```java
Shapes shape = new Circle();

shape.area();          // ✅ Runs Circle's overridden area() method!
// shape.radius;       // ❌ COMPILE ERROR: 'radius' is not defined in Shapes.
```

---

### Why is Upcasting used?
It allows you to write **clean, flexible, generic code**. For example, treating different shapes uniformly in an array or method:

```java
// You can group different child objects under one parent array:
Shapes[] shapes = { new Circle(), new Square(), new Triangle() };

for (Shapes s : shapes) {
    s.area(); // Calls each shape's specific area() dynamically!
}
```

### What is Dynamic Method Dispatch?

**Dynamic Method Dispatch** (also called *Runtime Method Binding* or *Late Binding*) is the mechanism by which Java resolves a call to an **overridden method at runtime**, rather than at compile-time. 

It is the underlying mechanism that makes **Runtime Polymorphism** work.

---

### How It Works (Step-by-Step):

```java
Shapes obj = new Circle();
obj.area();
```

1. **At Compile Time (The Check):**
   * The compiler looks at the **reference type** (`Shapes`).
   * It only checks: *"Does `Shapes` have an `area()` method?"*
   * If yes, the code compiles successfully.

2. **At Runtime (The Dispatch):**
   * When the program runs, the JVM inspects the **actual object created in heap memory** (`Circle`).
   * The JVM dynamically dispatches the call to **`Circle`'s version of `area()`**, overriding the parent's implementation.

---

### Why is it called "Dynamic"?

Because the decision of **which version** of the method to execute is made **dynamically while the code is running**, depending entirely on what object the reference is pointing to:

```java
Shapes obj;

obj = new Circle();
obj.area(); // Dispatches to Circle's area()

obj = new Square();
obj.area(); // Dispatches to Square's area()
```

---

> [!IMPORTANT]
> **Exceptions to Dynamic Method Dispatch:**
> - `final` methods
> - `static` methods
> - `private` methods
> 
> These methods **cannot be overridden**, so the JVM binds them at **compile-time (Early / Static Binding)** instead of using dynamic dispatch.

### What is "Binding"?
**Binding** is the process of connecting a **method call** (e.g., `obj.method()`) to the actual **method body / code** that gets executed.

---

### 1. Early Binding (Static Binding)

* **When it occurs:** At **compile-time** (by the compiler).
* **How it works:** The compiler knows the exact method definition to link before the program even runs.
* **When is it used?**
  * **Method Overloading**
  * `static` methods (belong to class, not instances)
  * `final` methods (cannot be overridden)
  * `private` methods (not visible outside the class)
* **Advantage:** **Faster execution** because there is no runtime overhead or lookup needed.

```java
class Demo {
    // Early binding happens here:
    public static void show() { ... }
    public final void print() { ... }
    private void test() { ... }
}
```

---

### 2. Late Binding (Dynamic Binding)

* **When it occurs:** At **runtime** (by the JVM).
* **How it works:** The compiler cannot know which method will be executed until the program runs, because it depends on the **actual object type in heap memory** (via Dynamic Method Dispatch).
* **When is it used?**
  * **Method Overriding** (virtual instance methods).
* **Advantage:** Enables **Polymorphism and flexibility** (e.g., passing any subclass object to a parent reference).

```java
Shapes obj = new Circle(); // Upcasting
obj.area(); // Late binding: JVM decides at runtime to call Circle's area()
```

---

### Quick Comparison

| Feature | Early Binding (Static) | Late Binding (Dynamic) |
| :--- | :--- | :--- |
| **Binding Time** | **Compile-time** | **Runtime** |
| **Mechanism** | Method Overloading, `static`, `final`, `private` | Method Overriding |
| **Decided by** | **Reference Type** | **Actual Object in Heap** |
| **Speed** | **Faster** (direct jump in bytecode) | **Slightly slower** (runtime lookup table) |
| **Polymorphism** | Compile-Time Polymorphism | Runtime Polymorphism |

# 3. Encapsulation

#### Wrapping up the implementation of the data memebers and the methods inside a class
* Hides the code and all the data into a single entity to protect the data from outside interference and misuse.

### Abstraction != Encapsulation

# 4. Abstraction
#### Hiding the implementation details and showing only the functionality to the user.

### Abstraction focuses on **what** the object does, while encapsulation focuses on **how** it does it.

* Abstraction is achieved using **abstract classes** and **interfaces** in Java.
* Encapsulation is achieved using **access modifiers** (private, protected, public) and **getter/setter methods**.