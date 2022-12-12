CREATE SCHEMA IF NOT EXISTS My_Db;

USE My_Db;

DROP TABLE IF EXISTS Personal_Information;
DROP TABLE IF EXISTS Candidate_References;
DROP TABLE IF EXISTS Vetting_Officers;
DROP TABLE IF EXISTS Applications;
DROP TABLE IF EXISTS Candidates;
DROP TABLE IF EXISTS Master_Admin;

-- -----------------------------------------------------
-- TABLE `Candidates`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Candidates
(
    ID           int          not null auto_increment primary key,
    First_Name   varchar(100) not null,
    Surname      varchar(100) not null,
    Email        varchar(200) not null unique,
    Password     varchar(100) not null unique,
    Company_Name varchar(100) not null,
    constraint candidateEmailAndPasswordLengthOver1CharacterCheck
        check (LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Personal_Information`
-- -----------------------------------------------------

create table Personal_Information
(
    ID                 int auto_increment not null primary key,
    CID                int                not null,
    National_Insurance varchar(9) unique,
    Ethnicity          varchar(50),
    Gender             varchar(50),
    Age                int,
    Telephone_Number   varchar(50),
    constraint PI_candidate_fk
        foreign key (CID) references candidates (ID),
    constraint personalInfoAgeBetween18And120Check
        check (Age > 18 AND Age < 120)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Vetting_Officers`
-- -----------------------------------------------------

create table Vetting_Officers
(
    ID         int auto_increment not null primary key,
    First_Name varchar(100)       not null,
    Surname    varchar(100)       not null,
    Email      varchar(200)       not null unique,
    Password   varchar(100)       not null,
    constraint vettingOfficerEmailAndPasswordLengthOver1CharacterCheck
        check (LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Candidate_References`
-- -----------------------------------------------------

create table Candidate_References
(
    ID                   int auto_increment not null primary key,
    CID                  int                not null,
    Referee_Name         varchar(100)       not null,
    Referee_Phone_Number varchar(20)        not null unique,
    constraint refereePhoneNumberFormatCheck
        check (LENGTH(Referee_Phone_Number) > 9 AND LENGTH(Referee_Phone_Number) <= 13),
    constraint candidate_references__fk
        foreign key (CID) references candidates (ID)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Applications`
-- -----------------------------------------------------

create table Applications
(
    ID         int auto_increment not null primary key,
    App_Status varchar(100)       not null,
    CID        int                not null,
    constraint appStatusLength
        check (LENGTH(App_Status) > 1 AND LENGTH(App_Status) < 30),
    constraint applications_candidates_fk
        foreign key (CID) references candidates (ID)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `MasterAdmin`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Master_Admin
(
    ID       int auto_increment not null primary key,
    Email    varchar(200)       not null unique,
    Password varchar(100)       not null,
    constraint masterAdminEmailAndPasswordLengthOver1CharacterCheck
        check (LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;

/* SHOW VARIABLES; -- returns all system variable --

SHOW VARIABLES WHERE Variable_name = 'port';

SELECT @@port;

SHOW VARIABLES WHERE Variable_name = 'hostname';

SELECT @@hostname;

SELECT CONNECTION_ID();

USE mysql;

SHOW tables;

SELECT * FROM user; -- user table accepts and rejects a connection from a host --
SELECT * FROM db; -- contains database level privileges --
SELECT * FROM tables_priv;
SELECT * FROM columns_priv;
SELECT * FROM procs_priv;

SELECT user(); -- checks the current user --

DROP USER 'miah9'@'localhost'; -- drops users so we can create them--
DROP USER 'milliganec'@'localhost';

CREATE USER 'miaha9'@'localhost' IDENTIFIED BY 'comsc'; -- creates a new user who can connect from a local host ONLY --
SHOW GRANTS FOR 'miaha9'@'localhost';
GRANT ALL PRIVILEGES ON * . * TO 'miaha9'; -- grants user privileges --

CREATE USER 'milliganec'@'localhost' IDENTIFIED BY 'comsc';
SHOW GRANTS FOR 'milliganec'@'localhost'; -- grants user privileges --

USE mysql;

Set @authstring = (SELECT authentication_string
                   FROM user WHERE User='miaha9' and Host='localhost');
-- the authentication string has been hashed using mysql-native-password

SELECT plugin FROM user WHERE User='miaha9' and Host='localhost';
SELECT plugin FROM user WHERE User='milliganec' and Host='localhost';
-- password passes it to the authentication plugin --
*/

