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

- By default this application runs on: localhost:3300
- Swagger: http://localhost:3300/swagger-ui/index.html#



Improvement plan
---------------------
Development:
- I'd suggest implementing a proper user Authentication, with e-mail verification
- We need a real database
- More testing and error handling. Integration tests in staging environment.
- 
Deployment:
- I'd create a proper build pipeline, which would produce deployable assets for example a .jar or a Docker container containing the jar file.
- Then, I'd collaborate with the infra team to deploy it to an environment where it could be tested.