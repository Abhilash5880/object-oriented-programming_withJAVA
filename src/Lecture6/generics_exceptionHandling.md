### Amortized Constant time complexity
---

# Part 1. Dynamic Arrays & Custom ArrayList

Standard arrays in Java have a **fixed size** upon initialization. An **`ArrayList`** provides a **dynamic array** that automatically grows when it runs out of space.

---

## 1. How `ArrayList` Expands Its Size

When you add elements to an `ArrayList`:
1. It maintains an internal array of object references. For the standard `ArrayList<E>`, the backing array is an `Object[]`. The no-argument constructor starts with an empty backing array; capacity is grown as elements are added.
2. Elements are added in $O(1)$ time when sufficient capacity is available.
3. **When the current capacity is insufficient**:
   - A larger backing array is allocated.
   - Existing element references are copied to the new array ($O(N)$ copy operations).
   - The internal reference is updated to point to the new array.
   - The old array becomes eligible for garbage collection if no other references to it exist.
   - The new element is appended.

> **Important:** The Java API specifies that `ArrayList.add()` has **amortized $O(1)$** time and that capacity grows automatically, but it does not specify a particular growth factor. The commonly observed growth policy is an implementation detail, not a language guarantee.

#### Code Representation (`resize()` in CustomArrayList):
```java
private void resize() {
    // 1. Create a new array of double the size
    int[] temp = new int[data.length * 2];

    // 2. Copy all current items to the new array (Takes O(N) time)
    for (int i = 0; i < data.length; i++) {
        temp[i] = data[i];
    }

    // 3. Point data reference to the new array
    data = temp;
}
```

---

## 2. What is Amortized Constant Time $O(1)$?

### The Question:
If resizing and copying elements takes **$O(N)$ linear time**, why is adding an element (`add()`) to an `ArrayList` considered **$O(1)$ constant time**?

### The Answer: **Amortized Analysis**
The term **"amortized"** comes from financial accounting (spreading a large, occasional expense over time). 

In computer science, **Amortized Time Complexity** means:
> Even though an occasional operation is very expensive ($O(N)$ when doubling and copying), it happens so rarely that when the cost is averaged out over all the operations, each individual operation effectively takes **$O(1)$ constant time**.

---

## 3. Mathematical Proof (The Doubling Strategy)

Assume an initial capacity of **1**, and we insert **$N$ elements** (where $N$ is a power of 2, e.g., $N = 16$):

| Element Inserted | Array Capacity Before | Needs Resize? | Elements Copied | Total Cost (Copy + Insert) |
| :---: | :---: | :---: | :---: | :---: |
| **1st** | 1 | No | 0 | 1 |
| **2nd** | 1 | **Yes (Doubles to 2)** | 1 | 1 copy + 1 insert = **2** |
| **3rd** | 2 | **Yes (Doubles to 4)** | 2 | 2 copies + 1 insert = **3** |
| **4th** | 4 | No | 0 | 1 |
| **5th** | 4 | **Yes (Doubles to 8)** | 4 | 4 copies + 1 insert = **5** |
| 6th – 8th | 8 | No | 0 | 1 each |
| **9th** | 8 | **Yes (Doubles to 16)** | 8 | 8 copies + 1 insert = **9** |
| 10th – 16th | 16 | No | 0 | 1 each |

---

### Calculating the Total Cost for $N$ insertions:

1. **Cost of individual insertions**:
   Every element takes 1 write operation $\to N \times 1 = \mathbf{N}$ operations.

2. **Cost of copy operations across all resizes**:
   $$\text{Copies} = 1 + 2 + 4 + 8 + 16 + \dots + \frac{N}{2}$$

   This is a Geometric Progression where the sum is:
   $$\sum_{i=0}^{k-1} 2^i = 2^k - 1 = N - 1 < \mathbf{N}$$

3. **Total Work Done for $N$ Insertions**:
   $$\text{Total Cost} = \text{Insertions} + \text{Copies} = N + (N - 1) \approx \mathbf{2N}$$

