CREATE TABLE Clients
(
  clientKey VARCHAR (36) PRIMARY KEY,
  clientId BIGINT AUTO_INCREMENT,
  clientName VARCHAR (100),
  email VARCHAR (150)
);

CREATE TABLE Positions
(
  positionId BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR (50),
  location VARCHAR (50)
);