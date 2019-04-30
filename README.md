# HAI! CIS_454_Project 2
# 2nd Group Project, Group 4

HAI, Homeowners Assistant for Insurance

## Members:
<pre>
Nicholas Robson - nlrobson@syr.edu                  Jon Prishvalko - jgprishv@syr.edu
Curtis Oliver - cuoliver@syr.edu                    Matt Hillebrand - mchilleb@syr.edu
Meagan Sanchez - mnsanche@syr.edu
</pre>

## Getting Started:
These instructions will help with setting up your development environment so you can properly run this program.

### Intellij is required:
Intellij can be installed on either Windows, macOS, or Linux; however, our software currently ONLY functions in the
Windows operating environment.

https://www.jetbrains.com/idea/download/#section=windows

You will also need to install the JDBC plugin, located in 'Settings' -> 'Plugins'

### Setting Intellij Porject,  Project dependencies as well as Module Paths and Dependencies:
1. In Intellij, select to open Project, and open CIS_454_Project2 folder.
2. Select File -> Project Structure (See fig 1):
    1. Select Java 1.8 JDK. (If this isn't an option, you need to install Java 1.8 JDK)
    2. Select Project Language Level to SDK Default (8 - Lambdas ...)
    3. Set the Compiler output folder the CIS_454_Project2/out folder
3. Set source folders (See fig 2):
    1. out -> set to Exclude
    2. src -> set to Sources
    3. test -> set to Tests
    4. thirdparty -> set to Resources
4. Import the dependancies from the Thirdparty folder (See fig 3):
    1. junit-4.13-beta-2.jar
    2. hamcrest-core-2.1.jar
    3. mssql-jdbc-7.2.1.jr8.jar
    4. commons-csv-1.6.jar
    5. guava-23.0.jar
    6. import jar_files folder as a whole

Figure 1:
![alt text][f1]
[f1]: https://github.com/nlrobson/CIS_454_Project2/blob/master/HAI_ProjectSettings.PNG "Project Settings"


Figure 2:
![alt text][f2]
[f2]: https://github.com/nlrobson/CIS_454_Project2/blob/master/HAI_ModuleSources.PNG "Module Sources"


Figure f3:
![alt text][f3]
[f3]: https://github.com/nlrobson/CIS_454_Project2/blob/master/HAI_ModuleDependencies.PNG "Module Dependencies"



### Running HAI!
Once the Java and Intellij environment is setup:
1. Build the project
..* CTRL + F9 (or select Build -> Build Project)
2. Run the project
..* Shift + F10 to start the run.

The default user login and password are:
username: cmuney13@gmail.com
password: password

## Interacting with HAI!
### Main Menu
After successful login, the user has the following options:
1. View Profile: User can view and manage their profile
2. View Items: User can view their item list
3. LOGOUT: User can logout from the server.
4. Add Items: User can go to directly add new items

### Items Menu
User can view their list of items. Images and receipts are displayed on the right.
User can add new items with the add button and delete a single line or multiple lines by selecting
these lines and clicking delete.

### Add items
A user can add a single item by pressing Add Item or can add multiple items by pressing Insert and Add New.

## Misc Details:
1. This is a Java based application that utilizes JavaFX for the GUI.
..*Due to some complications with compatibility, HAI! currently is only compatible with Windows.
2. We integrated Guava EventBus to enable us to efficiently use multi-threaded functions to keep UI thread open.
..*[https://github.com/google/guava/wiki/EventBusExplained]
3. We use AWS in conjunction with S3 for storage of images and item lists.
4. Our recommender algorithm is currently based on finding median values for similar items uploaded to our system.

**NOTE: The Main.java is located in src/userinterface/**
**Each JavaFXML file has a corresponding controller.java class that controls the functionality**

[f1]: https://github.com/nlrobson/CIS_454_Project2/HAI_ProjectSettings.PNG
[f2]: https://github.com/nlrobson/CIS_454_Project2/HAI_ModuleSources.PNG
[f3]: https://github.com/nlrobson/CIS_454_Project2/HAI_ModuleDependencies.PNG
