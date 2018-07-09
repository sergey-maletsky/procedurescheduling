Deployment

I Common information

1. Application is running on the 5050 port

2. Swagger Api Documentation is available at http://localhost:5050/swagger-ui.html

II Configuring database

Database connection settings are in the follow file:
src/main/resources/spring/database.properties

1. Install PostgreSQL
2. Execute the createdb.sql script within PostgreSQL 
console under superuser(it's postgres in here): 
psql -U postgres -a -f createdb.sql

The script will add "pscheduling" database as well as 
add new user with name "pscheduler" with access rights only to this database. 