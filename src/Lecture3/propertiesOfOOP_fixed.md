# Inheritance, Polymorphism, Encapsulation, Abstraction

# 1. Inheritance

- Inheritance is a mechanism in which one class acquires the properties (fields) and behaviors (methods) of another class.

  The class that inherits from another class is called the **subclass** (or derived class / child class), and the class being inherited from is called the **superclass** (or base class / parent class).

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
        dog.eat();  // Inherited method from Animal
        dog.bark(); // Method defined in Dog
    }
}
```

### Output:

```text
This animal eats food.
The dog barks.
```

Here, the `Dog` class inherits the `eat()` method from the `Animal` class.
`Dog` is the subclass/child class, and `Animal` is the superclass/parent class.

## `extends` Keyword

- In Java, the `extends` keyword is used to establish inheritance between classes.
- A subclass can directly access the superclass's `public` and `protected` members. Package-private (default) members are also directly accessible when the subclass is in the same package.
- `private` members of the superclass cannot be directly accessed by the subclass.
- Constructors are **not inherited** by subclasses.

### Important: Parent Reference vs Child Members

A child object can contain inherited state and child-specific state, but what you can access through a reference depends on the **reference type**.

```java
Box box = new BoxWeight(2, 3, 4, 8);

box.l;       // ✅ l is defined in Box
box.weight;  // ❌ weight is not defined in Box
```

On the other hand:

```java
Box box = new Box();
```

This object is only a `Box`; it does not contain the child-specific state or behavior of `BoxWeight`.

### `super` Keyword

- `super` is a special keyword used inside a subclass to refer to the **superclass context of the current object**.
- It can be used to invoke a superclass constructor and to access superclass fields or methods.
- It does **not** refer to a separate parent object; there is still one object being constructed.

### Example: `extends` and `super` Keywords (`Box.java` & `BoxWeight.java`)

**Inheritance** allows a child class (subclass) to inherit accessible fields and methods from a parent class (superclass) using the **`extends`** keyword, while the **`super`** keyword is used to invoke superclass constructors and access superclass members.

---

#### 1. Core Concepts

##### The `extends` Keyword:

- Establishes an **IS-A** relationship (e.g., `BoxWeight` **is a** `Box`).
- Syntax: `public class BoxWeight extends Box`
- A subclass inherits the superclass's accessible members. `private` members are not directly accessible from the subclass, and constructors are not inherited.
- In Java, a class can extend only **one** superclass (single inheritance of classes).

##### The `super` Keyword:

1. **To invoke the superclass constructor:**
   - Syntax: `super(l, h, w);`
   - It must be the **first statement** in the subclass constructor.
   - It delegates initialization of the superclass portion of the object to the selected superclass constructor.
   - If a constructor does not explicitly invoke another constructor using `this(...)` or `super(...)`, Java implicitly inserts `super()`.
   - The superclass must have an accessible no-argument constructor for that implicit `super()` call to compile.

2. **To access superclass variables or methods:**
   - If a child class declares a field with the same name as a superclass field, `this.fieldName` refers to the child's field and `super.fieldName` refers to the superclass field.
   - `super.methodName()` can be used to explicitly invoke the superclass implementation of an overridden instance method.

---

#### 2. Code Implementation

##### File 1: Parent Class (`Box.java`)

```java
package Lecture3.inheritance;

public class Box {
    double l;
    double h;
    double w;

    // User-defined no-argument constructor
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

    // Copy Constructor (a Java convention, not a special language feature)
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

// 'extends' establishes inheritance from Box
public class BoxWeight extends Box {
    double weight;

