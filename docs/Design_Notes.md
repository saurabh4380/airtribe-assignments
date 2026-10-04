#### Why you used ArrayList instead of array?

ArrayList has a dynamic size which is better suited for storing the entities in-memory.

#### Where you used static members and why?

The `ArrayList<Student>`, `ArrayList<Course>` & `ArrayList<Enrollment>` are static in the repository classes as its a in-memory store. Making it static means the list can be shared across the application. 
