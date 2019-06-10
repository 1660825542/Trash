CREATE TABLE userinfo(
   userid INT PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(40),
   sex VARCHAR(2),
   email VARCHAR(100),
   levelid INT 
);
ALTER TABLE userinfo ADD CONSTRAINT fk_userinfo_001 FOREIGN KEY(levelid) REFERENCES userlevel(levelid);

CREATE TABLE userlevel(
   levelid INT PRIMARY KEY AUTO_INCREMENT,
   levelname  VARCHAR(100) ,
   leveltxt VARCHAR(100)
);

SELECT * FROM userinfo;
SELECT * FROM userlevel;