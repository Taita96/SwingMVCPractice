CREATE DATABASE if not exists store;
--
USE store;
--
CREATE TABLE IF NOT EXISTS users(
iduser int auto_increment primary key,
name VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL UNIQUE,
email VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(64) NOT NULL,
birthday DATE NOT NULL,
created_at DATE,
updated_at DATE
);
--
CREATE TABLE IF NOT EXISTS address(
idaddress int auto_increment primary key,
street VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL,
num_apartament VARCHAR(50) NOT NULL,
created_at DATE,
updated_at DATE
);
--
CREATE TABLE IF NOT EXISTS roles(
idrole VARCHAR(50) primary key,
created_at DATE,
updated_at DATE
);
--
CREATE TABLE IF NOT EXISTS users_has_roles(
iduser int,
idrole VARCHAR(50),
PRIMARY KEY (iduser,idrole),
FOREIGN KEY (iduser) REFERENCES users(iduser),
FOREIGN KEY (idrole) REFERENCES roles(idrole)
);
--
INSERT INTO roles(idrole,created_at,updated_at) VALUES ('ADMIN',CURRENT_DATE,CURRENT_DATE);
--
INSERT INTO roles(idrole,created_at,updated_at) VALUES ('CLIENT',CURRENT_DATE,CURRENT_DATE);
--
create procedure insertAdmin(
     p_name VARCHAR(50),
     p_lastname VARCHAR(50),
     p_username VARCHAR(50),
     p_email VARCHAR(50),
     p_password VARCHAR(64),
     p_birthday DATE,
     p_created_at DATE,
     p_updated_at DATE
)
begin
 DECLARE total INT;

 SELECT COUNT(*) INTO total FROM users;

 if(total = 0)
 THEN
          INSERT INTO users(name, lastname, username, email, password, birthday, created_at,updated_at)
        VALUES (p_name, p_lastname, p_username, p_email, p_password, p_birthday, p_created_at,p_updated_at);
    END IF;
END;
--
create procedure accessAdmin()
begin
 DECLARE total INT;
 DECLARE f_iduser INT;
 DECLARE f_idrole VARCHAR(50);

 SELECT COUNT(*) INTO total FROM users_has_roles;

 if(total = 0)
 THEN
    SELECT iduser INTO f_iduser FROM users WHERE username = 'admin' LIMIT 1;
    SELECT idrole INTO f_idrole FROM roles WHERE idrole = 'ADMIN';

          INSERT INTO users_has_roles(iduser,idrole)
        VALUES (f_iduser,f_idrole);
    END IF;
END;