4. **Amortized Cost per Insertion**:
   $$\text{Amortized Cost} = \frac{\text{Total Cost}}{N} = \frac{2N}{N} = \mathbf{2} \implies \mathcal{O}(1)$$

---

## 4. Why Not Increase Size by a Constant Amount (e.g., $+1$ or $+10$)?

| Strategy | Resize Frequency | Total Copy Cost for $N$ items | Amortized Time |
| :--- | :--- | :--- | :--- |
| **Incremental (+1 each time)** | Every single insertion | $1 + 2 + 3 + \dots + N = \frac{N(N+1)}{2} = \mathcal{O}(N^2)$ | $\frac{\mathcal{O}(N^2)}{N} = \mathbf{\mathcal{O}(N)}$ *(Very Bad!)* |
| **Doubling ($\times 2$ each time)** | Exponentially rarer ($1, 2, 4, 8, \dots$) | $1 + 2 + 4 + \dots + \frac{N}{2} < N$ | $\frac{2N}{N} = \mathbf{\mathcal{O}(1)}$ *(Amortized constant)* |

### Key Takeaway:
* **Worst-case time for a single `add()`**: $\mathcal{O}(N)$ when a resize/copy is required.
* **Amortized time for `add()`**: $\mathcal{O}(1)$ over a sequence of insertions when the backing array grows geometrically.


# Part 2. Generics in Java
To implement generics, the Java compiler uses **type erasure**. Generic type arguments are not retained in the same way as ordinary runtime class information. Because `T` is a type parameter rather than a reifiable runtime component type, you cannot directly create `new T[DEFAULT_SIZE]`. A common implementation technique is to use an `Object[]` as the backing array and cast individual elements to `T` when retrieving them.



### Amortized Constant Time $\mathcal{O}(1)$
Even though doubling and copying takes $\mathcal{O}(N)$ linear time, resizing happens so rarely that the cost averaged out over all insertions is **$\mathcal{O}(1)$ constant time**.

#### Mathematical Proof:
Suppose initial capacity is 1, and we insert $N$ items ($N = 2^k$):
- **Insert operations**: $N \times 1 = N$
- **Copy operations across resizes**: $1 + 2 + 4 + 8 + \dots + \frac{N}{2} = N - 1 < N$
- **Total operations**: $\text{Insertions} + \text{Copies} \approx 2N$
- **Amortized cost per operation**: $\frac{2N}{N} = \mathbf{2} \implies \mathbf{\mathcal{O}(1)}$


### 1. The Problem with `CustomArrayList`
In `CustomArrayList`, our internal array is hardcoded to `int[]`:
```java
public class CustomArrayList {
    private int[] data; // ❌ Only works for integers!
}
```
If we want a list for `String`, `Float`, or a custom `Student` object:
1. **Option A (Code Duplication)**: Write `CustomStringArrayList`, `CustomFloatArrayList`, etc. *(Terrible practice)*.
2. **Option B (Using `Object[]`)**: Store everything as `Object`. 
   - **Drawback**: **No Type Safety!** Any type could be added (`list.add("hello")`, `list.add(10)`).
   - Requires manual type casting everywhere: `String s = (String) list.get(0)`.
   - Can throw runtime **`ClassCastException`**.

---

### 2. What are Generics?
Introduced in Java 5, **Generics** (`<T>`) allow classes, interfaces, and methods to take **types as parameters** (parameterized types).

#### Key Advantages:
1. **Type Safety**: The compiler ensures you only add elements of the declared type.
2. **Elimination of Type Casting**: No need to manually cast `(Type)` when retrieving items.
3. **Compile-time Checking**: Detects type mismatch errors at compile-time rather than crashing at runtime.

---

### 3. Implementation: `CustomGenArrayList<T>`

