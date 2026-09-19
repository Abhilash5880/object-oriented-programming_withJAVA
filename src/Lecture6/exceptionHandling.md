# Exception Handling in Java

An **Exception** is an abnormal event or condition that occurs during the execution of a program, disrupting the normal flow of instructions.

**Exception Handling** is a mechanism in Java to handle runtime errors so that the normal flow of the application can be maintained (graceful degradation instead of an abrupt program crash).



## 1. Exception Hierarchy in Java

Every error and exception class in Java is a descendant of the **`Throwable`** class (`java.lang.Throwable`), which directly extends `Object`.

```
                         ┌──────────────────┐
                         │ java.lang.Object │
                         └────────┬─────────┘
                                  │
                         ┌────────▼─────────┐
                         │    Throwable     │
                         └────────┬─────────┘
                                  │
               ┌──────────────────┴──────────────────┐
               ▼                                     ▼
           Exception                               Error
               │                         (OutOfMemoryError,
               │                          StackOverflowError,
               │                          VirtualMachineError)
      ┌────────┴────────┐
      ▼                 ▼
   Checked           Unchecked (Runtime)
  Exceptions         Exceptions (`RuntimeException`)
(IOException,       (ArithmeticException,
 SQLException,       NullPointerException,
 ClassNotFound)      ArrayIndexOutOfBoundsException)

```

## 2. Error vs. Exception

| Feature | Error (`java.lang.Error`) | Exception (`java.lang.Exception`) |
| :--- | :--- | :--- |
| **Origin** | Caused by the environment / system / JVM. | Caused by programmer code or unexpected input. |
| **Recoverable?** | **Irrecoverable** (the application should terminate). | **Recoverable** using `try-catch` blocks. |
| **Type** | Always **Unchecked**. | Can be **Checked** or **Unchecked**. |
| **Examples** | `StackOverflowError`, `OutOfMemoryError` | `ArithmeticException`, `NullPointerException` |

---

## 3. Checked vs. Unchecked Exceptions

### 1. Checked Exceptions (Compile-Time Exceptions)
- Classes that directly extend `Exception` (except `RuntimeException`).
- The compiler **checks and forces** you to handle them at compile-time (using `try-catch` or declaring with `throws`). If unhandled, the code will not compile.
- **Examples**: `IOException`, `FileNotFoundException`, `SQLException`, `ClassNotFoundException`.

### 2. Unchecked Exceptions (Runtime Exceptions)
- Classes that extend **`RuntimeException`**.
- Occur at **runtime**; usually caused by logical flaws or bad coding.
- The compiler does **not** force you to catch or declare them.
- **Examples**:
  - `ArithmeticException`: Dividing an integer by zero (`5 / 0`).
  - `NullPointerException`: Accessing methods or fields of a `null` reference.
  - `ArrayIndexOutOfBoundsException`: Accessing an index outside `0` to `array.length - 1`.
  - `ClassCastException`: Invalid type casting.
  - `IllegalArgumentException`: Passing an illegal argument to a method.



## 4. The 5 Core Keywords

Java provides **5 keywords** for managing exceptions:

| Keyword | Purpose |
| :--- | :--- |
| **`try`** | Wraps the block of code that might throw an exception. |
| **`catch`** | Catches and handles the exception thrown by the `try` block. |
| **`finally`** | Block of code that **always executes**, whether an exception occurs or not (used for cleanup). |
| **`throw`** | Used to **explicitly throw** an exception instance. |
| **`throws`** | Used in a **method declaration** to declare exceptions that the method might throw. |

---

## 5. Code Implementation: `try`, `catch`, `finally`, `throw`, `throws`

### File 1: Standard Exception Handling (`Main.java`)
```java
package Lecture6.exceptionHandling;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 0;

        try {
            // 1. Calling a method that declares 'throws'
            divide(a, b);

            // 2. Explicitly throwing an exception with 'throw'
            // throw new Exception("just for fun");
        } 
        // Specific catch block must come FIRST
        catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } 
        // Generic catch block must come LAST
        catch (Exception e) {
            System.out.println("Caught General Exception: " + e.getMessage());
        } 
        // Finally block always runs
        finally {
            System.out.println("Finally block will always execute!");
        }

        System.out.println("Program continues normally...");
    }

    // 'throws' keyword: declares that this method may throw an ArithmeticException
    static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            // 'throw' keyword: explicitly instantiates and throws the exception
            throw new ArithmeticException("Please do not divide by zero!");
        }
        return a / b;
    }
}
```

#### Output:
```text
Caught ArithmeticException: Please do not divide by zero!
Finally block will always execute!
Program continues normally...
```

---

## 6. Creating Custom Exceptions (`MyException.java`)

You can create your own application-specific exceptions by extending `Exception` (for a checked exception) or `RuntimeException` (for an unchecked exception).

### File 2: `MyException.java`
```java
package Lecture6.exceptionHandling;

// Custom Checked Exception
public class MyException extends Exception {
    public MyException(String message) {
        super(message); // Passes message to Exception superclass
    }
}
```

### Using Custom Exception:
```java
public class Demo {
    public static void main(String[] args) {
        try {
            String name = "kunal";
            if (name.equals("kunal")) {
                throw new MyException("Name cannot be kunal!");
            }
        } catch (MyException e) {
            System.out.println("Custom Exception caught: " + e.getMessage());
        }
    }
}
```

---

## 7. Crucial Rules & Interview Questions

### 1. Order of Multiple `catch` Blocks
- Subclass exceptions **must be caught before superclass exceptions**.
- If you place `catch (Exception e)` above `catch (ArithmeticException e)`, the compiler throws an error:
  > *"Unreachable catch block for ArithmeticException. It is already handled by the catch block for Exception."*

### 2. Does the `finally` block execute if there is a `return` statement?
- **YES!** Even if the `try` or `catch` block executes a `return` statement, the `finally` block **will still run right before** the method actually returns to the caller.
- **The only scenario where `finally` will NOT execute**:
  - If you invoke `System.exit(0);` (terminates the JVM process immediately).
  - A fatal JVM crash or catastrophic power failure.

### 3. Valid Block Combinations
- `try` **cannot stand alone**.
- Valid combinations:
  - `try` + `catch`
  - `try` + `finally`
  - `try` + multiple `catch` + `finally`

### 4. `throw` vs. `throws` Comparison

| Feature | `throw` | `throws` |
| :--- | :--- | :--- |
| **Purpose** | Used to **explicitly throw** an exception instance. | Used to **declare** that a method may throw exceptions. |
| **Location** | Inside the **method body**. | In the **method signature**. |
| **Syntax** | Followed by an **object/instance**: `throw new Exception();` | Followed by **class name(s)**: `throws IOException, SQLException` |
| **Quantity** | Can only throw **one exception instance** at a time. | Can declare **multiple exception classes** separated by commas. |

---

## 8. Modern Java: Try-with-Resources (Java 7+)

When working with resources that must be closed (such as `Scanner`, `FileInputStream`, `BufferedReader`), **Try-with-Resources** automatically closes them at the end of the statement without needing a verbose `finally` block.

```java
import java.util.Scanner;

public class ResourceDemo {
    public static void main(String[] args) {
        // Any class implementing java.lang.AutoCloseable can be used inside try(...)
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            System.out.println("Number: " + num);
        } // sc.close() is automatically called here, even if an exception occurs!
    }
}
```
```