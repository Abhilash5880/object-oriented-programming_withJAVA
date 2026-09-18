# Class 01: Introduction to Classes

## The `Student` Class

```java
class Student {
		int[] rno = new int[5];
		float[] marks = new float[5];
		String[] name = new String[5];
}
```

`Student` is a class. A class is a type definition that describes the state and behavior that
objects of that type can have. It groups related data (fields) and behavior
(methods) into one unit.

## Fields in This Class

The class contains three fields (also called attributes or properties):

| Field | Type | Purpose | Capacity |
| --- | --- | --- | ---: |
| `rno` | `int[]` | Stores roll numbers | 5 |
| `marks` | `float[]` | Stores marks | 5 |
| `name` | `String[]` | Stores student names | 5 |

Each `new ...[5]` expression creates an array object with five positions.
Because these are instance-field initializers, each `Student` object gets its
own three array objects when the `Student` object is initialized.

## Class vs. Object

- A **class** is a type definition that describes the structure and behavior
  that objects of that type can have.
- An **object** is a runtime entity created from a class (or, for arrays, from
  an array type).
- An object has its own instance state, so different objects can have different
  values in their instance fields.

For example:

```java
Student student = new Student();
```

This creates one `Student` object and stores a reference to that object in the
local variable `student`.

In this example, each `Student` object has three array fields. Each array has
five elements.

```text
CLASS -> TYPE DEFINITION
OBJECT -> RUNTIME ENTITY WITH STATE
```

The current `Student` class defines data fields but does not declare any
methods or constructors explicitly.

## 3 essential properties of an object
* **State of the object** -> The state of an object is represented by the values of its instance fields (and, more generally, the values that make up its observable state). 

For example, a Student object may have a roll number of 1, marks of 85.5, and a name of "John Doe". These values define the current state of that particular Student object.

* **Identity of the object** -> Object identity distinguishes one object from another.

In Java, object identity is not defined as a memory address. Two distinct objects
can contain identical state and still be different objects. The `==` operator
compares references for identity when its operands are reference types.

* **Behavior of the object** -> The behavior of an object is defined by the methods that can be called on it. These methods define what actions the object can perform and how it can interact with other objects. 

For example, a Student object may have methods to calculate the average marks, display student information, or update the student's name. 

## 4. DOT operator / separator in JAVA
	The dot (`.`) operator is used for member access. It can be used with an
object/reference expression to access accessible instance fields and methods,
and with a class name to access accessible static members.

For example, if we have a Student object named student, we can access its fields like this:
```java
student.rno[0] = 1;
student.marks[0] = 85.5f;
student.name[0] = "John Doe";
```
* **Instance variables** -> Instance variables are non-`static` fields. Each
object has its own set of instance fields, so different objects can have
different values for them.

## 5. `new` keyword

The `new` operator is used to create a new object or array and obtain a
reference to it. For a class object, initialization includes running instance
field initializers and then the appropriate constructor.

```java
Student student1;
```

This declares a local reference variable named `student1`; it does **not**
create a `Student` object. A local variable of reference type must be assigned
a value before it is read.

> **Memory note:** It is common to visualize local variables and references as
> being on the stack and objects as being on the heap. However, the Java
> Language Specification does not require this particular memory layout. JVM
> implementations may optimize allocation and storage.

```java
Student student = new Student();
```

Conceptually, the declaration and type checking are handled by the compiler,
while the `new Student()` expression creates and initializes the object when
the program executes.

> **Important:** Do not memorize "every class object must be created with
> `new`." `new` is the normal explicit object-creation syntax, but Java also
> has other mechanisms through which objects can come into existence, such as
> deserialization, cloning, reflection, boxing, and JVM-created objects.

## 6. `Student()` -> Constructor

The `Student()` part of an expression is a constructor invocation. A
constructor is a special class member used to initialize a newly created
object. A constructor has the same name as its class and **no return type**,
not even `void`.

For example:

```java
Student student = new Student(17, "Rick", 90.5f);
```

This invokes the `Student(int, String, float)` constructor if that constructor
exists.

A constructor can have parameters, and constructor overloading allows a class
to provide multiple constructors with different parameter lists.

> In the earlier `Student` example, the arrays are created by the instance
> field initializers (`new int[5]`, etc.). A constructor can initialize or
> modify fields, but it is not accurate to say that the constructor itself is
> necessarily responsible for allocating every field object.

#### What if we create our own constructor?

We can write a constructor inside the class to initialize an object's fields:

```java
class Student {
	int rno;
	String name;
	float marks;

	Student(int rno, String name, float marks) {
		this.rno = rno;
		this.name = name;
		this.marks = marks;
	}
}
```

A constructor has the same name as its class and has no return type. The
`this` keyword refers to the current object. It is especially useful when a
constructor parameter has the same name as an instance field:

```java
this.rno = rno;
// instance variable = parameter
```

Now the constructor can be used when creating an object:

```java
Student student = new Student(17, "Rick", 90.5f);

System.out.println(student.rno);    // 17
System.out.println(student.name);  // Rick
System.out.println(student.marks); // 90.5
```

### The default constructor rule

If a class has no constructor, Java automatically provides a no-argument
default constructor. However, if we create any constructor ourselves, Java no
longer creates the default constructor for us.

```java
class Student {
	int rno;

	Student(int rno) {
		this.rno = rno;
	}
}

Student first = new Student(17); // valid
// Student second = new Student(); // error: no no-argument constructor
```

If both forms are needed, we must define the no-argument constructor ourselves:

```java
class Student {
	int rno;
	String name;
	float marks;

	Student() {
		this(0, null, 0.0f);
	}

	Student(int rno, String name, float marks) {
		this.rno = rno;
		this.name = name;
		this.marks = marks;
	}
}
```

`this(...)` invokes another constructor in the same class. It must be the
first statement in the constructor. A class having multiple constructors with
different parameter lists is called **constructor overloading**:

```java
Student emptyStudent = new Student();
Student fullStudent = new Student(17, "Rick", 90.5f);
```

### Copy constructor

Java does not automatically provide a special copy-constructor mechanism.
However, you can define a constructor that accepts another object of the same
class and uses it to initialize a new object. This is commonly called a
**copy constructor**:

```java
class Student {
	int rno;
	String name;
	float marks;

	Student(int rno, String name, float marks) {
		this.rno = rno;
		this.name = name;
		this.marks = marks;
	}

	Student(Student other) {
		this(other.rno, other.name, other.marks);
	}
}

Student original = new Student(17, "Rick", 90.5f);
Student copy = new Student(original);
```

`original` and `copy` are two different objects. This copy constructor copies
the values of the three fields into the new object. Because `String` is
immutable, sharing the same `String` reference is normally harmless here. For
mutable reference-type fields, this style would be a **shallow copy** unless
the referenced objects are copied as well.

### Constructor overloading

Constructor overloading means defining more than one constructor in the same
class, where each constructor has a different parameter list. Java chooses the
matching constructor based on the arguments passed to `new Student(...)`.

The `Student` class in `Student.java` has three overloaded constructors:

```java
class Student {
	int rno;
	float marks;
	String name;

	// 1. No-argument constructor
	Student() {
		this(0, null, 0.0f);
	}

	// 2. Parameterized constructor
	Student(int rno, String name, float marks) {
		this.rno = rno;
		this.name = name;
		this.marks = marks;
	}

	// 3. Copy constructor
	Student(Student student) {
		this(student.rno, student.name, student.marks);
	}
}
```

Each constructor can be called with a different argument list:

```java
Student emptyStudent = new Student();
Student fullStudent = new Student(17, "Rick", 90.5f);
Student copiedStudent = new Student(fullStudent);
```

The no-argument constructor delegates to the parameterized constructor using
`this(0, null, 0.0f)`. The copy constructor also delegates to the parameterized
constructor, passing the values from another `Student` object. This avoids
duplicating the field-initialization code.

These constructors are overloaded because their parameter lists are different:

| Constructor | Parameter list | Purpose |
| --- | --- | --- |
| `Student()` | No parameters | Creates a student with default values |
| `Student(int, String, float)` | Roll number, name, and marks | Creates a student with supplied values |
| `Student(Student)` | Another `Student` object | Creates a student with copied values |

* Constructor overloading is resolved at compile time based on the applicable
constructor signatures. Constructors are not inherited and therefore cannot be
overridden. Method overriding is a separate concept in which a subclass
provides a new implementation of an overridable inherited instance method.

### Why are primitive datatypes not created with the `new` keyword?

Primitive types (`byte`, `short`, `int`, `long`, `float`, `double`, `char`,
`boolean`) are not objects and are not instantiated with `new`.

For example:

```java
int x = 10;
```

For primitive **fields**, Java provides default values when the containing
object is created. Local primitive variables do **not** receive automatic
default values and must be assigned before they are read.

Java also provides wrapper classes such as `Integer`, `Double`, and `Boolean`
when an object representation of a primitive value is needed.

Python differs in this respect: Python's built-in integers, floats, booleans,
etc. are objects, whereas Java distinguishes primitive types from reference
types.

# 7. Wrapper Classes

Java provides **wrapper classes** that represent primitive values as objects.

| Primitive | Wrapper |
| --- | --- |
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

For example:

```java
int x = 10;

Integer boxed = Integer.valueOf(x); // explicit boxing
Integer boxed2 = x;                 // autoboxing

int y = boxed;                      // unboxing
```