    // User-defined no-argument constructor
    public BoxWeight() {
        this.weight = -1;
        // Java implicitly inserts super(); here because this constructor
        // does not explicitly invoke this(...) or super(...).
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
        super(other); // Box(Box old) accepts a BoxWeight because BoxWeight IS-A Box
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
        // System.out.println(box3.weight); // ❌ COMPILE ERROR
    }
}
```

---

#### 3. Crucial Interview Concept: Reference Type vs Object Type

In Java, the **reference type determines what members are accessible through the reference at compile time**, while the **actual object type matters for runtime method overriding**.

| Code Snippet | Valid? | Accessible Members | Explanation |
| :--- | :---: | :--- | :--- |
| `BoxWeight b = new BoxWeight(2, 3, 4, 8);` | ✅ Yes | `l, h, w, weight` | Both the reference and object are `BoxWeight`. |
| `Box b = new BoxWeight(2, 3, 4, 8);` | ✅ Yes | `l, h, w` (not `weight`) | `b` is a `Box` reference, so the compiler only allows members available through `Box`. The actual object is still a `BoxWeight`. |
| `BoxWeight b = new Box(2, 3, 4);` | ❌ No | **Compile Error** | A `Box` is not necessarily a `BoxWeight`. A child reference cannot safely refer to an arbitrary parent object. |

### The IS-A Rule

```text
BoxWeight IS-A Box
Box is NOT necessarily a BoxWeight
```

Therefore:

```java
Box b = new BoxWeight(...);       // ✅ Child -> Parent (upcasting)
BoxWeight b = new Box(...);       // ❌ Parent -> Child without a valid cast
```

---

#### 4. Summary Rules for `super`

1. `super(...)` invokes a constructor of the **direct parent class**.
2. `super(...)` must be the **first statement** in a constructor.
3. A constructor may explicitly invoke **either** `this(...)` or `super(...)` as its first statement, but not both directly.
4. If neither `this(...)` nor `super(...)` is explicitly used, Java implicitly inserts `super()`.
5. If a superclass field is hidden by a subclass field, `super.fieldName` accesses the superclass field.
6. `super.methodName()` can explicitly invoke the superclass implementation of an overridden instance method.

## Types of Inheritance in Java

### 1. Single Inheritance

A class inherits from one superclass.

* Example:

```java
public class BoxWeight extends Box {
    // BoxWeight inherits from Box
}
```

### 2. Multilevel Inheritance

A class inherits from a superclass, and another class inherits from the first subclass.

* Example:

```java
public class BoxWeight extends Box {
    // BoxWeight inherits from Box
}

public class BoxPrice extends BoxWeight {
    // BoxPrice inherits from BoxWeight,
    // which inherits from Box
}
```

* The inheritance chain is:

```text
BoxPrice -> BoxWeight -> Box
```

  A member that is inherited through the chain can originate from `BoxWeight` or `Box` if it is not found/declared in `BoxPrice`.

### 3. Multiple Inheritance

Multiple inheritance means a class inherits from more than one **class**.

Java does **not** support multiple inheritance of classes:

```java
class C extends A, B { } // ❌ Not allowed
```

However, a Java class can implement multiple interfaces:

```java
class C implements A, B { } // ✅ Allowed
```

This allows a class to inherit multiple **types/contracts** and, where applicable, default method implementations, but interfaces are not the same thing as having multiple superclasses.

### 4. Hierarchical Inheritance

Multiple classes inherit from a single superclass.

* Example:

```java
public class Dog extends Animal {
    // Dog inherits from Animal
}

public class Cat extends Animal {
    // Cat inherits from Animal
}
```

* Here, both `Dog` and `Cat` inherit from the same parent class `Animal` and are independent subclasses of it.

### 5. Hybrid Inheritance

A combination of two or more types of inheritance (for example, multilevel + hierarchical inheritance).

Java does not support hybrid inheritance when it requires multiple inheritance of classes. However, interfaces can be combined with class inheritance to model more complex inheritance structures.


# 2. Polymorphism

`Poly` -> many  
`Morphism` -> forms

- Polymorphism is the ability of something to take on multiple forms.
- In Java, polymorphism allows the same operation or method call to behave differently depending on the applicable method or the actual runtime object.
- It is one of the core concepts of Object-Oriented Programming (OOP).

### Example: Runtime Polymorphism & Method Overriding (`Shapes`, `Circle`, `Square`, `Triangle`)

**Polymorphism** comes from Greek roots: `poly` means **many**, and `morph` means **form**.

---

### 1. Compile-Time Polymorphism (Static Polymorphism / Early Binding)

In compile-time polymorphism, the compiler determines which **overloaded method** is applicable based on the compile-time information available about the method call, including the argument types.

- **How it is achieved:** Via **Method Overloading**.

- **Rules for Method Overloading:**
  1. Methods must have the **same name**.
  2. Methods must have **different parameter lists**:
     - Different number of parameters (`sum(int, int)` vs `sum(int, int, int)`).
     - Different types of parameters (`sum(int, int)` vs `sum(double, double)`).
     - Different order of parameter types (`sum(int, String)` vs `sum(String, int)`).
  3. **Return type does NOT determine overloading**: Changing only the return type while keeping the same parameter list results in a compile error.

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

        // The compiler determines which overload is applicable
        // based on the arguments.
        System.out.println(obj.sum(2, 3));        // Calls sum(int, int) -> Output: 5
        System.out.println(obj.sum(1, 3, 7));     // Calls sum(int, int, int) -> Output: 11
        System.out.println(obj.sum(2.5, 3.5));    // Calls sum(double, double) -> Output: 6.0
    }
}
```

---

### 2. Runtime Polymorphism (Dynamic Polymorphism)

Runtime polymorphism is achieved through **method overriding**, where a subclass provides its own implementation of an overridable instance method inherited from its superclass.

---

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
    @Override // Annotation: asks the compiler to verify the override
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
        Shapes circle = new Circle();    // Upcasting: parent reference -> child object
        Shapes square = new Square();    // Upcasting: parent reference -> child object
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

#### 3. How Dynamic Method Dispatch Works

When you write:

```java
Shapes circle = new Circle();
circle.area();
```

1. **At Compile Time (The Check):**
   - The Java compiler checks the **reference type** (`Shapes`).
   - It verifies that an applicable `area()` method is available through `Shapes`.
   - If `area()` is not available through `Shapes`, the call does not compile.

2. **At Runtime (The Dispatch):**
   - The call is dispatched according to the **actual runtime class of the object** (`Circle`).
   - Because `Circle` overrides `area()`, the `Circle` implementation is executed instead of the inherited `Shapes` implementation.
   - This mechanism is called **Dynamic Method Dispatch** or **Late Binding**.

> The important mental model is:
>
> **Reference type determines what method call is allowed at compile time.**  
> **Runtime object type determines which overridden implementation executes.**

---

#### 4. The `@Override` Annotation

- `@Override` is an annotation that asks the compiler to verify that the following method actually overrides a superclass method or implements an interface method.
- If you make a mistake in the method signature, such as changing `area()` to `Area()`, the compiler reports an error because the method does not actually override the superclass method.

---

#### 5. Important Rules & Interview Questions for Method Overriding

1. **Can static methods be overridden?**
   - **No.** Static methods belong to the class rather than being dynamically dispatched based on an object.
   - If a subclass declares a static method with the same signature as a superclass static method, this is called **method hiding**, not overriding.

2. **Can `final` methods be overridden?**
   - **No.** A `final` method cannot be overridden by a subclass.
   - Do not assume that `final` automatically means a method will be inlined; such optimizations are implementation-dependent.

3. **Can `final` classes be inherited?**
   - **No.** A `final` class cannot be extended by another class.
   - Therefore, there can be no subclass that overrides its methods.

4. **Can `private` methods be overridden?**
   - **No.** A `private` method is not inherited by subclasses, so a subclass method with the same name/signature is not an override of the private method.

5. **Access Modifiers Rule:**
   - An overriding method cannot provide **weaker access** than the method it overrides.
   - For example, if the superclass method is `public`, the overriding method cannot be `protected` or package-private.
   - The overriding method may use the same or a more accessible access level.

### What is Upcasting?

**Upcasting** is assigning a child-class object to a parent-class reference variable, moving up the inheritance hierarchy.

```java
Parent obj = new Child();

// Examples:
Shapes shape = new Circle();
Box box = new BoxWeight(2, 3, 4, 8);
```

---

### Key Characteristics

1. **Automatic / Implicit & Safe:**
   - Java performs upcasting automatically; no explicit cast is required.
   - It is type-safe because every child object is also an instance of its parent class (IS-A relationship).

2. **The Golden Rule of Access:**
   - **Reference Type (`Shapes`) determines what is accessible:** You can access members available through `Shapes`. Child-specific members cannot be accessed through a `Shapes` reference without an appropriate cast.
   - **Runtime Object Type (`Circle`) determines which overridden instance method runs:** If the method is overridden, Java dispatches the call to the implementation corresponding to the actual runtime object.

```java
Shapes shape = new Circle();

shape.area();          // ✅ Runs Circle's overridden area() method
// shape.radius;       // ❌ COMPILE ERROR if radius is only defined in Circle
```

---

### Why is Upcasting used?

It allows you to write clean, flexible, generic code. For example, different child objects can be treated uniformly through a parent reference:

```java
// You can group different child objects under one parent array:
Shapes[] shapes = { new Circle(), new Square(), new Triangle() };

for (Shapes s : shapes) {
    s.area(); // Calls each shape's specific implementation dynamically
}
```

### What is Dynamic Method Dispatch?

**Dynamic Method Dispatch** (also called *Runtime Method Binding* or *Late Binding*) is the mechanism by which Java selects the implementation of an **overridden instance method at runtime**, based on the actual runtime class of the object.

It is the mechanism that enables **Runtime Polymorphism**.

---

### How It Works (Step-by-Step)

```java
Shapes obj = new Circle();
obj.area();
```

1. **At Compile Time (The Check):**
   - The compiler looks at the **reference type** (`Shapes`).
   - It checks whether an applicable `area()` method is available through `Shapes`.
   - If it is not, the code does not compile.

2. **At Runtime (The Dispatch):**
   - The method call is dynamically dispatched according to the **actual runtime class** (`Circle`).
   - Because `Circle` overrides `area()`, `Circle`'s implementation executes.

---

### Why is it called "Dynamic"?

Because the implementation selected for an overridden method call depends on the object's **runtime type**:

```java
Shapes obj;

obj = new Circle();
obj.area(); // Dispatches to Circle's area()

obj = new Square();
obj.area(); // Dispatches to Square's area()
```

The same reference variable can refer to different subclass objects at different times, and the overridden method implementation can therefore differ.

---

> [!IMPORTANT]
> **Methods that do not participate in normal runtime overriding:**
> - `final` methods
> - `static` methods
> - `private` methods
>
> These methods do not use the normal dynamic dispatch mechanism for overridable instance methods:
> - `final` methods cannot be overridden.
> - `static` methods are hidden, not overridden.
> - `private` methods are not inherited and therefore cannot be overridden.

### What is "Binding"?

**Binding** is the process of associating a method call (such as `obj.method()`) with the method implementation that will execute.

---

### 1. Early Binding (Static Binding)

- **General idea:** The method selection can be determined without relying on runtime overriding.
- **Common examples:**
  - Method overloading is resolved at compile time.
  - `static` methods do not participate in runtime overriding.
  - `private` methods do not participate in overriding because they are not inherited.
  - `final` methods cannot be overridden.
- Avoid treating "early binding" as a promise about performance. The JVM may apply many runtime optimizations, and the exact implementation strategy is JVM-dependent.

```java
class Demo {
    public static void show() { /* ... */ }
    public final void print() { /* ... */ }
    private void test() { /* ... */ }
}
```

---

### 2. Late Binding (Dynamic Binding)

- **General idea:** For an overridable instance method, the implementation to execute is selected at runtime according to the actual runtime class of the object.
- **Main mechanism:** **Method Overriding**.
- **Why it matters:** It enables runtime polymorphism and flexible code, such as passing different subclass objects through a common parent reference.

```java
Shapes obj = new Circle(); // Upcasting
obj.area(); // Late binding: Circle's area() executes
```

---

### Quick Comparison

| Feature | Compile-Time Selection / Early Binding | Runtime Dynamic Dispatch / Late Binding |
| :--- | :--- | :--- |
| **Main mechanism** | Method Overloading | Method Overriding |
| **When method selection occurs** | Compile time | Runtime for overridable instance methods |
| **What is important?** | Compile-time types and method signatures | Actual runtime object type |
| **Reference type** | Important for determining the compile-time types involved | Determines which members/method calls are accessible through the reference |
| **Runtime object type** | Not used to choose among overloads | Determines which overridden implementation executes |
| **Polymorphism** | Compile-Time Polymorphism | Runtime Polymorphism |

> **Remember:**
>
> **Overloading:** same method name + different parameter list → compiler chooses the overload.
>
> **Overriding:** subclass replaces an inherited overridable instance method → runtime dispatch chooses the implementation based on the actual object.

# 3. Encapsulation

### Definition

**Encapsulation** is the bundling of data and the methods that operate on that data inside a class, while controlling how that internal state can be accessed from outside the class.

- Encapsulation helps protect an object's internal state from uncontrolled external access or modification.
- Access modifiers such as `private`, `protected`, and `public` are used to control visibility.
- Getter/setter methods are one common way to provide controlled access, but they are **not required** for encapsulation.
- Good encapsulation can expose a controlled public interface while keeping implementation details and internal state hidden where appropriate.

### Abstraction != Encapsulation

They are related but different concepts:

- **Encapsulation:** bundles data/behavior and controls access to the internal state.
- **Abstraction:** hides unnecessary implementation details and exposes the essential functionality.

# 4. Abstraction

### Definition

**Abstraction** means hiding unnecessary implementation details and exposing only the essential functionality to the user.

### Abstraction vs Encapsulation

A useful mental model is:

> **Abstraction focuses on what an object does.**  
> **Encapsulation focuses on how its internal state and implementation are organized and protected.**

In Java, abstraction is commonly expressed using **abstract classes** and **interfaces**.

Encapsulation is commonly achieved using **access modifiers** such as `private`, `protected`, and `public`, together with a controlled public interface such as methods. Getters and setters are common tools, but they are not mandatory.
