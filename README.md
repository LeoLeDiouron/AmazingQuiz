# AmazingQuiz

Quiz project with JAVA

## How to install the project

### PART I : Install PostgreSQL and run the sql script

To use the project, you need to host by yourself a PostgreSQL database.

First, you need to download PSQL here : https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Next, open a terminal in the root folder of the project, and run this command :
```psql -U postgres -f init_database.sql```

### PART II : Install the project with IntelliJ

Open the project in IntelliJ and press ctrl + alt + shift + s to open the project options :
- Define the JDK (a java version 10 at least is needed)
- Define the path of your project compiler output (select the path of your project and add "\out")
- Define the level of language at 7
- Go in the "module" section
- Select the "src" folder
- Mark it as "sources"

Now, you need to add a build configuration :
- Click on "add configuration"
- Click on "+"
- Add a Main class (the path of the main is "src/app/main")
- Add a working directory (the path of your project)
- Add a classpath of module (AmazingQuiz)

### PART III : Install JDBC with IntelliJ

Download the JDBC driver here : https://jdbc.postgresql.org/download.html (select the version "PostgreSQL JDBC 4.2 Driver, 42.2.5")

To put the downloaded file "postgresql-42.2.5" in the project :
- Press ctrl + alt + shift + s to open the project options
- Go in the "library" section
- Click on "+"
- Click on "java"
- Select the file "postgresql-42.2.5"
- Click on "ok"

### PART III : Run the project

Click on "Run" and enjoy :)