Java version is 1.8.0_131

Deployment

I Common information

1. Application is running on the 5050 port

2. Swagger Api Documentation is available at http://localhost:5050/swagger-ui.html

II Configuring database

Database connection settings are in the follow file:
src/main/resources/spring/database.properties

1. Install PostgreSQL 9.3
2. Execute the createdb.sql script within PostgreSQL 
console under superuser(it's postgres in here): 
psql -U postgres -a -f createdb.sql

The script will add "pscheduling" database as well as 
add new user with name "pscheduler" with access rights only to this database. 

III Build automation tool
Thereis Gradle as a build automation tool in this project.

To build and run project you could execute following
command in the terminate or console while you are in 
the root of project:
1. ./gradlew clean build
2. java -jar build/libs/procedurescheduling-1.0-SNAPSHOT.jar

IV Docker
You can run this application using by docker image
Some gradle tasks was created to build and push a new docker image 
to the hub repository.