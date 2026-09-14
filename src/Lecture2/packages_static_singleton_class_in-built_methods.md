# 1. Packages
* A package in Java is a way of organizing classes and interfaces into a logical group. It helps in avoiding naming conflicts and provides access control.
* Packages are used to create a namespace for the classes and interfaces they contain.
* The `java.lang` package is automatically imported into every Java program, so its classes can be used without explicit import statements.
* To use a class from another package, you need to import it using the `import` statement.
* You can also use the fully qualified name of a class to avoid importing it.

## How to create a package
* To create a package, you need to use the `package` keyword at the top of your Java source file, followed by the package name. For example:
```java
package com.example.myapp;
```
* The package name should follow the reverse domain name convention to ensure uniqueness.

## Usecases of packages
* **Organizing code**: Packages help in organizing related classes and interfaces into a single unit, making the codebase easier to manage and understand.
* **Avoiding naming conflicts**: By using packages, you can have classes with the same name in different packages without any conflict.
* **Access control**: Packages provide a way to control access to classes and members. Classes in the same package can access each other's package-private members, while classes in different packages cannot.

### for packages examples-
* check `Main.class` in `Lecture1` folder
* check `TestPackage.class` in `Lecture2` folder

# 2. Static 
* Object independent methods and variables are called static methods and variables. They can be accessed without creating an instance of the class.

* Static variables are shared among all instances of a class, meaning that if one instance changes the value of a static variable, it will be reflected in all other instances.

* Refer to `human.java` in `Lecture2/staticExample` folder for example of static variable.