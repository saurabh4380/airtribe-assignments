#### Why you used ArrayList instead of array?

ArrayList has a dynamic size which is better suited for storing the entities in-memory.

#### Where you used static members and why?

The `ArrayList<Student>`, `ArrayList<Course>` & `ArrayList<Enrollment>` are static in the repository classes as its a in-memory store. Making it static means the list can be shared across the application. 

#### Why used Maven as a build tool?

Although this is a simple console based application, Maven is used as a build tool so that the project can be opened and worked upon in any IDE without any extra configuration steps.


#### Why use AtomicInteger over int for generating IDs in `IdGenerator.java`?

It helps in multithreading scenarios and has a good `getAndIncrement()` api available.