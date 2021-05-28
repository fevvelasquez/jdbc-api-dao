# Java JDBC API basis: DAO-CRUD.

![Java JDBC API](https://img.shields.io/badge/JAVA%20SE%2011-JDBC%20API%20|%20Generics%20|%20Interfaces%20|%20Logger%20|%20ResourceBundle%20|%20Javadoc-indianred?style=flat&logo=java)
![MySQL RDBMS](https://img.shields.io/badge/MySQL-%20DQL%20|%20DDL%20|%20DML%20-steelblue?style=flat&logo=mysql&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-%20DAO%20|%20Singleton%20|%20Factory%20|%20Immutable%20-paleturquoise?style=flat&logo=java&logoColor=white)

## ( *jdbc-api-dao-crud* ) ![v0.2](https://img.shields.io/badge/version-0.3.x-green?style=flat&logo=GitHub&logoColor=white)
> Exploring `java.sql.DriverManager` , `java.sql.Connection`  , **`java.sql.PreparedStatement`** , `java.sql.ResultSet` features.

*fevvelasquez* writes an exercise using Java JDBC API for DAO pattern to get separate data access from business logic.

### MODEL layer:
* [Office](/src/me/fevvelasquez/jdbc/api/dao/model/Office.java) class models `offices` [database](/mysql-scripts/create-rh-db.sql) table.
* [Employee](/src/me/fevvelasquez/jdbc/api/dao/model/Employee.java) class models `employees` [database](/mysql-scripts/create-rh-db.sql) table.

### DAO layer:
DAO operations are defined in [DAO](/src/me/fevvelasquez/jdbc/api/dao/daoi/DAO.java) generic interface.
* [OfficeDAO](/src/me/fevvelasquez/jdbc/api/dao/dao/OfficeDAO.java) class implements [OfficeDAOI](/src/me/fevvelasquez/jdbc/api/dao/daoi/OfficeDAOI.java) which extends DAO generic interface.
* [EmployeeDAO](/src/me/fevvelasquez/jdbc/api/dao/dao/EmployeeDAO.java) class implements [EmployeeDAOI](/src/me/fevvelasquez/jdbc/api/dao/daoi/EmployeeDAOI.java) which extends DAO generic interface.

These classes provide CRUD database operations coding, including SQL statements needed.

### DAO Manager:

[DAOManager](/src/me/fevvelasquez/jdbc/api/dao/dao/DAOManager.java) *singleton* has been created to manage a unique database connection and providing DAO *immutable* instances access.

### Demo:

[Demo](/src/me/fevvelasquez/jdbc/api/dao/app/Demo.java) class, with *offices* and *employees* database tables CRUD examples, is available.

Even if *MySql* is used for the demo, a distinct *RDBMS* or distinct database can be configured in [db.properties](/src/db.properties).

### Recreating the, MySQL, demo database.
If needed, you can recreate the demo database using this [MySQL script](/mysql-scripts/create-rh-db.sql).

## Quick Preview.
> *DELETE*, *INSERT*, *UPDATE* and *SELECT* operations are performed on both *offices* and *employees* tables. 

Code for *offices* table from [Demo](/src/me/fevvelasquez/jdbc/api/dao/app/Demo.java) goes as follows:

```java

// Delete office
DAOManager.getOfficeDAO().delete(officeId);
// Insert office
insertOffice(officeId, "3320 Montes Urales", "Ciudad de Mexico", "ME");
// Update office
updateOffice(officeId, "1022 Fanta Fe", "Mexico City", "MX");
// Select All
List<Office> officeList = DAOManager.getOfficeDAO().getAll();

```

Code above produces next result:

![](/.gitimages/demo-1.png)
