# Spurven 
-. 3.Semester exam project.

# Explore Rest APIs (also known as Restful API)
The app defines following CRUD APIs.
API = application programming interface.
REST = architectural style.
REST = stands for representational state transfer and allows for interaction with RESTful web services.

# Annotations
Java annotations are metadata (data about data) for our program source code.
They provide additional information about the program to the compiler but are not part of the program itself.
These annotations do not affect the execution of the compiled program.

# JPA
JPA bruger objektet som skabalon til at oprette databasen.
Måden JPA gør dette er et @Entity.
Dette er selve Objektet.
Objektet sikker alt der sker i databasen.

# CRUD
Create = Post.
Read = Get.
Update = PUT.
Delete = Delete.

## Users
| Method    | Url                  | Description | Sample Valid Request Body |
|-----------|----------------------|-------------|---------------------------|
| GET       | users                | Get all     |                           |
| GET       | /username/{username} | Get all     |                           |
| POST      |                      | Create      | Ingen Create (POST)       |
| PUT       | /username/{username} | Update      |                           |
| DELETE    |                      | Delete      | Ingen Delete              |
|           |                      |             |                           |
| TEST      | Postman              | Done        |                           |
| Unit test | UserService          |             |                           |
| Unit test | UserWithRoles        |             |                           |
| Unit test |                      |             |                           |



## Contact
| Method     | Url                   | Description | Sample Valid Request Body |
|------------|-----------------------|-------------|---------------------------|
| GET        | contacts              | Get all     |                           |
| GET        | /contact/{contact_id} | Get all     |                           |
| POST       | /contact/{contact_id} | Create      |                           |
| PUT        | /contact/{contact_id} | Update      |                           |
| DELETE     | /contact/{contact_id} | Delete      |                           |
|            |                       |             |                           |
| TEST       | Postman               | Done        |                           |
| Unit test  | ContactService        |             |                           |
| Unit test  | ContactRepository     |             |                           |


## Event
| Method     | Url               | Description | Sample Valid Request Body |
|------------|-------------------|-------------|---------------------------|
| GET        | events            | Get all     |                           |
| GET        | /event/{id}       | Get all     |                           |
| POST       | /event/{id}       | Create      |                           |
| PUT        | /event/{id}       | Update      |                           |
| DELETE     | /event/{id}       | Delete      |                           |
|            |                   |             |                           |
| TEST       | Postman           | Done        |                           |
| Unit test  | ContactService    |             |                           |
| Unit test  | ContactRepository |             |                           |
