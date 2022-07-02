CREATE TABLE Clients
(
  clientKey VARCHAR (36) PRIMARY KEY,
  clientId BIGINT AUTO_INCREMENT,
  clientName VARCHAR (100),
  email VARCHAR (150)
);