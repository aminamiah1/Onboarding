GRANT SELECT, UPDATE, INSERT ON My_DB.* TO 'root'@'localhost';
DROP USER 'root'@'localhost';
DROP USER 'c21063494'@'localhost';
CREATE USER 'c21063494'@'localhost' IDENTIFIED BY 'comsc';
GRANT SELECT, UPDATE, INSERT ON My_DB.* TO 'c21063494'@'localhost';