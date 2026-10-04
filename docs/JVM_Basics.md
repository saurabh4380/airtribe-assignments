### What is JDK, JRE, JVM?

JDK (Java Development Kit), JRE (Java Runtime Environment), and JVM (Java Virtual Machine) are the three core components of the Java platform architecture. They work together to allow you to write, compile, and run Java applications.
The easiest way to understand them is as a nested hierarchy: JDK contains JRE, and JRE contains JVM.

```text
+-------------------------------------------------------------+

| JDK (Java Development Kit)                                  |
|   - Development Tools (javac, jdb, javadoc, jar)            |
|                                                             |
|   +-----------------------------------------------------+   |
|   | JRE (Java Runtime Environment)                      |   |
|   |   - Core Class Libraries (java.lang, java.util)     |   |
|   |                                                     |   |
|   |   +---------------------------------------------+   |   |
|   |   | JVM (Java Virtual Machine)                  |   |   |
|   |   |   - Execution Engine                        |   |   |
|   |   |   - Memory Management & Garbage Collector   |   |   |
|   |   +---------------------------------------------+   |   |
|   +-----------------------------------------------------+   |
+-------------------------------------------------------------+
```

### What is bytecode?

Bytecode is the intermediate, machine-readable code produced by compiling a Java program. It serves as the bridge between your human-readable Java source code and the physical hardware of a computer.
When you write a Java program, it is saved as a .java file. When you compile it, the Java compiler (javac) translates it into a .class file, which contains the bytecode.

### What does “write once, run anywhere” mean?

It means that a developer can write and compile Java code exactly once on one operating system (like Windows), and that same compiled code will run perfectly on any other device (like a Mac, Linux server, smartphone, or smart appliance) without needing any modifications or recompilation.