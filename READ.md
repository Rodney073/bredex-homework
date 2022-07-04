Bredex Backend homework by Rodney073
====================



What is this?
---------------------
* My homework project for Bredex as part of the job application process

Short description
---------------------
* This project is a super simple jobboard where clients can register then upload and search for open positions. 

How to run
---------------------

To use this project, you are going to need;

- Java JDK 11
- Maven compatible with JDK 11
- Any Java IDE
- [Postman tool](https://www.getpostman.com/) (optional, will be used for testing web service)

* You can run the service by running the following commands:
```
git clone https://github.com/Rodney073/bredex-homework.git
cd bredex-homework
./mvnw package
java -jar target/*.jar
```

![registerClient](doc/ClientController_registerClient.svg)

![getClient](doc/PositionController_getPositionById.svg)

![Alt text](doc/ClientController_registerClient.svg)

The application is running on: localhost:3300/

Swagger:
http://localhost:3300/swagger-ui/index.html#


localhost:3300/client
localhost:3300/position
localhost:3300/position/search