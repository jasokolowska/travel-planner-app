CREATE TABLE 'users' (
    'id' INT NOT NULL  AUTO_INCREMENT,
    'username' VARCHAR(50) NOT NULL,
    'password' VARCHAR(50) NOT NULL,
    'enabled' INT NOT NULL,
    PRIMARY KEY ('id')
);