```java
package Lecture6.generics;

import java.util.Arrays;

// T is the type parameter. Generic type arguments must be reference types.
public class CustomGenArrayList<T> {
    private Object[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;

    public CustomGenArrayList() {
        // Cannot create generic array directly: data = new T[DEFAULT_SIZE]; ❌
        data = new Object[DEFAULT_SIZE];
    }

    public void add(T num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private boolean isFull() {
        return size == data.length;
    }

    private void resize() {
        Object[] temp = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        checkIndex(size - 1);
        T removed = (T)(data[--size]);
        return removed;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T)(data[index]); // Explicit cast from Object to T
    }

    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

    @Override
    public String toString() {
        return "CustomGenArrayList{" +
                "data=" + Arrays.toString(Arrays.copyOf(data, size)) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        // Type-safe for Integer:
        CustomGenArrayList<Integer> intList = new CustomGenArrayList<>();
        intList.add(10);
        intList.add(20);
        System.out.println(intList); // [10, 20]

        // Type-safe for String:
        CustomGenArrayList<String> strList = new CustomGenArrayList<>();
        strList.add("Hello");
        strList.add("World");
        // strList.add(45); ❌ COMPILE ERROR: Incompatible types!
        System.out.println(strList);
    }
}
```

---

### 4. Why Can't We Write `new T[]`? (Type Erasure)

In the constructor, you **cannot** write:
```java
data = new T[DEFAULT_SIZE]; // ❌ COMPILE ERROR
```

#### Reason: **Type Erasure**
- Generic type parameters are checked by the compiler and are subject to **type erasure**.
- During erasure, an unbounded type parameter is replaced with `Object`, while a bounded type parameter is replaced with its leftmost bound.
- A type parameter such as `T` is not a reifiable runtime array component type, so `new T[...]` is not permitted.
- **Common solution**: Create an `Object[]` backing array and cast retrieved elements to `T` as needed. This pattern relies on the class maintaining its type-safety invariant.

---

### 5. Bounded Types (`extends` Keyword)

Sometimes you want to restrict the types allowed in your generic class.

#### Example: Restricting to Numbers Only
If we want a list that only accepts numeric types (`Integer`, `Float`, `Double`), we use **Upper Bounded Generics**:

```java
// T must be Number or a subclass of Number
public class CustomNumberList<T extends Number> {
    private Object[] data;
    // ...
}
```
- `CustomNumberList<Integer>` ✅ Allowed
- `CustomNumberList<Double>` ✅ Allowed
- `CustomNumberList<String>` ❌ **Compile Error**: `String` does not extend `Number`!

---

### 6. Wildcards in Generics (`?`)

The question mark `?` represents an **unknown type** in generic method parameters.

#### 1. Unbounded Wildcard: `<?>`
Accepts a list of any type:
```java
public static void printList(CustomGenArrayList<?> list) {
    for (int i = 0; i < list.size(); i++) {
        System.out.println(list.get(i));
    }
}
```

#### 2. Upper Bounded Wildcard: `<? extends Number>`
Accepts `Number` or any of its subclasses (`Integer`, `Double`):
```java
public static double sumOfList(CustomGenArrayList<? extends Number> list) {
    double sum = 0.0;
    for (int i = 0; i < list.size(); i++) {
        sum += list.get(i).doubleValue();
    }
    return sum;
}
```

#### 3. Lower Bounded Wildcard: `<? super Integer>`
Accepts `Integer` or any of its superclasses (`Number`, `Object`).

---

### 7. Important Rules & Restrictions of Generics:

| Restriction | Why? |
| :--- | :--- |
| **No Primitive Types** (`List<int>` ❌) | Generics require object references; primitives do not inherit from `Object`. Use wrappers (`List<Integer>`). |
| **Cannot instantiate `new T()`** | `T` is erased at runtime; JVM cannot know which constructor to invoke. |
| **Cannot declare `static` field of type `T`** | Static fields are shared by all instances of the class; `T` varies per instance. |
| **Cannot use `instanceof` on parameterized types** | `obj instanceof List<String>` fails because type arguments are erased at runtime (use `obj instanceof List<?>`). |


# Part 3: Comparing Objects (`Comparable` & `Comparator`)

### 1. The Problem: Why Can't We Use `<` or `>` on Objects?

With primitive data types, comparison is straightforward:
```java
int a = 5;
int b = 10;
System.out.println(a < b); // ✅ true
```

