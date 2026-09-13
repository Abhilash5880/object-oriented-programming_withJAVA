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

#### What if we create our own contructor???
