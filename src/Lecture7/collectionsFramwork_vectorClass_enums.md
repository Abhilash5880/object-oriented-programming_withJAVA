

## Part 1: The Java Collections Framework

The **Collections Framework** (`java.util`) is a standardized, unified architecture for storing, manipulating, and accessing groups of objects.

### 1. The Core Hierarchy Diagram

```
                           ┌──────────────────┐
                           │   Iterable<T>    │ (Root of Collection Hierarchy)
                           └────────┬─────────┘
                                    │
                           ┌────────▼─────────┐
                           │  Collection<T>   │
                           └────────┬─────────┘
         ┌──────────────────────────┼──────────────────────────┐
         ▼                          ▼                          ▼
     List<T>                     Queue<T>                   Set<T>
 (Ordered, Duplicates)       (FIFO / Processing)      (Unique, No Duplicates)
   ├── ArrayList               ├── PriorityQueue        ├── HashSet
   ├── LinkedList              ├── ArrayDeque           ├── LinkedHashSet
   ├── Vector                  └── LinkedList           └── TreeSet (SortedSet)
   └── Stack
```

> [!NOTE]
> **What about `Map<K, V>`?**  
> `Map` (`HashMap`, `TreeMap`, `LinkedHashMap`) is part of the Java Collections Framework, but it **does not extend `Collection` or `Iterable`** because it stores key-value pairs, not standalone elements.

---

### 2. The Big 3 Interfaces: `List`, `Set`, and `Queue`

| Interface | Duplicates Allowed? | Order Maintained? | Positional Index Access? | Key Implementations |
| :--- | :---: | :---: | :---: | :--- |
| **`List`** | ✅ **Yes** | ✅ **Insertion Order** | ✅ **Yes** (`get(i)`, `set(i)`) | `ArrayList`, `LinkedList`, `Vector`, `Stack` |
| **`Set`** | ❌ **No** | ❌ Unordered (`HashSet`) / Sorted (`TreeSet`) | ❌ **No** | `HashSet`, `LinkedHashSet`, `TreeSet` |
| **`Queue`** | ✅ **Yes** | ✅ **FIFO / Priority** | ❌ **No** | `PriorityQueue`, `ArrayDeque`, `LinkedList` |

---

### 3. `ArrayList` vs. `LinkedList`

```java
List<Integer> arrayList = new ArrayList<>();
List<Integer> linkedList = new LinkedList<>();
```

| Feature | `ArrayList` | `LinkedList` |
| :--- | :--- | :--- |
| **Underlying Data Structure** | Resizable Dynamic Array | Doubly Linked List (Nodes with `prev` & `next`) |
| **Memory Allocation** | Contiguous blocks in memory | Scattered nodes connected via pointers |
| **Random Access (`get(i)`)** | **$\mathcal{O}(1)$ Constant time** (very fast) | **$\mathcal{O}(N)$ Linear time** (must traverse from head/tail) |
| **Insertion / Deletion in middle**| **$\mathcal{O}(N)$ Slow** (requires element shifting) | **$\mathcal{O}(1)$ Fast** (once position is reached, just update pointers) |
| **Best Used When** | Frequent reads / searches, rare modifications | Frequent insertions/deletions in the middle/beginning |

---

## Part 2: The `Vector` Class (`java.util.Vector`)

The **`Vector`** class is a legacy class introduced in Java 1.0 (later retrofitted in Java 2 to implement `List`). Like `ArrayList`, it implements a resizable dynamic array.

### 1. Code Example:
```java
package Lecture7;

import java.util.List;
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        List<Integer> vector = new Vector<>();
        vector.add(45);
        vector.add(5);
        vector.add(15);
        vector.add(56);

        System.out.println(vector); // [45, 5, 15, 56]
    }
}
```

---

### 2. `Vector` vs. `ArrayList` (Classic Interview Question)

| Feature | `Vector` | `ArrayList` |
| :--- | :--- | :--- |
| **Introduced In** | Java 1.0 (Legacy Class) | Java 1.2 (Modern Collection) |
| **Synchronization** | **Synchronized** (Thread-safe) | **Non-Synchronized** (Not thread-safe) |
| **Performance** | **Slower** due to thread locking/synchronization overhead | **Much faster** for single-threaded or modern concurrent workflows |
| **Growth Strategy** | Doubles its capacity (**$100\%$ increase / $2\times$**) | Grows by **$50\%$ ($1.5\times$, `old + old >> 1`)** |
| **Recommendation** | Avoid in modern Java; use `ArrayList`. | Preferred standard choice for dynamic lists. |