However, when comparing custom objects:
```java
Student kunal = new Student(12, 89.76f);
Student rahul = new Student(5, 99.52f);

// if (kunal < rahul) ❌ COMPILE ERROR! The operator < is undefined for the argument type(s) Student, Student
```

**Why?** The Java compiler has no way of knowing *what* attribute to compare. Should it compare by `rollno`, `marks`, or `name`?

To compare custom objects or sort them using `Arrays.sort()` or `Collections.sort()`, Java provides two interfaces:
1. **`Comparable<T>`** (Natural Ordering)
2. **`Comparator<T>`** (Custom / Multiple Ordering)

---

### 2. The `Comparable<T>` Interface (`java.lang`)

The `Comparable` interface is used to define the **natural / default sorting order** of an object. The class itself implements `Comparable<T>`.

#### The `compareTo()` Method:
```java
public int compareTo(T o);
```

#### The `compareTo` Contract:
| Return Value | Meaning |
| :--- | :--- |
| **Negative Integer (`< 0`)** | `this` object is **smaller** than `o` (`this` comes before `o`). |
| **Zero (`0`)** | `this` and `o` are equivalent according to this ordering. This does **not necessarily mean** `this.equals(o)` is `true`. |
| **Positive Integer (`> 0`)** | `this` object is **greater** than `o` (`this` comes after `o`). |

---

### Code Implementation (`Student.java` with `Comparable`):

```java
package Lecture6.comparing;

public class Student implements Comparable<Student> {
    int rollno;
    float marks;

    public Student(int rollno, float marks) {
        this.rollno = rollno;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" + "rollno=" + rollno + ", marks=" + marks + "}";
    }

    // Implementing natural ordering based on marks
    @Override
    public int compareTo(Student other) {
        return Float.compare(this.marks, other.marks);
    }
}
```

#### Execution & Sorting:
```java
package Lecture6.comparing;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Student kunal = new Student(12, 89.76f);
        Student rahul = new Student(5, 99.52f);
        Student arpit = new Student(2, 95.52f);
        Student sachin = new Student(13, 77.12f);

        // 1. Direct Comparison:
        if (kunal.compareTo(rahul) < 0) {
            System.out.println("Rahul scored higher than Kunal");
        }

        // 2. Sorting an Array of Objects:
        Student[] list = {kunal, rahul, arpit, sachin};

        // Arrays.sort() uses the elements' natural ordering via compareTo().
        Arrays.sort(list);

        System.out.println(Arrays.toString(list));
        // Sorted ascending: [sachin (77), kunal (89), arpit (95), rahul (99)]
    }
}
```

---

### 3. The `Comparator<T>` Interface (`java.util`)

#### Why do we need `Comparator` if we already have `Comparable`?
- **Limitation of `Comparable`**: It only provides **one single sorting order** hardcoded inside the class.
- What if you want to sort students by **marks** in one place, by **roll number** in another place, or in **descending order**?
- What if you cannot modify the source code of the class?

**Solution**: Use **`Comparator<T>`**! It allows external, custom, and multiple sorting criteria.

#### The `compare()` Method:
```java
public int compare(T o1, T o2);
```

---

### Code Examples with `Comparator`:

#### 1. Using Anonymous Class:
```java
// Sorting in descending order of marks
Arrays.sort(list, new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return (int)(o2.marks - o1.marks); // o2 - o1 for descending
    }
});
```

#### 2. Using Lambda Expression (Modern Java):
```java
// Sort ascending by marks:
Arrays.sort(list, (o1, o2) -> Float.compare(o1.marks, o2.marks));

// Sort descending by marks:
Arrays.sort(list, (o1, o2) -> (int)(o2.marks - o1.marks));

// Sort by roll number instead:
Arrays.sort(list, (o1, o2) -> Integer.compare(o1.rollno, o2.rollno));
```

---

### 4. Comparison: `Comparable` vs. `Comparator`

