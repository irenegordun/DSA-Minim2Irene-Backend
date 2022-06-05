DROP DATABASE IF EXISTS marioDB;
CREATE DATABASE marioDB;
USE marioDB;

CREATE TABLE User (
    id VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    coins INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Item (
    id VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    damage INT,
    dmgReduction INT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Inventory (
    id VARCHAR(30) NOT NULL,
    userId VARCHAR(30) NOT NULL,
    itemId VARCHAR(30) NOT NULL,
    --active BOOLEAN,
    FOREIGN KEY (userId) REFERENCES User(id),
    FOREIGN KEY (itemId) REFERENCES Item(id)
) ENGINE = InnoDB;

INSERT INTO User VALUES ('fq3rv42rv', 'Alba', '1234', 'alba@upc.com', 500);
INSERT INTO Item VALUES ('fq34b563o', 'Gun', 'Ranged', 50, 'Weapon', 10, 0);
INSERT INTO Inventory VALUES ('1r3buoi2', 'fq3rv42rv', 'fq34b563o');