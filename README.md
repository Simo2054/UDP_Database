<p align="center">
    <a href="https://github.com/Simo2054/UDP_Database">
    <img src="images/Logo.png" alt="Logo" width="300" height="300">
    </a>
</p>

<p align="center">
    A demonstrative client-server application for sending and executing 
    SQL queries over UDP, using a built-in SQL manager on the server side
    <br />
</p>

<!-- Table of contents -->
<details>
    <summary>Table of contents</summary>
    <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#sources">Sources</a></li>
    </ol>
</details>

<!-- About the project -->
## About the project

UDP Database is a demonstrative client-server application that showcases how SQL queries can be transmitted and executed over the UDP protocol. 

Built for educational and experimental purposes, the project illustrates lightweight database interaction. It features a simple client interface for composing SQL queries and a server-side with a SQL manager that handles execution.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Java][Java-badge]][Java-url]
* [![PostgreSQL][Postgres-badge]][Postgres-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting started

In order to get a local copy up and running follow these simple example steps.

### Prerequisities

You would first need to install a Java JDK. You can do that by navigating to [https://jdk.java.net/](https://jdk.java.net/) and choosing the one you want and according to your machine, and then set it up locally. In this project, I used version 23.0.2.

You can check if it works by opening a terminal and typing
```sh
javac --version
```

Second step is installing a preferred Database Management System and set it up on your system. In this application, I used PostgreSQL. 

Third step is downloading a JDBC Driver of your preferred Database Management System. In this project, I used `postgresql-42.7.5.jar`

Now You should be all set for installation :smile:

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Simo2054/UDP_Database
    ```
2. Change git remote URL to avoid accidental pushes to base project
    ```sh
    git remote set-url origin github_username/repo_name
    git remote -v # confirm the changes
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

In order for you to use the application, you need to place the JDBC Driver inside the root of the project.
It should look something like this:
[![Structure Screen Shot][structure-screenshot]]

Second step is modifying this line in SQL/SQLManager.java according to the structure above
```sh
private static final String DB_URL = "jdbc:postgresql://localhost:5432/rcldb";
``` 

Now all that's left is compiling the application :smile:
⚠️ Replace `"postgresql-42.7.5.jar"` with your JDBC Driver
```sh
javac -cp "postgresql-42.7.5.jar" application/*.java SQL/*.java
```

and run the application:
```sh
java -cp "postgresql-42.7.5.jar" application/Main.java 
```

You will be prompted to type "s" or "c", where "s" stands for "server" and "c" stands for client.
Your default is localhost, but you can run the server on another computer if you want and connect your client to the server by typing 
```sh
c <other computer IP address>
```
for example:
```sh
c 192.168.1.0
```

⚠️ If you decide to run the server on another computer, you will also need the JDBC Driver and the SQLManager.java

All set! ✨💫⭐️

Now you can start typing queries into your database :smile:

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Sources

Some inspiration sources I took as examples in the process of implementing this application:

[README template](https://github.com/othneildrew/Best-README-Template)
[Establishing JDBC Connection](https://www.geeksforgeeks.org/establishing-jdbc-connection-in-java/)
[Installing and setting up PostgreSQL](https://www.w3schools.com/postgresql/postgresql_install.php)
[UDP Application Model](https://github.com/aclblaj/bsp-rn)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- Markdown links -->
[Java-badge]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/

[Postgres-badge]: https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white
[Postgres-url]: https://www.postgresql.org/

[structure-screenshot]: images/Screenshot1.jpg
