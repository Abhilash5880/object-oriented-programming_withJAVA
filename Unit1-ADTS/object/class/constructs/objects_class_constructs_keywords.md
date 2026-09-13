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