> [!TIP]
> If you need thread safety with `ArrayList`, use:
> ```java
> List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());
> ```
> Or in concurrent multithreaded environments: `CopyOnWriteArrayList`.

---

## Part 3: Enums in Java (`Enumerations`)

An **`enum`** (short for enumeration) is a special data type used to define a collection of **fixed, named constants** (e.g., days of the week, compass directions, order statuses).

---

### 1. Why Use Enums Instead of `final static int` or `String`?
- **Type Safety**: Prevents passing invalid values. If a method expects a `Week` enum, you cannot accidentally pass `"Funday"` or the number `99`.
- **Readability & Maintainability**: Grouped constants are self-documenting.

---

### 2. Code Implementation: `A.java`

```java
package Lecture7;

public class A {

    // Defining an Enum inside a class (can also be in its own file)
    enum Week {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
        // 1. These are enum constants.
        // 2. Each constant is implicitly: public static final Week Monday = new Week();
        // 3. Since they are final, you cannot create child enums.

        // Constructor in Enum
        Week() {
            System.out.println("Constructor called for: " + this);
        }
    }

    public static void main(String[] args) {
        // Accessing a constant
        Week day = Week.Monday;

        // 1. Using in a switch statement
        switch (day) {
            case Monday -> System.out.println("Start of work week!");
            case Friday -> System.out.println("Weekend is near!");
            default -> System.out.println("Regular workday.");
        }

        // 2. Built-in method: values() -> returns array of all constants
        System.out.println("\nIterating through all enum constants:");
        for (Week w : Week.values()) {
            // ordinal(): zero-based index of the constant
            System.out.println(w + " at index: " + w.ordinal());
        }

        // 3. Built-in method: valueOf(String) -> converts String to Enum
        Week parsedDay = Week.valueOf("Tuesday");
        System.out.println("\nParsed Enum: " + parsedDay);
    }
}
```

#### Output:
```text
Constructor called for: Monday
Constructor called for: Tuesday
Constructor called for: Wednesday
Constructor called for: Thursday
Constructor called for: Friday
Constructor called for: Saturday
Constructor called for: Sunday
Start of work week!

Iterating through all enum constants:
Monday at index: 0
Tuesday at index: 1
Wednesday at index: 2
Thursday at index: 3
Friday at index: 4
Saturday at index: 5
Sunday at index: 6

Parsed Enum: Tuesday
```

---

### 3. Deep Dive into Java Enum Internals

1. **How Enum Constants Work**:
   Every enum constant (`Monday`, `Tuesday`) is an **instance/object** of type `Week`.
   Under the hood, the compiler translates:
   ```java
   enum Week { Monday, Tuesday }
   ```
   into:
   ```java
   public final class Week extends java.lang.Enum<Week> {
       public static final Week Monday = new Week();
       public static final Week Tuesday = new Week();
   }
   ```

2. **Enum Constructors**:
   - Enum constructors **cannot be `public` or `protected`**. They are implicitly `private` or package-private.
   - You **cannot invoke an enum constructor directly** with `new Week()` ❌.
   - The constructor is executed automatically once for **every constant** when the enum is loaded.

3. **Inheritance Rules for Enums**:
   - Every enum in Java implicitly extends **`java.lang.Enum`**.
   - Because Java does not support multiple class inheritance, an enum **cannot extend any other class**.
   - However, an enum **CAN implement interfaces**:
     ```java
     interface Displayable {
         void display();
     }

     enum Week implements Displayable {
         Monday, Tuesday;

         @Override
         public void display() {
             System.out.println("This is day: " + this);
         }
     }
     ```

---

### 4. Built-in Methods of `Enum`

| Method | Return Type | Description |
| :--- | :--- | :--- |
| **`values()`** | `Enum[]` | Returns an array containing all constants in the order declared. |
| **`ordinal()`** | `int` | Returns the ordinal/position index of the constant (starts at `0`). |
| **`valueOf(String name)`** | `Enum` | Returns the enum constant matching the exact string name. |
| **`name()`** | `String` | Returns the exact declared name of the constant. |
| **`compareTo(Enum o)`** | `int` | Compares two enum constants based on their ordinal positions. |
