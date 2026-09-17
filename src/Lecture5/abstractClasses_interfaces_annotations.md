# 1. Abstract Classes

Abstract classes are classes that cannot be instantiated and are meant to be subclassed. They can contain both abstract methods (methods without implementation) and concrete methods (methods with implementation).

The Parent class is only going to give a generalized form of it, not the body of the method. Such a class will determine the nature of the methods the subclass/child class must implement. The parent class is just giving the definition of the method, not the body of the method. **These are known as abstract methods.** 

Now when a function does not have a body, it totally depends on the child class to implement it. The child class is going to give the body of that function.

* This is done by **overriding**. We have to make sure the child class overrides all the methods. These are the methods that are defined in the parent class but not implemented. 

    If we do not override all the methods, then the child class will also become an abstract class, and inorder to used them the child class must override it.

**NOTE:** Any class that has at least one abstract method must also be declared as an abstract class.