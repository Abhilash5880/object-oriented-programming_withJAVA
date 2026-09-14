# Class 01: Introduction to Classes

## The `Student` Class

```java
class Student {
		int[] rno = new int[5];
		float[] marks = new float[5];
		String[] name = new String[5];
}
```

`Student` is a class. A class is a blueprint or template used to create
objects. It groups related data and, when needed, the functions that operate
on that data.

## Fields in This Class

The class contains three fields (also called attributes or properties):

| Field | Type | Purpose | Capacity |
| --- | --- | --- | ---: |
| `rno` | `int[]` | Stores roll numbers | 5 |
| `marks` | `float[]` | Stores marks | 5 |
| `name` | `String[]` | Stores student names | 5 |

Each `new ...[5]` expression creates an array with five positions. The arrays
are initialized when a `Student` object is created.

## Class vs. Object

- A **class** is a logical definition or template.
- An **object** is an instance created from that class and occupies memory.
- The class describes the fields; an object stores the actual values in those
	fields.

For example, this creates one `Student` object:

```java
Student student = new Student();
```
A class is a blueprint for creating objects. It defines the properties (attributes) and behaviors (methods) that the objects created from the class will have. In this case, the Student class has three properties: rno (roll number), marks, and name, which are arrays that can hold information for up to 5 students.

CLASS -> LOGICAL CONSTRUCT || OBJECT -> PHYSICAL ENTITY (takes up memory)

The current class only defines data fields. It does not yet declare any
methods, constructors, or objects.

## 3 essenstial properties of an object
* **state of the object** -> The state of an object is represented by the values of its attributes. 

For example, a Student object may have a roll number of 1, marks of 85.5, and a name of "John Doe". These values define the current state of that particular Student object.

* **Identity of the object** -> The identity of an object is a unique identifier that distinguishes it from other objects. 

In Java, every object has a unique identity, which is typically represented by its memory address. Even if two objects have the same state (i.e., the same values for their attributes), they are considered different objects if they have different identities.

* **Behavior of the object** -> The behavior of an object is defined by the methods that can be called on it. These methods define what actions the object can perform and how it can interact with other objects. 

For example, a Student object may have methods to calculate the average marks, display student information, or update the student's name. The behavior of an object is determined by the methods defined in its class.

## 4. DOT operator / seperator in JAVA
	The dot operator (.) is used to link the reference variable of an object to its fields and methods. It allows us to access the attributes and behaviors of an object.

For example, if we have a Student object named student, we can access its fields like this:
```java
student.rno[0] = 1;
student.marks[0] = 85.5f;
student.name[0] = "John Doe";
```
* **Instance variables** -> Variables inside the object are called instance variables. Each object has its own copy of these variables, and they can hold different values for different objects.

## 5. NEW keyword:
The `new` keyword is used to create an instance of a class. It allocates memory for the object and calls the constructor to initialize it.

`Student student1` -> declaring reference variable (student1) to object (Student)
* but it does not yet create the object in memory. It only creates a reference variable that can point to an object of type Student. It is kept in **stack memory**.

**`new` operator dynamically allocates memory for the object in **heap memory** and returns a reference to the newly created object.**

### Hence, all class objects in java must to created dynamically using the `new` keyword.

```java
Student student = new Student();
```
Left side happens during  **compile time** and right side happens during **runtime**.

## 6. `Student()` -> Constructor
The `Student()` part of the expression is a constructor. A constructor is a special method that is called when an object is created. It initializes the object's state and can take parameters to set initial values for the object's attributes.

Basically defines what happens when an object is created. In this case, the constructor initializes the arrays for roll numbers, marks, and names. (data allocation)

`Student Rick = new Student(17, "Rick", 90.5f);`
* **Student()** is a special type of function in class -> This is **dynamic** constructor. It is called when an object is created. It initializes the object's state and can take parameters to set initial values for the object's attributes.

Now a fucntion by default has some arguments, but a constructor has no return type. It is used to initialize the object when it is created.

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

The constructor has the same name as the class and has no return type. The
`this` keyword refers to the current object. It is useful when a constructor
parameter has the same name as an instance variable:

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

`this(...)` calls another constructor in the same class. It must be the first
statement in the constructor. This is called constructor overloading because
the class has multiple constructors with different parameter lists:

```java
Student emptyStudent = new Student();
Student fullStudent = new Student(17, "Rick", 90.5f);
```

### Copy constructor

Java does not automatically provide a copy constructor, but we can create one
that initializes a new object using another `Student` object:

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

`original` and `copy` are two different objects. The copy constructor gives
the new object the same initial state as the original object.

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

* Constructor overloading is resolved at compile time. It is different from method overriding, which happens when a subclass provides a new implementation
of an inherited method.

### Why are primitive datatypes not implemented with `new` keyword?
* Primitive datatypes in Java are not objects, so they cannot be instantiated with the `new` keyword. Instead, they are stored directly in memory and have default values assigned to them.

* For e.g Java and Python work differently...in Python there are no primitive datatypes, everything is an object. In Java, primitive datatypes are not objects, so they cannot be instantiated with the `new` keyword. Instead, they are stored directly in memory and have default values assigned to them.

# 7. WrapperClass

This is a Java class that demonstrates the use of wrapper classes.

### Description

The `wrapperClass` class demonstrates the use of wrapper classes in Java. Wrapper classes are used to convert primitive data types into objects. The `Integer` class, which is a wrapper class for the `int` primitive data type, is used in this example.

### Usage

To use the `wrapperClass` class, follow these steps:

1. Create an instance of the `wrapperClass` class.
2. Call the `swap` method with two integer values to swap them.
3. The swapped values will be printed to the console.

### Example

```java
public class wrapperClass {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        swap(a, b);
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + ", b = " + b);
    }
}
```

# 8. `final` Keyword
* The `final` keyword in Java is used to declare constants, prevent method overriding, and prevent inheritance of classes. 

When a variable is declared as `final`, its value cannot be changed once it has been assigned. When a method is declared as `final`, it cannot be overridden by subclasses. When a class is declared as `final`, it cannot be subclassed.

E.g -> ```final  int INCREASE=2;``` -> This means that the value of `INCREASE` cannot be changed after it has been assigned.

* But it will only ensure immatability if the instance variable is of primitive datatype. If the instance variable is of reference type, then the reference cannot be changed, but the object it points to can still be modified. 

E.g -> ```final Student student = new Student();``` -> This means that the reference variable `student` cannot be changed to point to a different `Student` object, but the fields of the `Student` object it points to can still be modified.

# 9. Garbage Collection
* Garbage collection is the process of automatically freeing up memory by removing objects that are no longer in use. In Java, the garbage collector is responsible for identifying and removing objects that are no longer reachable from the program.

* C++ does not have automatic garbage collection, so the programmer is responsible for managing memory manually using destructors. In C++, if an object is no longer needed, the programmer must explicitly delete it to free up memory. If the programmer forgets to delete an object, it can lead to memory leaks and other issues.