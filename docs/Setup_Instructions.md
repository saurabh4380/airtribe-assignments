



## Development Environment Setup

#### JDK Installation Steps (Windows 10+):

1. Install Chocolatey pacakage manager if not installed already using this guide. [Chocolatey Installation Guide](https://chocolatey.org/install)
2. Run the following command in an elevated command line interface (run as Administrator) to install JDK:  
`choco install openjdk.  

This will download and install the JDK and automatically configure `JAVA_HOME` environment variable, and updates your system `PATH`.

 To verify the installation run the following command in a seperate terminal,  
`java --version`

*Why install using Chocolatey?*

*Downloading and installing OpenJDK using Chocolatey has less number of manual steps as compared to the usual way of downloading and installing JDK using Oracle download page and editing the environment variables manually.*

On MacOS and Linux machines use [this](https://www.geeksforgeeks.org/java/download-and-install-jdk-on-windows-mac-and-linux/) guide.

#### IDE Setup

1. Downlaod and install VS Code from [here](https://code.visualstudio.com/download).
2. Once the installation is completed then install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)


*Note: If you prefer IntelliJ IDEA over VS Code then download and install it from [here](https://www.jetbrains.com/idea/).*

#### “Hello World” program

1. Create a file called App.java with following code,
```java
public class App
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
}
```

2. Open a terminal in the folder containing the above file (App.java) and run following command to compile the program.

```bash
javac App.java
```

`javac` compiles the program into a `class` file which contains the Java Bytecode. THe class file is platform-independent and can be executed by the JVM. 

```bash
java App
```

`java` command loads the compiled code (bytecode) and executed the program using JVM.

[YourCode.java]  -->  (Compiled via 'javac')  -->  [YourCode.class]  -->  (Executed via 'java')  -->  [JVM / Machine Code]