| Feature | `Comparable<T>` | `Comparator<T>` |
| :--- | :--- | :--- |
| **Package** | `java.lang` | `java.util` |
| **Method Name** | `public int compareTo(T o)` | `public int compare(T o1, T o2)` |
| **Parameters** | Takes **1 parameter** (compares `this` with `o`) | Takes **2 parameters** (compares `o1` with `o2`) |
| **Sorting Logic** | Single, **Natural ordering** | Multiple, **Custom orderings** |
| **Modifies Class?** | **Yes** (class must implement it) | **No** (can be defined externally) |
| **Sorting Call** | `Arrays.sort(list)` | `Arrays.sort(list, comparator)` |
| **Lambda Support** | **Yes** (it is a functional interface) | **Yes** (it is a functional interface) |

# Part 4: Lambda Functions & Functional Interfaces

### The Code in `LambdaFunctions.java`

```java
package Lecture6.generics;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LambdaFunctions {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(i + 1);
        }

        // Part 1: Built-in Consumer Functional Interface
        // arr.forEach((item) -> System.out.println(item * 2));
        Consumer<Integer> fun = (item) -> System.out.println(item * 2);
        arr.forEach(fun);

        // Part 2: Custom Functional Interface (Operation)
        Operation sum = (a, b) -> a + b;
        Operation prod = (a, b) -> a * b;
        Operation sub = (a, b) -> a - b;

        LambdaFunctions myCalculator = new LambdaFunctions();
        System.out.println(myCalculator.operate(5, 3, sum));  // 8
        System.out.println(myCalculator.operate(5, 3, prod)); // 15
        System.out.println(myCalculator.operate(5, 3, sub));  // 2
    }

    private int operate(int a, int b, Operation op) {
        return op.operation(a, b);
    }
}

interface Operation {
    int operation(int a, int b);
}
```

---

### What is Happening Step-by-Step?

#### 1. What is a Lambda Expression?
A **Lambda Expression** is essentially an **anonymous function** (a function without a name, return type, or access modifier).
* **Syntax**: `(parameters) -> { body }` or `(parameters) -> expression`
* Introduced in Java 8 to bring **functional programming** capabilities to Java.

---

#### 2. Part 1: Iterating with `forEach` & `Consumer<T>`

```java
ArrayList<Integer> arr = new ArrayList<>();
for (int i = 0; i < 5; i++) {
    arr.add(i + 1); // arr = [1, 2, 3, 4, 5]
}

Consumer<Integer> fun = (item) -> System.out.println(item * 2);
arr.forEach(fun);
```

* **`Consumer<T>`**: A built-in functional interface from `java.util.function`. It has a single abstract method:
  ```java
  void accept(T t);
  ```
  It takes an input and returns nothing (`void`).
* **The Lambda**: `(item) -> System.out.println(item * 2)` implements that `accept()` method on the fly!
* **Storing in a variable**: Notice that you can store a lambda expression in a reference variable (`Consumer<Integer> fun`).
* **`arr.forEach(fun)`**: Passes each element (`1, 2, 3, 4, 5`) to `fun` one by one, printing:
  ```text
  2
  4
  6
  8
  10
  ```

---

#### 3. Part 2: Custom Functional Interface (`Operation`)

```java
interface Operation {
    int operation(int a, int b);
}
```

* **What makes this a Functional Interface?** It has exactly **one abstract method** (`operation(int a, int b)`).
* Because it is a functional interface, a compatible lambda expression can be used where an `Operation` is expected. The compiler uses the interface's single abstract method as the lambda's target method.

---

#### 4. Part 3: Assigning Different Behaviors

```java
Operation sum = (a, b) -> a + b;
Operation prod = (a, b) -> a * b;
Operation sub = (a, b) -> a - b;
```

Here, three different lambda functions are created using the exact same interface:
* `sum`: Implements `operation(a, b)` to return `a + b`.
* `prod`: Implements `operation(a, b)` to return `a * b`.
* `sub`: Implements `operation(a, b)` to return `a - b`.

No new classes or boilerplate methods needed!

---

#### 5. Part 4: Passing Behavior as a Parameter (Higher-Order Function)