Wrapper objects are useful when an API requires objects rather than primitive
values, such as generic collections:

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10); // autoboxing converts int to Integer
```

### Important: Java is pass-by-value

A common beginner example is trying to swap two primitive variables inside a
method:

```java
public class WrapperExample {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        swap(a, b);

        System.out.println("a = " + a); // 10
        System.out.println("b = " + b); // 20
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("Inside swap: a = " + a + ", b = " + b);
    }
}
```

The swap affects only the method's local copies. Java is **always
pass-by-value**. When an object is passed to a method, the value being copied is
the reference value. Therefore, the method can modify the referenced object's
state, but assigning a different object to its parameter does not change the
caller's reference.

> Wrapper classes do not change Java's pass-by-value rule.

# 8. `final` Keyword

The `final` keyword is used in several contexts:

- A **final variable** can be assigned only once.
- A **final method** cannot be overridden by a subclass.
- A **final class** cannot be extended.

For example:

```java
final int INCREASE = 2;
```

After `INCREASE` has been assigned, it cannot be assigned another value.

A commonly used Java constant is declared with both `static` and `final`:

```java
static final int MAX_SIZE = 100;
```

### `final` references and immutability

A `final` reference means the **reference cannot be reassigned**. It does
**not** make the referenced object immutable.

```java
final Student student = new Student();

student.name = "Rick";        // allowed if the field is accessible
// student = new Student();  // ❌ cannot reassign the final reference
```

So:

```text
final reference != immutable object
```

For a final primitive variable, the primitive value cannot be reassigned after
the variable has been assigned.

For a final reference variable, only the reference is final. The referenced
object may still be mutable.

# 9. Garbage Collection

Garbage collection (GC) is Java's automatic memory-management mechanism for
reclaiming storage associated with objects that are no longer reachable by
the program.

```text
reachable object   -> may still be used
unreachable object -> eligible for garbage collection
```

> **Eligible** does not mean "collected immediately." Java does not guarantee
> exactly when garbage collection will occur.

Java does not provide a general `delete` operation for ordinary objects. The
JVM manages their reclamation automatically.

### Java vs. C++

C++ does not use a Java-style garbage collector for ordinary memory
management. C++ commonly relies on deterministic object lifetimes, automatic
storage duration, RAII, and smart pointers such as `std::unique_ptr` and
`std::shared_ptr`.

When raw dynamic allocation is used with `new`, the programmer is responsible
for releasing the corresponding object with `delete`. C++ destructors handle
object/resource cleanup when an object's lifetime ends; they are **not** the
same mechanism as Java garbage collection.

# * Are Objects and Instances the Same Thing?

In everyday Java discussion, **object** and **instance** are often used
interchangeably. The word "instance" emphasizes an object's relationship to a
particular class or type.

It is not accurate to define an object simply as "the entity created on the
heap with `new`." Java does not require a particular stack/heap implementation,
and objects can also come into existence through mechanisms other than an
explicit `new` expression.

Conceptually, an object is a runtime entity with identity and state. Calling
it an **instance of `Dog`** emphasizes its relationship to the `Dog` class.

---

### The Difference in Emphasis

| Term | What it emphasizes | Perspective |
| :--- | :--- | :--- |
| **Object** | A runtime entity with identity and state. | The entity itself |
| **Instance** | The relationship between an object and a class/type. | "An instance **of** a class" |

---

### Real-World Analogy

Think of the words **"Person"** vs. **"Son"**:

* Alex is a **person**: this describes what Alex is.
* Alex is a **son of John**: this describes a relationship.

Similarly:

```java
Dog myDog = new Dog();
```

* `myDog` refers to a **`Dog` object**.
* That object is an **instance of `Dog`**.
* If `Dog extends Animal`, the same object can also be described as an
  instance of `Animal`.

---

### In Java's Language

Java provides the `instanceof` operator for testing whether an object is
compatible with a particular reference type:

```java
Circle c = new Circle();

System.out.println(c instanceof Circle); // true
System.out.println(c instanceof Shapes); // true, if Circle extends Shapes
```

Here, `c` refers to **one object**. That object is an instance of `Circle`,
and because `Circle` is a subtype of `Shapes`, it can also be treated as an
instance of `Shapes`.

If the reference is `null`, `instanceof` evaluates to `false`:

```java
Circle c = null;
System.out.println(c instanceof Circle); // false
```

---

### Summary

* In ordinary Java terminology, an **instance is an object considered in
  relation to a class/type**.
* "Object" and "instance" are often used interchangeably when discussing
  ordinary class instances.
* An array is also an object in Java, even though arrays are not declared by
  ordinary class declarations. Therefore, avoid the absolute statement
  "every object is an instance of some class."
* `instanceof` checks runtime type compatibility. For a `null` reference, it
  evaluates to `false`.