```java
private int operate(int a, int b, Operation op) {
    return op.operation(a, b);
}
```

* Look at the third parameter: `Operation op`.
* Instead of passing only data, **we are passing an object representing behavior into a method!**
* When we call:
  ```java
  myCalculator.operate(5, 3, sum);
  ```
  1. `a = 5`, `b = 3`.
  2. `op` is the `sum` lambda `(a, b) -> a + b`.
  3. `op.operation(5, 3)` executes `5 + 3`, returning **`8`**.

Similarly:
* `myCalculator.operate(5, 3, prod)` $\to 5 \times 3 =$ **`15`**
* `myCalculator.operate(5, 3, sub)` $\to 5 - 3 =$ **`2`**

---

### Output of the Program:
```text
2
4
6
8
10
8
15
2
```

---

### Summary: Why is this important?
1. **Concise Code**: Eliminates the need to write bulky anonymous inner classes (`new Operation() { public int operation(...) { ... } }`).
2. **Behavior Parameterization**: Allows methods to accept executable logic as arguments, making code modular and reusable.
3. **Core Requirement**: Lambdas in Java **only work with Functional Interfaces** (interfaces with a Single Abstract Method).

# Part 5: Comparators

## Detailed Guide: The `Comparator<T>` Interface (`comparison` package)

While **`Comparable<T>`** provides a single, hardcoded default sorting logic inside the class (`Student.java`), the **`Comparator<T>`** interface (from `java.util`) allows you to define **multiple, flexible, external sorting strategies** without altering the original class.

---

### 1. Why Do We Need `Comparator`?

Consider our `Student` class:
* In `Student.java`, `compareTo()` sorts students by **marks**.
* **What if you need to:**
  1. Sort by **roll number** in one part of your app, but by **marks** in another?
  2. Sort in **descending order** (highest marks first)?
  3. Sort objects from a **third-party library** whose source code you cannot modify?

> **`Comparable` = Natural / Default ordering (Single order, inside the class)**  
> **`Comparator` = Custom / Flexible ordering (Multiple orders, outside the class)**

---

### 2. The `Comparator<T>` Contract

`Comparator<T>` is a **Functional Interface** located in `java.util`. It has a single abstract method:

```java
int compare(T o1, T o2);
```

#### Return Value Rules:
| Return Value | Meaning | Sorting Effect (Ascending) |
| :--- | :--- | :--- |
| **Negative (`< 0`)** | `o1` is smaller than `o2` | `o1` placed **before** `o2` |
| **Zero (`0`)** | `o1` is equivalent to `o2` according to the comparator | They compare as tied; a stable sorting algorithm preserves their relative order |
| **Positive (`> 0`)** | `o1` is greater than `o2` | `o1` placed **after** `o2` |

---

### 3. Code Implementation (`Student.java` & `Main.java`)

#### The Base Class (`Student.java`)
```java
package Lecture6.generics.comparison;

public class Student implements Comparable<Student> {
    int rollno;
    float marks;

    public Student(int rollno, float marks) {
        this.rollno = rollno;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" + "rollno=" + rollno + ", marks=" + marks + "}";
    }

    // Default natural ordering by marks
    @Override
    public int compareTo(Student o) {
        return Float.compare(this.marks, o.marks);
    }
}
```

---

#### The Execution Class: 4 Ways to Use `Comparator` (`Main.java`)

```java
package Lecture6.generics.comparison;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Student kunal  = new Student(12, 89.76f);
        Student rahul  = new Student(5,  99.52f);
        Student arpit  = new Student(2,  95.52f);
        Student karan  = new Student(13, 77.52f);
        Student sachin = new Student(24, 65.52f);

        Student[] list = {kunal, rahul, arpit, karan, sachin};

        // -------------------------------------------------------------
        // Approach 1: Anonymous Inner Class (Pre-Java 8 style)
        // -------------------------------------------------------------
        Arrays.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Float.compare(o1.marks, o2.marks); // Ascending by marks
            }
        });
        System.out.println("Ascending by marks (Anonymous class):");
        System.out.println(Arrays.toString(list));

        // -------------------------------------------------------------
        // Approach 2: Lambda Expression (Ascending by marks)
        // -------------------------------------------------------------
        // Since Comparator has only ONE abstract method (compare), we can use a lambda:
        Arrays.sort(list, (o1, o2) -> Float.compare(o1.marks, o2.marks));
        System.out.println("\nAscending by marks (Lambda):");
        System.out.println(Arrays.toString(list));

        // -------------------------------------------------------------
        // Approach 3: Descending Order (Highest marks first)
        // -------------------------------------------------------------
        // Negating the difference -(o1 - o2) OR swapping operands (o2 - o1):
        Arrays.sort(list, (o1, o2) -> Float.compare(o2.marks, o1.marks));
        // Equivalent descending form: Arrays.sort(list, (o1, o2) -> -Float.compare(o1.marks, o2.marks));
        System.out.println("\nDescending by marks:");
        System.out.println(Arrays.toString(list));

        // -------------------------------------------------------------
        // Approach 4: Custom Sorting by Roll Number (Different Attribute)
        // -------------------------------------------------------------
        // Student class compareTo sorts by marks, but we can override this externally:
        Arrays.sort(list, (o1, o2) -> Integer.compare(o1.rollno, o2.rollno));
        System.out.println("\nAscending by Roll Number:");
        System.out.println(Arrays.toString(list));

        // -------------------------------------------------------------
        // Approach 5: Modern Java 8+ Comparator Methods
        // -------------------------------------------------------------
        Arrays.sort(list, Comparator.comparingInt(s -> s.rollno));
    }
}
```

---

### 4. Output of `Main.java`

```text
Ascending by marks (Lambda):
[Student{rollno=24, marks=65.52}, Student{rollno=13, marks=77.52}, Student{rollno=12, marks=89.76}, Student{rollno=2, marks=95.52}, Student{rollno=5, marks=99.52}]

Descending by marks:
[Student{rollno=5, marks=99.52}, Student{rollno=2, marks=95.52}, Student{rollno=12, marks=89.76}, Student{rollno=13, marks=77.52}, Student{rollno=24, marks=65.52}]

Ascending by Roll Number:
[Student{rollno=2, marks=95.52}, Student{rollno=5, marks=99.52}, Student{rollno=12, marks=89.76}, Student{rollno=13, marks=77.52}, Student{rollno=24, marks=65.52}]
```

---

### 5. Floating-Point Precision Warning in `(int)(o1.marks - o2.marks)`

> [!WARNING]
> Avoid implementing a comparator by subtracting floating-point values and casting the result to `int`.
> For example, if `o1.marks = 89.9f` and `o2.marks = 89.2f`, the difference is about `0.7f`; casting it to `int` produces `0`, incorrectly treating the values as tied.
>
> **Best Practice for Floats/Doubles:**
> Use `Float.compare()` or `Double.compare()`:
> ```java
> Arrays.sort(list, (o1, o2) -> Float.compare(o1.marks, o2.marks));
> ```

---

### 6. Quick Cheat-Sheet: `Comparable` vs. `Comparator`

| Feature | `Comparable<T>` | `Comparator<T>` |
| :--- | :--- | :--- |
| **Package** | `java.lang` (no import needed) | `java.util` (must import) |
| **Method** | `compareTo(T o)` (1 parameter) | `compare(T o1, T o2)` (2 parameters) |
| **Sorting Criteria** | Only **1 default strategy** | **Multiple independent strategies** |
| **Source Modification** | Must edit the class source code | No need to touch the class source code |
| **Calling Syntax** | `Arrays.sort(list);` | `Arrays.sort(list, comparator);` |
| **Lambda Support** | ❌ No | ✅ Yes (`(o1, o2) -> ...`) |


---

## Verification Note

This file currently contains material on amortized analysis, dynamic arrays, generics, `Comparable`, `Comparator`, lambdas, and functional interfaces. Despite the filename `generics_exceptionHandling.md`, **no exception-handling section is present in the supplied content**, so no exception-handling material has been invented or added here.
